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

    public static JPanel createPanel(Container container ,int x, int y, int width, int height) {
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
}
