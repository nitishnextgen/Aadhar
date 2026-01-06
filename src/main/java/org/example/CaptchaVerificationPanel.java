package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * CAPTCHA Verification Panel with Image
 */
public class CaptchaVerificationPanel extends JPanel {
    private JTextField captchaField;
    private JButton verifyButton;
    private JButton refreshButton;
    private JLabel statusLabel;
    private JPanel captchaImagePanel;
    private String generatedCaptcha;
    private Runnable onSuccess;

    public CaptchaVerificationPanel(Runnable onSuccess) {
        this.onSuccess = onSuccess;
        initializeUI();
        generateNewCaptcha();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel titleLabel = new JLabel("CAPTCHA Verification");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // CAPTCHA Image Panel
        captchaImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCaptcha((Graphics2D) g);
            }
        };
        captchaImagePanel.setBackground(Color.WHITE);
        captchaImagePanel.setBorder(new LineBorder(Color.GRAY, 2));
        captchaImagePanel.setPreferredSize(new Dimension(300, 100));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        add(captchaImagePanel, gbc);

        // Input field
        captchaField = new JTextField(15);
        captchaField.setFont(new Font("Arial", Font.PLAIN, 14));
        captchaField.setPreferredSize(new Dimension(200, 35));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.7;
        add(captchaField, gbc);

        // Refresh button
        refreshButton = new JButton("↻");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setPreferredSize(new Dimension(40, 35));
        refreshButton.setBackground(new Color(100, 100, 100));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> {
            generateNewCaptcha();
            captchaField.setText("");
            statusLabel.setText(" ");
        });
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 10);
        add(refreshButton, gbc);

        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(statusLabel, gbc);

        // Verify button
        verifyButton = new JButton("Verify CAPTCHA");
        verifyButton.setFont(new Font("Arial", Font.BOLD, 12));
        verifyButton.setBackground(new Color(25, 103, 210));
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setFocusPainted(false);
        verifyButton.setPreferredSize(new Dimension(140, 40));
        verifyButton.addActionListener(e -> handleVerification());
        gbc.gridy = 4;
        add(verifyButton, gbc);
    }

    private void generateNewCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        for (int i = 0; i < 6; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        generatedCaptcha = captcha.toString();
        captchaImagePanel.repaint();
    }

    private void drawCaptcha(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Clear background with white
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 300, 100);
        
        // Draw background noise (light lines only)
        g2d.setColor(new Color(220, 220, 220));
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(300);
            int y1 = random.nextInt(100);
            int x2 = random.nextInt(300);
            int y2 = random.nextInt(100);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Draw CAPTCHA text with better visibility
        g2d.setColor(new Color(0, 0, 0)); // Pure black for maximum contrast
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics fm = g2d.getFontMetrics();
        
        int panelWidth = 300;
        int panelHeight = 100;
        int startY = (panelHeight - fm.getAscent()) / 2 + fm.getAscent();

        for (int i = 0; i < generatedCaptcha.length(); i++) {
            char c = generatedCaptcha.charAt(i);
            
            // Position each character
            int offsetX = 20 + i * 45;
            int offsetY = startY + random.nextInt(15) - 7;
            double angle = (random.nextDouble() - 0.5) * 0.3; // Small rotation
            
            AffineTransform transform = new AffineTransform();
            transform.translate(offsetX, offsetY);
            transform.rotate(angle);
            
            AffineTransform oldTransform = g2d.getTransform();
            g2d.setTransform(transform);
            g2d.drawString(String.valueOf(c), 0, 0);
            g2d.setTransform(oldTransform);
        }

        // Draw minimal noise dots
        g2d.setColor(new Color(180, 180, 180));
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(300);
            int y = random.nextInt(100);
            g2d.fillOval(x, y, 1, 1);
        }
        
        // Draw border
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new java.awt.BasicStroke(2));
        g2d.drawRect(0, 0, 299, 99);
    }

    private void handleVerification() {
        String enteredCaptcha = captchaField.getText().trim().toUpperCase();
        
        if (enteredCaptcha.isEmpty()) {
            statusLabel.setText("Please enter CAPTCHA");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (enteredCaptcha.equals(generatedCaptcha)) {
            statusLabel.setText("CAPTCHA verified successfully!");
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
            statusLabel.setText("Invalid CAPTCHA! Try again");
            statusLabel.setForeground(Color.RED);
            captchaField.setText("");
            generateNewCaptcha();
        }
    }
}
