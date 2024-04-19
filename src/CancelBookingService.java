import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

class CancelBookingService {
    private Connection connection;

    public CancelBookingService(Connection connection) {
        this.connection = connection;
    }

    // Method to retrieve booking details
    public Booking getBookingDetails(int bookingId) throws SQLException {
        BookingDAO bookingDAO = new BookingDAO(connection);
        return bookingDAO.getBookingById(bookingId);
    }

    // Method to cancel the booking
    public void cancelBooking(Scanner scanner) {
        try {
            System.out.print("Enter the booking ID to cancel: ");
            int bookingId = scanner.nextInt();

            BookingDAO bookingDAO = new BookingDAO(connection);
            Booking booking = bookingDAO.getBookingById(bookingId);

            if (booking != null) {
                // Update booking status to "cancelled"
                bookingDAO.updateBookingStatus(bookingId, Booking.CANCELLED);

                FlightDAO flightDAO = new FlightDAO(connection);
                flightDAO.incrementAvailableSeats(booking.getFlightId(), 1);
                System.out.println("Booking cancelled successfully.");
            } else {
                System.out.println("Booking not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while cancelling booking: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
