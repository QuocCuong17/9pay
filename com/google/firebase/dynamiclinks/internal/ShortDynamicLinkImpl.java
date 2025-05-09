package com.google.firebase.dynamiclinks.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class ShortDynamicLinkImpl extends AbstractSafeParcelable implements ShortDynamicLink {
    public static final Parcelable.Creator<ShortDynamicLinkImpl> CREATOR = new ShortDynamicLinkImplCreator();
    private final Uri previewLink;
    private final Uri shortLink;
    private final List<WarningImpl> warnings;

    public ShortDynamicLinkImpl(Uri uri, Uri uri2, List<WarningImpl> list) {
        this.shortLink = uri;
        this.previewLink = uri2;
        this.warnings = list == null ? new ArrayList<>() : list;
    }

    @Override // com.google.firebase.dynamiclinks.ShortDynamicLink
    public Uri getShortLink() {
        return this.shortLink;
    }

    @Override // com.google.firebase.dynamiclinks.ShortDynamicLink
    public Uri getPreviewLink() {
        return this.previewLink;
    }

    @Override // com.google.firebase.dynamiclinks.ShortDynamicLink
    public List<WarningImpl> getWarnings() {
        return this.warnings;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ShortDynamicLinkImplCreator.writeToParcel(this, parcel, i);
    }

    /* loaded from: classes4.dex */
    public static class WarningImpl extends AbstractSafeParcelable implements ShortDynamicLink.Warning {
        public static final Parcelable.Creator<WarningImpl> CREATOR = new WarningImplCreator();
        private final String message;

        @Override // com.google.firebase.dynamiclinks.ShortDynamicLink.Warning
        public String getCode() {
            return null;
        }

        public WarningImpl(String str) {
            this.message = str;
        }

        @Override // com.google.firebase.dynamiclinks.ShortDynamicLink.Warning
        public String getMessage() {
            return this.message;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            WarningImplCreator.writeToParcel(this, parcel, i);
        }
    }
}
