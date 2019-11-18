package Business;

public interface Mercado {

	double getCotacaoAcao(String identifier);
	double getCotacaoCommodity(String identifier);
	double getCotacaoIndice(String identifier);
	double getCotacaoMoeda(String identifier);
}