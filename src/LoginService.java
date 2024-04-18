import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

class LoginService {
    public static boolean login(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Java Airlines. Please log in.");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        LoginDAO loginDAO = new LoginDAO(connection);
        try {
            return loginDAO.authenticate(username, password);
        } catch (Exception e) {
            System.out.println("Login failed.");
            e.printStackTrace();
            return false;
        }
    }
}