# Health Insurance Project

## 📚 Project Overview
This project is a mini health insurance backend application. It provides APIs for creating and managing insurance products and policies, processing payments, and automatically activating policies through scheduled jobs.

The system follows best practices like:
- Clean separation into Controller, Service, and Repository layers.
- Use of Spring Data JPA for database operations.
- `@Transactional` used to maintain database consistency during critical operations.
- Swagger UI integration for easy API documentation and testing.
- Scheduled batch jobs (`@Scheduled`) for automated backend tasks.
- Global and custom exception handling for better error management.
- Utility classes for clean and reusable code.
- Strong business logic validations to ensure data consistency and prevent code breakage.

## 🛠️ Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Swagger UI
- Maven
- IntelliJ IDEA / Eclipse

## 🚀 Features
- Create and retrieve insurance products.
- Create, update, and fetch policies linked to products.
- Payment processing and tracking using `CollectionDtl` entity.
- Automated batch processing to activate policies when payment is completed.
- Auto-generated policy numbers.
- Custom exception classes for better error responses.
- Utility methods for common reusable logic.
- `@Transactional` for ensuring database integrity during service-layer operations.
- Retry and timeout handling for batch operations.
![image](https://github.com/user-attachments/assets/c9949b25-d787-4af1-9490-1098e4be6ca8)
