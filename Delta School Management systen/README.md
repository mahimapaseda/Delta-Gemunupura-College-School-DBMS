# Delta Gemunupura College DBMS

A comprehensive Android Database Management System for Delta Gemunupura College, built with modern Android development practices and Firebase backend integration.

## 🏫 About

Delta Gemunupura College DBMS is a feature-rich Android application designed to streamline school management operations. It provides a complete solution for managing staff, students, assets, examinations, timetables, and academic results.

## ✨ Key Features

### 🔐 **Multi-Layer Security**
- Firebase Authentication
- 4-digit PIN system
- Biometric authentication (Fingerprint/Face)
- Role-based access control

### 👥 **Staff Management**
- Complete staff records with personal and employment details
- Academic and non-academic staff classification
- Photo management and profile updates
- Access level controls

### 🎓 **Student Management**
- Comprehensive student records (Grades 6-13)
- Parent/guardian information
- Academic tracking and performance monitoring
- Grade promotion/demotion system

### 📦 **Asset Management**
- Complete inventory tracking
- Asset categorization and status monitoring
- Maintenance tracking
- Professional PDF reports

### 📝 **Exam Management**
- O/L and A/L results management
- Term test tracking for all grade levels
- Performance analytics
- Bulk and individual result exports

### ⏰ **Timetable Management**
- Class scheduling and teacher assignments
- Room and resource allocation
- PDF timetable generation
- Teacher attendance tracking

### 📊 **Reporting & Analytics**
- Professional PDF report generation
- Excel import/export capabilities
- Comprehensive data analytics
- Custom report formatting

## 🛠️ Technology Stack

- **Language**: Kotlin 1.9.22
- **Platform**: Android (API 26-34)
- **Backend**: Firebase (Firestore + Realtime Database)
- **UI**: Material Design Components
- **Architecture**: MVVM with Repository pattern
- **Security**: Multi-layer authentication system

## 📱 Screenshots

*Screenshots would be added here showing the main features*

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 26+
- Firebase project setup
- Google Services configuration

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/delta-gemunupura-dbms.git
   cd delta-gemunupura-dbms
   ```

2. **Firebase Setup**
   - Create a Firebase project
   - Enable Authentication, Firestore, and Realtime Database
   - Download `google-services.json` and place it in the `app/` directory

3. **Build and Run**
   ```bash
   ./gradlew assembleDebug
   ```

## 🏗️ Project Structure

```
app/src/main/java/com/deltagemunupuramv/dbms/
├── adapter/          # RecyclerView adapters
├── fragment/         # UI fragments
├── manager/          # Business logic managers
├── model/           # Data models
├── repository/      # Data repositories
├── util/            # Utility classes
└── Activities       # UI activities
```

## 🔧 Configuration

### Firebase Configuration
The app requires Firebase configuration for:
- Authentication
- Firestore Database
- Realtime Database
- Analytics

### Permissions
- Internet access
- Camera (for profile photos)
- External storage (for file exports)
- Biometric authentication

## 👥 User Roles

- **Principal**: Full system access
- **Data Officer**: Full data management access
- **Technical Officer**: System administration access
- **Academic Staff**: Limited access to relevant modules
- **Non-Academic Staff**: Basic access to assigned modules

## 📋 Features by Module

### Staff Management
- Add/Edit/View staff records
- Photo upload and management
- Employment history tracking
- Qualification management

### Student Management
- Complete student profiles
- Academic progress tracking
- Parent/guardian information
- Grade management system

### Asset Management
- Inventory tracking
- Asset categorization
- Status monitoring
- Maintenance records

### Exam Management
- O/L and A/L results
- Term test management
- Performance analytics
- Report generation

### Timetable Management
- Class scheduling
- Teacher assignments
- Room allocation
- Attendance tracking

## 🔒 Security Features

- **Multi-factor Authentication**: Firebase Auth + PIN + Biometric
- **Role-based Access Control**: Granular permission system
- **Session Management**: Secure session handling
- **Data Encryption**: Firebase security rules
- **Offline Security**: Local data protection

## 📊 Export Capabilities

- **PDF Reports**: Professional school-branded documents
- **Excel Export**: Data import/export functionality
- **File Sharing**: Secure file provider integration
- **Custom Formatting**: School-specific document templates

## 🧪 Testing

The project includes comprehensive testing:
- Unit tests for business logic
- Instrumented tests for Android components
- UI tests with Espresso
- Firebase integration tests

## 📈 Performance

- **Offline Support**: Firebase offline persistence
- **Image Optimization**: Glide for efficient loading
- **Memory Management**: Proper resource cleanup
- **Background Processing**: Coroutines for async operations

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions:
- Email: support@deltagemunupura.edu.lk
- School: Delta Gemunupura College
- Location: Sri Lanka

## 🙏 Acknowledgments

- Firebase team for excellent backend services
- Material Design team for UI components
- Android development community
- Delta Gemunupura College administration

---

**Delta Gemunupura College DBMS** - Streamlining education management with modern technology.
