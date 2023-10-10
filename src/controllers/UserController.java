package controllers;

import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {

    private Map<String, User> userMap = new HashMap();

    private User currentUser;

    // метод для регистрации пользователя
    // добавим нового пользователя в список пользователей
    public void registerUser(User user) {
        userMap.put(user.getLogin(), user);
        System.out.println("Пользователь успешно зарегистрирован!");
    }

    // метод для авторизации пользователя
    public boolean authorizeUser(User user) {
        if (userMap.containsKey(user.getLogin()) && userMap.get(user.getLogin()).getPassword().equals(user.getPassword())) {
            currentUser = userMap.get(user.getLogin());  // получили текущего пользователя(авторизованного)
            System.out.println("Авторизация прошла успешно!");
            return true;
        } else {
            System.out.println("Ошибка авторизации!");
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }



}
