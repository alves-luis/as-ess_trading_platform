package Business.Ativos;

public class Moeda extends Ativo {
	private String moedaA;
	private String moedaB;

	public Moeda(String id, String nome, double vpu) {
		super(id, nome, vpu);
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

	@Override
	public void run() {

	}
}