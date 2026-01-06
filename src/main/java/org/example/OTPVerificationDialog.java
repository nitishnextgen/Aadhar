package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * OTP Verification Dialog for Aadhar download
 */
public class OTPVerificationDialog extends JDialog {
    private JTextField otpField;
    private JButton verifyButton;
    private JButton resendButton;
    private JLabel statusLabel;
    private String generatedOTP;
    private boolean verified = false;
    private int attempts = 0;
    private int maxAttempts = 3;
    private Timer resendTimer;
    private int resendCountdown = 0;

    public OTPVerificationDialog(JFrame parent, String phoneNumber) {
        super(parent, "Aadhar OTP Verification", true);
        this.generatedOTP = generateOTP();
        initializeUI(phoneNumber);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void initializeUI(String phoneNumber) {
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("OTP Verification");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        // Info
        String maskedPhone = maskPhoneNumber(phoneNumber);
        JLabel infoLabel = new JLabel("An OTP has been sent to " + maskedPhone);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(infoLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // OTP Input Panel
        JPanel otpPanel = new JPanel();
        otpPanel.setBackground(Color.WHITE);
        otpPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel otpLabel = new JLabel("Enter OTP:");
        otpLabel.setFont(new Font("Arial", Font.BOLD, 12));
        otpField = new JTextField(15);
        otpField.setFont(new Font("Arial", Font.PLAIN, 14));
        otpField.setHorizontalAlignment(JTextField.CENTER);
        otpPanel.add(otpLabel, BorderLayout.WEST);
        otpPanel.add(otpField, BorderLayout.CENTER);
        mainPanel.add(otpPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Status Label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setForeground(new Color(100, 100, 100));
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.setBackground(Color.WHITE);

        verifyButton = createStyledButton("Verify OTP");
        verifyButton.addActionListener(e -> verifyOTP());
        buttonsPanel.add(verifyButton);

        resendButton = createStyledButton("Resend OTP");
        resendButton.addActionListener(e -> resendOTP());
        buttonsPanel.add(resendButton);

        mainPanel.add(buttonsPanel);

        add(mainPanel);
        
        // Display OTP in console for testing
        System.out.println("Generated OTP: " + generatedOTP);
        JOptionPane.showMessageDialog(this, 
            "Test OTP: " + generatedOTP + "\n\nIn production, this would be sent to the registered phone.",
            "OTP for Testing", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() < 4) {
            return "XXXXXX XXXX";
        }
        String cleaned = phone.replaceAll("[\\s-]", "");
        if (cleaned.length() < 4) {
            return "XXXXXX XXXX";
        }
        String last4 = cleaned.substring(cleaned.length() - 4);
        return "XXXXXX " + last4;
    }

    private void verifyOTP() {
        String enteredOTP = otpField.getText().trim();
        attempts++;

        if (enteredOTP.isEmpty()) {
            statusLabel.setText("Please enter OTP");
            statusLabel.setForeground(new Color(200, 0, 0));
            return;
        }

        if (enteredOTP.equals(generatedOTP)) {
            verified = true;
            statusLabel.setText("✓ OTP Verified Successfully!");
            statusLabel.setForeground(new Color(0, 150, 0));
            verifyButton.setEnabled(false);
            otpField.setEnabled(false);
            resendButton.setEnabled(false);
            
            // Close dialog after 1.5 seconds
            Timer timer = new Timer(1500, e -> dispose());
            timer.setRepeats(false);
            timer.start();
        } else {
            int remainingAttempts = maxAttempts - attempts;
            if (remainingAttempts <= 0) {
                statusLabel.setText("✗ Max attempts exceeded. Try again later.");
                statusLabel.setForeground(new Color(200, 0, 0));
                verifyButton.setEnabled(false);
                otpField.setEnabled(false);
                resendButton.setEnabled(true);
            } else {
                statusLabel.setText("✗ Invalid OTP. " + remainingAttempts + " attempt(s) remaining");
                statusLabel.setForeground(new Color(200, 0, 0));
            }
            otpField.setText("");
            otpField.requestFocus();
        }
    }

    private void resendOTP() {
        generatedOTP = generateOTP();
        attempts = 0;
        otpField.setText("");
        statusLabel.setText("OTP resent successfully");
        statusLabel.setForeground(new Color(0, 150, 0));
        verifyButton.setEnabled(true);
        resendButton.setEnabled(false);
        resendCountdown = 30;
        
        // Show new OTP
        System.out.println("New Generated OTP: " + generatedOTP);
        JOptionPane.showMessageDialog(this, 
            "Test OTP: " + generatedOTP,
            "OTP Resent", 
            JOptionPane.INFORMATION_MESSAGE);

        // Start resend timer
        resendTimer = new Timer(1000, e -> {
            resendCountdown--;
            if (resendCountdown <= 0) {
                resendButton.setEnabled(true);
                resendButton.setText("Resend OTP");
                ((Timer) e.getSource()).stop();
            } else {
                resendButton.setText("Resend in " + resendCountdown + "s");
            }
        });
        resendTimer.start();
        resendButton.setText("Resend in 30s");
        otpField.requestFocus();
    }

    private String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(66, 133, 244));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(40, 100, 200), 2),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(52, 113, 220));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(20, 70, 180), 2),
                    BorderFactory.createEmptyBorder(10, 25, 10, 25)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(66, 133, 244));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(40, 100, 200), 2),
                    BorderFactory.createEmptyBorder(10, 25, 10, 25)
                ));
            }
        });
        
        return button;
    }

    public boolean isVerified() {
        return verified;
    }
}
