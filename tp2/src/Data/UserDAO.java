package Data;

import BusinessModel.User.Admin;
import BusinessModel.User.Investor;
import BusinessModel.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Data.Connect.connect;

public class UserDAO implements DAO<User> {
    private Connection con;


    private Investor createInvestor(ResultSet rs) throws SQLException {
        return (new Investor(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                rs.getString("Password"), (new PortfolioDAO()).get(rs.getInt("Portfolio_idPortfolio"))
                , rs.getDouble("Credit")));
    }

    private Admin createAdmin(ResultSet rs) throws SQLException{
        return (new Admin(rs.getInt("idUser"), rs.getString("Username"), rs.getString("Email"),
                rs.getString("Password"), rs.getString("Password")));
    }

    @Override
    public User get(int id) {
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from User where idUser=?");
                pStm.setInt(1, id);
                ResultSet rs = pStm.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("isAdmin") == 0) {
                        return createInvestor(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return new Investor();
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from User");
                ResultSet rs = pStm.executeQuery();
                while(rs.next()) {
                    if (rs.getInt("isAdmin") == 0) {
                        users.add(createAdmin(rs));
                    } else users.add(createInvestor(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return users;
    }

    private void setLastValuesForUser (PreparedStatement pStm, int isAdmin, double credito, int id_Portfolio) throws SQLException{
        pStm.setInt(5,isAdmin);
        pStm.setDouble(6, credito);
        pStm.setInt(7, id_Portfolio);
    }

    @Override
    public void save(User user) {
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into User(idUser, Username, Email, Password, isAdmin, Credit, Portfolio_idPortfolio) values (?,?,?,?,?,?,?) ");
                pStm.setInt(1, user.getId());
                pStm.setString(2, user.getUsername());
                pStm.setString(3, user.getEmail());
                pStm.setString(4, user.getPassword());
                if(user instanceof Investor){
                    Investor i = (Investor) user;
                    setLastValuesForUser(pStm, 1, i.getCredit(), i.getPortfolioId());
                } else{
                    if(user instanceof Admin) {
                        setLastValuesForUser(pStm, 0, 0, 0);
                    }
                }
                pStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void update(User user) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("update  User set Credit=? where idUser=?");
                if(user instanceof Investor) {
                    Investor i = (Investor) user;
                    pStm.setDouble(1, i.getCredit());
                    pStm.setInt(2, i.getId());
                    pStm.execute();
                }

            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void delete(User user) {

    }
}
