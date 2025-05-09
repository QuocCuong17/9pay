package vn.ai.faceauth.sdk.presentation.domain.configs;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.firebase.dynamiclinks.DynamicLink;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/configs/Certificate;", "", DynamicLink.Builder.KEY_DOMAIN, "", "cert", "(Ljava/lang/String;Ljava/lang/String;)V", "getCert", "()Ljava/lang/String;", "setCert", "(Ljava/lang/String;)V", "getDomain", "setDomain", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class Certificate {
    private String cert;
    private String domain;

    public Certificate(String str, String str2) {
        this.domain = str;
        this.cert = str2;
    }

    public static /* synthetic */ Certificate copy$default(Certificate certificate, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = certificate.domain;
        }
        if ((i & 2) != 0) {
            str2 = certificate.cert;
        }
        return certificate.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDomain() {
        return this.domain;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCert() {
        return this.cert;
    }

    public final Certificate copy(String domain, String cert) {
        return new Certificate(domain, cert);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Certificate)) {
            return false;
        }
        Certificate certificate = (Certificate) other;
        return Intrinsics.areEqual(this.domain, certificate.domain) && Intrinsics.areEqual(this.cert, certificate.cert);
    }

    public final String getCert() {
        return this.cert;
    }

    public final String getDomain() {
        return this.domain;
    }

    public int hashCode() {
        return this.cert.hashCode() + (this.domain.hashCode() * 31);
    }

    public final void setCert(String str) {
        this.cert = str;
    }

    public final void setDomain(String str) {
        this.domain = str;
    }

    public String toString() {
        return tfwhgw.rnigpa(83) + this.domain + tfwhgw.rnigpa(84) + this.cert;
    }
}
