-- Create database
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;



-- Users table (Admin + Employees)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(15),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Tasks table
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    description VARCHAR(255),
    completed BOOLEAN DEFAULT FALSE,
    score INT DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Quiz Questions table
CREATE TABLE quiz_questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(50) NOT NULL,
    question TEXT NOT NULL,
    option1 VARCHAR(100),
    option2 VARCHAR(100),
    option3 VARCHAR(100),
    correct_answer VARCHAR(100)
);

-- Insert Admin
INSERT INTO users(name, role, address, phone, email, password) VALUES
('System Admin','Admin','US','9483929232','admin@etms.com','admin123');

-- Insert Employees
INSERT INTO users(name, role, address, phone, email, password) VALUES
('Alice Johnson','Full Stack Developer','Bangalore','9876543210','alice@etms.com','alice123'),
('Bob Smith','Java Developer','Chennai','9876543220','bob@etms.com','bob123'),
('Charlie Brown','Python Developer','Hyderabad','9876543230','charlie@etms.com','charlie123');

-- Quiz Questions for Full Stack Developer
INSERT INTO quiz_questions(role, question, option1, option2, option3, correct_answer) VALUES
('Full Stack Developer','Which technology is used for frontend development?','React','MySQL','Spring Boot','React'),
('Full Stack Developer','Which stack combines MongoDB, Express, React, Node?','MERN','LAMP','MEAN','MERN');

-- Quiz Questions for Java Developer
INSERT INTO quiz_questions(role, question, option1, option2, option3, correct_answer) VALUES
('Java Developer','Which keyword is used to inherit a class in Java?','extends','implements','inherit','extends'),
('Java Developer','Which package is automatically imported in every Java program?','java.lang','java.util','java.io','java.lang');

-- Quiz Questions for Python Developer
INSERT INTO quiz_questions(role, question, option1, option2, option3, correct_answer) VALUES
('Python Developer','Which keyword is used to define a function in Python?','func','def','function','def'),
('Python Developer','Which data type is immutable in Python?','List','Dictionary','Tuple','Tuple');
