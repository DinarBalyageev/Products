package connections;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerPostgresImpl implements ConnectionManager {
    private static Logger logger = Logger.getLogger(ConnectionManagerPostgresImpl.class);

    private static ConnectionManager connectionManager;

    public static ConnectionManager getInstance(){
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerPostgresImpl();
        }
        return connectionManager;
    }

    private ConnectionManagerPostgresImpl(){
    }
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ordering_products", "postgres", "123456");
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        }
        return connection;
    }
}