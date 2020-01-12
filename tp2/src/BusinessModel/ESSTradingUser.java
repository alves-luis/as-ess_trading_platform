package BusinessModel;

import BusinessModel.Assets.Asset;
import BusinessModel.Trading.CFD;
import Services.Observer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ESSTradingUser {
    void addObserver(Observer o);
    void insertCredit(int userID, Double value);
    void takeCredit(int userID, Double value);
    List<CFD> getPortfolio(int userID);
    Map<Integer, Asset> getAssets();
    double getUserCredit(int userID);
    double getTotalInvestedByUser(int userID);
    List<Asset> getInvestorWatchList(int id);
    void removeItemFromWatchList(int userID, int assetId);
    Collection<Asset> getAssetsByType(String type);
    void addItemToWatchList(int userID, int assetID);
    boolean checkUserCredit(int userID, int assetID, double numberOfAssets, int positionType);
    void createCFD(int userID, int positionType, int assetID, double numberOfAssets, double tp, double sl);
    void closePosition(int userID, int cfdToClose);
    void reportBug(int idUser, String text);
    void stopThread();
}
