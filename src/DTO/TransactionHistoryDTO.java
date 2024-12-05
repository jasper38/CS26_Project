package DTO;

import java.sql.Date;
import java.sql.Timestamp;

public class TransactionHistoryDTO {
    private int transactionID;
    private int bankAccountNumberID;
    private String bankName;
    private String transactionType;
    private float amount;
    private Timestamp transactionDateTime;
    private String requestStatus;
    private String OTP;

    public TransactionHistoryDTO() {}

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public Timestamp getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Timestamp transactionDateTime) {
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
