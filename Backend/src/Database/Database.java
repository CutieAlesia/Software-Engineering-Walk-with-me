package Database;

import Functionality.FunctionHandler;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author C. Maas
 * @version 1.3
 */
public class Database {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet query = null;
    private FunctionHandler functionHandler;

    /**
     * @throws IOException DB connection error
     */
    public Database() throws IOException {
        //Only for testing purpose
        functionHandler = new FunctionHandler();
        ArrayList<String> creds = functionHandler.readFile("PATH");
        connect(creds.get(0), creds.get(1), creds.get(2));
    }

    /**
     * @param url DB hostname
     * @param user DB username
     * @param pass DB user password
     */
    private void connect(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            if(connection != null) {
                System.out.format("Connected to Database as {%s}", connection.getMetaData().getUserName());
            } else {
                System.out.println("Connection is null");
                System.exit(0);
            }
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @throws SQLException DB connection error
     */
    public void viewUsers() throws SQLException {
        query = statement.executeQuery("SELECT * FROM user");
        System.out.println("\n--- Registered Users ---");
        while(query.next()) {
            System.out.format("%s | %s", query.getString("username"), query.getString("email"));
        }
    }

}
