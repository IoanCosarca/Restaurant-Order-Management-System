package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private LocalDateTime orderDate;

    public Order(int orderID, int clientID, LocalDateTime orderDate)
    {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
