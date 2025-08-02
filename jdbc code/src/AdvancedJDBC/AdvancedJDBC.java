package AdvancedJDBC;

import java.sql.*;

/**
 * AdvancedJDBC demonstrates advanced SQL operations via JDBC:
 * - INNER JOIN & LEFT JOIN
 * - GROUP BY & Aggregation
 * - Subqueries
 * - Sorting and Filtering
 * 
 * Database: advanced_db
 * Tables: departments, employees
 * 
 * Author: Khushi Tiwary
 */
public class AdvancedJDBC {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/advanced_db";
        String user = "root";
        String password = "KHU12@shi";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            System.out.println(" Connected to database.");

            // Create tables
            String createDeptTable = "CREATE TABLE IF NOT EXISTS departments (" +
                    "dept_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "dept_name VARCHAR(50)," +
                    "location VARCHAR(50))";
            stmt.executeUpdate(createDeptTable);

            String createEmpTable = "CREATE TABLE IF NOT EXISTS employees (" +
                    "emp_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "emp_name VARCHAR(100)," +
                    "dept_id INT," +
                    "salary DOUBLE," +
                    "FOREIGN KEY (dept_id) REFERENCES departments(dept_id))";
            stmt.executeUpdate(createEmpTable);

            // Insert sample data
            stmt.executeUpdate("INSERT INTO departments (dept_name, location) VALUES " +
                    "('IT', 'Delhi'), ('HR', 'Mumbai'), ('Finance', 'Bangalore')");

            stmt.executeUpdate("INSERT INTO employees (emp_name, dept_id, salary) VALUES " +
                    "('Khushi', 1, 60000)," +
                    "('Rahul', 1, 75000)," +
                    "('Riya', 2, 50000)," +
                    "('Aman', 3, 45000)," +
                    "('Sohan', 2, 55000)");

            // INNER JOIN: employees with their department info
            System.out.println("\n INNER JOIN - Employee with Department:");
            ResultSet rs1 = stmt.executeQuery(
                    "SELECT emp_name, dept_name, location, salary " +
                    "FROM employees e JOIN departments d ON e.dept_id = d.dept_id");

            while (rs1.next()) {
                System.out.println("Name: " + rs1.getString("emp_name") +
                        ", Dept: " + rs1.getString("dept_name") +
                        ", Location: " + rs1.getString("location") +
                        ", Salary: " + rs1.getDouble("salary"));
            }

            // GROUP BY + Aggregation
            System.out.println("\n Average Salary by Department:");
            ResultSet rs2 = stmt.executeQuery(
                    "SELECT d.dept_name, AVG(e.salary) AS avg_salary " +
                    "FROM employees e JOIN departments d ON e.dept_id = d.dept_id " +
                    "GROUP BY d.dept_name");

            while (rs2.next()) {
                System.out.println("Dept: " + rs2.getString("dept_name") +
                        ", Avg Salary: ₹" + rs2.getDouble("avg_salary"));
            }

            // Subquery: Employees earning above avg salary
            System.out.println("\n Employees earning above average salary:");
            ResultSet rs3 = stmt.executeQuery(
                    "SELECT emp_name, salary FROM employees " +
                    "WHERE salary > (SELECT AVG(salary) FROM employees)");

            while (rs3.next()) {
                System.out.println("Name: " + rs3.getString("emp_name") +
                        ", Salary: ₹" + rs3.getDouble("salary"));
            }

            // Sorting & Filtering with HAVING
            System.out.println("\n Departments with Avg Salary > 50000:");
            ResultSet rs4 = stmt.executeQuery(
                    "SELECT d.dept_name, AVG(e.salary) AS avg_sal " +
                    "FROM employees e JOIN departments d ON e.dept_id = d.dept_id " +
                    "GROUP BY d.dept_name HAVING avg_sal > 50000 ORDER BY avg_sal DESC");

            while (rs4.next()) {
                System.out.println("Dept: " + rs4.getString("dept_name") +
                        ", Avg Salary: ₹" + rs4.getDouble("avg_sal"));
            }

            // Close resources
            rs1.close(); rs2.close(); rs3.close(); rs4.close();
            stmt.close(); conn.close();
            System.out.println("\n Connection closed.");

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
