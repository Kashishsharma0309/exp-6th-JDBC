import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root"; // your MySQL username
        String password = "root"; // your MySQL password

        try {
            // 1. Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // 3. Create Statement
            Statement stmt = con.createStatement();

            // 4. Execute Query
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            // 5. Display Results
            System.out.println("Employee Details:");
            System.out.println("-------------------");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("EmpID") +
                                   ", Name: " + rs.getString("Name") +
                                   ", Salary: " + rs.getDouble("Salary"));
            }

            // 6. Close connection
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
