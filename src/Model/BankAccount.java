package Model;

import java.sql.Date;

public class BankAccount {
    private int bankAccountNumberID;
    private int customerID;
    private String bankAccountType;
    private float bankAccountBalance;
    private Date dateOpened;
    private Date dateClosed;

    public BankAccount(){};

    public int getBankAccountNumberID() {
        return bankAccountNumberID;
    }

    public void setBankAccountNumberID(int bankAccountNumberID) {
        this.bankAccountNumberID = bankAccountNumberID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public float getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(float bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }
}
