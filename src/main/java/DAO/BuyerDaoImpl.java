package DAO;

import POJO.Buyer;
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
public class BuyerDaoImpl implements BuyerDao {
    final static Logger logger = Logger.getLogger(BuyerDaoImpl.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerPostgresImpl.getInstance();

    @XmlRootElement(name = "buyerList")
    public static class BuyerList {
        private ArrayList<Buyer> buyerList = new ArrayList<>();

        public void add(Buyer buyer) {
            buyerList.add(buyer);
        }

        @XmlElement(name = "buyer")
        public List<Buyer> getBuyer() {
            return buyerList;
        }

        public void setBuyerList(ArrayList<Buyer> buyerList) {
            this.buyerList = buyerList;
        }
    }

    @Override
    public List<Buyer> getAll() {
        List<Buyer> buyers = new ArrayList<>();
        Buyer buyer;
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT id, name, first_name, last_name, address FROM public.Buyer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                buyer = new Buyer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address")
                );
                buyers.add(buyer);
            }
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }

        return buyers;
    }

    public Boolean addObject(Buyer buyer) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.buyer (id, name, first_name, last_name, address) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, buyer.getId());
            preparedStatement.setString(2, buyer.getName());
            preparedStatement.setString(3, buyer.getFirst_name());
            preparedStatement.setString(4, buyer.getLast_name());
            preparedStatement.setString(5, buyer.getAddress());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public int add(String name, String first_name, String last_name, String address) {
        int id = 0;
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.buyer (name, first_name, last_name, address) VALUES (?,?,?,?) RETURNING id");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.setString(4, address);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id");
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return id;
    }

    @Override
    public void inXML() {
        BuyerDao buyerDao = new BuyerDaoImpl();
        File file = new File("src/com/company/XML/buyer.xml");
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(BuyerList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            BuyerDaoImpl.BuyerList buyerList = new BuyerDaoImpl.BuyerList();
            for (Buyer buyer : buyerDao.getAll()) {
                buyerList.add(buyer);
            }
            marshaller.marshal(buyerList, file);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }

    @Override
    public void outXML() {
        File file = new File("src/com/company/XML/buyer.xml");
        JAXBContext context2 = null;
        try {
            context2 = JAXBContext.newInstance(BuyerList.class);
            Unmarshaller unmarshaller = context2.createUnmarshaller();
            BuyerList buyerList = (BuyerList) unmarshaller.unmarshal(file);
            BuyerDao buyerDao = new BuyerDaoImpl();
            for (Buyer buyer : buyerList.getBuyer()) {
                System.out.println(buyer);
                buyerDao.addObject(buyer);
            }
        } catch (JAXBException e) {
            logger.error(e);
        }
    }

}
