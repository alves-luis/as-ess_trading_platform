package business;

import java.util.List;

public interface Observable {

	boolean registerObserver(Observer o);

	void notifyObservers();

	void removeObserver(Observer o);

	void setObservers(List<Observer> l);
}