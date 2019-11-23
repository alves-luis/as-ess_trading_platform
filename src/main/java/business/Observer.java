package business;

public interface Observer {

	boolean update(double valorAtivo); // return true se deve continuar a ser observador
}