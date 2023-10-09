package model;

public class Transaction {
    private int id;

    public TypeOfTransaction getTypeOfTransaction() {
        return typeOfTransaction;
    }

    private float amount;

    private TypeOfTransaction typeOfTransaction;

    public Transaction(int id, float amount, TypeOfTransaction typeOfTransaction) {
        this.id = id;
        this.amount = amount;
        this.typeOfTransaction = typeOfTransaction;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum TypeOfTransaction {
        DEBIT,
        CREDIT
    }
}
