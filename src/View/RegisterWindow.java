package View;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.*;

import DTO.RegistrationRequestDTO;
import Utility.ViewUtility;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;

import Controller.IMBankController;

public class RegisterWindow {

    private JFrame registerFrame;

    // Personal Information panel and its components
    private JPanel step1Panel;
    private JTextField fNameField, lNameField, ageField;
    private ButtonGroup genderBtnGroup;
    private JRadioButton maleRadioBtn, femaleRadioBtn, selectedGenderRadioBtn;
    private JDatePickerImpl datePicker;

    // Contact Information panel and its components
    private JPanel step2Panel;
    private JTextField phoneNumField, addressField, cityField;
    private JTextField zipCodeField, provinceField;

    // Security panel and its components
    private JPanel step3Panel;
    private JTextField emailField, usernameField;
    private JPasswordField passField, confirmPassField;
    private ButtonGroup bankAccountTypeBtnGroup;
    private JRadioButton checkingsRadioBtn, savingsRadioBtn, selectedBankAccountTypeRadioBtn;

    // Re-used components
    private JButton nextBtn, backBtn, logInBtn, submitBtn;

    //dimensions
    private int height = 600;
    private int width = 650;

    // JPanel array
    private JPanel[] panels = new JPanel[3];;

    private final IMBankController bankController;

    public RegisterWindow(IMBankController bankController) {
        this.bankController = bankController;
        initRegisterWindowComponents();
    }

