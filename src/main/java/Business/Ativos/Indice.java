package Business.Ativos;

public class Indice extends Ativo {
	private int numEmpresas;

	public Indice(String id, String nome, double vpu) {
		super(id, nome, vpu);
	}

	public void setNumEmpresas(int numEmpresas) {
		this.numEmpresas = numEmpresas;
	}

	public int getNumEmpresas() {
		return this.numEmpresas;
	}

	@Override
	public void run() {

	}
}