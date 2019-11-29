package business;

import java.time.LocalDateTime;

public class Short extends CFD {

    private static double formula(double investimentoInicial, double valorCFD) {
        return Math.max(investimentoInicial * 2 - valorCFD, 0);
    }

    public Short(int id, LocalDateTime data, double unidadesDeCompra, double valor, Double limiteMin, Double limiteMax, String ativoId, int nifNegociador, boolean aberto) {
        super(id, data, unidadesDeCompra, valor, limiteMin, limiteMax, ativoId, nifNegociador, aberto);
    }

    public double getGanhoDoFecho() {
        if (this.isAberto()) // se CFD aberto, não valoriza
            return 0;
        double valorInicial = this.getValorInvestido();
        double valorFinal = this.getValorPorUnidadeFinal() * this.getUnidadesDeAtivo();
        return formula(valorInicial, valorFinal);
    }

    public double getValorCFD(double valorAtivo) {
        return formula(this.getValorInvestido(), this.getUnidadesDeAtivo() * valorAtivo);
    }
}