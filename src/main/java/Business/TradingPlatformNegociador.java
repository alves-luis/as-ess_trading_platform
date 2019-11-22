package Business;

import Business.Ativos.Acao;
import Business.Ativos.Ativo;
import Business.Exceptions.CFDNaoExisteException;
import Business.Exceptions.NegociadorNaoExisteException;
import Business.Exceptions.NegociadorNaoPossuiSaldoSuficienteException;
import Persistence.AtivoDAO;
import Persistence.CFDDao;
import Persistence.NegociadorDAO;

import java.time.LocalDateTime;
import java.util.*;

public class TradingPlatformNegociador implements FacadeNegociador {
	private Map<String, Ativo> ativos;
	private Map<Integer, CFD> cfds;
	private Map<Integer, Negociador> negociadores;

	public TradingPlatformNegociador() {
		this.ativos = new AtivoDAO();
		this.cfds = new CFDDao();
		this.negociadores = new NegociadorDAO();
	}

	/**
	 * @param nif Nif do negociador
	 * @param nome Nome do negociador
	 * @param email Email do negociador
	 * @param password Password do negociador
	 * @param saldo Saldo inicial do negociador
	 * @return True se foi possível registar o negociador, false se não
	 */
	public boolean registarNegociador(int nif, String nome, String email, String password, double saldo) {
		Negociador n = new Negociador(nif, nome, email, password, saldo);
		if (this.negociadores.containsKey(nif))
			return false;
		this.negociadores.put(nif, n);
		return true;
	}

	/**
	 * @return Todos os ativos disponíveis no sistema
	 */
	public List<Ativo> getAtivos() {
		return new ArrayList<>(this.ativos.values());
	}

	public List<Ativo> getAtivos(String tipo) {
		return new ArrayList(Arrays.asList(new Acao("1","EDP",10, "Eletricidade de Portugal"), new Acao("2","GALP",5, "Galp Energia")));
		//throw new UnsupportedOperationException();
	}

	/**
	 * @param id Id do ativo
	 * @return O Ativo pretendido, ou null se não existir
	 */
	public Ativo getAtivo(String id) {
		return this.ativos.get(id);
	}

	/**
	 * @param idAtivo id do ativo ao qual deve estar associado o CFD
	 * @param nifNegociador nif de quem estabelece o CFD
	 * @param unidadesDeCompra numero de unidades do ativo que pretende adquirir
	 * @param limiteMin stoploss, pode ser null
	 * @param limiteMax maxprofit, pode ser null
	 * @param tipo Long ou short
	 * @return o CFD registado
	 * @throws NegociadorNaoExisteException caso se tente registar um CFD de um negociador que não está
	 * registado na plataforma
	 * @throws NegociadorNaoPossuiSaldoSuficienteException caso se tente estabelecer um CFD mas o negociador
	 * não possui saldo suficiente
	 */
	public CFD registarCFD(String idAtivo, int nifNegociador, double unidadesDeCompra, Double limiteMin, Double limiteMax, String tipo)
			throws NegociadorNaoExisteException, NegociadorNaoPossuiSaldoSuficienteException {

		if (!this.negociadores.containsKey(nifNegociador))
			throw new NegociadorNaoExisteException(nifNegociador);

		Ativo ativo = this.ativos.get(idAtivo);
		Negociador n = this.negociadores.get(nifNegociador);
		double investimento = unidadesDeCompra * ativo.getValorPorUnidade();

		if (!n.podeGastar(investimento))
			throw new NegociadorNaoPossuiSaldoSuficienteException(investimento, nifNegociador);

		int id = cfds.size();
		// by default creating long positions
		CFD c = new Long(id, LocalDateTime.now(), unidadesDeCompra, ativo.getValorPorUnidade(),limiteMin, limiteMax, ativo.getId(), nifNegociador, true);
		this.cfds.put(c.getId(),c);
		return c;
	}

	/**
	 * @param id do CFD
	 * @return o valor de fecho do CFD, isto é, quanto recebeu o negociador na sua carteira
	 * @throws CFDNaoExisteException caso se tente encerrar um CFD não existente
	 */
	public double fecharCFD(int id) throws CFDNaoExisteException {
		if (!this.cfds.containsKey(id))
			throw new CFDNaoExisteException(id);


		CFD c = this.cfds.get(id);
		Ativo a = this.ativos.get(c.getIdAtivo());
		c.fecharCFD(a.getValorPorUnidade());
		this.cfds.put(id,c); // to update state


		double valorAtivo = a.getValorPorUnidade();
		double saldoAAdicionar = valorAtivo * c.getUnidadesDeAtivo();

		this.atualizarSaldo(c.getNifNegociador(), saldoAAdicionar);

		return saldoAAdicionar;

	}

	public List<CFD> getCFDs(int nifNegociador) {
		return new ArrayList<CFD>(Arrays.asList(new Short(1, LocalDateTime.now(), 10, 10, null, 10.0, "APPL", 274129914, true)));
		//throw new UnsupportedOperationException();
	}

	public double atualizarSaldo(int nif, double quantia) {
		throw new UnsupportedOperationException();
	}

	public boolean verificarCredenciais(int nif, String password) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double getSaldo(int nif) {
		return 0;
	}

	public double getValorAtualCFD(int idCFD) {
		return 30;
	}

}