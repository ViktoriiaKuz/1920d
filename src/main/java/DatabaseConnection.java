import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection implements CitiesService {


    private Connection connection;

    DatabaseConnection(Connection connection) {

        this.connection = connection;
    }


    public void addCity(City city) throws SQLException {


        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate("create table if not exists cities(id text primary key, name text unique)");
        statement.executeUpdate("insert into cities values('" + city.getId() + "', '" + city.getName() + "');");

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
    }

    public Map<String, String> getCity() throws SQLException {


        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        ResultSet rs = statement.executeQuery("select * from cities");
        Map<String, String> map = new HashMap<>();

        while (rs.next()) {
            // read the result set
            System.out.println("name = " + rs.getString("name"));
            System.out.println("id = " + rs.getString("id"));
            map.put(rs.getString("name"),rs.getString("id"));
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return map;

    }


}

