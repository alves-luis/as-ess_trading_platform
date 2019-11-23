package Business.Ativos;

public class Acao extends Ativo {
	private String empresa;

	public Acao(String id, String nome, double vpu) {
		super(id, nome, vpu);
	}

	public Acao(String id, String nome, double vpu, String empresa) {
		super(id,nome,vpu);
		this.empresa = empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	@Override
	public double getValorPorUnidadeMaisRecente() {
		return this.getMercado().getCotacaoAcao(this.getId());
	}
}