package Controller;

import DTO.*;
import Service.IMBankServiceImpl;
import Utility.ViewUtility;
import View.LogInWindow;
import View.MainWindow;
import View.RegisterWindow;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class IMBankController {

    private LogInWindow logInWindow;
    private RegisterWindow registerWindow;
    private MainWindow mainWindow;
    private Timer activeTransactionTimer;

    private final IMBankServiceImpl bankService;

    public IMBankController(IMBankServiceImpl bankService) {
        this.bankService = bankService;
        SwingUtilities.invokeLater(() -> {
            this.logInWindow = new LogInWindow(this);
            this.registerWindow = new RegisterWindow(this);
            this.mainWindow = new MainWindow(this);
        });
    }

    public void registerPerson(RegistrationRequestDTO registrationRequestDTO) {
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
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
                        ViewUtility.showInfoMessage("Registration Successful! Please claim your ATM Card at the Bank.");
                    }else {
                        throw new Exception("An Error occurred during registration.");
                    }
                } catch (Exception e) {
                    ViewUtility.showErrorMessage(null,e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void verifyLogin(LogInRequestDTO logInRequest) {
        SwingWorker<LogInResult, Void> worker = new SwingWorker<>() {
            @Override
            protected LogInResult doInBackground() throws Exception {
                Thread.sleep(30);
                return bankService.verifyLogIn(logInRequest);
            }

            @Override
            protected void done() {
                try {
                    LogInResult result = get();
                    if (result.isSuccess()) {
                        getName();
                        getAccountBalance();
                        showMainWindow();
                    } else {
                        ViewUtility.showInfoMessage(result.getMessage());
                    }
                } catch (Exception e) {
                    ViewUtility.showErrorMessage(null, e.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void getName(){
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                return bankService.getName();
            }
            @Override
            protected void done() {
                try {
                    String name = get();
                    if(name.isEmpty()) {
                        throw new Exception("An unexpected error occurred.");
                    }
                    mainWindow.updateHeaderLbl(get());
                } catch (Exception e) {
                    ViewUtility.showErrorMessage(null, e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void getAccountBalance() {
        SwingWorker<Float, Void> worker = new SwingWorker<>(){
            @Override
            protected Float doInBackground() throws Exception {
                return bankService.getBankAccountBalance();
            }
            @Override
            protected void done() {
                try {
                    float bankAccountBalance = get();
                    if (bankAccountBalance == 0) {
                        throw new Exception("Failed to retrieve account balance.");
                    }
                    mainWindow.setDisplayBalanceField(String.valueOf(bankAccountBalance));
                } catch (Exception e) {
                    ViewUtility.showInfoMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void authenticateBankCredentials(int bankAccountNumberID, int cardPIN){
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return bankService.verifyBankAccountCredentials(bankAccountNumberID, cardPIN);
            }

            @Override
            protected void done() {
                try{
                    boolean isVerified = get();
                    if (!isVerified) {
                        throw new Exception("Invalid credentials");
                    }
                    mainWindow.createPopUpWindow2();
                } catch (Exception e) {
                    mainWindow.clearAccountCredentialsField();
                    ViewUtility.showInfoMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void verifyNumberOfTransactions(){
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return bankService.verifyNumberOfTransactions();
            }
            @Override
            protected void done() {
                try {
                    boolean haveTransactions = get();
                    if (!haveTransactions) {
                        mainWindow.disposePopUpFrame1();
                        mainWindow.enableButtons();
                        ViewUtility.showWarningMessage("Warning: System detected user does not have any transactions completed." +
                                " ");
                    }
                } catch (Exception e){
                    ViewUtility.showErrorMessage(null, e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void initiateTransactionRequest(String transactionType, String selectedBank, int amount){
        SwingWorker<Integer, Void> worker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return bankService.createTransaction(transactionType ,selectedBank, amount);
            }
            @Override
            protected void done() {
                try{
                    int OTP = get();
                    if (OTP > 0) {
                        ViewUtility.showInfoMessage("Transaction Created. OTP: " + OTP);

                        if(activeTransactionTimer != null && activeTransactionTimer.isRunning()) {
                            System.out.println("Timer stopped");
                            activeTransactionTimer.stop();
                        }
                            activeTransactionTimer = new Timer(60000, _ -> {
                                cancelTransaction();
                                mainWindow.getTransactionBtn().doClick();
                            });
                            activeTransactionTimer.setRepeats(false);
                            activeTransactionTimer.start();
                    } else {
                        throw new Exception("Transaction could not be created");
                    }
                } catch (Exception e) {
                    ViewUtility.showInfoMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void checkForPendingTransactions(){
        SwingWorker<Integer, Void> worker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return bankService.getTransactionID();
            }
            @Override
            protected void done() {
                try{
                    int transactionID = get();
                    if(transactionID > 0) {
                        throw new Exception("Pending transaction detected. Please complete it first.");
                    }
                    mainWindow.proceedTransaction();
                } catch (Exception e){
                    ViewUtility.showInfoMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void getTransactionHistory(){
        SwingWorker<List<TransactionHistoryDTO>, Void> worker = new SwingWorker<>() {
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
                        mainWindow.updateTransactionHistoryTable(transaction);
                    }
                } catch (Exception e){
                    ViewUtility.showErrorMessage(null, e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void getUserProfile(){
        SwingWorker<UserProfileDTO, Void> worker = new SwingWorker<>() {
            @Override
            protected UserProfileDTO doInBackground() throws Exception {
                return bankService.getUserProfile();
            }
            @Override
            protected void done() {
                try{
                    UserProfileDTO profile = get();
                    if(profile != null){
                        mainWindow.displayUserProfile(profile);
                    } else {
                        throw new Exception("Could not retrieve user profile");
                    }
                } catch (Exception e){
                    ViewUtility.showInfoMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void cancelTransaction(){
        SwingWorker<Integer, Void> worker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return bankService.cancelPendingTransaction();
            }
            @Override
            protected void done() {
                try{
                  int pendingTransactionID = get();
                  if(pendingTransactionID > 0) {
                      ViewUtility.showInfoMessage("Transaction cancelled.");
                  }
                } catch (Exception e){
                    ViewUtility.showErrorMessage(null, e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void updateUserProfile(String username, String contactNum, String email){
        SwingWorker<Integer, Void> worker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return bankService.updateUserProfile(username, contactNum, email);
            }
            @Override
            protected void done() {
                try{
                    int rowsAffected = get();
                    if (rowsAffected > 0) {
                        ViewUtility.showInfoMessage("User Profile Updated.");
                    } else {
                        throw new Exception("User Profile could not be updated");
                    }
                } catch (Exception e){
                    ViewUtility.showInfoMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void deleteTransactionsByIdsAsync(List<Integer> idsToDelete, Runnable callback) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                bankService.deleteTransactionsByIds(idsToDelete);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    if (callback != null) {
                        callback.run();
                    }

                    JOptionPane.showMessageDialog(null,
                            "Selected items deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "An error occurred while deleting records: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    public void changePassword(String newPassword){
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                return bankService.updatePassword(newPassword);
            }
            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        ViewUtility.showInfoMessage("Password changed successfully.");
                    }
                } catch (Exception e) {
                    ViewUtility.showErrorMessage(null, e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public void logoutUserSession(){
        bankService.logoutUserSession();
        SwingUtilities.invokeLater(() -> {
            logInWindow.getUserNameField().setText("");
            logInWindow.getPassField().setText("");
        });
    }

    public void showLoginWindow() {
        SwingUtilities.invokeLater(() -> {
            ViewUtility.show(logInWindow.getLoginFrame());
            ViewUtility.hide(registerWindow.getRegisterFrame());
            ViewUtility.hide(mainWindow.getMainFrame());
        });
    }

    public void showRegisterWindow() {
        SwingUtilities.invokeLater(() -> {
            ViewUtility.show(registerWindow.getRegisterFrame());
            ViewUtility.hide(logInWindow.getLoginFrame());
            ViewUtility.hide(mainWindow.getMainFrame());
        });
    }

    public void showMainWindow() {
        SwingUtilities.invokeLater(() -> {
            ViewUtility.show(mainWindow.getMainFrame());
            ViewUtility.hide(logInWindow.getLoginFrame());
            ViewUtility.hide(registerWindow.getRegisterFrame());
        });
    }
}
