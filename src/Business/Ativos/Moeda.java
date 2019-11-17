package Business.Ativos;

import Business.Ativos.Ativo;

public class Moeda extends Ativo {
	private String moedaA;
	private String moedaB;

	public void setMoedaA(String moedaA) {
		this.moedaA = moedaA;
	}

	public String getMoedaA() {
		return this.moedaA;
	}

	public void setMoedaB(String moedaB) {
		this.moedaB = moedaB;
	}

	public String getMoedaB() {
		return this.moedaB;
	}
}