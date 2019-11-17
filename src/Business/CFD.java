package Business;

import java.time.LocalDateTime;

public abstract class CFD {
	private int id;
	private LocalDateTime data;
	private double unidadesDeAtivo;
	private double valorPorUnidadeNaCompra;
	private Double limitSup;
	private Double limiteInf;
	private int idAtivo;
	private int nifNegociador;
	private boolean aberto;

	public CFD(int id, double unidadesDeCompra, double valor, Double limiteMin, Double limiteMax, int ativoId, int nifNegociador) {
		this.id = id;
		this.data = LocalDateTime.now();
		this.unidadesDeAtivo = unidadesDeCompra;
		this.valorPorUnidadeNaCompra = valor;
		this.limiteInf = limiteMin;
		this.limitSup = limiteMax;
		this.idAtivo = ativoId;
		this.nifNegociador = nifNegociador;
		this.aberto = true;
	}

	public double getValorInvestido() {
		throw new UnsupportedOperationException();
	}

	public void setTakeProfit(double max) {
		throw new UnsupportedOperationException();
	}

	public void setStopLoss(double min) {
		throw new UnsupportedOperationException();
	}

	public int getId() {
		return this.id;
	}

	public LocalDateTime getData() {
		return this.data;
	}

	public double getUnidadesDeAtivo() {
		return this.unidadesDeAtivo;
	}

	public double getValorPorUnidadeNaCompra() {
		return this.valorPorUnidadeNaCompra;
	}

	public Double getLimitSup() {
		return this.limitSup;
	}

	public Double getLimiteInf() {
		return this.limiteInf;
	}

	public int getIdAtivo() {
		return this.idAtivo;
	}

	public int getNifNegociador() {
		return this.nifNegociador;
	}

	/**
	 * @return True se o CFD se encontrava aberto e o seu estado foi alterado para fechado. Falso em todos
	 * os restantes casos
	 */
	public boolean fecharCFD() {
		if (this.aberto) {
			this.aberto = false;
			return true;
		}
		else
			return false;
	}
}