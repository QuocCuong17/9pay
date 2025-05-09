package com.otaliastudios.transcoder;

import com.otaliastudios.transcoder.ThumbnailerOptions;
import com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsEngine;
import com.otaliastudios.transcoder.internal.utils.ThreadPool;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Thumbnailer.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007J%\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/otaliastudios/transcoder/Thumbnailer;", "", "()V", "thumbnails", "Ljava/util/concurrent/Future;", "Ljava/lang/Void;", "options", "Lcom/otaliastudios/transcoder/ThumbnailerOptions;", "builder", "Lkotlin/Function1;", "Lcom/otaliastudios/transcoder/ThumbnailerOptions$Builder;", "", "Lkotlin/ExtensionFunctionType;", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Thumbnailer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ Thumbnailer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Thumbnailer() {
    }

    public final Future<Void> thumbnails(final ThumbnailerOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        Future<Void> submit = ThreadPool.getExecutor().submit(new Callable() { // from class: com.otaliastudios.transcoder.Thumbnailer$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void m983thumbnails$lambda0;
                m983thumbnails$lambda0 = Thumbnailer.m983thumbnails$lambda0(ThumbnailerOptions.this);
                return m983thumbnails$lambda0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(submit, "executor.submit(Callable…          null\n        })");
        return submit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: thumbnails$lambda-0, reason: not valid java name */
    public static final Void m983thumbnails$lambda0(ThumbnailerOptions options) {
        Intrinsics.checkNotNullParameter(options, "$options");
        ThumbnailsEngine.INSTANCE.thumbnails(options);
        return null;
    }

    public final Future<Void> thumbnails(Function1<? super ThumbnailerOptions.Builder, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ThumbnailerOptions.Builder builder2 = new ThumbnailerOptions.Builder();
        builder.invoke(builder2);
        return thumbnails(builder2.build());
    }

    /* compiled from: Thumbnailer.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/otaliastudios/transcoder/Thumbnailer$Companion;", "", "()V", "getInstance", "Lcom/otaliastudios/transcoder/Thumbnailer;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Thumbnailer getInstance() {
            return new Thumbnailer(null);
        }
    }
}
