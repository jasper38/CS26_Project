package View;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
    private JLabel mainLbl1;
    private JLabel firstNameLbl;
    private JLabel lastNameLbl;
    private JLabel ageLbl;
    private JLabel dtOfBirthLbl;
    private JLabel genderLbl;
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
    private JLabel mainLbl2;
    private JLabel phoneNumLbl;
    private JLabel addressLbl;
    private JLabel cityLbl;
    private JLabel provinceLbl;
    private JLabel zipCodeLbl;
    private JTextField phoneNumField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField provinceField;
    private JTextField zipCodeField;

    // Security panel and its components
    private JPanel step3Panel;
    private JLabel mainLbl3;
    private JLabel emailLbl;
    private JLabel usernameLbl;
    private JLabel passLbl;
    private JLabel confirmPassLbl;
    private JLabel bankAccountTypeLbl;
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

    // JPanel array
    private JPanel[] panels = new JPanel[3];;

    private final IMBankController bankController;

    public RegisterWindow(IMBankController bankController) {
        this.bankController = bankController;
        registerWindowInitComponents();
    }

    private void registerWindowInitComponents() {
        registerFrame = new JFrame("IM Bank: Register");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLayout(null);
        registerFrame.setSize(600, 400);
        registerFrame.setLocationRelativeTo(null);

        initStep1PanelComponents();
        initStep2PanelComponents();
        initStep3PanelComponents();

        registerFrame.getContentPane().add(step1Panel);
        registerFrame.getContentPane().add(step2Panel);
        registerFrame.getContentPane().add(step3Panel);
    }

    private void initStep1PanelComponents() {
        step1Panel = new JPanel();
        step1Panel.setLayout(null);
        step1Panel.setBounds(0, 0, 600, 400);

        mainLbl1 = new JLabel("Personal Information");
        mainLbl1.setBounds(0, 0, 120, 30);

        firstNameLbl = new JLabel("First Name:");
        firstNameLbl.setBounds(0, 40, 100, 30);

        lastNameLbl = new JLabel("Last Name:");
        lastNameLbl.setBounds(0, 80, 100, 30);

        ageLbl = new JLabel("Age:");
        ageLbl.setBounds(0, 120, 70, 30);

        dtOfBirthLbl = new JLabel("Date of Birth:");
        dtOfBirthLbl.setBounds(0, 160, 150, 30);

        genderLbl = new JLabel("Gender:");
        genderLbl.setBounds(0, 200, 150, 30);

        fNameField = new JTextField();
        fNameField.setBounds(120, 45, 150, 25);

        lNameField = new JTextField();
        lNameField.setBounds(120, 85, 150, 25);

        ageField = new JTextField(3);
        ageField.setBounds(120, 125, 150, 25);
        ageField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        JDateComponentFactory fac = new JDateComponentFactory();
        datePicker = (JDatePickerImpl) fac.createJDatePicker();
        datePicker.setBounds(120, 165, 150, 25);

        ItemListener genderListener = new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedGenderRadioBtn = (JRadioButton) e.getItem();
                }
            }
        };

        maleRadioBtn = new JRadioButton("Male");
        maleRadioBtn.setFocusable(false);
        maleRadioBtn.setBounds(120, 205, 70, 25);
        maleRadioBtn.addItemListener(genderListener);

        femaleRadioBtn = new JRadioButton("Female");
        femaleRadioBtn.setFocusable(false);
        femaleRadioBtn.setBounds(190, 205, 70, 25);
        femaleRadioBtn.addItemListener(genderListener);

        genderBtnGroup = new ButtonGroup();
        genderBtnGroup.add(maleRadioBtn);
        genderBtnGroup.add(femaleRadioBtn);

        initRepeatComponents1();
        initRepeatComponents2();

        step1Panel.add(mainLbl1);
        step1Panel.add(firstNameLbl);
        step1Panel.add(lastNameLbl);
        step1Panel.add(ageLbl);
        step1Panel.add(dtOfBirthLbl);
        step1Panel.add(fNameField);
        step1Panel.add(lNameField);
        step1Panel.add(ageField);
        step1Panel.add(datePicker);
        step1Panel.add(genderLbl);
        step1Panel.add(maleRadioBtn);
        step1Panel.add(femaleRadioBtn);
        step1Panel.add(nextBtn);
        step1Panel.add(confirmationLbl);
        step1Panel.add(logInBtn);

        panels[0] = step1Panel;

        ViewUtility.setEnabledPanelAndComponents(panels[0], true);
    }

    private void initStep2PanelComponents() {
        step2Panel = new JPanel();
        step2Panel.setLayout(null);
        step2Panel.setBounds(0, 0, 600, 400);

        mainLbl2 = new JLabel("Contact Information");
        mainLbl2.setBounds(0, 0, 120, 30);

        phoneNumLbl = new JLabel("Phone Number:");
        phoneNumLbl.setBounds(0, 40, 100, 30);

        addressLbl = new JLabel("Address:");
        addressLbl.setBounds(0, 80, 100, 30);

        cityLbl = new JLabel("City:");
        cityLbl.setBounds(0, 120, 100, 30);

        provinceLbl = new JLabel("Province");
        provinceLbl.setBounds(0, 160, 100, 30);

        zipCodeLbl = new JLabel("ZIP Code:");
        zipCodeLbl.setBounds(0, 200, 100, 30);

        phoneNumField = new JTextField(11);
        phoneNumField.setBounds(120, 45, 150, 25);
        phoneNumField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        addressField = new JTextField();
        addressField.setBounds(120, 85, 150, 25);

        cityField = new JTextField();
        cityField.setBounds(120, 125, 150, 25);

        provinceField = new JTextField();
        provinceField.setBounds(120, 165, 150, 25);

        zipCodeField = new JTextField(4);
        zipCodeField.setBounds(120, 205, 150, 25);
        zipCodeField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        initRepeatComponents1();
        initRepeatComponents2();
        initRepeatComponents3();

        step2Panel.add(mainLbl2);
        step2Panel.add(phoneNumLbl);
        step2Panel.add(addressLbl);
        step2Panel.add(cityLbl);
        step2Panel.add(provinceLbl);
        step2Panel.add(zipCodeLbl);
        step2Panel.add(phoneNumField);
        step2Panel.add(addressField);
        step2Panel.add(cityField);
        step2Panel.add(provinceField);
        step2Panel.add(zipCodeField);
        step2Panel.add(backBtn);
        step2Panel.add(nextBtn);
        step2Panel.add(confirmationLbl);
        step2Panel.add(logInBtn);

        panels[1] = step2Panel;

        ViewUtility.setEnabledPanelAndComponents(panels[1], false);
    }

    private void initStep3PanelComponents() {
        step3Panel = new JPanel();
        step3Panel.setLayout(null);
        step3Panel.setBounds(0, 0, 600, 400);

        mainLbl3 = new JLabel("Security");
        mainLbl3.setBounds(0, 0, 120, 30);

        emailLbl = new JLabel("Email:");
        emailLbl.setBounds(0, 40, 100, 30);

        usernameLbl = new JLabel("Username:");
        usernameLbl.setBounds(0, 80, 100, 30);

        passLbl = new JLabel("Password:");
        passLbl.setBounds(0, 120, 70, 30);

        confirmPassLbl = new JLabel("Confirm Password:");
        confirmPassLbl.setBounds(0, 160, 150, 30);

        bankAccountTypeLbl = new JLabel("Type of Bank Account:");
        bankAccountTypeLbl.setBounds(0, 200, 150, 30);

        emailField = new JTextField();
        emailField.setBounds(120, 45, 150, 25);

        usernameField = new JTextField();
        usernameField.setBounds(120, 85, 150, 25);

        passField = new JPasswordField();
        passField.setBounds(120, 125, 150, 25);

        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(120, 165, 150, 25);

        ItemListener bankAccountTypeListener = new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedBankAccountTypeRadioBtn = (JRadioButton) e.getItem();
                }
            }
        };

        savingsRadioBtn = new JRadioButton("Savings");
        savingsRadioBtn.setFocusable(false);
        savingsRadioBtn.setBounds(120, 205, 70, 25);
        savingsRadioBtn.addItemListener(bankAccountTypeListener);

        checkingsRadioBtn = new JRadioButton("Checkings");
        checkingsRadioBtn.setFocusable(false);
        checkingsRadioBtn.setBounds(190, 205, 70, 25);
        checkingsRadioBtn.addItemListener(bankAccountTypeListener);

        bankAccountTypeBtnGroup = new ButtonGroup();
        bankAccountTypeBtnGroup.add(savingsRadioBtn);
        bankAccountTypeBtnGroup.add(checkingsRadioBtn);

        submitBtn = new JButton("Submit");
        submitBtn.setFocusable(false);
        submitBtn.setBounds(200, 245, 70, 25);
        submitBtn.addActionListener(this::submitBtnActionPerformed);

        initRepeatComponents1();
        initRepeatComponents3();

        step3Panel.add(mainLbl3);
        step3Panel.add(emailLbl);
        step3Panel.add(usernameLbl);
        step3Panel.add(passLbl);
        step3Panel.add(confirmPassLbl);
        step3Panel.add(bankAccountTypeLbl);
        step3Panel.add(emailField);
        step3Panel.add(usernameField);
        step3Panel.add(passField);
        step3Panel.add(confirmPassField);
        step3Panel.add(savingsRadioBtn);
        step3Panel.add(checkingsRadioBtn);
        step3Panel.add(backBtn);
        step3Panel.add(submitBtn);
        step3Panel.add(confirmationLbl);
        step3Panel.add(logInBtn);

        panels[2] = step3Panel;

        ViewUtility.setEnabledPanelAndComponents(panels[2], false);
    }

    private void initRepeatComponents1() {
        confirmationLbl = new JLabel("Already have an account?");
        confirmationLbl.setBounds(0, 300, 150, 30);

        logInBtn = new JButton("Log In");
        logInBtn.setFocusable(false);
        logInBtn.setBounds(150, 305, 70, 25);
        logInBtn.addActionListener(this::logInBtnActionPerformed);
    }

    private void initRepeatComponents2() {
        nextBtn = new JButton("Next");
        nextBtn.setFocusable(false);
        nextBtn.setBounds(200, 245, 70, 25);
        nextBtn.addActionListener(this::nextBtnActionPerformed);
    }

    private void initRepeatComponents3() {
        backBtn = new JButton("Bank");
        backBtn.setFocusable(false);
        backBtn.setBounds(120, 245, 70, 25);
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
            ViewUtility.showMessage("Passwords don't match. Please try again");
        }
    }

    private void nextBtnActionPerformed(ActionEvent ae) {
        if (panels[0].isVisible()) {
            ViewUtility.setEnabledPanelAndComponents(panels[0], false);
            ViewUtility.setEnabledPanelAndComponents(panels[1], true);
        } else if (panels[1].isVisible()) {
            ViewUtility.setEnabledPanelAndComponents(panels[1], false);
            ViewUtility.setEnabledPanelAndComponents(panels[2], true);
        }
    }

    private void backBtnActionPerformed(ActionEvent ae) {
        if (panels[2].isVisible()) {
            ViewUtility.setEnabledPanelAndComponents(panels[2], false);
            ViewUtility.setEnabledPanelAndComponents(panels[1], true);
        } else if (panels[1].isVisible()) {
            ViewUtility.setEnabledPanelAndComponents(panels[1], false);
            ViewUtility.setEnabledPanelAndComponents(panels[0], true);
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
