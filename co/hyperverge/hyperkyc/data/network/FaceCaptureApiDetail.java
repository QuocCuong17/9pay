package co.hyperverge.hyperkyc.data.network;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: responses.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001*BM\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003JQ\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u001dHÖ\u0001J\u0006\u0010#\u001a\u00020\u001fJ\t\u0010$\u001a\u00020\u0005HÖ\u0001J\u0019\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001dHÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006+"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/FaceCaptureApiDetail;", "Landroid/os/Parcelable;", "liveFace", "Lco/hyperverge/hyperkyc/data/network/FieldValue;", "live", "", "livenessScore", "toBeReviewed", "qualityChecks", "Lco/hyperverge/hyperkyc/data/network/FaceCaptureApiDetail$QualityChecks;", "error", "(Lco/hyperverge/hyperkyc/data/network/FieldValue;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/network/FaceCaptureApiDetail$QualityChecks;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getLive", "getLiveFace", "()Lco/hyperverge/hyperkyc/data/network/FieldValue;", "getLivenessScore", "getQualityChecks", "()Lco/hyperverge/hyperkyc/data/network/FaceCaptureApiDetail$QualityChecks;", "getToBeReviewed", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "isFaceLive", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "QualityChecks", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FaceCaptureApiDetail implements Parcelable {
    public static final Parcelable.Creator<FaceCaptureApiDetail> CREATOR = new Creator();
    private final String error;
    private final String live;
    private final FieldValue liveFace;

    @SerializedName("liveness-score")
    private final String livenessScore;
    private final QualityChecks qualityChecks;

    @SerializedName("to-be-reviewed")
    private final String toBeReviewed;

    /* compiled from: responses.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<FaceCaptureApiDetail> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FaceCaptureApiDetail createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FaceCaptureApiDetail(parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? QualityChecks.CREATOR.createFromParcel(parcel) : null, parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FaceCaptureApiDetail[] newArray(int i) {
            return new FaceCaptureApiDetail[i];
        }
    }

    public FaceCaptureApiDetail() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ FaceCaptureApiDetail copy$default(FaceCaptureApiDetail faceCaptureApiDetail, FieldValue fieldValue, String str, String str2, String str3, QualityChecks qualityChecks, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            fieldValue = faceCaptureApiDetail.liveFace;
        }
        if ((i & 2) != 0) {
            str = faceCaptureApiDetail.live;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = faceCaptureApiDetail.livenessScore;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = faceCaptureApiDetail.toBeReviewed;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            qualityChecks = faceCaptureApiDetail.qualityChecks;
        }
        QualityChecks qualityChecks2 = qualityChecks;
        if ((i & 32) != 0) {
            str4 = faceCaptureApiDetail.error;
        }
        return faceCaptureApiDetail.copy(fieldValue, str5, str6, str7, qualityChecks2, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final FieldValue getLiveFace() {
        return this.liveFace;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLive() {
        return this.live;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLivenessScore() {
        return this.livenessScore;
    }

    /* renamed from: component4, reason: from getter */
    public final String getToBeReviewed() {
        return this.toBeReviewed;
    }

    /* renamed from: component5, reason: from getter */
    public final QualityChecks getQualityChecks() {
        return this.qualityChecks;
    }

    /* renamed from: component6, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final FaceCaptureApiDetail copy(FieldValue liveFace, String live, String livenessScore, String toBeReviewed, QualityChecks qualityChecks, String error) {
        return new FaceCaptureApiDetail(liveFace, live, livenessScore, toBeReviewed, qualityChecks, error);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceCaptureApiDetail)) {
            return false;
        }
        FaceCaptureApiDetail faceCaptureApiDetail = (FaceCaptureApiDetail) other;
        return Intrinsics.areEqual(this.liveFace, faceCaptureApiDetail.liveFace) && Intrinsics.areEqual(this.live, faceCaptureApiDetail.live) && Intrinsics.areEqual(this.livenessScore, faceCaptureApiDetail.livenessScore) && Intrinsics.areEqual(this.toBeReviewed, faceCaptureApiDetail.toBeReviewed) && Intrinsics.areEqual(this.qualityChecks, faceCaptureApiDetail.qualityChecks) && Intrinsics.areEqual(this.error, faceCaptureApiDetail.error);
    }

    public int hashCode() {
        FieldValue fieldValue = this.liveFace;
        int hashCode = (fieldValue == null ? 0 : fieldValue.hashCode()) * 31;
        String str = this.live;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.livenessScore;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.toBeReviewed;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        QualityChecks qualityChecks = this.qualityChecks;
        int hashCode5 = (hashCode4 + (qualityChecks == null ? 0 : qualityChecks.hashCode())) * 31;
        String str4 = this.error;
        return hashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "FaceCaptureApiDetail(liveFace=" + this.liveFace + ", live=" + this.live + ", livenessScore=" + this.livenessScore + ", toBeReviewed=" + this.toBeReviewed + ", qualityChecks=" + this.qualityChecks + ", error=" + this.error + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        FieldValue fieldValue = this.liveFace;
        if (fieldValue == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            fieldValue.writeToParcel(parcel, flags);
        }
        parcel.writeString(this.live);
        parcel.writeString(this.livenessScore);
        parcel.writeString(this.toBeReviewed);
        QualityChecks qualityChecks = this.qualityChecks;
        if (qualityChecks == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            qualityChecks.writeToParcel(parcel, flags);
        }
        parcel.writeString(this.error);
    }

    public FaceCaptureApiDetail(FieldValue fieldValue, String str, String str2, String str3, QualityChecks qualityChecks, String str4) {
        this.liveFace = fieldValue;
        this.live = str;
        this.livenessScore = str2;
        this.toBeReviewed = str3;
        this.qualityChecks = qualityChecks;
        this.error = str4;
    }

    public /* synthetic */ FaceCaptureApiDetail(FieldValue fieldValue, String str, String str2, String str3, QualityChecks qualityChecks, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : fieldValue, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : qualityChecks, (i & 32) != 0 ? null : str4);
    }

    public final FieldValue getLiveFace() {
        return this.liveFace;
    }

    public final String getLive() {
        return this.live;
    }

    public final String getLivenessScore() {
        return this.livenessScore;
    }

    public final String getToBeReviewed() {
        return this.toBeReviewed;
    }

    public final QualityChecks getQualityChecks() {
        return this.qualityChecks;
    }

    public final String getError() {
        return this.error;
    }

    public final boolean isFaceLive() {
        if (!Intrinsics.areEqual(this.live, "yes")) {
            FieldValue fieldValue = this.liveFace;
            if (!Intrinsics.areEqual(fieldValue != null ? fieldValue.getValue() : null, "yes")) {
                return false;
            }
        }
        return true;
    }

    /* compiled from: responses.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006 "}, d2 = {"Lco/hyperverge/hyperkyc/data/network/FaceCaptureApiDetail$QualityChecks;", "Landroid/os/Parcelable;", "eyesClosed", "Lco/hyperverge/hyperkyc/data/network/FieldValue;", "maskPresent", "multipleFaces", "blur", "(Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;)V", "getBlur", "()Lco/hyperverge/hyperkyc/data/network/FieldValue;", "getEyesClosed", "getMaskPresent", "getMultipleFaces", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class QualityChecks implements Parcelable {
        public static final Parcelable.Creator<QualityChecks> CREATOR = new Creator();
        private final FieldValue blur;
        private final FieldValue eyesClosed;
        private final FieldValue maskPresent;
        private final FieldValue multipleFaces;

        /* compiled from: responses.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<QualityChecks> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final QualityChecks createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new QualityChecks(parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() != 0 ? FieldValue.CREATOR.createFromParcel(parcel) : null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final QualityChecks[] newArray(int i) {
                return new QualityChecks[i];
            }
        }

        public QualityChecks() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ QualityChecks copy$default(QualityChecks qualityChecks, FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4, int i, Object obj) {
            if ((i & 1) != 0) {
                fieldValue = qualityChecks.eyesClosed;
            }
            if ((i & 2) != 0) {
                fieldValue2 = qualityChecks.maskPresent;
            }
            if ((i & 4) != 0) {
                fieldValue3 = qualityChecks.multipleFaces;
            }
            if ((i & 8) != 0) {
                fieldValue4 = qualityChecks.blur;
            }
            return qualityChecks.copy(fieldValue, fieldValue2, fieldValue3, fieldValue4);
        }

        /* renamed from: component1, reason: from getter */
        public final FieldValue getEyesClosed() {
            return this.eyesClosed;
        }

        /* renamed from: component2, reason: from getter */
        public final FieldValue getMaskPresent() {
            return this.maskPresent;
        }

        /* renamed from: component3, reason: from getter */
        public final FieldValue getMultipleFaces() {
            return this.multipleFaces;
        }

        /* renamed from: component4, reason: from getter */
        public final FieldValue getBlur() {
            return this.blur;
        }

        public final QualityChecks copy(FieldValue eyesClosed, FieldValue maskPresent, FieldValue multipleFaces, FieldValue blur) {
            return new QualityChecks(eyesClosed, maskPresent, multipleFaces, blur);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QualityChecks)) {
                return false;
            }
            QualityChecks qualityChecks = (QualityChecks) other;
            return Intrinsics.areEqual(this.eyesClosed, qualityChecks.eyesClosed) && Intrinsics.areEqual(this.maskPresent, qualityChecks.maskPresent) && Intrinsics.areEqual(this.multipleFaces, qualityChecks.multipleFaces) && Intrinsics.areEqual(this.blur, qualityChecks.blur);
        }

        public int hashCode() {
            FieldValue fieldValue = this.eyesClosed;
            int hashCode = (fieldValue == null ? 0 : fieldValue.hashCode()) * 31;
            FieldValue fieldValue2 = this.maskPresent;
            int hashCode2 = (hashCode + (fieldValue2 == null ? 0 : fieldValue2.hashCode())) * 31;
            FieldValue fieldValue3 = this.multipleFaces;
            int hashCode3 = (hashCode2 + (fieldValue3 == null ? 0 : fieldValue3.hashCode())) * 31;
            FieldValue fieldValue4 = this.blur;
            return hashCode3 + (fieldValue4 != null ? fieldValue4.hashCode() : 0);
        }

        public String toString() {
            return "QualityChecks(eyesClosed=" + this.eyesClosed + ", maskPresent=" + this.maskPresent + ", multipleFaces=" + this.multipleFaces + ", blur=" + this.blur + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            FieldValue fieldValue = this.eyesClosed;
            if (fieldValue == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue2 = this.maskPresent;
            if (fieldValue2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue2.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue3 = this.multipleFaces;
            if (fieldValue3 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue3.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue4 = this.blur;
            if (fieldValue4 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue4.writeToParcel(parcel, flags);
            }
        }

        public QualityChecks(FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4) {
            this.eyesClosed = fieldValue;
            this.maskPresent = fieldValue2;
            this.multipleFaces = fieldValue3;
            this.blur = fieldValue4;
        }

        public /* synthetic */ QualityChecks(FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : fieldValue, (i & 2) != 0 ? null : fieldValue2, (i & 4) != 0 ? null : fieldValue3, (i & 8) != 0 ? null : fieldValue4);
        }

        public final FieldValue getEyesClosed() {
            return this.eyesClosed;
        }

        public final FieldValue getMaskPresent() {
            return this.maskPresent;
        }

        public final FieldValue getMultipleFaces() {
            return this.multipleFaces;
        }

        public final FieldValue getBlur() {
            return this.blur;
        }
    }
}
