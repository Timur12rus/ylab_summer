package dao;

import java.sql.*;

import static java.sql.DriverManager.*;

public class UserDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/ws_db";

    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        try (Connection connection = getConnection(URL, USER_NAME, PASSWORD)) {
            createTable(connection);
            insertRecord(connection);
            ResultSet resultSet = retrieveUsers(connection);
            printUsers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createSequenceForGenerateId = "CREATE SEQUENCE s1; SELECT nextVal('s1'); SELECT currval('s1');";

        String createTableSQL = "CREATE SEQUENCE s1; SELECT nextVal('s1'); SELECT currval('s1');CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL  PRIMARY KEY DEFAULT(nextval('s1'))," +
                "login VARCHAR(255) NOT NULL unique," +
                "password VARCHAR(255) NOT NULL)";
        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableSQL);
    }

    private static void insertRecord(Connection connection) throws SQLException {
        String insertDataSQL = "INSERT INTO users (login, password) VALUES (?, ?)";
        PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
        insertDataStatement.setString(1, "User1");
        insertDataStatement.setString(2, "password");
        insertDataStatement.executeUpdate();
    }

    private static ResultSet retrieveUsers(Connection connection) throws SQLException {
        String retrieveDataSQL = "SELECT * FROM users";
        Statement retrieveDataStatement = connection.createStatement();
        ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
        return resultSet;
    }

    private static void printUsers(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("login");
            int age = resultSet.getInt("password");
            System.out.println("ID: " + id + ", Login: " + name + ", Password: " + age);
        }
    }
}
