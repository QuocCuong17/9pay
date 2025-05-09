package co.hyperverge.hypersnapsdk.data.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class FeatureConfigResponse {

    @SerializedName("features")
    private List<FeatureConfig> features;

    protected boolean canEqual(Object obj) {
        return obj instanceof FeatureConfigResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FeatureConfigResponse)) {
            return false;
        }
        FeatureConfigResponse featureConfigResponse = (FeatureConfigResponse) obj;
        if (!featureConfigResponse.canEqual(this)) {
            return false;
        }
        List<FeatureConfig> features = getFeatures();
        List<FeatureConfig> features2 = featureConfigResponse.getFeatures();
        return features != null ? features.equals(features2) : features2 == null;
    }

    public int hashCode() {
        List<FeatureConfig> features = getFeatures();
        return 59 + (features == null ? 43 : features.hashCode());
    }

    public void setFeatures(List<FeatureConfig> list) {
        this.features = list;
    }

    public String toString() {
        return "FeatureConfigResponse(features=" + getFeatures() + ")";
    }

    public List<FeatureConfig> getFeatures() {
        return this.features;
    }
}
