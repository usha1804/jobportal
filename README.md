# CareerBoost Job Board Mini-App

A full-stack Job Board application where users can:
- Browse job listings
- View job details
- Apply to jobs

---

## 🧱 Tech Stack

### 🔧 Backend
- Spring Boot (Java 17+)
- MongoDB (NoSQL database)
- Spring Data MongoDB
- Spring Web (REST APIs)
- Lombok
- Maven

### 🎨 Frontend
- React (TypeScript)
- Axios (API requests)
- React Router DOM (navigation)
- TailwindCSS (or Bootstrap/CSS Modules)
- Responsive Design

---


---

## ⚙️ Backend Setup – Spring Boot + MongoDB

### 🧩 Requirements

- Java 17+
- Maven
- MongoDB running locally on `mongodb://localhost:27017`

### ▶️ Steps to Run

```bash
# 1. Navigate to backend
cd backend/backend

# 2. Build the project
mvn clean install

# 3. Run the application
mvn spring-boot:run


# 1. Navigate to frontend
cd frontend/job-portal-nexus

# 2. Install dependencies
npm install

# 3. Start the dev server
npm run dev

