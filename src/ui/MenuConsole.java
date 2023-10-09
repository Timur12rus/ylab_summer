package ui;

import controllers.UserController;
import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private Scanner scanner = new Scanner(System.in);
    private UserController userController;

    private List<Transaction> transactionList;

    private List<String> historyTransactionList;
    private List<String> historyUserActions;

    private boolean isAuthorized;   // пользователь авторизован

    public MenuConsole() {
        userController = new UserController();
        historyTransactionList = new ArrayList<>();
        historyUserActions = new ArrayList<>();
    }

    public void showMenu() {
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

        doTransactions(userController.getCurrentUser());
    }

    public void doTransactions(User user) {
        transactionList = createTransactions();
        for (Transaction transaction : transactionList) {
            switch (transaction.getTypeOfTransaction()) {
                case DEBIT:
                    if (user.getBalance() - transaction.getAmount() >= 0) {
                        user.setBalance(user.getBalance() - transaction.getAmount());
                        historyTransactionList.add("Id транзакции: " + transaction.getId() + "/сумма: " + transaction.getAmount() + "/тип операции: дебет");
                        historyUserActions.add("Снятие");
                    } else {
                        System.out.println("Не достаточно средств на счёте!");
                    }
                    break;
                case CREDIT:
                    user.setBalance(user.getBalance() + transaction.getAmount());
                    historyTransactionList.add("Id транзакции: " + transaction.getId() + "/сумма: " + transaction.getAmount() + "/тип операции: кредит");
                    historyUserActions.add("Пополнение");
            }
        }
    }

    public void exit() {
        historyUserActions.add("Завершение работы");
    }

    public List<Transaction> createTransactions() {
        transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1, 150, Transaction.TypeOfTransaction.CREDIT));
        transactionList.add(new Transaction(2, 55, Transaction.TypeOfTransaction.DEBIT));
        transactionList.add(new Transaction(3, 25, Transaction.TypeOfTransaction.CREDIT));
        return transactionList;
    }

    // создает пользователя для регистрации
    public User createUserForRegistration() {
        System.out.println("Регистрация пользователя");
        System.out.println("Введите логин: ");
        String login = scanner.next();

        System.out.println("Введите пароль: ");
        String password = scanner.next();

        System.out.println("Введите баланс на счёте: ");
        float balance = scanner.nextFloat();

        return new User(login, password, balance);
    }

    public User getUserForAuthorisation() {
        System.out.println("Авторизация пользователя");
        System.out.println("Введите логин пользователя: ");
        String login = scanner.next();

        System.out.println("Введите пароль: ");
        String password = scanner.next();

        return new User(login, password);
    }
}
