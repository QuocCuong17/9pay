package co.hyperverge.hypersnapsdk.analytics.mixpanel.network;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Map;

@Deprecated
/* loaded from: classes2.dex */
public class MixPanelEvent implements Serializable {

    @SerializedName(NotificationCompat.CATEGORY_EVENT)
    private String eventName;

    @SerializedName("properties")
    private Map<String, Object> props;

    protected boolean canEqual(Object obj) {
        return obj instanceof MixPanelEvent;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MixPanelEvent)) {
            return false;
        }
        MixPanelEvent mixPanelEvent = (MixPanelEvent) obj;
        if (!mixPanelEvent.canEqual(this)) {
            return false;
        }
        String eventName = getEventName();
        String eventName2 = mixPanelEvent.getEventName();
        if (eventName != null ? !eventName.equals(eventName2) : eventName2 != null) {
            return false;
        }
        Map<String, Object> props = getProps();
        Map<String, Object> props2 = mixPanelEvent.getProps();
        return props != null ? props.equals(props2) : props2 == null;
    }

    public int hashCode() {
        String eventName = getEventName();
        int hashCode = eventName == null ? 43 : eventName.hashCode();
        Map<String, Object> props = getProps();
        return ((hashCode + 59) * 59) + (props != null ? props.hashCode() : 43);
    }

    public void setEventName(String str) {
        this.eventName = str;
    }

    public void setProps(Map<String, Object> map) {
        this.props = map;
    }

    public String toString() {
        return "MixPanelEvent(eventName=" + getEventName() + ", props=" + getProps() + ")";
    }

    public MixPanelEvent(String str, Map<String, Object> map) {
        this.eventName = str;
        this.props = map;
    }

    public String getEventName() {
        return this.eventName;
    }

    public Map<String, Object> getProps() {
        return this.props;
    }
}
