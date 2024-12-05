package Controller;

import DTO.LogInRequestDTO;
import DTO.LogInResult;
import DTO.RegistrationRequestDTO;
import DTO.TransactionHistoryDTO;
import Service.IMBankServiceImpl;
import Utility.ViewUtility;
import View.LogInWindow;
import View.MainWindow;
import View.RegisterWindow;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class IMBankController {
    private final IMBankServiceImpl bankService;
    private LogInWindow logInWindow;
    private RegisterWindow registerWindow;
    private MainWindow mainWindow;

    public IMBankController(IMBankServiceImpl bankService) {
        this.bankService = bankService;
        this.logInWindow = new LogInWindow(this);
        this.registerWindow = new RegisterWindow(this);
        this.mainWindow = new MainWindow(this);
    }

    public void registerPerson(RegistrationRequestDTO registrationRequestDTO) {
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws SQLException {
                return bankService.registerPerson(registrationRequestDTO);
            }

            @Override
            protected void done() {
                try {
                    boolean registrationSuccessful = get();
                    if (registrationSuccessful) {
                        showLoginWindow();
                        ViewUtility.showMessage("Registration Successful! Please claim your ATM Card at the Bank.");
                    }else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    ViewUtility.showMessage("An Error occured during registration.");
                }
            }
        };
        worker.execute();
    }

    public void verifyLogin(LogInRequestDTO logInRequest) {
        SwingWorker<LogInResult, Void> worker = new SwingWorker<>() {
            @Override
            protected LogInResult doInBackground() throws Exception {
                Thread.sleep(100);
                return bankService.verifyLogIn(logInRequest);
            }

            @Override
            protected void done() {
                try {
                    LogInResult result = get();
                    if (result.isSuccess()) {
                        getAccountBalance();
                        showMainWindow();
                    } else {
                        ViewUtility.showMessage(result.getMessage());
                    }
                } catch (Exception e) {
                    ViewUtility.showMessage("An unexpected error occurred: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void getAccountBalance() {
        SwingWorker<Float, Void> worker = new SwingWorker<Float, Void>(){
            @Override
            protected Float doInBackground() throws Exception {
                return bankService.getBankAccountBalance();
            }
            @Override
            protected void done() {
                try {
                    float bankAccountBalance = get();
                    mainWindow.setDisplayBalanceField(String.valueOf(bankAccountBalance));
                } catch (Exception e) {
                    ViewUtility.showMessage("Failed to retrieve balance");
                }
            }
        };
        worker.execute();
    }

    public void authenticateBankCredentials(int bankAccountNumberID, int cardPIN){
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                try{
                    return bankService.verifyBankAccountCredentials(bankAccountNumberID, cardPIN);
                } catch (SQLException se) {
                    throw new Exception(se.getMessage());
                }
            }

            @Override
            protected void done() {
                try{
                    boolean isVerified = get();
                    if (isVerified) {
                        mainWindow.createPopUpWindow2();
                    } else {
                        throw new Exception("Invalid credentials");
                    }
                } catch (Exception e) {
                    ViewUtility.showMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void initiateTransactionRequest(String transactionType, String selectedBank, int amount){
        SwingWorker<Integer, Void> worker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                try{
                    return bankService.createTransaction(transactionType ,selectedBank, amount);
                } catch (Exception e){
                    throw new Exception(e.getMessage());
                }
            }
            @Override
            protected void done() {
                try{
                    int OTP = get();
                    if (OTP > 0) {
                        ViewUtility.showMessage("Transaction Created. OTP: " + OTP);
                    }
                } catch (Exception e) {
                    ViewUtility.showMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void getTransactionHistory(){
        SwingWorker<List<TransactionHistoryDTO>, Void> worker = new SwingWorker<List<TransactionHistoryDTO>, Void>() {
            @Override
            protected List<TransactionHistoryDTO> doInBackground() throws Exception {
                return bankService.getTransactions();
            }

            @Override
            protected void done() {
                try{
                    mainWindow.getTableModel().setNumRows(0);
                    List<TransactionHistoryDTO> transactions = get();
                    for(TransactionHistoryDTO transaction : transactions){
                        mainWindow.udpateTransactionHistoryTable(transaction);
                    }
                } catch (Exception e){
                    ViewUtility.showMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    //TO DO: profile panel
    //logout validation securely method here

    public void showLoginWindow() {
        ViewUtility.show(logInWindow.getLoginFrame());
        ViewUtility.hide(registerWindow.getRegisterFrame());
        ViewUtility.hide(mainWindow.getMainFrame());
    }

    public void showRegisterWindow() {
        ViewUtility.show(registerWindow.getRegisterFrame());
        ViewUtility.hide(logInWindow.getLoginFrame());
        ViewUtility.hide(mainWindow.getMainFrame());
    }

    public void showMainWindow() {
        ViewUtility.show(mainWindow.getMainFrame());
        ViewUtility.hide(logInWindow.getLoginFrame());
        ViewUtility.hide(registerWindow.getRegisterFrame());
    }
}
