package Model;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionID;
    private int bankAccountNumberID;
    private int affiliatedBankID;
    private String transactionType;
    private float amount;
    private LocalDateTime transactionDateTime;
    private String requestStatus;
    private String OTP;

    public Transaction(){}

    public Transaction(int transactionID, int bankAccountNumberID, int affiliatedBankID, String transactionType, float amount, LocalDateTime transactionDateTime, String requestStatus, String OTP) {
        this.transactionID = transactionID;
        this.bankAccountNumberID = bankAccountNumberID;
        this.affiliatedBankID = affiliatedBankID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
        this.requestStatus = requestStatus;
        this.OTP = OTP;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getBankAccountNumberID() {
        return bankAccountNumberID;
    }

    public void setBankAccountNumberID(int bankAccountNumberID) {
        this.bankAccountNumberID = bankAccountNumberID;
    }

    public int getAffiliatedBankID() {
        return affiliatedBankID;
    }

    public void setAffiliatedBankID(int affiliatedBankID) {
        this.affiliatedBankID = affiliatedBankID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
