package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * PDF Export Utility for Documents
 */
public class PDFExporter {
    
    public static void exportAadharToPDF(AadharData data, String filePath) throws IOException {
        StringBuilder pdfContent = new StringBuilder();
        
        pdfContent.append("%PDF-1.4\n");
        pdfContent.append("1 0 obj\n");
        pdfContent.append("<< /Type /Catalog /Pages 2 0 R >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("2 0 obj\n");
        pdfContent.append("<< /Type /Pages /Kids [3 0 R] /Count 1 >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("3 0 obj\n");
        pdfContent.append("<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Contents 4 0 R /Resources << /Font << /F1 5 0 R >> >> >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("4 0 obj\n");
        pdfContent.append("<< >>\n");
        pdfContent.append("stream\n");
        
        // PDF content
        StringBuilder content = new StringBuilder();
        content.append("BT\n");
        content.append("/F1 24 Tf\n");
        content.append("50 750 Td\n");
        content.append("(AADHAR VERIFICATION REPORT) Tj\n");
        content.append("ET\n");
        
        content.append("BT\n");
        content.append("/F1 12 Tf\n");
        content.append("50 700 Td\n");
        content.append("(Generated: ").append(getCurrentDateTime()).append(") Tj\n");
        content.append("ET\n");
        
        // Details
        content.append("BT\n");
        content.append("/F1 11 Tf\n");
        content.append("50 650 Td\n");
        content.append("(Aadhar Number: ").append(maskAadhar(data.getAadhaar())).append(") Tj\n");
        content.append("ET\n");
        
        content.append("BT\n");
        content.append("/F1 11 Tf\n");
        content.append("50 630 Td\n");
        content.append("(Name: ").append(data.getName()).append(") Tj\n");
        content.append("ET\n");
        
        content.append("BT\n");
        content.append("/F1 11 Tf\n");
        content.append("50 610 Td\n");
        content.append("(Date of Birth: ").append(data.getDob()).append(") Tj\n");
        content.append("ET\n");
        
        content.append("BT\n");
        content.append("/F1 11 Tf\n");
        content.append("50 590 Td\n");
        content.append("(Gender: ").append(data.getGender()).append(") Tj\n");
        content.append("ET\n");
        
        content.append("BT\n");
        content.append("/F1 11 Tf\n");
        content.append("50 570 Td\n");
        content.append("(Phone: ").append(maskPhone(data.getPhone())).append(") Tj\n");
        content.append("ET\n");
        
        content.append("BT\n");
        content.append("/F1 11 Tf\n");
        content.append("50 550 Td\n");
        content.append("(Status: VERIFIED) Tj\n");
        content.append("ET\n");
        
        pdfContent.append(content.toString());
        pdfContent.append("endstream\n");
        pdfContent.append("endobj\n");
        pdfContent.append("5 0 obj\n");
        pdfContent.append("<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("xref\n");
        pdfContent.append("0 6\n");
        pdfContent.append("0000000000 65535 f\n");
        pdfContent.append("0000000009 00000 n\n");
        pdfContent.append("0000000074 00000 n\n");
        pdfContent.append("0000000133 00000 n\n");
        pdfContent.append("0000000281 00000 n\n");
        pdfContent.append("0000000590 00000 n\n");
        pdfContent.append("trailer\n");
        pdfContent.append("<< /Size 6 /Root 1 0 R >>\n");
        pdfContent.append("startxref\n");
        pdfContent.append("687\n");
        pdfContent.append("%%EOF\n");
        
        // Write to file
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(pdfContent.toString());
        }
    }
    
    public static void exportDocumentToPDF(String docType, String documentInfo, String filePath) throws IOException {
        StringBuilder pdfContent = new StringBuilder();
        
        pdfContent.append("%PDF-1.4\n");
        pdfContent.append("1 0 obj\n");
        pdfContent.append("<< /Type /Catalog /Pages 2 0 R >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("2 0 obj\n");
        pdfContent.append("<< /Type /Pages /Kids [3 0 R] /Count 1 >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("3 0 obj\n");
        pdfContent.append("<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Contents 4 0 R /Resources << /Font << /F1 5 0 R >> >> >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("4 0 obj\n");
        pdfContent.append("<< >>\n");
        pdfContent.append("stream\n");
        
        // PDF content
        StringBuilder content = new StringBuilder();
        content.append("BT\n");
        content.append("/F1 24 Tf\n");
        content.append("50 750 Td\n");
        content.append("(").append(docType).append(" VERIFICATION REPORT) Tj\n");
        content.append("ET\n");
        
        content.append("BT\n");
        content.append("/F1 12 Tf\n");
        content.append("50 700 Td\n");
        content.append("(Generated: ").append(getCurrentDateTime()).append(") Tj\n");
        content.append("ET\n");
        
        pdfContent.append(content.toString());
        pdfContent.append("endstream\n");
        pdfContent.append("endobj\n");
        pdfContent.append("5 0 obj\n");
        pdfContent.append("<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\n");
        pdfContent.append("endobj\n");
        pdfContent.append("xref\n");
        pdfContent.append("0 6\n");
        pdfContent.append("0000000000 65535 f\n");
        pdfContent.append("0000000009 00000 n\n");
        pdfContent.append("0000000074 00000 n\n");
        pdfContent.append("0000000133 00000 n\n");
        pdfContent.append("0000000281 00000 n\n");
        pdfContent.append("0000000490 00000 n\n");
        pdfContent.append("trailer\n");
        pdfContent.append("<< /Size 6 /Root 1 0 R >>\n");
        pdfContent.append("startxref\n");
        pdfContent.append("587\n");
        pdfContent.append("%%EOF\n");
        
        // Write to file
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(pdfContent.toString());
        }
    }
    
    private static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
    
    private static String maskAadhar(String aadhar) {
        if (aadhar == null || aadhar.length() < 4) return "XXXX XXXX XXXX XXXX";
        return aadhar.substring(0, 4) + " XXXX XXXX " + aadhar.substring(aadhar.length() - 4);
    }
    
    private static String maskPhone(String phone) {
        if (phone == null || phone.length() < 4) return "XXXXXX XXXX";
        return "XXXXXX " + phone.substring(phone.length() - 4);
    }
}
