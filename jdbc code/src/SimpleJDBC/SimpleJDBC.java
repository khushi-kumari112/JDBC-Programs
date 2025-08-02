package SimpleJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * SimpleJDBC is a basic example of using JDBC in Java to:
 * - Connect to a MySQL database
 * - Create a table if it doesn't exist
 * - Insert sample data
 * - Retrieve and display that data
 * 
 * <p>Database: jdbc<br>
 * Table: students (id, name, course, marks)</p>
 * 
 * Make sure your MySQL server is running and the database exists.
 * 
 * @author Khushi
 * @version 1.0
 */
public class SimpleJDBC {
    public static void main(String[] args) {

        // Database connection variables
        String url = "jdbc:mysql://localhost:3306/jdbc";  
        String user = "root"; 
        String password = "KHU12@shi"; 

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println(" Connected to the database!");

            // Create SQL statement object
            Statement stmt = conn.createStatement();

            // Create table if it doesn't exist
            String createTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100)," +
                    "course VARCHAR(50)," +
                    "marks INT)";
            stmt.executeUpdate(createTable);
            System.out.println(" Table 'students' created (if not exists).");

            // Insert sample data
            String insertData = "INSERT INTO students (name, course, marks) VALUES" +
                    "('Aman', 'Math', 88)," +
                    "('Riya', 'English', 76)," +
                    "('Sohan', 'Science', 92)";
            stmt.executeUpdate(insertData);
            System.out.println(" Sample data inserted into table.");

            // Retrieve and display data
            String selectQuery = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(selectQuery);

            System.out.println("\n Student Data:");
            System.out.println("----------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String course = rs.getString("course");
                int marks = rs.getInt("marks");

                System.out.println("ID: " + id + ", Name: " + name + ", Course: " + course + ", Marks: " + marks);
            }

            // Close all resources
            rs.close();          
            stmt.close(); 
            conn.close();
            System.out.println("\n Database connection closed.");

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
