package com.facebook.messenger;

import android.net.Uri;
import androidx.media3.common.MimeTypes;
import androidx.webkit.ProxyConfig;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShareToMessengerParams.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/facebook/messenger/ShareToMessengerParams;", "", "builder", "Lcom/facebook/messenger/ShareToMessengerParamsBuilder;", "(Lcom/facebook/messenger/ShareToMessengerParamsBuilder;)V", "externalUri", "Landroid/net/Uri;", "getExternalUri", "()Landroid/net/Uri;", "metaData", "", "getMetaData", "()Ljava/lang/String;", "mimeType", "getMimeType", ShareConstants.MEDIA_URI, "getUri", "Companion", "facebook-messenger_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ShareToMessengerParams {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> VALID_EXTERNAL_URI_SCHEMES;
    private static final Set<String> VALID_MIME_TYPES;
    private static final Set<String> VALID_URI_SCHEMES;
    private final Uri externalUri;
    private final String metaData;
    private final String mimeType;
    private final Uri uri;

    @JvmStatic
    public static final ShareToMessengerParamsBuilder newBuilder(Uri uri, String str) {
        return INSTANCE.newBuilder(uri, str);
    }

    /* compiled from: ShareToMessengerParams.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/facebook/messenger/ShareToMessengerParams$Companion;", "", "()V", "VALID_EXTERNAL_URI_SCHEMES", "", "", "getVALID_EXTERNAL_URI_SCHEMES", "()Ljava/util/Set;", "VALID_MIME_TYPES", "getVALID_MIME_TYPES", "VALID_URI_SCHEMES", "getVALID_URI_SCHEMES", InAppPurchaseConstants.METHOD_NEW_BUILDER, "Lcom/facebook/messenger/ShareToMessengerParamsBuilder;", ShareConstants.MEDIA_URI, "Landroid/net/Uri;", "mimeType", "facebook-messenger_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getVALID_URI_SCHEMES() {
            return ShareToMessengerParams.VALID_URI_SCHEMES;
        }

        public final Set<String> getVALID_MIME_TYPES() {
            return ShareToMessengerParams.VALID_MIME_TYPES;
        }

        public final Set<String> getVALID_EXTERNAL_URI_SCHEMES() {
            return ShareToMessengerParams.VALID_EXTERNAL_URI_SCHEMES;
        }

        @JvmStatic
        public final ShareToMessengerParamsBuilder newBuilder(Uri uri, String mimeType) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            return new ShareToMessengerParamsBuilder(uri, mimeType);
        }
    }

    public ShareToMessengerParams(ShareToMessengerParamsBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Uri uri = builder.getUri();
        if (uri == null) {
            throw new IllegalStateException("Must provide non-null uri".toString());
        }
        this.uri = uri;
        String mimeType = builder.getMimeType();
        if (mimeType == null) {
            throw new IllegalStateException("Must provide mimeType".toString());
        }
        this.mimeType = mimeType;
        this.metaData = builder.getMetaData();
        Uri externalUri = builder.getExternalUri();
        this.externalUri = externalUri;
        if (!CollectionsKt.contains(VALID_URI_SCHEMES, uri.getScheme())) {
            throw new IllegalArgumentException(("Unsupported URI scheme: " + uri.getScheme()).toString());
        }
        if (!VALID_MIME_TYPES.contains(mimeType)) {
            throw new IllegalArgumentException(("Unsupported mime-type: " + mimeType).toString());
        }
        if (externalUri == null || CollectionsKt.contains(VALID_EXTERNAL_URI_SCHEMES, externalUri.getScheme())) {
            return;
        }
        throw new IllegalArgumentException(("Unsupported external uri scheme: " + externalUri.getScheme()).toString());
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("image/*");
        hashSet.add("image/jpeg");
        hashSet.add(MimeTypes.IMAGE_PNG);
        hashSet.add("image/gif");
        hashSet.add(MimeTypes.IMAGE_WEBP);
        hashSet.add("video/*");
        hashSet.add(MimeTypes.VIDEO_MP4);
        hashSet.add("audio/*");
        hashSet.add(MimeTypes.AUDIO_MPEG);
        VALID_MIME_TYPES = CollectionsKt.toSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add(FirebaseAnalytics.Param.CONTENT);
        hashSet2.add("android.resource");
        hashSet2.add("file");
        VALID_URI_SCHEMES = CollectionsKt.toSet(hashSet2);
        HashSet hashSet3 = new HashSet();
        hashSet3.add(ProxyConfig.MATCH_HTTP);
        hashSet3.add("https");
        VALID_EXTERNAL_URI_SCHEMES = CollectionsKt.toSet(hashSet3);
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getMetaData() {
        return this.metaData;
    }

    public final Uri getExternalUri() {
        return this.externalUri;
    }
}
