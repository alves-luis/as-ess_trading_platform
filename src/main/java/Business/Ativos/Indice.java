package Business.Ativos;

import Business.Ativos.Ativo;

public class Indice extends Ativo {
	private int numEmpresas;

	public void setNumEmpresas(int numEmpresas) {
		this.numEmpresas = numEmpresas;
	}

	public int getNumEmpresas() {
		return this.numEmpresas;
	}
}