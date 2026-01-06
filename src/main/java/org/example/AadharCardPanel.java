package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Visual Aadhar Card Display Component
 */
public class AadharCardPanel extends JPanel {
    private AadharData aadharData;

    public AadharCardPanel(AadharData data) {
        this.aadharData = data;
        setPreferredSize(new Dimension(400, 250));
        setBackground(new Color(255, 255, 255));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Card background (golden/yellow like real Aadhar)
        g2d.setColor(new Color(255, 200, 0));
        g2d.fillRect(20, 20, 360, 210);

        // Card border
        g2d.setColor(new Color(200, 150, 0));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(20, 20, 360, 210);

        // Header
        g2d.setColor(new Color(70, 70, 70));
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("AADHAAR", 160, 45);

        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        g2d.drawString("Unique Identification Authority of India", 100, 58);

        // Horizontal line
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(30, 65, 370, 65);

        // Data section
        g2d.setColor(new Color(50, 50, 50));
        g2d.setFont(new Font("Arial", Font.BOLD, 11));

        int y = 85;
        int lineHeight = 20;

        // Name
        g2d.drawString("Name:", 40, y);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        String name = aadharData.getName() != null ? aadharData.getName() : "Sample Name";
        g2d.drawString(name, 120, y);

        // Aadhar Number
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString("Aadhaar:", 40, y + lineHeight);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        String aadhaar = aadharData.getAadhaar() != null ? aadharData.getAadhaar() : "XXXX XXXX XXXX XXXX";
        g2d.drawString(aadhaar, 120, y + lineHeight);

        // DOB
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString("DOB:", 40, y + lineHeight * 2);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        String dob = aadharData.getDob() != null ? aadharData.getDob() : "01/01/1990";
        g2d.drawString(dob, 120, y + lineHeight * 2);

        // Gender
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString("Gender:", 40, y + lineHeight * 3);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        String gender = aadharData.getGender() != null ? aadharData.getGender() : "M";
        g2d.drawString(gender, 120, y + lineHeight * 3);

        // Address
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString("Address:", 40, y + lineHeight * 4);
        g2d.setFont(new Font("Arial", Font.PLAIN, 9));
        String address = "Sample Address";
        g2d.drawString(address, 120, y + lineHeight * 4);

        // Footer
        g2d.setColor(new Color(100, 100, 100));
        g2d.setFont(new Font("Arial", Font.PLAIN, 8));
        g2d.drawString("Verified: " + java.time.LocalDate.now(), 40, y + lineHeight * 5 + 10);
    }
}
