package vn.ai.faceauth.sdk.presentation.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\tHÖ\u0001J\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "", "Landroid/os/Parcelable;", "textValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTextValue", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "STEP_FIRST_CHECK", "STEP_ZOOM_IN", "STEP_ZOOM_OUT", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public enum StepLiveness implements Parcelable {
    STEP_FIRST_CHECK(tfwhgw.rnigpa(308)),
    STEP_ZOOM_IN(tfwhgw.rnigpa(310)),
    STEP_ZOOM_OUT(tfwhgw.rnigpa(312));

    public static final Parcelable.Creator<StepLiveness> CREATOR = new Parcelable.Creator<StepLiveness>() { // from class: vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness.Creator
        @Override // android.os.Parcelable.Creator
        public final StepLiveness createFromParcel(Parcel parcel) {
            return StepLiveness.valueOf(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final StepLiveness[] newArray(int i) {
            return new StepLiveness[i];
        }
    };
    private final String textValue;

    StepLiveness(String str) {
        this.textValue = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final String getTextValue() {
        return this.textValue;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name());
    }
}
