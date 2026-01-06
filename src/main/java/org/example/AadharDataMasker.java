package org.example;

/**
 * Utility class for masking sensitive Aadhar data
 */
public class AadharDataMasker {
    
    /**
     * Mask Aadhar number - show only first 4 and last 4 digits
     * @param aadharNumber Full Aadhar number
     * @return Masked Aadhar number
     */
    public static String maskAadharNumber(String aadharNumber) {
        if (aadharNumber == null || aadharNumber.length() < 8) {
            return "XXXX XXXX XXXX XXXX";
        }
        String cleaned = aadharNumber.replaceAll("[\\s-]", "");
        if (cleaned.length() < 8) {
            return "XXXX XXXX XXXX XXXX";
        }
        String first4 = cleaned.substring(0, 4);
        String last4 = cleaned.substring(cleaned.length() - 4);
        return first4 + " XXXX XXXX " + last4;
    }
    
    /**
     * Mask phone number
     * @param phone Full phone number
     * @return Masked phone number
     */
    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() < 4) {
            return "XXXXXX XXXX";
        }
        String cleaned = phone.replaceAll("[\\s-]", "");
        if (cleaned.length() < 4) {
            return "XXXXXX XXXX";
        }
        String last4 = cleaned.substring(cleaned.length() - 4);
        return "XXXXXX " + last4;
    }
    
    /**
     * Mask email address
     * @param email Full email address
     * @return Masked email address
     */
    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "XX@XXXXXX.COM";
        }
        String[] parts = email.split("@");
        if (parts[0].length() <= 2) {
            return "X" + parts[0].substring(parts[0].length() - 1) + "@" + parts[1];
        }
        String name = parts[0].substring(0, 2) + "***" + parts[0].substring(parts[0].length() - 1);
        return name + "@" + parts[1];
    }
    
    /**
     * Mask name - show only first and last letter
     * @param name Full name
     * @return Masked name
     */
    public static String maskName(String name) {
        if (name == null || name.length() < 2) {
            return "X";
        }
        String[] parts = name.split("\\s");
        StringBuilder masked = new StringBuilder();
        for (String part : parts) {
            if (part.length() > 0) {
                masked.append(part.charAt(0));
                for (int i = 1; i < part.length() - 1; i++) {
                    masked.append("*");
                }
                if (part.length() > 1) {
                    masked.append(part.charAt(part.length() - 1));
                }
                masked.append(" ");
            }
        }
        return masked.toString().trim();
    }
    
    /**
     * Get partial masked data for display
     * @param aadharData The original Aadhar data
     * @return AadharData with masked sensitive fields
     */
    public static AadharData getMaskedData(AadharData aadharData) {
        AadharData masked = new AadharData();
        masked.setName(maskName(aadharData.getName()));
        masked.setAadhaar(maskAadharNumber(aadharData.getAadhaar()));
        masked.setPhone(maskPhoneNumber(aadharData.getPhone()));
        masked.setDob(aadharData.getDob());
        masked.setGender(aadharData.getGender());
        masked.setVid(maskVID(aadharData.getVid()));
        return masked;
    }
    
    /**
     * Mask VID - show only first and last 4 characters
     * @param vid Full VID
     * @return Masked VID
     */
    public static String maskVID(String vid) {
        if (vid == null || vid.length() < 8) {
            return "XXXX XXXX XXXX XXXX";
        }
        String first4 = vid.substring(0, 4);
        String last4 = vid.substring(vid.length() - 4);
        return first4 + " XXXX XXXX " + last4;
    }
    
    /**
     * Mask address - show only first word and last word
     * @param address Full address
     * @return Masked address
     */
    public static String maskAddress(String address) {
        if (address == null || address.length() < 10) {
            return "XXXX XXXX XXXX";
        }
        String[] parts = address.split(",");
        if (parts.length == 0) {
            return "XXXX XXXX XXXX";
        }
        return parts[0].substring(0, Math.min(5, parts[0].length())) + " XXXX ... " + 
               (parts.length > 0 ? parts[parts.length - 1].substring(0, Math.min(5, parts[parts.length - 1].length())) : "");
    }
    
    /**
     * Mask pincode - show only first 2 and last 2 digits
     * @param pincode Full pincode
     * @return Masked pincode
     */
    public static String maskPincode(String pincode) {
        if (pincode == null || pincode.length() < 4) {
            return "XXXXXX";
        }
        String first2 = pincode.substring(0, 2);
        String last2 = pincode.substring(pincode.length() - 2);
        return first2 + "XX" + last2;
    }
}
