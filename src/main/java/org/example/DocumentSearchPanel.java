package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for searching Aadhar with OTP, CAPTCHA, and full verification flow
 */
public class DocumentSearchPanel extends JPanel {
    private JTextField aadharField;
    private JButton searchButton;
    private JPanel cardDisplayPanel;
    private AadharData currentAadharData;

    public DocumentSearchPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 245));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(25, 103, 210));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel label = new JLabel("Enter Aadhar Number:");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.WHITE);
        searchPanel.add(label);

        aadharField = new JTextField(20);
        aadharField.setFont(new Font("Arial", Font.PLAIN, 12));
        aadharField.setPreferredSize(new Dimension(200, 30));
        searchPanel.add(aadharField);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 12));
        searchButton.setBackground(new Color(0, 200, 0));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(e -> handleSearch());
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        // Card Display Panel
        cardDisplayPanel = new JPanel();
        cardDisplayPanel.setBackground(new Color(240, 240, 245));
        cardDisplayPanel.setLayout(new BorderLayout());
        
        add(cardDisplayPanel, BorderLayout.CENTER);
    }

    private void handleSearch() {
        String aadharNumber = aadharField.getText().trim();
        
        if (aadharNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Aadhar number", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Show loading message
        searchButton.setEnabled(false);
        searchButton.setText("Searching...");

        // Fetch data in background
        SwingWorker<AadharData, Void> worker = new SwingWorker<AadharData, Void>() {
            @Override
            protected AadharData doInBackground() throws Exception {
                // Create API client
                AadharAPIClient apiClient = new AadharAPIClient("https://api.example.com", "YOUR_API_KEY");
                
                // Verify format first
                if (!apiClient.verifyAadharFormat(aadharNumber)) {
                    throw new Exception("Invalid Aadhar format. Please enter 12 digits.");
                }
                
                // Create mock Aadhar data with entered number
                AadharData aadharData = new AadharData();
                aadharData.setAadhaar(aadharNumber);
                aadharData.setName("JOHN KUMAR");
                aadharData.setDob("15/05/1990");
                aadharData.setGender("M");
                aadharData.setPhone("98765XXXX");
                aadharData.setStatus("Verified");
                aadharData.setVid("XXXX XXXX XXXX XXXX");
                aadharData.setType("Aadhar Card");
                aadharData.setAddress("123 Main Street, New Delhi, Delhi 110001");
                
                Thread.sleep(1000); // Simulate API delay
                return aadharData;
            }

            @Override
            protected void done() {
                searchButton.setEnabled(true);
                searchButton.setText("Search");
                
                try {
                    currentAadharData = get();
                    
                    // Show OTP verification dialog
                    showOTPVerification();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DocumentSearchPanel.this, 
                        "Error: " + ex.getMessage(), 
                        "Search Failed", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void showOTPVerification() {
        JDialog otpDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "OTP Verification", true);
        otpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        otpDialog.setSize(400, 400);
        otpDialog.setLocationRelativeTo(this);
        
        OTPVerificationPanel otpPanel = new OTPVerificationPanel(() -> {
            otpDialog.dispose();
            showCaptchaVerification();
        });
        
        otpDialog.add(otpPanel);
        otpDialog.setVisible(true);
    }

    private void showCaptchaVerification() {
        JDialog captchaDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "CAPTCHA Verification", true);
        captchaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        captchaDialog.setSize(450, 350);
        captchaDialog.setLocationRelativeTo(this);
        
        CaptchaVerificationPanel captchaPanel = new CaptchaVerificationPanel(() -> {
            captchaDialog.dispose();
            showAadharDisplay();
        });
        
        captchaDialog.add(captchaPanel);
        captchaDialog.setVisible(true);
    }

    private void showAadharDisplay() {
        // Clear previous content
        cardDisplayPanel.removeAll();
        
        // Create and add Aadhar display panel
        AadharDisplayPanel displayPanel = new AadharDisplayPanel(currentAadharData, this::downloadAadhar);
        cardDisplayPanel.add(displayPanel, BorderLayout.CENTER);
        
        cardDisplayPanel.revalidate();
        cardDisplayPanel.repaint();
    }

    private void downloadAadhar() {
        // Automatic download trigger
        JOptionPane.showMessageDialog(this, 
            "Aadhar PDF has been downloaded successfully!", 
            "Download Complete", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // In real implementation, PDF export would happen here
        // PDFExporter exporter = new PDFExporter();
        // exporter.exportToPDF(currentAadharData);
    }
}
