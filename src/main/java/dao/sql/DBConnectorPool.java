package dao.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBConnectorPool {
    private static DBConnectorPool instance;
    private List<Connection> connections = new LinkedList();
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBConnectorPool(String url, String username, String password, Driver driver) {
        try {
            DriverManager.registerDriver(driver);
            connection = (DriverManager.getConnection(url, username, password));
            connections.add(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectorPool getInstance(String URL, String user, String password,Driver driver) throws SQLException {
        if (instance == null) {
            instance = new DBConnectorPool(URL, user, password,driver);
        }
        return instance;
    }
}
