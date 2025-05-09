package co.hyperverge.hypersnapsdk.model;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class UILogos implements Serializable {
    private String captureScreenClientLogo;
    private String clientLogo;

    protected boolean canEqual(Object obj) {
        return obj instanceof UILogos;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UILogos)) {
            return false;
        }
        UILogos uILogos = (UILogos) obj;
        if (!uILogos.canEqual(this)) {
            return false;
        }
        String clientLogo = getClientLogo();
        String clientLogo2 = uILogos.getClientLogo();
        if (clientLogo != null ? !clientLogo.equals(clientLogo2) : clientLogo2 != null) {
            return false;
        }
        String captureScreenClientLogo = getCaptureScreenClientLogo();
        String captureScreenClientLogo2 = uILogos.getCaptureScreenClientLogo();
        return captureScreenClientLogo != null ? captureScreenClientLogo.equals(captureScreenClientLogo2) : captureScreenClientLogo2 == null;
    }

    public int hashCode() {
        String clientLogo = getClientLogo();
        int hashCode = clientLogo == null ? 43 : clientLogo.hashCode();
        String captureScreenClientLogo = getCaptureScreenClientLogo();
        return ((hashCode + 59) * 59) + (captureScreenClientLogo != null ? captureScreenClientLogo.hashCode() : 43);
    }

    public void setCaptureScreenClientLogo(String str) {
        this.captureScreenClientLogo = str;
    }

    public void setClientLogo(String str) {
        this.clientLogo = str;
    }

    public String toString() {
        return "UILogos(clientLogo=" + getClientLogo() + ", captureScreenClientLogo=" + getCaptureScreenClientLogo() + ")";
    }

    public String getClientLogo() {
        return this.clientLogo;
    }

    public String getCaptureScreenClientLogo() {
        return this.captureScreenClientLogo;
    }
}
