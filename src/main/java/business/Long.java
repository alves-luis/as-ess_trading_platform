package business;

import java.time.LocalDateTime;

public class Long extends CFD {

    public Long(int id, LocalDateTime data, double unidadesDeCompra, double valor, Double limiteMin, Double limiteMax,
                String idAtivo, int nifNegociador, boolean aberto) {

        super(id, data, unidadesDeCompra, valor, limiteMin, limiteMax, idAtivo, nifNegociador, aberto);
    }

    public Long(int id, LocalDateTime data, double unidadesDeCompra, double valor, Double limiteMin, Double limiteMax,
                String idAtivo, int nifNegociador, boolean aberto, double valorPorUnidadeFinal ) {

        super(id, data, unidadesDeCompra, valor, limiteMin, limiteMax, idAtivo, nifNegociador, aberto, valorPorUnidadeFinal);
    }


    /**
     * @param valorAtivo valor do ativo associado a este CFD
     * @return se deve continuar a ser atualizado
     */
    @Override
    public boolean update(double valorAtivo) {
        // se cfd fechado, retorna falso (nao atualizou)
        if (!this.isAberto())
            return false;

        double valorDoCFD = this.getUnidadesDeAtivo() * valorAtivo;
        Double takeprofit = this.getLimitSup();
        Double stoploss = this.getLimiteInf();

        boolean atualizou = false;
        if (takeprofit != null && valorDoCFD >= takeprofit) {
            this.fecharCFD(valorAtivo);
            atualizou = true;
        }
        if (stoploss != null && valorDoCFD <= stoploss) {
            this.fecharCFD(valorAtivo);
            atualizou = true;
        }

        return atualizou;
    }
}