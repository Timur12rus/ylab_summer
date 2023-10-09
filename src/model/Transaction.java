package model;

public class Transaction {
    private String id;

    public TypeOfTransaction getTypeOfTransaction() {
        return typeOfTransaction;
    }

    private float amount;

    private TypeOfTransaction typeOfTransaction;

    public Transaction(String id, float amount, TypeOfTransaction typeOfTransaction) {
        this.id = id;
        this.amount = amount;
        this.typeOfTransaction = typeOfTransaction;
    }

    public String getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public enum TypeOfTransaction {
        DEBIT,
        CREDIT
    }
}
