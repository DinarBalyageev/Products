package DAO;

import POJO.Products;
import connections.ConnectionManager;
import connections.ConnectionManagerPostgresImpl;
import org.apache.log4j.Logger;

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

public class ProductsDaoImpl implements ProductsDao {
    final static Logger logger = Logger.getLogger(ProductsDaoImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerPostgresImpl.getInstance();

    @XmlRootElement(name = "productsList")
    public static class ProductsList {
        private ArrayList<Products> productsList = new ArrayList<>();

        public void add(Products products) {
            productsList.add(products);
        }

        @XmlElement(name = "products")
        public List<Products> getProducts() {
            return productsList;
        }

        public void setProductsList(ArrayList<Products> productsList) {
            this.productsList = productsList;
        }
    }


    @Override
    public List<Products> getAll() {
        Connection connection = connectionManager.getConnection();
        List<Products> productsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT id, name, manufacturer, address, price, count FROM public.products");
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
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return productsList;
    }

    @Override
    public Boolean add(String name, String manufacturer, String address, int count, float price) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO public.products (name, manufacturer, address, count, price VALUES (?,?,?,?,?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, manufacturer);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, count);
            preparedStatement.setFloat(5, price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public Boolean addObject(Products products) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO public.products (id,name, manufacturer, address, count, price) VALUES (?,?,?,?,?,?);");
            preparedStatement.setInt(1, products.getId());
            preparedStatement.setString(2, products.getName());
            preparedStatement.setString(3, products.getManufacturer());
            preparedStatement.setString(4, products.getAddress());
            preparedStatement.setInt(5, products.getCount());
            preparedStatement.setFloat(6, products.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public Products find(String name) {
        Connection connection = connectionManager.getConnection();
        Products products = new Products();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT id, name, manufacturer, address, price, count " +
                                    "FROM public.products WHERE name ~*?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.setId(resultSet.getInt("id"));
                products.setName(resultSet.getString("name"));
                products.setManufacturer(resultSet.getString("manufacturer"));
                products.setAddress(resultSet.getString("address"));
                products.setCount(resultSet.getInt("count"));
                products.setPrice(resultSet.getFloat("price"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return products;
    }

    @Override
    public Products findId(int id) {
        Connection connection = connectionManager.getConnection();
        Products products = new Products();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT id, name, manufacturer, address, price, count " +
                            "FROM public.products WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.setId(resultSet.getInt("id"));
                products.setName(resultSet.getString("name"));
                products.setManufacturer(resultSet.getString("manufacturer"));
                products.setAddress(resultSet.getString("address"));
                products.setCount(resultSet.getInt("count"));
                products.setPrice(resultSet.getFloat("price"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return products;
    }


    public void inXML() throws JAXBException {
        ProductsDao productsDao = new ProductsDaoImpl();
        File file = new File("src/com/company/XML/products.xml");
        JAXBContext context = JAXBContext.newInstance(ProductsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ProductsList productsList = new ProductsList();
        for (Products products : productsDao.getAll()) {
            productsList.add(products);
        }
        marshaller.marshal(productsList, file);
    }

    @Override
    public void outXML() throws JAXBException{
        File file = new File("src/com/company/XML/products.xml");
        JAXBContext context2 = JAXBContext.newInstance(ProductsList.class);
        Unmarshaller unmarshaller = context2.createUnmarshaller();
        ProductsList productsList = (ProductsList) unmarshaller.unmarshal(file);
        ProductsDao productsDao = new ProductsDaoImpl();
        for (Products products : productsList.getProducts()) {
            System.out.println(productsList);
            productsDao.addObject(products);
        }
    }


}
