package service;

import controllers.UserController;
import model.Transaction;
import model.User;

import java.util.*;

public class UserService {
    private Scanner scanner = new Scanner(System.in);
    private UserController userController;

    private List<Transaction> transactionList = new ArrayList<>();

    private List<String> historyTransactionList;
    private List<String> historyUserActions;

    private boolean isAuthorized;   // пользователь авторизован

    public UserService() {
        userController = new UserController();
        historyTransactionList = new ArrayList<>();
        historyUserActions = new ArrayList<>();
    }

    public void start() {
        while (!isAuthorized) {
            System.out.print("Меню:\nВыберите действие:\n1 - регистрация пользователя\n2 - авторизация пользователя\n");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Регистрация пользователя");
                    System.out.println("Введите логин: ");
                    String login = scanner.next();
                    if (userController.getUserMap().containsKey(login)) {
                        System.out.println("Пользователь с таким логином уже зарегистрирован!");
                        break;
                    }
                    System.out.println("Введите пароль: ");
                    String password = scanner.next();

                    System.out.println("Введите баланс на счёте: ");
                    float balance = scanner.nextFloat();

                    userController.registerUser(new User(login, password, balance));
                    break;
                case 2:
                    System.out.println("Авторизация пользователя");
                    System.out.println("Введите логин пользователя: ");
                    login = scanner.next();
                    System.out.println("Введите пароль: ");
                    password = scanner.next();
                    isAuthorized = userController.authorizeUser(new User(login, password));
                    if (isAuthorized) {
                        historyUserActions.add("Авторизация");
                    }
                    break;
            }
        }
        System.out.println("Текущий баланс игрока: " + userController.getCurrentUser().getBalance());

        boolean isExit = false;
        while (!isExit) {
            printTransactions();
            System.out.println("1 - Зарегистрировать транзакцию");
            System.out.println("2 - Выполнить транзакцию");
            System.out.println("3 - Выход");
            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    registerTransactions();
                    break;
                case 2:
                    DoTransaction();
                    break;
                case 3:
                    System.out.println("Выход");
                    historyUserActions.add("Завершение работы");
                    isExit = true;
                    break;
                default:
                    System.out.println("Введите пункт меню (1, 2 или 3)");
                    break;
            }
        }

        for (String s : historyUserActions) {
            System.out.println(s);
        }
        System.out.println("Текущий баланс игрока: " + userController.getCurrentUser().getBalance());


        System.out.println("\nИстория операций:");     // история операций
        for (String s : historyTransactionList) {
            System.out.println(s);
        }

        System.out.println("\nАудит действий игрока:");     // аудит действий игрока
        for (String s : historyUserActions) {
            System.out.println(s);
        }
    }


    private void DoTransaction() {
        System.out.println("Введите Id транзакции(целое число): ");
        int idTransaction = scanner.nextInt();
        if (!transactionList.isEmpty()) {
            boolean isTransactionFinished = false;
            for (Transaction transaction : transactionList) {
                if (transaction.getId() == idTransaction) {
                    runTransaction(userController.getCurrentUser(), transaction);
                    isTransactionFinished = true;
                    break;
                }
            }
            if (!isTransactionFinished) {
                System.out.println("Нет транзакции с таким Id!");
            }

        } else {
            System.out.println("Нет ни одной транзакции!");
        }
    }

    public void runTransaction(User user, Transaction transaction) {
        switch (transaction.getTypeOfTransaction()) {
            case DEBIT:
                if (user.getBalance() - transaction.getAmount() >= 0) {
                    user.setBalance(user.getBalance() - transaction.getAmount());
                    historyTransactionList.add("Id транзакции: " + transaction.getId() + "|сумма: " + transaction.getAmount() + "|тип операции: дебет|УСПЕШНО");
                    historyUserActions.add("Снятие");
                } else {
                    historyTransactionList.add("Id транзакции: " + transaction.getId() + "|сумма: " + transaction.getAmount() + "|тип операции: дебет|НЕ УСПЕШНО(НЕ ДОСТАТОЧНО СРЕДСТВ)");
                }
                break;
            case CREDIT:
                user.setBalance(user.getBalance() + transaction.getAmount());
                historyTransactionList.add("Id транзакции: " + transaction.getId() + "|сумма: " + transaction.getAmount() + "|тип операции: кредит|УСПЕШНО");
                historyUserActions.add("Пополнение");
                break;
        }
    }

    private void registerTransactions() {
        boolean isEndReagistrationTransactions = false;
        while (!isEndReagistrationTransactions) {
            printTransactions();
            System.out.println("Регистрация транзакций");
            System.out.println("Введите id транзакции(целое число): ");
            int id = scanner.nextInt();

            if (checkUniqueIdTransaction(id)) {       // проверка Id транзакции на уникальность
                System.out.println("Введите сумму транзакции: ");
                float amount = scanner.nextFloat();
                System.out.println("Введите тип транзакции(1 - Дебет(снятие) | 2 - Кредит(пополнение");
                int intTypeOfOperation = scanner.nextInt();
                switch (intTypeOfOperation) {
                    case 1:
                        transactionList.add(new Transaction(id, amount, Transaction.TypeOfTransaction.DEBIT));
                        break;
                    case 2:
                        transactionList.add(new Transaction(id, amount, Transaction.TypeOfTransaction.CREDIT));
                        break;
                    default:
                        System.out.println("Вы ввели неверный тип транзакции!");
                        break;
                }
                System.out.println("Повторить регистрацию транзакций? 1 - ДА | 2 - НЕТ");

                switch (scanner.nextInt()) {
                    case 1:
                        break;
                    case 2:
                        isEndReagistrationTransactions = true;
                        break;
                }
            } else {
                System.out.println("Транзакция с таким Id уже существует!");
                break;
            }
        }
    }

    private boolean checkUniqueIdTransaction(int id) {
        for (Transaction transaction : transactionList) {
            if (id == transaction.getId()) {
                return false;
            }
        }
        return true;
    }

    private void printTransactions() {
        if (!transactionList.isEmpty()) {
            System.out.println("Список транзакций: ");
            for (Transaction transaction : transactionList) {
                System.out.println("id=" + transaction.getId() + " | сумма: " +
                        transaction.getAmount() + " | тип: " + transaction.getTypeOfTransaction().toString());
            }
        }
    }
}
