package service;

import DAO.UserDao;
import DAO.UserDaoImpl;

public class RegistrationService {
    private UserDao userDao = new UserDaoImpl();
    private Boolean exit = false;

    public boolean identification(String login){
        if (userDao.findLogin(login)==true) exit = true;
        return exit;
    }


    public boolean registration(String login, String password){

       if (userDao.add(login,password)) exit = true;

       return exit;
    }
}
