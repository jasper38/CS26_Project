package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class ViewUtility {

    public static void enablePanelAndComponents(Container container, boolean isEnabled) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                enablePanelAndComponents((Container) component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
        container.revalidate();
        container.repaint();
        container.setVisible(isEnabled);
    }

    public static WindowAdapter getWindowAdapter() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        };
    }

    public static KeyAdapter addNumberInputKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '.') {
                    ke.consume();
                }
            }
        };
    }

    public static void show(JFrame frame) {
        frame.setVisible(true);
    }

    public static void hide(JFrame frame) {
        frame.setVisible(false);
    }

    public static void showErrorMessage(JFrame parentFrame, String msg) {
        JOptionPane.showMessageDialog(parentFrame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    // Add document filter here
}
