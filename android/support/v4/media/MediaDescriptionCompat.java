package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final long BT_FOLDER_TYPE_ALBUMS = 2;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3;
    public static final long BT_FOLDER_TYPE_GENRES = 4;
    public static final long BT_FOLDER_TYPE_MIXED = 0;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5;
    public static final long BT_FOLDER_TYPE_TITLES = 1;
    public static final long BT_FOLDER_TYPE_YEARS = 6;
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new Parcelable.Creator<MediaDescriptionCompat>() { // from class: android.support.v4.media.MediaDescriptionCompat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            if (Build.VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(parcel);
            }
            return MediaDescriptionCompat.fromMediaDescription(MediaDescription.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    };
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
    public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
    public static final long STATUS_DOWNLOADED = 2;
    public static final long STATUS_DOWNLOADING = 1;
    public static final long STATUS_NOT_DOWNLOADED = 0;
    private static final String TAG = "MediaDescriptionCompat";
    private final CharSequence mDescription;
    private MediaDescription mDescriptionFwk;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.mMediaId = str;
        this.mTitle = charSequence;
        this.mSubtitle = charSequence2;
        this.mDescription = charSequence3;
        this.mIcon = bitmap;
        this.mIconUri = uri;
        this.mExtras = bundle;
        this.mMediaUri = uri2;
    }

    MediaDescriptionCompat(Parcel parcel) {
        this.mMediaId = parcel.readString();
        this.mTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSubtitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mDescription = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = getClass().getClassLoader();
        this.mIcon = (Bitmap) parcel.readParcelable(classLoader);
        this.mIconUri = (Uri) parcel.readParcelable(classLoader);
        this.mExtras = parcel.readBundle(classLoader);
        this.mMediaUri = (Uri) parcel.readParcelable(classLoader);
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public Bitmap getIconBitmap() {
        return this.mIcon;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public Uri getMediaUri() {
        return this.mMediaUri;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT < 21) {
            parcel.writeString(this.mMediaId);
            TextUtils.writeToParcel(this.mTitle, parcel, i);
            TextUtils.writeToParcel(this.mSubtitle, parcel, i);
            TextUtils.writeToParcel(this.mDescription, parcel, i);
            parcel.writeParcelable(this.mIcon, i);
            parcel.writeParcelable(this.mIconUri, i);
            parcel.writeBundle(this.mExtras);
            parcel.writeParcelable(this.mMediaUri, i);
            return;
        }
        ((MediaDescription) getMediaDescription()).writeToParcel(parcel, i);
    }

    public String toString() {
        return ((Object) this.mTitle) + ", " + ((Object) this.mSubtitle) + ", " + ((Object) this.mDescription);
    }

    public Object getMediaDescription() {
        Bundle bundle;
        if (this.mDescriptionFwk != null || Build.VERSION.SDK_INT < 21) {
            return this.mDescriptionFwk;
        }
        MediaDescription.Builder createBuilder = Api21Impl.createBuilder();
        Api21Impl.setMediaId(createBuilder, this.mMediaId);
        Api21Impl.setTitle(createBuilder, this.mTitle);
        Api21Impl.setSubtitle(createBuilder, this.mSubtitle);
        Api21Impl.setDescription(createBuilder, this.mDescription);
        Api21Impl.setIconBitmap(createBuilder, this.mIcon);
        Api21Impl.setIconUri(createBuilder, this.mIconUri);
        if (Build.VERSION.SDK_INT < 23 && this.mMediaUri != null) {
            if (this.mExtras == null) {
                bundle = new Bundle();
                bundle.putBoolean(DESCRIPTION_KEY_NULL_BUNDLE_FLAG, true);
            } else {
                bundle = new Bundle(this.mExtras);
            }
            bundle.putParcelable(DESCRIPTION_KEY_MEDIA_URI, this.mMediaUri);
            Api21Impl.setExtras(createBuilder, bundle);
        } else {
            Api21Impl.setExtras(createBuilder, this.mExtras);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setMediaUri(createBuilder, this.mMediaUri);
        }
        MediaDescription build = Api21Impl.build(createBuilder);
        this.mDescriptionFwk = build;
        return build;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static MediaDescriptionCompat fromMediaDescription(Object obj) {
        Bundle bundle = null;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        Builder builder = new Builder();
        MediaDescription mediaDescription = (MediaDescription) obj;
        builder.setMediaId(Api21Impl.getMediaId(mediaDescription));
        builder.setTitle(Api21Impl.getTitle(mediaDescription));
        builder.setSubtitle(Api21Impl.getSubtitle(mediaDescription));
        builder.setDescription(Api21Impl.getDescription(mediaDescription));
        builder.setIconBitmap(Api21Impl.getIconBitmap(mediaDescription));
        builder.setIconUri(Api21Impl.getIconUri(mediaDescription));
        Bundle extras = Api21Impl.getExtras(mediaDescription);
        if (extras != null) {
            extras = MediaSessionCompat.unparcelWithClassLoader(extras);
        }
        Uri uri = extras != null ? (Uri) extras.getParcelable(DESCRIPTION_KEY_MEDIA_URI) : null;
        if (uri != null) {
            if (!extras.containsKey(DESCRIPTION_KEY_NULL_BUNDLE_FLAG) || extras.size() != 2) {
                extras.remove(DESCRIPTION_KEY_MEDIA_URI);
                extras.remove(DESCRIPTION_KEY_NULL_BUNDLE_FLAG);
            }
            builder.setExtras(bundle);
            if (uri == null) {
                builder.setMediaUri(uri);
            } else if (Build.VERSION.SDK_INT >= 23) {
                builder.setMediaUri(Api23Impl.getMediaUri(mediaDescription));
            }
            MediaDescriptionCompat build = builder.build();
            build.mDescriptionFwk = mediaDescription;
            return build;
        }
        bundle = extras;
        builder.setExtras(bundle);
        if (uri == null) {
        }
        MediaDescriptionCompat build2 = builder.build();
        build2.mDescriptionFwk = mediaDescription;
        return build2;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder setMediaId(String str) {
            this.mMediaId = str;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.mSubtitle = charSequence;
            return this;
        }

        public Builder setDescription(CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap) {
            this.mIcon = bitmap;
            return this;
        }

        public Builder setIconUri(Uri uri) {
            this.mIconUri = uri;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setMediaUri(Uri uri) {
            this.mMediaUri = uri;
            return this;
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api21Impl {
        private Api21Impl() {
        }

        static MediaDescription.Builder createBuilder() {
            return new MediaDescription.Builder();
        }

        static void setMediaId(MediaDescription.Builder builder, String str) {
            builder.setMediaId(str);
        }

        static void setTitle(MediaDescription.Builder builder, CharSequence charSequence) {
            builder.setTitle(charSequence);
        }

        static void setSubtitle(MediaDescription.Builder builder, CharSequence charSequence) {
            builder.setSubtitle(charSequence);
        }

        static void setDescription(MediaDescription.Builder builder, CharSequence charSequence) {
            builder.setDescription(charSequence);
        }

        static void setIconBitmap(MediaDescription.Builder builder, Bitmap bitmap) {
            builder.setIconBitmap(bitmap);
        }

        static void setIconUri(MediaDescription.Builder builder, Uri uri) {
            builder.setIconUri(uri);
        }

        static void setExtras(MediaDescription.Builder builder, Bundle bundle) {
            builder.setExtras(bundle);
        }

        static MediaDescription build(MediaDescription.Builder builder) {
            return builder.build();
        }

        static String getMediaId(MediaDescription mediaDescription) {
            return mediaDescription.getMediaId();
        }

        static CharSequence getTitle(MediaDescription mediaDescription) {
            return mediaDescription.getTitle();
        }

        static CharSequence getSubtitle(MediaDescription mediaDescription) {
            return mediaDescription.getSubtitle();
        }

        static CharSequence getDescription(MediaDescription mediaDescription) {
            return mediaDescription.getDescription();
        }

        static Bitmap getIconBitmap(MediaDescription mediaDescription) {
            return mediaDescription.getIconBitmap();
        }

        static Uri getIconUri(MediaDescription mediaDescription) {
            return mediaDescription.getIconUri();
        }

        static Bundle getExtras(MediaDescription mediaDescription) {
            return mediaDescription.getExtras();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api23Impl {
        private Api23Impl() {
        }

        static void setMediaUri(MediaDescription.Builder builder, Uri uri) {
            builder.setMediaUri(uri);
        }

        static Uri getMediaUri(MediaDescription mediaDescription) {
            return mediaDescription.getMediaUri();
        }
    }
}
