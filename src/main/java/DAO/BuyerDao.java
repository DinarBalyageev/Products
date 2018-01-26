package DAO;

import POJO.Buyer;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.SQLException;
import java.util.List;

public interface BuyerDao {
    List<Buyer> getAll();
    Boolean add(String name, String first_name, String last_name, String address);
    void inXML();
    void outXML();
    Boolean addObject(Buyer buyer);
}
