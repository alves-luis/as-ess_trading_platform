package Business;

import java.util.Collection;

public interface FacadeNegociador {
    boolean registarNegociador(int nif, String nome, String email, String password, double saldo);

    Collection<Ativo> getAtivos();

    Collection<Ativo> getAtivos(String tipo);

    Ativo getAtivo(int id);

    CFD registarCFD(int idAtivo, int nifNegociador, double unidadesDeCompra, Double limiteMin, Double limiteMax, String tipo);

    double fecharCFD(int id);

    Collection<CFD> getCFDs(int nifNegociador);

    double atualizarSaldo(int nif, double quantia);
}
