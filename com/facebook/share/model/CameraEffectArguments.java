package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraEffectArguments.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0015\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u001d\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0013J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0015J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u000bH\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/share/model/CameraEffectArguments;", "Lcom/facebook/share/model/ShareModel;", "builder", "Lcom/facebook/share/model/CameraEffectArguments$Builder;", "(Lcom/facebook/share/model/CameraEffectArguments$Builder;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "params", "Landroid/os/Bundle;", "describeContents", "", WorkflowRequestType.Method.GET, "", "key", "", "getString", "getStringArray", "", "(Ljava/lang/String;)[Ljava/lang/String;", "keySet", "", "writeToParcel", "", "out", "flags", "Builder", "Companion", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CameraEffectArguments implements ShareModel {
    private final Bundle params;
    public static final Parcelable.Creator<CameraEffectArguments> CREATOR = new Parcelable.Creator<CameraEffectArguments>() { // from class: com.facebook.share.model.CameraEffectArguments$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraEffectArguments createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CameraEffectArguments(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraEffectArguments[] newArray(int size) {
            return new CameraEffectArguments[size];
        }
    };

    public /* synthetic */ CameraEffectArguments(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CameraEffectArguments(Builder builder) {
        this.params = builder.getParams();
    }

    public CameraEffectArguments(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.params = parcel.readBundle(getClass().getClassLoader());
    }

    public final String getString(String key) {
        Bundle bundle = this.params;
        if (bundle != null) {
            return bundle.getString(key);
        }
        return null;
    }

    public final String[] getStringArray(String key) {
        Bundle bundle = this.params;
        if (bundle != null) {
            return bundle.getStringArray(key);
        }
        return null;
    }

    public final Object get(String key) {
        Bundle bundle = this.params;
        if (bundle != null) {
            return bundle.get(key);
        }
        return null;
    }

    public final Set<String> keySet() {
        Bundle bundle = this.params;
        Set<String> keySet = bundle != null ? bundle.keySet() : null;
        return keySet == null ? SetsKt.emptySet() : keySet;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeBundle(this.params);
    }

    /* compiled from: CameraEffectArguments.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\u0002H\u0016J!\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u0010\u0010\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/facebook/share/model/CameraEffectArguments$Builder;", "Lcom/facebook/share/model/ShareModelBuilder;", "Lcom/facebook/share/model/CameraEffectArguments;", "()V", "params", "Landroid/os/Bundle;", "getParams$facebook_common_release", "()Landroid/os/Bundle;", "build", "putArgument", "key", "", "arrayValue", "", "(Ljava/lang/String;[Ljava/lang/String;)Lcom/facebook/share/model/CameraEffectArguments$Builder;", "value", "readFrom", "parcel", "Landroid/os/Parcel;", "model", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Builder implements ShareModelBuilder<CameraEffectArguments, Builder> {
        private final Bundle params = new Bundle();

        /* renamed from: getParams$facebook_common_release, reason: from getter */
        public final Bundle getParams() {
            return this.params;
        }

        public final Builder putArgument(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.params.putString(key, value);
            return this;
        }

        public final Builder putArgument(String key, String[] arrayValue) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(arrayValue, "arrayValue");
            this.params.putStringArray(key, arrayValue);
            return this;
        }

        @Override // com.facebook.share.model.ShareModelBuilder
        public Builder readFrom(CameraEffectArguments model) {
            if (model != null) {
                this.params.putAll(model.params);
            }
            return this;
        }

        public final Builder readFrom(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return readFrom((CameraEffectArguments) parcel.readParcelable(CameraEffectArguments.class.getClassLoader()));
        }

        @Override // com.facebook.share.ShareBuilder
        public CameraEffectArguments build() {
            return new CameraEffectArguments(this, null);
        }
    }
}
