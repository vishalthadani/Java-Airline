import java.sql.Connection;
import java.util.Scanner;

class MainMenu {
    public static void showMainMenu(Connection connection) {
        boolean loggedIn = LoginService.login(connection);
        if (!loggedIn) {
            System.out.println("Login failed. Exiting.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        BookFlightService bookFlightService = new BookFlightService(connection);
        CancelBookingService cancelBookingService = new CancelBookingService(connection);
        while (true) {
            AirlineReservationCLI.clearScreen();
            System.out.println("Main Menu");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Booked Tickets");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You selected Book Ticket.");
                    bookFlightService.searchForFlights(scanner);
                    break;
                case 2:
                    System.out.println("You selected Cancel Ticket.");
                    cancelBookingService.cancelBooking(scanner);
                    break;
                case 3:
                    System.out.println("You selected View Booked Tickets.");
                    // Implement view booked tickets functionality
                    break;
                case 4:
                    System.out.println("Exiting. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}