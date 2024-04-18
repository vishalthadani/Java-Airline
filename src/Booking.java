import java.math.BigDecimal;
import java.time.LocalDateTime;

class Booking {
    private int bookingId;
    private int flightId;
    private int customerId;
    private LocalDateTime bookingDateTime;
    private String bookingStatus;
    private BigDecimal totalAmount;
    public static final String BOOKED = "booked";
    public static final String CANCELLED = "cancelled";

    // Constructors
    public Booking() {
    }

    public Booking(int bookingId, int flightId, int customerId, LocalDateTime bookingDateTime,
                   String bookingStatus, BigDecimal totalAmount) {
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.customerId = customerId;
        this.bookingDateTime = bookingDateTime;
        this.bookingStatus = bookingStatus;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // toString method
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", flightId=" + flightId +
                ", customerId=" + customerId +
                ", bookingDateTime=" + bookingDateTime +
                ", bookingStatus=" + bookingStatus +
                ", totalAmount=" + totalAmount +
                '}';
    }
}