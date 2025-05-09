package co.hyperverge.hyperkyc.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* compiled from: HyperKycFlow.kt */
@Deprecated(message = "create workflows from dashboard instead")
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/HyperKycFlow;", "Landroid/os/Parcelable;", "()V", "Api", StandardStructureTypes.DOCUMENT, HVRetakeActivity.FACE_CALLING_ACTIVITY_VALUE, "Lco/hyperverge/hyperkyc/data/models/HyperKycFlow$Api;", "Lco/hyperverge/hyperkyc/data/models/HyperKycFlow$Document;", "Lco/hyperverge/hyperkyc/data/models/HyperKycFlow$Face;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class HyperKycFlow implements Parcelable {
    public /* synthetic */ HyperKycFlow(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private HyperKycFlow() {
    }

    /* compiled from: HyperKycFlow.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\fHÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fHÖ\u0001R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0019"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/HyperKycFlow$Document;", "Lco/hyperverge/hyperkyc/data/models/HyperKycFlow;", "Landroid/os/Parcelable;", HVRetakeActivity.CONFIG_TAG, "Lco/hyperverge/hyperkyc/data/models/DocFlowConfig;", "(Lco/hyperverge/hyperkyc/data/models/DocFlowConfig;)V", "getConfig", "()Lco/hyperverge/hyperkyc/data/models/DocFlowConfig;", "setConfig", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Document extends HyperKycFlow implements Parcelable {
        public static final Parcelable.Creator<Document> CREATOR = new Creator();
        private DocFlowConfig config;

        /* compiled from: HyperKycFlow.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<Document> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Document createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Document(parcel.readInt() == 0 ? null : DocFlowConfig.CREATOR.createFromParcel(parcel));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Document[] newArray(int i) {
                return new Document[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Document() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Document copy$default(Document document, DocFlowConfig docFlowConfig, int i, Object obj) {
            if ((i & 1) != 0) {
                docFlowConfig = document.config;
            }
            return document.copy(docFlowConfig);
        }

        /* renamed from: component1, reason: from getter */
        public final DocFlowConfig getConfig() {
            return this.config;
        }

        public final Document copy(DocFlowConfig config) {
            return new Document(config);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Document) && Intrinsics.areEqual(this.config, ((Document) other).config);
        }

        public int hashCode() {
            DocFlowConfig docFlowConfig = this.config;
            if (docFlowConfig == null) {
                return 0;
            }
            return docFlowConfig.hashCode();
        }

        public String toString() {
            return "Document(config=" + this.config + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            DocFlowConfig docFlowConfig = this.config;
            if (docFlowConfig == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                docFlowConfig.writeToParcel(parcel, flags);
            }
        }

        public /* synthetic */ Document(DocFlowConfig docFlowConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : docFlowConfig);
        }

        public final DocFlowConfig getConfig() {
            return this.config;
        }

        public final void setConfig(DocFlowConfig docFlowConfig) {
            this.config = docFlowConfig;
        }

        public Document(DocFlowConfig docFlowConfig) {
            super(null);
            this.config = docFlowConfig;
        }
    }

    /* compiled from: HyperKycFlow.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bHÖ\u0001R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/HyperKycFlow$Face;", "Lco/hyperverge/hyperkyc/data/models/HyperKycFlow;", "Landroid/os/Parcelable;", HVRetakeActivity.CONFIG_TAG, "Lco/hyperverge/hyperkyc/data/models/FaceFlowConfig;", "(Lco/hyperverge/hyperkyc/data/models/FaceFlowConfig;)V", "getConfig", "()Lco/hyperverge/hyperkyc/data/models/FaceFlowConfig;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Face extends HyperKycFlow implements Parcelable {
        public static final Parcelable.Creator<Face> CREATOR = new Creator();
        private final FaceFlowConfig config;

        /* compiled from: HyperKycFlow.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<Face> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Face createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Face(parcel.readInt() == 0 ? null : FaceFlowConfig.CREATOR.createFromParcel(parcel));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Face[] newArray(int i) {
                return new Face[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Face() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Face copy$default(Face face, FaceFlowConfig faceFlowConfig, int i, Object obj) {
            if ((i & 1) != 0) {
                faceFlowConfig = face.config;
            }
            return face.copy(faceFlowConfig);
        }

        /* renamed from: component1, reason: from getter */
        public final FaceFlowConfig getConfig() {
            return this.config;
        }

        public final Face copy(FaceFlowConfig config) {
            return new Face(config);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Face) && Intrinsics.areEqual(this.config, ((Face) other).config);
        }

        public int hashCode() {
            FaceFlowConfig faceFlowConfig = this.config;
            if (faceFlowConfig == null) {
                return 0;
            }
            return faceFlowConfig.hashCode();
        }

        public String toString() {
            return "Face(config=" + this.config + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            FaceFlowConfig faceFlowConfig = this.config;
            if (faceFlowConfig == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                faceFlowConfig.writeToParcel(parcel, flags);
            }
        }

        public Face(FaceFlowConfig faceFlowConfig) {
            super(null);
            this.config = faceFlowConfig;
        }

        public /* synthetic */ Face(FaceFlowConfig faceFlowConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : faceFlowConfig);
        }

        public final FaceFlowConfig getConfig() {
            return this.config;
        }
    }

    /* compiled from: HyperKycFlow.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bHÖ\u0001R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/HyperKycFlow$Api;", "Lco/hyperverge/hyperkyc/data/models/HyperKycFlow;", "Landroid/os/Parcelable;", HVRetakeActivity.CONFIG_TAG, "Lco/hyperverge/hyperkyc/data/models/ApiFlowConfig;", "(Lco/hyperverge/hyperkyc/data/models/ApiFlowConfig;)V", "getConfig", "()Lco/hyperverge/hyperkyc/data/models/ApiFlowConfig;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Api extends HyperKycFlow implements Parcelable {
        public static final Parcelable.Creator<Api> CREATOR = new Creator();
        private final ApiFlowConfig config;

        /* compiled from: HyperKycFlow.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<Api> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Api createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Api(parcel.readInt() == 0 ? null : ApiFlowConfig.CREATOR.createFromParcel(parcel));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Api[] newArray(int i) {
                return new Api[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Api() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Api copy$default(Api api, ApiFlowConfig apiFlowConfig, int i, Object obj) {
            if ((i & 1) != 0) {
                apiFlowConfig = api.config;
            }
            return api.copy(apiFlowConfig);
        }

        /* renamed from: component1, reason: from getter */
        public final ApiFlowConfig getConfig() {
            return this.config;
        }

        public final Api copy(ApiFlowConfig config) {
            return new Api(config);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Api) && Intrinsics.areEqual(this.config, ((Api) other).config);
        }

        public int hashCode() {
            ApiFlowConfig apiFlowConfig = this.config;
            if (apiFlowConfig == null) {
                return 0;
            }
            return apiFlowConfig.hashCode();
        }

        public String toString() {
            return "Api(config=" + this.config + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            ApiFlowConfig apiFlowConfig = this.config;
            if (apiFlowConfig == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                apiFlowConfig.writeToParcel(parcel, flags);
            }
        }

        public Api(ApiFlowConfig apiFlowConfig) {
            super(null);
            this.config = apiFlowConfig;
        }

        public /* synthetic */ Api(ApiFlowConfig apiFlowConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : apiFlowConfig);
        }

        public final ApiFlowConfig getConfig() {
            return this.config;
        }
    }
}
