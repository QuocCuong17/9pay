package vn.ai.faceauth.sdk.presentation.domain.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.mlkit.common.MlKitException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001eJ\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006 "}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/AuthenticationSDKResult;", "", "code", "", "firstFace", "", "lastFace", "signature", "encryptedFaceImages", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "getEncryptedFaceImages", "()Ljava/lang/String;", "getFirstFace", "getLastFace", "getSignature", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toJson", "Lorg/json/JSONObject;", "toMap", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class AuthenticationSDKResult {
    private final int code;
    private final String encryptedFaceImages;
    private final String firstFace;
    private final String lastFace;
    private final String signature;

    public AuthenticationSDKResult() {
        this(0, null, null, null, null, 31, null);
    }

    public AuthenticationSDKResult(int i, String str, String str2, String str3, String str4) {
        this.code = i;
        this.firstFace = str;
        this.lastFace = str2;
        this.signature = str3;
        this.encryptedFaceImages = str4;
    }

    public /* synthetic */ AuthenticationSDKResult(int i, String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) == 0 ? str4 : "");
    }

    public static /* synthetic */ AuthenticationSDKResult copy$default(AuthenticationSDKResult authenticationSDKResult, int i, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = authenticationSDKResult.code;
        }
        if ((i2 & 2) != 0) {
            str = authenticationSDKResult.firstFace;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = authenticationSDKResult.lastFace;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = authenticationSDKResult.signature;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            str4 = authenticationSDKResult.encryptedFaceImages;
        }
        return authenticationSDKResult.copy(i, str5, str6, str7, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFirstFace() {
        return this.firstFace;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLastFace() {
        return this.lastFace;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    /* renamed from: component5, reason: from getter */
    public final String getEncryptedFaceImages() {
        return this.encryptedFaceImages;
    }

    public final AuthenticationSDKResult copy(int code, String firstFace, String lastFace, String signature, String encryptedFaceImages) {
        return new AuthenticationSDKResult(code, firstFace, lastFace, signature, encryptedFaceImages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticationSDKResult)) {
            return false;
        }
        AuthenticationSDKResult authenticationSDKResult = (AuthenticationSDKResult) other;
        return this.code == authenticationSDKResult.code && Intrinsics.areEqual(this.firstFace, authenticationSDKResult.firstFace) && Intrinsics.areEqual(this.lastFace, authenticationSDKResult.lastFace) && Intrinsics.areEqual(this.signature, authenticationSDKResult.signature) && Intrinsics.areEqual(this.encryptedFaceImages, authenticationSDKResult.encryptedFaceImages);
    }

    public final int getCode() {
        return this.code;
    }

    public final String getEncryptedFaceImages() {
        return this.encryptedFaceImages;
    }

    public final String getFirstFace() {
        return this.firstFace;
    }

    public final String getLastFace() {
        return this.lastFace;
    }

    public final String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        int i = this.code;
        int hashCode = this.firstFace.hashCode();
        int hashCode2 = this.lastFace.hashCode();
        return this.encryptedFaceImages.hashCode() + ((this.signature.hashCode() + ((hashCode2 + ((hashCode + (i * 31)) * 31)) * 31)) * 31);
    }

    public final JSONObject toJson() {
        return new JSONObject(toMap());
    }

    public final Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(tfwhgw.rnigpa(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256), Integer.valueOf(this.code)), TuplesKt.to(tfwhgw.rnigpa(198), this.firstFace), TuplesKt.to(tfwhgw.rnigpa(199), this.lastFace), TuplesKt.to(tfwhgw.rnigpa(200), this.signature), TuplesKt.to(tfwhgw.rnigpa(201), this.encryptedFaceImages));
    }

    public String toString() {
        return tfwhgw.rnigpa(202) + this.code + tfwhgw.rnigpa(203) + this.firstFace + tfwhgw.rnigpa(MlKitException.CODE_SCANNER_TASK_IN_PROGRESS) + this.lastFace + tfwhgw.rnigpa(MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR) + this.signature + tfwhgw.rnigpa(MlKitException.CODE_SCANNER_PIPELINE_INFERENCE_ERROR) + this.encryptedFaceImages + ')';
    }
}
