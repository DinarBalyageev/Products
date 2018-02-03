package service;

import DAO.ProductsDao;
import DAO.ProductsDaoImpl;
import POJO.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProducts {

    @Autowired
    private ProductsDao productsDao;

    public List<Products> getAll(){
        return productsDao.getAll();
    }

    public Products findId(int id){
        return productsDao.findId(id);
    }
}
