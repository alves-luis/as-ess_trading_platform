package Business.Ativos;

import Business.IntrinioAPI;
import Business.Mercado;
import Business.Observer;
import Persistence.CFDAtivoDao;

import java.util.List;

public abstract class Ativo implements Runnable {
	private String id;
	private String nome;
	private double valorPorUnidade;
	private List<Observer> observers;
	private Mercado mercado;

	public Ativo(String id, String nome, double vpu) {
		this.id = id;
		this.nome = nome;
		this.valorPorUnidade = vpu;
		this.observers = new CFDAtivoDao(id);
		this.mercado = new IntrinioAPI();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setValorPorUnidade(double valorPorUnidade) {
		this.valorPorUnidade = valorPorUnidade;
	}

	public double getValorPorUnidade() {
		return this.valorPorUnidade;
	}

	public Mercado getMercado() {
		return this.mercado;
	}

	public List<Observer> getObservers() {
		return this.observers;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(id).append("\n");
		sb.append("Nome: ").append(nome).append("\n");
		sb.append("Valor por Unidade: ").append(valorPorUnidade).append("\n");
		return sb.toString();
	}

	public void setObservers(List<Observer> observers) {
		this.observers.clear();
		this.observers.addAll(observers);
	}
}