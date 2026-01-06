# Aadhar KYC - Know Your Customer Application

A Java-based desktop application for Aadhar KYC (Know Your Customer) verification and document processing. This application provides an intuitive GUI for Aadhar authentication, OTP verification, document search, and PDF export functionality.

**Developed by**: Nitish  
**Repository**: https://github.com/thenitishmind/Aadhar_Kyc

## Technology Stack & Versions

| Component | Technology | Version |
|-----------|-----------|---------|
| **Programming Language** | Java | JDK 11+ (JavaSE-25 LTS Recommended) |
| **Build Tool** | Gradle | 7.0+ |
| **GUI Framework** | Java Swing | Built-in (JDK) |
| **Testing Framework** | JUnit | 4.12+ |
| **IDE Compatible** | Eclipse, IntelliJ IDEA, VS Code | Latest |
| **Operating System** | Cross-Platform | Windows, macOS, Linux |

### Key Frameworks & Libraries
- **Java Swing**: For desktop GUI components
- **Gradle Build System**: For dependency management and build automation
- **JUnit**: For unit testing
- **REST/HTTP Client**: For API communications (included in JDK)

## Project Structure

```
perfiosandroid/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── example/
│   │               ├── Main.java                      ⭐ APPLICATION ENTRY POINT
│   │               ├── EnhancedMainFrame.java         # Main application window
│   │               ├── AadharAPIClient.java           # API client for Aadhar services
│   │               ├── AadharCardPanel.java           # Aadhar card display component
│   │               ├── AadharData.java                # Data model for Aadhar information
│   │               ├── AadharDataMasker.java          # Utility for data masking
│   │               ├── AadharDisplayPanel.java        # Display panel for Aadhar details
│   │               ├── AadharDownloadPanel.java       # Download/export functionality
│   │               ├── AadharVerificationPanel.java   # Verification panel
│   │               ├── CaptchaVerificationPanel.java  # CAPTCHA verification UI
│   │               ├── DocumentSearchPanel.java       # Document search interface
│   │               ├── LoginPanel.java                # Login interface
│   │               ├── OTPVerificationDialog.java     # OTP dialog component
│   │               ├── OTPVerificationPanel.java      # OTP verification interface
│   │               ├── PDFExporter.java               # PDF export utility
│   │               └── Component.java                 # Common component definitions
│   ├── resources/                                     # Application resources
│   └── (other source files)
├── test/
│   ├── java/
│   │   └── org/
│   │       └── example/
│   │           ├── AadharAPIClientTest.java           # API client tests
│   │           └── AadharDataTest.java                # Data model tests
│   └── resources/
├── build/                                             # Build output directory
│   ├── classes/java/main/                             # Compiled classes
│   ├── libs/                                          # Generated JAR file
│   ├── reports/                                       # Test reports
│   └── generated/                                     # Generated sources
├── gradle/                                            # Gradle wrapper
├── build.gradle.kts                                   # Gradle build configuration
├── settings.gradle.kts                                # Gradle settings
├── gradlew                                            # Gradle wrapper script (Linux/Mac)
├── gradlew.bat                                        # Gradle wrapper script (Windows)
├── local.properties                                   # Local configuration
├── README.md                                          # This file
├── LICENSE                                            # Apache License 2.0
├── MANIFEST.MF                                        # JAR manifest
├── Run-AadharKYC.bat                                  # Quick launch script (Windows)
└── QUICKSTART.txt                                     # Quick start guide
```

## Which File Runs?

### ⭐ **Main Entry Point**: `Main.java`

The application starts from:
```
src/main/java/org/example/Main.java
```

This file contains the `main()` method that initializes the application and launches the GUI.

### How to Run

**Option 1: Using Gradle (Recommended)**
```bash
./gradlew run
```

**Option 2: Using the Batch Script (Windows)**
```bash
Run-AadharKYC.bat
```

**Option 3: From Compiled JAR**
```bash
java -jar build/libs/AadharKYC.jar
```

## Features

