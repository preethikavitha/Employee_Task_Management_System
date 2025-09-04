# Employee Task Management System

## Project Overview

The **Employee Task Management System** is a Java console-based application designed to manage employees, tasks, and role-based quizzes. Admins can manage employees, assign tasks, and add quiz questions. Employees can view assigned tasks, attempt quizzes, and track their task completion status.

---

## Features

### Admin

* Add and manage employees.
* Assign tasks to employees.
* View all employees with task status (completed/pending).
* Add and view role-specific quiz questions.

### Employee

* View assigned tasks with real-time status.
* Attempt quizzes based on role.
* Tasks are automatically updated upon quiz completion.

---

## Technology Stack

* **Programming Language:** Java
* **Database:** MySQL
* **Connectivity:** JDBC
* **Libraries:** mysql-connector-java (JDBC Driver)

---

## Database Schema

### `users`

| Field    | Type         | Key | Description       |
| -------- | ------------ | --- | ----------------- |
| id       | int          | PK  | Auto-increment ID |
| name     | varchar(50)  |     | Employee name     |
| role     | varchar(20)  |     | Admin/Employee    |
| address  | varchar(255) |     | Address           |
| phone    | varchar(20)  |     | Phone number      |
| email    | varchar(50)  |     | Email             |
| password | varchar(50)  |     | Password          |

### `tasks`

| Field        | Type         | Description                  |
| ------------ | ------------ | ---------------------------- |
| id           | int          | Auto-increment ID            |
| employee\_id | int          | Foreign key to `users`       |
| description  | varchar(255) | Task description             |
| completed    | tinyint(1)   | Task completion status (0/1) |
| score        | int          | Task score                   |

### `quiz_questions`

| Field           | Type         | Description                             |
| --------------- | ------------ | --------------------------------------- |
| id              | int          | Auto-increment ID                       |
| role            | varchar(20)  | Role for which the question is intended |
| question        | varchar(255) | Quiz question text                      |
| option1         | varchar(100) | Option 1                                |
| option2         | varchar(100) | Option 2                                |
| option3         | varchar(100) | Option 3                                |
| correct\_answer | varchar(100) | Correct answer                          |

---

## Setup & Run

1. **Import Database**
   Import `employee_db.sql` into MySQL.

2. **Configure DB Connection**
   Update `DBConnection.java` with your MySQL username and password.

3. **Compile Java Files**

   ```bash
   javac -cp .;lib/mysql-connector-j-9.3.0.jar *.java
   ```

4. **Run Application**

   ```bash
   java -cp .;lib/mysql-connector-j-9.3.0.jar Main
   ```

---

## Usage

1. Start the application and log in as Admin or Employee.
2. Admin can manage employees, tasks, and quiz questions.
3. Employee can view tasks and take quizzes.
4. Task completion and scores are automatically updated in the database.

---


