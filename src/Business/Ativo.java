package Business;

import java.util.ArrayList;
import Business.Observer;

public abstract class Ativo {
	private int id;
	private String nome;
	private double valorPorUnidade;
	public ArrayList<Observer> observers = new ArrayList<Observer>();
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
}