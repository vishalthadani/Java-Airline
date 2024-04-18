import java.math.BigDecimal;
import java.time.LocalDateTime;

class Flight {
    private int flightId;
    private int srcId;
    private int destId;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int duration;
    private int totalSeats;
    private int availableSeats;
    private BigDecimal ticketPrice;

    // Constructors
    public Flight() {
    }

    public Flight(int flightId, int srcId, int destId, LocalDateTime departureDateTime,
                  LocalDateTime arrivalDateTime, int duration, int totalSeats, int availableSeats,
                  BigDecimal ticketPrice) {
        this.flightId = flightId;
        this.srcId = srcId;
        this.destId = destId;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.duration = duration;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
    }

    // Getters and setters
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public int getDestId() {
        return destId;
    }

    public void setDestId(int destId) {
        this.destId = destId;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    // toString method
    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", srcId=" + srcId +
                ", destId=" + destId +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                ", duration=" + duration +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}