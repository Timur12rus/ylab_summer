package service;

import controllers.UserController;
import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserService {
    private Scanner scanner = new Scanner(System.in);
    private UserController userController;

    private List<Transaction> transactionList;

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

        // совершаем транзакции
        doTransactions(userController.getCurrentUser());
        for (String s : historyUserActions) {
            System.out.println(s);
        }
        System.out.println("Текущий баланс игрока: " + userController.getCurrentUser().getBalance());

        exit();

        System.out.println("\nИстория операций:");     // история операций
        for (String s : historyTransactionList) {
            System.out.println(s);
        }

        System.out.println("\nАудит действий игрока:");     // аудит действий игрока
        for (String s : historyUserActions) {
            System.out.println(s);
        }
    }

    public void doTransactions(User user) {
        transactionList = createTransactions();
        for (Transaction transaction : transactionList) {
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
    }

    public void exit() {
        historyUserActions.add("Завершение работы");
    }

    public List<Transaction> createTransactions() {
        transactionList = new ArrayList<>();
        Transaction transaction1 = new Transaction(generateUniqueId(), 150, Transaction.TypeOfTransaction.CREDIT);
        if (checkUniqueIdTransaction(transaction1)) {
            transactionList.add(transaction1);
        }
        Transaction transaction2 = new Transaction(generateUniqueId(), 300, Transaction.TypeOfTransaction.DEBIT);
        if (checkUniqueIdTransaction(transaction2)) {
            transactionList.add(transaction2);
        }
        Transaction transaction3 = new Transaction(generateUniqueId(), 50, Transaction.TypeOfTransaction.CREDIT);
        if (checkUniqueIdTransaction(transaction3)) {
            transactionList.add(transaction3);
        }
        return transactionList;
    }

    private boolean checkUniqueIdTransaction(Transaction newTransaction) {
        for (Transaction transaction : transactionList) {
            if (transaction.getId().equals(newTransaction.getId())) {
                System.out.println("Ошибка! Id транзакции не уникальный!");
                return false;
            }
        }
        return true;
    }

    private String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
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
