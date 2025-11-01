import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/shopdb";
        String user = "root";
        String password = "root";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false); // for transaction handling

            while (true) {
                System.out.println("\n=== Product CRUD Menu ===");
                System.out.println("1. Insert Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product Price");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();

                        PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO Product VALUES(?, ?, ?, ?)");
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setDouble(3, price);
                        ps.setInt(4, qty);
                        ps.executeUpdate();
                        con.commit();
                        System.out.println("Product Added Successfully!");
                        break;

                    case 2:
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
                        System.out.println("Product List:");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + " | " +
                                               rs.getString(2) + " | " +
                                               rs.getDouble(3) + " | " +
                                               rs.getInt(4));
                        }
                        break;

                    case 3:
                        System.out.print("Enter Product ID to Update: ");
                        int uid = sc.nextInt();
                        System.out.print("Enter New Price: ");
                        double newPrice = sc.nextDouble();

                        PreparedStatement ps2 = con.prepareStatement(
                            "UPDATE Product SET Price=? WHERE ProductID=?");
                        ps2.setDouble(1, newPrice);
                        ps2.setInt(2, uid);
                        ps2.executeUpdate();
                        con.commit();
                        System.out.println("Product Updated Successfully!");
                        break;

                    case 4:
                        System.out.print("Enter Product ID to Delete: ");
                        int did = sc.nextInt();
                        PreparedStatement ps3 = con.prepareStatement(
                            "DELETE FROM Product WHERE ProductID=?");
                        ps3.setInt(1, did);
                        ps3.executeUpdate();
                        con.commit();
                        System.out.println("Product Deleted Successfully!");
                        break;

                    case 5:
                        con.close();
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
