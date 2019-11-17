package Business;

public interface Observable {

	boolean registerObserver(Observer o);

	void notifyObservers();

	void removeObserver(Observer o);
}