    private void initRegisterWindowComponents() {
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

            ViewFactory.createMainLabel(step1Panel, "Personal Information", new ViewFactory.Bounds(170, 100, 350, 40));
            ViewFactory.createFieldLabel(step1Panel, "First Name:", new ViewFactory.Bounds(80, 160, 250, 30));
            ViewFactory.createFieldLabel(step1Panel, "Last Name:", new ViewFactory.Bounds(80, 210, 250, 30));
            ViewFactory.createFieldLabel(step1Panel, "Age:", new ViewFactory.Bounds(80, 260, 70, 30));
            ViewFactory.createFieldLabel(step1Panel, "Date of Birth:", new ViewFactory.Bounds(80, 310, 250, 30));
            ViewFactory.createFieldLabel(step1Panel, "Gender:", new ViewFactory.Bounds(80, 360, 150, 30));

            fNameField = ViewFactory.createRegisterTextField(step1Panel, new ViewFactory.Bounds(320, 160, 200, 30));
            lNameField = ViewFactory.createRegisterTextField(step1Panel, new ViewFactory.Bounds(320, 210, 200, 30));
            ageField = ViewFactory.createRegisterTextField(step1Panel, new ViewFactory.Bounds(320, 260, 200, 30));
                ageField.addKeyListener(ViewUtility.addNumberInputKeyListener());

            JDateComponentFactory fac = new JDateComponentFactory();
            datePicker = (JDatePickerImpl) fac.createJDatePicker();
            datePicker.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
            datePicker.setBounds(320, 310, 200, 30);

            ItemListener genderListener = e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedGenderRadioBtn = (JRadioButton) e.getItem();
                }
            };

            maleRadioBtn = ViewFactory.createRadioButton(step1Panel, "Male", new ViewFactory.Bounds(320, 360, 80, 30));
                maleRadioBtn.addItemListener(genderListener);
            femaleRadioBtn = ViewFactory.createRadioButton(step1Panel, "Female", new ViewFactory.Bounds(410, 360, 150, 30));
                femaleRadioBtn.addItemListener(genderListener);

            genderBtnGroup = new ButtonGroup();
            genderBtnGroup.add(maleRadioBtn);
            genderBtnGroup.add(femaleRadioBtn);

            initRepeatComponents1(step1Panel);
            initRepeatComponents2(step1Panel);

            step1Panel.add(datePicker);

        return step1Panel;
    }

    private JPanel initStep2PanelComponents() {
        step2Panel = ViewFactory.createMultiStepPanel(registerFrame, width, height);

            initHeaderPanel(step2Panel);

            ViewFactory.createMainLabel(step2Panel, "Contact Information", new ViewFactory.Bounds(170, 100, 350, 30));
            ViewFactory.createFieldLabel(step2Panel, "Phone Number:", new ViewFactory.Bounds(80, 160, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "Address:", new ViewFactory.Bounds(80, 210, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "City:", new ViewFactory.Bounds(80, 260, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "Province:", new ViewFactory.Bounds(80, 310, 250, 30));
            ViewFactory.createFieldLabel(step2Panel, "Zip Code:", new ViewFactory.Bounds(80, 360, 250, 30));

            phoneNumField = ViewFactory.createRegisterTextField(step2Panel, new ViewFactory.Bounds(320, 160, 200, 30));
                phoneNumField.addKeyListener(ViewUtility.addNumberInputKeyListener());
            addressField = ViewFactory.createRegisterTextField(step2Panel, new ViewFactory.Bounds(320, 210, 200, 30));
            cityField = ViewFactory.createRegisterTextField(step2Panel, new ViewFactory.Bounds(320, 260, 200, 30));
            provinceField = ViewFactory.createRegisterTextField(step2Panel, new ViewFactory.Bounds(320, 310, 200, 30));
            zipCodeField = ViewFactory.createRegisterTextField(step2Panel, new ViewFactory.Bounds(320, 360, 200, 30));
                zipCodeField.addKeyListener(ViewUtility.addNumberInputKeyListener());

            initRepeatComponents1(step2Panel);
            initRepeatComponents2(step2Panel);
            initRepeatComponents3(step2Panel);

        return step2Panel;
    }

    private JPanel initStep3PanelComponents() {
        step3Panel = ViewFactory.createMultiStepPanel(registerFrame, width, height);

            initHeaderPanel(step3Panel);

            ViewFactory.createMainLabel(step3Panel, "Security", new ViewFactory.Bounds(250, 100, 300, 40));
            ViewFactory.createFieldLabel(step3Panel, "Email:", new ViewFactory.Bounds(80, 160, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Username:", new ViewFactory.Bounds(80, 210, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Password:", new ViewFactory.Bounds(80, 260, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Confirm Password:", new ViewFactory.Bounds(80, 310, 250, 30));
            ViewFactory.createFieldLabel(step3Panel, "Bank Account Type:", new ViewFactory.Bounds(80, 360, 250, 30));

            emailField = ViewFactory.createRegisterTextField(step3Panel, new ViewFactory.Bounds(320, 160, 200, 30));
            usernameField = ViewFactory.createRegisterTextField(step3Panel, new ViewFactory.Bounds(320, 210, 200, 30));
            passField = ViewFactory.createPasswordField(step3Panel, new ViewFactory.Bounds(320, 260, 200, 30));
            confirmPassField = ViewFactory.createPasswordField(step3Panel, new ViewFactory.Bounds(320, 310, 200, 30));

            ItemListener bankAccountTypeListener = e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedBankAccountTypeRadioBtn = (JRadioButton) e.getItem();
                }
            };

            savingsRadioBtn = ViewFactory.createRadioButton(step3Panel, "Savings", new ViewFactory.Bounds(320, 360, 120, 30));
                savingsRadioBtn.addItemListener(bankAccountTypeListener);

            checkingsRadioBtn = ViewFactory.createRadioButton(step3Panel, "Checkings", new ViewFactory.Bounds(440, 360, 160, 30));
                checkingsRadioBtn.addItemListener(bankAccountTypeListener);

            bankAccountTypeBtnGroup = new ButtonGroup();
            bankAccountTypeBtnGroup.add(savingsRadioBtn);
            bankAccountTypeBtnGroup.add(checkingsRadioBtn);

            submitBtn = ViewFactory.createCustomButton1(step3Panel, "Submit", new ViewFactory.Bounds(350, 450, 120, 35), 28);
            submitBtn.addActionListener(this::submitBtnActionPerformed);

            initRepeatComponents1(step3Panel);
            initRepeatComponents3(step3Panel);

        return step3Panel;
    }

    private void initHeaderPanel(JPanel panel){
        JPanel bannerPanel = ViewFactory.createHeaderPanel(panel);
        ViewFactory.createHeaderLabel(bannerPanel, "IMBANK REGISTRATION", 40);
    }

    private void initRepeatComponents1(JPanel panel) {
        ViewFactory.createConfirmationLabel(panel, "Already have an account?", new ViewFactory.Bounds(170, 510, 250, 30), 18);
        logInBtn = ViewFactory.createCustomButton1(panel, "Log In", new ViewFactory.Bounds(390, 513, 79, 30), 18);
        logInBtn.addActionListener(this::logInBtnActionPerformed);
    }

    private void initRepeatComponents2(JPanel panel) {
        nextBtn = ViewFactory.createCustomButton1(panel, "Next", new ViewFactory.Bounds(350, 450, 120, 35), 28);
        nextBtn.addActionListener(this::nextBtnActionPerformed);
    }

    private void initRepeatComponents3(JPanel panel) {
        backBtn = ViewFactory.createCustomButton1(panel, "Back", new ViewFactory.Bounds(200, 450, 120, 35), 28);
        backBtn.addActionListener(this::backBtnActionPerformed);
    }

    private void logInBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            bankController.showLoginWindow();
            ViewUtility.enablePanelAndComponents(panels[0], true);
            ViewUtility.enablePanelAndComponents(panels[1], false);
            ViewUtility.enablePanelAndComponents(panels[2], false);
            clearFields();
        });
    }

    private void submitBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if(usernameField.getText().isBlank() || emailField.getText().isBlank() || String.valueOf(passField.getPassword()).isBlank()
                    || String.valueOf(confirmPassField.getPassword()).isBlank() || bankAccountTypeBtnGroup.getSelection() == null){
                ViewUtility.showErrorMessage(null, "Please fill in all fields.");
                return;
            }
            if (String.valueOf(passField.getPassword()).equals(String.valueOf(confirmPassField.getPassword()))) {
                bankController.registerPerson(getRegistrationData());
                clearFields();
            } else {
                ViewUtility.showInfoMessage("Passwords don't match. Please try again");
            }
        });
    }

    private void nextBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if (panels[0].isVisible()) {
                if (String.valueOf(fNameField.getText()).isBlank() || String.valueOf(lNameField.getText()).isBlank()
                        || String.valueOf(ageField.getText()).isBlank() || genderBtnGroup.getSelection() == null) {
                    ViewUtility.showErrorMessage(null, "Please fill in all fields.");
                    return;
                }

                // Age validation
                try {
                    int age = Integer.parseInt(ageField.getText());
                    if (age < 18) {
                        JOptionPane.showMessageDialog(null, "You must be 18 years or older to proceed.",
                                "Age Restriction", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid age.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ViewUtility.enablePanelAndComponents(panels[1], true);
                ViewUtility.enablePanelAndComponents(panels[0], false);
            } else if (panels[1].isVisible()) {
                if (String.valueOf(phoneNumField.getText()).isBlank() || String.valueOf(addressField.getText()).isBlank()
                        || String.valueOf(cityField.getText()).isBlank() || String.valueOf(zipCodeField.getText()).isBlank()
                        || String.valueOf(provinceField.getText()).isBlank()) {
                    ViewUtility.showErrorMessage(null, "Please fill in all fields.");
                    return;
                }
                ViewUtility.enablePanelAndComponents(panels[2], true);
                ViewUtility.enablePanelAndComponents(panels[1], false);
            }
        });
    }


    private void backBtnActionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(() -> {
            if (panels[2].isVisible()) {
                ViewUtility.enablePanelAndComponents(panels[1], true);
                ViewUtility.enablePanelAndComponents(panels[2], false);
            } else if (panels[1].isVisible()) {
                ViewUtility.enablePanelAndComponents(panels[0], true);
                ViewUtility.enablePanelAndComponents(panels[1], false);
            }
        });
    }

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

    private void clearFields() {
        fNameField.setText("");
        lNameField.setText("");
        ageField.setText("");
        genderBtnGroup.clearSelection();
        phoneNumField.setText("");
        addressField.setText("");
        cityField.setText("");
        zipCodeField.setText("");
        provinceField.setText("");
        usernameField.setText("");
        emailField.setText("");
        passField.setText("");
        confirmPassField.setText("");
        bankAccountTypeBtnGroup.clearSelection();
    }
}