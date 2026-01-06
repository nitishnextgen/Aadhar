package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * API Client for Aadhar document verification and OCR processing
 */
public class AadharAPIClient {
    private String apiEndpoint;
    private String apiKey;
    private static final int CONNECTION_TIMEOUT = 30000; // 30 seconds
    private static final int READ_TIMEOUT = 30000; // 30 seconds

    public AadharAPIClient(String apiEndpoint, String apiKey) {
        this.apiEndpoint = apiEndpoint;
        this.apiKey = apiKey;
    }

    /**
     * Fetch Aadhar verification data from OCR API
     * @param filePath Path to the Aadhar document image/PDF
     * @return AadharData object with extracted information
     * @throws Exception if API call fails
     */
    public AadharData fetchAadharData(String filePath) throws Exception {
        // Mock implementation - in real scenario, this would call actual API
        if (!new File(filePath).exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        // Simulated API call with mock response
        return parseMockResponse();
    }

    /**
     * Verify Aadhar number format
     * @param aadhaarNumber The Aadhar number to verify
     * @return true if valid, false otherwise
     */
    public boolean verifyAadharFormat(String aadhaarNumber) {
        // Aadhar is 12-digit number
        if (aadhaarNumber == null || aadhaarNumber.isEmpty()) {
            return false;
        }
        
        String cleanNumber = aadhaarNumber.replaceAll("[^0-9]", "");
        
        // Aadhar should be 12 digits
        if (cleanNumber.length() != 12) {
            return false;
        }
        
        // Check if all characters are digits
        return Pattern.matches("\\d{12}", cleanNumber);
    }

    /**
     * Verify VID (Virtual ID) format
     * @param vid The Virtual ID to verify
     * @return true if valid, false otherwise
     */
    public boolean verifyVIDFormat(String vid) {
        if (vid == null || vid.isEmpty()) {
            return false;
        }
        
        // VID is 16-digit number
        String cleanVID = vid.replaceAll("[^0-9]", "");
        return cleanVID.length() == 16 && Pattern.matches("\\d{16}", cleanVID);
    }

    /**
     * Validate date of birth format (DD/MM/YYYY)
     * @param dob Date of birth string
     * @return true if valid format, false otherwise
     */
    public boolean validateDOBFormat(String dob) {
        if (dob == null || dob.isEmpty()) {
            return false;
        }
        
        // Check DD/MM/YYYY format
        Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$");
        return pattern.matcher(dob).matches();
    }

    /**
     * Validate extracted Aadhar data
     * @param data The AadharData object to validate
     * @return ValidationResult with status and messages
     */
    public ValidationResult validateAadharData(AadharData data) {
        ValidationResult result = new ValidationResult();
        
        if (data == null) {
            result.addError("Aadhar data is null");
            return result;
        }
        
        // Validate name
        if (data.getName() == null || data.getName().trim().isEmpty()) {
            result.addError("Name is empty");
        } else if (data.getNameConfidence() < 80) {
            result.addWarning("Name confidence is low: " + data.getNameConfidence() + "%");
        }
        
        // Validate Aadhar number
        if (data.getAadhaar() == null || data.getAadhaar().trim().isEmpty()) {
            result.addError("Aadhar number is empty");
        } else if (!verifyAadharFormat(data.getAadhaar())) {
            result.addError("Invalid Aadhar format");
        } else if (data.getAadhaarConfidence() < 85) {
            result.addWarning("Aadhar confidence is low: " + data.getAadhaarConfidence() + "%");
        }
        
        // Validate DOB
        if (data.getDob() != null && !data.getDob().isEmpty()) {
            if (!validateDOBFormat(data.getDob())) {
                result.addWarning("Invalid DOB format");
            }
        }
        
        // Validate gender
        if (data.getGender() != null && !data.getGender().isEmpty()) {
            if (!data.getGender().matches("MALE|FEMALE|OTHER")) {
                result.addWarning("Invalid gender value");
            }
        }
        
        // Check fraud status
        if (data.isFraudCheckResult()) {
            result.addError("Fraud detected: " + data.getFraudCheckRemarks());
        }
        
        return result;
    }

    /**
     * Mock response parser - simulates API response
     */
    private AadharData parseMockResponse() {
        AadharData data = new AadharData();
        data.setType("aadhaar_front_bottom");
        data.setName("Ravi Babulal Mali");
        data.setNameConfidence(98);
        data.setAadhaar("XXXXXXXX9740");
        data.setAadhaarConfidence(90);
        data.setVid("9108904587965513");
        data.setVidConfidence(90);
        data.setDob("05/08/1992");
        data.setDobConfidence(98);
        data.setGender("MALE");
        data.setGenderConfidence(98);
        data.setFraudCheckResult(false);
        data.setFraudCheckRemarks("NA");
        data.setBlackWhite(false);
        data.setStatus("success");
        data.setStatusCode("200");
        data.setOcrReqId("bff39c3c72b94bd0acea4d65d3b45260");
        data.setClientRefId("4r2pyf");
        
        Map<String, String> fraudDetails = new HashMap<>();
        fraudDetails.put("textBased", "NA");
        fraudDetails.put("fakeWatermark", "NA");
        fraudDetails.put("tamperedPhoto", "NA");
        fraudDetails.put("genderMismatchInPhoto", "NA");
        data.setFraudCheckDetails(fraudDetails);
        
        return data;
    }

    /**
     * Inner class for validation results
     */
    public static class ValidationResult {
        private List<String> errors = new ArrayList<>();
        private List<String> warnings = new ArrayList<>();
        private boolean isValid = true;

        public void addError(String error) {
            errors.add(error);
            isValid = false;
        }

        public void addWarning(String warning) {
            warnings.add(warning);
        }

        public boolean isValid() {
            return isValid;
        }

        public List<String> getErrors() {
            return errors;
        }

        public List<String> getWarnings() {
            return warnings;
        }

        public String getErrorMessage() {
            return String.join(", ", errors);
        }

        public String getWarningMessage() {
            return String.join(", ", warnings);
        }
    }
}
