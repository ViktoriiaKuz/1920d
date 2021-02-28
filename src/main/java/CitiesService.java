import java.sql.SQLException;
import java.util.Map;

public interface CitiesService {

    Map<String, String> getCity() throws SQLException;

    void addCity(City city) throws SQLException;
}
