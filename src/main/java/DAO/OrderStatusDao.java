package DAO;

import POJO.Buyer;
import POJO.OrderStatus;
import POJO.Products;

import java.sql.SQLException;
import java.util.List;

public interface OrderStatusDao {
    List<OrderStatus> getAll();
    Boolean setStatus(int id_order, String status);
    Buyer gerBuyer(int id_order);
    List<Products> getOrderProducts(int id_order);
}
