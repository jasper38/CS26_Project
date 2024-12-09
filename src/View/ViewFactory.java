package View;

import Utility.ViewUtility;

import javax.swing.*;
import java.awt.*;

// Class responsible for creation of UI Containers and Components
public final class ViewFactory {

    /*
    * Creates a JFrame container
    *
    * @param title  the title or name of the frame
    * @param width  the specified width of the frame
    * @param height the specified height of the frame
    * @return the created frame/window
    */
    public static JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(null);
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
        return frame;
    }

    /*
    * Creates a JPanel with specified width, height, and coordinate
    *
    * @param container  the container to add said panel, i.e. frame
    * @param x          the x-coordinate of the panel
    * @param y          the y-coordinate of the panel
    * @param width      the width of the panel
    * @param height     the height of the panel
    * @return the created panel
    */
    public static JPanel createPanel(Container container, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
            panel.setLayout(null);
            JFrame frame = (JFrame) container;
            frame.getContentPane().add(panel);
        return panel;
    }

    public static JLabel createLabel(Container container, String label, Bounds bounds, int fontSize) {
        JLabel label1 = new JLabel(label);
        label1.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        label1.setFont(new Font("MS UI Gothic", Font.BOLD, fontSize));
        container.add(label1);
        return label1;
    }
    /*
    * Creates a JToggleButton with a specified label and position.
    *
    * @param container  the container to add said toggleButton
    * @param label      the button label
    * @param x          the x-coordinate of the button
    * @param y          the y-coordinate of the button
    * @return the created JToggleButton
    */
    public static JToggleButton createToggleButton(Container container, String label, int x, int y) {
        JToggleButton toggleButton = new JToggleButton(label);
            toggleButton.setFocusable(false);
            toggleButton.setBounds(x, y, 100, 30);
            container.add(toggleButton);
        return toggleButton;
    }

    public static JButton createButton(Container container, String label, Bounds bounds) {
        JButton button = new JButton(label);

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
        return textField;
    }

    public static JPasswordField createPasswordField(Container container, String label, int x, int y) {
        JPasswordField passwordField = new JPasswordField();
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

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
