package BusinessLayer;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String type;
    private int OrdersPlaced;

    public User(String username, String password, String type)
    {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrdersPlaced() {
        return OrdersPlaced;
    }

    public void setOrdersPlaced() {
        OrdersPlaced = 0;
    }

    public void incOrdersPlaced() {
        OrdersPlaced++;
    }
}
