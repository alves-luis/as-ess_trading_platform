package business.ativos;

public class Acao extends Ativo {

	private String empresa;

	public Acao(String id, String nome, double vpu, String empresa) {
		super(id,nome,vpu);
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	@Override
	public double getValorPorUnidadeMaisRecente() {
		double val = this.getMercado().getCotacaoAcao(this.getId());
		this.setValorPorUnidade(val);
		return val;
	}

	public String toString() {
		String s = super.toString();
		return s + "Empresa: " + this.empresa;
	}
}