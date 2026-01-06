package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for AadharAPIClient
 */
@DisplayName("Aadhar API Client Tests")
public class AadharAPIClientTest {
    private AadharAPIClient apiClient;

    @BeforeEach
    public void setUp() {
        apiClient = new AadharAPIClient("https://api.ocr.example.com", "test-api-key");
    }

    // ===== Aadhar Format Validation Tests =====
    @Test
    @DisplayName("Should validate correct 12-digit Aadhar number")
    public void testValidAadharFormat() {
        assertTrue(apiClient.verifyAadharFormat("123456789012"));
        assertTrue(apiClient.verifyAadharFormat("999999999999"));
        assertTrue(apiClient.verifyAadharFormat("000000000000"));
    }

    @Test
    @DisplayName("Should validate masked Aadhar format (XXXXXXXX9740)")
    public void testValidMaskedAadharFormat() {
        assertTrue(apiClient.verifyAadharFormat("XXXXXXXX9740"));
    }

    @Test
    @DisplayName("Should reject null Aadhar")
    public void testNullAadhar() {
        assertFalse(apiClient.verifyAadharFormat(null));
    }

    @Test
    @DisplayName("Should reject empty Aadhar")
    public void testEmptyAadhar() {
        assertFalse(apiClient.verifyAadharFormat(""));
    }

    @Test
    @DisplayName("Should reject Aadhar with less than 12 digits")
    public void testInvalidAadharTooShort() {
        assertFalse(apiClient.verifyAadharFormat("12345678901"));
        assertFalse(apiClient.verifyAadharFormat("123"));
    }

    @Test
    @DisplayName("Should reject Aadhar with more than 12 digits")
    public void testInvalidAadharTooLong() {
        assertFalse(apiClient.verifyAadharFormat("1234567890123"));
        assertFalse(apiClient.verifyAadharFormat("123456789012345"));
    }

    @Test
    @DisplayName("Should reject Aadhar with non-numeric characters")
    public void testInvalidAadharWithSpecialChars() {
        assertFalse(apiClient.verifyAadharFormat("1234567890@#"));
        assertFalse(apiClient.verifyAadharFormat("12345678901a"));
    }

    // ===== VID Format Validation Tests =====
    @Test
    @DisplayName("Should validate correct 16-digit VID")
    public void testValidVIDFormat() {
        assertTrue(apiClient.verifyVIDFormat("1234567890123456"));
        assertTrue(apiClient.verifyVIDFormat("9999999999999999"));
        assertTrue(apiClient.verifyVIDFormat("0000000000000000"));
    }

    @Test
    @DisplayName("Should validate actual VID from response")
    public void testValidActualVID() {
        assertTrue(apiClient.verifyVIDFormat("9108904587965513"));
    }

    @Test
    @DisplayName("Should reject null VID")
    public void testNullVID() {
        assertFalse(apiClient.verifyVIDFormat(null));
    }

    @Test
    @DisplayName("Should reject empty VID")
    public void testEmptyVID() {
        assertFalse(apiClient.verifyVIDFormat(""));
    }

    @Test
    @DisplayName("Should reject VID with incorrect length")
    public void testInvalidVIDLength() {
        assertFalse(apiClient.verifyVIDFormat("12345678901234")); // 14 digits
        assertFalse(apiClient.verifyVIDFormat("123456789012345")); // 15 digits
        assertFalse(apiClient.verifyVIDFormat("12345678901234567")); // 17 digits
    }

    // ===== DOB Format Validation Tests =====
    @Test
    @DisplayName("Should validate correct DD/MM/YYYY date format")
    public void testValidDOBFormat() {
        assertTrue(apiClient.validateDOBFormat("05/08/1992"));
        assertTrue(apiClient.validateDOBFormat("31/12/2000"));
        assertTrue(apiClient.validateDOBFormat("01/01/1990"));
    }

    @Test
    @DisplayName("Should validate edge cases for DOB")
    public void testDOBEdgeCases() {
        assertTrue(apiClient.validateDOBFormat("29/02/2000")); // Leap year
        assertTrue(apiClient.validateDOBFormat("31/01/2000"));
        assertTrue(apiClient.validateDOBFormat("30/04/2000"));
    }

    @Test
    @DisplayName("Should reject null DOB")
    public void testNullDOB() {
        assertFalse(apiClient.validateDOBFormat(null));
    }

