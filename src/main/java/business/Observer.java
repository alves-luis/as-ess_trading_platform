package business;

public interface Observer {

	boolean update(double valorAtivo, String idAtivo); // return true se atualizou
}