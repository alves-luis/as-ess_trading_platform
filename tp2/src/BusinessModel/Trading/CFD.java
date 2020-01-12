package BusinessModel.Trading;

import java.time.LocalDateTime;

public class CFD {

	private int id;
	private Double takeProfit;
	private Double stopLoss;
	private Double priceAcquisition;
	private Double quantity;
	private LocalDateTime date;
	private Position position;
	private int assetID;

	public CFD(int id, Double takeProfit, Double stopLoss, Double priceAcquisition, Double quantity, LocalDateTime date, Position position, int assetID) {
		this.id = id;
		this.takeProfit = takeProfit;
		this.stopLoss = stopLoss;
		this.priceAcquisition = priceAcquisition;
		this.quantity = quantity;
		this.date = date;
		this.position = position;
		this.assetID = assetID;
	}

	public CFD() {
	}

	public int getId() {
		return id;
	}

	public Double getPriceAcquisition() {
		return priceAcquisition;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getAssetID() {
		return assetID;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTP() {
		return takeProfit;
	}

	public Double getSL() {
		return stopLoss;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Position getPosition() {
		return position;
	}
}