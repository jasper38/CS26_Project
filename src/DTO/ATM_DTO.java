package DTO;

public class ATM_DTO {
    private int transactionID;
    private long cardNumber;
    private String transactionType;
    private double transactionAmount;
    private int transactionBankID;
    private double bankAccountBalance;
    private int bankAccountNumberID;

    public ATM_DTO(int transactionID, long cardNumber, String transactionType, double transactionAmount, int transactionBankID, double bankAccountBalance, int bankAccountNumberID) {
        this.setTransactionID(transactionID);
        this.setCardNumber(cardNumber);
        this.setTransactionType(transactionType);
        this.setTransactionAmount(transactionAmount);
        this.setTransactionBankID(transactionBankID);
        this.setBankAccountBalance(bankAccountBalance);
        this.setBankAccountNumberID(bankAccountNumberID);
    }
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionBankID() {
        return transactionBankID;
    }

    public void setTransactionBankID(int transactionBankID) {
        this.transactionBankID = transactionBankID;
    }

    public double getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public int getBankAccountNumberID() {
        return bankAccountNumberID;
    }

    public void setBankAccountNumberID(int bankAccountNumberID) {
        this.bankAccountNumberID = bankAccountNumberID;
    }

    @Override
    public String toString() {
        return "ATM_DTO{" +
                "transactionID=" + transactionID +
                ", cardNumber=" + cardNumber +
                ", transactionType='" + transactionType +
                ", transactionAmount=" + transactionAmount +
                ", transactionBankID=" + transactionBankID +
                ", bankAccountBalance=" + bankAccountBalance +
                ", bankAccountNumberID=" + bankAccountNumberID +
                '}';
    }
}
