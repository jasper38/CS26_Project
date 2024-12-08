package View;

import Controller.IMBankController;
import DTO.LogInRequestDTO;
import Utility.ViewUtility;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LogInWindow {

    // Simple GUI for testing
    private JFrame loginFrame;
    private JPanel loginPanel, bannerPanel;
    private JLabel userNameLbl,titleLabel;
    private JLabel passwordLbl;
    private JLabel confirmationLbl;
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

            // Main panel
            loginPanel = new JPanel();
            loginPanel.setLayout(null); // Absolute layout
            loginPanel.setBounds(0, 0, 650, 480);

                // Top banner panel
                bannerPanel = new JPanel();
                bannerPanel.setBackground(new java.awt.Color(35, 35, 77));
                bannerPanel.setBounds(0, 0, 650, 80);

                titleLabel = new JLabel("IMBANK LOG IN");
                titleLabel.setFont(new java.awt.Font("MS UI Gothic", 1, 48));
                titleLabel.setForeground(new java.awt.Color(255, 255, 255));
                bannerPanel.add(titleLabel);

                // Username label
                userNameLbl = new JLabel("Username:");
                userNameLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 20));
                userNameLbl.setForeground(new java.awt.Color(35, 35, 77));
                userNameLbl.setBounds(110, 96, 150, 30);

                // Password label
                passwordLbl = new JLabel("Password:");
                passwordLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 20));
                passwordLbl.setForeground(new java.awt.Color(35, 35, 77));
                passwordLbl.setBounds(110, 190, 150, 30);

                // Username field
                userNameField = new JTextField("Type your Username");
                userNameField.setFont(new java.awt.Font("MS UI Gothic", 0, 24));
                userNameField.setForeground(new java.awt.Color(102, 102, 102));
                userNameField.setBounds(110, 136, 362, 45);

                // Password field
                passField = new JPasswordField();
                passField.setFont(new java.awt.Font("MS UI Gothic", 0, 24));
                passField.setForeground(new java.awt.Color(102, 102, 102));
                passField.setBounds(110, 230, 362, 45);
                passField.setEchoChar('*');

                showPass = new JCheckBox("Show password");
                showPass.setFont(new java.awt.Font("MS UI Gothic", 0, 19));
                showPass.setFocusable(false);
                showPass.setBounds(480, 240, 150, 25);
                showPass.addActionListener(this::showPassActionPerformed);

                // Submit button
                logInBtn = new JButton("Submit");
                logInBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 24));
                logInBtn.setForeground(new java.awt.Color(224, 224, 231));
                logInBtn.setBackground(new java.awt.Color(35, 35, 77));
                logInBtn.setBorder(null);
                logInBtn.setBounds(110, 302, 362, 43);
                logInBtn.addActionListener(this::logInBtnActionPerformed);

                // Sign up section
                confirmationLbl = new JLabel("Don't have an account?");
                confirmationLbl.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
                confirmationLbl.setBounds(130, 364, 200, 30);

                registerBtn = new JButton("Sign Up");
                registerBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
                registerBtn.setForeground(new java.awt.Color(224, 224, 231));
                registerBtn.setBackground(new java.awt.Color(35, 35, 77));
                registerBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
                registerBtn.setBorderPainted(false);
                registerBtn.setBounds(325, 363, 79, 30);
                registerBtn.addActionListener(this::registerBtnActionPerformed);

            loginPanel.add(bannerPanel);
            loginPanel.add(userNameLbl);
            loginPanel.add(passwordLbl);
            loginPanel.add(userNameField);
            loginPanel.add(passField);
            loginPanel.add(showPass);
            loginPanel.add(logInBtn);
            loginPanel.add(confirmationLbl);
            loginPanel.add(registerBtn);

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
            ViewUtility.showMessage("Please input Username and Password");
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
}
