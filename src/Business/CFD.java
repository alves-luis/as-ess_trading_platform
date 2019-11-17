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
}