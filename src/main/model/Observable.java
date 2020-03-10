package model;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        if (! observers.contains(o))  {
            observers.add(o);
        }
    }

    public void notifyObserver(Object o) {
        for (Observer ob: observers) {
            ob.update(o);
        }
    }
}
