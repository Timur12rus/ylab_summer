package model;

public class User {

    private static int nextUser;
    private int id;
    private String name;

    private String password;
    private float balance;

    public User(String name, float balance) {
        this.name = name;
        this.balance = balance;
        id = nextUser++;
    }
}
