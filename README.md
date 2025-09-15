# MSCS-632-A01_ToDoApplication

# 📝 Collaborative To-Do List Application (CLI)

This project is a **command-line collaborative To-Do list application** built with **Node.js**.  
It supports multiple users, task assignment, categories, and demonstrates **concurrency control** using asynchronous JavaScript (`async/await`) with JSON-based persistence.

This was developed as part of an **Advanced Programming Languages** course project.

---

## Features

- Add, remove, and complete tasks
- Assign tasks to different users
- Categorize tasks (e.g., Work, Personal)
- Persistent data storage in `db.json`
- CLI-based interface using **Inquirer.js**

---
## *************************************************************************************************
## ⚙️ Setup Instructions For JavaScript Program

### 1. Clone the Repository

```bash
git clone git@github.com:Kejmerkew/MSCS-632-A01_GroupProject_ToDoApplication.git
cd JavaScript
```
### 2. Install Dependencies

```bash
npm install
```

### 3. Run the Application
```bash
node index.js
```
## *************************************************************************************************
## ⚙️ Setup Instructions For Java Program

### 1. Clone the Repository

```bash
git clone git@github.com:Kejmerkew/MSCS-632-A01_GroupProject_ToDoApplication.git
cd Java
```
### 2. Ensure Java 17+ and Maven are installed:

```bash
java -version
mvn -version
```

### 3. Build the project:
```bash
mvn clean compile
```

### 4. Run the application (CLI):
```bash
mvn exec:java
```

### 5. (To Run Concurrency Demo)
Edit this part of pom.xml from this:
```bash
<configuration>
    <mainClass>com.ucumberlands.todoapp.Main</mainClass>
</configuration>
```
To this:
```bash
<configuration>
    <mainClass>com.ucumberlands.todoapp.ConcurrentDemo</mainClass>
</configuration>
```
Then re-compile and re-run.





