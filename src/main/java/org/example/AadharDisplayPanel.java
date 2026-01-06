package org.example;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Aadhar Display Panel with Front and Back Images
 */
public class AadharDisplayPanel extends JPanel {
    private AadharData aadharData;
    private JButton downloadButton;
    private Runnable onDownload;

    public AadharDisplayPanel(AadharData aadharData, Runnable onDownload) {
        this.aadharData = aadharData;
        this.onDownload = onDownload;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(240, 240, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Verified Aadhar Card");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Cards container
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(1, 2, 20, 0));
        cardsPanel.setBackground(new Color(240, 240, 245));

        // Front side
        JPanel frontPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawFrontAadhar((Graphics2D) g);
            }
        };
        frontPanel.setBackground(new Color(255, 255, 255));
        frontPanel.setBorder(new TitledBorder("Front Side"));
        frontPanel.setPreferredSize(new Dimension(350, 250));
        cardsPanel.add(frontPanel);

        // Back side
        JPanel backPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackAadhar((Graphics2D) g);
            }
        };
        backPanel.setBackground(new Color(255, 255, 255));
        backPanel.setBorder(new TitledBorder("Back Side"));
        backPanel.setPreferredSize(new Dimension(350, 250));
        cardsPanel.add(backPanel);

        add(cardsPanel, BorderLayout.CENTER);

        // Details panel
        JPanel detailsPanel = createDetailsPanel();
        add(detailsPanel, BorderLayout.SOUTH);
    }

    private void drawFrontAadhar(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Golden background
        g.setColor(new Color(255, 200, 0));
        g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);

        // UIDAI header
        g.setColor(new Color(0, 51, 102));
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Unique Identification Authority of India", 20, 35);
        
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("AADHAAR", 120, 60);

        // Photo placeholder
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(20, 80, 60, 80);
        g.setColor(Color.GRAY);
        g.drawString("Photo", 30, 130);

        // Details
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", Font.PLAIN, 11));

        int x = 100, y = 90;
        int lineHeight = 18;

        g.drawString("Name: " + (aadharData.getName() != null ? aadharData.getName() : "N/A"), x, y);
        y += lineHeight;
        
        g.drawString("Aadhar Number:", x, y);
        String maskedAadhar = aadharData.getAadhaar();
        if (maskedAadhar != null && maskedAadhar.length() >= 4) {
            String masked = "XXXX XXXX " + maskedAadhar.substring(maskedAadhar.length() - 4);
            g.drawString(masked, x + 110, y);
        }
        y += lineHeight;
        
        g.drawString("DOB: " + (aadharData.getDob() != null ? aadharData.getDob() : "N/A"), x, y);
        y += lineHeight;
        
        g.drawString("Gender: " + (aadharData.getGender() != null ? aadharData.getGender() : "N/A"), x, y);
        y += lineHeight;
        
        g.drawString("Address:", x, y);
        String address = aadharData.getAddress();
        if (address != null && address.length() > 0) {
            String[] words = address.split(" ");
            int charCount = 0;
            y += 15;
            StringBuilder line = new StringBuilder();
            
            for (String word : words) {
                if ((line.length() + word.length()) > 35) {
                    g.drawString(line.toString(), x + 10, y);
                    y += 12;
                    line = new StringBuilder(word);
                } else {
                    if (line.length() > 0) line.append(" ");
                    line.append(word);
                }
            }
            if (line.length() > 0) {
                g.drawString(line.toString(), x + 10, y);
            }
        }
    }

    private void drawBackAadhar(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Golden background
        g.setColor(new Color(255, 200, 0));
        g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);

        // Back side label
        g.setColor(new Color(0, 51, 102));
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Back Side (For Verification)", 40, 35);

        // QR Code placeholder
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 60, 100, 100);
        g.setColor(Color.GRAY);
        g.drawString("QR Code", 60, 115);

        // Signature placeholder
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(160, 60, 80, 50);
        g.setColor(Color.GRAY);
        g.drawString("Signature", 170, 90);

        // Security features
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        
        g.drawString("Security Features:", 30, 185);
        g.drawString("✓ Hologram", 30, 205);
        g.drawString("✓ Serial Number", 30, 220);
        
        // Serial number
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("SN: " + generateSerialNumber(), 160, 220);
    }

    private String generateSerialNumber() {
        return String.format("%04d-%04d-%04d", 
            (int)(Math.random() * 10000),
            (int)(Math.random() * 10000),
            (int)(Math.random() * 10000));
    }

    private JPanel createDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(new Color(240, 240, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Verification details
        JLabel verifiedLabel = new JLabel("✓ Verification Complete - All documents verified successfully");
        verifiedLabel.setFont(new Font("Arial", Font.BOLD, 12));
        verifiedLabel.setForeground(new Color(0, 150, 0));
        panel.add(verifiedLabel, BorderLayout.WEST);

        // Download button
        downloadButton = new JButton("Download Aadhar PDF");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 12));
        downloadButton.setBackground(new Color(25, 103, 210));
        downloadButton.setForeground(Color.WHITE);
        downloadButton.setFocusPainted(false);
        downloadButton.setPreferredSize(new Dimension(200, 35));
        downloadButton.addActionListener(e -> {
            if (onDownload != null) {
                onDownload.run();
            }
        });
        panel.add(downloadButton, BorderLayout.EAST);

        return panel;
    }
}
