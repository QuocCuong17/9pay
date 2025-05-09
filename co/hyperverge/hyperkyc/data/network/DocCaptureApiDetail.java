package co.hyperverge.hyperkyc.data.network;

import android.os.Parcel;
import android.os.Parcelable;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.pqc.crypto.qteslarnd1.Parameter;

/* compiled from: responses.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0003%&'B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J9\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006("}, d2 = {"Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail;", "Landroid/os/Parcelable;", "idType", "", "fieldsExtracted", "Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted;", "qualityChecks", "Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$QualityChecks;", "ruleChecks", "Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$RuleChecks;", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted;Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$QualityChecks;Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$RuleChecks;)V", "getFieldsExtracted", "()Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted;", "getIdType", "()Ljava/lang/String;", "getQualityChecks", "()Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$QualityChecks;", "getRuleChecks", "()Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$RuleChecks;", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "FieldsExtracted", "QualityChecks", "RuleChecks", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocCaptureApiDetail implements Parcelable {
    public static final Parcelable.Creator<DocCaptureApiDetail> CREATOR = new Creator();
    private final FieldsExtracted fieldsExtracted;
    private final String idType;

    @SerializedName("qualityChecks")
    private final QualityChecks qualityChecks;
    private final RuleChecks ruleChecks;

    /* compiled from: responses.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<DocCaptureApiDetail> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocCaptureApiDetail createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new DocCaptureApiDetail(parcel.readString(), parcel.readInt() == 0 ? null : FieldsExtracted.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : QualityChecks.CREATOR.createFromParcel(parcel), parcel.readInt() != 0 ? RuleChecks.CREATOR.createFromParcel(parcel) : null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocCaptureApiDetail[] newArray(int i) {
            return new DocCaptureApiDetail[i];
        }
    }

    public DocCaptureApiDetail() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ DocCaptureApiDetail copy$default(DocCaptureApiDetail docCaptureApiDetail, String str, FieldsExtracted fieldsExtracted, QualityChecks qualityChecks, RuleChecks ruleChecks, int i, Object obj) {
        if ((i & 1) != 0) {
            str = docCaptureApiDetail.idType;
        }
        if ((i & 2) != 0) {
            fieldsExtracted = docCaptureApiDetail.fieldsExtracted;
        }
        if ((i & 4) != 0) {
            qualityChecks = docCaptureApiDetail.qualityChecks;
        }
        if ((i & 8) != 0) {
            ruleChecks = docCaptureApiDetail.ruleChecks;
        }
        return docCaptureApiDetail.copy(str, fieldsExtracted, qualityChecks, ruleChecks);
    }

    /* renamed from: component1, reason: from getter */
    public final String getIdType() {
        return this.idType;
    }

    /* renamed from: component2, reason: from getter */
    public final FieldsExtracted getFieldsExtracted() {
        return this.fieldsExtracted;
    }

    /* renamed from: component3, reason: from getter */
    public final QualityChecks getQualityChecks() {
        return this.qualityChecks;
    }

    /* renamed from: component4, reason: from getter */
    public final RuleChecks getRuleChecks() {
        return this.ruleChecks;
    }

    public final DocCaptureApiDetail copy(String idType, FieldsExtracted fieldsExtracted, QualityChecks qualityChecks, RuleChecks ruleChecks) {
        return new DocCaptureApiDetail(idType, fieldsExtracted, qualityChecks, ruleChecks);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocCaptureApiDetail)) {
            return false;
        }
        DocCaptureApiDetail docCaptureApiDetail = (DocCaptureApiDetail) other;
        return Intrinsics.areEqual(this.idType, docCaptureApiDetail.idType) && Intrinsics.areEqual(this.fieldsExtracted, docCaptureApiDetail.fieldsExtracted) && Intrinsics.areEqual(this.qualityChecks, docCaptureApiDetail.qualityChecks) && Intrinsics.areEqual(this.ruleChecks, docCaptureApiDetail.ruleChecks);
    }

    public int hashCode() {
        String str = this.idType;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        FieldsExtracted fieldsExtracted = this.fieldsExtracted;
        int hashCode2 = (hashCode + (fieldsExtracted == null ? 0 : fieldsExtracted.hashCode())) * 31;
        QualityChecks qualityChecks = this.qualityChecks;
        int hashCode3 = (hashCode2 + (qualityChecks == null ? 0 : qualityChecks.hashCode())) * 31;
        RuleChecks ruleChecks = this.ruleChecks;
        return hashCode3 + (ruleChecks != null ? ruleChecks.hashCode() : 0);
    }

    public String toString() {
        return "DocCaptureApiDetail(idType=" + this.idType + ", fieldsExtracted=" + this.fieldsExtracted + ", qualityChecks=" + this.qualityChecks + ", ruleChecks=" + this.ruleChecks + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.idType);
        FieldsExtracted fieldsExtracted = this.fieldsExtracted;
        if (fieldsExtracted == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            fieldsExtracted.writeToParcel(parcel, flags);
        }
        QualityChecks qualityChecks = this.qualityChecks;
        if (qualityChecks == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            qualityChecks.writeToParcel(parcel, flags);
        }
        RuleChecks ruleChecks = this.ruleChecks;
        if (ruleChecks == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            ruleChecks.writeToParcel(parcel, flags);
        }
    }

    public DocCaptureApiDetail(String str, FieldsExtracted fieldsExtracted, QualityChecks qualityChecks, RuleChecks ruleChecks) {
        this.idType = str;
        this.fieldsExtracted = fieldsExtracted;
        this.qualityChecks = qualityChecks;
        this.ruleChecks = ruleChecks;
    }

    public /* synthetic */ DocCaptureApiDetail(String str, FieldsExtracted fieldsExtracted, QualityChecks qualityChecks, RuleChecks ruleChecks, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : fieldsExtracted, (i & 4) != 0 ? null : qualityChecks, (i & 8) != 0 ? null : ruleChecks);
    }

    public final String getIdType() {
        return this.idType;
    }

    public final FieldsExtracted getFieldsExtracted() {
        return this.fieldsExtracted;
    }

    public final QualityChecks getQualityChecks() {
        return this.qualityChecks;
    }

    public final RuleChecks getRuleChecks() {
        return this.ruleChecks;
    }

    /* compiled from: responses.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001[B\u0099\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0002\u0010\u001bJ\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u001aHÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u009d\u0002\u0010L\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÆ\u0001J\t\u0010M\u001a\u00020NHÖ\u0001J\u0013\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010RHÖ\u0003J\t\u0010S\u001a\u00020NHÖ\u0001J\t\u0010T\u001a\u00020UHÖ\u0001J\u0019\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020NHÖ\u0001R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001fR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001f¨\u0006\\"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted;", "Landroid/os/Parcelable;", "idNumber", "Lco/hyperverge/hyperkyc/data/network/FieldValue;", "firstName", "middleName", "lastName", "fullName", "dateOfBirth", "dateOfIssue", "dateOfExpiry", WorkflowModule.Properties.VideoStatementV2API.COUNTRY_CODE, "type", "gender", "placeOfBirth", "placeOfIssue", "yearOfBirth", "age", "fatherName", "motherName", "husbandName", "spouseName", "nationality", "mrzString", "hometown", "address", "Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted$Address;", "(Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted$Address;)V", "getAddress", "()Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted$Address;", "getAge", "()Lco/hyperverge/hyperkyc/data/network/FieldValue;", "getCountryCode", "getDateOfBirth", "getDateOfExpiry", "getDateOfIssue", "getFatherName", "getFirstName", "getFullName", "getGender", "getHometown", "getHusbandName", "getIdNumber", "getLastName", "getMiddleName", "getMotherName", "getMrzString", "getNationality", "getPlaceOfBirth", "getPlaceOfIssue", "getSpouseName", "getType", "getYearOfBirth", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Address", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class FieldsExtracted implements Parcelable {
        public static final Parcelable.Creator<FieldsExtracted> CREATOR = new Creator();
        private final Address address;
        private final FieldValue age;
        private final FieldValue countryCode;
        private final FieldValue dateOfBirth;
        private final FieldValue dateOfExpiry;
        private final FieldValue dateOfIssue;
        private final FieldValue fatherName;
        private final FieldValue firstName;
        private final FieldValue fullName;
        private final FieldValue gender;
        private final FieldValue hometown;
        private final FieldValue husbandName;
        private final FieldValue idNumber;
        private final FieldValue lastName;
        private final FieldValue middleName;
        private final FieldValue motherName;
        private final FieldValue mrzString;
        private final FieldValue nationality;
        private final FieldValue placeOfBirth;
        private final FieldValue placeOfIssue;
        private final FieldValue spouseName;
        private final FieldValue type;
        private final FieldValue yearOfBirth;

        /* compiled from: responses.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<FieldsExtracted> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FieldsExtracted createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new FieldsExtracted(parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() != 0 ? Address.CREATOR.createFromParcel(parcel) : null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FieldsExtracted[] newArray(int i) {
                return new FieldsExtracted[i];
            }
        }

        public FieldsExtracted() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, Parameter.B_III_P, null);
        }

        /* renamed from: component1, reason: from getter */
        public final FieldValue getIdNumber() {
            return this.idNumber;
        }

        /* renamed from: component10, reason: from getter */
        public final FieldValue getType() {
            return this.type;
        }

        /* renamed from: component11, reason: from getter */
        public final FieldValue getGender() {
            return this.gender;
        }

        /* renamed from: component12, reason: from getter */
        public final FieldValue getPlaceOfBirth() {
            return this.placeOfBirth;
        }

        /* renamed from: component13, reason: from getter */
        public final FieldValue getPlaceOfIssue() {
            return this.placeOfIssue;
        }

        /* renamed from: component14, reason: from getter */
        public final FieldValue getYearOfBirth() {
            return this.yearOfBirth;
        }

        /* renamed from: component15, reason: from getter */
        public final FieldValue getAge() {
            return this.age;
        }

        /* renamed from: component16, reason: from getter */
        public final FieldValue getFatherName() {
            return this.fatherName;
        }

        /* renamed from: component17, reason: from getter */
        public final FieldValue getMotherName() {
            return this.motherName;
        }

        /* renamed from: component18, reason: from getter */
        public final FieldValue getHusbandName() {
            return this.husbandName;
        }

        /* renamed from: component19, reason: from getter */
        public final FieldValue getSpouseName() {
            return this.spouseName;
        }

        /* renamed from: component2, reason: from getter */
        public final FieldValue getFirstName() {
            return this.firstName;
        }

        /* renamed from: component20, reason: from getter */
        public final FieldValue getNationality() {
            return this.nationality;
        }

        /* renamed from: component21, reason: from getter */
        public final FieldValue getMrzString() {
            return this.mrzString;
        }

        /* renamed from: component22, reason: from getter */
        public final FieldValue getHometown() {
            return this.hometown;
        }

        /* renamed from: component23, reason: from getter */
        public final Address getAddress() {
            return this.address;
        }

        /* renamed from: component3, reason: from getter */
        public final FieldValue getMiddleName() {
            return this.middleName;
        }

        /* renamed from: component4, reason: from getter */
        public final FieldValue getLastName() {
            return this.lastName;
        }

        /* renamed from: component5, reason: from getter */
        public final FieldValue getFullName() {
            return this.fullName;
        }

        /* renamed from: component6, reason: from getter */
        public final FieldValue getDateOfBirth() {
            return this.dateOfBirth;
        }

        /* renamed from: component7, reason: from getter */
        public final FieldValue getDateOfIssue() {
            return this.dateOfIssue;
        }

        /* renamed from: component8, reason: from getter */
        public final FieldValue getDateOfExpiry() {
            return this.dateOfExpiry;
        }

        /* renamed from: component9, reason: from getter */
        public final FieldValue getCountryCode() {
            return this.countryCode;
        }

        public final FieldsExtracted copy(FieldValue idNumber, FieldValue firstName, FieldValue middleName, FieldValue lastName, FieldValue fullName, FieldValue dateOfBirth, FieldValue dateOfIssue, FieldValue dateOfExpiry, FieldValue countryCode, FieldValue type, FieldValue gender, FieldValue placeOfBirth, FieldValue placeOfIssue, FieldValue yearOfBirth, FieldValue age, FieldValue fatherName, FieldValue motherName, FieldValue husbandName, FieldValue spouseName, FieldValue nationality, FieldValue mrzString, FieldValue hometown, Address address) {
            return new FieldsExtracted(idNumber, firstName, middleName, lastName, fullName, dateOfBirth, dateOfIssue, dateOfExpiry, countryCode, type, gender, placeOfBirth, placeOfIssue, yearOfBirth, age, fatherName, motherName, husbandName, spouseName, nationality, mrzString, hometown, address);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FieldsExtracted)) {
                return false;
            }
            FieldsExtracted fieldsExtracted = (FieldsExtracted) other;
            return Intrinsics.areEqual(this.idNumber, fieldsExtracted.idNumber) && Intrinsics.areEqual(this.firstName, fieldsExtracted.firstName) && Intrinsics.areEqual(this.middleName, fieldsExtracted.middleName) && Intrinsics.areEqual(this.lastName, fieldsExtracted.lastName) && Intrinsics.areEqual(this.fullName, fieldsExtracted.fullName) && Intrinsics.areEqual(this.dateOfBirth, fieldsExtracted.dateOfBirth) && Intrinsics.areEqual(this.dateOfIssue, fieldsExtracted.dateOfIssue) && Intrinsics.areEqual(this.dateOfExpiry, fieldsExtracted.dateOfExpiry) && Intrinsics.areEqual(this.countryCode, fieldsExtracted.countryCode) && Intrinsics.areEqual(this.type, fieldsExtracted.type) && Intrinsics.areEqual(this.gender, fieldsExtracted.gender) && Intrinsics.areEqual(this.placeOfBirth, fieldsExtracted.placeOfBirth) && Intrinsics.areEqual(this.placeOfIssue, fieldsExtracted.placeOfIssue) && Intrinsics.areEqual(this.yearOfBirth, fieldsExtracted.yearOfBirth) && Intrinsics.areEqual(this.age, fieldsExtracted.age) && Intrinsics.areEqual(this.fatherName, fieldsExtracted.fatherName) && Intrinsics.areEqual(this.motherName, fieldsExtracted.motherName) && Intrinsics.areEqual(this.husbandName, fieldsExtracted.husbandName) && Intrinsics.areEqual(this.spouseName, fieldsExtracted.spouseName) && Intrinsics.areEqual(this.nationality, fieldsExtracted.nationality) && Intrinsics.areEqual(this.mrzString, fieldsExtracted.mrzString) && Intrinsics.areEqual(this.hometown, fieldsExtracted.hometown) && Intrinsics.areEqual(this.address, fieldsExtracted.address);
        }

        public int hashCode() {
            FieldValue fieldValue = this.idNumber;
            int hashCode = (fieldValue == null ? 0 : fieldValue.hashCode()) * 31;
            FieldValue fieldValue2 = this.firstName;
            int hashCode2 = (hashCode + (fieldValue2 == null ? 0 : fieldValue2.hashCode())) * 31;
            FieldValue fieldValue3 = this.middleName;
            int hashCode3 = (hashCode2 + (fieldValue3 == null ? 0 : fieldValue3.hashCode())) * 31;
            FieldValue fieldValue4 = this.lastName;
            int hashCode4 = (hashCode3 + (fieldValue4 == null ? 0 : fieldValue4.hashCode())) * 31;
            FieldValue fieldValue5 = this.fullName;
            int hashCode5 = (hashCode4 + (fieldValue5 == null ? 0 : fieldValue5.hashCode())) * 31;
            FieldValue fieldValue6 = this.dateOfBirth;
            int hashCode6 = (hashCode5 + (fieldValue6 == null ? 0 : fieldValue6.hashCode())) * 31;
            FieldValue fieldValue7 = this.dateOfIssue;
            int hashCode7 = (hashCode6 + (fieldValue7 == null ? 0 : fieldValue7.hashCode())) * 31;
            FieldValue fieldValue8 = this.dateOfExpiry;
            int hashCode8 = (hashCode7 + (fieldValue8 == null ? 0 : fieldValue8.hashCode())) * 31;
            FieldValue fieldValue9 = this.countryCode;
            int hashCode9 = (hashCode8 + (fieldValue9 == null ? 0 : fieldValue9.hashCode())) * 31;
            FieldValue fieldValue10 = this.type;
            int hashCode10 = (hashCode9 + (fieldValue10 == null ? 0 : fieldValue10.hashCode())) * 31;
            FieldValue fieldValue11 = this.gender;
            int hashCode11 = (hashCode10 + (fieldValue11 == null ? 0 : fieldValue11.hashCode())) * 31;
            FieldValue fieldValue12 = this.placeOfBirth;
            int hashCode12 = (hashCode11 + (fieldValue12 == null ? 0 : fieldValue12.hashCode())) * 31;
            FieldValue fieldValue13 = this.placeOfIssue;
            int hashCode13 = (hashCode12 + (fieldValue13 == null ? 0 : fieldValue13.hashCode())) * 31;
            FieldValue fieldValue14 = this.yearOfBirth;
            int hashCode14 = (hashCode13 + (fieldValue14 == null ? 0 : fieldValue14.hashCode())) * 31;
            FieldValue fieldValue15 = this.age;
            int hashCode15 = (hashCode14 + (fieldValue15 == null ? 0 : fieldValue15.hashCode())) * 31;
            FieldValue fieldValue16 = this.fatherName;
            int hashCode16 = (hashCode15 + (fieldValue16 == null ? 0 : fieldValue16.hashCode())) * 31;
            FieldValue fieldValue17 = this.motherName;
            int hashCode17 = (hashCode16 + (fieldValue17 == null ? 0 : fieldValue17.hashCode())) * 31;
            FieldValue fieldValue18 = this.husbandName;
            int hashCode18 = (hashCode17 + (fieldValue18 == null ? 0 : fieldValue18.hashCode())) * 31;
            FieldValue fieldValue19 = this.spouseName;
            int hashCode19 = (hashCode18 + (fieldValue19 == null ? 0 : fieldValue19.hashCode())) * 31;
            FieldValue fieldValue20 = this.nationality;
            int hashCode20 = (hashCode19 + (fieldValue20 == null ? 0 : fieldValue20.hashCode())) * 31;
            FieldValue fieldValue21 = this.mrzString;
            int hashCode21 = (hashCode20 + (fieldValue21 == null ? 0 : fieldValue21.hashCode())) * 31;
            FieldValue fieldValue22 = this.hometown;
            int hashCode22 = (hashCode21 + (fieldValue22 == null ? 0 : fieldValue22.hashCode())) * 31;
            Address address = this.address;
            return hashCode22 + (address != null ? address.hashCode() : 0);
        }

        public String toString() {
            return "FieldsExtracted(idNumber=" + this.idNumber + ", firstName=" + this.firstName + ", middleName=" + this.middleName + ", lastName=" + this.lastName + ", fullName=" + this.fullName + ", dateOfBirth=" + this.dateOfBirth + ", dateOfIssue=" + this.dateOfIssue + ", dateOfExpiry=" + this.dateOfExpiry + ", countryCode=" + this.countryCode + ", type=" + this.type + ", gender=" + this.gender + ", placeOfBirth=" + this.placeOfBirth + ", placeOfIssue=" + this.placeOfIssue + ", yearOfBirth=" + this.yearOfBirth + ", age=" + this.age + ", fatherName=" + this.fatherName + ", motherName=" + this.motherName + ", husbandName=" + this.husbandName + ", spouseName=" + this.spouseName + ", nationality=" + this.nationality + ", mrzString=" + this.mrzString + ", hometown=" + this.hometown + ", address=" + this.address + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            FieldValue fieldValue = this.idNumber;
            if (fieldValue == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue2 = this.firstName;
            if (fieldValue2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue2.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue3 = this.middleName;
            if (fieldValue3 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue3.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue4 = this.lastName;
            if (fieldValue4 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue4.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue5 = this.fullName;
            if (fieldValue5 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue5.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue6 = this.dateOfBirth;
            if (fieldValue6 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue6.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue7 = this.dateOfIssue;
            if (fieldValue7 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue7.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue8 = this.dateOfExpiry;
            if (fieldValue8 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue8.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue9 = this.countryCode;
            if (fieldValue9 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue9.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue10 = this.type;
            if (fieldValue10 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue10.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue11 = this.gender;
            if (fieldValue11 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue11.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue12 = this.placeOfBirth;
            if (fieldValue12 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue12.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue13 = this.placeOfIssue;
            if (fieldValue13 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue13.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue14 = this.yearOfBirth;
            if (fieldValue14 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue14.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue15 = this.age;
            if (fieldValue15 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue15.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue16 = this.fatherName;
            if (fieldValue16 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue16.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue17 = this.motherName;
            if (fieldValue17 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue17.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue18 = this.husbandName;
            if (fieldValue18 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue18.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue19 = this.spouseName;
            if (fieldValue19 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue19.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue20 = this.nationality;
            if (fieldValue20 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue20.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue21 = this.mrzString;
            if (fieldValue21 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue21.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue22 = this.hometown;
            if (fieldValue22 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue22.writeToParcel(parcel, flags);
            }
            Address address = this.address;
            if (address == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                address.writeToParcel(parcel, flags);
            }
        }

        public FieldsExtracted(FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4, FieldValue fieldValue5, FieldValue fieldValue6, FieldValue fieldValue7, FieldValue fieldValue8, FieldValue fieldValue9, FieldValue fieldValue10, FieldValue fieldValue11, FieldValue fieldValue12, FieldValue fieldValue13, FieldValue fieldValue14, FieldValue fieldValue15, FieldValue fieldValue16, FieldValue fieldValue17, FieldValue fieldValue18, FieldValue fieldValue19, FieldValue fieldValue20, FieldValue fieldValue21, FieldValue fieldValue22, Address address) {
            this.idNumber = fieldValue;
            this.firstName = fieldValue2;
            this.middleName = fieldValue3;
            this.lastName = fieldValue4;
            this.fullName = fieldValue5;
            this.dateOfBirth = fieldValue6;
            this.dateOfIssue = fieldValue7;
            this.dateOfExpiry = fieldValue8;
            this.countryCode = fieldValue9;
            this.type = fieldValue10;
            this.gender = fieldValue11;
            this.placeOfBirth = fieldValue12;
            this.placeOfIssue = fieldValue13;
            this.yearOfBirth = fieldValue14;
            this.age = fieldValue15;
            this.fatherName = fieldValue16;
            this.motherName = fieldValue17;
            this.husbandName = fieldValue18;
            this.spouseName = fieldValue19;
            this.nationality = fieldValue20;
            this.mrzString = fieldValue21;
            this.hometown = fieldValue22;
            this.address = address;
        }

        public /* synthetic */ FieldsExtracted(FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4, FieldValue fieldValue5, FieldValue fieldValue6, FieldValue fieldValue7, FieldValue fieldValue8, FieldValue fieldValue9, FieldValue fieldValue10, FieldValue fieldValue11, FieldValue fieldValue12, FieldValue fieldValue13, FieldValue fieldValue14, FieldValue fieldValue15, FieldValue fieldValue16, FieldValue fieldValue17, FieldValue fieldValue18, FieldValue fieldValue19, FieldValue fieldValue20, FieldValue fieldValue21, FieldValue fieldValue22, Address address, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : fieldValue, (i & 2) != 0 ? null : fieldValue2, (i & 4) != 0 ? null : fieldValue3, (i & 8) != 0 ? null : fieldValue4, (i & 16) != 0 ? null : fieldValue5, (i & 32) != 0 ? null : fieldValue6, (i & 64) != 0 ? null : fieldValue7, (i & 128) != 0 ? null : fieldValue8, (i & 256) != 0 ? null : fieldValue9, (i & 512) != 0 ? null : fieldValue10, (i & 1024) != 0 ? null : fieldValue11, (i & 2048) != 0 ? null : fieldValue12, (i & 4096) != 0 ? null : fieldValue13, (i & 8192) != 0 ? null : fieldValue14, (i & 16384) != 0 ? null : fieldValue15, (i & 32768) != 0 ? null : fieldValue16, (i & 65536) != 0 ? null : fieldValue17, (i & 131072) != 0 ? null : fieldValue18, (i & 262144) != 0 ? null : fieldValue19, (i & 524288) != 0 ? null : fieldValue20, (i & 1048576) != 0 ? null : fieldValue21, (i & 2097152) != 0 ? null : fieldValue22, (i & 4194304) != 0 ? null : address);
        }

        public final FieldValue getIdNumber() {
            return this.idNumber;
        }

        public final FieldValue getFirstName() {
            return this.firstName;
        }

        public final FieldValue getMiddleName() {
            return this.middleName;
        }

        public final FieldValue getLastName() {
            return this.lastName;
        }

        public final FieldValue getFullName() {
            return this.fullName;
        }

        public final FieldValue getDateOfBirth() {
            return this.dateOfBirth;
        }

        public final FieldValue getDateOfIssue() {
            return this.dateOfIssue;
        }

        public final FieldValue getDateOfExpiry() {
            return this.dateOfExpiry;
        }

        public final FieldValue getCountryCode() {
            return this.countryCode;
        }

        public final FieldValue getType() {
            return this.type;
        }

        public final FieldValue getGender() {
            return this.gender;
        }

        public final FieldValue getPlaceOfBirth() {
            return this.placeOfBirth;
        }

        public final FieldValue getPlaceOfIssue() {
            return this.placeOfIssue;
        }

        public final FieldValue getYearOfBirth() {
            return this.yearOfBirth;
        }

        public final FieldValue getAge() {
            return this.age;
        }

        public final FieldValue getFatherName() {
            return this.fatherName;
        }

        public final FieldValue getMotherName() {
            return this.motherName;
        }

        public final FieldValue getHusbandName() {
            return this.husbandName;
        }

        public final FieldValue getSpouseName() {
            return this.spouseName;
        }

        public final FieldValue getNationality() {
            return this.nationality;
        }

        public final FieldValue getMrzString() {
            return this.mrzString;
        }

        public final FieldValue getHometown() {
            return this.hometown;
        }

        public final Address getAddress() {
            return this.address;
        }

        /* compiled from: responses.kt */
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bq\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ju\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020\"HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\u0019\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\"HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006."}, d2 = {"Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$FieldsExtracted$Address;", "Landroid/os/Parcelable;", "value", "", "confidence", "score", "houseNumber", "additionalInfo", "province", "district", "street", "zipCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAdditionalInfo", "()Ljava/lang/String;", "getConfidence", "getDistrict", "getHouseNumber", "getProvince", "getScore", "getStreet", "getValue", "getZipCode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Address implements Parcelable {
            public static final Parcelable.Creator<Address> CREATOR = new Creator();
            private final String additionalInfo;
            private final String confidence;
            private final String district;
            private final String houseNumber;
            private final String province;
            private final String score;
            private final String street;
            private final String value;
            private final String zipCode;

            /* compiled from: responses.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* loaded from: classes2.dex */
            public static final class Creator implements Parcelable.Creator<Address> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Address createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Address(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Address[] newArray(int i) {
                    return new Address[i];
                }
            }

            public Address() {
                this(null, null, null, null, null, null, null, null, null, 511, null);
            }

            /* renamed from: component1, reason: from getter */
            public final String getValue() {
                return this.value;
            }

            /* renamed from: component2, reason: from getter */
            public final String getConfidence() {
                return this.confidence;
            }

            /* renamed from: component3, reason: from getter */
            public final String getScore() {
                return this.score;
            }

            /* renamed from: component4, reason: from getter */
            public final String getHouseNumber() {
                return this.houseNumber;
            }

            /* renamed from: component5, reason: from getter */
            public final String getAdditionalInfo() {
                return this.additionalInfo;
            }

            /* renamed from: component6, reason: from getter */
            public final String getProvince() {
                return this.province;
            }

            /* renamed from: component7, reason: from getter */
            public final String getDistrict() {
                return this.district;
            }

            /* renamed from: component8, reason: from getter */
            public final String getStreet() {
                return this.street;
            }

            /* renamed from: component9, reason: from getter */
            public final String getZipCode() {
                return this.zipCode;
            }

            public final Address copy(String value, String confidence, String score, String houseNumber, String additionalInfo, String province, String district, String street, String zipCode) {
                return new Address(value, confidence, score, houseNumber, additionalInfo, province, district, street, zipCode);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Address)) {
                    return false;
                }
                Address address = (Address) other;
                return Intrinsics.areEqual(this.value, address.value) && Intrinsics.areEqual(this.confidence, address.confidence) && Intrinsics.areEqual(this.score, address.score) && Intrinsics.areEqual(this.houseNumber, address.houseNumber) && Intrinsics.areEqual(this.additionalInfo, address.additionalInfo) && Intrinsics.areEqual(this.province, address.province) && Intrinsics.areEqual(this.district, address.district) && Intrinsics.areEqual(this.street, address.street) && Intrinsics.areEqual(this.zipCode, address.zipCode);
            }

            public int hashCode() {
                String str = this.value;
                int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                String str2 = this.confidence;
                int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                String str3 = this.score;
                int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
                String str4 = this.houseNumber;
                int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
                String str5 = this.additionalInfo;
                int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
                String str6 = this.province;
                int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
                String str7 = this.district;
                int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
                String str8 = this.street;
                int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
                String str9 = this.zipCode;
                return hashCode8 + (str9 != null ? str9.hashCode() : 0);
            }

            public String toString() {
                return "Address(value=" + this.value + ", confidence=" + this.confidence + ", score=" + this.score + ", houseNumber=" + this.houseNumber + ", additionalInfo=" + this.additionalInfo + ", province=" + this.province + ", district=" + this.district + ", street=" + this.street + ", zipCode=" + this.zipCode + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.value);
                parcel.writeString(this.confidence);
                parcel.writeString(this.score);
                parcel.writeString(this.houseNumber);
                parcel.writeString(this.additionalInfo);
                parcel.writeString(this.province);
                parcel.writeString(this.district);
                parcel.writeString(this.street);
                parcel.writeString(this.zipCode);
            }

            public Address(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
                this.value = str;
                this.confidence = str2;
                this.score = str3;
                this.houseNumber = str4;
                this.additionalInfo = str5;
                this.province = str6;
                this.district = str7;
                this.street = str8;
                this.zipCode = str9;
            }

            public /* synthetic */ Address(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) == 0 ? str9 : null);
            }

            public final String getValue() {
                return this.value;
            }

            public final String getConfidence() {
                return this.confidence;
            }

            public final String getScore() {
                return this.score;
            }

            public final String getHouseNumber() {
                return this.houseNumber;
            }

            public final String getAdditionalInfo() {
                return this.additionalInfo;
            }

            public final String getProvince() {
                return this.province;
            }

            public final String getDistrict() {
                return this.district;
            }

            public final String getStreet() {
                return this.street;
            }

            public final String getZipCode() {
                return this.zipCode;
            }
        }
    }

    /* compiled from: responses.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003JQ\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0019\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006&"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$QualityChecks;", "Landroid/os/Parcelable;", "blur", "Lco/hyperverge/hyperkyc/data/network/FieldValue;", "glare", "blackAndWhite", "capturedFromScreen", "whiteBackground", "cutId", "(Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;Lco/hyperverge/hyperkyc/data/network/FieldValue;)V", "getBlackAndWhite", "()Lco/hyperverge/hyperkyc/data/network/FieldValue;", "getBlur", "getCapturedFromScreen", "getCutId", "getGlare", "getWhiteBackground", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class QualityChecks implements Parcelable {
        public static final Parcelable.Creator<QualityChecks> CREATOR = new Creator();
        private final FieldValue blackAndWhite;
        private final FieldValue blur;
        private final FieldValue capturedFromScreen;
        private final FieldValue cutId;
        private final FieldValue glare;
        private final FieldValue whiteBackground;

        /* compiled from: responses.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<QualityChecks> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final QualityChecks createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new QualityChecks(parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel), parcel.readInt() != 0 ? FieldValue.CREATOR.createFromParcel(parcel) : null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final QualityChecks[] newArray(int i) {
                return new QualityChecks[i];
            }
        }

        public QualityChecks() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ QualityChecks copy$default(QualityChecks qualityChecks, FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4, FieldValue fieldValue5, FieldValue fieldValue6, int i, Object obj) {
            if ((i & 1) != 0) {
                fieldValue = qualityChecks.blur;
            }
            if ((i & 2) != 0) {
                fieldValue2 = qualityChecks.glare;
            }
            FieldValue fieldValue7 = fieldValue2;
            if ((i & 4) != 0) {
                fieldValue3 = qualityChecks.blackAndWhite;
            }
            FieldValue fieldValue8 = fieldValue3;
            if ((i & 8) != 0) {
                fieldValue4 = qualityChecks.capturedFromScreen;
            }
            FieldValue fieldValue9 = fieldValue4;
            if ((i & 16) != 0) {
                fieldValue5 = qualityChecks.whiteBackground;
            }
            FieldValue fieldValue10 = fieldValue5;
            if ((i & 32) != 0) {
                fieldValue6 = qualityChecks.cutId;
            }
            return qualityChecks.copy(fieldValue, fieldValue7, fieldValue8, fieldValue9, fieldValue10, fieldValue6);
        }

        /* renamed from: component1, reason: from getter */
        public final FieldValue getBlur() {
            return this.blur;
        }

        /* renamed from: component2, reason: from getter */
        public final FieldValue getGlare() {
            return this.glare;
        }

        /* renamed from: component3, reason: from getter */
        public final FieldValue getBlackAndWhite() {
            return this.blackAndWhite;
        }

        /* renamed from: component4, reason: from getter */
        public final FieldValue getCapturedFromScreen() {
            return this.capturedFromScreen;
        }

        /* renamed from: component5, reason: from getter */
        public final FieldValue getWhiteBackground() {
            return this.whiteBackground;
        }

        /* renamed from: component6, reason: from getter */
        public final FieldValue getCutId() {
            return this.cutId;
        }

        public final QualityChecks copy(FieldValue blur, FieldValue glare, FieldValue blackAndWhite, FieldValue capturedFromScreen, FieldValue whiteBackground, FieldValue cutId) {
            return new QualityChecks(blur, glare, blackAndWhite, capturedFromScreen, whiteBackground, cutId);
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
            return Intrinsics.areEqual(this.blur, qualityChecks.blur) && Intrinsics.areEqual(this.glare, qualityChecks.glare) && Intrinsics.areEqual(this.blackAndWhite, qualityChecks.blackAndWhite) && Intrinsics.areEqual(this.capturedFromScreen, qualityChecks.capturedFromScreen) && Intrinsics.areEqual(this.whiteBackground, qualityChecks.whiteBackground) && Intrinsics.areEqual(this.cutId, qualityChecks.cutId);
        }

        public int hashCode() {
            FieldValue fieldValue = this.blur;
            int hashCode = (fieldValue == null ? 0 : fieldValue.hashCode()) * 31;
            FieldValue fieldValue2 = this.glare;
            int hashCode2 = (hashCode + (fieldValue2 == null ? 0 : fieldValue2.hashCode())) * 31;
            FieldValue fieldValue3 = this.blackAndWhite;
            int hashCode3 = (hashCode2 + (fieldValue3 == null ? 0 : fieldValue3.hashCode())) * 31;
            FieldValue fieldValue4 = this.capturedFromScreen;
            int hashCode4 = (hashCode3 + (fieldValue4 == null ? 0 : fieldValue4.hashCode())) * 31;
            FieldValue fieldValue5 = this.whiteBackground;
            int hashCode5 = (hashCode4 + (fieldValue5 == null ? 0 : fieldValue5.hashCode())) * 31;
            FieldValue fieldValue6 = this.cutId;
            return hashCode5 + (fieldValue6 != null ? fieldValue6.hashCode() : 0);
        }

        public String toString() {
            return "QualityChecks(blur=" + this.blur + ", glare=" + this.glare + ", blackAndWhite=" + this.blackAndWhite + ", capturedFromScreen=" + this.capturedFromScreen + ", whiteBackground=" + this.whiteBackground + ", cutId=" + this.cutId + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            FieldValue fieldValue = this.blur;
            if (fieldValue == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue2 = this.glare;
            if (fieldValue2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue2.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue3 = this.blackAndWhite;
            if (fieldValue3 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue3.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue4 = this.capturedFromScreen;
            if (fieldValue4 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue4.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue5 = this.whiteBackground;
            if (fieldValue5 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue5.writeToParcel(parcel, flags);
            }
            FieldValue fieldValue6 = this.cutId;
            if (fieldValue6 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue6.writeToParcel(parcel, flags);
            }
        }

        public QualityChecks(FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4, FieldValue fieldValue5, FieldValue fieldValue6) {
            this.blur = fieldValue;
            this.glare = fieldValue2;
            this.blackAndWhite = fieldValue3;
            this.capturedFromScreen = fieldValue4;
            this.whiteBackground = fieldValue5;
            this.cutId = fieldValue6;
        }

        public /* synthetic */ QualityChecks(FieldValue fieldValue, FieldValue fieldValue2, FieldValue fieldValue3, FieldValue fieldValue4, FieldValue fieldValue5, FieldValue fieldValue6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : fieldValue, (i & 2) != 0 ? null : fieldValue2, (i & 4) != 0 ? null : fieldValue3, (i & 8) != 0 ? null : fieldValue4, (i & 16) != 0 ? null : fieldValue5, (i & 32) != 0 ? null : fieldValue6);
        }

        public final FieldValue getBlur() {
            return this.blur;
        }

        public final FieldValue getGlare() {
            return this.glare;
        }

        public final FieldValue getBlackAndWhite() {
            return this.blackAndWhite;
        }

        public final FieldValue getCapturedFromScreen() {
            return this.capturedFromScreen;
        }

        public final FieldValue getWhiteBackground() {
            return this.whiteBackground;
        }

        public final FieldValue getCutId() {
            return this.cutId;
        }
    }

    /* compiled from: responses.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail$RuleChecks;", "Landroid/os/Parcelable;", "idNumberInvalid", "Lco/hyperverge/hyperkyc/data/network/FieldValue;", "(Lco/hyperverge/hyperkyc/data/network/FieldValue;)V", "getIdNumberInvalid", "()Lco/hyperverge/hyperkyc/data/network/FieldValue;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class RuleChecks implements Parcelable {
        public static final Parcelable.Creator<RuleChecks> CREATOR = new Creator();
        private final FieldValue idNumberInvalid;

        /* compiled from: responses.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<RuleChecks> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final RuleChecks createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new RuleChecks(parcel.readInt() == 0 ? null : FieldValue.CREATOR.createFromParcel(parcel));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final RuleChecks[] newArray(int i) {
                return new RuleChecks[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public RuleChecks() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ RuleChecks copy$default(RuleChecks ruleChecks, FieldValue fieldValue, int i, Object obj) {
            if ((i & 1) != 0) {
                fieldValue = ruleChecks.idNumberInvalid;
            }
            return ruleChecks.copy(fieldValue);
        }

        /* renamed from: component1, reason: from getter */
        public final FieldValue getIdNumberInvalid() {
            return this.idNumberInvalid;
        }

        public final RuleChecks copy(FieldValue idNumberInvalid) {
            return new RuleChecks(idNumberInvalid);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RuleChecks) && Intrinsics.areEqual(this.idNumberInvalid, ((RuleChecks) other).idNumberInvalid);
        }

        public int hashCode() {
            FieldValue fieldValue = this.idNumberInvalid;
            if (fieldValue == null) {
                return 0;
            }
            return fieldValue.hashCode();
        }

        public String toString() {
            return "RuleChecks(idNumberInvalid=" + this.idNumberInvalid + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            FieldValue fieldValue = this.idNumberInvalid;
            if (fieldValue == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                fieldValue.writeToParcel(parcel, flags);
            }
        }

        public RuleChecks(FieldValue fieldValue) {
            this.idNumberInvalid = fieldValue;
        }

        public /* synthetic */ RuleChecks(FieldValue fieldValue, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : fieldValue);
        }

        public final FieldValue getIdNumberInvalid() {
            return this.idNumberInvalid;
        }
    }
}
