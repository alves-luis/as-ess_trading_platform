package BusinessModel;

import BusinessModel.Assets.Asset;
import BusinessModel.Assets.AssetType;
import Data.AssetDAO;

import java.util.*;

public class AssetsManager {
    private Map<Integer, Asset> assets;
    private Map<AssetType, List<Asset>> assetsByType;

    public AssetsManager() {
        assets = new HashMap<>();
        assetsByType = new HashMap<>();

        for (Asset asset : (new AssetDAO()).getAll()) {
            assets.put(asset.getId(), asset);
            List<Asset> byType = assetsByType.get(asset.getType());
            if (byType == null) {
                byType = new ArrayList<>();
            }
            byType.add(asset);
            assetsByType.put(asset.getType(), byType);
        }
    }

    public Collection<Asset> getAllAssets() {
        return this.assets.values();
    }

    public Map<Integer, Asset> getAssetsMap() {
        return this.assets;
    }

    public Collection<Asset> getAssetsByType(String type) {
        return this.assetsByType.get(AssetType.valueOf(type));
    }

    public Asset getAsset(int id) {
        return this.assets.get(id);
    }
}