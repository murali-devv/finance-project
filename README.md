<<<<<<< HEAD
# 💰 Finance Tracker Backend API

## 📌 Project Overview

Finance Tracker Backend is a secure and scalable REST API built using Spring Boot. The application helps users manage their personal financial records such as income and expenses while providing analytics and secure JWT-based authentication.

This project was built with an industry-level backend architecture using:
- Layered architecture
- DTO pattern
- JWT Authentication
- Global Exception Handling
- Swagger API Documentation
- Docker containerization

---

# 🚀 Features

## 🔐 Authentication & Security
- User Registration
- User Login
- JWT Authentication
- Protected APIs
- Stateless Authentication
- Password Encryption using BCrypt

---

## 💰 Financial Record Management
- Add Financial Records
- Update Financial Records
- Delete Financial Records
- Fetch All Records
- Filter Records by Type

---

## 📊 Analytics APIs
- Total Income
- Total Expense
- Current Balance
- Monthly Analytics
- Expense Summary

---

## 🧠 Additional Features
- Swagger API Documentation
- Global Exception Handling
- Validation Handling
- Logging using SLF4J
- Docker Support
- Cloud Deployment Ready

---

# 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Stateless Security |
| Spring Data JPA | Database Operations |
| MySQL | Database |
| Maven | Dependency Management |
| Swagger / OpenAPI | API Documentation |
| Docker | Containerization |
| Git & GitHub | Version Control |
| Postman | API Testing |

---

# 🧱 Project Architecture

```text
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
MySQL Database
```

---

# 🔐 Authentication Flow

```text
User Login
    ↓
JWT Token Generated
    ↓
Client Sends Token in Header
    ↓
JWT Filter Validates Token
    ↓
Access Protected APIs
```

---

# 📂 API Endpoints

## 🔐 Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/auth/register | Register User |
| POST | /api/auth/login | Login User |

---

## 💰 Financial Record APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/records | Create Record |
| GET | /api/records | Get All Records |
| GET | /api/records/{id} | Get Record By ID |
| PUT | /api/records/{id} | Update Record |
| DELETE | /api/records/{id} | Delete Record |

---

## 📊 Analytics APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/analytics/summary | Financial Summary |
| GET | /api/analytics/monthly | Monthly Analytics |
| GET | /api/analytics/income | Total Income |
| GET | /api/analytics/expense | Total Expense |

---

# 📘 Swagger Documentation

After running the application:

```text
http://localhost:8081/swagger-ui/index.html
```

---

# 🐳 Docker Setup

## 📦 Build Project

```bash
mvn clean package
```

---

## 🐳 Build Docker Image

```bash
docker build -t finance-app .
```

---

## 🚀 Run Docker Container

```bash
docker run -p 8080:8080 finance-app
```

---

## 🐬 Run Using Docker Compose

```bash
docker-compose up --build
```

---

# ⚙️ Environment Variables

```env
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
JWT_SECRET=
```

---

# 🧪 Testing

APIs were tested using:
- Postman
- Swagger UI

Tested Scenarios:
- Authentication
- JWT Validation
- CRUD Operations
- Analytics APIs
- Validation Errors
- Unauthorized Access

---

# ☁️ Deployment

The application is Dockerized and deployment-ready.

Suggested Platforms:
- Render
- Railway
- AWS
- Azure

---

# 📸 Suggested Screenshots for GitHub

Add screenshots of:
- Swagger UI
- Login API Response
- Financial Record APIs
- Analytics APIs
- Docker Running Containers

---

# 🧠 Key Learnings

- Spring Boot REST API Development
- JWT Authentication
- Layered Architecture
- Docker Containerization
- Exception Handling
- Secure API Development
- Cloud Deployment Basics

---

# 👨‍💻 Author

Developed as an industry-level backend project for learning scalable backend architecture using Spring Boot.

---

# ⭐ Future Improvements

- Refresh Tokens
- Email Notifications
- Microservices Architecture
- Kubernetes Deployment
- CI/CD Pipeline

=======
# finance-project
>>>>>>> db967e208e069dbedf25bdbc09964aaa97aad0e9
