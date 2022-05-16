package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.util.List;

public interface Observer {
    void update(Order order, List<MenuItem> menuItems);
}
