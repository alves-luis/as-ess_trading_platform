package business;

import persistence.CFDNegociadorDao;

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
    	this.cfds = new CFDNegociadorDao();
    }

	public Negociador() {

	}

	public double adicionarSaldo(double valor) {
		throw new UnsupportedOperationException();
	}

	public boolean verificarCredenciais(int nif, String password) {
		throw new UnsupportedOperationException();
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

	public boolean podeGastar(double valor) {
    	throw new UnsupportedOperationException();
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
}