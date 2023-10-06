package ui;

import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Меню:\nВыберите действие:");

        if (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    startRegistration();
                    break;
                case 2:
                    authorizeUser();
                    break;
            }
        }
    }

    public void startRegistration() {
        String name;
        String password;
        System.out.println("Регистрация пользователя:\nВведите имя пользователя: ");
        if (scanner.hasNextLine()) {
            name = scanner.nextLine();
        }

        System.out.println("Введите пароль: ");
        if (scanner.hasNextLine()) {
            password = scanner.nextLine();
        }
    }

    public void authorizeUser() {
        System.out.println("Введите имя пользователя: ");
        if (scanner.hasNextLine()) {
            String userName = scanner.nextLine();
            //TODO authorize user by username
        }
    }
}
