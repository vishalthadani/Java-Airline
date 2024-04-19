import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PriceCalculationService {
    private Connection connection;

    public PriceCalculationService(Connection connection) {
        this.connection = connection;
    }

    public BigDecimal getDynamicPrice(Flight flight) {
        CityDAO cityDAO = new CityDAO(connection);
        String cityType = null;
        try {

            List<City> cities = cityDAO.getAllCities();
            for (City city : cities) {
                if (city.getCityId() == flight.getSrcId()) {
                    cityType = city.getCityType();
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to calculate dynamic price.");
            e.printStackTrace();
        }

        DynamicPriceCalculator calculator;
        switch (cityType) {
            case "A":
                calculator = new CalculatorForA();
                break;
            case "B":
                calculator = new CalculatorForB();
                break;
            case "C":
                calculator = new CalculatorForC();
                break;
            default:
                throw new IllegalArgumentException("Invalid city type: " + cityType);
        }

        return calculator.calculateDynamicPrice(cityType, flight.getAvailableSeats(), flight.getTotalSeats(), flight.getTicketPrice());
    }

    interface DynamicPriceCalculator {
        BigDecimal calculateDynamicPrice(String cityType, int availableSeats, int totalSeats, BigDecimal ticketPrice);
    }

    class CalculatorForA implements DynamicPriceCalculator {
        public BigDecimal calculateDynamicPrice(String cityType, int availableSeats, int totalSeats, BigDecimal ticketPrice) {
            double priceFactor = 1.0 * ((double) (totalSeats - availableSeats) / totalSeats);
            BigDecimal basePrice = ticketPrice; //
            return basePrice.multiply(BigDecimal.valueOf(priceFactor));
        }
    }

    class CalculatorForB implements DynamicPriceCalculator {
        public BigDecimal calculateDynamicPrice(String cityType, int availableSeats, int totalSeats, BigDecimal ticketPrice) {
            double priceFactor = 2.0 * ((double) (totalSeats - availableSeats) / totalSeats);
            BigDecimal basePrice = ticketPrice;
            return basePrice.multiply(BigDecimal.valueOf(priceFactor));
        }
    }

    class CalculatorForC implements DynamicPriceCalculator {
        public BigDecimal calculateDynamicPrice(String cityType, int availableSeats, int totalSeats, BigDecimal ticketPrice) {
            double priceFactor = 3.0 * ((double) (totalSeats - availableSeats) / totalSeats);
            BigDecimal basePrice = ticketPrice;
            return basePrice.multiply(BigDecimal.valueOf(priceFactor));
        }
    }
}