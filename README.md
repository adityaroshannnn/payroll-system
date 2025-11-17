# Payroll System (JSP/Servlet + MySQL)

Minimal payroll app with Admin and Employee roles.

## Features
- Admin: login, view employees, add employee (+ optional login), remove employee, create payslips
- Employee: login, mark attendance (Present/Absent for today), view payslips
- Secure password hashing (SHA-256)
- Simple JDBC DAO layer
- Maven build, WAR deployable to Apache Tomcat 9/10

## Quick Start

### 1) Requirements
- JDK 8+
- Maven 3+
- MySQL 8+
- Apache Tomcat 9 or 10

### 2) Database
- Edit `src/main/resources/db.properties` if needed.
- Run the SQL: `schema.sql` in MySQL Workbench or CLI:

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS payroll_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE payroll_db;

-- Users table (admin + employee login accounts)
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  role ENUM('ADMIN','EMPLOYEE') NOT NULL
);

-- Employees table
CREATE TABLE IF NOT EXISTS employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  salary DECIMAL(10,2) NOT NULL,
  join_date DATE NOT NULL,
  user_id INT UNIQUE,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Attendance table
CREATE TABLE IF NOT EXISTS attendance (
  id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT NOT NULL,
  att_date DATE NOT NULL,
  status ENUM('PRESENT','ABSENT') NOT NULL,
  UNIQUE KEY uniq_att (employee_id, att_date),
  FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- Payslips table
CREATE TABLE IF NOT EXISTS payslips (
  id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT NOT NULL,
  month INT NOT NULL,       -- 1..12
  year INT NOT NULL,
  basic DECIMAL(10,2) NOT NULL,
  hra DECIMAL(10,2) NOT NULL,
  deductions DECIMAL(10,2) NOT NULL,
  net DECIMAL(10,2) NOT NULL,
  generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uniq_emp_month (employee_id, month, year),
  FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- Seed admin user: username 'admin', password 'admin123'
INSERT INTO users (username, password_hash, role)
VALUES ('admin', SHA2('admin123', 256), 'ADMIN')
ON DUPLICATE KEY UPDATE username=username;


```

This creates database `payroll_db` and seed admin:
- **username**: `admin`
- **password**: `admin123`

### 3) Build & Deploy
```bash
mvn clean package
# result: target/payroll-system.war
# Deploy to Tomcat webapps/ (copy the WAR) or use Tomcat Manager
```

### 4) URLs
- Login: `http://localhost:8080/payroll-system/login`
- Admin dashboard (after login as admin)
- Employee dashboard (after login as employee)

### Notes
- To create an employee login, use Add Employee form with username/password fields.
- Payslips are generated as DB records and listed for employees; export/print to PDF via browser if needed.
- Attendance can be marked once per day; re-submitting will overwrite status for that day.

### Troubleshooting
- If you see DB connection errors, verify MySQL credentials and that the DB exists.
- On Windows, ensure MySQL service is running.
- For Tomcat 10 (Jakarta namespace), this app targets Servlet 4.0 (javax.*). Use Tomcat 9.x or enable compatibility mode; or adjust imports to `jakarta.servlet.*` and set servlet-api accordingly.
