package DAO;

import POJO.Order;

import javax.xml.bind.JAXBException;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    List<Order> getAll();
    Boolean add(int id, int id_product,int count);
    void inXML();
    void outXML();
}
