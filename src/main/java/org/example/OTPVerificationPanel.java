package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * OTP Verification Panel
 */
public class OTPVerificationPanel extends JPanel {
    private JTextField otpField;
    private JButton verifyButton;
    private JLabel statusLabel;
    private JLabel timerLabel;
    private Runnable onSuccess;
    private String generatedOTP;
    private int timeLeft = 60;

    public OTPVerificationPanel(Runnable onSuccess) {
        this.onSuccess = onSuccess;
        initializeUI();
        generateAndDisplayOTP();
        startTimer();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("OTP Verification");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Message
        JLabel messageLabel = new JLabel("Enter OTP sent to your registered phone");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 1;
        add(messageLabel, gbc);

        // OTP display (for demo)
        JLabel otpDisplayLabel = new JLabel("(Demo OTP will be shown below)");
        otpDisplayLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        otpDisplayLabel.setForeground(Color.GRAY);
        gbc.gridy = 2;
        add(otpDisplayLabel, gbc);

        // OTP Field
        otpField = new JTextField(10);
        otpField.setFont(new Font("Arial", Font.BOLD, 20));
        otpField.setHorizontalAlignment(JTextField.CENTER);
        otpField.setPreferredSize(new Dimension(150, 50));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(otpField, gbc);

        // Timer label
        timerLabel = new JLabel("Time left: 60s");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        timerLabel.setForeground(new Color(0, 100, 200));
        gbc.gridy = 4;
        add(timerLabel, gbc);

        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setForeground(Color.RED);
        gbc.gridy = 5;
        add(statusLabel, gbc);

        // Verify button
        verifyButton = new JButton("Verify OTP");
        verifyButton.setFont(new Font("Arial", Font.BOLD, 12));
        verifyButton.setBackground(new Color(25, 103, 210));
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setFocusPainted(false);
        verifyButton.setPreferredSize(new Dimension(120, 40));
        verifyButton.addActionListener(e -> handleVerification());
        gbc.gridy = 6;
        add(verifyButton, gbc);
    }

    private void generateAndDisplayOTP() {
        Random random = new Random();
        generatedOTP = String.format("%06d", random.nextInt(1000000));
        
        // Show OTP in a dialog (for demo)
        JOptionPane.showMessageDialog(this, 
            "Demo OTP: " + generatedOTP + "\n\nEnter this OTP to continue", 
            "OTP Generated", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void startTimer() {
        Timer timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + "s");
            
            if (timeLeft <= 0) {
                verifyButton.setEnabled(false);
                statusLabel.setText("OTP expired!");
                statusLabel.setForeground(Color.RED);
            }
        });
        timer.start();
    }

    private void handleVerification() {
        String enteredOTP = otpField.getText().trim();
        
        if (enteredOTP.isEmpty()) {
            statusLabel.setText("Please enter OTP");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (enteredOTP.equals(generatedOTP)) {
            statusLabel.setText("OTP verified successfully!");
            statusLabel.setForeground(new Color(0, 150, 0));
            verifyButton.setEnabled(false);
            
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(1000);
                    if (onSuccess != null) {
                        onSuccess.run();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            statusLabel.setText("Invalid OTP! Try again");
            statusLabel.setForeground(Color.RED);
            otpField.setText("");
        }
    }
}
