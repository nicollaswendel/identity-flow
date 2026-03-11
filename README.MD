![Java](https://img.shields.io/badge/Java-21+-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-darkgreen)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)
![Spring Security](https://img.shields.io/badge/Security-SpringSecurity-green)
![JWT](https://img.shields.io/badge/Auth-JWT-black)
![License](https://img.shields.io/badge/License-MIT-red)

# 🔐 IdentityFlow API

REST API for **user management and authentication**, built with **Spring Boot and JWT security**.

This project demonstrates a **complete authentication flow**, including secure password storage, token-based authentication and protected endpoints.

A simple **HTML, CSS and JavaScript frontend** was implemented to interact with the API and demonstrate full-stack integration.

---

# 🚀 Features

- User registration
- Secure login with **JWT authentication**
- Password encryption using **BCrypt**
- Protected API endpoints using **Spring Security**
- Stateless authentication with **JWT tokens**
- Login and logout flow in the frontend
- Authorization header validation via **custom security filter**
- Structured logging using **SLF4J**
- Frontend-backend communication using **Fetch API**

---

# 🛠 Tech Stack

## Backend

- Java 21+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- BCrypt Password Encoder
- SLF4J Logger
- MySQL

## Frontend

- HTML5
- CSS3
- Vanilla JavaScript
- Fetch API
- LocalStorage for token persistence

---

# 📁 Project Structure
```
identity-flow/
│
├── identity-flow-backend/ # Spring Boot REST API
│
└── identity-flow-frontend/ # Static frontend interface
```

---


---

# ▶️ How to Run the Project

## 1️⃣ Backend (Spring Boot)

1. Open `identity-flow-backend` in **IntelliJ IDEA**
2. Configure the **MySQL database connection** in `application.properties`
3. Run the main application class

API will start at:
http://localhost:8080

---

## 2️⃣ Frontend

1. Open `identity-flow-frontend` in **VSCode**
2. Install the **Live Server extension**
3. Right-click `login.html`
4. Click **"Open with Live Server"**

Frontend will run at something like:
http://127.0.0.1:5500

---

# 🔑 Authentication Flow

The API uses **JWT (JSON Web Tokens)** for stateless authentication.

### Login
POST /users/login

Returns a token:
```
json
{
  "token": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

The frontend stores the token in localStorage.

## Authenticated Requests
Protected endpoints require the header:
```
Authorization: Bearer <token>
```
Requests without a valid token will return:
```
401 Unauthorized
```

---

## 📦 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/users` | Register a new user (protected) |
| POST | `/users/login` | Authenticate user and return JWT |
| GET | `/users` | List users (protected endpoint) |
| PUT | `/users/{id}` | Update user (protected) |
| DELETE | `/users/{id}` | Delete user (protected) |

---

## 🔐 Security Implementation

Security was implemented using:
- Spring Security
- JWT Token validation
- BCrypt password encryption
- Custom Security Filter
- Stateless session policy

Authentication is validated on every request by intercepting the `Authorization` header and verifying the token.

---

## 📂 Frontend Integration

The frontend demonstrates how to interact with the API:
- Login page
- User registration form
- Logout functionality
- Token persistence using `localStorage`
- Automatic authorization header injection in requests

---

## 🧠 Architecture Highlights

- Controller → Service → Repository layered architecture
- DTO usage for request abstraction
- Password encryption with BCrypt
- Token generation and validation via JWT
- Custom security filter integrated with Spring Security filter chain
- Stateless authentication model
- Structured logging using SLF4J

---

## 📜 License

MIT License
