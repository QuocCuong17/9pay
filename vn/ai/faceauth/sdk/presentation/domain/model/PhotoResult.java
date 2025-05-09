package vn.ai.faceauth.sdk.presentation.domain.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/PhotoResult;", "", "createdAt", "", "fileBase64", "(Ljava/lang/String;Ljava/lang/String;)V", "getCreatedAt", "()Ljava/lang/String;", "getFileBase64", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class PhotoResult {
    private final String createdAt;
    private final String fileBase64;

    public PhotoResult(String str, String str2) {
        this.createdAt = str;
        this.fileBase64 = str2;
    }

    public static /* synthetic */ PhotoResult copy$default(PhotoResult photoResult, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = photoResult.createdAt;
        }
        if ((i & 2) != 0) {
            str2 = photoResult.fileBase64;
        }
        return photoResult.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFileBase64() {
        return this.fileBase64;
    }

    public final PhotoResult copy(String createdAt, String fileBase64) {
        return new PhotoResult(createdAt, fileBase64);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhotoResult)) {
            return false;
        }
        PhotoResult photoResult = (PhotoResult) other;
        return Intrinsics.areEqual(this.createdAt, photoResult.createdAt) && Intrinsics.areEqual(this.fileBase64, photoResult.fileBase64);
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getFileBase64() {
        return this.fileBase64;
    }

    public int hashCode() {
        return this.fileBase64.hashCode() + (this.createdAt.hashCode() * 31);
    }

    public String toString() {
        return tfwhgw.rnigpa(85) + this.createdAt + tfwhgw.rnigpa(86) + this.fileBase64 + ')';
    }
}
