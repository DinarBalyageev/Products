package DAO;

import POJO.Buyer;
import POJO.OrderStatus;
import POJO.Products;
import connections.ConnectionManager;
import connections.ConnectionManagerPostgresImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDaoImpl implements OrderStatusDao {
    final static Logger logger = Logger.getLogger(OrderStatusDaoImpl.class);
    private static ConnectionManager connectionManager
            = ConnectionManagerPostgresImpl.getInstance();

    @Override
    public List<OrderStatus> getAll() {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT ors.id_order as id_order_status, ors.id_buyer, ors.id_order,ors.status" +
                            " FROM public.orderstatus ors");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderStatus orderStatus = new OrderStatus(
                        resultSet.getInt("id_order_status"),
                        resultSet.getInt("id_buyer"),
                        resultSet.getInt("id_order"),
                        resultSet.getString("status")
                );
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return orderStatuses;
    }

    @Override
    public Boolean setStatus(int id_order, String status) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE public.orderstatus SET status=? where id_order=?");
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id_order);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public Buyer gerBuyer(int id_order) {
        Connection connection = connectionManager.getConnection();
        Buyer buyer = new Buyer();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT br.id, br.name,br.first_name, br.last_name, br.address" +
                            " FROM buyer br JOIN orderstatus ord ON (br.id = ord.id_buyer)" +
                            " AND (ord.id_order=?)"
            );
            preparedStatement.setInt(1, id_order);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                buyer.setId(resultSet.getInt("id"));
                buyer.setName(resultSet.getString("name"));
                buyer.setFirst_name(resultSet.getString("first_name"));
                buyer.setLast_name(resultSet.getString("last_name"));
                buyer.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return buyer;
    }

    @Override
    public List<Products> getOrderProducts(int id_order) {
        Connection connection = connectionManager.getConnection();
        List<Products> productsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT pr.id, pr.name, pr.manufacturer, pr.address, pr.price, pr.count" +
                            " FROM public.orderstatus os " +
                            " LEFT JOIN public.products pr ON os.id_order=?" +
                            " LEFT JOIN public.order ord ON ord.id_product = pr.id");
            preparedStatement.setInt(1, id_order);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Products products = new Products(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getString("address"),
                        resultSet.getInt("count"),
                        resultSet.getFloat("price")
                );
                productsList.add(products);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return productsList;
    }

    @Override
    public int add(int id_buyer, String status) {
        int id = 0;
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO public.orderstatus (id_buyer, status) VALUES (?,?) RETURNING id_order");
            preparedStatement.setInt(1, id_buyer);
            preparedStatement.setString(2, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id_order");
        } catch (SQLException e) {
            logger.error(e);
        }
        return id;
    }
}
