# 📲 notification-wise-backend

### 🌟 **Overview**

The **Notification Service** is responsible for sending **messages** to patients, using **notification templates**.
It can send appointment reminders, cancellation notifications, and other types of communication. Notifications can be sent through different channels, such as **WhatsApp** and **email**. The service also records **sending logs** to control and monitor notification attempts.

This service aims to ensure that patients receive important information efficiently and without fail, providing **adequate follow-up** and ensuring that communications are recorded and can be audited later.

## 🗂️ Project Structure

### **1. Core Modules**
- **`domain`**: Contains the core business logic and domain models.
- **`application`**: Includes service classes and facades that orchestrate business operations.
- **`gateway`**: Gateways for external systems like RabbitMQ and MongoDB.
- **`infrastructure`**: Handles external integrations, configurations, and persistence layers.
    - **`controller`**: REST and GraphQL controllers for handling API requests.

    - **`configuration`**: Spring Boot configuration classes.
- **`tests`**: Unit and integration tests for all modules.

---

### **2. Key Components**

#### **Domain Layer**
- **`NotificationTemplates`**: Represents notification templates with placeholders for dynamic content.
- **`StreamMessage`**: Represents messages sent to external systems like RabbitMQ.

#### **Application Layer**
- **`NotificationFacade`**: Provides a unified interface for notification operations.
- **`NotificationTemplatesFacade`**: Manages operations related to notification templates.

#### **Infrastructure Layer**
- **Controllers**:
    - **`NotificationController`**: Handles notification-related API requests.
    - **`NotificationTemplatesController`**: Manages notification template operations.
- **Gateways**:
    - **`SendSmsRabbitMQGateway`**: Sends SMS messages via RabbitMQ.
    - **`NotificationTemplatesJpaGateway`**: Manages database operations for notification templates.
- **Configurations**:
    - **`MongoConfig`**: Configures MongoDB connections.
    - **`RabbitMQConfig`**: Configures RabbitMQ messaging.

---

## 🛠️ Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building microservices.
- **Maven**: Dependency management and build tool.
- **MongoDB**: Database for storing notification templates and logs.
- **RabbitMQ**: Message broker for asynchronous communication.
- **JUnit 5**: Testing framework.
- **Instancio**: Library for generating test data.

---

## 🧪 Testing

The project includes comprehensive unit and integration tests for all modules. Key testing practices:
- **Instancio**: Used for generating dynamic test data.
- **Mocking**: Mockito is used to mock dependencies in unit tests.
- **Coverage**: Tests cover all methods and scenarios, ensuring reliability.

---

## 📂 Directory Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── br.com.wise.notification/
│   │   │   ├── domain/
│   │   │   ├── application/
│   │   │   ├── gateway/
│   │   │   ├── infrastructure/
│   │   │   │   ├── controller/
│   │   │   │   ├── configuration/
│   │   │   └── ...
│   └── resources/
│       ├── application.yml
│       └── ...
├── test/
│   ├── java/
│   │   ├── br.com.wise.notification/
│   │   │   ├── domain/
│   │   │   ├── application/
│   │   │   ├── infrastructure/
│   │   │   │   ├── controller/
│   │   │   │   ├── gateway/
│   │   │   │   ├── configuration/
│   │   │   └── ...
│   └── resources/
│       └── ...
  ```

---
## 🗃️ Database Schema
![Notification Service Database Schema](https://github.com/user-attachments/assets/dca704f6-75cb-4dda-a4b5-dbf7037f07ef)

---

## 🚀 How to Run
- **1. Clone the repository**  
  Clone the repository to your development environment:
    ```bash
    git clone https://github.com/Postech-Code-Wizards/food-wise-backend.git
    ```
  In the terminal, navigate to the root of your project:
    ```bash
    cd notification-wise-backend
    ```
  Run the project:
    ```bash
    docker-compose up -d --build
    ```
  Build the project:
    ```bash
    mvn clean install
    ```
  
  Run the application:
    ```bash
    mvn spring-boot:run
    ```
  Access the API:
    - The application will be available at the URL: http://localhost:8084
    - GraphQL Playground: http://localhost:8084/graphiql?path=/graphql

---

##  Good Practices in Modeling and Design

### **1. Use of Flexible Templates**
- **Dynamic templates** are used to allow customizations in messages. Using placeholders such as `{patient_name}` and `{date}`, the system can generate personalized and efficient notifications.
- This approach allows the creation of different types of messages (appointment reminders, cancellation, among others), without the need to create new code every time a new notification is needed.

### **2. Logging**
- The **`notification_logs`** table ensures that **all attempts to send** notifications are recorded. This is **essential for auditing**, in addition to allowing errors to be **monitored** and corrected.
- Storing the **status** of the attempt and the **error message** allows you to quickly identify **sending failures** and take corrective actions.

### **3. Status Control**
- The **`notifications`** table uses a **status** field to indicate whether the notification has been **sent**, **pending**, or **failed**. This ensures that the system has full control over the state of each notification.
- The status helps **manage the lifecycle of notifications**, allowing **resend attempts** to be made if the notification fails, and helping with **delivery monitoring**.