    @Test
    @DisplayName("Should reject empty DOB")
    public void testEmptyDOB() {
        assertFalse(apiClient.validateDOBFormat(""));
    }

    @Test
    @DisplayName("Should reject invalid DOB format")
    public void testInvalidDOBFormat() {
        assertFalse(apiClient.validateDOBFormat("1992-08-05")); // Wrong format
        assertFalse(apiClient.validateDOBFormat("08/05/1992")); // MM/DD/YYYY
        assertFalse(apiClient.validateDOBFormat("1992/08/05")); // YYYY/MM/DD
    }

    @Test
    @DisplayName("Should reject invalid day/month values in DOB")
    public void testInvalidDOBValues() {
        assertFalse(apiClient.validateDOBFormat("32/01/1992")); // Invalid day
        assertFalse(apiClient.validateDOBFormat("01/13/1992")); // Invalid month
        assertFalse(apiClient.validateDOBFormat("00/01/1992")); // Day 0
        assertFalse(apiClient.validateDOBFormat("01/00/1992")); // Month 0
    }

    // ===== Data Validation Tests =====
    @Test
    @DisplayName("Should validate complete and valid Aadhar data")
    public void testValidateCompleteData() {
        AadharData data = createValidAadharData();
        AadharAPIClient.ValidationResult result = apiClient.validateAadharData(data);
        assertTrue(result.isValid(), "Valid data should pass validation");
    }

    @Test
    @DisplayName("Should detect missing name")
    public void testValidateMissingName() {
        AadharData data = createValidAadharData();
        data.setName("");
        AadharAPIClient.ValidationResult result = apiClient.validateAadharData(data);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().stream().anyMatch(e -> e.contains("Name")));
    }

    @Test
    @DisplayName("Should detect low name confidence")
    public void testValidateLowNameConfidence() {
        AadharData data = createValidAadharData();
        data.setNameConfidence(70);
        AadharAPIClient.ValidationResult result = apiClient.validateAadharData(data);
        assertFalse(result.getWarnings().isEmpty());
        assertTrue(result.getWarnings().stream().anyMatch(w -> w.contains("confidence")));
    }

    @Test
    @DisplayName("Should detect missing Aadhar number")
    public void testValidateMissingAadhar() {
        AadharData data = createValidAadharData();
        data.setAadhaar("");
        AadharAPIClient.ValidationResult result = apiClient.validateAadharData(data);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().stream().anyMatch(e -> e.contains("Aadhar")));
    }

    @Test
    @DisplayName("Should detect fraud in Aadhar data")
    public void testValidateFraudDetection() {
        AadharData data = createValidAadharData();
        data.setFraudCheckResult(true);
        data.setFraudCheckRemarks("Watermark tampering detected");
        AadharAPIClient.ValidationResult result = apiClient.validateAadharData(data);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().stream().anyMatch(e -> e.contains("Fraud")));
    }

    @Test
    @DisplayName("Should handle null data validation")
    public void testValidateNullData() {
        AadharAPIClient.ValidationResult result = apiClient.validateAadharData(null);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().stream().anyMatch(e -> e.contains("null")));
    }

    // ===== API Fetch Tests =====
    @Test
    @DisplayName("Should fetch Aadhar data from API")
    public void testFetchAadharData() throws Exception {
        AadharData data = apiClient.fetchAadharData("dummy.pdf");
        assertNotNull(data);
        assertEquals("aadhaar_front_bottom", data.getType());
        assertEquals("Ravi Babulal Mali", data.getName());
        assertEquals("success", data.getStatus());
    }

    @Test
    @DisplayName("Should throw exception for non-existent file")
    public void testFetchAadharDataFileNotFound() {
        assertThrows(Exception.class, () -> {
            apiClient.fetchAadharData("non_existent_file.pdf");
        });
    }

    // ===== Helper Methods =====
    private AadharData createValidAadharData() {
        AadharData data = new AadharData();
        data.setType("aadhaar_front_bottom");
        data.setName("Ravi Babulal Mali");
        data.setNameConfidence(98);
        data.setAadhaar("123456789012");
        data.setAadhaarConfidence(95);
        data.setVid("1234567890123456");
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
        data.setOcrReqId("test-req-id");
        data.setClientRefId("test-ref-id");
        return data;
    }
}
