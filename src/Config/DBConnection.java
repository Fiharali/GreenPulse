package Config;

import utils.PropertiesReader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {


    private static DBConnection instance;

    private final Connection connection;


    private DBConnection() throws SQLException {
        try {

            Properties properties = PropertiesReader.readProperties();
            String url = properties.getProperty("db.url");
            String dbName = properties.getProperty("db.name");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String driver = properties.getProperty("db.driver");

            Class.forName(driver);
            this.connection = DriverManager.getConnection(url + dbName, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }


    public Connection getConnection() {
        return connection;
    }
}
