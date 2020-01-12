package BusinessModel;

import BusinessModel.Assets.Asset;
import BusinessModel.Report.Bug;

import java.util.Collection;
import java.util.List;

public interface ESSTradingAdmin {
    Collection<Asset> getAssetsByType(String type);
    List<Bug> getBugs();
    void stopThread();
}
