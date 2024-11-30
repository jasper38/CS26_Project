package Utility;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewUtility {
    public static void setEnabledPanelAndComponents(Container container, boolean isEnabled) {
        for (Component component : container.getComponents()) {
            component.setEnabled(isEnabled);
            if (component instanceof Container) {
                setEnabledPanelAndComponents((Container) component, isEnabled);
            }
        }
        container.setVisible(isEnabled);
    }

    public static  KeyAdapter addNumberInputKeyListener() {
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
}
