package com.otaliastudios.transcoder.internal.thumbnails;

import com.otaliastudios.transcoder.ThumbnailerOptions;
import com.otaliastudios.transcoder.internal.DataSources;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.thumbnail.Thumbnail;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThumbnailsEngine.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u001c\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007H&¨\u0006\n"}, d2 = {"Lcom/otaliastudios/transcoder/internal/thumbnails/ThumbnailsEngine;", "", "()V", "cleanup", "", "thumbnails", "progress", "Lkotlin/Function1;", "Lcom/otaliastudios/transcoder/thumbnail/Thumbnail;", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class ThumbnailsEngine {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger log = new Logger("ThumbnailsEngine");

    @JvmStatic
    public static final void thumbnails(ThumbnailerOptions thumbnailerOptions) {
        INSTANCE.thumbnails(thumbnailerOptions);
    }

    public abstract void cleanup();

    public abstract void thumbnails(Function1<? super Thumbnail, Unit> progress);

    /* compiled from: ThumbnailsEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/thumbnails/ThumbnailsEngine$Companion;", "", "()V", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "thumbnails", "", "options", "Lcom/otaliastudios/transcoder/ThumbnailerOptions;", "isInterrupted", "", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final boolean isInterrupted(Throwable th) {
            Throwable cause;
            if (th instanceof InterruptedException) {
                return true;
            }
            if (Intrinsics.areEqual(th, th.getCause()) || (cause = th.getCause()) == null) {
                return false;
            }
            return isInterrupted(cause);
        }

        @JvmStatic
        public final void thumbnails(ThumbnailerOptions options) {
            DefaultThumbnailsEngine defaultThumbnailsEngine;
            Intrinsics.checkNotNullParameter(options, "options");
            ThumbnailsEngine.log.i("thumbnails(): called...");
            final ThumbnailsDispatcher thumbnailsDispatcher = new ThumbnailsDispatcher(options);
            DefaultThumbnailsEngine defaultThumbnailsEngine2 = null;
            try {
                try {
                    defaultThumbnailsEngine = new DefaultThumbnailsEngine(new DataSources(options), options.getRotation(), options.getResizer(), options.getThumbnailRequests());
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
            try {
                defaultThumbnailsEngine.thumbnails(new Function1<Thumbnail, Unit>() { // from class: com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsEngine$Companion$thumbnails$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Thumbnail thumbnail) {
                        invoke2(thumbnail);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Thumbnail it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        ThumbnailsDispatcher.this.dispatchThumbnail(it);
                    }
                });
                thumbnailsDispatcher.dispatchCompletion();
                defaultThumbnailsEngine.cleanup();
            } catch (Exception e2) {
                e = e2;
                defaultThumbnailsEngine2 = defaultThumbnailsEngine;
                if (isInterrupted(e)) {
                    ThumbnailsEngine.log.i("Transcode canceled.", e);
                    thumbnailsDispatcher.dispatchCancel();
                    if (defaultThumbnailsEngine2 == null) {
                        return;
                    }
                    defaultThumbnailsEngine2.cleanup();
                    return;
                }
                ThumbnailsEngine.log.e("Unexpected error while transcoding.", e);
                thumbnailsDispatcher.dispatchFailure(e);
                throw e;
            } catch (Throwable th2) {
                th = th2;
                defaultThumbnailsEngine2 = defaultThumbnailsEngine;
                if (defaultThumbnailsEngine2 != null) {
                    defaultThumbnailsEngine2.cleanup();
                }
                throw th;
            }
        }
    }
}
