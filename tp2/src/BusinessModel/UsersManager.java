package BusinessModel;

import BusinessModel.Trading.Portfolio;
import BusinessModel.User.Investor;
import BusinessModel.User.User;
import Data.UserDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersManager {

    private Map<Integer, User> users;
    private Map<String, User> usersByEmail;

    public UsersManager() {
        this.users = new HashMap<>();
        this.usersByEmail = new HashMap<>();
        for (User user : (new UserDAO()).getAll()) {
            this.users.put(user.getId(), user);
            this.usersByEmail.put(user.getEmail(), user);
        }
    }

    public int getUserID(String email) {
        User u = usersByEmail.get(email);
        if (u == null)
            return -1;
        else
            return u.getId();
    }

    public User getUser(int id) {
        return this.users.get(id);
    }

    private Investor getInvestor(int id) throws NoInvestorWithIDException {
        User u = users.get(id);
        if (u instanceof Investor)
            return (Investor) u;
        else
            throw new NoInvestorWithIDException();
    }

    public List<Integer> getInvestorCFDs(int id) {
        try {
            return getInvestor(id).getAllCFDids();
        } catch (NoInvestorWithIDException e) {
            return new ArrayList<>();
        }
    }

    public double getInvestorCredit(int id) {
        try {
            return getInvestor(id).getCredit();
        } catch (NoInvestorWithIDException e) {
            return 0;
        }
    }

    public void updateInvestorCredit(int id, double value) {
        try {
            Investor i = getInvestor(id);
            if (value > 0)
                i.insertCredit(value);
            else
                i.takeCredit(value);
        } catch (NoInvestorWithIDException ignored) {
        }
    }

    public User validUserCredentials(String email, String password) {
        User u = usersByEmail.get(email);
        if (u != null && u.getPassword().equals(password))
            return u;
        return null;
    }

    public void addInvestor(String email, String password) {
        int id = users.size() + 1;
        Investor i = new Investor(id, email, email, password, new Portfolio(), 0.0);
        users.put(id, i);
        usersByEmail.put(email, i);
        (new UserDAO()).save(i);
    }

    public int getInvestorPortfolioID(int id) {
        try {
            return getInvestor(id).getPortfolio().getId();
        } catch (NoInvestorWithIDException ignored) {
            return 0;
        }
    }

    public void addCFDtoInvestorPortfolio(int id, int cfd) {
        try {
            getInvestor(id).getPortfolio().addCFD(cfd);
        } catch (NoInvestorWithIDException ignored) {
        }
    }

    public void addAssetToInvestorWatchList(int id, int assetID) {
        try {
            getInvestor(id).addToWatchList(assetID);
        } catch (NoInvestorWithIDException ignored) {
        }
    }

    public void removeAssetFromInvestorWatchList(int id, int assetID) {
        try {
            getInvestor(id).removeWatchList(assetID);
        } catch (NoInvestorWithIDException ignored) {
        }
    }

    public List<Integer> getInvestorWatchList(int id) {
        try {
            return getInvestor(id).getPortfolio().getWatchList();
        } catch (NoInvestorWithIDException e) {
            return new ArrayList<>();
        }
    }

    private class NoInvestorWithIDException extends Throwable {
    }
}
