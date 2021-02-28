import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class CitiesAddLogic {

    public Map addCities() throws IOException, SQLException {
        Main main = new Main();
        DatabaseConnection databaseConnection = main.databaseConnection;
        City city;
        BufferedReader reader = main.reader;

        Map map1 = new HashMap();
        for (int i = 0; i < 3; i++) {

            city = new City(UUID.randomUUID().toString(), addManualy(reader));

            databaseConnection.addCity(city);
            databaseConnection.getCity();
            map1.put(city.getName(), city.getId());
        }
        return databaseConnection.getCity();
    }

    public String addManualy(BufferedReader reader) throws IOException {
        return reader.readLine();
    }
}