# Aadhar KYC Verification System

A comprehensive Know Your Customer (KYC) verification application for Aadhar document verification with OTP and CAPTCHA authentication.

**Developed by: Nitish**

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [File Descriptions](#file-descriptions)
- [Verification Flow](#verification-flow)
- [License](#license)

---

## 🎯 Overview

The Aadhar KYC Verification System is a desktop application built with Java Swing that provides a secure and complete Know Your Customer (KYC) verification process. Users can search for Aadhar documents, verify their identity through OTP and CAPTCHA, and view detailed Aadhar card information (front and back sides) before downloading the verification report.

---

## ✨ Features

- ✅ **User Authentication**: Secure login with username and password
- ✅ **Aadhar Search**: Search Aadhar by 12-digit number
- ✅ **OTP Verification**: One-Time Password verification with 60-second timer
- ✅ **CAPTCHA Verification**: Image-based CAPTCHA with anti-bot security
- ✅ **Aadhar Card Display**: Shows both front and back sides of verified Aadhar
- ✅ **Data Masking**: Sensitive information is masked for privacy
- ✅ **PDF Download**: Automatic PDF export of verified documents
- ✅ **Logout Functionality**: Secure logout with session management
- ✅ **Real-time Validation**: Format validation and fraud detection

---

## 🛠 Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java (JDK) | 25 LTS |
| **GUI Framework** | Swing | Built-in (Java) |
| **Build Tool** | javac (Direct Compilation) | 25 |
| **IDE Support** | VS Code / Any Java IDE | - |
| **Operating System** | Windows 10/11 | - |
| **Executable Format** | JAR (Java Archive) | - |

---

## 📁 Project Structure

```
perfiosandroid/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── Main.java                      [Entry Point]
│   │   │       ├── LoginFrame.java                [Login Screen Manager]
│   │   │       ├── LoginPanel.java                [Login UI Component]
│   │   │       ├── EnhancedMainFrame.java         [Main Application Window]
│   │   │       ├── DocumentSearchPanel.java       [Search & Verification Flow]
│   │   │       ├── OTPVerificationPanel.java      [OTP Verification UI]
│   │   │       ├── CaptchaVerificationPanel.java  [CAPTCHA Verification UI]
│   │   │       ├── AadharDisplayPanel.java        [Aadhar Card Display (Front & Back)]
│   │   │       ├── AadharCardPanel.java           [Golden Aadhar Card Rendering]
│   │   │       ├── AadharDownloadPanel.java       [PDF Download & Export]
│   │   │       ├── AadharVerificationPanel.java   [Multi-tab Verification]
│   │   │       ├── AadharAPIClient.java           [API Client & Validation]
│   │   │       ├── AadharData.java                [Data Model]
│   │   │       ├── AadharDataMasker.java          [Data Privacy/Masking]
│   │   │       ├── PDFExporter.java               [PDF Generation]
│   │   │       └── OTPVerificationDialog.java     [OTP Dialog]
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       │   └── org/example/
│       │       ├── AadharAPIClientTest.java
│       │       └── AadharDataTest.java
│       └── resources/
│
├── bin/
│   └── main/
│       └── org/example/                           [Compiled Classes]
│
├── dist/
│   └── AadharKYC.jar                              [Executable JAR File]
│
├── build.gradle.kts                               [Gradle Configuration (Legacy)]
├── settings.gradle.kts                            [Gradle Settings (Legacy)]
├── build_log.txt                                  [Build Log]
├── MANIFEST.MF                                    [JAR Manifest]
├── gradlew / gradlew.bat                          [Gradle Wrapper]
├── local.properties                               [Local Configuration]
└── README.md                                      [This File]
```

---

## 💾 How to Run

### **Option 1: Using JAR File (Recommended)**

1. **From Desktop**:
   - Double-click `AadharKYC.jar` on your Desktop
   - OR double-click `Run-AadharKYC.bat`

2. **From Command Line**:
   ```bash
   cd C:\Users\NITISH\Desktop
   java -jar AadharKYC.jar
   ```

### **Option 2: Direct Java Execution**

```bash
cd C:\Users\NITISH\Desktop\android _kyc\perfiosandroid\bin\main
java org.example.Main
```

### **Option 3: From Source Code**

1. **Compile**:
   ```bash
   cd C:\Users\NITISH\Desktop\android _kyc\perfiosandroid\src\main\java
   javac -d ..\..\..\bin\main org\example\*.java
   ```

2. **Run**:
   ```bash
   cd C:\Users\NITISH\Desktop\android _kyc\perfiosandroid\bin\main
   java org.example.Main
   ```

---

## 📄 File Descriptions

### **Core Files**

| File | Purpose |
|------|---------|
| `Main.java` | Entry point of the application, initializes LoginFrame |
| `LoginFrame.java` | Manages login screen and transitions to main app |
| `LoginPanel.java` | Login UI with username/password fields |
| `EnhancedMainFrame.java` | Main application window with 3 tabs and logout button |

### **Verification & Search**

| File | Purpose |
|------|---------|
| `DocumentSearchPanel.java` | Aadhar search with complete OTP → CAPTCHA → Display flow |
| `OTPVerificationPanel.java` | OTP input and verification with 60s timer |
| `CaptchaVerificationPanel.java` | Image-based CAPTCHA with character distortion |
| `AadharDisplayPanel.java` | Shows front and back of verified Aadhar card |
| `AadharCardPanel.java` | Golden Aadhar card visualization with UIDAI format |

### **Business Logic**

| File | Purpose |
|------|---------|
| `AadharAPIClient.java` | API client with format validation and fraud detection |
| `AadharData.java` | Data model for Aadhar information |
| `AadharDataMasker.java` | Masks sensitive data (Aadhar, phone, etc.) |
| `PDFExporter.java` | Generates PDF reports of verified documents |

### **Additional**

| File | Purpose |
|------|---------|
| `AadharDownloadPanel.java` | PDF download and export options |
| `AadharVerificationPanel.java` | Multi-tab verification interface |
| `OTPVerificationDialog.java` | OTP dialog with retry logic |

---

## 🔐 Verification Flow

```
START
  ↓
[Login Screen]
  ├─ Enter: user / pass123
  └─ Click: Login
    ↓
[Main Application]
  ├─ Tab 1: Search Aadhar
  ├─ Tab 2: Download
  └─ Tab 3: Verification
    ↓
[Enter Aadhar Number]
  └─ Click: Search
    ↓
[API Validation]
  ├─ Format Check: 12 digits
  └─ Proceed if valid
    ↓
[OTP Verification]
  ├─ Demo OTP shown
  ├─ Enter OTP
  └─ 60-second timer
    ↓
[CAPTCHA Verification]
  ├─ Read distorted text
  ├─ Enter CAPTCHA
  └─ Refresh if needed
    ↓
[Display Aadhar Card]
  ├─ Front Side: Golden card with details
  ├─ Back Side: QR code, signature, security features
  └─ Download Button
    ↓
[Download PDF]
  └─ Automatic PDF export
    ↓
[Logout]
  └─ Return to Login
END
```

---

## 🔑 Demo Credentials

- **Username**: `user`
- **Password**: `pass123`

---

## 📋 System Requirements

- **Java Runtime Environment (JRE)**: Java 25 LTS or later
- **Operating System**: Windows 10/11, Linux, macOS
- **RAM**: Minimum 512 MB
- **Screen Resolution**: 1024x768 or higher
- **Internet**: Not required (offline application)

---

## 🎨 UI Components

### **Color Scheme**
- Primary Blue: `#1967D2` (Headers, buttons)
- Success Green: `#00C800` (Search button)
- Golden Yellow: `#FFC800` (Aadhar card)
- Light Gray: `#F0F0F5` (Backgrounds)
- Red: `#D23232` (Logout button)

### **Fonts**
- **Headers**: Arial Bold 18-20pt
- **Labels**: Arial Bold 12pt
- **Content**: Arial Regular 11-12pt
- **CAPTCHA**: Arial Bold 40pt

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| "java not found" | Install Java JDK 25 LTS from oracle.com |
| JAR won't run | Double-click Run-AadharKYC.bat or use command line |
| CAPTCHA code not visible | Ensure screen resolution ≥ 1024x768 |
| Login failed | Use credentials: user / pass123 |
| Application freezes | Wait for background API calls to complete |

---

## 📦 Build Information

- **JAR File Size**: ~56 KB
- **Total Classes**: 20+ compiled Java classes
- **Compilation Method**: Direct javac compilation
- **Build Date**: January 2026

---

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2026 Nitish

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 👨‍💻 Developer

**Nitish**

- Aadhar KYC Verification System
- Java Swing Desktop Application
- Full-Stack Development (UI, Logic, Data Management)
- Verification Flow & Security Implementation

---

## 📞 Support

For issues or questions regarding this application:
1. Check the [Troubleshooting](#troubleshooting) section
2. Verify demo credentials: `user` / `pass123`
3. Ensure Java 25 LTS is installed

---

## 🚀 Future Enhancements

- [ ] Connect to real REST API endpoints
- [ ] Database integration for user data
- [ ] Multi-language support
- [ ] Mobile app version (Android/iOS)
- [ ] Cloud-based document storage
- [ ] Advanced fraud detection
- [ ] SMS/Email OTP integration
- [ ] Biometric authentication

---

## 📋 Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0.0 | Jan 2026 | Initial release with OTP, CAPTCHA, and Aadhar display |

---

**Last Updated**: January 6, 2026

**Status**: ✅ Production Ready

---

*Built with ☕ Java and ❤️ by Nitish*
