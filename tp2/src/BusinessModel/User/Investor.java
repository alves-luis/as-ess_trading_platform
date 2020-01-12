package BusinessModel.User;

import BusinessModel.Trading.*;
import java.util.*;
import Data.UserDAO;

public class Investor extends User {

	private Double credit;
	private Portfolio portfolio;
	public Investor(int id, String username, String email, String password, Portfolio p, Double credit) {
		super(id, username, email, password);
		portfolio = p;
		this.credit = credit;
	}

	public Investor() {
		super();
		this.credit = 0.0;
		this.portfolio = new Portfolio();
	}

	public Double getCredit(){
		return credit;
	}

	public List<Integer> getAllCFDids(){
		return portfolio.getCFDs();
	}

	public void removeWatchList(int idAsset){
		// TODO verificar isto porque pode partir (remove por Objeto ou index???)
		portfolio.removeItemWatchList(idAsset);
	}

	public void insertCredit(Double value){
		this.credit += value;
		(new UserDAO()).update(this);
	}

	public void takeCredit(Double value){
		this.credit -= value;
		(new UserDAO()).update(this);
	}

	public int getPortfolioId(){
		return this.portfolio.getId();
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void addToWatchList(int assetId){
		portfolio.addItemToWatchList(assetId);
	}
}
