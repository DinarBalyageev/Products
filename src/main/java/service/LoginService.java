package service;

import DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    public UserDao userDao;

    public boolean authorization(String login, String password) {
        return userDao.get(login,password);
    }

    public int getId(String login){
        return userDao.getId(login);
    }

}
