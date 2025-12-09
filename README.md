# 🎬 Movie Booking System

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-5.7+-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A comprehensive web-based movie ticket booking and management system built with Java, Jakarta Servlets, and MySQL. This application provides a complete solution for cinema management, allowing users to browse movies, book tickets, make payments, and manage bookings.

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Database Setup](#-database-setup)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Deployment](#-deployment)
- [Testing](#-testing)
- [Troubleshooting](#-troubleshooting)
- [Contributing](#-contributing)
- [License](#-license)
- [Authors](#-authors)

## ✨ Features

### User Features

- 🔐 **User Authentication & Authorization**

  - Email-based registration with verification
  - Secure login system
  - Google OAuth integration
  - Password reset functionality
  - Role-based access control (Client/Admin)

- 🎥 **Movie Browsing**

  - Browse all available movies
  - View movie details (description, cast, release date)
  - Filter movies by status (Now Showing, Coming Soon)
  - Search functionality

- 🎫 **Booking System**

  - Select movie, date, and showtime
  - Choose seats
  - View booking history
  - Cancel bookings

- 💳 **Payment Processing**

  - Secure payment integration with Stripe
  - Multiple payment methods
  - Payment confirmation

- ⭐ **Reviews & Ratings**
  - Submit movie reviews
  - View reviews from other users
  - Rating system

### Admin Features

- 🎬 **Movie Management**

  - Add, edit, and delete movies
  - Manage movie schedules (dates and showtimes)
  - Update movie status
  - Upload movie posters

- 👥 **User Management**

  - View all registered users
  - Manage user accounts
  - User role management

- 📊 **Booking Management**

  - View all bookings
  - Manage booking status
  - Generate booking reports

- 💬 **Review Management**
  - Moderate user reviews
  - Approve/reject reviews

## 🛠 Technology Stack

### Backend

- **Java 11** - Programming language
- **Jakarta Servlet API 5.0** - Web framework
- **Maven 3.6+** - Build and dependency management
- **MySQL 9.1.0** - Database
- **Jakarta Mail 2.0.1** - Email services
- **Stripe Java SDK 28.1.0** - Payment processing

### Frontend

- **JSP (JavaServer Pages)** - Server-side rendering
- **JSTL 2.0** - JSP Standard Tag Library
- **Tailwind CSS** - Styling framework
- **JavaScript** - Client-side scripting

### Tools & Libraries

- **JUnit 3.8.1** - Unit testing
- **Dotenv Java 2.3.1** - Environment variable management

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 11 or higher**

  ```bash
  java -version
  ```

- **Apache Maven 3.6 or higher**

  ```bash
  mvn -version
  ```

- **MySQL 5.7+ or MariaDB 10.3+**

  ```bash
  mysql --version
  ```

- **Application Server** (choose one):

  - Apache Tomcat 9.0+ or higher
  - Jetty 11.0+ or higher
  - Any Jakarta EE compatible server

- **Git** (for cloning the repository)
  ```bash
  git --version
  ```

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/movie-booking-system.git
cd movie-booking-system/java-project
```

### 2. Database Setup

1. Create a MySQL database:

   ```sql
   CREATE DATABASE cinema;
   ```

2. Import the database schema:

   ```bash
   mysql -u root -p cinema < ../Database/db.sql
   ```

   Or use the SQL file located in the `Database/` directory.

### 3. Configure Database Connection

Edit `src/main/resources/config/db.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/cinema?useSSL=false&serverTimezone=UTC
db.username=your_username
db.password=your_password
db.driver=com.mysql.cj.jdbc.Driver
```

### 4. Configure Email Settings (Optional)

If you want email verification to work, configure your email settings in the application configuration.

### 5. Configure Stripe (Optional)

For payment processing, set up your Stripe API keys in the application configuration or environment variables.

### 6. Build the Project

```bash
mvn clean install
```

This will:

- Download all dependencies
- Compile the source code
- Run tests (if any)
- Package the application as a WAR file

The WAR file will be generated in the `target/` directory as `Java_Project.war`.

## ⚙️ Configuration

### Database Configuration

Edit `src/main/resources/config/db.properties`:

```properties
# Database connection URL
db.url=jdbc:mysql://localhost:3306/cinema?useSSL=false&serverTimezone=UTC

# Database credentials
db.username=root
db.password=your_password

# JDBC Driver
db.driver=com.mysql.cj.jdbc.Driver
```

### Application Configuration

Key configuration files:

- `src/main/resources/config/db.properties` - Database settings
- `src/main/webapp/WEB-INF/web.xml` - Web application deployment descriptor

### Environment Variables

You can use environment variables for sensitive configuration:

- Database credentials
- Stripe API keys
- Email server settings

## 🗄 Database Setup

### Schema Overview

The database includes the following main tables:

- **users** - User accounts and authentication
- **movies** - Movie information
- **bookings** - Ticket bookings
- **reviews** - User reviews and ratings
- **verificationcode** - Email verification codes
- **showdates** - Available show dates
- **showtimes** - Available show times
- **seats** - Seat availability

### Initial Data

The database schema includes sample data. You can customize it according to your needs.

## 💻 Usage

### Running Locally

1. **Start MySQL Server**

   ```bash
   # On macOS/Linux
   sudo systemctl start mysql

   # On Windows
   net start MySQL
   ```

2. **Deploy to Application Server**

   **Option A: Using Tomcat**

   ```bash
   # Copy WAR file to Tomcat webapps directory
   cp target/Java_Project.war $CATALINA_HOME/webapps/

   # Start Tomcat
   $CATALINA_HOME/bin/startup.sh  # Linux/Mac
   $CATALINA_HOME/bin/startup.bat # Windows
   ```

   **Option B: Using IDE**

   - Import project into IntelliJ IDEA or Eclipse
   - Configure application server
   - Run/debug from IDE

3. **Access the Application**
   - Open your browser
   - Navigate to: `http://localhost:8080/Java_Project/`
   - Default admin credentials (if configured):
     - Email: `admin@gmail.com`
     - Password: `123`

### Default User Roles

- **Admin**: Full access to all features
- **Client**: Can browse movies, book tickets, and write reviews

## 📁 Project Structure

```
java-project/
├── pom.xml                          # Maven configuration
├── README.md                         # Project documentation
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/movieproject/
│   │   │       ├── config/          # Configuration classes
│   │   │       ├── constant/        # Application constants
│   │   │       ├── controller/      # Servlet controllers (MVC)
│   │   │       │   ├── admin/       # Admin controllers
│   │   │       │   ├── auth/        # Authentication controllers
│   │   │       │   ├── client/      # Client-facing controllers
│   │   │       │   └── payment/     # Payment controllers
│   │   │       ├── exception/       # Custom exceptions
│   │   │       ├── model/           # Domain entities/POJOs
│   │   │       ├── service/         # Business logic layer
│   │   │       └── utils/           # Utility classes
│   │   ├── resources/
│   │   │   └── config/
│   │   │       └── db.properties   # Database configuration
│   │   └── webapp/
│   │       ├── components/          # Reusable JSP components
│   │       ├── views/               # JSP view pages
│   │       │   ├── admin/          # Admin views
│   │       │   ├── auth/           # Authentication views
│   │       │   └── client/         # Client views
│   │       ├── WEB-INF/
│   │       │   ├── lib/            # Runtime dependencies (Maven managed)
│   │       │   └── web.xml         # Deployment descriptor
│   │       ├── index.jsp           # Application entry point
│   │       └── error.jsp           # Error page
│   └── test/                        # Test source files
│       ├── java/                   # Test classes
│       └── resources/              # Test resources
└── target/                          # Build output (generated)
```

## 🔌 API Endpoints

### Authentication

- `GET/POST /auth/register` - User registration
- `GET/POST /auth/login` - User login
- `GET /login` - Google OAuth login
- `GET /logout` - User logout
- `GET/POST /auth/forgot-password` - Password reset request
- `GET/POST /auth/verify-email-password` - Verify email for password reset
- `GET/POST /auth/password-verify-code` - Verify reset code
- `POST /auth/reset-password` - Reset password
- `GET/POST /auth/verify-email` - Email verification
- `GET/POST /auth/verify-code` - Verify email code
- `POST /send-verification-email` - Send verification email

### Movies

- `GET /home` - Home page with movies
- `GET /movies` - List all movies
- `GET /movie-details` - Movie details page
- `GET /about-us` - About us page

### Bookings

- `GET /booking` - Booking page
- `POST /booking` - Create booking
- `GET /user-bookings` - User's booking history

### Reviews

- `GET/POST /add-review` - Add movie review

### Payment

- `POST /pay-now` - Payment processing
- `POST /create-checkout-session` - Create Stripe checkout session
- `POST /stripe-webhook` - Stripe webhook handler

### Admin

- `GET /admin/home` or `/admin/dashboard` - Admin dashboard
- `GET/POST /admin/movies` - Movie management
- `GET/POST /admin/user-management` - User management
- `GET/POST /admin/bookings` - Booking management
- `GET/POST /admin/manage-reviews` - Review management
- `GET/POST /admin/admin-management` - Admin account management

### Utility

- `GET /test-database` - Database connection test (development only)

## 🚢 Deployment

### Production Deployment

1. **Build for Production**

   ```bash
   mvn clean package -Pproduction
   ```

2. **Configure Production Database**

   - Update `db.properties` with production database credentials
   - Ensure database is accessible from application server

3. **Deploy WAR File**

   - Copy WAR file to application server's webapps directory
   - Configure server settings (port, context path, etc.)

4. **Environment Variables**
   - Set production environment variables
   - Configure Stripe production keys
   - Set up email server for production

### Docker Deployment (Optional)

You can containerize the application using Docker. Create a `Dockerfile`:

```dockerfile
FROM tomcat:9-jdk11
COPY target/Java_Project.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
```

## 🧪 Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=BookingServiceTest

# Generate test coverage report
mvn test jacoco:report
```

### Manual Testing

1. Test user registration and login
2. Test movie browsing and booking
3. Test payment processing
4. Test admin functionalities
5. Test email verification

## 🐛 Troubleshooting

### Common Issues

**Issue: Database Connection Failed**

- Verify MySQL server is running
- Check database credentials in `db.properties`
- Ensure database exists: `CREATE DATABASE cinema;`
- Check firewall settings

**Issue: WAR File Not Deploying**

- Verify application server is running
- Check server logs for errors
- Ensure Java version compatibility (Java 11+)
- Verify `web.xml` configuration

**Issue: Dependencies Not Found**

- Run `mvn clean install` to download dependencies
- Check internet connection
- Verify Maven settings (`~/.m2/settings.xml`)

**Issue: Payment Not Working**

- Verify Stripe API keys are configured
- Check Stripe webhook endpoint configuration
- Review server logs for payment errors

**Issue: Email Not Sending**

- Verify email server configuration
- Check SMTP settings
- Review email service logs

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes**
4. **Commit your changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```
5. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```
6. **Open a Pull Request**

### Coding Standards

- Follow Java naming conventions
- Write meaningful commit messages
- Add comments for complex logic
- Write unit tests for new features
- Update documentation as needed

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Development Team** - Software Engineering 2

## 🙏 Acknowledgments

- Jakarta EE community
- Stripe for payment processing
- Tailwind CSS for styling framework
- All contributors and users

## 📞 Support

For support, email your-email@example.com or open an issue in the repository.

---

**Made with ❤️ using Java and Jakarta Servlets**
