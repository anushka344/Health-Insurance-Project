 Health Insurance Project

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

---

## 🔁 Idempotency Key Implementation (Design Doc)

### 🧩 Purpose
To prevent duplicate resource creation in `createQuote` API when a client retries the same request (e.g., due to timeout), we implemented **idempotency using Redis**.

---

### ✅ 1. Idempotency Flow Summary
- Client sends an `Idempotency-Key` header with POST request.
- Request body is hashed (using SHA-256).
- Redis stores: `key → requestHash → responseBody`
- On duplicate request:
  - Same key & body → return cached response.
  - Same key & different body → 409 Conflict.
  - New key → process request & store result.

---

### 🧱 Components

#### 🔹 `HashUtil`
- Utility for hashing request bodies via SHA-256.

#### 🔹 `IdempotencyService`
- Manages Redis logic.
- Stores & retrieves key-hash-response mappings.
- TTL = 15 minutes.
- Uses `StringRedisTemplate`.

#### 🔹 `PolicyController`
- Validates key and hashes the request.
- Uses `IdempotencyService` to:
  - Check cache.
  - Handle conflicts.
  - Save new response.

#### 🔹 `PolicyService`
- Main business logic to create a policy.
- Called only if it's a first-time request.

---

### 🧠 Key Decisions

| Concern              | Implementation                                 |
|----------------------|-------------------------------------------------|
| Cache Storage        | Redis (StringRedisTemplate)                    |
| Expiry Time          | 15 minutes TTL                                 |
| Comparison Logic     | SHA-256 hash of request body                   |
| Failure Handling     | Graceful fallback on Redis failure             |
| Conflict Detection   | If same key used for different request bodies  |

---

### ✅ Benefits
- Prevents duplicate policy creation.
- Supports retry-safe APIs.
