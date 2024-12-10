package View;

import javax.swing.*;
import java.awt.*;

// Class responsible for creation of UI Containers and Components
public final class ViewFactory {

    public static JFrame createFrame(String title, int width, int height) {
        return ViewFactory.createFrameHelper(title, width, height);
    }

    public static JPanel createMultiStepPanel(Container container, int width, int height) {
        return ViewFactory.createMultiStepPanelHelper(container, width, height);
    }

    public static JPanel createHeaderPanel(Container container) {
        return ViewFactory.createHeaderPanelHelper(container);
    }

    public static void createHeaderLabel(Container container, String text, int fontSize) {
        ViewFactory.createHeaderLabelHelper(container, text, fontSize);
    }

    public static void createConfirmationLabel(Container container, String text, Bounds bounds, int fontSize) {
       ViewFactory.createConfirmationLabelHelper(container, text, bounds, fontSize);
    }

    public static void createMainLabel(Container container, String text, Bounds bounds) {
        ViewFactory.createMainLabelHelper(container, text, bounds);
    }
    public static void createFieldLabel(Container container, String text, Bounds bounds) {
        ViewFactory.createFieldLabelHelper(container, text, bounds);
    }

    public static JToggleButton createToggleButton(Container container, String label, int x, int y) {
        return ViewFactory.createToggleButtonHelper(container, label, x, y);
    }

    public static JButton createCustomButton1(Container container, String label, Bounds bounds, int fontSize) {
        return ViewFactory.createCustomButton1Helper(container, label, bounds, fontSize);
    }

    public static JRadioButton createRadioButton(Container container, String label, Bounds bounds) {
        return ViewFactory.createRadioButtonHelper(container, label, bounds);
    }

    public static JTextField createLogInTextField(Container container, Bounds bounds) {
        return ViewFactory.createLogInTextFieldHelper(container, bounds);
    }

    public static JTextField createRegisterTextField(Container container, Bounds bounds) {
        return ViewFactory.createRegisterTextFieldHelper(container, bounds);
    }

    public static JPasswordField createLogInPasswordField(Container container, Bounds bounds) {
       return ViewFactory.createLogInPasswordFieldHelper(container, bounds);
    }

    public static JPasswordField createPasswordField(Container container, Bounds bounds) {
        return ViewFactory.createPasswordFieldHelper(container, bounds);
    }

    public static JCheckBox createCheckBox(Container container, String text, Bounds bounds) {
        return ViewFactory.createCheckBoxHelper(container, text, bounds);
    }

    public static class Bounds{
        private int x;
        private int y;
        private int width;
        private int height;

        public Bounds(int x, int y, int width, int height) {
            this.setX(x);
            this.setY(y);
            this.setWidth(width);
            this.setHeight(height);
        }

        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
        public int getY() { return y; }
        public void setY(int y) { this.y = y; }
        public int getWidth() { return width; }
        public void setWidth(int width) { this.width = width; }
        public int getHeight() { return height; }
        public void setHeight(int height) { this.height = height; }
    }

    private static JFrame createFrameHelper(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        return frame;
    }

    private static JPanel createMultiStepPanelHelper(Container container, int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, width, height);
        JFrame frame = (JFrame) container;
        frame.getContentPane().add(panel);
        return panel;
    }

    private static JPanel createHeaderPanelHelper(Container container) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(35, 35, 77));
        panel.setBounds(0, 0, 650, 80);
        container.add(panel);
        return panel;
    }

    private static void createHeaderLabelHelper(Container container, String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("MS UI Gothic", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        container.add(label);
    }

    private static void createConfirmationLabelHelper(Container container, String text, Bounds bounds, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("MS UI Gothic", Font.BOLD, fontSize));
        label.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(label);
    }

    private static void createMainLabelHelper(Container container, String text, Bounds bounds) {
        JLabel label = new JLabel(text);
        label.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        label.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
        container.add(label);
    }
    private static void createFieldLabelHelper(Container container, String text, Bounds bounds) {
        JLabel label1 = new JLabel(text);
        label1.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        label1.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        container.add(label1);
    }

    private static JToggleButton createToggleButtonHelper(Container container, String label, int x, int y) {
        JToggleButton toggleButton = new JToggleButton(label);
        toggleButton.setFocusable(false);
        toggleButton.setBounds(x, y, 100, 30);
        container.add(toggleButton);
        return toggleButton;
    }

    private static JButton createCustomButton1Helper(Container container, String label, Bounds bounds, int fontSize) {
        JButton button = new JButton(label);
        button.setFont(new java.awt.Font("MS UI Gothic", Font.BOLD, fontSize));
        button.setForeground(new java.awt.Color(224, 224, 231));
        button.setBackground(new java.awt.Color(35, 35, 77));
        //button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(button);
        return button;
    }

    private static JRadioButton createRadioButtonHelper(Container container, String label, Bounds bounds) {
        JRadioButton radioButton = new JRadioButton(label);
        radioButton.setFocusable(false);
        radioButton.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
        radioButton.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(radioButton);
        return radioButton;
    }

    private static JTextField createLogInTextFieldHelper(Container container, Bounds bounds) {
        JTextField field = new JTextField();
        field.setFont(new java.awt.Font("MS UI Gothic", Font.PLAIN, 24));
        field.setForeground(new java.awt.Color(102, 102, 102));
        field.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(field);
        return field;
    }

    private static JTextField createRegisterTextFieldHelper(Container container, Bounds bounds) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        textField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(textField);
        return textField;
    }

    private static JPasswordField createLogInPasswordFieldHelper(Container container, Bounds bounds) {
        JPasswordField field = new JPasswordField();
        field.setFont(new java.awt.Font("MS UI Gothic", Font.PLAIN, 24));
        field.setForeground(new java.awt.Color(102, 102, 102));
        field.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(field);
        return field;
    }

    private static JPasswordField createPasswordFieldHelper(Container container, Bounds bounds) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        passwordField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(passwordField);
        return passwordField;
    }

    private static JCheckBox createCheckBoxHelper(Container container, String text, Bounds bounds) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(new Font("MS UI Gothic", Font.PLAIN, 19));
        checkBox.setFocusPainted(false);
        checkBox.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(checkBox);
        return checkBox;
    }
}
