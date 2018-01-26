package POJO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
    private int id;
    private int id_product;
    private Products products;
    private int count;

    public Order(int id, int id_product, int count) {
        this.id = id;
        this.id_product = id_product;
        this.count = count;
    }

    public Order(int id, Products products, int count) {
        this.id = id;
        this.products = products;
        this.count = count;
    }

    public Order(int id, int id_product, Products products, int count) {
        this.id = id;
        this.id_product = id_product;
        this.products = products;
        this.count = count;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", id_product=" + id_product +
                ", products=" + products +
                ", count=" + count +
                '}';
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public int getId_product() {
        return id_product;
    }

    @XmlElement
    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getCount() {
        return count;
    }

    @XmlElement
    public void setCount(int count) {
        this.count = count;
    }
}
