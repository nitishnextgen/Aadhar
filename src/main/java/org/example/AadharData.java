package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Model class to represent Aadhar document details extracted from OCR
 */
public class AadharData {
    private String type;
    private String name;
    private String aadhaar;
    private String vid;
    private String dob;
    private String gender;
    private String phone;
    private String father;
    private String mother;
    private String husband;
    private int nameConfidence;
    private int aadharConfidence;
    private int vidConfidence;
    private int dobConfidence;
    private int genderConfidence;
    private boolean fraudCheckResult;
    private String fraudCheckRemarks;
    private Map<String, String> fraudCheckDetails;
    private boolean isBlackWhite;
    private String ocrReqId;
    private String clientRefId;
    private String status;
    private String statusCode;
    private String address;

    public AadharData() {
        this.fraudCheckDetails = new HashMap<>();
        this.status = "pending";
        this.statusCode = "0";
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getHusband() {
        return husband;
    }

    public void setHusband(String husband) {
        this.husband = husband;
    }

    public int getNameConfidence() {
        return nameConfidence;
    }

    public void setNameConfidence(int nameConfidence) {
        this.nameConfidence = nameConfidence;
    }

    public int getAadhaarConfidence() {
        return aadharConfidence;
    }

    public void setAadhaarConfidence(int aadharConfidence) {
        this.aadharConfidence = aadharConfidence;
    }

    public int getVidConfidence() {
        return vidConfidence;
    }

    public void setVidConfidence(int vidConfidence) {
        this.vidConfidence = vidConfidence;
    }

    public int getDobConfidence() {
        return dobConfidence;
    }

    public void setDobConfidence(int dobConfidence) {
        this.dobConfidence = dobConfidence;
    }

    public int getGenderConfidence() {
        return genderConfidence;
    }

    public void setGenderConfidence(int genderConfidence) {
        this.genderConfidence = genderConfidence;
    }

    public boolean isFraudCheckResult() {
        return fraudCheckResult;
    }

    public void setFraudCheckResult(boolean fraudCheckResult) {
        this.fraudCheckResult = fraudCheckResult;
    }

    public String getFraudCheckRemarks() {
        return fraudCheckRemarks;
    }

    public void setFraudCheckRemarks(String fraudCheckRemarks) {
        this.fraudCheckRemarks = fraudCheckRemarks;
    }

    public Map<String, String> getFraudCheckDetails() {
        return fraudCheckDetails;
    }

    public void setFraudCheckDetails(Map<String, String> fraudCheckDetails) {
        this.fraudCheckDetails = fraudCheckDetails;
    }

    public boolean isBlackWhite() {
        return isBlackWhite;
    }

    public void setBlackWhite(boolean blackWhite) {
        isBlackWhite = blackWhite;
    }

    public String getOcrReqId() {
        return ocrReqId;
    }

    public void setOcrReqId(String ocrReqId) {
        this.ocrReqId = ocrReqId;
    }

    public String getClientRefId() {
        return clientRefId;
    }

    public void setClientRefId(String clientRefId) {
        this.clientRefId = clientRefId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AadharData{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", aadhaar='" + aadhaar + '\'' +
                ", vid='" + vid + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
