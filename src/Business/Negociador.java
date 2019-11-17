package Business;

import java.util.List;

public class Negociador {
	private int nif;
	private String nome;
	private String email;
	private String password;
	private double saldo;
	public List<CFD> cfds;

	public double adicionarSaldo(double valor) {
		throw new UnsupportedOperationException();
	}

	public boolean verificarCredenciais(String email, String password) {
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

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo() {
		return this.saldo;
	}
}