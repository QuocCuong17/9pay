package co.hyperverge.hyperkyc.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KycDocument.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 62\u00020\u00012\u00020\u0002:\u000267BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010$\u001a\u00020\u0004HÆ\u0003J\u000e\u0010%\u001a\u00020\fHÀ\u0003¢\u0006\u0002\b&JM\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\t\u0010(\u001a\u00020\u0014HÖ\u0001J\u000e\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u0004J\u0013\u0010+\u001a\u00020\f2\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020\u0014HÖ\u0001J\u000e\u0010/\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u0004J\t\u00100\u001a\u00020\u0004HÖ\u0001J\u0019\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u000b\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000f¨\u00068"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/KycDocument;", "Landroid/os/Parcelable;", "Ljava/io/Serializable;", "id", "", "name", "sides", "", "sidesConfig", "Lco/hyperverge/hyperkyc/data/models/KycDocument$SidesConfig;", "type", FormFragment.KEY_SELECTED, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lco/hyperverge/hyperkyc/data/models/KycDocument$SidesConfig;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getName", "numberOfSides", "", "getNumberOfSides", "()I", "getSelected$hyperkyc_release", "()Z", "setSelected$hyperkyc_release", "(Z)V", "getSides", "()Ljava/util/List;", "getSidesConfig", "()Lco/hyperverge/hyperkyc/data/models/KycDocument$SidesConfig;", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component6$hyperkyc_release", "copy", "describeContents", "disableOCR", "side", "equals", "other", "", "hashCode", "readBarcode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "SidesConfig", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class KycDocument implements Parcelable, Serializable {
    public static final /* synthetic */ String ARG_KEY_DOCUMENTS = "documents";
    private String id;
    private final String name;
    private boolean selected;
    private final List<String> sides;
    private final SidesConfig sidesConfig;
    private final String type;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<KycDocument> CREATOR = new Creator();

    /* compiled from: KycDocument.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<KycDocument> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final KycDocument createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new KycDocument(parcel.readString(), parcel.readString(), parcel.createStringArrayList(), parcel.readInt() == 0 ? null : SidesConfig.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final KycDocument[] newArray(int i) {
            return new KycDocument[i];
        }
    }

    public static /* synthetic */ KycDocument copy$default(KycDocument kycDocument, String str, String str2, List list, SidesConfig sidesConfig, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = kycDocument.id;
        }
        if ((i & 2) != 0) {
            str2 = kycDocument.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            list = kycDocument.sides;
        }
        List list2 = list;
        if ((i & 8) != 0) {
            sidesConfig = kycDocument.sidesConfig;
        }
        SidesConfig sidesConfig2 = sidesConfig;
        if ((i & 16) != 0) {
            str3 = kycDocument.type;
        }
        String str5 = str3;
        if ((i & 32) != 0) {
            z = kycDocument.selected;
        }
        return kycDocument.copy(str, str4, list2, sidesConfig2, str5, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component3() {
        return this.sides;
    }

    /* renamed from: component4, reason: from getter */
    public final SidesConfig getSidesConfig() {
        return this.sidesConfig;
    }

    /* renamed from: component5, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component6$hyperkyc_release, reason: from getter */
    public final boolean getSelected() {
        return this.selected;
    }

    public final KycDocument copy(String id2, String name, List<String> sides, SidesConfig sidesConfig, String type, boolean selected) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sides, "sides");
        Intrinsics.checkNotNullParameter(type, "type");
        return new KycDocument(id2, name, sides, sidesConfig, type, selected);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KycDocument)) {
            return false;
        }
        KycDocument kycDocument = (KycDocument) other;
        return Intrinsics.areEqual(this.id, kycDocument.id) && Intrinsics.areEqual(this.name, kycDocument.name) && Intrinsics.areEqual(this.sides, kycDocument.sides) && Intrinsics.areEqual(this.sidesConfig, kycDocument.sidesConfig) && Intrinsics.areEqual(this.type, kycDocument.type) && this.selected == kycDocument.selected;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.sides.hashCode()) * 31;
        SidesConfig sidesConfig = this.sidesConfig;
        int hashCode2 = (((hashCode + (sidesConfig == null ? 0 : sidesConfig.hashCode())) * 31) + this.type.hashCode()) * 31;
        boolean z = this.selected;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "KycDocument(id=" + this.id + ", name=" + this.name + ", sides=" + this.sides + ", sidesConfig=" + this.sidesConfig + ", type=" + this.type + ", selected=" + this.selected + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeStringList(this.sides);
        SidesConfig sidesConfig = this.sidesConfig;
        if (sidesConfig == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            sidesConfig.writeToParcel(parcel, flags);
        }
        parcel.writeString(this.type);
        parcel.writeInt(this.selected ? 1 : 0);
    }

    public KycDocument(String id2, String name, List<String> sides, SidesConfig sidesConfig, String type, boolean z) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sides, "sides");
        Intrinsics.checkNotNullParameter(type, "type");
        this.id = id2;
        this.name = name;
        this.sides = sides;
        this.sidesConfig = sidesConfig;
        this.type = type;
        this.selected = z;
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

    public /* synthetic */ KycDocument(String str, String str2, List list, SidesConfig sidesConfig, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? CollectionsKt.listOf("front") : list, (i & 8) != 0 ? null : sidesConfig, (i & 16) != 0 ? "card" : str3, (i & 32) != 0 ? false : z);
    }

    public final List<String> getSides() {
        return this.sides;
    }

    public final SidesConfig getSidesConfig() {
        return this.sidesConfig;
    }

    public final String getType() {
        return this.type;
    }

    public final /* synthetic */ boolean getSelected$hyperkyc_release() {
        return this.selected;
    }

    public final /* synthetic */ void setSelected$hyperkyc_release(boolean z) {
        this.selected = z;
    }

    /* compiled from: KycDocument.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J-\u0010\r\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fHÖ\u0001R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u001b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/KycDocument$SidesConfig;", "Landroid/os/Parcelable;", "Ljava/io/Serializable;", "readBarcode", "", "", "disableOCR", "(Ljava/util/List;Ljava/util/List;)V", "getDisableOCR", "()Ljava/util/List;", "getReadBarcode", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class SidesConfig implements Parcelable, Serializable {
        public static final Parcelable.Creator<SidesConfig> CREATOR = new Creator();
        private final List<String> disableOCR;
        private final List<String> readBarcode;

        /* compiled from: KycDocument.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<SidesConfig> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final SidesConfig createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new SidesConfig(parcel.createStringArrayList(), parcel.createStringArrayList());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final SidesConfig[] newArray(int i) {
                return new SidesConfig[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SidesConfig() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SidesConfig copy$default(SidesConfig sidesConfig, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = sidesConfig.readBarcode;
            }
            if ((i & 2) != 0) {
                list2 = sidesConfig.disableOCR;
            }
            return sidesConfig.copy(list, list2);
        }

        public final List<String> component1() {
            return this.readBarcode;
        }

        public final List<String> component2() {
            return this.disableOCR;
        }

        public final SidesConfig copy(List<String> readBarcode, List<String> disableOCR) {
            return new SidesConfig(readBarcode, disableOCR);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SidesConfig)) {
                return false;
            }
            SidesConfig sidesConfig = (SidesConfig) other;
            return Intrinsics.areEqual(this.readBarcode, sidesConfig.readBarcode) && Intrinsics.areEqual(this.disableOCR, sidesConfig.disableOCR);
        }

        public int hashCode() {
            List<String> list = this.readBarcode;
            int hashCode = (list == null ? 0 : list.hashCode()) * 31;
            List<String> list2 = this.disableOCR;
            return hashCode + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "SidesConfig(readBarcode=" + this.readBarcode + ", disableOCR=" + this.disableOCR + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeStringList(this.readBarcode);
            parcel.writeStringList(this.disableOCR);
        }

        public SidesConfig(List<String> list, List<String> list2) {
            this.readBarcode = list;
            this.disableOCR = list2;
        }

        public /* synthetic */ SidesConfig(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2);
        }

        public final List<String> getReadBarcode() {
            return this.readBarcode;
        }

        public final List<String> getDisableOCR() {
            return this.disableOCR;
        }
    }

    public final int getNumberOfSides() {
        return this.sides.size();
    }

    public final boolean readBarcode(String side) {
        List<String> readBarcode;
        Intrinsics.checkNotNullParameter(side, "side");
        SidesConfig sidesConfig = this.sidesConfig;
        return (sidesConfig == null || (readBarcode = sidesConfig.getReadBarcode()) == null || !readBarcode.contains(side)) ? false : true;
    }

    public final boolean disableOCR(String side) {
        List<String> disableOCR;
        Intrinsics.checkNotNullParameter(side, "side");
        SidesConfig sidesConfig = this.sidesConfig;
        return (sidesConfig == null || (disableOCR = sidesConfig.getDisableOCR()) == null || !disableOCR.contains(side)) ? false : true;
    }

    /* compiled from: KycDocument.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/KycDocument$Companion;", "", "()V", "ARG_KEY_DOCUMENTS", "", "getDummy", "", "Lco/hyperverge/hyperkyc/data/models/KycDocument;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final /* synthetic */ List getDummy() {
            SidesConfig sidesConfig = null;
            String str = null;
            boolean z = false;
            DefaultConstructorMarker defaultConstructorMarker = null;
            List list = null;
            int i = 60;
            return CollectionsKt.listOf((Object[]) new KycDocument[]{new KycDocument("id", "Aadhaar", CollectionsKt.listOf((Object[]) new String[]{"front", "back"}), null, null, false, 56, null), new KycDocument("voterid", "Voter ID", CollectionsKt.listOf((Object[]) new String[]{"front", "back"}), sidesConfig, str, z, 56, defaultConstructorMarker), new KycDocument(CmcdConfiguration.KEY_DEADLINE, "Driver License", list, sidesConfig, str, z, i, defaultConstructorMarker), new KycDocument("pan", "PAN", list, sidesConfig, str, z, i, defaultConstructorMarker), new KycDocument("passport", "Passport", CollectionsKt.listOf((Object[]) new String[]{"front", "back"}), sidesConfig, str, z, 56, defaultConstructorMarker)});
        }
    }
}
