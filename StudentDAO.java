import java.sql.*;

public class StudentDAO {
    private Connection con;

    public StudentDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/schooldb", "root", "root");
    }

    public void insertStudent(Student s) throws SQLException {
        String query = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, s.getStudentID());
        ps.setString(2, s.getName());
        ps.setString(3, s.getDepartment());
        ps.setDouble(4, s.getMarks());
        ps.executeUpdate();
        System.out.println("✅ Student inserted successfully!");
    }

    public void displayStudents() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
        System.out.println("Student Records:");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " | " +
                               rs.getString(2) + " | " +
                               rs.getString(3) + " | " +
                               rs.getDouble(4));
        }
    }
}
