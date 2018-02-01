package service;

import DAO.OrderStatusDao;
import DAO.OrderStatusDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;
import POJO.OrderStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class OrderService {

    public List<OrderStatus> getList(String login){
        OrderStatusDao orderStatusDao = new OrderStatusDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<OrderStatus> orderStatusList = orderStatusDao.getOrderProducts(userDao.getId(login));
        return orderStatusList;
    }
}
