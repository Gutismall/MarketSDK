# 📦 MarketAPI

MarketAPI is the backend service for the Market platform. Built with **Spring Boot**, it exposes RESTful endpoints to manage marketplace features such as users and listings. The API connects to a **PostgreSQL database** and is designed to be deployed to AWS services including RDS, Elastic Beanstalk.

---

## 🚀 Features

- 🔐 Secure RESTful API with Spring Boot
- 🗃️ PostgreSQL integration (via environment variables)
- ☁️ Cloud deployment-ready (Elastic Beanstalk)
- 📁 JAR packaging with Gradle
- 🧪 Easily testable and extendable architecture

---

## ⚙️ Configuration

The application is configured via **environment variables** injected at runtime (e.g., by Elastic Beanstalk). These are mapped inside `application.properties`.

```properties
# application.properties

spring.datasource.url=jdbc:postgresql://${RDS_HOSTNAME}:${RDS_PORT}/${RDS_DB_NAME}
spring.datasource.username=${RDS_USERNAME}
spring.datasource.password=${RDS_PASSWORD}
server.port=${SERVER_PORT:5000}
```

### Required Environment Variables:

| Variable       | Description                   |
| -------------- | ----------------------------- |
| `RDS_HOSTNAME` | RDS PostgreSQL hostname       |
| `RDS_PORT`     | RDS port (default: `5432`)    |
| `RDS_DB_NAME`  | Name of the database          |
| `RDS_USERNAME` | Database username             |
| `RDS_PASSWORD` | Database password             |
| `SERVER_PORT`  | Server port (default: `5000`) |

---

## 🛠️ Build Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/Gutismall/MarketAPI.git
   cd MarketAPI
   ```

2. Build the JAR:

   ```bash
   ./gradlew bootJar
   ```

3. Output will be in:
   ```
   build/libs/market-api-<version>.jar
   ```

---

## 🧪 Running Locally

Make sure you have PostgreSQL running locally or use Docker to spin up a container.

```bash
export RDS_HOSTNAME=localhost
export RDS_PORT=5432
export RDS_DB_NAME=market
export RDS_USERNAME=postgres
export RDS_PASSWORD=postgres
export SERVER_PORT=5000

./gradlew bootRun
```

---

## ☁️ Deployment

The API is deployed on:

- ✅ **Amazon RDS** – PostgreSQL instance
- ✅ **Elastic Beanstalk** – Running the API JAR

---

## 📚 Related Projects

- [`Market-Android-Library`](https://github.com/Gutismall/Market-Android-Library) — Android SDK that interacts with this API
- [`Market-Dashboard`](https://github.com/Gutismall/Market-Dashboard) — Admin dashboard for managing marketplace data
- [`Market-Demo-Application`](https://github.com/Gutismall/Market-Demo-Appliction) — Example client app using the SDK

---

## 🧑‍💻 Author

**Ari Guterman**  
Student Software Engineer • Passionate about backend & cloud infrastructure  
[GitHub](https://github.com/Gutismall)

---

## 📄 License

This project is licensed under the MIT License.
