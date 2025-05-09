package co.hyperverge.hyperkyc.webCore.models;

import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebCoreNativeResponse.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b(\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0093\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0011J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010'\u001a\u00020\bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u009c\u0001\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\u0006HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0017\u0010\u0013R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001bR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016¨\u00064"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/models/FaceNativeResponse;", "Lco/hyperverge/hyperkyc/webCore/models/WebCoreNativeResponse;", "response", "", "errorMessage", "errorCode", "", "isBackPressed", "", Keys.IMAGE_BASE_64, "headers", "latitude", "longitude", "submittedTimestamp", "croppedFace", "videoBlob", AppConstants.ATTEMPTS_KEY, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAttempts", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCroppedFace", "()Ljava/lang/String;", "getErrorCode", "getErrorMessage", "getHeaders", "getImgBase64", "()Z", "getLatitude", "getLongitude", "getResponse", "getSubmittedTimestamp", "getVideoBlob", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lco/hyperverge/hyperkyc/webCore/models/FaceNativeResponse;", "equals", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FaceNativeResponse extends WebCoreNativeResponse {
    private final Integer attempts;
    private final String croppedFace;
    private final Integer errorCode;
    private final String errorMessage;
    private final String headers;
    private final String imgBase64;
    private final boolean isBackPressed;
    private final String latitude;
    private final String longitude;
    private final String response;
    private final String submittedTimestamp;
    private final String videoBlob;

    public FaceNativeResponse() {
        this(null, null, null, false, null, null, null, null, null, null, null, null, 4095, null);
    }

    public final String component1() {
        return getResponse();
    }

    /* renamed from: component10, reason: from getter */
    public final String getCroppedFace() {
        return this.croppedFace;
    }

    /* renamed from: component11, reason: from getter */
    public final String getVideoBlob() {
        return this.videoBlob;
    }

    /* renamed from: component12, reason: from getter */
    public final Integer getAttempts() {
        return this.attempts;
    }

    public final String component2() {
        return getErrorMessage();
    }

    public final Integer component3() {
        return getErrorCode();
    }

    public final boolean component4() {
        return getIsBackPressed();
    }

    /* renamed from: component5, reason: from getter */
    public final String getImgBase64() {
        return this.imgBase64;
    }

    /* renamed from: component6, reason: from getter */
    public final String getHeaders() {
        return this.headers;
    }

    /* renamed from: component7, reason: from getter */
    public final String getLatitude() {
        return this.latitude;
    }

    /* renamed from: component8, reason: from getter */
    public final String getLongitude() {
        return this.longitude;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSubmittedTimestamp() {
        return this.submittedTimestamp;
    }

    public final FaceNativeResponse copy(String response, String errorMessage, Integer errorCode, boolean isBackPressed, String imgBase64, String headers, String latitude, String longitude, String submittedTimestamp, String croppedFace, String videoBlob, Integer attempts) {
        return new FaceNativeResponse(response, errorMessage, errorCode, isBackPressed, imgBase64, headers, latitude, longitude, submittedTimestamp, croppedFace, videoBlob, attempts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceNativeResponse)) {
            return false;
        }
        FaceNativeResponse faceNativeResponse = (FaceNativeResponse) other;
        return Intrinsics.areEqual(getResponse(), faceNativeResponse.getResponse()) && Intrinsics.areEqual(getErrorMessage(), faceNativeResponse.getErrorMessage()) && Intrinsics.areEqual(getErrorCode(), faceNativeResponse.getErrorCode()) && getIsBackPressed() == faceNativeResponse.getIsBackPressed() && Intrinsics.areEqual(this.imgBase64, faceNativeResponse.imgBase64) && Intrinsics.areEqual(this.headers, faceNativeResponse.headers) && Intrinsics.areEqual(this.latitude, faceNativeResponse.latitude) && Intrinsics.areEqual(this.longitude, faceNativeResponse.longitude) && Intrinsics.areEqual(this.submittedTimestamp, faceNativeResponse.submittedTimestamp) && Intrinsics.areEqual(this.croppedFace, faceNativeResponse.croppedFace) && Intrinsics.areEqual(this.videoBlob, faceNativeResponse.videoBlob) && Intrinsics.areEqual(this.attempts, faceNativeResponse.attempts);
    }

    public int hashCode() {
        int hashCode = (((((getResponse() == null ? 0 : getResponse().hashCode()) * 31) + (getErrorMessage() == null ? 0 : getErrorMessage().hashCode())) * 31) + (getErrorCode() == null ? 0 : getErrorCode().hashCode())) * 31;
        boolean isBackPressed = getIsBackPressed();
        int i = isBackPressed;
        if (isBackPressed) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        String str = this.imgBase64;
        int hashCode2 = (i2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.headers;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.latitude;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.longitude;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.submittedTimestamp;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.croppedFace;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.videoBlob;
        int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Integer num = this.attempts;
        return hashCode8 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "FaceNativeResponse(response=" + getResponse() + ", errorMessage=" + getErrorMessage() + ", errorCode=" + getErrorCode() + ", isBackPressed=" + getIsBackPressed() + ", imgBase64=" + this.imgBase64 + ", headers=" + this.headers + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", submittedTimestamp=" + this.submittedTimestamp + ", croppedFace=" + this.croppedFace + ", videoBlob=" + this.videoBlob + ", attempts=" + this.attempts + ')';
    }

    public /* synthetic */ FaceNativeResponse(String str, String str2, Integer num, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? false : z, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : str8, (i & 1024) != 0 ? null : str9, (i & 2048) == 0 ? num2 : null);
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    public String getResponse() {
        return this.response;
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    public Integer getErrorCode() {
        return this.errorCode;
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    /* renamed from: isBackPressed, reason: from getter */
    public boolean getIsBackPressed() {
        return this.isBackPressed;
    }

    public final String getImgBase64() {
        return this.imgBase64;
    }

    public final String getHeaders() {
        return this.headers;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final String getLongitude() {
        return this.longitude;
    }

    public final String getSubmittedTimestamp() {
        return this.submittedTimestamp;
    }

    public final String getCroppedFace() {
        return this.croppedFace;
    }

    public final String getVideoBlob() {
        return this.videoBlob;
    }

    public final Integer getAttempts() {
        return this.attempts;
    }

    public FaceNativeResponse(String str, String str2, Integer num, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num2) {
        super(null);
        this.response = str;
        this.errorMessage = str2;
        this.errorCode = num;
        this.isBackPressed = z;
        this.imgBase64 = str3;
        this.headers = str4;
        this.latitude = str5;
        this.longitude = str6;
        this.submittedTimestamp = str7;
        this.croppedFace = str8;
        this.videoBlob = str9;
        this.attempts = num2;
    }
}
