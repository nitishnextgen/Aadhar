# Aadhar KYC - Know Your Customer Application

A Java-based desktop application for Aadhar KYC (Know Your Customer) verification and document processing. This application provides an intuitive GUI for Aadhar authentication, OTP verification, document search, and PDF export functionality.

## Features

- **Aadhar Authentication**: Verify and fetch Aadhar card details using API integration
- **OTP Verification**: Multi-step OTP verification process for security
- **Document Search**: Search and retrieve documents by various criteria
- **Aadhar Card Display**: Enhanced display of masked Aadhar information
- **PDF Export**: Export verified Aadhar data to PDF format
- **Login Panel**: Secure login interface with credential validation
- **CAPTCHA Verification**: CAPTCHA-based security verification
- **Data Masking**: Automatic masking of sensitive Aadhar data for privacy

## Project Structure

```
src/
├── main/
│   ├── java/org/example/
│   │   ├── Main.java                      # Application entry point
│   │   ├── EnhancedMainFrame.java         # Main application window
│   │   ├── AadharAPIClient.java           # API client for Aadhar services
│   │   ├── AadharCardPanel.java           # Aadhar card display component
│   │   ├── AadharData.java                # Data model for Aadhar information
│   │   ├── AadharDataMasker.java          # Utility for data masking
│   │   ├── AadharDisplayPanel.java        # Display panel for Aadhar details
│   │   ├── AadharDownloadPanel.java       # Download/export functionality
│   │   ├── AadharVerificationPanel.java   # Verification panel
│   │   ├── CaptchaVerificationPanel.java  # CAPTCHA verification UI
│   │   ├── DocumentSearchPanel.java       # Document search interface
│   │   ├── LoginPanel.java                # Login interface
│   │   ├── OTPVerificationDialog.java     # OTP dialog component
│   │   ├── OTPVerificationPanel.java      # OTP verification interface
│   │   ├── PDFExporter.java               # PDF export utility
│   │   └── Component.java                 # Common component definitions
│   └── resources/                         # Application resources
└── test/
    ├── java/org/example/
    │   ├── AadharAPIClientTest.java       # API client tests
    │   └── AadharDataTest.java            # Data model tests
    └── resources/                         # Test resources
```

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

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For issues, questions, or suggestions:
- Open an issue on [GitHub Issues](https://github.com/thenitishmind/Aadhar_Kyc/issues)
- Contact the maintainer

## Version History

- **v1.0.0** (January 2026): Initial release with core KYC functionality

## Acknowledgments

- Built with Java Swing for GUI
- Gradle for build automation
- Contributors and testers

---

**Note**: This application handles sensitive personal information. Ensure proper security measures and compliance with data protection regulations before deployment.