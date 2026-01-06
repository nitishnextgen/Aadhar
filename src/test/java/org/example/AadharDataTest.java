package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for AadharData model
 */
@DisplayName("Aadhar Data Model Tests")
public class AadharDataTest {
    private AadharData aadharData;

    @BeforeEach
    public void setUp() {
        aadharData = new AadharData();
    }

    @Test
    @DisplayName("Should create AadharData with default values")
    public void testConstructorDefaults() {
        assertNotNull(aadharData);
        assertEquals("pending", aadharData.getStatus());
        assertEquals("0", aadharData.getStatusCode());
        assertNotNull(aadharData.getFraudCheckDetails());
    }

    @Test
    @DisplayName("Should set and get name")
    public void testNameSetterGetter() {
        String testName = "Ravi Babulal Mali";
        aadharData.setName(testName);
        assertEquals(testName, aadharData.getName());
    }

    @Test
    @DisplayName("Should set and get Aadhar number")
    public void testAadharSetterGetter() {
        String testAadhar = "XXXXXXXX9740";
        aadharData.setAadhaar(testAadhar);
        assertEquals(testAadhar, aadharData.getAadhaar());
    }

    @Test
    @DisplayName("Should set and get VID")
    public void testVIDSetterGetter() {
        String testVID = "9108904587965513";
        aadharData.setVid(testVID);
        assertEquals(testVID, aadharData.getVid());
    }

    @Test
    @DisplayName("Should set and get date of birth")
    public void testDOBSetterGetter() {
        String testDOB = "05/08/1992";
        aadharData.setDob(testDOB);
        assertEquals(testDOB, aadharData.getDob());
    }

    @Test
    @DisplayName("Should set and get gender")
    public void testGenderSetterGetter() {
        String testGender = "MALE";
        aadharData.setGender(testGender);
        assertEquals(testGender, aadharData.getGender());
    }

    @Test
    @DisplayName("Should set and get phone")
    public void testPhoneSetterGetter() {
        String testPhone = "9876543210";
        aadharData.setPhone(testPhone);
        assertEquals(testPhone, aadharData.getPhone());
    }

    @Test
    @DisplayName("Should set and get father name")
    public void testFatherSetterGetter() {
        String testFather = "Babulal Mali";
        aadharData.setFather(testFather);
        assertEquals(testFather, aadharData.getFather());
    }

    @Test
    @DisplayName("Should set and get mother name")
    public void testMotherSetterGetter() {
        String testMother = "Savitri Mali";
        aadharData.setMother(testMother);
        assertEquals(testMother, aadharData.getMother());
    }

    @Test
    @DisplayName("Should set and get husband name")
    public void testHusbandSetterGetter() {
        String testHusband = "John Doe";
        aadharData.setHusband(testHusband);
        assertEquals(testHusband, aadharData.getHusband());
    }

    @Test
    @DisplayName("Should set and get confidence scores")
    public void testConfidenceScores() {
        aadharData.setNameConfidence(98);
        aadharData.setAadhaarConfidence(95);
        aadharData.setVidConfidence(90);
        aadharData.setDobConfidence(98);
        aadharData.setGenderConfidence(98);

        assertEquals(98, aadharData.getNameConfidence());
        assertEquals(95, aadharData.getAadhaarConfidence());
        assertEquals(90, aadharData.getVidConfidence());
        assertEquals(98, aadharData.getDobConfidence());
        assertEquals(98, aadharData.getGenderConfidence());
    }

    @Test
    @DisplayName("Should set and get fraud check results")
    public void testFraudCheckResults() {
        aadharData.setFraudCheckResult(true);
        aadharData.setFraudCheckRemarks("Watermark tampering detected");
        
        assertTrue(aadharData.isFraudCheckResult());
        assertEquals("Watermark tampering detected", aadharData.getFraudCheckRemarks());
    }

    @Test
    @DisplayName("Should set and get quality check flags")
    public void testQualityCheckFlags() {
        aadharData.setBlackWhite(true);
        assertTrue(aadharData.isBlackWhite());
        
        aadharData.setBlackWhite(false);
        assertFalse(aadharData.isBlackWhite());
    }

    @Test
    @DisplayName("Should set and get OCR metadata")
    public void testOCRMetadata() {
        String testReqId = "bff39c3c72b94bd0acea4d65d3b45260";
        String testRefId = "4r2pyf";
        
        aadharData.setOcrReqId(testReqId);
        aadharData.setClientRefId(testRefId);
        
        assertEquals(testReqId, aadharData.getOcrReqId());
        assertEquals(testRefId, aadharData.getClientRefId());
    }

    @Test
    @DisplayName("Should set and get status and status code")
    public void testStatusFields() {
        aadharData.setStatus("success");
        aadharData.setStatusCode("200");
        
        assertEquals("success", aadharData.getStatus());
        assertEquals("200", aadharData.getStatusCode());
    }

    @Test
    @DisplayName("Should set and get fraud check details map")
    public void testFraudCheckDetailsMap() {
        java.util.Map<String, String> fraudDetails = new java.util.HashMap<>();
        fraudDetails.put("textBased", "NA");
        fraudDetails.put("fakeWatermark", "NA");
        fraudDetails.put("tamperedPhoto", "DETECTED");
        fraudDetails.put("genderMismatchInPhoto", "NA");
        
        aadharData.setFraudCheckDetails(fraudDetails);
        
        assertEquals(fraudDetails, aadharData.getFraudCheckDetails());
        assertEquals("DETECTED", aadharData.getFraudCheckDetails().get("tamperedPhoto"));
    }

    @Test
    @DisplayName("Should generate meaningful toString representation")
    public void testToString() {
        aadharData.setName("Ravi Babulal Mali");
        aadharData.setAadhaar("XXXXXXXX9740");
        aadharData.setDob("05/08/1992");
        aadharData.setGender("MALE");
        aadharData.setStatus("success");
        
        String toString = aadharData.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Ravi Babulal Mali"));
        assertTrue(toString.contains("XXXXXXXX9740"));
        assertTrue(toString.contains("05/08/1992"));
        assertTrue(toString.contains("success"));
    }

    @Test
    @DisplayName("Should handle all null values gracefully")
    public void testNullValues() {
        aadharData.setName(null);
        aadharData.setAadhaar(null);
        aadharData.setVid(null);
        aadharData.setDob(null);
        
        assertNull(aadharData.getName());
        assertNull(aadharData.getAadhaar());
        assertNull(aadharData.getVid());
        assertNull(aadharData.getDob());
    }

    @Test
    @DisplayName("Should maintain data integrity after multiple updates")
    public void testDataIntegrity() {
        // Initial set
        aadharData.setName("John Doe");
        aadharData.setNameConfidence(90);
        
        // Verify
        assertEquals("John Doe", aadharData.getName());
        assertEquals(90, aadharData.getNameConfidence());
        
        // Update
        aadharData.setName("Jane Doe");
        aadharData.setNameConfidence(95);
        
        // Verify update
        assertEquals("Jane Doe", aadharData.getName());
        assertEquals(95, aadharData.getNameConfidence());
    }
}
