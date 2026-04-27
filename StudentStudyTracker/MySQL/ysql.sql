CREATE DATABASE study_tracker;
USE study_tracker;
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    semester INT NOT NULL
);
CREATE TABLE study_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    study_date DATE,
    hours_studied INT,
    stress_level INT CHECK (stress_level BETWEEN 1 AND 5),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);
ALTER TABLE study_log
ADD CONSTRAINT fk_student
FOREIGN KEY (student_id)
REFERENCES students(student_id)
ON DELETE CASCADE;