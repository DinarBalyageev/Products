package DAO;

import connections.ConnectionManager;
import connections.ConnectionManagerPostgresImpl;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDaoImpl implements UserDao {
    private final static Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerPostgresImpl.getInstance();

    @Override
    public Boolean get(String login, String password) {
        Boolean exit = false;
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT login, password FROM public.login WHERE login=? AND password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                    exit = true;
                    logger.info(resultSet.getString("login") + " вошел в систему");
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return exit;
    }

    @Override
    public Boolean add(int id, String login, String password) {
        Boolean exit = false;
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO public.login (id_buyer, login, password) VALUES (? ,?, ?);");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            exit = true;
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return exit;
    }

    @Override
    public Boolean findLogin(String login) {
        Boolean exit = false;
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT login FROM public.login WHERE login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exit = true;
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return exit;
    }

    @Override
    public int getId(String login) {
        int id = 0;
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT id_buyer FROM public.login WHERE login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id_buyer");
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return id;
    }
}
