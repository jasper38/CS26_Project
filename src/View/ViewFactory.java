package View;

import javax.swing.*;
import java.awt.*;

// Class responsible for creation of UI Containers and Components
public final class ViewFactory {

    public static JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(null);
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
        return frame;
    }

    public static JPanel createMultiStepPanel(Container container, int width, int height) {
        JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(0, 0, width, height);
            JFrame frame = (JFrame) container;
            frame.getContentPane().add(panel);
        return panel;
    }

    public static JPanel createHeaderPanel(Container container) {
        JPanel panel = new JPanel();
            panel.setBackground(new Color(35, 35, 77));
            panel.setBounds(0, 0, 650, 80);
            container.add(panel);
        return panel;
    }

    public static void createHeaderLabel(Container container, String text) {
        JLabel label = new JLabel(text);
            label.setFont(new Font("MS UI Gothic", Font.BOLD, 40));
            label.setForeground(Color.WHITE);
            container.add(label);
    }

    public static void createMainLabel(Container container, String text, Bounds bounds) {
        JLabel label = new JLabel(text);
            label.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
            label.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
            container.add(label);
    }
    public static void createFieldLabel(Container container, String text, Bounds bounds) {
        JLabel label1 = new JLabel(text);
            label1.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
            label1.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
            container.add(label1);
    }

    public static JToggleButton createToggleButton(Container container, String label, int x, int y) {
        JToggleButton toggleButton = new JToggleButton(label);
            toggleButton.setFocusable(false);
            toggleButton.setBounds(x, y, 100, 30);
            container.add(toggleButton);
        return toggleButton;
    }

    public static JButton createCustomButton1(Container container, String label, Bounds bounds) {
        JButton button = new JButton(label);
        button.setFont(new java.awt.Font("MS UI Gothic", 1, 18));
        button.setForeground(new java.awt.Color(224, 224, 231));
        button.setBackground(new java.awt.Color(35, 35, 77));
        button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 35, 77), 1, true));
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(button);
        return button;
    }

    public static JRadioButton createRadioButton(Container container, String label, Bounds bounds) {
        JRadioButton radioButton = new JRadioButton(label);
            radioButton.setFocusable(false);
            radioButton.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
            radioButton.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
            container.add(radioButton);
        return radioButton;
    }

    public static JTextField createTextField(Container container, Bounds bounds) {
        JTextField textField = new JTextField();
            textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
            textField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
            container.add(textField);
        return textField;
    }

    public static JPasswordField createPasswordField(Container container, Bounds bounds) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
        passwordField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        container.add(passwordField);
        return passwordField;
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
}
