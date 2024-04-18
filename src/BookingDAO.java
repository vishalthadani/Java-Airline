import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class BookingDAO {
    private Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO booking (flight_id, customer_id, booking_datetime, booking_status, total_amount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, booking.getFlightId());
            stmt.setInt(2, booking.getCustomerId());
            stmt.setObject(3, booking.getBookingDateTime());
            stmt.setString(4, booking.getBookingStatus());
            stmt.setBigDecimal(5, booking.getTotalAmount());
            stmt.executeUpdate();
        }
    }

    public List<Booking> getBookingsByCustomerId(int customerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getInt("booking_id"));
                    booking.setFlightId(rs.getInt("flight_id"));
                    booking.setCustomerId(rs.getInt("customer_id"));
                    booking.setBookingDateTime(rs.getObject("booking_date_time", LocalDateTime.class));
                    booking.setBookingStatus(rs.getString("booking_status"));
                    booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }
}
