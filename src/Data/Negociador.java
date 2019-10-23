package Data;

import java.util.Collection;

public class Negociador {

	private int nif;
	private String nome;
	private String email;
	private String password;
	private double plafond;
	private Collection<CFD> cfds;

	/**
	 * 
	 * @param cfd
	 */
	public boolean newCFD(CFD cfd) {
		// TODO - implement Negociador.newCFD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public boolean closeCFD(int id) {
		// TODO - implement Negociador.closeCFD
		throw new UnsupportedOperationException();
	}

	public Collection<CFD> getCFDs() {
		// TODO - implement Negociador.getCFDs
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param money
	 */
	public double addPlafond(double money) {
		// TODO - implement Negociador.addPlafond
		throw new UnsupportedOperationException();
	}

}