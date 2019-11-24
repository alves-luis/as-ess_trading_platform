package business.ativos;

public class Moeda extends Ativo {
	private String moedaA;
	private String moedaB;

	public Moeda(String id, String nome, double vpu) {
		super(id, nome, vpu);
	}

	public Moeda(String id, String nome, double vpu, String moedaA, String moedaB) {
		super(id, nome, vpu);
		this.moedaA = moedaA;
		this.moedaB = moedaB;
	}

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

	public double getValorPorUnidadeMaisRecente() {
		double val = this.getMercado().getCotacaoMoeda(this.getId());
		this.setValorPorUnidade(val);
		return val;
	}

	public String toString() {
		String s = super.toString();
		return s + "MoedaA: " + this.moedaA + "\nMoedaB: " + this.moedaB;
	}
}