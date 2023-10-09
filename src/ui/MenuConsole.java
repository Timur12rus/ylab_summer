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
            System.out.print("Меню:\nВыберите действие:\n1 - регистрция пользователя\n2 - авторизация пользователя\n");
            switch (scanner.nextInt()) {
                case 1:
                    userController.registerUser(createUserForRegistration());
                    break;
                case 2:
                    User user = getUserForAuthorisation();
                    isAuthorized = userController.authorizeUser(user);
                    break;
            }
        }
        System.out.println("Текущий баланс игрока: " + userController.getCurrentUser().getBalance());

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
//        if (scanner.hasNextLine()) {
        String login = scanner.next();
//        }

        System.out.println("Введите пароль: ");
//        if (scanner.hasNextLine()) {
        String password = scanner.next();
//        }

        return new User(login, password);
    }
}
