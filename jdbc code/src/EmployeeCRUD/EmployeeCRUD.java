import java.sql.*;
import java.util.Scanner;

/**
 * EmployeeCRUD class provides a menu-driven program to perform CRUD operations
 * (Create, Read, Update, Delete) on an employee table in a MySQL database.
 * 
 * <p>Database: company <br>
 * Table: employee (id, name, department, salary)</p>
 * 
 * Make sure MySQL is running and the database/table is already created.
 * 
 * @author Khushi
 * @version 1.0
 */
public class EmployeeCRUD {

    // Database credentials
    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";
    static final String PASSWORD = "KHU12@shi";

    /**
     * Entry point of the program. Displays menu and performs CRUD operations.
     */
    public static void main(String[] args) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner sc = new Scanner(System.in);

            while (true) {
                // Menu display
                System.out.println("\n========= EMPLOYEE MANAGEMENT =========");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        // Insert new employee
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Department: ");
                        String dept = sc.next();
                        System.out.print("Enter Salary: ");
                        double sal = sc.nextDouble();

                        String insertQuery = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                        insertStmt.setString(1, name);
                        insertStmt.setString(2, dept);
                        insertStmt.setDouble(3, sal);
                        insertStmt.executeUpdate();
                        System.out.println(" Employee Added.");
                        break;

                    case 2:
                        // Display all employees
                        String selectQuery = "SELECT * FROM employee";
                        Statement selectStmt = conn.createStatement();
                        ResultSet rs = selectStmt.executeQuery(selectQuery);

                        System.out.println("\n📋 Employee List:");
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getInt("id")
                                    + ", Name: " + rs.getString("name")
                                    + ", Dept: " + rs.getString("department")
                                    + ", Salary: " + rs.getDouble("salary"));
                        }
                        break;

                    case 3:
                        // Update salary
                        System.out.print("Enter Employee ID to Update: ");
                        int upId = sc.nextInt();
                        System.out.print("Enter New Salary: ");
                        double newSalary = sc.nextDouble();

                        String updateQuery = "UPDATE employee SET salary = ? WHERE id = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                        updateStmt.setDouble(1, newSalary);
                        updateStmt.setInt(2, upId);
                        int updateCount = updateStmt.executeUpdate();
                        if (updateCount > 0) System.out.println("Salary Updated.");
                        else System.out.println(" Employee Not Found.");
                        break;

                    case 4:
                        // Delete employee
                        System.out.print("Enter Employee ID to Delete: ");
                        int delId = sc.nextInt();
                        String deleteQuery = "DELETE FROM employee WHERE id = ?";
                        PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                        deleteStmt.setInt(1, delId);
                        int delCount = deleteStmt.executeUpdate();
                        if (delCount > 0) System.out.println(" Employee Deleted.");
                        else System.out.println(" Employee Not Found.");
                        break;

                    case 5:
                        // Exit
                        conn.close();
                        sc.close();
                        System.out.println(" Connection Closed. Bye!");
                        return;

                    default:
                        System.out.println(" Invalid Option.");
                }
            }

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
