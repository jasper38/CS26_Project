package View;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.text.View;

import DTO.RegistrationRequestDTO;
import Utility.ViewUtility;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;

import Controller.IMBankController;

public class RegisterWindow {

    // Cafe Verdania
    // Simple GUI for backend testing

    private JFrame registerFrame;

    // Personal Information panel and its components
    private JPanel step1Panel;
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField ageField;
    private ButtonGroup genderBtnGroup;
    private JRadioButton maleRadioBtn;
    private JRadioButton femaleRadioBtn;
    private JRadioButton selectedGenderRadioBtn;
    private JDatePickerImpl datePicker;

    // Contact Information panel and its components
    private JPanel step2Panel;
    private JTextField phoneNumField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField provinceField;
    private JTextField zipCodeField;

    // Security panel and its components
    private JPanel step3Panel;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;
    private ButtonGroup bankAccountTypeBtnGroup;
    private JRadioButton checkingsRadioBtn;
    private JRadioButton savingsRadioBtn;
    private JRadioButton selectedBankAccountTypeRadioBtn;

    // Re-used components
    private JButton nextBtn;
    private JButton backBtn;
    private JButton logInBtn;
    private JButton submitBtn;
    private JLabel confirmationLbl;

    //dimensions
    private int height = 600;
    private int width = 650;

    // JPanel array
    private JPanel[] panels = new JPanel[3];;

    private final IMBankController bankController;

    public RegisterWindow(IMBankController bankController) {
        this.bankController = bankController;
        registerWindowInitComponents();
    }

    private void registerWindowInitComponents() {
        registerFrame = ViewFactory.createFrame("IM Bank: Register", width, height);
        registerFrame.addWindowListener(ViewUtility.getWindowAdapter());

        panels[0] = initStep1PanelComponents();
        panels[1] = initStep2PanelComponents();
        panels[2] = initStep3PanelComponents();

        ViewUtility.enablePanelAndComponents(panels[0], true);
        ViewUtility.enablePanelAndComponents(panels[1], false);
        ViewUtility.enablePanelAndComponents(panels[2], false);

        registerFrame.getContentPane().add(step1Panel);
        registerFrame.getContentPane().add(step2Panel);
        registerFrame.getContentPane().add(step3Panel);
    }

