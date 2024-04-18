import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CityDAO {
    private Connection connection;

    public CityDAO(Connection connection) {
        this.connection = connection;
    }

    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM city";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cityId = resultSet.getInt("city_id");
                String cityName = resultSet.getString("city_name");
                String cityType = resultSet.getString("city_type");
                cities.add(new City(cityId, cityName, cityType));
            }
        }
        return cities;
    }
}