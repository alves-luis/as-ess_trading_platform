package Business;

import Business.Ativos.Ativo;

public interface AtivoFactory {
	Ativo getAtivo(String tipo);
}