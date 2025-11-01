import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empIdParam = request.getParameter("empid");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");
            Statement st = con.createStatement();

            String query;
            if (empIdParam != null && !empIdParam.isEmpty()) {
                query = "SELECT * FROM Employee WHERE EmpID=" + empIdParam;
            } else {
                query = "SELECT * FROM Employee";
            }

            ResultSet rs = st.executeQuery(query);

            out.println("<h2>Employee Details</h2>");
            out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                            rs.getString("Name") + "</td><td>" +
                            rs.getDouble("Salary") + "</td></tr>");
            }
            out.println("</table>");

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