    private JPanel initStep1PanelComponents() {
        step1Panel = ViewFactory.createMultiStepPanel(registerFrame, width, height);

            initHeaderPanel(step1Panel);

            ViewFactory.createMainLabel(step1Panel, "Personal Information", new ViewFactory.Bounds(100, 90, 300, 30));
            ViewFactory.createFieldLabel(step1Panel, "First Name", new ViewFactory.Bounds(100, 140, 250, 30));
            ViewFactory.createFieldLabel(step1Panel, "Last Name", new ViewFactory.Bounds(100, 190, 250, 30));
            ViewFactory.createFieldLabel(step1Panel, "Age", new ViewFactory.Bounds(100, 240, 70, 30));
            ViewFactory.createFieldLabel(step1Panel, "Date of Birth", new ViewFactory.Bounds(100, 290, 250, 30));
            ViewFactory.createFieldLabel(step1Panel, "Gender", new ViewFactory.Bounds(100, 340, 150, 30));

            fNameField = ViewFactory.createTextField(step1Panel, new ViewFactory.Bounds(250, 140, 200, 30));
            lNameField = ViewFactory.createTextField(step1Panel, new ViewFactory.Bounds(250, 190, 200, 30));
            ageField = ViewFactory.createTextField(step1Panel, new ViewFactory.Bounds(250, 240, 200, 30));
                ageField.addKeyListener(ViewUtility.addNumberInputKeyListener());

            JDateComponentFactory fac = new JDateComponentFactory();
            datePicker = (JDatePickerImpl) fac.createJDatePicker();
            datePicker.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
            datePicker.setBounds(250, 290, 200, 30);

            ItemListener genderListener = e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedGenderRadioBtn = (JRadioButton) e.getItem();
                }
            };

            maleRadioBtn = ViewFactory.createRadioButton(step1Panel, "Male", new ViewFactory.Bounds(250, 340, 80, 30));
                maleRadioBtn.addItemListener(genderListener);
            femaleRadioBtn = ViewFactory.createRadioButton(step1Panel, "Female", new ViewFactory.Bounds(340, 340, 100, 30));
                femaleRadioBtn.addItemListener(genderListener);

            genderBtnGroup = new ButtonGroup();
            genderBtnGroup.add(maleRadioBtn);
            genderBtnGroup.add(femaleRadioBtn);

            initRepeatComponents1();
            initRepeatComponents2();

        step1Panel.add(datePicker);
        step1Panel.add(nextBtn);
        step1Panel.add(confirmationLbl);
        step1Panel.add(logInBtn);

        return step1Panel;
    }

    private JPanel initStep2PanelComponents() {
        step2Panel = ViewFactory.createMultiStepPanel(registerFrame, width, height);

            initHeaderPanel(step2Panel);

            ViewFactory.createMainLabel(step2Panel, "Contact Information", new ViewFactory.Bounds(100, 90, 300, 30));
            ViewFactory.createFieldLabel(step2Panel, "Phone Number:", new ViewFactory.Bounds(100, 140, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "Address:", new ViewFactory.Bounds(100, 190, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "City:", new ViewFactory.Bounds(100, 240, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "Province:", new ViewFactory.Bounds(100, 290, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "Zip Code:", new ViewFactory.Bounds(100, 340, 250, 30));

            phoneNumField = ViewFactory.createTextField(step2Panel, new ViewFactory.Bounds(250, 140, 200, 30));
                phoneNumField.addKeyListener(ViewUtility.addNumberInputKeyListener());
            addressField = ViewFactory.createTextField(step2Panel, new ViewFactory.Bounds(250, 190, 200, 30));
            cityField = ViewFactory.createTextField(step2Panel, new ViewFactory.Bounds(250, 240, 200, 30));
            provinceField = ViewFactory.createTextField(step2Panel, new ViewFactory.Bounds(250, 290, 200, 30));
            zipCodeField = ViewFactory.createTextField(step2Panel, new ViewFactory.Bounds(250, 340, 200, 30));
                zipCodeField.addKeyListener(ViewUtility.addNumberInputKeyListener());

            initRepeatComponents1();
            initRepeatComponents2();
            initRepeatComponents3();

        step2Panel.add(backBtn);
        step2Panel.add(nextBtn);
        step2Panel.add(confirmationLbl);
        step2Panel.add(logInBtn);

        return step2Panel;
    }

    private JPanel initStep3PanelComponents() {
        step3Panel = ViewFactory.createMultiStepPanel(registerFrame, width, height);

            initHeaderPanel(step3Panel);

            ViewFactory.createMainLabel(step3Panel, "Security", new ViewFactory.Bounds(100, 90, 300, 30));
            ViewFactory.createFieldLabel(step3Panel, "Email:", new ViewFactory.Bounds(100, 140, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Username:", new ViewFactory.Bounds(100, 190, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Password:", new ViewFactory.Bounds(100, 240, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Confirm Password:", new ViewFactory.Bounds(100, 290, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Bank Account Type:", new ViewFactory.Bounds(100, 340, 150, 30));

            emailField = ViewFactory.createTextField(step3Panel, new ViewFactory.Bounds(280, 140, 200, 30));
            usernameField = ViewFactory.createTextField(step3Panel, new ViewFactory.Bounds(280, 190, 200, 30));
            passField = ViewFactory.createPasswordField(step3Panel, new ViewFactory.Bounds(280, 240, 200, 30));
            confirmPassField = ViewFactory.createPasswordField(step3Panel, new ViewFactory.Bounds(280, 290, 200, 30));

            ItemListener bankAccountTypeListener = e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedBankAccountTypeRadioBtn = (JRadioButton) e.getItem();
                }
            };

            savingsRadioBtn = ViewFactory.createRadioButton(step3Panel, "Savings", new ViewFactory.Bounds(280, 340, 100, 30));
                savingsRadioBtn.addItemListener(bankAccountTypeListener);

            checkingsRadioBtn = ViewFactory.createRadioButton(step3Panel, "Checkings", new ViewFactory.Bounds(380, 340, 120, 30));
                checkingsRadioBtn.addItemListener(bankAccountTypeListener);

            bankAccountTypeBtnGroup = new ButtonGroup();
            bankAccountTypeBtnGroup.add(savingsRadioBtn);
            bankAccountTypeBtnGroup.add(checkingsRadioBtn);

            submitBtn = new JButton("Submit");
            submitBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
            submitBtn.setForeground(new java.awt.Color(224, 224, 231));
            submitBtn.setBackground(new java.awt.Color(35, 35, 77));
            submitBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
            submitBtn.setBorderPainted(false);
            submitBtn.setFocusable(false);
            submitBtn.setBounds(280, 400, 70, 25);
            submitBtn.addActionListener(this::submitBtnActionPerformed);

            initRepeatComponents1();
            initRepeatComponents3();

        //step3Panel.add(headerPanel);
        step3Panel.add(savingsRadioBtn);
        step3Panel.add(checkingsRadioBtn);
        step3Panel.add(backBtn);
        step3Panel.add(submitBtn);
        step3Panel.add(confirmationLbl);
        step3Panel.add(logInBtn);

        return step3Panel;
    }

    private void initHeaderPanel(JPanel panel){
        JPanel bannerPanel = ViewFactory.createHeaderPanel(panel);
        ViewFactory.createHeaderLabel(bannerPanel, "IMBANK REGISTRATION");
    }

    private JPanel initFooterPanel() {
        JPanel footer = new JPanel();
        return footer;
    }

    private void initRepeatComponents1() {
        confirmationLbl = new JLabel("Already have an account?");
        confirmationLbl.setFont(new Font("MS UI Gothic", Font.PLAIN, 15));
        confirmationLbl.setBounds(130, 460, 250, 30);

        logInBtn = new JButton("Log In");
        logInBtn.setFocusable(false);
        logInBtn.setBorderPainted(false);
        logInBtn.setBounds(290, 460, 90, 25);
        logInBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
        logInBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        logInBtn.setForeground(new java.awt.Color(224, 224, 231));
        logInBtn.setBackground(new java.awt.Color(35, 35, 77));
        logInBtn.addActionListener(this::logInBtnActionPerformed);
    }

    private void initRepeatComponents2() {
        nextBtn = new JButton("Next");
        nextBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
        nextBtn.setForeground(new java.awt.Color(224, 224, 231));
        nextBtn.setBackground(new java.awt.Color(35, 35, 77));
        nextBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        nextBtn.setBorderPainted(false);
        nextBtn.setFocusable(false);
        nextBtn.setBounds(280, 400, 70, 25);
        nextBtn.addActionListener(this::nextBtnActionPerformed);
    }

    private void initRepeatComponents3() {
        backBtn = new JButton("Back");
        backBtn.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
        backBtn.setForeground(new java.awt.Color(224, 224, 231));
        backBtn.setBackground(new java.awt.Color(35, 35, 77));
        backBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        backBtn.setBorderPainted(false);
        backBtn.setFocusable(false);
        backBtn.setBounds(190, 400, 70, 25);
        backBtn.addActionListener(this::backBtnActionPerformed);
    }

    // Actions of buttons in the following lines of code
    private void logInBtnActionPerformed(ActionEvent ae) {
        bankController.showLoginWindow();
    }

    private void submitBtnActionPerformed(ActionEvent ae) {
        if (String.valueOf(passField.getPassword()).equals(String.valueOf(confirmPassField.getPassword()))) {
            bankController.registerPerson(getRegistrationData());
        } else {
            ViewUtility.showInfoMessage("Passwords don't match. Please try again");
        }
    }

    private void nextBtnActionPerformed(ActionEvent ae) {
        if (panels[0].isVisible()) {
            ViewUtility.enablePanelAndComponents(panels[0], false);
            ViewUtility.enablePanelAndComponents(panels[1], true);
        } else if (panels[1].isVisible()) {
            ViewUtility.enablePanelAndComponents(panels[1], false);
            ViewUtility.enablePanelAndComponents(panels[2], true);
        }
    }

    private void backBtnActionPerformed(ActionEvent ae) {
        if (panels[2].isVisible()) {
            ViewUtility.enablePanelAndComponents(panels[2], false);
            ViewUtility.enablePanelAndComponents(panels[1], true);
        } else if (panels[1].isVisible()) {
            ViewUtility.enablePanelAndComponents(panels[1], false);
            ViewUtility.enablePanelAndComponents(panels[0], true);
        }
    }

    // Form Validation
    private RegistrationRequestDTO getRegistrationData() {

        RegistrationRequestDTO registrationRequestDTO = new RegistrationRequestDTO();
            registrationRequestDTO.setFirstName(String.valueOf(fNameField.getText()));
            registrationRequestDTO.setLastName(String.valueOf(lNameField.getText()));
            registrationRequestDTO.setAge(Integer.parseInt(ageField.getText()));

            Calendar selectedCalendar = (Calendar) datePicker.getModel().getValue();
            java.util.Date utilDate = selectedCalendar.getTime();
            Date sqlDate = new Date(utilDate.getTime());
            registrationRequestDTO.setBirthdate(sqlDate);
            registrationRequestDTO.setSex(selectedGenderRadioBtn.getText());
            registrationRequestDTO.setPhoneNo(phoneNumField.getText());
            registrationRequestDTO.setAddress(addressField.getText() + ", " + cityField.getText() + ", "
                    + provinceField.getText() + ", " + String.valueOf(zipCodeField.getText()));
            registrationRequestDTO.setEmail(emailField.getText());
            registrationRequestDTO.setUsername(usernameField.getText());
            registrationRequestDTO.setPassword(String.valueOf(passField.getPassword()));
            registrationRequestDTO.setBankAccountType(selectedBankAccountTypeRadioBtn.getText());
        return registrationRequestDTO;
    }

    public JFrame getRegisterFrame() {
        return registerFrame;
    }
}