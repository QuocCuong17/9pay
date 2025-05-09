package co.hyperverge.hypersnapsdk.data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Deprecated
/* loaded from: classes2.dex */
public class MixpanelConfig implements Serializable {

    @SerializedName("events")
    private MixpanelEvents mixpanelEvents = new MixpanelEvents();

    @SerializedName("token")
    private String mixpanelToken = "";

    protected boolean canEqual(Object obj) {
        return obj instanceof MixpanelConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MixpanelConfig)) {
            return false;
        }
        MixpanelConfig mixpanelConfig = (MixpanelConfig) obj;
        if (!mixpanelConfig.canEqual(this)) {
            return false;
        }
        MixpanelEvents mixpanelEvents = getMixpanelEvents();
        MixpanelEvents mixpanelEvents2 = mixpanelConfig.getMixpanelEvents();
        if (mixpanelEvents != null ? !mixpanelEvents.equals(mixpanelEvents2) : mixpanelEvents2 != null) {
            return false;
        }
        String mixpanelToken = getMixpanelToken();
        String mixpanelToken2 = mixpanelConfig.getMixpanelToken();
        return mixpanelToken != null ? mixpanelToken.equals(mixpanelToken2) : mixpanelToken2 == null;
    }

    public int hashCode() {
        MixpanelEvents mixpanelEvents = getMixpanelEvents();
        int hashCode = mixpanelEvents == null ? 43 : mixpanelEvents.hashCode();
        String mixpanelToken = getMixpanelToken();
        return ((hashCode + 59) * 59) + (mixpanelToken != null ? mixpanelToken.hashCode() : 43);
    }

    public void setMixpanelEvents(MixpanelEvents mixpanelEvents) {
        this.mixpanelEvents = mixpanelEvents;
    }

    public void setMixpanelToken(String str) {
        this.mixpanelToken = str;
    }

    public String toString() {
        return "MixpanelConfig(mixpanelEvents=" + getMixpanelEvents() + ", mixpanelToken=" + getMixpanelToken() + ")";
    }

    public MixpanelEvents getMixpanelEvents() {
        return this.mixpanelEvents;
    }

    public String getMixpanelToken() {
        return this.mixpanelToken;
    }
}
