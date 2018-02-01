package DAO;

import java.sql.SQLException;

public interface UserDao {
    Boolean get(String login, String password);
    Boolean add(int id, String login, String password);
    Boolean findLogin(String login);
    int getId(String login);
}
