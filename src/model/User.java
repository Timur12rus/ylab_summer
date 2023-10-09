package model;

public class User {

    private static int nextUser;
    private int id;
    private String login;

    private String password;
    private float balance;

    public User(String login, String password, float balance) {
        this.login = login;
        this.balance = balance;
        id = nextUser++;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
