package vn.ai.faceauth.sdk.domain.model;

import android.graphics.Bitmap;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/PhotoResultDomain;", "", "createdAt", "", "fileBase64", "bitMap", "Landroid/graphics/Bitmap;", "(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V", "getBitMap", "()Landroid/graphics/Bitmap;", "getCreatedAt", "()Ljava/lang/String;", "getFileBase64", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class PhotoResultDomain {
    private final Bitmap bitMap;
    private final String createdAt;
    private final String fileBase64;

    public PhotoResultDomain(String str, String str2, Bitmap bitmap) {
        this.createdAt = str;
        this.fileBase64 = str2;
        this.bitMap = bitmap;
    }

    public static /* synthetic */ PhotoResultDomain copy$default(PhotoResultDomain photoResultDomain, String str, String str2, Bitmap bitmap, int i, Object obj) {
        if ((i & 1) != 0) {
            str = photoResultDomain.createdAt;
        }
        if ((i & 2) != 0) {
            str2 = photoResultDomain.fileBase64;
        }
        if ((i & 4) != 0) {
            bitmap = photoResultDomain.bitMap;
        }
        return photoResultDomain.copy(str, str2, bitmap);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFileBase64() {
        return this.fileBase64;
    }

    /* renamed from: component3, reason: from getter */
    public final Bitmap getBitMap() {
        return this.bitMap;
    }

    public final PhotoResultDomain copy(String createdAt, String fileBase64, Bitmap bitMap) {
        return new PhotoResultDomain(createdAt, fileBase64, bitMap);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhotoResultDomain)) {
            return false;
        }
        PhotoResultDomain photoResultDomain = (PhotoResultDomain) other;
        return Intrinsics.areEqual(this.createdAt, photoResultDomain.createdAt) && Intrinsics.areEqual(this.fileBase64, photoResultDomain.fileBase64) && Intrinsics.areEqual(this.bitMap, photoResultDomain.bitMap);
    }

    public final Bitmap getBitMap() {
        return this.bitMap;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getFileBase64() {
        return this.fileBase64;
    }

    public int hashCode() {
        int hashCode = this.createdAt.hashCode();
        int hashCode2 = this.fileBase64.hashCode();
        Bitmap bitmap = this.bitMap;
        return ((hashCode2 + (hashCode * 31)) * 31) + (bitmap == null ? 0 : bitmap.hashCode());
    }

    public String toString() {
        return tfwhgw.rnigpa(CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256) + this.createdAt + tfwhgw.rnigpa(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256) + this.fileBase64 + tfwhgw.rnigpa(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256) + this.bitMap + ')';
    }
}
