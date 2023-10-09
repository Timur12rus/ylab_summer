package controllers;

import model.User;
import ui.MenuConsole;

import java.util.HashSet;
import java.util.Set;

public class UserController {
    private Set<User> usersSet = new HashSet<>();

    private MenuConsole menuConsole;

    private User currentUser;

    // метод для регистрации пользователя
    // добавим нового пользователя в список пользователей
    public void registerUser(User user) {
        if (!usersSet.contains(user)) {
            usersSet.add(user);
            System.out.println("Пользователь успешно зарегистрирован!");
        } else {
            System.out.println("Пользователь с таким логином уже зарегистрирован!");
        }
    }

    // метод для авторизации пользователя
    public boolean authorizeUser(User user) {
        if (usersSet.contains(user)) {
            System.out.println("Авторизация прошла успешно!");
            currentUser = user;  // получили текущего пользователя(авторизованного)
            return true;
        } else {
            System.out.println("Ошибка авторизации!");
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
