package BusinessModel;

import BusinessModel.Report.Bug;
import BusinessModel.User.*;
import BusinessModel.Assets.*;
import BusinessModel.Trading.*;
import Data.BugDAO;
import Data.CFDdao;
import Services.BackgroundWorker;
import Services.Observer;

import java.time.LocalDateTime;
import java.util.*;

public class ESSTrading implements ESSTradingAdmin, ESSTradingUser {

    private UsersManager users;
    private AssetsManager assets;
    private Map<Integer, CFD> cfds;
    private List<Bug> bugs;
    private BackgroundWorker bw;

    public ESSTrading() {
        users = new UsersManager();
        assets = new AssetsManager();

        cfds = new HashMap<>();
        CFDdao cfddao = new CFDdao();
        for (CFD cfd : cfddao.getAll()) {
            cfds.put(cfd.getId(), cfd);
        }

        bugs = (new BugDAO()).getAll();

        bw = new BackgroundWorker();
        for (Asset a : this.assets.getAllAssets()) {
            bw.addObserver(a);
        }
        Thread t = new Thread(bw);
        t.start();
    }

    public List<Bug> getBugs() {
        return this.bugs;
    }

    public Map<Integer, Asset> getAssets() {
        return this.assets.getAssetsMap();
    }

    public int getUserID(String email) {
        return this.users.getUserID(email);
    }

    public List<CFD> getPortfolio(int userID) {
        List<CFD> cfdList = new ArrayList<>();
        for (int i : users.getInvestorCFDs(userID)) {
            if (cfds.get(i) != null) {
                cfdList.add(cfds.get(i)); //TODO VALIDATION added
            }
        }
        return cfdList;
    }

    // GET ASSETS
    public Collection<Asset> getAssetsByType(String type) {
        return this.assets.getAssetsByType(type);
    }

    // USER CREDIT
    public double getUserCredit(int userID) {
        return users.getInvestorCredit(userID);
    }

    public double getTotalInvestedByUser(int userID) {
        double ret = 0;
        for (Integer i : users.getInvestorCFDs(userID)) {
            CFD c = cfds.get(i);
            if (c != null) {
                ret += (c.getPriceAcquisition() * c.getQuantity());
            }
        }
        return ret;
    }

    public void insertCredit(int userID, Double value) {
        users.updateInvestorCredit(userID, value);
    }

    public void takeCredit(int userID, Double value) {
        users.updateInvestorCredit(userID, value);
    }

    public boolean checkUserCredit(int userID, int assetID, double numberOfAssets, int positionType) {
        boolean ret = false;
        double buyValue = assets.getAsset(assetID).getValue() * numberOfAssets;
        if (users.getInvestorCredit(userID) >= buyValue) {
            if (positionType == 1) {
                ret = true;
                users.updateInvestorCredit(userID, 0 - buyValue);
            } else {
                ret = checkSellPosition(userID, assetID);
            }
        }
        return ret;
    }

    // LOGIN REGISTRATION STUFF
    public User loginUser(String email, String password) {
        return users.validUserCredentials(email, password);
    }

    public boolean saveNewUser(String email, String password) {
        users.addInvestor(email, password);
        return true;
    }

    // CFD
    public void createCFD(int userID, int positionType, int assetID, double numberOfAssets, double tp, double sl) {
        int id = cfds.size() + 1;
        Position pos = getPositionType(positionType);
        double assetPrice = assets.getAsset(assetID).getValue();

        CFD cfd = new CFD(id, tp, sl, assetPrice, numberOfAssets, LocalDateTime.now(), pos, assetID);
        int portfolioID = users.getInvestorPortfolioID(userID);
        users.addCFDtoInvestorPortfolio(userID, id);
        openCFD(cfd, portfolioID);
    }

    private Position getPositionType(int input) {
        Position pos;
        if (input == 1) {
            pos = Position.LONG; // COMPRA
        } else {
            pos = Position.SHORT; // VENDA
        }
        return pos;
    }

    public void openCFD(CFD cfd, int portfolioId) {
        cfds.put(cfd.getId(), cfd);
        (new CFDdao()).saveToPortfolio(cfd, portfolioId);
    }

    // CLOSE POSITION //
    public boolean checkSellPosition(int userID, int assetID) {
        boolean ret = false;

        if (assets.getAsset(assetID).getValue() <= users.getInvestorCredit(userID)) {
            ret = true;
        }

        return ret;
    }

    public void closePosition(int userID, int cfdToClose) {
        CFD c = cfds.get(cfdToClose);
        double credit = 0;
        double aquisitionPrice = c.getPriceAcquisition() * c.getQuantity();

        if (c.getPosition().equals(Position.LONG)) {
            credit = aquisitionPrice - assets.getAsset(cfdToClose).getValue();
            insertCredit(userID, credit);
        } else {
            credit = assets.getAsset(cfdToClose).getValue() - aquisitionPrice;
            insertCredit(userID, credit);
        }
        closeCFD(c);
    }

    public void closeCFD(CFD cfd) {
        (new CFDdao()).delete(cfd);
        cfds.remove(cfd.getId());
    }

    // WATCHLIST //
    public List<Asset> getInvestorWatchList(int id) {
        List<Asset> watchList = new ArrayList<>();
        for (Integer i : users.getInvestorWatchList(id)) {
            if (assets.getAsset(i) != null) {
                watchList.add(assets.getAsset(i)); //TODO ADDED VALIDATION
            }
        }
        return watchList;
    }

    public void removeItemFromWatchList(int userID, int assetId) {
        users.removeAssetFromInvestorWatchList(userID, assetId);
    }

    public void addItemToWatchList(int userID, int assetID) {
        users.addAssetToInvestorWatchList(userID, assetID);
    }

    // BUG REPORT
    public void reportBug(int idUser, String text) {
        Bug b;
        bugs.add(b = (new Bug(bugs.size() + 1, text, LocalDateTime.now(), idUser)));
        (new BugDAO()).save(b);
    }

    // THREAD MANAGE //
    public void stopThread() {
        bw.stop();
    }

    public void addObserver(Observer o) {
		bw.addObserver(o);
	}
}
