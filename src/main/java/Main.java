import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class Main {

    static Set<String> usedBySystem = new HashSet<>();
    static Set<String> usedByUser = new HashSet<>();

    private final static String dbURL = "jdbc:sqlite:/Users/viktoriakuznichenko/coursework.db";
    private static Connection connection = null;

    static {
        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static DatabaseConnection databaseConnection = new DatabaseConnection(connection);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException, SQLException {

//        CitiesAddLogic citiesAddLogic = new CitiesAddLogic();
//        citiesGame(citiesAddLogic.addCities());

        Map<String,String> map2 = databaseConnection.getCity();
        citiesGame(map2);

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

    }

    public static void citiesGame(Map<String,String> map) {

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        if (map.containsKey(userInput)) {

            char ending = citiesGameEndingChar(map, userInput);
            do {

                String nextUserInput = scanner.nextLine();

                if (map.containsKey(nextUserInput)) {

                    if (nextUserInput.charAt(0) != ending) {

                        System.out.println("First char not valid! Please enter city, starting from: " + ending);
                    } else if (usedByUser.contains(nextUserInput)) {
                        System.out.println("Already used by user");

                    } else if (usedBySystem.contains(nextUserInput)) {
                        System.out.println("Already used by system");

                    } else {

                        ending = citiesGameEndingChar(map, nextUserInput);


                    }
                    usedByUser.add(nextUserInput);

                } else {
                    System.out.println("Put correct name! Please enter city, starting from: " + ending);
                }
            } while (true);
        }
    }

    public static char citiesGameEndingChar(Map<String, String> map, String string) {


        Set<String> stringsKeys = map.keySet();
        char ending = 0;
        char charAt = string.charAt(string.length() - 1);

        for (String string1 : stringsKeys) {

            char string2 = string1.charAt(0);

            if (charAt == (string2) && (!usedBySystem.contains(string1)) && (!string.equals(string1)) && (!usedByUser.contains(string1))) {

                ending = string1.charAt(string1.length() - 1);

                System.out.println(string1 + " " + map.get(string1) + " " + ending);
                usedBySystem.add(string1);

                break;

            }

        }

        return ending;

    }

}



