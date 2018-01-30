package service;

import DAO.ProductsDao;
import DAO.ProductsDaoImpl;
import POJO.Products;

import java.util.ArrayList;
import java.util.List;

public class ServiceProducts {
    private ProductsDao productsDao = new ProductsDaoImpl();

    public List<Products> getAll(){
        List<Products> list = new ArrayList<>();
        for (Products products: productsDao.getAll()) {
            list.add(products);
        }
        return list;
    }

    public Products findId(int id){
        Products products = new Products();
        products = productsDao.findId(id);
        return products;
    }
}
