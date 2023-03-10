package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;

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
        return "Order with id " + this.orderID + ", placed by the user " + this.client.getUsername() + ", on " + this.orderDate;
    }

    @Override
    public int hashCode() {
        return (orderDate.getSecond() + orderDate.getMinute() * 60 + orderDate.getHour() * 3600) % 10;
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
