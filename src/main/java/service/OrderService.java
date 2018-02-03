package service;

import DAO.OrderDao;
import DAO.OrderDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public void add(int idOrder, int idProducts, int count){
        orderDao.add(idOrder,idProducts,count);
    }
}
