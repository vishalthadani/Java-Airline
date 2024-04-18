import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FlightDAO {
    private Connection connection;

    public FlightDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFlight(Flight flight) throws SQLException {
        String query = "INSERT INTO flight (src_id, dest_id, departure_date_time, arrival_date_time, duration, total_seats, available_seats, ticket_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, flight.getSrcId());
            stmt.setInt(2, flight.getDestId());
            stmt.setObject(3, flight.getDepartureDateTime());
            stmt.setObject(4, flight.getArrivalDateTime());
            stmt.setInt(5, flight.getDuration());
            stmt.setInt(6, flight.getTotalSeats());
            stmt.setInt(7, flight.getAvailableSeats());
            stmt.setBigDecimal(8, flight.getTicketPrice());
            stmt.executeUpdate();
        }
    }

    public List<Flight> getAllFlights() throws SQLException {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flight";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flight_id"));
                flight.setSrcId(rs.getInt("src_id"));
                flight.setDestId(rs.getInt("dest_id"));
                flight.setDepartureDateTime(rs.getObject("departure_date_time", LocalDateTime.class));
                flight.setArrivalDateTime(rs.getObject("arrival_date_time", LocalDateTime.class));
                flight.setDuration(rs.getInt("duration"));
                flight.setTotalSeats(rs.getInt("total_seats"));
                flight.setAvailableSeats(rs.getInt("available_seats"));
                flight.setTicketPrice(rs.getBigDecimal("ticket_price"));
                flights.add(flight);
            }
        }
        return flights;
    }

    public void updateAvailableSeats(int flightId, int seatsBooked) throws SQLException {
        String query = "UPDATE flight SET available_seats = available_seats - ? WHERE flight_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, seatsBooked);
            stmt.setInt(2, flightId);
            stmt.executeUpdate();
        }
    }

    public void updateTicketPrice(int flightId, BigDecimal newPrice) throws SQLException {
        String query = "UPDATE flight SET ticket_price = ? WHERE flight_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBigDecimal(1, newPrice);
            stmt.setInt(2, flightId);
            stmt.executeUpdate();
        }
    }

    public List<Flight> searchAvailableFlights(int src_id, int dest_id, String date, int numOfPassengers) throws SQLException {
        List<Flight> availableFlights = new ArrayList<>();
        String query = "SELECT * FROM flight WHERE src_id = ? AND dest_id = ? AND DATE(departure_datetime) = ? AND available_seats >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, src_id);
            preparedStatement.setInt(2, dest_id);
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, numOfPassengers);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setFlightId(resultSet.getInt("flight_id"));
                flight.setSrcId(resultSet.getInt("src_id"));
                flight.setDestId(resultSet.getInt("dest_id"));
                flight.setDepartureDateTime(resultSet.getObject("departure_datetime", LocalDateTime.class));
                flight.setArrivalDateTime(resultSet.getObject("arrival_datetime", LocalDateTime.class));
                flight.setDuration(resultSet.getInt("duration"));
                flight.setTotalSeats(resultSet.getInt("total_seats"));
                flight.setAvailableSeats(resultSet.getInt("available_seats"));
                flight.setTicketPrice(resultSet.getBigDecimal("ticket_price"));
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }

}