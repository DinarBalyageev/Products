package service;

import DAO.BuyerDao;
import DAO.BuyerDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;

public class RegistrationService {
    private UserDao userDao = new UserDaoImpl();
    private BuyerDao buyerDao = new BuyerDaoImpl();
    private Boolean exit = false;

    public boolean identification(String login) {
        if (userDao.findLogin(login) == true) exit = true;
        return exit;
    }


    public boolean registration(String login, String password, String firstname, String name, String lastname, String address) {
        int idUser = buyerDao.add(name, firstname, lastname, address);
        userDao.add(idUser,login, password);
        exit = true;
        return exit;
    }
}
