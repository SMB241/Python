import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/javadatabase?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root"; // MySQL default username
        String password = "";     // MySQL default password for root in XAMPP (empty)

        // Connection object
        Connection conn = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement stmt = conn.createStatement();

            // Query to fetch data from the student table
            String query = "SELECT * FROM student"; // Select all records from the student table
            ResultSet rs = stmt.executeQuery(query);

            // Process the ResultSet
            while (rs.next()) {
                // Retrieve and print the student details
                int id = rs.getInt("id");
                String studentid = rs.getString("studentid");
                String lname = rs.getString("lname");
                String fname = rs.getString("fname");
                String mi = rs.getString("mi");

                System.out.println("ID: " + id);
                System.out.println("Student ID: " + studentid);
                System.out.println("Last Name: " + lname);
                System.out.println("First Name: " + fname);
                System.out.println("Middle Initial: " + mi);
                System.out.println("--------------------------");
                System.out.println(System.getProperty("java.class.path"));
            }

            // Close resources
            rs.close();
            stmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
