package ui;

import model.User;

import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Меню:\nВыберите действие:");

        if (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    authorizeUser();
                    break;
            }
        }
    }

    // TODO refactorMethods

    public User registerUser() {
        String login = null;
        String password = null;
        float balance = 0;
        System.out.println("Регистрация пользователя:\nВведите логин пользователя: ");
        if (scanner.hasNextLine()) {
            login = scanner.nextLine();
        }

        System.out.println("Введите пароль: ");
        if (scanner.hasNextLine()) {
            password = scanner.nextLine();
        }

        System.out.println("Введите баланс на счёте: ");
        if (scanner.hasNextFloat()) {
            balance = scanner.nextFloat();
        }
        return new User(login, password, balance);
    }

    public User authorizeUser() {
        String login = null;
        String password = null;
        float balance = 0;
        User user = null;

        System.out.println("Введите логин пользователя: ");
        if (scanner.hasNextLine()) {
            login = scanner.nextLine();
        }

        System.out.println("Введите пароль: ");
        if (scanner.hasNextLine()) {
            password = scanner.nextLine();
        }

        user = new User(login, password);

        for (User u : userList) {
            if (user.equals(u)) {

            }
        }
    }
}
