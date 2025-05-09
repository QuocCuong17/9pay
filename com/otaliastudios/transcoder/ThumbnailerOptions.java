package com.otaliastudios.transcoder;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.motion.widget.Key;
import androidx.media3.extractor.ts.PsExtractor;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.share.internal.ShareConstants;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.otaliastudios.transcoder.resize.ExactResizer;
import com.otaliastudios.transcoder.resize.MultiResizer;
import com.otaliastudios.transcoder.resize.Resizer;
import com.otaliastudios.transcoder.source.DataSource;
import com.otaliastudios.transcoder.source.FileDescriptorDataSource;
import com.otaliastudios.transcoder.source.FilePathDataSource;
import com.otaliastudios.transcoder.source.UriDataSource;
import com.otaliastudios.transcoder.thumbnail.ThumbnailRequest;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThumbnailerOptions.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0001\u001bBA\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/otaliastudios/transcoder/ThumbnailerOptions;", "", "dataSources", "", "Lcom/otaliastudios/transcoder/source/DataSource;", "resizer", "Lcom/otaliastudios/transcoder/resize/Resizer;", Key.ROTATION, "", "thumbnailRequests", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/otaliastudios/transcoder/ThumbnailerListener;", "listenerHandler", "Landroid/os/Handler;", "(Ljava/util/List;Lcom/otaliastudios/transcoder/resize/Resizer;ILjava/util/List;Lcom/otaliastudios/transcoder/ThumbnailerListener;Landroid/os/Handler;)V", "getDataSources", "()Ljava/util/List;", "getListener", "()Lcom/otaliastudios/transcoder/ThumbnailerListener;", "getListenerHandler", "()Landroid/os/Handler;", "getResizer", "()Lcom/otaliastudios/transcoder/resize/Resizer;", "getRotation", "()I", "getThumbnailRequests", "Builder", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class ThumbnailerOptions {
    private final List<DataSource> dataSources;
    private final ThumbnailerListener listener;
    private final Handler listenerHandler;
    private final Resizer resizer;
    private final int rotation;
    private final List<ThumbnailRequest> thumbnailRequests;

    /* JADX WARN: Multi-variable type inference failed */
    public ThumbnailerOptions(List<? extends DataSource> dataSources, Resizer resizer, int i, List<? extends ThumbnailRequest> thumbnailRequests, ThumbnailerListener listener, Handler listenerHandler) {
        Intrinsics.checkNotNullParameter(dataSources, "dataSources");
        Intrinsics.checkNotNullParameter(resizer, "resizer");
        Intrinsics.checkNotNullParameter(thumbnailRequests, "thumbnailRequests");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(listenerHandler, "listenerHandler");
        this.dataSources = dataSources;
        this.resizer = resizer;
        this.rotation = i;
        this.thumbnailRequests = thumbnailRequests;
        this.listener = listener;
        this.listenerHandler = listenerHandler;
    }

    public final List<DataSource> getDataSources() {
        return this.dataSources;
    }

    public final Resizer getResizer() {
        return this.resizer;
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final List<ThumbnailRequest> getThumbnailRequests() {
        return this.thumbnailRequests;
    }

    public final ThumbnailerListener getListener() {
        return this.listener;
    }

    public final Handler getListenerHandler() {
        return this.listenerHandler;
    }

    /* compiled from: ThumbnailerOptions.kt */
    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0011J\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010#\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u000e\u0010$\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/otaliastudios/transcoder/ThumbnailerOptions$Builder;", "", "()V", "dataSources", "", "Lcom/otaliastudios/transcoder/source/DataSource;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/otaliastudios/transcoder/ThumbnailerListener;", "listenerHandler", "Landroid/os/Handler;", "resizer", "Lcom/otaliastudios/transcoder/resize/MultiResizer;", "resizerSet", "", Key.ROTATION, "", "thumbnailRequests", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "addDataSource", "context", "Landroid/content/Context;", ShareConstants.MEDIA_URI, "Landroid/net/Uri;", "dataSource", "fileDescriptor", "Ljava/io/FileDescriptor;", "filePath", "", "addResizer", "Lcom/otaliastudios/transcoder/resize/Resizer;", "addThumbnailRequest", "request", "build", "Lcom/otaliastudios/transcoder/ThumbnailerOptions;", InAppPurchaseConstants.METHOD_SET_LISTENER, "setListenerHandler", "setRotation", "thumbnails", "Ljava/util/concurrent/Future;", "Ljava/lang/Void;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Builder {
        private ThumbnailerListener listener;
        private Handler listenerHandler;
        private boolean resizerSet;
        private int rotation;
        private final List<DataSource> dataSources = new ArrayList();
        private final List<ThumbnailRequest> thumbnailRequests = new ArrayList();
        private final MultiResizer resizer = new MultiResizer();

        public final Builder addDataSource(DataSource dataSource) {
            Intrinsics.checkNotNullParameter(dataSource, "dataSource");
            this.dataSources.add(dataSource);
            return this;
        }

        public final Builder addDataSource(FileDescriptor fileDescriptor) {
            Intrinsics.checkNotNullParameter(fileDescriptor, "fileDescriptor");
            return addDataSource(new FileDescriptorDataSource(fileDescriptor));
        }

        public final Builder addDataSource(String filePath) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            return addDataSource(new FilePathDataSource(filePath));
        }

        public final Builder addDataSource(Context context, Uri uri) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, "uri");
            return addDataSource(new UriDataSource(context, uri));
        }

        public final Builder addResizer(Resizer resizer) {
            Intrinsics.checkNotNullParameter(resizer, "resizer");
            this.resizer.addResizer(resizer);
            this.resizerSet = true;
            return this;
        }

        public final Builder setRotation(int rotation) {
            this.rotation = rotation;
            return this;
        }

        public final Builder addThumbnailRequest(ThumbnailRequest request) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.thumbnailRequests.add(request);
            return this;
        }

        public final Builder setListenerHandler(Handler listenerHandler) {
            this.listenerHandler = listenerHandler;
            return this;
        }

        public final Builder setListener(ThumbnailerListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listener = listener;
            return this;
        }

        public final ThumbnailerOptions build() {
            if (!(!this.dataSources.isEmpty())) {
                throw new IllegalArgumentException("At least one data source is required!".toString());
            }
            if (!(!this.thumbnailRequests.isEmpty())) {
                throw new IllegalArgumentException("At least one thumbnail request is required!".toString());
            }
            ThumbnailerListener thumbnailerListener = this.listener;
            if (thumbnailerListener == null) {
                throw new IllegalArgumentException("Listener can't be null.".toString());
            }
            Handler handler = this.listenerHandler;
            if (handler == null) {
                Looper myLooper = Looper.myLooper();
                if (myLooper == null) {
                    myLooper = Looper.getMainLooper();
                }
                handler = new Handler(myLooper);
            }
            return new ThumbnailerOptions(CollectionsKt.toList(this.dataSources), this.resizerSet ? this.resizer : new ExactResizer(BaselineTIFFTagSet.TAG_COLOR_MAP, PsExtractor.VIDEO_STREAM_MASK), this.rotation, CollectionsKt.toList(this.thumbnailRequests), thumbnailerListener, handler);
        }

        public final Future<Void> thumbnails() {
            return Thumbnailer.INSTANCE.getInstance().thumbnails(build());
        }
    }
}
