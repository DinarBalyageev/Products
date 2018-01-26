package service;

import DAO.ProductsDao;
import DAO.ProductsDaoImpl;
import POJO.Products;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceProducts {

    public List<Products> getAll(){
        ProductsDao productsDao = new ProductsDaoImpl();
        List<Products> list = new ArrayList<>();
        for (Products products: productsDao.getAll()) {
            list.add(products);
        }
        return list;
    }
}
