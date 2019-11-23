package business;

import java.util.Collection;

public interface Observable {

	boolean registerObserver(Observer o);

	void notifyObservers();

	void removeObserver(Observer o);

	void setObservers(Collection<Observer> l);
}