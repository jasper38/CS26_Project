package Controller;

import DTO.LogInRequestDTO;
import DTO.RegistrationRequestDTO;
import Service.IMBankServiceImpl;
import View.LogInWindow;
import View.MainWindow;
import View.RegisterWindow;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

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
                        registerWindow.showMessage("Registration Successful! Please claim your ATM Card at the Bank.");
                    }
                } catch (Exception se) {
                    registerWindow.showMessage("An Error occured during registration.");
                }
            }
        };
        worker.execute();
    }

    public void verifyLogin(LogInRequestDTO logInRequest) {
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return bankService.verifyLogIn(logInRequest);
            }
            @Override
            protected void done() {
                try {
                    boolean logInSuccess = get();
                    if (logInSuccess) {
                        getAccountBalance();
                        showMainWindow();
                    }
                } catch (Exception e) {
                    logInWindow.showMessage(e.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void getAccountBalance() {
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
                    mainWindow.showMessage("Failed to retrieve balance");
                }
            }
        };
        worker.execute();
    }

    public void showLoginWindow() {
        registerWindow.hide();
        logInWindow.show();
        mainWindow.hide();
    }

    public void showRegisterWindow() {
        logInWindow.hide();
        registerWindow.show();
        mainWindow.hide();
    }

    public void showMainWindow() {
        logInWindow.hide();
        mainWindow.show();
        registerWindow.hide();
    }
}
