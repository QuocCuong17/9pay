package co.hyperverge.hvnfcsdk.model;

import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class Authentication implements Serializable {

    @SerializedName("dateOfBirth")
    String dateOfBirth;

    @SerializedName("dateOfExpiry")
    String dateOfExpiry;

    @SerializedName(AnalyticsLogger.Keys.DOCUMENT_ID)
    String documentId;

    protected boolean canEqual(Object obj) {
        return obj instanceof Authentication;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Authentication)) {
            return false;
        }
        Authentication authentication = (Authentication) obj;
        if (!authentication.canEqual(this)) {
            return false;
        }
        String documentId = getDocumentId();
        String documentId2 = authentication.getDocumentId();
        if (documentId != null ? !documentId.equals(documentId2) : documentId2 != null) {
            return false;
        }
        String dateOfBirth = getDateOfBirth();
        String dateOfBirth2 = authentication.getDateOfBirth();
        if (dateOfBirth != null ? !dateOfBirth.equals(dateOfBirth2) : dateOfBirth2 != null) {
            return false;
        }
        String dateOfExpiry = getDateOfExpiry();
        String dateOfExpiry2 = authentication.getDateOfExpiry();
        return dateOfExpiry != null ? dateOfExpiry.equals(dateOfExpiry2) : dateOfExpiry2 == null;
    }

    public int hashCode() {
        String documentId = getDocumentId();
        int hashCode = documentId == null ? 43 : documentId.hashCode();
        String dateOfBirth = getDateOfBirth();
        int hashCode2 = ((hashCode + 59) * 59) + (dateOfBirth == null ? 43 : dateOfBirth.hashCode());
        String dateOfExpiry = getDateOfExpiry();
        return (hashCode2 * 59) + (dateOfExpiry != null ? dateOfExpiry.hashCode() : 43);
    }

    public void setDateOfBirth(String str) {
        this.dateOfBirth = str;
    }

    public void setDateOfExpiry(String str) {
        this.dateOfExpiry = str;
    }

    public void setDocumentId(String str) {
        this.documentId = str;
    }

    public String toString() {
        return "Authentication(documentId=" + getDocumentId() + ", dateOfBirth=" + getDateOfBirth() + ", dateOfExpiry=" + getDateOfExpiry() + ")";
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }
}
