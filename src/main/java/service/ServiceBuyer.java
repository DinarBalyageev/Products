package service;

import DAO.BuyerDao;
import DAO.BuyerDaoImpl;
import POJO.Buyer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBuyer {

    public List<String> getAll() throws SQLException {
        BuyerDao buyerDao = new BuyerDaoImpl();
        List<String> list = new ArrayList<>();
        for (Buyer buyer : buyerDao.getAll()) {
            list.add(buyer.getName() + " " + buyer.getFirst_name() + " " + buyer.getLast_name() + " " + buyer.getAddress());
        }
        return list;
    }
}
