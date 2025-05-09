package co.hyperverge.hyperkyc.data.network;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: responses.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0017HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006#"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/FieldValue;", "Landroid/os/Parcelable;", "value", "", "confidence", "score", "", "reviewNeeded", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)V", "getConfidence", "()Ljava/lang/String;", "getReviewNeeded", "getScore", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getValue", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)Lco/hyperverge/hyperkyc/data/network/FieldValue;", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FieldValue implements Parcelable {
    public static final Parcelable.Creator<FieldValue> CREATOR = new Creator();
    private final String confidence;
    private final String reviewNeeded;
    private final Double score;
    private final String value;

    /* compiled from: responses.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<FieldValue> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FieldValue createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FieldValue(parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FieldValue[] newArray(int i) {
            return new FieldValue[i];
        }
    }

    public FieldValue() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ FieldValue copy$default(FieldValue fieldValue, String str, String str2, Double d, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fieldValue.value;
        }
        if ((i & 2) != 0) {
            str2 = fieldValue.confidence;
        }
        if ((i & 4) != 0) {
            d = fieldValue.score;
        }
        if ((i & 8) != 0) {
            str3 = fieldValue.reviewNeeded;
        }
        return fieldValue.copy(str, str2, d, str3);
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
    public final Double getScore() {
        return this.score;
    }

    /* renamed from: component4, reason: from getter */
    public final String getReviewNeeded() {
        return this.reviewNeeded;
    }

    public final FieldValue copy(String value, String confidence, Double score, String reviewNeeded) {
        return new FieldValue(value, confidence, score, reviewNeeded);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FieldValue)) {
            return false;
        }
        FieldValue fieldValue = (FieldValue) other;
        return Intrinsics.areEqual(this.value, fieldValue.value) && Intrinsics.areEqual(this.confidence, fieldValue.confidence) && Intrinsics.areEqual((Object) this.score, (Object) fieldValue.score) && Intrinsics.areEqual(this.reviewNeeded, fieldValue.reviewNeeded);
    }

    public int hashCode() {
        String str = this.value;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.confidence;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Double d = this.score;
        int hashCode3 = (hashCode2 + (d == null ? 0 : d.hashCode())) * 31;
        String str3 = this.reviewNeeded;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "FieldValue(value=" + this.value + ", confidence=" + this.confidence + ", score=" + this.score + ", reviewNeeded=" + this.reviewNeeded + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.value);
        parcel.writeString(this.confidence);
        Double d = this.score;
        if (d == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeDouble(d.doubleValue());
        }
        parcel.writeString(this.reviewNeeded);
    }

    public FieldValue(String str, String str2, Double d, String str3) {
        this.value = str;
        this.confidence = str2;
        this.score = d;
        this.reviewNeeded = str3;
    }

    public /* synthetic */ FieldValue(String str, String str2, Double d, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : d, (i & 8) != 0 ? null : str3);
    }

    public final String getValue() {
        return this.value;
    }

    public final String getConfidence() {
        return this.confidence;
    }

    public final Double getScore() {
        return this.score;
    }

    public final String getReviewNeeded() {
        return this.reviewNeeded;
    }
}
