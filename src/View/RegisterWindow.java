package View;

import java.awt.*;
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
        registerFrame = new JFrame("IM Bank: Register");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLayout(null);
        registerFrame.setSize(width, height);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.addWindowListener(ViewUtility.getWindowAdapter());

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
        step1Panel.setBounds(0, 0, width, height);

        // Top banner panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(new Color(35, 35, 77));
        bannerPanel.setBounds(0, 0, 650, 80);

        JLabel mainLbl = new JLabel("IMBANK REGISTRATION");
        mainLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 40));
        mainLbl.setForeground(Color.WHITE);
        bannerPanel.add(mainLbl);

        mainLbl1 = new JLabel("Personal Information");
        mainLbl1.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
        mainLbl1.setBounds(100, 90, 300, 30);

        firstNameLbl = new JLabel("First Name:");
        firstNameLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        firstNameLbl.setBounds(100, 140, 250, 30);

        lastNameLbl = new JLabel("Last Name:");
        lastNameLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        lastNameLbl.setBounds(100, 190, 250, 30);

        ageLbl = new JLabel("Age:");
        ageLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        ageLbl.setBounds(100, 240, 70, 30);

        dtOfBirthLbl = new JLabel("Date of Birth:");
        dtOfBirthLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        dtOfBirthLbl.setBounds(100, 290, 250, 30);

        genderLbl = new JLabel("Gender:");
        genderLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        genderLbl.setBounds(100, 340, 150, 30);

        fNameField = new JTextField();
        fNameField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        fNameField.setBounds(250, 140, 200, 30);

        lNameField = new JTextField();
        lNameField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        lNameField.setBounds(250, 190, 200, 30);

        ageField = new JTextField(3);
        ageField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        ageField.setBounds(250, 240, 200, 30);
        ageField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        JDateComponentFactory fac = new JDateComponentFactory();
        datePicker = (JDatePickerImpl) fac.createJDatePicker();
        datePicker.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        datePicker.setBounds(250, 290, 200, 30);

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
        maleRadioBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        maleRadioBtn.setBounds(250, 340, 80, 30);
        maleRadioBtn.addItemListener(genderListener);

        femaleRadioBtn = new JRadioButton("Female");
        femaleRadioBtn.setFocusable(false);
        femaleRadioBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        femaleRadioBtn.setBounds(340, 340, 100, 30);
        femaleRadioBtn.addItemListener(genderListener);

        genderBtnGroup = new ButtonGroup();
        genderBtnGroup.add(maleRadioBtn);
        genderBtnGroup.add(femaleRadioBtn);

        initRepeatComponents1();
        initRepeatComponents2();

        step1Panel.add(bannerPanel);
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
        step2Panel.setBounds(0, 0, width, height);

        // Top banner panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(new Color(35, 35, 77));
        bannerPanel.setBounds(0, 0, 650, 80);

        JLabel mainLbl = new JLabel("IMBANK REGISTRATION");
        mainLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 40));
        mainLbl.setForeground(Color.WHITE);
        bannerPanel.add(mainLbl);

        mainLbl2 = new JLabel("Contact Information");
        mainLbl2.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
        mainLbl2.setBounds(100, 90, 300, 30);

        phoneNumLbl = new JLabel("Phone Number:");
        phoneNumLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        phoneNumLbl.setBounds(100, 140, 250, 30);

        addressLbl = new JLabel("Address:");
        addressLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        addressLbl.setBounds(100, 190, 250, 30);

        cityLbl = new JLabel("City:");
        cityLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        cityLbl.setBounds(100, 240, 250, 30);

        provinceLbl = new JLabel("Province");
        provinceLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        provinceLbl.setBounds(100, 290, 250, 30);

        zipCodeLbl = new JLabel("ZIP Code:");
        zipCodeLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        zipCodeLbl.setBounds(100, 340, 250, 30);

        phoneNumField = new JTextField(11);
        phoneNumField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        phoneNumField.setBounds(250, 140, 200, 30);
        phoneNumField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        addressField = new JTextField();
        addressField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        addressField.setBounds(250, 190, 200, 30);

        cityField = new JTextField();
        cityField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        cityField.setBounds(250, 240, 200, 30);

        provinceField = new JTextField();
        provinceField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        provinceField.setBounds(250, 290, 200, 30);

        zipCodeField = new JTextField(4);
        zipCodeField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        zipCodeField.setBounds(250, 340, 200, 30);
        zipCodeField.addKeyListener(ViewUtility.addNumberInputKeyListener());

        initRepeatComponents1();
        initRepeatComponents2();
        initRepeatComponents3();

        step2Panel.add(bannerPanel);
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
        step3Panel.setBounds(0, 0, width, height);

        // Top banner panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(new Color(35, 35, 77));
        bannerPanel.setBounds(0, 0, 650, 80);

        JLabel mainLbl = new JLabel("IMBANK REGISTRATION");
        mainLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 40));
        mainLbl.setForeground(Color.WHITE);
        bannerPanel.add(mainLbl);

        mainLbl3 = new JLabel("Security");
        mainLbl3.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
        mainLbl3.setBounds(100, 90, 300, 30);

        emailLbl = new JLabel("Email:");
        emailLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        emailLbl.setBounds(100, 140, 250, 30);

        usernameLbl = new JLabel("Username:");
        usernameLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        usernameLbl.setBounds(100, 190, 250, 30);

        passLbl = new JLabel("Password:");
        passLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        passLbl.setBounds(100, 240, 250, 30);

        confirmPassLbl = new JLabel("Confirm Password:");
        confirmPassLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        confirmPassLbl.setBounds(100, 290, 250, 30);

        bankAccountTypeLbl = new JLabel("Bank Account Type:");
        bankAccountTypeLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        bankAccountTypeLbl.setBounds(100, 340, 250, 30);

        emailField = new JTextField();
        emailField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        emailField.setBounds(280, 140, 200, 30);

        usernameField = new JTextField();
        usernameField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        usernameField.setBounds(280, 190, 200, 30);

        passField = new JPasswordField();
        passField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        passField.setBounds(280, 240, 200, 30);

        confirmPassField = new JPasswordField();
        confirmPassField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        confirmPassField.setBounds(280, 290, 200, 30);

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
        savingsRadioBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        savingsRadioBtn.setBounds(280, 340, 100, 30);
        savingsRadioBtn.addItemListener(bankAccountTypeListener);

        checkingsRadioBtn = new JRadioButton("Checkings");
        checkingsRadioBtn.setFocusable(false);
        checkingsRadioBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        checkingsRadioBtn.setBounds(380, 340, 120, 30);
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

        step3Panel.add(bannerPanel);
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
        confirmationLbl.setFont(new Font("MS UI Gothic", Font.PLAIN, 15));
        confirmationLbl.setBounds(130, 460, 250, 30);


        logInBtn = new JButton("Log In");
        logInBtn.setFocusable(false);
        logInBtn.setBounds(290, 460, 90, 25);
        logInBtn.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
        logInBtn.setForeground(new java.awt.Color(224, 224, 231));
        logInBtn.setBackground(new java.awt.Color(35, 35, 77));
        logInBtn.setBorderPainted(false);
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