package BusinessLayer;

import PresentationLayer.Observer;

import java.io.Serializable;
import java.util.List;

abstract class Observable implements Serializable {
    public abstract void registerObserver(Observer o);

    public abstract void unregisterObserver(Observer o);

    public abstract void notifyObservers(Order order, List<MenuItem> list);
}
