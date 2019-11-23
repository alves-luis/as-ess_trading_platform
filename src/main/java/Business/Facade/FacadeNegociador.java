package Business.Facade;

import Business.Ativos.Ativo;
import Business.CFD;
import Business.Exceptions.CFDNaoExisteException;
import Business.Exceptions.NegociadorNaoExisteException;
import Business.Exceptions.NegociadorNaoPossuiSaldoSuficienteException;

import java.util.List;

public interface FacadeNegociador {
    boolean registarNegociador(int nif, String nome, String email, String password, double saldo);

    List<Ativo> getAtivos();

    List<Ativo> getAtivos(String tipo);

    Ativo getAtivo(String id);

    CFD registarCFD(String idAtivo, int nifNegociador, double unidadesDeCompra, Double limiteMin, Double limiteMax, String tipo) throws NegociadorNaoExisteException, NegociadorNaoPossuiSaldoSuficienteException;

    double fecharCFD(int id) throws CFDNaoExisteException;

    /**
     * @param nifNegociador
     * @return Lista dos CFDs abertos do negociador
     */
    List<CFD> getCFDs(int nifNegociador);

    double atualizarSaldo(int nif, double quantia);

    boolean verificarCredenciais(int nif, String pass);

    double getSaldo(int nif);

    double getValorAtualCFD(int idCFD);
}
