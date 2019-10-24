package Business;

import Data.*;

import java.util.Collection;
import java.util.Map;

public class ESS_Trading_Platform {

	private int id;
	private Map<Integer,Negociador> negociadores;
	private Map<Integer,Ativo> ativos;
	private Map<Integer,CFD> cfds;
	private Notificador notificador;

	/**
	 * 
	 * @param nif
	 * @param nome
	 * @param email
	 * @param password
	 */
	public boolean registerNegociador(int nif, String nome, String email, String password) {
		// TODO - implement ESS_Trading_Platform.registerNegociador
		throw new UnsupportedOperationException();
	}

	public Collection<Ativo> getAtivos() {
		// TODO - implement ESS_Trading_Platform.getAtivos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public double getValorAtivo(int id) {
		// TODO - implement ESS_Trading_Platform.getValorAtivo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idA
	 * @param valor
	 * @param tipo
	 * @param nif
	 * @param limiteMax
	 * @param limiteMin
	 */
	public CFD registerCFD(int idA, double valor, String tipo, int nif, Double limiteMax, Double limiteMin) {
		// TODO - implement ESS_Trading_Platform.registerCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param nif
	 */
	public double closeCFD(int id, int nif) {
		// TODO - implement ESS_Trading_Platform.closeCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nif
	 */
	public Collection<CFD> getCFDs(int nif) {
		// TODO - implement ESS_Trading_Platform.getCFDs
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public CFD getCFD(int id) {
		// TODO - implement ESS_Trading_Platform.getCFD
		throw new UnsupportedOperationException();
	}

	public int generateId() {
		// TODO - implement ESS_Trading_Platform.generateId
		throw new UnsupportedOperationException();
	}

	public double updatePlafond(int nif, double qt) {
		// TODO - implement ESS_Trading_Platform.updatePlafond
		throw new UnsupportedOperationException();
	}

}