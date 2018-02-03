package service;

import DAO.BuyerDao;
import DAO.BuyerDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BuyerDao buyerDao;

    public boolean identification(String login) {
        return userDao.findLogin(login);
    }


    public boolean registration(String login, String password, String firstname, String name, String lastname, String address) {
        int idUser = buyerDao.add(name, firstname, lastname, address);
        return userDao.add(idUser,login, password);
    }
}
