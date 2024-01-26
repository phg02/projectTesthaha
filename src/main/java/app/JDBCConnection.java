package app;

import java.util.ArrayList;

import helper.WorldTemperature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/ClimateDB.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<info> getMinYearTemp() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> min = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AVG_temp, year FROM WorldTemperature \n" + //
                    "WHERE year = (SELECT MIN(year)FROM WorldTemperature);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                double avgTemp = results.getDouble("AVG_temp");
                int year = results.getInt("year");

                info.AVGtemp = avgTemp;
                info.year = year;

                // Add the lga object to the array
                min.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return min;
    }

    public ArrayList<info> getMaxYearTemp() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> max = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AVG_temp, year FROM WorldTemperature WHERE year = (SELECT MAX(year)FROM WorldTemperature)";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                double avgTemp = results.getDouble("AVG_temp");
                int year = results.getInt("year");

                info.AVGtemp = avgTemp;
                info.year = year;

                // Add the lga object to the array
                max.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return max;
    }

    public ArrayList<info> getMinYearPopulation() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> min = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT pop_num, year FROM Population p JOIN Country c ON p.country_code = c.country_code \r\n"
                    + //
                    "WHERE p.country_code =\"WLD\" AND p.year=(SELECT MIN(year)FROM Population);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                long population = results.getLong("pop_num");
                int year = results.getInt("year");

                info.population = population;
                info.year = year;

                // Add the lga object to the array
                min.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return min;
    }

    public ArrayList<info> getMaxYearPopulation() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> max = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT pop_num, year FROM Population p JOIN Country c ON p.country_code = c.country_code \r\n"
                    + //
                    "WHERE p.country_code =\"WLD\" AND p.year=(SELECT MAX(year)FROM Population);";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                info info = new info();
                long population = results.getLong("pop_num");
                int year = results.getInt("year");

                info.population = population;
                info.year = year;

                // Add the lga object to the array
                max.add(info);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return max;
    }

    public ArrayList<info> getallYear() {
        // Create the ArrayList of LGA objects to return
        ArrayList<info> yearlist = new ArrayList<info>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT year FROM Population ORDER BY year ASC;";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results

            while (results.next()) {
                info info = new info();
                // Lookup the columns we need
                info.year = results.getInt("year");
                // Add the lga object to the array
                yearlist.add(info);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return yearlist;
    }

    public ArrayList<info> getallCountry() {
        ArrayList<info> countrylist = new ArrayList<info>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT country_name FROM Country;";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                info info = new info();
                info.country = results.getString("country_name");
                countrylist.add(info);
            }

            statement.close();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return countrylist;
    }

    public ArrayList<info> getallCity() {
        ArrayList<info> citylist = new ArrayList<info>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT city_name FROM CityTemperature ORDER BY city_name;";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                info info = new info();
                info.cityName = results.getString("city_name");
                citylist.add(info);
            }

            statement.close();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return citylist;
    }

    public ArrayList<info> lvl2query(String one, String two) {
        ArrayList<info> lvl2list = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year ,pop_num ,AVG_temp, MIN_temp, MAX_TEMP FROM WorldTemperature wt JOIN Population p  WHERE wt.year>="
                    + one + " AND wt.year<=" + two + " AND p.country_code= 'WLD' AND p.year = wt.year GROUP BY wt.year";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2list.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2list;

    }

    public ArrayList<info> lv2queryYearReader(String one) {
        ArrayList<info> lvl2list = new ArrayList<info>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT wt.year ,pop_num ,AVG_temp, MIN_temp, MAX_TEMP FROM WorldTemperature wt JOIN Population p  WHERE wt.year= "
                    + one + " AND p.country_code= 'WLD' AND  p.year = wt.year GROUP BY wt.year";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                info info = new info();
                info.year = results.getInt("year");
                info.population = results.getLong("pop_num");
                info.AVGtemp = results.getDouble("AVG_temp");
                info.Mintemp = results.getDouble("MIN_temp");
                info.Maxtemp = results.getDouble("MAX_TEMP");
                lvl2list.add(info);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return lvl2list;

    }

    // TODO: Add your required methods here
}
