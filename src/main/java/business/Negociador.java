package business;

import persistence.CFDNegociadorDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Negociador {
	private int nif;
	private String nome;
	private String email;
	private String password;
	private double saldo;
	private List<CFD> cfds;

    public Negociador(int nif, String nome, String email, String password, double saldo) {
    	this.nif = nif;
    	this.nome = nome;
    	this.email = email;
    	this.password = password;
    	this.saldo = saldo;
    	this.cfds = new CFDNegociadorDao(nif);
    }

	/**
	 * @param valor a adicionar/remover
	 * @return saldo após a atualização
	 */
	public double adicionarSaldo(double valor) {
		this.saldo += valor;
		return this.saldo;
	}

	/**
	 * @param nif nif do negociador
	 * @param password password do negociador
	 * @return se corresponde às credenciais que o negociador forneceu no momento de criação
	 */
	public boolean verificarCredenciais(int nif, String password) {
		return this.nif == nif && this.password.equals(password);
	}

	public int getNif() {
		return this.nif;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
    	return this.password;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo() {
		return this.saldo;
	}

	/**
	 * @param valor valor que se quer saber se o negociador pode dispender
	 * @return true se tem saldo suficiente, false se não
	 */
	public boolean podeGastar(double valor) {
    	return this.saldo >= valor;
	}

	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Nif: ").append(this.nif).append("\n");
    	sb.append("Nome: ").append(this.nome).append("\n");
		sb.append("Email: ").append(this.email).append("\n");
		sb.append("Password: ").append(this.password).append("\n");
		sb.append("Saldo: ").append(this.saldo).append("\n");
		return sb.toString();
	}

	/**
	 * @return Uma Lista com os CFDs do Negociador ainda abertos
	 */
	public List<CFD> getCFDsAbertos() {
    	List<CFD> result = new ArrayList<>();
    	for(int i = 0; i < this.cfds.size(); i++) {
			CFD c = this.cfds.get(i);
			if (c.isAberto())
				result.add(c);
		}
    	return result;
	}
}