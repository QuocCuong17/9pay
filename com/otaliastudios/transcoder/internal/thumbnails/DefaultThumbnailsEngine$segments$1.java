package com.otaliastudios.transcoder.internal.thumbnails;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DefaultThumbnailsEngine.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public /* synthetic */ class DefaultThumbnailsEngine$segments$1 extends FunctionReferenceImpl implements Function4<TrackType, Integer, TrackStatus, MediaFormat, Pipeline> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultThumbnailsEngine$segments$1(Object obj) {
        super(4, obj, DefaultThumbnailsEngine.class, "createPipeline", "createPipeline(Lcom/otaliastudios/transcoder/common/TrackType;ILcom/otaliastudios/transcoder/common/TrackStatus;Landroid/media/MediaFormat;)Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", 0);
    }

    public final Pipeline invoke(TrackType p0, int i, TrackStatus p2, MediaFormat p3) {
        Pipeline createPipeline;
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p3, "p3");
        createPipeline = ((DefaultThumbnailsEngine) this.receiver).createPipeline(p0, i, p2, p3);
        return createPipeline;
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Pipeline invoke(TrackType trackType, Integer num, TrackStatus trackStatus, MediaFormat mediaFormat) {
        return invoke(trackType, num.intValue(), trackStatus, mediaFormat);
    }
}