- **Aadhar Authentication**: Verify and fetch Aadhar card details using API integration
- **OTP Verification**: Multi-step OTP verification process for security
- **Document Search**: Search and retrieve documents by various criteria
- **Aadhar Card Display**: Enhanced display of masked Aadhar information
- **PDF Export**: Export verified Aadhar data to PDF format
- **Login Panel**: Secure login interface with credential validation
- **CAPTCHA Verification**: CAPTCHA-based security verification
- **Data Masking**: Automatic masking of sensitive Aadhar data for privacy

## Prerequisites

- **Java**: JDK 11 or higher
- **Gradle**: 7.0 or higher
- **Operating System**: Windows, macOS, or Linux

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/thenitishmind/Aadhar_Kyc.git
cd perfiosandroid
```

### 2. Build the Project

Using Gradle:

```bash
# On Windows
./gradlew.bat build

# On macOS/Linux
./gradlew build
```

Or use the provided batch file:

```bash
Run-AadharKYC.bat
```

### 3. Run the Application

```bash
./gradlew run
```

Or execute directly from the built JAR file:

```bash
java -jar build/libs/AadharKYC.jar
```

## Configuration

### Local Properties

Edit `local.properties` to configure:

```properties
# API Endpoints
api.endpoint=https://your-api-endpoint.com
api.key=your-api-key

# Application Settings
app.timeout=30000
```

## Usage

1. **Launch Application**: Run the application using the methods described above
2. **Login**: Enter your credentials in the login panel
3. **Verify Aadhar**: Enter Aadhar number and proceed with verification
4. **OTP Entry**: Complete OTP verification
5. **CAPTCHA**: Complete CAPTCHA verification
6. **View Details**: Review masked Aadhar information
7. **Export**: Download verified details as PDF

## API Integration

The application uses `AadharAPIClient.java` to communicate with backend services:

- Aadhar verification
- OTP generation and validation
- CAPTCHA verification
- Document retrieval

## Testing

Run the test suite:

```bash
./gradlew test
```

Tests include:
- `AadharAPIClientTest`: API client functionality
- `AadharDataTest`: Data model validation

## Build Outputs

- **JAR**: `build/libs/AadharKYC.jar`
- **Classes**: `build/classes/java/main/`
- **Test Reports**: `build/reports/`

## Security Features

- **Data Masking**: Sensitive Aadhar digits are masked (e.g., XXXX-XXXX-1234)
- **OTP Verification**: Two-factor verification for enhanced security
- **CAPTCHA**: Bot prevention through CAPTCHA challenges
- **HTTPS**: Secure API communication

## Troubleshooting

### Build Issues

```bash
# Clean build
./gradlew clean build

# Debug mode
./gradlew build --info
```

### Runtime Issues

- Check Java version: `java -version`
- Verify Gradle installation: `gradle -v`
- Check local.properties configuration
- Review application logs in `build/` directory

## Dependencies

Key dependencies are configured in `build.gradle.kts`:
- Java Standard Edition libraries
- Gradle Build Tool
- JUnit (for testing)

See `build.gradle.kts` for complete dependency list.

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -am 'Add new feature'`
4. Push to branch: `git push origin feature/your-feature`
5. Submit a pull request

## License

This project is licensed under the **Apache License 2.0**. See the [LICENSE](LICENSE) file for complete details.

### License Summary
- ✅ You may use this software commercially
- ✅ You may distribute this software
- ✅ You may modify this software
- ✅ You may use this software privately
- ⚠️ You must include a copy of the license
- ⚠️ You must state significant changes to the code
- ✋ This software is provided AS-IS with no warranty

Full Apache License 2.0 text is available in the [LICENSE](LICENSE) file included in this repository.

## Support

For issues, questions, or suggestions:
- Open an issue on [GitHub Issues](https://github.com/thenitishmind/Aadhar_Kyc/issues)
- Contact the maintainer

## Author

**Nitish** - Project Developer and Maintainer

## Version History

- **v1.0.0** (January 2026): Initial release with core KYC functionality

## Acknowledgments

- Built with Java Swing for GUI
- Gradle for build automation
- Contributors and testers

---

**Note**: This application handles sensitive personal information. Ensure proper security measures and compliance with data protection regulations before deployment.

**License**: Apache License 2.0 - See LICENSE file for full text