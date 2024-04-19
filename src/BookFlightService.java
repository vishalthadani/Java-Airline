import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BookFlightService {
    private Connection connection;

    public BookFlightService(Connection connection) {
        this.connection = connection;
    }

    public void searchForFlights(Scanner scanner) {
        AirlineReservationCLI.clearScreen();
        System.out.println("Search for Available Flights");

        // Get user input: source city, destination city, date, number of passengers
        CityDAO cityDAO = new CityDAO(connection);
        try {
            List<City> cities = cityDAO.getAllCities();

            // Display available cities
            System.out.println("Available Cities:");
            for (City city : cities) {
                System.out.println(city.getCityId() + ". " + city.getCityName());
            }

            System.out.print("Enter source city ID: ");
            int sourceCityId = scanner.nextInt();

            System.out.print("Enter destination city ID: ");
            int destCityId = scanner.nextInt();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.next();

            System.out.print("Enter number of passengers: ");
            int numOfPassengers = scanner.nextInt();

            // Call method to search for available flights
            FlightDAO flightDAO = new FlightDAO(connection);
            List<Flight> availableFlights = flightDAO.searchAvailableFlights(sourceCityId, destCityId, date, numOfPassengers);

            if(availableFlights.isEmpty()) {
                System.out.println("No flights available for the provided details.");
                return;
            }

            //Call method to select from available flights
            int selectedFlightIndex = -1;
            while(selectedFlightIndex == -1){
                selectedFlightIndex = selectFlight(availableFlights, scanner);
            }

            //Call method to add passenger details
            List<Customer> passengers = addCustomerDetails(scanner, numOfPassengers);

            //Ask for confirmation
            System.out.print("Confirm booking (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                try {
                    CustomerDAO customerDAO = new CustomerDAO(connection);
                    BookingDAO bookingDAO = new BookingDAO(connection);
                    List<Booking> bookings = new ArrayList<>();

                    // Add customer details to the Customer table
                    for (Customer passenger : passengers) {
                        customerDAO.addCustomer(passenger);
                    }

                    // Get the last inserted customer ID
                    int lastCustomerId = customerDAO.getLastCustomerId();

                    // Add booking details to the Booking table
                    for (int i = 0; i < passengers.size(); i++) {
                        Customer passenger = passengers.get(i);
                        int customerId = lastCustomerId - passengers.size() + i + 1;
                        Booking booking = new Booking(0, availableFlights.get(selectedFlightIndex).getFlightId(), customerId, LocalDateTime.now(), Booking.BOOKED, availableFlights.get(selectedFlightIndex).getTicketPrice());
                        bookingDAO.addBooking(booking);
                        bookings.add(booking);
                    }

                    //Decrement number of available seats
                    flightDAO.decrementAvailableSeats(availableFlights.get(selectedFlightIndex).getFlightId(), numOfPassengers);

                    System.out.println("Booking confirmed.");
                    System.out.println(bookings);

                } catch (SQLException e) {
                    System.out.println("Failed to book flight. Please try again later.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Booking cancelled.");
            }


        } catch (SQLException e) {
            System.out.println("Failed to retrieve flight details.");
            e.printStackTrace();
        }
    }

    public int selectFlight(List<Flight> availableFlights, Scanner scanner) {
        System.out.println("Available Flights:");
        int index = 1;
        for (Flight flight : availableFlights) {
            System.out.println("Flight Index " + index + ":");
            System.out.println("Flight Code: " + flight.getFlightId());
            System.out.println("Source City ID: " + flight.getSrcId());
            System.out.println("Destination City ID: " + flight.getDestId());
            System.out.println("Departure Date and Time: " + flight.getDepartureDateTime());
            System.out.println("Arrival Date and Time: " + flight.getArrivalDateTime());
            System.out.println("Duration: " + flight.getDuration());
            System.out.println("Total Seats: " + flight.getTotalSeats());
            System.out.println("Available Seats: " + flight.getAvailableSeats());
            System.out.println("Ticket Price: " + flight.getTicketPrice());
            System.out.println("----------------------------------------");
            index++;
        }

        System.out.print("Select a flight by entering its index: ");
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();

        if (selectedIndex >= 1 && selectedIndex <= availableFlights.size()) {
            // Valid selection
            Flight selectedFlight = availableFlights.get(selectedIndex - 1);
        } else {
            System.out.println("Invalid selection. Please try again.");
            return -1;
        }
        return (selectedIndex-1);
    }

    public List<Customer> addCustomerDetails(Scanner scanner, int numOfPassengers) {
        List<Customer> passengers = new ArrayList<>();
        System.out.println();

        for (int i = 1; i <= numOfPassengers; i++) {
            System.out.println("Passenger " + i + " Details:");
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter mobile: ");
            String mobile = scanner.nextLine();

            // Create a new Customer object and add it to the passengers list
            Customer customer = new Customer(i, firstName, lastName, email, mobile);
            passengers.add(customer);
        }

        // Display passenger details for confirmation
        System.out.println("Passenger Details:");
        for (int i = 0; i < numOfPassengers; i++) {
            Customer passenger = passengers.get(i);
            System.out.println("Passenger " + (i + 1) + ": " + passenger.getFirstName() + " " + passenger.getLastName());
            // Display other details as needed
        }
        return passengers;
    }
}
