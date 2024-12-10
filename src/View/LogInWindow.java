package View;

import Controller.IMBankController;
import DTO.LogInRequestDTO;
import Utility.ViewUtility;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.View;


public class LogInWindow {

    private JFrame loginFrame;
    private JPanel loginPanel, bannerPanel;
    private JTextField userNameField;
    private JPasswordField passField;
    private JCheckBox showPass;
    private JButton logInBtn;
    private JButton registerBtn;

    private final IMBankController bankController;

    public LogInWindow(IMBankController bankController) {
        this.bankController = bankController;
        initComponents();
    }

    private void initComponents() {
        // Frame settings
        loginFrame = ViewFactory.createFrame("IM Bank Log In", 650, 480);
        loginFrame.addWindowListener(ViewUtility.getWindowAdapter());

            // Main panel
            loginPanel = new JPanel();
            loginPanel.setLayout(null); // Absolute layout
            loginPanel.setBounds(0, 0, 650, 480);

                // Top banner panel
                bannerPanel = ViewFactory.createHeaderPanel(loginPanel);

                ViewFactory.createHeaderLabel(bannerPanel, "IMBANK LOG IN", 48);
                // Username label
                ViewFactory.createFieldLabel(loginPanel, "Username:", new ViewFactory.Bounds(110, 96, 150, 30));
                // Password label
                ViewFactory.createFieldLabel(loginPanel, "Password:", new ViewFactory.Bounds(110, 190, 150, 30));

                // Username field
                userNameField = ViewFactory.createLogInTextField(loginPanel, new ViewFactory.Bounds(110, 136, 362, 45));
                // Password field
                passField = ViewFactory.createLogInPasswordField(loginPanel, new ViewFactory.Bounds(110, 230, 362, 45));
                passField.setEchoChar('*');

                showPass = ViewFactory.createCheckBox(loginPanel, "Show Password", new ViewFactory.Bounds(480, 240, 180, 25));
                showPass.addActionListener(this::showPassActionPerformed);

                // Submit button
                logInBtn = ViewFactory.createCustomButton1(loginPanel, "Submit", new ViewFactory.Bounds(110, 302, 362, 43), 24);
                logInBtn.addActionListener(this::logInBtnActionPerformed);

                // Sign up section
                ViewFactory.createConfirmationLabel(loginPanel, "Don't have an account?", new ViewFactory.Bounds(130, 385, 200, 30), 18);

                registerBtn = ViewFactory.createCustomButton1(loginPanel, "Sign Up", new ViewFactory.Bounds(330,384, 79, 30), 18);
                registerBtn.addActionListener(this::registerBtnActionPerformed);

        loginFrame.getContentPane().add(loginPanel);
    }

    private void showPassActionPerformed(ActionEvent ae) {
        if (showPass.isSelected()) {
            passField.setEchoChar((char) 0);
        } else {
            passField.setEchoChar('*');
        }
    }

    private void logInBtnActionPerformed(ActionEvent ae) {
        if(userNameField.getText().isEmpty() || String.valueOf(passField.getPassword()).isEmpty()) {
            ViewUtility.showInfoMessage("Please input Username and Password");
            return;
        }
        bankController.verifyLogin(getUserCredentials());
    }

    private void registerBtnActionPerformed(ActionEvent ae) {
        bankController.showRegisterWindow();
    }

    private LogInRequestDTO getUserCredentials() {
        return new LogInRequestDTO(userNameField.getText(),
                String.valueOf(passField.getPassword()));
    }

    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public JPasswordField getPassField() {
        return passField;
    }
}
