package business;

import java.time.LocalDateTime;

public class Short extends CFD {

    public Short(int id, LocalDateTime data, double unidadesDeCompra, double valor, Double limiteMin, Double limiteMax, String ativoId, int nifNegociador, boolean aberto) {
        super(id, data, unidadesDeCompra, valor, limiteMin, limiteMax, ativoId, nifNegociador, aberto);
    }

    @Override
    public boolean update(double valorAtivo) {
        if (!this.isAberto()) // se CFD fechado, não atualizou
            return false;
        double valorCFD = this.getUnidadesDeAtivo() * valorAtivo;
        Double takeprofit = this.getLimitSup();
        Double stoploss = this.getLimiteInf();

        boolean atualizou = false;
        if (takeprofit != null && valorCFD <= takeprofit) {
            this.fecharCFD(valorAtivo);
            atualizou = true;
        }
        if (stoploss != null && valorCFD >= stoploss) {
            this.fecharCFD(valorAtivo);
            atualizou = true;
        }

        return atualizou;
    }
}