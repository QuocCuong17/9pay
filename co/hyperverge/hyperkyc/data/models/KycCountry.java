package co.hyperverge.hyperkyc.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KycCountry.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000 02\u00020\u0001:\u00010BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003JU\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001J\u0013\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020%HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001J\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020%HÖ\u0001R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0013\"\u0004\b\u001a\u0010\u001b¨\u00061"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/KycCountry;", "Landroid/os/Parcelable;", "id", "", "name", "region", "documents", "", "Lco/hyperverge/hyperkyc/data/models/KycDocument;", "baseUrl", "enabled", "", FormFragment.KEY_SELECTED, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZZ)V", "getBaseUrl", "()Ljava/lang/String;", "getDocuments", "()Ljava/util/List;", "getEnabled", "()Z", "getId", "setId", "(Ljava/lang/String;)V", "getName", "getRegion", "getSelected", "setSelected", "(Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class KycCountry implements Parcelable {

    @SerializedName("base_url")
    private final String baseUrl;
    private final List<KycDocument> documents;
    private final boolean enabled;
    private String id;
    private final String name;
    private final String region;
    private boolean selected;
    public static final Parcelable.Creator<KycCountry> CREATOR = new Creator();

    /* compiled from: KycCountry.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<KycCountry> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final KycCountry createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i = 0; i != readInt; i++) {
                arrayList.add(KycDocument.CREATOR.createFromParcel(parcel));
            }
            return new KycCountry(readString, readString2, readString3, arrayList, parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final KycCountry[] newArray(int i) {
            return new KycCountry[i];
        }
    }

    public static /* synthetic */ KycCountry copy$default(KycCountry kycCountry, String str, String str2, String str3, List list, String str4, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = kycCountry.id;
        }
        if ((i & 2) != 0) {
            str2 = kycCountry.name;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = kycCountry.region;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            list = kycCountry.documents;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            str4 = kycCountry.baseUrl;
        }
        String str7 = str4;
        if ((i & 32) != 0) {
            z = kycCountry.enabled;
        }
        boolean z3 = z;
        if ((i & 64) != 0) {
            z2 = kycCountry.selected;
        }
        return kycCountry.copy(str, str5, str6, list2, str7, z3, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRegion() {
        return this.region;
    }

    public final List<KycDocument> component4() {
        return this.documents;
    }

    /* renamed from: component5, reason: from getter */
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getSelected() {
        return this.selected;
    }

    public final KycCountry copy(String id2, String name, String region, List<KycDocument> documents, String baseUrl, boolean enabled, boolean selected) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(documents, "documents");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        return new KycCountry(id2, name, region, documents, baseUrl, enabled, selected);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KycCountry)) {
            return false;
        }
        KycCountry kycCountry = (KycCountry) other;
        return Intrinsics.areEqual(this.id, kycCountry.id) && Intrinsics.areEqual(this.name, kycCountry.name) && Intrinsics.areEqual(this.region, kycCountry.region) && Intrinsics.areEqual(this.documents, kycCountry.documents) && Intrinsics.areEqual(this.baseUrl, kycCountry.baseUrl) && this.enabled == kycCountry.enabled && this.selected == kycCountry.selected;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.region.hashCode()) * 31) + this.documents.hashCode()) * 31) + this.baseUrl.hashCode()) * 31;
        boolean z = this.enabled;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.selected;
        return i2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "KycCountry(id=" + this.id + ", name=" + this.name + ", region=" + this.region + ", documents=" + this.documents + ", baseUrl=" + this.baseUrl + ", enabled=" + this.enabled + ", selected=" + this.selected + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.region);
        List<KycDocument> list = this.documents;
        parcel.writeInt(list.size());
        Iterator<KycDocument> it = list.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(parcel, flags);
        }
        parcel.writeString(this.baseUrl);
        parcel.writeInt(this.enabled ? 1 : 0);
        parcel.writeInt(this.selected ? 1 : 0);
    }

    public KycCountry(String id2, String name, String region, List<KycDocument> documents, String baseUrl, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(documents, "documents");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        this.id = id2;
        this.name = name;
        this.region = region;
        this.documents = documents;
        this.baseUrl = baseUrl;
        this.enabled = z;
        this.selected = z2;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final String getRegion() {
        return this.region;
    }

    public /* synthetic */ KycCountry(String str, String str2, String str3, List list, String str4, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? "" : str4, (i & 32) != 0 ? true : z, (i & 64) != 0 ? false : z2);
    }

    public final List<KycDocument> getDocuments() {
        return this.documents;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
    }
}
