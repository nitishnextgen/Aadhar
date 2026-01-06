package org.example;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * UI Panel for Aadhar document verification and OCR processing
 */
public class AadharVerificationPanel extends JPanel {
    private AadharAPIClient apiClient;
    private AadharData currentAadharData;
    
    // UI Components
    private JTextArea resultsTextArea;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private JTabbedPane tabbedPane;
    
    // Detail panels
    private JPanel detailsPanel;
    private java.util.Map<String, JTextField> detailFields;

    public AadharVerificationPanel() {
        this.apiClient = new AadharAPIClient("https://api.ocr.example.com", "your-api-key");
        this.detailFields = new java.util.HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Center Panel - Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("OCR Results", createResultsPanel());
        tabbedPane.addTab("Extracted Details", createDetailsPanel());
        tabbedPane.addTab("Validation Report", createValidationPanel());
        tabbedPane.addTab("📥 Download Aadhar", new AadharDownloadPanel());
        tabbedPane.addTab("🔍 Search Documents", new DocumentSearchPanel());
        add(tabbedPane, BorderLayout.CENTER);

        // Bottom Panel - Status
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "OCR Extracted Data",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false);
        resultsTextArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        resultsTextArea.setLineWrap(true);
        resultsTextArea.setWrapStyleWord(true);
        resultsTextArea.setMargin(new Insets(10, 10, 10, 10));
        resultsTextArea.setText("OCR data will be displayed here...");

        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createDetailsPanel() {
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Extracted Details",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        String[] labels = {
            "Name", "Aadhaar", "VID", "Date of Birth",
            "Gender", "Phone", "Father", "Mother"
        };

        String[] fieldNames = {
            "name", "aadhaar", "vid", "dob",
            "gender", "phone", "father", "mother"
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(new Font("Arial", Font.BOLD, 11));
            detailsPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            JTextField field = new JTextField(25);
            field.setEditable(false);
            detailFields.put(fieldNames[i], field);
            detailsPanel.add(field, gbc);
        }

        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(scrollPane, BorderLayout.CENTER);
        
        return wrapper;
    }

    private JPanel createValidationPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Validation Results",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        JTextArea validationArea = new JTextArea();
        validationArea.setEditable(false);
        validationArea.setFont(new Font("Arial", Font.PLAIN, 11));
        validationArea.setLineWrap(true);
        validationArea.setWrapStyleWord(true);
        validationArea.setMargin(new Insets(10, 10, 10, 10));
        validationArea.setText("Validation results will be displayed here...");

        JScrollPane scrollPane = new JScrollPane(validationArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Store reference for later update
        panel.putClientProperty("validationArea", validationArea);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(230, 230, 230));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);

        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(0, 100, 0));

        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.EAST);

        return panel;
    }

    private void updateStatus(String message) {
        updateStatus(message, false);
    }

    private void updateStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? Color.RED : new Color(0, 100, 0));
    }
}
