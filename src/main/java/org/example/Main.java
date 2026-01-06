package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}

/**
 * Frame that manages login and main application flow
 */
class LoginFrame extends JFrame {
    private LoginPanel loginPanel;
    private EnhancedMainFrame mainFrame;

    public LoginFrame() {
        setTitle("Aadhar Verification System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Show login panel
        loginPanel = new LoginPanel(() -> onLoginSuccess());
        add(loginPanel);
        
        setVisible(true);
    }

    private void onLoginSuccess() {
        // Close login frame
        dispose();
        
        // Open main application
        mainFrame = new EnhancedMainFrame();
    }
}
