package vn.ai.faceauth.sdk.presentation.domain.configs;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/configs/AuthenInput;", "Landroid/os/Parcelable;", "transId", "", "personId", "(Ljava/lang/String;Ljava/lang/String;)V", "getPersonId", "()Ljava/lang/String;", "getTransId", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class AuthenInput implements Parcelable {
    public static final Parcelable.Creator<AuthenInput> CREATOR = new Creator();
    private final String personId;
    private final String transId;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Creator implements Parcelable.Creator<AuthenInput> {
        @Override // android.os.Parcelable.Creator
        public final AuthenInput createFromParcel(Parcel parcel) {
            return new AuthenInput(parcel.readString(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final AuthenInput[] newArray(int i) {
            return new AuthenInput[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AuthenInput() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public AuthenInput(String str, String str2) {
        this.transId = str;
        this.personId = str2;
    }

    public /* synthetic */ AuthenInput(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2);
    }

    public static /* synthetic */ AuthenInput copy$default(AuthenInput authenInput, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authenInput.transId;
        }
        if ((i & 2) != 0) {
            str2 = authenInput.personId;
        }
        return authenInput.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTransId() {
        return this.transId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPersonId() {
        return this.personId;
    }

    public final AuthenInput copy(String transId, String personId) {
        return new AuthenInput(transId, personId);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenInput)) {
            return false;
        }
        AuthenInput authenInput = (AuthenInput) other;
        return Intrinsics.areEqual(this.transId, authenInput.transId) && Intrinsics.areEqual(this.personId, authenInput.personId);
    }

    public final String getPersonId() {
        return this.personId;
    }

    public final String getTransId() {
        return this.transId;
    }

    public int hashCode() {
        return this.personId.hashCode() + (this.transId.hashCode() * 31);
    }

    public String toString() {
        return tfwhgw.rnigpa(80) + this.transId + tfwhgw.rnigpa(81) + this.personId + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.transId);
        parcel.writeString(this.personId);
    }
}
