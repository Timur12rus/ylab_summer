package model;

import java.util.Objects;

public class User {

    private static int nextUser;
    private int id;

    private String login;


    private String password;
    private float balance;

    public User(String login, String password, float balance) {
        this.login = login;
        this.password = password;
        this.balance = balance;
        id = nextUser++;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public float getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }
}
