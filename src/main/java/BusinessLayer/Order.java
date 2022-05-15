package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order implements Serializable {
    private int orderID;
    private User client;
    private LocalDateTime orderDate;

    public Order(int orderID, User client, LocalDateTime orderDate)
    {
        this.orderID = orderID;
        this.client = client;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getHour() {
        return orderDate.getHour();
    }

    public int getDay() {
        return orderDate.getDayOfMonth();
    }

    public int getMonth() {
        return orderDate.getMonthValue();
    }

    public int getYear() {
        return orderDate.getYear();
    }

    @Override
    public String toString() {
        return "Order with id " + this.orderID + ", placed by the user " + this.client + ", on " + this.orderDate;
    }

    @Override
    public int hashCode()
    {
        int m = 11;
        return (orderID + client.hashCode() + orderDate.hashCode()) % m;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return orderID == order.orderID && client.equals(order.client);
    }
}
