package ui;

import controllers.UserController;
import model.User;

import java.util.Scanner;

public class MenuConsole {
    private Scanner scanner = new Scanner(System.in);
    private UserController userController;

    private boolean isAuthorized;   // пользователь авторизован

    public MenuConsole() {
        userController = new UserController();
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
                    break;
            }
        }
        System.out.println("Текущий баланс игрока: " + userController.getCurrentUser().getBalance());
        System.out.println("Введите идентификатор транзакции: ");
        int transactionId = scanner.nextInt();

        // TODO doTransaction()
//        doTransaction();

//        System.out.println("Выберите операцию: \n1 - Дебет/снятие средств\n2 - Кредит на игрока/пополнение средств");
//        switch (scanner.next()) {
//            case 1:
//                userController.debet();
//                break;
//            case 2:
//                userController.credit();
//        }
    }

    public void doTransaction(int transactionId) {

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
