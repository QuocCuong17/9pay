package vn.ai.faceauth.sdk.presentation.domain.configs;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.domain.model.Constants;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001bHÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0011\u0010\r\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\tR\u0011\u0010\u0011\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\tR\u0011\u0010\u0013\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\t¨\u0006("}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;", "Landroid/os/Parcelable;", "_eyeOpenProbability", "", "_smileProbability", "_blurProbability", "_luminosityProbability", "(FFFF)V", "get_blurProbability", "()F", "get_eyeOpenProbability", "get_luminosityProbability", "get_smileProbability", "blurProbability", "getBlurProbability", "eyeOpenProbability", "getEyeOpenProbability", "luminosityProbability", "getLuminosityProbability", "smileProbability", "getSmileProbability", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LivenessConfig implements Parcelable {
    public static final Parcelable.Creator<LivenessConfig> CREATOR = new Creator();
    private final float _blurProbability;
    private final float _eyeOpenProbability;
    private final float _luminosityProbability;
    private final float _smileProbability;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Creator implements Parcelable.Creator<LivenessConfig> {
        @Override // android.os.Parcelable.Creator
        public final LivenessConfig createFromParcel(Parcel parcel) {
            return new LivenessConfig(parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
        }

        @Override // android.os.Parcelable.Creator
        public final LivenessConfig[] newArray(int i) {
            return new LivenessConfig[i];
        }
    }

    public LivenessConfig() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 15, null);
    }

    public LivenessConfig(float f, float f2, float f3, float f4) {
        this._eyeOpenProbability = f;
        this._smileProbability = f2;
        this._blurProbability = f3;
        this._luminosityProbability = f4;
    }

    public /* synthetic */ LivenessConfig(float f, float f2, float f3, float f4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Constants.INSTANCE.getEYE_OPENED_PROBABILITY() : f, (i & 2) != 0 ? Constants.INSTANCE.getSMILE_PROBABILITY() : f2, (i & 4) != 0 ? Constants.INSTANCE.getBLUR_PROBABILITY() : f3, (i & 8) != 0 ? Constants.INSTANCE.getLUMINOSITY_PROBABILITY() : f4);
    }

    public static /* synthetic */ LivenessConfig copy$default(LivenessConfig livenessConfig, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = livenessConfig._eyeOpenProbability;
        }
        if ((i & 2) != 0) {
            f2 = livenessConfig._smileProbability;
        }
        if ((i & 4) != 0) {
            f3 = livenessConfig._blurProbability;
        }
        if ((i & 8) != 0) {
            f4 = livenessConfig._luminosityProbability;
        }
        return livenessConfig.copy(f, f2, f3, f4);
    }

    /* renamed from: component1, reason: from getter */
    public final float get_eyeOpenProbability() {
        return this._eyeOpenProbability;
    }

    /* renamed from: component2, reason: from getter */
    public final float get_smileProbability() {
        return this._smileProbability;
    }

    /* renamed from: component3, reason: from getter */
    public final float get_blurProbability() {
        return this._blurProbability;
    }

    /* renamed from: component4, reason: from getter */
    public final float get_luminosityProbability() {
        return this._luminosityProbability;
    }

    public final LivenessConfig copy(float _eyeOpenProbability, float _smileProbability, float _blurProbability, float _luminosityProbability) {
        return new LivenessConfig(_eyeOpenProbability, _smileProbability, _blurProbability, _luminosityProbability);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessConfig)) {
            return false;
        }
        LivenessConfig livenessConfig = (LivenessConfig) other;
        return Intrinsics.areEqual((Object) Float.valueOf(this._eyeOpenProbability), (Object) Float.valueOf(livenessConfig._eyeOpenProbability)) && Intrinsics.areEqual((Object) Float.valueOf(this._smileProbability), (Object) Float.valueOf(livenessConfig._smileProbability)) && Intrinsics.areEqual((Object) Float.valueOf(this._blurProbability), (Object) Float.valueOf(livenessConfig._blurProbability)) && Intrinsics.areEqual((Object) Float.valueOf(this._luminosityProbability), (Object) Float.valueOf(livenessConfig._luminosityProbability));
    }

    public final float getBlurProbability() {
        return RangesKt.coerceIn(this._blurProbability, 0.0f, 1.0f);
    }

    public final float getEyeOpenProbability() {
        return RangesKt.coerceIn(this._eyeOpenProbability, 0.0f, 1.0f);
    }

    public final float getLuminosityProbability() {
        return RangesKt.coerceIn(this._luminosityProbability, 0.0f, 1.0f);
    }

    public final float getSmileProbability() {
        return RangesKt.coerceIn(this._smileProbability, 0.0f, 1.0f);
    }

    public final float get_blurProbability() {
        return this._blurProbability;
    }

    public final float get_eyeOpenProbability() {
        return this._eyeOpenProbability;
    }

    public final float get_luminosityProbability() {
        return this._luminosityProbability;
    }

    public final float get_smileProbability() {
        return this._smileProbability;
    }

    public int hashCode() {
        int floatToIntBits = Float.floatToIntBits(this._eyeOpenProbability);
        int floatToIntBits2 = Float.floatToIntBits(this._smileProbability);
        return Float.floatToIntBits(this._luminosityProbability) + ((Float.floatToIntBits(this._blurProbability) + ((floatToIntBits2 + (floatToIntBits * 31)) * 31)) * 31);
    }

    public String toString() {
        return tfwhgw.rnigpa(353) + this._eyeOpenProbability + tfwhgw.rnigpa(354) + this._smileProbability + tfwhgw.rnigpa(355) + this._blurProbability + tfwhgw.rnigpa(356) + this._luminosityProbability + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeFloat(this._eyeOpenProbability);
        parcel.writeFloat(this._smileProbability);
        parcel.writeFloat(this._blurProbability);
        parcel.writeFloat(this._luminosityProbability);
    }
}
