package Business;

import java.util.Collection;
import java.util.Map;

public class TradingPlatformNegociador implements FacadeNegociador {
	public Map<Integer,Ativo> ativos;
	public Map<Integer,CFD> cfds;
	public Map<Integer,Negociador> negociadores;

	public boolean registarNegociador(int nif, String nome, String email, String password, double saldo) {
		throw new UnsupportedOperationException();
	}

	public Collection<Ativo> getAtivos() {
		throw new UnsupportedOperationException();
	}

	public Collection<Ativo> getAtivos(String tipo) {
		throw new UnsupportedOperationException();
	}

	public Ativo getAtivo(int id) {
		throw new UnsupportedOperationException();
	}

	public CFD registarCFD(int idAtivo, int nifNegociador, double unidadesDeCompra, Double limiteMin, Double limiteMax, String tipo) {
		throw new UnsupportedOperationException();
	}

	public double fecharCFD(int id) {
		throw new UnsupportedOperationException();
	}

	public Collection<CFD> getCFDs(int nifNegociador) {
		throw new UnsupportedOperationException();
	}

	public double atualizarSaldo(int nif, double quantia) {
		throw new UnsupportedOperationException();
	}
}