package service;

import DAO.UserDao;
import DAO.UserDaoImpl;

import java.sql.SQLException;

public class LoginService {
    private static UserDao userDao = new UserDaoImpl();

    public boolean autorization(String login,String password) {

        if (userDao.get(login,password)==true) return true; else return false;
    }

}
