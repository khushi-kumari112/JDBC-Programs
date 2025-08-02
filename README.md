# JDBC-Programs 💻

This repository contains Java programs demonstrating how to interact with **MySQL databases using JDBC**. It covers:

## 📂 Folder Structure

📁 SimpleJDBC/ → Basic JDBC connection and table operations
📁 EmployeeCRUD/ → Full CRUD application for employee management
📁 AdvancedJDBC/ → Advanced SQL operations like JOIN, GROUP BY
📁 lib/ → MySQL JDBC connector .jar file
📄 db_commands.sql → All MySQL commands for table and data setup
📄 db_cheatsheet.md → Quick reference for SQL queries and concepts



## ✅ Features

- Java + JDBC connection
- Table creation, insertion, updates, deletion
- SQL joins, aggregates, and multi-table relations
- Console-based interaction
- MySQL connectivity

## 🚀 Setup

1. Clone this repo  
2. Make sure MySQL is installed and running  
3. Run `.java` files from `src` after setting up the MySQL DB  
4. Use the included `db_commands.sql` for schema setup
   
## 🖼️ Demo

Here’s how the console output looks when running `EmployeeCRUD`:

========= EMPLOYEE MANAGEMENT =========

Add Employee

View All Employees

Update Employee Salary

Delete Employee

Exit


Advance_db result tables:-
Choose: SELECT * FROM employees;
+--------+----------+---------+--------+
| emp_id | emp_name | dept_id | salary |
+--------+----------+---------+--------+
|      1 | Khushi   |       1 |  60000 |
|      2 | Rahul    |       1 |  75000 |
|      3 | Riya     |       2 |  50000 |
|      4 | Aman     |       3 |  45000 |
|      5 | Sohan    |       2 |  55000 |
+--------+----------+---------+--------+
5 rows in set (0.00 sec)

mysql> SELECT * FROM departments;
+---------+-----------+-----------+
| dept_id | dept_name | location  |
+---------+-----------+-----------+
|       1 | IT        | Delhi     |
|       2 | HR        | Mumbai    |
|       3 | Finance   | Bangalore |
+---------+-----------+-----------+
3 rows in set (0.00 sec)


## 👩‍💻 Author

**Khushi Kumari**  
_Java enthusiast and backend learner_  
🌐 GitHub: [@khushi-kumari112](https://github.com/khushi-kumari112)

