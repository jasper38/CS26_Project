package Controller;

import DTO.LogInRequestDTO;
import DTO.RegistrationRequestDTO;
import Service.IMBankServiceImpl;
import View.LogInWindow;
import View.MainWindow;
import View.RegisterWindow;

import javax.swing.*;
import java.sql.SQLException;

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
        Thread backendThread = new Thread(() -> {
            try {
                if (bankService.registerPerson(registrationRequestDTO)) {
                    SwingUtilities.invokeLater(() -> {
                        showLoginWindow();
                        registerWindow.showMessage("Registration Successful! Please claim your ATM Card at the Bank.");
                    });
                }
            } catch (SQLException se) {
                registerWindow.showMessage("An Error occured during registration.");
            }
        });
        backendThread.start();
        disposeThread(backendThread);
    }

    public void verifyLogin(LogInRequestDTO logInRequest) {
        Thread backendThread = new Thread(() -> {
            try {
                if (bankService.verifyLogIn(logInRequest)) {
                    getAccountBalance();
                    SwingUtilities.invokeLater(() -> {
                        showMainWindow();
                        System.out.println("Success");
                    });
                }
            } catch (SQLException se) {
                logInWindow.showMessage("Invalid Username or Password");
            } catch (IllegalArgumentException ie) {
                logInWindow.showMessage(ie.getMessage());
            }
        });
        backendThread.start();
        disposeThread(backendThread);
    }

    public void getAccountBalance() {
        try {
            float bankAccountBalance = bankService.getBankAccountBalance();
            SwingUtilities.invokeLater(() -> {
                mainWindow.setDisplayBalanceField(String.valueOf(bankAccountBalance));
            });
        } catch (Exception e) {
            mainWindow.showMessage("Failed to retrieve balance");
        }
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

    private void disposeThread(Thread thread) {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Unable to stop thread.");
            }
        }
    }
}
