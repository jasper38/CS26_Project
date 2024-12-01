package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class ViewUtility {
    public static void setEnabledPanelAndComponents(Container container, boolean isEnabled) {
        for (Component component : container.getComponents()) {
            component.setEnabled(isEnabled);
            if (component instanceof Container) {
                setEnabledPanelAndComponents((Container) component, isEnabled);
            }
        }
        container.setVisible(isEnabled);
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

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
