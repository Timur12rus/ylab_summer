package controllers;

import model.User;
import ui.Console;

import java.util.List;

public class UserController {
    private List<User> userList;

    private Console console;

    // метод для регистрации пользователя
    // добавим нового пользователя в список пользователей
    public void registerUser(User user) {
        userList.add(console.registerUser());
    }

    // метод для авторизации пользователя
    public void authorizeUser(User user) {

    }
}
