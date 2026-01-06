package org.example;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel for downloading and verifying Aadhar with OTP
 */
public class AadharDownloadPanel extends JPanel {
    private JTextField aadharNumberField;
    private JButton downloadButton;
    private JButton clearButton;
    private JTextArea resultsArea;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private AadharAPIClient apiClient;

    public AadharDownloadPanel() {
        this.apiClient = new AadharAPIClient("https://api.ocr.example.com", "your-api-key");
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Top Panel - Input Section
        JPanel topPanel = createInputPanel();
        add(topPanel, BorderLayout.NORTH);

        // Center Panel - Results
        JPanel centerPanel = createResultsPanel();
        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Status
        JPanel bottomPanel = createStatusPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(230, 230, 230));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Enter Aadhar Details",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        // Aadhar Input Panel
        JPanel inputFieldPanel = new JPanel(new BorderLayout(10, 10));
        inputFieldPanel.setBackground(new Color(230, 230, 230));
        inputFieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel aadharLabel = new JLabel("Aadhar Number:");
        aadharLabel.setFont(new Font("Arial", Font.BOLD, 12));
        aadharNumberField = new JTextField(20);
        aadharNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        aadharNumberField.setToolTipText("Enter 12-digit Aadhar number (XXXX XXXX XXXX)");
        aadharNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                formatAadharInput();
            }
        });

        inputFieldPanel.add(aadharLabel, BorderLayout.WEST);
        inputFieldPanel.add(aadharNumberField, BorderLayout.CENTER);

        panel.add(inputFieldPanel);
        panel.add(Box.createVerticalStrut(10));

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonsPanel.setBackground(new Color(230, 230, 230));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        downloadButton = createStyledButton("📥 Download Aadhar");
        downloadButton.setPreferredSize(new Dimension(180, 40));
        downloadButton.addActionListener(e -> handleDownload());
        buttonsPanel.add(downloadButton);

        JButton exportPDFButton = createStyledButton("📄 Export PDF");
        exportPDFButton.setPreferredSize(new Dimension(140, 40));
        exportPDFButton.addActionListener(e -> handleExportPDF());
        buttonsPanel.add(exportPDFButton);

        clearButton = createStyledButton("🗑️ Clear");
        clearButton.setPreferredSize(new Dimension(120, 40));
        clearButton.addActionListener(e -> handleClear());
        buttonsPanel.add(clearButton);

        panel.add(buttonsPanel);

        return panel;
    }

    private void formatAadharInput() {
        String text = aadharNumberField.getText();
        String digits = text.replaceAll("[^0-9]", "");
        
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < digits.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ");
            }
            formatted.append(digits.charAt(i));
        }
        
        aadharNumberField.setText(formatted.toString());
    }

    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Aadhar Data (Masked)",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        resultsArea.setMargin(new Insets(10, 10, 10, 10));
        resultsArea.setText("Enter Aadhar number and click 'Download Aadhar' to fetch your data...");

        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(230, 230, 230));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        progressBar.setIndeterminate(true);

        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(0, 100, 0));

        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.EAST);

        return panel;
    }

    private void handleDownload() {
        String aadharNumber = aadharNumberField.getText().trim();

        if (aadharNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter your Aadhar number",
                "Input Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isValidAadharNumber(aadharNumber)) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid 12-digit Aadhar number",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show progress
        downloadButton.setEnabled(false);
        clearButton.setEnabled(false);
        progressBar.setVisible(true);
        statusLabel.setText("Processing...");
        statusLabel.setForeground(new Color(100, 100, 0));

        // Simulate API call in background thread
        SwingWorker<AadharData, Void> worker = new SwingWorker<AadharData, Void>() {
            @Override
            protected AadharData doInBackground() throws Exception {
                // Simulate network delay
                Thread.sleep(1500);
                
                // Create mock Aadhar data
                AadharData data = new AadharData();
                data.setAadhaar(aadharNumber);
                data.setName("NITISH KUMAR");
                data.setDob("15-03-1995");
                data.setGender("Male");
                data.setPhone("+91-9876543210");
                data.setVid("123456789012");
                
                return data;
            }

            @Override
            protected void done() {
                try {
                    AadharData aadharData = get();
                    
                    // Show OTP Verification Dialog
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(AadharDownloadPanel.this);
                    OTPVerificationDialog otpDialog = new OTPVerificationDialog(
                        parentFrame,
                        aadharData.getPhone()
                    );
                    otpDialog.setVisible(true);

                    if (otpDialog.isVerified()) {
                        // Get masked data
                        AadharData maskedData = AadharDataMasker.getMaskedData(aadharData);
                        displayMaskedAadharData(maskedData);
                        
                        statusLabel.setText("✓ Aadhar verified and downloaded successfully");
                        statusLabel.setForeground(new Color(0, 150, 0));
                    } else {
                        statusLabel.setText("✗ OTP verification failed");
                        statusLabel.setForeground(new Color(200, 0, 0));
                        resultsArea.setText("OTP verification could not be completed.\nPlease try again.");
                    }
                } catch (Exception e) {
                    statusLabel.setText("✗ Error: " + e.getMessage());
                    statusLabel.setForeground(new Color(200, 0, 0));
                    resultsArea.setText("Error downloading Aadhar:\n" + e.getMessage());
                } finally {
                    progressBar.setVisible(false);
                    downloadButton.setEnabled(true);
                    clearButton.setEnabled(true);
                }
            }
        };
        worker.execute();
    }

    private void displayMaskedAadharData(AadharData maskedData) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════════════════════╗\n");
        sb.append("║         AADHAR DATA (MASKED FOR SECURITY)         ║\n");
        sb.append("╚═══════════════════════════════════════════════════╝\n\n");
        
        sb.append("Name:                ").append(maskedData.getName()).append("\n");
        sb.append("Aadhar Number:       ").append(maskedData.getAadhaar()).append("\n");
        sb.append("VID:                 ").append(maskedData.getVid()).append("\n");
        sb.append("Date of Birth:       ").append(maskedData.getDob()).append("\n");
        sb.append("Gender:              ").append(maskedData.getGender()).append("\n");
        sb.append("Phone Number:        ").append(maskedData.getPhone()).append("\n\n");
        
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        sb.append("✓ Verification Status: VERIFIED\n");
        sb.append("✓ Download Status: SUCCESSFUL\n");
        sb.append("✓ Security: Sensitive data is masked for your safety\n");
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        resultsArea.setText(sb.toString());
    }

    private boolean isValidAadharNumber(String aadharNumber) {
        String digits = aadharNumber.replaceAll("[^0-9]", "");
        return digits.length() == 12;
    }

    private void handleClear() {
        aadharNumberField.setText("");
        resultsArea.setText("Enter Aadhar number and click 'Download Aadhar' to fetch your data...");
        statusLabel.setText("Ready");
        statusLabel.setForeground(new Color(0, 100, 0));
        aadharNumberField.requestFocus();
    }

    private void handleExportPDF() {
        String aadharNumber = aadharNumberField.getText().trim();
        
        if (aadharNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Aadhar number first", "Export Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new java.io.File("Aadhar_Report_" + System.currentTimeMillis() + ".pdf"));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));
        
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.endsWith(".pdf")) {
                    filePath += ".pdf";
                }
                
                // Create sample AadharData for PDF export
                AadharData sampleData = new AadharData();
                sampleData.setAadhaar(aadharNumber);
                sampleData.setName("Sample Name");
                sampleData.setDob("01/01/1990");
                sampleData.setPhone("123-4567-8901");
                sampleData.setGender("M");
                sampleData.setStatus("Verified");
                
                PDFExporter.exportAadharToPDF(sampleData, filePath);
                JOptionPane.showMessageDialog(this, "PDF exported successfully to:\n" + filePath, "Export Success", JOptionPane.INFORMATION_MESSAGE);
                statusLabel.setText("PDF exported: " + new java.io.File(filePath).getName());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error exporting PDF: " + ex.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("PDF export failed");
            }
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(66, 133, 244));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(40, 100, 200), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(52, 113, 220));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(20, 70, 180), 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(66, 133, 244));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(40, 100, 200), 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
        });
        
        return button;
    }
}
