package DAO;

import POJO.Order;
import POJO.Products;
import connections.ConnectionManager;
import connections.ConnectionManagerPostgresImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {
    Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerPostgresImpl.getInstance();

    @XmlRootElement(name = "orderList")
    public static class OrderList {

        private ArrayList<Order> orderList = new ArrayList<>();

        public void add(Order order) {
            orderList.add(order);
        }

        @XmlElement(name = "order")
        public List<Order> getOrder() {
            return orderList;
        }

        public void setOrdersList(ArrayList<Order> orderList) {
            this.orderList = orderList;
        }
    }


    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT ord.id, pr.name, pr.manufacturer, pr.address, pr.count as product_count, pr.price, ord.count as order_count, ord.id_product " +
                            " FROM public.order ord JOIN public.products pr ON pr.id=ord.id_product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Products products = new Products(
                        resultSet.getInt("id_product"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getString("address"),
                        resultSet.getInt("product_count"),
                        resultSet.getFloat("price"));
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_product"),
                        products,
                        resultSet.getInt("order_count"));
                orders.add(order);
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return orders;
    }

    @Override
    public Boolean add(int id, int id_product, int count) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO public.order (id, id_product, count) VALUES (?,?,?);");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id_product);
            preparedStatement.setInt(3, count);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public void inXML() {
        OrderDao orderDao = new OrderDaoImpl();
        File file = new File("src/com/company/XML/order.xml");
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(OrderList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OrderDaoImpl.OrderList orderList = new OrderDaoImpl.OrderList();
            for (Order order : orderDao.getAll()) {
                orderList.add(order);
            }
            marshaller.marshal(orderList, file);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }

    @Override
    public void outXML() {
        File file = new File("src/com/company/XML/order.xml");
        JAXBContext context2 = null;
        try {
            context2 = JAXBContext.newInstance(OrderList.class);
            Unmarshaller unmarshaller = context2.createUnmarshaller();
            OrderList orderList = (OrderList) unmarshaller.unmarshal(file);
            OrderDao orderDao = new OrderDaoImpl();
            for (Order order : orderList.getOrder()) {
                System.out.println(order);
                orderDao.add(order.getId(), order.getId_product(), order.getCount());
            }
        } catch (JAXBException e) {
            logger.error(e);
        }
    }

}
