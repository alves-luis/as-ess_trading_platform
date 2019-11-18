package Business;

public interface Observer {

	boolean update(double valorAtivo); // return true se deve continuar a ser observador
	Double getFinal(); // return valor final (ou null caso ainda esteja a ser observador)
}