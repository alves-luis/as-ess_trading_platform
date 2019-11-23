package business;

import java.time.LocalDateTime;

public class Short extends CFD {

    public Short(int id, LocalDateTime data, double unidadesDeCompra, double valor, Double limiteMin, Double limiteMax, String ativoId, int nifNegociador, boolean aberto) {
        super(id, data, unidadesDeCompra, valor, limiteMin, limiteMax, ativoId, nifNegociador, aberto);
    }

    @Override
    public boolean update(double valorAtivo) {
        throw new UnsupportedOperationException();
    }
}