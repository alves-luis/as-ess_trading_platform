package Business;

import Business.Ativos.Ativo;
import Business.Exceptions.CFDNaoExisteException;
import Business.Exceptions.NegociadorNaoExisteException;
import Business.Exceptions.NegociadorNaoPossuiSaldoSuficienteException;

import java.util.Collection;

public interface FacadeNegociador {
    boolean registarNegociador(int nif, String nome, String email, String password, double saldo);

    Collection<Ativo> getAtivos();

    Collection<Ativo> getAtivos(String tipo);

    Ativo getAtivo(String id);

    CFD registarCFD(String idAtivo, int nifNegociador, double unidadesDeCompra, Double limiteMin, Double limiteMax, String tipo) throws NegociadorNaoExisteException, NegociadorNaoPossuiSaldoSuficienteException;

    double fecharCFD(int id) throws CFDNaoExisteException;

    Collection<CFD> getCFDs(int nifNegociador);

    double atualizarSaldo(int nif, double quantia);

    boolean verificarCredenciais(int nif, String pass);
}
