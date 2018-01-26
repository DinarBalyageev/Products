package POJO;

public class OrderStatus {
    private int id;
    private int idOrder;
    private Order order;
    private int idBuyer;
    private Buyer buyer;
    private String status;

    public OrderStatus(int id, int idOrder, int idBuyer, String status) {
        this.id = id;
        this.idOrder = idOrder;
        this.idBuyer = idBuyer;
        this.status = status;
    }

    public OrderStatus(int id, Order order, Buyer buyer, String status) {
        this.id = id;
        this.order = order;
        this.buyer = buyer;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(int idBuyer) {
        this.idBuyer = idBuyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", idBuyer=" + idBuyer +
                ", status='" + status + '\'' +
                '}';
    }
}
