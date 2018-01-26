package DAO;

import POJO.Products;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.SQLException;
import java.util.List;

public interface ProductsDao {
    List<Products> getAll();
    Boolean add(String name, String manufacturer, String address, int count, float price);
    Boolean addObject(Products products);
    Products find(String name);
    void inXML() throws JAXBException;
    void outXML() throws JAXBException;

}
