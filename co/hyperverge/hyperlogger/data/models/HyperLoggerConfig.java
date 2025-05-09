package co.hyperverge.hyperlogger.data.models;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HyperLoggerConfig.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lco/hyperverge/hyperlogger/data/models/HyperLoggerConfig;", "Ljava/io/Serializable;", "fileName", "", "url", "(Ljava/lang/String;Ljava/lang/String;)V", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "getUrl", "setUrl", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperlogger_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class HyperLoggerConfig implements Serializable {
    private String fileName;
    private String url;

    public static /* synthetic */ HyperLoggerConfig copy$default(HyperLoggerConfig hyperLoggerConfig, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hyperLoggerConfig.fileName;
        }
        if ((i & 2) != 0) {
            str2 = hyperLoggerConfig.url;
        }
        return hyperLoggerConfig.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final HyperLoggerConfig copy(String fileName, String url) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new HyperLoggerConfig(fileName, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HyperLoggerConfig)) {
            return false;
        }
        HyperLoggerConfig hyperLoggerConfig = (HyperLoggerConfig) other;
        return Intrinsics.areEqual(this.fileName, hyperLoggerConfig.fileName) && Intrinsics.areEqual(this.url, hyperLoggerConfig.url);
    }

    public int hashCode() {
        int hashCode = this.fileName.hashCode() * 31;
        String str = this.url;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "HyperLoggerConfig(fileName=" + this.fileName + ", url=" + ((Object) this.url) + ')';
    }

    public HyperLoggerConfig(String fileName, String str) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.fileName = fileName;
        this.url = str;
    }

    public /* synthetic */ HyperLoggerConfig(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final void setFileName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fileName = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }
}
