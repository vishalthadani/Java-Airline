//import java.sql.*;
//
//public class JDBCConnection {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/ars";
//        String username = "root";
//        String password = "";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM passenger_tbl");
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                String firstName = resultSet.getString(2);
//                String lastName = resultSet.getString(3);
//                String passportNumber = resultSet.getString(4);
//                Date dob = resultSet.getDate(5);
//                String email = resultSet.getString(6);
//                String mobile = resultSet.getString(7);
//
//                // Format the output
//                System.out.printf("%-5d %-15s %-15s %-25s %-15s %-8s %-15s%n",
//                        id, firstName, lastName, passportNumber, dob, email, mobile);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}
