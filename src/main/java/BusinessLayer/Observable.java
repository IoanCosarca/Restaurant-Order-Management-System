package BusinessLayer;

import PresentationLayer.Observer;

import java.util.List;

abstract class Observable {
    abstract void addObserver(Observer o);

    abstract void deleteObserver(Observer o);

    abstract void notifyObservers(Order order, List<MenuItem> list);
}
