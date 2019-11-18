package Business.Ativos;

import Business.Observer;

import java.util.ArrayList;
import java.util.List;

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
	public void run() {
		// THIS NEEDS TO CHANGE
		double quot = 2;//this.getMercado().getCotacaoAcao(this.getId()); // isto pode ser strategy?

		List<Observer> observers = this.getObservers();
		if (quot != this.getValorPorUnidade()) {
			List<Observer> updatedObservers = new ArrayList<>();
			for (int i = 0; i < observers.size(); i++) {
				Observer o = observers.get(0);
				boolean keepObserving = o.update(quot);
				if (!keepObserving)
					updatedObservers.add(o);
			}
			this.setObservers(updatedObservers);
		}

		this.setValorPorUnidade(quot);
	}
}