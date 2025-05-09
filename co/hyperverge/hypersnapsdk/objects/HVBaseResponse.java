package co.hyperverge.hypersnapsdk.objects;

import android.util.Log;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVBaseResponse implements Serializable {
    private String action;
    private String apiHeaders;
    private String apiResult;
    private int attemptsCount;
    private String compressedImageUri;
    private String fullImageURI;
    private HashMap<String, List<String>> gestureLivenessImageUrls;
    private String imageURI;
    private Double latitude;
    private Double longitude;
    private String rawBarcode;
    private String retakeMessage;
    private Integer statusCode;
    private String statusMessage;
    private Long submittedTimestamp;
    private String videoUri;
    private String waterMarkCroppedImageUri;
    private String waterMarkFullImageUri;

    protected boolean canEqual(Object obj) {
        return obj instanceof HVBaseResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVBaseResponse)) {
            return false;
        }
        HVBaseResponse hVBaseResponse = (HVBaseResponse) obj;
        if (!hVBaseResponse.canEqual(this) || getAttemptsCount() != hVBaseResponse.getAttemptsCount()) {
            return false;
        }
        Integer statusCode = getStatusCode();
        Integer statusCode2 = hVBaseResponse.getStatusCode();
        if (statusCode != null ? !statusCode.equals(statusCode2) : statusCode2 != null) {
            return false;
        }
        Double latitude = getLatitude();
        Double latitude2 = hVBaseResponse.getLatitude();
        if (latitude != null ? !latitude.equals(latitude2) : latitude2 != null) {
            return false;
        }
        Double longitude = getLongitude();
        Double longitude2 = hVBaseResponse.getLongitude();
        if (longitude != null ? !longitude.equals(longitude2) : longitude2 != null) {
            return false;
        }
        Long submittedTimestamp = getSubmittedTimestamp();
        Long submittedTimestamp2 = hVBaseResponse.getSubmittedTimestamp();
        if (submittedTimestamp != null ? !submittedTimestamp.equals(submittedTimestamp2) : submittedTimestamp2 != null) {
            return false;
        }
        String imageURI = getImageURI();
        String imageURI2 = hVBaseResponse.getImageURI();
        if (imageURI != null ? !imageURI.equals(imageURI2) : imageURI2 != null) {
            return false;
        }
        String retakeMessage = getRetakeMessage();
        String retakeMessage2 = hVBaseResponse.getRetakeMessage();
        if (retakeMessage != null ? !retakeMessage.equals(retakeMessage2) : retakeMessage2 != null) {
            return false;
        }
        String action = getAction();
        String action2 = hVBaseResponse.getAction();
        if (action != null ? !action.equals(action2) : action2 != null) {
            return false;
        }
        JSONObject apiResult = getApiResult();
        JSONObject apiResult2 = hVBaseResponse.getApiResult();
        if (apiResult != null ? !apiResult.equals(apiResult2) : apiResult2 != null) {
            return false;
        }
        JSONObject apiHeaders = getApiHeaders();
        JSONObject apiHeaders2 = hVBaseResponse.getApiHeaders();
        if (apiHeaders != null ? !apiHeaders.equals(apiHeaders2) : apiHeaders2 != null) {
            return false;
        }
        String rawBarcode = getRawBarcode();
        String rawBarcode2 = hVBaseResponse.getRawBarcode();
        if (rawBarcode != null ? !rawBarcode.equals(rawBarcode2) : rawBarcode2 != null) {
            return false;
        }
        String fullImageURI = getFullImageURI();
        String fullImageURI2 = hVBaseResponse.getFullImageURI();
        if (fullImageURI != null ? !fullImageURI.equals(fullImageURI2) : fullImageURI2 != null) {
            return false;
        }
        String waterMarkFullImageUri = getWaterMarkFullImageUri();
        String waterMarkFullImageUri2 = hVBaseResponse.getWaterMarkFullImageUri();
        if (waterMarkFullImageUri != null ? !waterMarkFullImageUri.equals(waterMarkFullImageUri2) : waterMarkFullImageUri2 != null) {
            return false;
        }
        String waterMarkCroppedImageUri = getWaterMarkCroppedImageUri();
        String waterMarkCroppedImageUri2 = hVBaseResponse.getWaterMarkCroppedImageUri();
        if (waterMarkCroppedImageUri != null ? !waterMarkCroppedImageUri.equals(waterMarkCroppedImageUri2) : waterMarkCroppedImageUri2 != null) {
            return false;
        }
        String videoUri = getVideoUri();
        String videoUri2 = hVBaseResponse.getVideoUri();
        if (videoUri != null ? !videoUri.equals(videoUri2) : videoUri2 != null) {
            return false;
        }
        String compressedImageUri = getCompressedImageUri();
        String compressedImageUri2 = hVBaseResponse.getCompressedImageUri();
        if (compressedImageUri != null ? !compressedImageUri.equals(compressedImageUri2) : compressedImageUri2 != null) {
            return false;
        }
        HashMap<String, List<String>> gestureLivenessImageUrls = getGestureLivenessImageUrls();
        HashMap<String, List<String>> gestureLivenessImageUrls2 = hVBaseResponse.getGestureLivenessImageUrls();
        if (gestureLivenessImageUrls != null ? !gestureLivenessImageUrls.equals(gestureLivenessImageUrls2) : gestureLivenessImageUrls2 != null) {
            return false;
        }
        String statusMessage = getStatusMessage();
        String statusMessage2 = hVBaseResponse.getStatusMessage();
        return statusMessage != null ? statusMessage.equals(statusMessage2) : statusMessage2 == null;
    }

    public int hashCode() {
        int attemptsCount = getAttemptsCount() + 59;
        Integer statusCode = getStatusCode();
        int hashCode = (attemptsCount * 59) + (statusCode == null ? 43 : statusCode.hashCode());
        Double latitude = getLatitude();
        int hashCode2 = (hashCode * 59) + (latitude == null ? 43 : latitude.hashCode());
        Double longitude = getLongitude();
        int hashCode3 = (hashCode2 * 59) + (longitude == null ? 43 : longitude.hashCode());
        Long submittedTimestamp = getSubmittedTimestamp();
        int hashCode4 = (hashCode3 * 59) + (submittedTimestamp == null ? 43 : submittedTimestamp.hashCode());
        String imageURI = getImageURI();
        int hashCode5 = (hashCode4 * 59) + (imageURI == null ? 43 : imageURI.hashCode());
        String retakeMessage = getRetakeMessage();
        int hashCode6 = (hashCode5 * 59) + (retakeMessage == null ? 43 : retakeMessage.hashCode());
        String action = getAction();
        int hashCode7 = (hashCode6 * 59) + (action == null ? 43 : action.hashCode());
        JSONObject apiResult = getApiResult();
        int hashCode8 = (hashCode7 * 59) + (apiResult == null ? 43 : apiResult.hashCode());
        JSONObject apiHeaders = getApiHeaders();
        int hashCode9 = (hashCode8 * 59) + (apiHeaders == null ? 43 : apiHeaders.hashCode());
        String rawBarcode = getRawBarcode();
        int hashCode10 = (hashCode9 * 59) + (rawBarcode == null ? 43 : rawBarcode.hashCode());
        String fullImageURI = getFullImageURI();
        int hashCode11 = (hashCode10 * 59) + (fullImageURI == null ? 43 : fullImageURI.hashCode());
        String waterMarkFullImageUri = getWaterMarkFullImageUri();
        int hashCode12 = (hashCode11 * 59) + (waterMarkFullImageUri == null ? 43 : waterMarkFullImageUri.hashCode());
        String waterMarkCroppedImageUri = getWaterMarkCroppedImageUri();
        int hashCode13 = (hashCode12 * 59) + (waterMarkCroppedImageUri == null ? 43 : waterMarkCroppedImageUri.hashCode());
        String videoUri = getVideoUri();
        int hashCode14 = (hashCode13 * 59) + (videoUri == null ? 43 : videoUri.hashCode());
        String compressedImageUri = getCompressedImageUri();
        int hashCode15 = (hashCode14 * 59) + (compressedImageUri == null ? 43 : compressedImageUri.hashCode());
        HashMap<String, List<String>> gestureLivenessImageUrls = getGestureLivenessImageUrls();
        int hashCode16 = (hashCode15 * 59) + (gestureLivenessImageUrls == null ? 43 : gestureLivenessImageUrls.hashCode());
        String statusMessage = getStatusMessage();
        return (hashCode16 * 59) + (statusMessage != null ? statusMessage.hashCode() : 43);
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setAttemptsCount(int i) {
        this.attemptsCount = i;
    }

    public void setCompressedImageUri(String str) {
        this.compressedImageUri = str;
    }

    public void setFullImageURI(String str) {
        this.fullImageURI = str;
    }

    public void setGestureLivenessImageUrls(HashMap<String, List<String>> hashMap) {
        this.gestureLivenessImageUrls = hashMap;
    }

    public void setImageURI(String str) {
        this.imageURI = str;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setRawBarcode(String str) {
        this.rawBarcode = str;
    }

    public void setRetakeMessage(String str) {
        this.retakeMessage = str;
    }

    public void setStatusCode(Integer num) {
        this.statusCode = num;
    }

    public void setStatusMessage(String str) {
        this.statusMessage = str;
    }

    public void setSubmittedTimestamp(Long l) {
        this.submittedTimestamp = l;
    }

    public void setVideoUri(String str) {
        this.videoUri = str;
    }

    public void setWaterMarkCroppedImageUri(String str) {
        this.waterMarkCroppedImageUri = str;
    }

    public void setWaterMarkFullImageUri(String str) {
        this.waterMarkFullImageUri = str;
    }

    public String toString() {
        return "HVBaseResponse(imageURI=" + getImageURI() + ", attemptsCount=" + getAttemptsCount() + ", retakeMessage=" + getRetakeMessage() + ", action=" + getAction() + ", apiResult=" + getApiResult() + ", apiHeaders=" + getApiHeaders() + ", rawBarcode=" + getRawBarcode() + ", fullImageURI=" + getFullImageURI() + ", waterMarkFullImageUri=" + getWaterMarkFullImageUri() + ", waterMarkCroppedImageUri=" + getWaterMarkCroppedImageUri() + ", videoUri=" + getVideoUri() + ", compressedImageUri=" + getCompressedImageUri() + ", gestureLivenessImageUrls=" + getGestureLivenessImageUrls() + ", statusCode=" + getStatusCode() + ", statusMessage=" + getStatusMessage() + ", latitude=" + getLatitude() + ", longitude=" + getLongitude() + ", submittedTimestamp=" + getSubmittedTimestamp() + ")";
    }

    public HVBaseResponse() {
    }

    public String getImageURI() {
        return this.imageURI;
    }

    public int getAttemptsCount() {
        return this.attemptsCount;
    }

    public String getRetakeMessage() {
        return this.retakeMessage;
    }

    public String getAction() {
        return this.action;
    }

    public String getRawBarcode() {
        return this.rawBarcode;
    }

    public String getFullImageURI() {
        return this.fullImageURI;
    }

    public String getWaterMarkFullImageUri() {
        return this.waterMarkFullImageUri;
    }

    public String getWaterMarkCroppedImageUri() {
        return this.waterMarkCroppedImageUri;
    }

    public String getVideoUri() {
        return this.videoUri;
    }

    public String getCompressedImageUri() {
        return this.compressedImageUri;
    }

    public HashMap<String, List<String>> getGestureLivenessImageUrls() {
        return this.gestureLivenessImageUrls;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Long getSubmittedTimestamp() {
        return this.submittedTimestamp;
    }

    public HVBaseResponse(JSONObject jSONObject, JSONObject jSONObject2, String str, String str2) {
        if (jSONObject != null) {
            this.apiResult = jSONObject.toString();
        }
        if (jSONObject2 != null) {
            this.apiHeaders = jSONObject2.toString();
        }
        this.imageURI = str;
        this.action = str2;
        this.attemptsCount = 0;
        this.gestureLivenessImageUrls = new HashMap<>();
    }

    public boolean isVideoRecorded() {
        return !StringUtils.isEmptyOrNull(this.videoUri);
    }

    public JSONObject getApiResult() {
        if (this.apiResult == null) {
            return null;
        }
        try {
            return new JSONObject(this.apiResult);
        } catch (Exception e) {
            Log.e("HVBaseResponse", Utils.getErrorMessage(e));
            return null;
        }
    }

    public JSONObject getApiHeaders() {
        if (this.apiHeaders == null) {
            return null;
        }
        try {
            return new JSONObject(this.apiHeaders);
        } catch (Exception e) {
            Log.e("HVBaseResponse", Utils.getErrorMessage(e));
            return null;
        }
    }

    public void setApiResult(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.apiResult = jSONObject.toString();
        }
    }

    public void setApiHeaders(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.apiHeaders = jSONObject.toString();
        }
    }
}
