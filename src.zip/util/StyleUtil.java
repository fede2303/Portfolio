package util;

import javax.swing.*;
import java.awt.*;

public class StyleUtil {
    public static final Color sfondo = Color.decode("#66a7cc");
    public static final Font lazyDogFont = new Font("Comic Sans MS", Font.PLAIN, 14);

    public static void styleFrame(JFrame frame) {
        frame.getContentPane().setBackground(sfondo);
    }

    public static void stylePanel(JPanel panel) {
        panel.setBackground(sfondo);
    }

    public static void styleLabel(JLabel label) {
        label.setFont(lazyDogFont);
        label.setForeground(Color.WHITE);
    }

    public static void styleButton(JButton button) {
        button.setFont(lazyDogFont);
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
    }

    public static void styleTextField(JTextField field) {
        field.setFont(lazyDogFont);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.DARK_GRAY);
    }

    public static void stylePasswordField(JPasswordField field) {
        field.setFont(lazyDogFont);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.DARK_GRAY);
    }

    public static void styleTextArea(JTextArea area) {
        area.setFont(lazyDogFont);
        area.setBackground(Color.WHITE);
        area.setForeground(Color.DARK_GRAY);
    }
}


