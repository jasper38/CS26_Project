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
        frame.addWindowListener(ViewUtility.getWindowAdapter());
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

    public static JLabel createLabel(Container container, String label, int x, int y) {
        JLabel label1 = new JLabel(label);
        return label1;
    }

    public static JTextField createTextField(Container container, String label, int x, int y) {
        JTextField textField = new JTextField();
        return textField;
    }

    public static JButton createButton(Container container, String label, int x, int y) {
        JButton button = new JButton(label);
        return button;
    }
}
