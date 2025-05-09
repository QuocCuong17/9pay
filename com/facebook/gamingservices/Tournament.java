package com.facebook.gamingservices;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.facebook.gamingservices.internal.DateFormatter;
import com.facebook.share.model.ShareModel;
import com.facebook.share.model.ShareModelBuilder;
import com.google.gson.annotations.SerializedName;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Tournament.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0013H\u0016R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f8F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/gamingservices/Tournament;", "Lcom/facebook/share/model/ShareModel;", "identifier", "", SDKConstants.PARAM_END_TIME, "title", "payload", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "newValue", "Ljava/time/ZonedDateTime;", "expiration", "getExpiration", "()Ljava/time/ZonedDateTime;", "setExpiration", "(Ljava/time/ZonedDateTime;)V", "describeContents", "", "writeToParcel", "", "out", "flags", "Builder", "CREATOR", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Tournament implements ShareModel {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @SerializedName("tournament_end_time")
    public String endTime;

    @SerializedName("id")
    public final String identifier;

    @SerializedName(SDKConstants.PARAM_TOURNAMENTS_PAYLOAD)
    public final String payload;

    @SerializedName(SDKConstants.PARAM_TOURNAMENTS_TITLE)
    public final String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final ZonedDateTime getExpiration() {
        String str = this.endTime;
        if (str != null) {
            return DateFormatter.INSTANCE.format$facebook_gamingservices_release(str);
        }
        return null;
    }

    private final void setExpiration(ZonedDateTime zonedDateTime) {
        if (Build.VERSION.SDK_INT < 26 || zonedDateTime == null) {
            return;
        }
        this.endTime = zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        setExpiration(zonedDateTime);
    }

    public Tournament(String identifier, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        this.identifier = identifier;
        this.endTime = str;
        this.title = str2;
        this.payload = str3;
        setExpiration(str != null ? DateFormatter.INSTANCE.format$facebook_gamingservices_release(str) : null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Tournament(Parcel parcel) {
        this(parcel.toString(), parcel.toString(), parcel.toString(), parcel.toString());
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }

    /* compiled from: Tournament.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\tJ\b\u0010\u0019\u001a\u00020\u0002H\u0016J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J7\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\u0010\u0010\u0005\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\t\u0010#\u001a\u00020$HÖ\u0001J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\b\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\u0012\u0010%\u001a\u00020\u00002\b\u0010&\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\t\u0010'\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006("}, d2 = {"Lcom/facebook/gamingservices/Tournament$Builder;", "Lcom/facebook/share/model/ShareModelBuilder;", "Lcom/facebook/gamingservices/Tournament;", "identifier", "", "expiration", "Ljava/time/ZonedDateTime;", "title", "payload", "(Ljava/lang/String;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/lang/String;)V", SDKConstants.PARAM_END_TIME, "getEndTime", "()Ljava/lang/String;", "setEndTime", "(Ljava/lang/String;)V", "getExpiration", "()Ljava/time/ZonedDateTime;", "setExpiration", "(Ljava/time/ZonedDateTime;)V", "getIdentifier", "setIdentifier", "getPayload", "setPayload", "getTitle", "setTitle", "build", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "readFrom", "tournament", InAppPurchaseConstants.METHOD_TO_STRING, "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Builder implements ShareModelBuilder<Tournament, Builder> {
        private String endTime;
        private ZonedDateTime expiration;
        private String identifier;
        private String payload;
        private String title;

        public static /* synthetic */ Builder copy$default(Builder builder, String str, ZonedDateTime zonedDateTime, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.identifier;
            }
            if ((i & 2) != 0) {
                zonedDateTime = builder.expiration;
            }
            if ((i & 4) != 0) {
                str2 = builder.title;
            }
            if ((i & 8) != 0) {
                str3 = builder.payload;
            }
            return builder.copy(str, zonedDateTime, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getIdentifier() {
            return this.identifier;
        }

        /* renamed from: component2, reason: from getter */
        public final ZonedDateTime getExpiration() {
            return this.expiration;
        }

        /* renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* renamed from: component4, reason: from getter */
        public final String getPayload() {
            return this.payload;
        }

        public final Builder copy(String identifier, ZonedDateTime expiration, String title, String payload) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            return new Builder(identifier, expiration, title, payload);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) other;
            return Intrinsics.areEqual(this.identifier, builder.identifier) && Intrinsics.areEqual(this.expiration, builder.expiration) && Intrinsics.areEqual(this.title, builder.title) && Intrinsics.areEqual(this.payload, builder.payload);
        }

        public int hashCode() {
            int hashCode = this.identifier.hashCode() * 31;
            ZonedDateTime zonedDateTime = this.expiration;
            int hashCode2 = (hashCode + (zonedDateTime == null ? 0 : zonedDateTime.hashCode())) * 31;
            String str = this.title;
            int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.payload;
            return hashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Builder(identifier=" + this.identifier + ", expiration=" + this.expiration + ", title=" + this.title + ", payload=" + this.payload + ')';
        }

        public Builder(String identifier, ZonedDateTime zonedDateTime, String str, String str2) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            this.identifier = identifier;
            this.expiration = zonedDateTime;
            this.title = str;
            this.payload = str2;
        }

        public /* synthetic */ Builder(String str, ZonedDateTime zonedDateTime, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : zonedDateTime, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
        }

        public final String getIdentifier() {
            return this.identifier;
        }

        public final void setIdentifier(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.identifier = str;
        }

        public final ZonedDateTime getExpiration() {
            return this.expiration;
        }

        public final void setExpiration(ZonedDateTime zonedDateTime) {
            this.expiration = zonedDateTime;
        }

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final String getPayload() {
            return this.payload;
        }

        public final void setPayload(String str) {
            this.payload = str;
        }

        public final String getEndTime() {
            return this.endTime;
        }

        public final void setEndTime(String str) {
            this.endTime = str;
        }

        public final Builder identifier(String identifier) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            this.identifier = identifier;
            return this;
        }

        public final Builder expiration(ZonedDateTime expiration) {
            this.expiration = expiration;
            if (Build.VERSION.SDK_INT >= 26 && expiration != null) {
                this.endTime = expiration.format(DateTimeFormatter.ISO_DATE_TIME);
            }
            return this;
        }

        public final Builder title(String title) {
            this.title = title;
            return this;
        }

        public final Builder payload(String payload) {
            this.payload = payload;
            return this;
        }

        @Override // com.facebook.share.ShareBuilder
        public Tournament build() {
            return new Tournament(this.identifier, this.endTime, this.title, this.payload);
        }

        @Override // com.facebook.share.model.ShareModelBuilder
        public Builder readFrom(Tournament tournament) {
            Builder payload;
            return (tournament == null || (payload = identifier(tournament.identifier).expiration(tournament.getExpiration()).title(tournament.title).payload(tournament.payload)) == null) ? this : payload;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.identifier);
        out.writeString(this.endTime);
        out.writeString(this.title);
        out.writeString(this.payload);
    }

    /* compiled from: Tournament.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/facebook/gamingservices/Tournament$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/facebook/gamingservices/Tournament;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/facebook/gamingservices/Tournament;", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: com.facebook.gamingservices.Tournament$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<Tournament> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tournament createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Tournament(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tournament[] newArray(int size) {
            return new Tournament[size];
        }
    }
}
