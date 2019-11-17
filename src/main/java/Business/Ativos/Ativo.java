package Business.Ativos;

import Business.Mercado;
import Business.Observer;

import java.util.List;

public abstract class Ativo implements Runnable {
	private int id;
	private String nome;
	private double valorPorUnidade;
	public List<Observer> observers;
	public Mercado mercado;

	public String toString() {
		throw new UnsupportedOperationException();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
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

	public void run() {
		// TO DO
	}
}