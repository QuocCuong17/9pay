package co.hyperverge.hyperkyc.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HyperKycFlow.kt */
@Deprecated(message = "create workflows from dashboard instead")
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001f\b\u0016\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bB'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\u0006\u0010\u0018\u001a\u00020\u0003J\t\u0010\u0019\u001a\u00020\u0014HÖ\u0001J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/DocFlowConfig;", "Landroid/os/Parcelable;", "useForFaceMatch", "", "(Z)V", AnalyticsLogger.Keys.COUNTRY_ID, "", AnalyticsLogger.Keys.DOCUMENT_ID, "(Ljava/lang/String;Ljava/lang/String;)V", "(ZLjava/lang/String;Ljava/lang/String;)V", "getCountryId", "()Ljava/lang/String;", "getDocumentId", "getUseForFaceMatch", "()Z", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hasCountryDocIds", "hashCode", "toLabel", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocFlowConfig implements Parcelable {
    public static final Parcelable.Creator<DocFlowConfig> CREATOR = new Creator();
    private final String countryId;
    private final String documentId;
    private final boolean useForFaceMatch;

    /* compiled from: HyperKycFlow.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<DocFlowConfig> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocFlowConfig createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new DocFlowConfig(parcel.readInt() != 0, parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocFlowConfig[] newArray(int i) {
            return new DocFlowConfig[i];
        }
    }

    public DocFlowConfig() {
        this(false, null, null, 7, null);
    }

    public static /* synthetic */ DocFlowConfig copy$default(DocFlowConfig docFlowConfig, boolean z, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = docFlowConfig.useForFaceMatch;
        }
        if ((i & 2) != 0) {
            str = docFlowConfig.countryId;
        }
        if ((i & 4) != 0) {
            str2 = docFlowConfig.documentId;
        }
        return docFlowConfig.copy(z, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getUseForFaceMatch() {
        return this.useForFaceMatch;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCountryId() {
        return this.countryId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDocumentId() {
        return this.documentId;
    }

    public final DocFlowConfig copy(boolean useForFaceMatch, String countryId, String documentId) {
        return new DocFlowConfig(useForFaceMatch, countryId, documentId);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocFlowConfig)) {
            return false;
        }
        DocFlowConfig docFlowConfig = (DocFlowConfig) other;
        return this.useForFaceMatch == docFlowConfig.useForFaceMatch && Intrinsics.areEqual(this.countryId, docFlowConfig.countryId) && Intrinsics.areEqual(this.documentId, docFlowConfig.documentId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.useForFaceMatch;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.countryId;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.documentId;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "DocFlowConfig(useForFaceMatch=" + this.useForFaceMatch + ", countryId=" + this.countryId + ", documentId=" + this.documentId + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.useForFaceMatch ? 1 : 0);
        parcel.writeString(this.countryId);
        parcel.writeString(this.documentId);
    }

    public DocFlowConfig(boolean z, String str, String str2) {
        this.useForFaceMatch = z;
        this.countryId = str;
        this.documentId = str2;
    }

    public /* synthetic */ DocFlowConfig(boolean z, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2);
    }

    public final boolean getUseForFaceMatch() {
        return this.useForFaceMatch;
    }

    public final String getCountryId() {
        return this.countryId;
    }

    public final String getDocumentId() {
        return this.documentId;
    }

    public DocFlowConfig(boolean z) {
        this(z, null, null);
    }

    public /* synthetic */ DocFlowConfig(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public DocFlowConfig(String str, String str2) {
        this(false, str, str2);
    }

    public final boolean hasCountryDocIds() {
        String str = this.documentId;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        String str2 = this.countryId;
        return !(str2 == null || StringsKt.isBlank(str2));
    }

    public final String toLabel() {
        if (!hasCountryDocIds()) {
            return null;
        }
        return this.documentId + " (" + this.countryId + ')';
    }
}
