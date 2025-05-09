package co.hyperverge.hypersnapsdk.service.qr;

import co.hyperverge.hypersnapsdk.objects.AadhaarQRResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class AadhaarQrData implements Serializable {

    @SerializedName(alternate = {"QDB", "QDA"}, value = "PrintLetterBarcodeData")
    AadhaarQRResponse aadhaarQRResponse;

    protected boolean canEqual(Object obj) {
        return obj instanceof AadhaarQrData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AadhaarQrData)) {
            return false;
        }
        AadhaarQrData aadhaarQrData = (AadhaarQrData) obj;
        if (!aadhaarQrData.canEqual(this)) {
            return false;
        }
        AadhaarQRResponse aadhaarQRResponse = getAadhaarQRResponse();
        AadhaarQRResponse aadhaarQRResponse2 = aadhaarQrData.getAadhaarQRResponse();
        return aadhaarQRResponse != null ? aadhaarQRResponse.equals(aadhaarQRResponse2) : aadhaarQRResponse2 == null;
    }

    public int hashCode() {
        AadhaarQRResponse aadhaarQRResponse = getAadhaarQRResponse();
        return 59 + (aadhaarQRResponse == null ? 43 : aadhaarQRResponse.hashCode());
    }

    public void setAadhaarQRResponse(AadhaarQRResponse aadhaarQRResponse) {
        this.aadhaarQRResponse = aadhaarQRResponse;
    }

    public String toString() {
        return "AadhaarQrData(aadhaarQRResponse=" + getAadhaarQRResponse() + ")";
    }

    public AadhaarQRResponse getAadhaarQRResponse() {
        return this.aadhaarQRResponse;
    }
}
