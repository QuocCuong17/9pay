package co.hyperverge.hypersnapsdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.media3.datasource.cache.CacheDataSink;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.PicassoManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/* compiled from: PicassoManager.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ2\u0010\u000f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0014\u0010\u0015\u001a\u00020\n2\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hypersnapsdk/utils/PicassoManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "picasso", "Lcom/squareup/picasso/Picasso;", "createOkHttp3Downloader", "Lcom/squareup/picasso/OkHttp3Downloader;", "getBitmap", "", "httpUrl", "", "callback", "Lco/hyperverge/hypersnapsdk/utils/PicassoManager$ImageDownloaderCallback;", "loadInto", "placeholderDrawable", "Landroid/graphics/drawable/Drawable;", "errorDrawable", "imageView", "Landroid/widget/ImageView;", "logError", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "prefetchImage", "Companion", "ImageDownloaderCallback", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class PicassoManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "PicassoUtils";
    private static PicassoManager instance;
    private final Picasso picasso;

    /* compiled from: PicassoManager.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, d2 = {"Lco/hyperverge/hypersnapsdk/utils/PicassoManager$ImageDownloaderCallback;", "", "onError", "", "errorMessage", "", "onSuccess", "bitmap", "Landroid/graphics/Bitmap;", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public interface ImageDownloaderCallback {
        void onError(String errorMessage);

        void onSuccess(Bitmap bitmap);
    }

    public /* synthetic */ PicassoManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createOkHttp3Downloader$lambda-0, reason: not valid java name */
    public static final boolean m549createOkHttp3Downloader$lambda0(String str, SSLSession sSLSession) {
        return true;
    }

    @JvmStatic
    public static final PicassoManager getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    private PicassoManager(Context context) {
        Picasso build = new Picasso.Builder(context).downloader(createOkHttp3Downloader(context)).loggingEnabled(false).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(context)\n       …lse)\n            .build()");
        this.picasso = build;
    }

    public final void prefetchImage(String httpUrl) {
        HVLogUtils.d(TAG, Intrinsics.stringPlus("prefetchImage() called with: httpUrl = ", httpUrl));
        this.picasso.load(httpUrl).fetch(new Callback() { // from class: co.hyperverge.hypersnapsdk.utils.PicassoManager$prefetchImage$1
            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
            }

            @Override // com.squareup.picasso.Callback
            public void onError(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                PicassoManager.this.logError(e);
            }
        });
    }

    public final void getBitmap(String httpUrl, final ImageDownloaderCallback callback) {
        HVLogUtils.d(TAG, "getBitmap() called with: httpUrl = " + ((Object) httpUrl) + ", callback = " + callback);
        this.picasso.load(httpUrl).into(new Target() { // from class: co.hyperverge.hypersnapsdk.utils.PicassoManager$getBitmap$1
            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                Intrinsics.checkNotNullParameter(from, "from");
                PicassoManager.ImageDownloaderCallback imageDownloaderCallback = PicassoManager.ImageDownloaderCallback.this;
                if (imageDownloaderCallback == null) {
                    return;
                }
                imageDownloaderCallback.onSuccess(bitmap);
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Intrinsics.checkNotNullParameter(e, "e");
                this.logError(e);
                PicassoManager.ImageDownloaderCallback imageDownloaderCallback = PicassoManager.ImageDownloaderCallback.this;
                if (imageDownloaderCallback == null) {
                    return;
                }
                imageDownloaderCallback.onError(e.getMessage());
            }
        });
    }

    public static /* synthetic */ void loadInto$default(PicassoManager picassoManager, String str, Drawable drawable, Drawable drawable2, ImageView imageView, int i, Object obj) {
        if ((i & 2) != 0) {
            drawable = null;
        }
        if ((i & 4) != 0) {
            drawable2 = null;
        }
        picassoManager.loadInto(str, drawable, drawable2, imageView);
    }

    public final void loadInto(String httpUrl, Drawable placeholderDrawable, Drawable errorDrawable, ImageView imageView) {
        HVLogUtils.d(TAG, "loadInto() called with: httpUrl = " + ((Object) httpUrl) + ", placeholderDrawable = " + placeholderDrawable + ", errorDrawable = " + errorDrawable + ", imageView = " + imageView);
        RequestCreator load = this.picasso.load(httpUrl);
        if (placeholderDrawable != null) {
            load.placeholder(placeholderDrawable);
        }
        if (errorDrawable != null) {
            load.error(errorDrawable);
        }
        load.into(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logError(Exception e) {
        HVLogUtils.d(TAG, Intrinsics.stringPlus("logError() called with: e = ", e));
        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final OkHttp3Downloader createOkHttp3Downloader(Context context) {
        SSLContext sSLContext;
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        File file;
        HVLogUtils.d(TAG, Intrinsics.stringPlus("createOkHttp3Downloader() called with: context = ", context));
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: co.hyperverge.hypersnapsdk.utils.PicassoManager$createOkHttp3Downloader$trustAllCertificates$1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Intrinsics.checkNotNullParameter(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Intrinsics.checkNotNullParameter(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        try {
            sSLContext = SSLContext.getInstance("TLS");
        } catch (KeyManagementException e3) {
            sSLContext = null;
            e2 = e3;
        } catch (NoSuchAlgorithmException e4) {
            sSLContext = null;
            e = e4;
        }
        try {
            sSLContext.init(null, trustManagerArr, new SecureRandom());
        } catch (KeyManagementException e5) {
            e2 = e5;
            logError(e2);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (sSLContext != null) {
            }
            file = new File(context.getCacheDir(), "picasso-cache");
            if (!file.exists()) {
            }
            return new OkHttp3Downloader(builder.cache(new Cache(file, CacheDataSink.DEFAULT_FRAGMENT_SIZE)).build());
        } catch (NoSuchAlgorithmException e6) {
            e = e6;
            logError(e);
            OkHttpClient.Builder builder2 = new OkHttpClient.Builder();
            if (sSLContext != null) {
            }
            file = new File(context.getCacheDir(), "picasso-cache");
            if (!file.exists()) {
            }
            return new OkHttp3Downloader(builder2.cache(new Cache(file, CacheDataSink.DEFAULT_FRAGMENT_SIZE)).build());
        }
        OkHttpClient.Builder builder22 = new OkHttpClient.Builder();
        if (sSLContext != null) {
            builder22.sslSocketFactory(sSLContext.getSocketFactory(), (X509TrustManager) trustManagerArr[0]);
            builder22.hostnameVerifier(new HostnameVerifier() { // from class: co.hyperverge.hypersnapsdk.utils.PicassoManager$$ExternalSyntheticLambda0
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    boolean m549createOkHttp3Downloader$lambda0;
                    m549createOkHttp3Downloader$lambda0 = PicassoManager.m549createOkHttp3Downloader$lambda0(str, sSLSession);
                    return m549createOkHttp3Downloader$lambda0;
                }
            });
        }
        file = new File(context.getCacheDir(), "picasso-cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new OkHttp3Downloader(builder22.cache(new Cache(file, CacheDataSink.DEFAULT_FRAGMENT_SIZE)).build());
    }

    /* compiled from: PicassoManager.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lco/hyperverge/hypersnapsdk/utils/PicassoManager$Companion;", "", "()V", "TAG", "", "instance", "Lco/hyperverge/hypersnapsdk/utils/PicassoManager;", "getInstance", "context", "Landroid/content/Context;", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PicassoManager getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (PicassoManager.instance == null) {
                PicassoManager.instance = new PicassoManager(context, defaultConstructorMarker);
            }
            PicassoManager picassoManager = PicassoManager.instance;
            if (picassoManager != null) {
                return picassoManager;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instance");
            return null;
        }
    }
}
