package service;

import DAO.OrderStatusDao;
import DAO.OrderStatusDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;
import POJO.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusDao orderStatusDao;

    @Autowired
    private UserDao userDao;

    public List<OrderStatus> getList(String login){
        return orderStatusDao.getOrderProducts(userDao.getId(login));
    }

    public int add(int userId){
       return orderStatusDao.add(userId, "new");
    }

}
