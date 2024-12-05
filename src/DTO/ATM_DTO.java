package DTO;

public class ATM_DTO {
    private String fullName;
    private String transactionType;
    private float amount;
    private int transactionID;

    public ATM_DTO(String fullName, String transactionType, float amount, int transactionID) {
        this.fullName = fullName;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionID = transactionID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public float getBalance() {
        return amount;
    }

    public int getTransactionID() {
        return transactionID;
    }
}
