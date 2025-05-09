package co.hyperverge.hypersnapsdk.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UIAlignment implements Serializable {
    private String titleTextAlignment = "left";
    private String descriptionTextAlignment = "left";
    private String statusTextAlignment = TtmlNode.CENTER;
    private String statementHelperTextAlignment = "left";
    private String statementTextAlignment = TtmlNode.CENTER;
    private String nfcStatusTextAlignment = "left";
    private String apiLoadingTitleTextAlignment = TtmlNode.CENTER;
    private String apiLoadingHintTextAlignment = TtmlNode.CENTER;

    protected boolean canEqual(Object obj) {
        return obj instanceof UIAlignment;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UIAlignment)) {
            return false;
        }
        UIAlignment uIAlignment = (UIAlignment) obj;
        if (!uIAlignment.canEqual(this)) {
            return false;
        }
        String titleTextAlignment = getTitleTextAlignment();
        String titleTextAlignment2 = uIAlignment.getTitleTextAlignment();
        if (titleTextAlignment != null ? !titleTextAlignment.equals(titleTextAlignment2) : titleTextAlignment2 != null) {
            return false;
        }
        String descriptionTextAlignment = getDescriptionTextAlignment();
        String descriptionTextAlignment2 = uIAlignment.getDescriptionTextAlignment();
        if (descriptionTextAlignment != null ? !descriptionTextAlignment.equals(descriptionTextAlignment2) : descriptionTextAlignment2 != null) {
            return false;
        }
        String statusTextAlignment = getStatusTextAlignment();
        String statusTextAlignment2 = uIAlignment.getStatusTextAlignment();
        if (statusTextAlignment != null ? !statusTextAlignment.equals(statusTextAlignment2) : statusTextAlignment2 != null) {
            return false;
        }
        String statementHelperTextAlignment = getStatementHelperTextAlignment();
        String statementHelperTextAlignment2 = uIAlignment.getStatementHelperTextAlignment();
        if (statementHelperTextAlignment != null ? !statementHelperTextAlignment.equals(statementHelperTextAlignment2) : statementHelperTextAlignment2 != null) {
            return false;
        }
        String statementTextAlignment = getStatementTextAlignment();
        String statementTextAlignment2 = uIAlignment.getStatementTextAlignment();
        if (statementTextAlignment != null ? !statementTextAlignment.equals(statementTextAlignment2) : statementTextAlignment2 != null) {
            return false;
        }
        String nfcStatusTextAlignment = getNfcStatusTextAlignment();
        String nfcStatusTextAlignment2 = uIAlignment.getNfcStatusTextAlignment();
        if (nfcStatusTextAlignment != null ? !nfcStatusTextAlignment.equals(nfcStatusTextAlignment2) : nfcStatusTextAlignment2 != null) {
            return false;
        }
        String apiLoadingTitleTextAlignment = getApiLoadingTitleTextAlignment();
        String apiLoadingTitleTextAlignment2 = uIAlignment.getApiLoadingTitleTextAlignment();
        if (apiLoadingTitleTextAlignment != null ? !apiLoadingTitleTextAlignment.equals(apiLoadingTitleTextAlignment2) : apiLoadingTitleTextAlignment2 != null) {
            return false;
        }
        String apiLoadingHintTextAlignment = getApiLoadingHintTextAlignment();
        String apiLoadingHintTextAlignment2 = uIAlignment.getApiLoadingHintTextAlignment();
        return apiLoadingHintTextAlignment != null ? apiLoadingHintTextAlignment.equals(apiLoadingHintTextAlignment2) : apiLoadingHintTextAlignment2 == null;
    }

    public int hashCode() {
        String titleTextAlignment = getTitleTextAlignment();
        int hashCode = titleTextAlignment == null ? 43 : titleTextAlignment.hashCode();
        String descriptionTextAlignment = getDescriptionTextAlignment();
        int hashCode2 = ((hashCode + 59) * 59) + (descriptionTextAlignment == null ? 43 : descriptionTextAlignment.hashCode());
        String statusTextAlignment = getStatusTextAlignment();
        int hashCode3 = (hashCode2 * 59) + (statusTextAlignment == null ? 43 : statusTextAlignment.hashCode());
        String statementHelperTextAlignment = getStatementHelperTextAlignment();
        int hashCode4 = (hashCode3 * 59) + (statementHelperTextAlignment == null ? 43 : statementHelperTextAlignment.hashCode());
        String statementTextAlignment = getStatementTextAlignment();
        int hashCode5 = (hashCode4 * 59) + (statementTextAlignment == null ? 43 : statementTextAlignment.hashCode());
        String nfcStatusTextAlignment = getNfcStatusTextAlignment();
        int hashCode6 = (hashCode5 * 59) + (nfcStatusTextAlignment == null ? 43 : nfcStatusTextAlignment.hashCode());
        String apiLoadingTitleTextAlignment = getApiLoadingTitleTextAlignment();
        int hashCode7 = (hashCode6 * 59) + (apiLoadingTitleTextAlignment == null ? 43 : apiLoadingTitleTextAlignment.hashCode());
        String apiLoadingHintTextAlignment = getApiLoadingHintTextAlignment();
        return (hashCode7 * 59) + (apiLoadingHintTextAlignment != null ? apiLoadingHintTextAlignment.hashCode() : 43);
    }

    public void setApiLoadingHintTextAlignment(String str) {
        this.apiLoadingHintTextAlignment = str;
    }

    public void setApiLoadingTitleTextAlignment(String str) {
        this.apiLoadingTitleTextAlignment = str;
    }

    public void setDescriptionTextAlignment(String str) {
        this.descriptionTextAlignment = str;
    }

    public void setNfcStatusTextAlignment(String str) {
        this.nfcStatusTextAlignment = str;
    }

    public void setStatementHelperTextAlignment(String str) {
        this.statementHelperTextAlignment = str;
    }

    public void setStatementTextAlignment(String str) {
        this.statementTextAlignment = str;
    }

    public void setStatusTextAlignment(String str) {
        this.statusTextAlignment = str;
    }

    public void setTitleTextAlignment(String str) {
        this.titleTextAlignment = str;
    }

    public String toString() {
        return "UIAlignment(titleTextAlignment=" + getTitleTextAlignment() + ", descriptionTextAlignment=" + getDescriptionTextAlignment() + ", statusTextAlignment=" + getStatusTextAlignment() + ", statementHelperTextAlignment=" + getStatementHelperTextAlignment() + ", statementTextAlignment=" + getStatementTextAlignment() + ", nfcStatusTextAlignment=" + getNfcStatusTextAlignment() + ", apiLoadingTitleTextAlignment=" + getApiLoadingTitleTextAlignment() + ", apiLoadingHintTextAlignment=" + getApiLoadingHintTextAlignment() + ")";
    }

    public String getTitleTextAlignment() {
        return this.titleTextAlignment;
    }

    public String getDescriptionTextAlignment() {
        return this.descriptionTextAlignment;
    }

    public String getStatusTextAlignment() {
        return this.statusTextAlignment;
    }

    public String getStatementHelperTextAlignment() {
        return this.statementHelperTextAlignment;
    }

    public String getStatementTextAlignment() {
        return this.statementTextAlignment;
    }

    public String getNfcStatusTextAlignment() {
        return this.nfcStatusTextAlignment;
    }

    public String getApiLoadingTitleTextAlignment() {
        return this.apiLoadingTitleTextAlignment;
    }

    public String getApiLoadingHintTextAlignment() {
        return this.apiLoadingHintTextAlignment;
    }
}
