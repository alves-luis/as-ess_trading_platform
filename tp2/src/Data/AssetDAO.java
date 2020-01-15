package Data;

import BusinessModel.Assets.Asset;
import BusinessModel.Assets.AssetType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssetDAO implements DAO<Asset> {
    private Connection con;

    @Override
    public Asset get(int id) {
        return null;
    }

    private Asset createAssetByType(ResultSet rs, AssetType type) throws SQLException{
        return (new Asset(rs.getInt("idAsset"), rs.getDouble("Value"),
              rs.getString("Company"), type));
    }

    @Override
    public List<Asset> getAll() {
        List<Asset> assets = new ArrayList<>();
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from Asset");
                ResultSet rs = pStm.executeQuery();
                while(rs.next()) {
                    if(rs.getString("Type").equals("coin")){
                        assets.add(createAssetByType(rs, AssetType.COIN));
                    } else{
                        if(rs.getString("Type").equals("stock")){
                          assets.add(createAssetByType(rs, AssetType.STOCK));
                        } else {
                            assets.add(createAssetByType(rs, AssetType.COMMODITY));
                        }
                    }
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return assets;
    }

    private void setStringByType(PreparedStatement pStm, int position, Asset asset) throws SQLException{

        if(asset.getType() == AssetType.COIN){
            pStm.setString(position, "coin");
        }
        if(asset.getType() == AssetType.COMMODITY){
            pStm.setString(position, "commodity");
        }
        if(asset.getType() == AssetType.STOCK){
            pStm.setString(position, "stock");
        }

    }


    @Override
    public void save(Asset asset) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into Asset VALUES (?,?,?,?)");
                pStm.setInt(1,asset.getId());
                pStm.setString(2,asset.getCompany());
                setStringByType(pStm,3,asset);
                pStm.setDouble(4, asset.getValue());

                pStm.execute();

            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void update(Asset asset) {
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("update  Asset set Company=?, Type=?, Value=? " +
                                                                 "where idAsset=?");
                pStm.setString(1,asset.getCompany());
                setStringByType(pStm,2,asset);
                pStm.setDouble(3, asset.getValue());
                pStm.setInt(4,asset.getId());
                pStm.execute();

            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public void delete(Asset asset) {

    }

}
