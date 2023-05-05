import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MyObservable {
    private List<Observer> observers = new ArrayList<>();

    public void notifyObservers(Observable o, Object arg) {
        for (Observer observer : observers) {
            observer.update(o,arg);
        }
    }

    public void add(Observer observer) {
        this.observers.add(observer);
    }
}
