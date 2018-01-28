package service;

import POJO.Products;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceProductsTest {

    @Test
    public void getAll() {
        ServiceProducts serviceProducts = new ServiceProducts();
        for (Products products : serviceProducts.getAll()) {
            System.out.println(products);
        }
    }
}