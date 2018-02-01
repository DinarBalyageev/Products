package DAO;

import POJO.Buyer;
import POJO.OrderStatus;
import POJO.Products;

import java.sql.SQLException;
import java.util.List;

public interface OrderStatusDao {
    List<OrderStatus> getAll();
    Boolean setStatus(int id_order, String status);
    Buyer getBuyer(int id_order);
    List<OrderStatus> getOrderProducts(int id_buyer);
    int add(int id_buyer, String status);
    List<OrderStatus> getFromId(int login);
}
