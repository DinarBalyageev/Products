package service;

import DAO.BuyerDao;
import DAO.BuyerDaoImpl;
import POJO.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceBuyer {

    @Autowired
    private BuyerDao buyerDao;

    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        for (Buyer buyer : buyerDao.getAll()) {
            list.add(buyer.getName() + " " + buyer.getFirst_name() + " " + buyer.getLast_name() + " " + buyer.getAddress());
        }
        return list;
    }
}
