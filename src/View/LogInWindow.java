package View;

import Controller.IMBankController;
import DTO.LogInRequestDTO;

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
    private JPanel loginPanel;
    private JLabel userNameLbl;
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
        loginFrame = new JFrame("IM Bank");
        loginFrame.setLayout(null);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(600, 400);
        loginFrame.setLocationRelativeTo(null);

        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(0, 0, 600, 400);

        userNameLbl = new JLabel("Username:");
        userNameLbl.setBounds(0, 0, 70, 30);

        passwordLbl = new JLabel("Password:");
        passwordLbl.setBounds(0, 40, 70, 30);

        userNameField = new JTextField();
        userNameField.setBounds(70, 5, 120, 25);

        passField = new JPasswordField();
        passField.setBounds(70, 45, 120, 25);

        showPass = new JCheckBox("Show password");
        showPass.setFocusable(false);
        showPass.setBounds(190, 50, 120, 25);
        showPass.addActionListener(this::showPassActionPerformed);

        logInBtn = new JButton("Log In");
        logInBtn.setFocusable(false);
        logInBtn.setBounds(70, 80, 120, 25);
        logInBtn.addActionListener(this::logInBtnActionPerformed);

        confirmationLbl = new JLabel("Don't have an account?");
        confirmationLbl.setBounds(0, 120, 150, 30);

        registerBtn = new JButton("Register");
        registerBtn.setFocusable(false);
        registerBtn.setBounds(150, 120, 100, 25);
        registerBtn.addActionListener(this::registerBtnActionPerformed);

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
            showMessage("Please input Username and Password");
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

    public void show() {
        loginFrame.setVisible(true);
    }

    public void hide() {
        loginFrame.setVisible(false);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(loginFrame, msg);
    }
}
