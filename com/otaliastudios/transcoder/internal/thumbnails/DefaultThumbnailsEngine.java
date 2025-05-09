package com.otaliastudios.transcoder.internal.thumbnails;

import android.graphics.Bitmap;
import android.media.MediaFormat;
import androidx.constraintlayout.motion.widget.Key;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.DataSources;
import com.otaliastudios.transcoder.internal.Segment;
import com.otaliastudios.transcoder.internal.Segments;
import com.otaliastudios.transcoder.internal.Timer;
import com.otaliastudios.transcoder.internal.Tracks;
import com.otaliastudios.transcoder.internal.codec.Decoder;
import com.otaliastudios.transcoder.internal.data.Reader;
import com.otaliastudios.transcoder.internal.data.Seeker;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import com.otaliastudios.transcoder.internal.pipeline.PipelineKt;
import com.otaliastudios.transcoder.internal.pipeline.PipelinesKt;
import com.otaliastudios.transcoder.internal.thumbnails.DefaultThumbnailsEngine;
import com.otaliastudios.transcoder.internal.utils.EosKt;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import com.otaliastudios.transcoder.internal.video.VideoRenderer;
import com.otaliastudios.transcoder.internal.video.VideoSnapshots;
import com.otaliastudios.transcoder.resize.Resizer;
import com.otaliastudios.transcoder.source.DataSource;
import com.otaliastudios.transcoder.strategy.DefaultVideoStrategy;
import com.otaliastudios.transcoder.strategy.RemoveTrackStrategy;
import com.otaliastudios.transcoder.thumbnail.Thumbnail;
import com.otaliastudios.transcoder.thumbnail.ThumbnailRequest;
import com.otaliastudios.transcoder.time.DefaultTimeInterpolator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultThumbnailsEngine.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 &2\u00020\u0001:\u0002&'B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\u001b\u001a\u00020\u0014H\u0016J(\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001c\u0010%\u001a\u00020\u00142\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\n0\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/otaliastudios/transcoder/internal/thumbnails/DefaultThumbnailsEngine;", "Lcom/otaliastudios/transcoder/internal/thumbnails/ThumbnailsEngine;", "dataSources", "Lcom/otaliastudios/transcoder/internal/DataSources;", Key.ROTATION, "", "resizer", "Lcom/otaliastudios/transcoder/resize/Resizer;", "requests", "", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "(Lcom/otaliastudios/transcoder/internal/DataSources;ILcom/otaliastudios/transcoder/resize/Resizer;Ljava/util/List;)V", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "positions", "Lkotlin/Pair;", "", "progress", "Lkotlin/Function1;", "Lcom/otaliastudios/transcoder/thumbnail/Thumbnail;", "", "segments", "Lcom/otaliastudios/transcoder/internal/Segments;", WorkflowModule.Properties.Section.Component.Type.TIMER, "Lcom/otaliastudios/transcoder/internal/Timer;", "tracks", "Lcom/otaliastudios/transcoder/internal/Tracks;", "cleanup", "createPipeline", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", FirebaseAnalytics.Param.INDEX, "status", "Lcom/otaliastudios/transcoder/common/TrackStatus;", "outputFormat", "Landroid/media/MediaFormat;", "thumbnails", "Companion", "Stub", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DefaultThumbnailsEngine extends ThumbnailsEngine {
    private final DataSources dataSources;
    private final Logger log;
    private final List<Pair<Long, ThumbnailRequest>> positions;
    private Function1<? super Thumbnail, Unit> progress;
    private final int rotation;
    private final Segments segments;
    private final Timer timer;
    private final Tracks tracks;
    private static final long WAIT_MS = 10;
    private static final long PROGRESS_LOOPS = 10;

    public DefaultThumbnailsEngine(DataSources dataSources, int i, Resizer resizer, List<? extends ThumbnailRequest> requests) {
        Intrinsics.checkNotNullParameter(dataSources, "dataSources");
        Intrinsics.checkNotNullParameter(resizer, "resizer");
        Intrinsics.checkNotNullParameter(requests, "requests");
        this.dataSources = dataSources;
        this.rotation = i;
        Logger logger = new Logger("ThumbnailsEngine");
        this.log = logger;
        Tracks tracks = new Tracks(TrackMapKt.trackMapOf(new DefaultVideoStrategy.Builder().frameRate(120).addResizer(resizer).build(), new RemoveTrackStrategy()), dataSources, i, true);
        this.tracks = tracks;
        Segments segments = new Segments(dataSources, tracks, new DefaultThumbnailsEngine$segments$1(this));
        this.segments = segments;
        this.timer = new Timer(new DefaultTimeInterpolator(), dataSources, tracks, segments.getCurrentIndex());
        logger.i("Created Tracks, Segments, Timer...");
        ArrayList arrayList = new ArrayList();
        for (ThumbnailRequest thumbnailRequest : requests) {
            List<Long> locate = thumbnailRequest.locate(this.timer.getTotalDurationUs());
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(locate, 10));
            Iterator<T> it = locate.iterator();
            while (it.hasNext()) {
                arrayList2.add(TuplesKt.to(Long.valueOf(((Number) it.next()).longValue()), thumbnailRequest));
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        this.positions = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.otaliastudios.transcoder.internal.thumbnails.DefaultThumbnailsEngine$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((Long) ((Pair) t).getFirst(), (Long) ((Pair) t2).getFirst());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultThumbnailsEngine.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/otaliastudios/transcoder/internal/thumbnails/DefaultThumbnailsEngine$Stub;", "", "request", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "positionUs", "", "localizedUs", "(Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;JJ)V", "actualLocalizedUs", "getActualLocalizedUs", "()J", "setActualLocalizedUs", "(J)V", "getLocalizedUs", "getPositionUs", "getRequest", "()Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Stub {
        private long actualLocalizedUs;
        private final long localizedUs;
        private final long positionUs;
        private final ThumbnailRequest request;

        public Stub(ThumbnailRequest request, long j, long j2) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.request = request;
            this.positionUs = j;
            this.localizedUs = j2;
            this.actualLocalizedUs = j2;
        }

        public final ThumbnailRequest getRequest() {
            return this.request;
        }

        public final long getPositionUs() {
            return this.positionUs;
        }

        public final long getLocalizedUs() {
            return this.localizedUs;
        }

        public final long getActualLocalizedUs() {
            return this.actualLocalizedUs;
        }

        public final void setActualLocalizedUs(long j) {
            this.actualLocalizedUs = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pipeline createPipeline(final TrackType type, int index, TrackStatus status, final MediaFormat outputFormat) {
        Stub stub;
        this.log.i("Creating pipeline #" + index + ". absoluteUs=" + CollectionsKt.joinToString$default(this.positions, null, null, null, 0, null, new Function1<Pair<? extends Long, ? extends ThumbnailRequest>, CharSequence>() { // from class: com.otaliastudios.transcoder.internal.thumbnails.DefaultThumbnailsEngine$createPipeline$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final CharSequence invoke2(Pair<Long, ? extends ThumbnailRequest> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return String.valueOf(it.getFirst().longValue());
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Pair<? extends Long, ? extends ThumbnailRequest> pair) {
                return invoke2((Pair<Long, ? extends ThumbnailRequest>) pair);
            }
        }, 31, null));
        List<Pair<Long, ThumbnailRequest>> list = this.positions;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            long longValue = ((Number) pair.component1()).longValue();
            ThumbnailRequest thumbnailRequest = (ThumbnailRequest) pair.component2();
            Long localize = this.timer.localize(type, index, longValue);
            if (localize == null) {
                stub = null;
            } else {
                localize.longValue();
                stub = new Stub(thumbnailRequest, longValue, localize.longValue());
            }
            if (stub != null) {
                arrayList.add(stub);
            }
        }
        final List mutableList = CollectionsKt.toMutableList((Collection) arrayList);
        if (mutableList.isEmpty()) {
            return PipelinesKt.EmptyPipeline();
        }
        final DataSource forcingEos = EosKt.forcingEos(this.dataSources.get(type).get(index), new Function0<Boolean>() { // from class: com.otaliastudios.transcoder.internal.thumbnails.DefaultThumbnailsEngine$createPipeline$source$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(mutableList.isEmpty());
            }
        });
        List list2 = mutableList;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Long.valueOf(((Stub) it2.next()).getLocalizedUs()));
        }
        final ArrayList arrayList3 = arrayList2;
        this.log.i("Requests for step #" + index + ": " + CollectionsKt.joinToString$default(arrayList3, null, null, null, 0, null, null, 63, null) + " [duration=" + forcingEos.getDurationUs() + ']');
        return Pipeline.INSTANCE.build$lib_release("Thumbnails", new Function0<Pipeline.Builder<?, Channel>>() { // from class: com.otaliastudios.transcoder.internal.thumbnails.DefaultThumbnailsEngine$createPipeline$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Pipeline.Builder<?, Channel> invoke() {
                int i;
                DataSource dataSource = DataSource.this;
                List<Long> list3 = arrayList3;
                final List<DefaultThumbnailsEngine.Stub> list4 = mutableList;
                Pipeline.Builder plus = PipelineKt.plus(new Seeker(dataSource, list3, new Function1<Long, Boolean>() { // from class: com.otaliastudios.transcoder.internal.thumbnails.DefaultThumbnailsEngine$createPipeline$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final Boolean invoke(long j) {
                        DefaultThumbnailsEngine.Stub stub2 = (DefaultThumbnailsEngine.Stub) CollectionsKt.firstOrNull((List) list4);
                        boolean z = false;
                        if (stub2 != null && j == stub2.getLocalizedUs()) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Long l) {
                        return invoke(l.longValue());
                    }
                }), new Reader(DataSource.this, type));
                MediaFormat trackFormat = DataSource.this.getTrackFormat(type);
                Intrinsics.checkNotNull(trackFormat);
                Intrinsics.checkNotNullExpressionValue(trackFormat, "source.getTrackFormat(type)!!");
                Pipeline.Builder plus2 = plus.plus(new Decoder(trackFormat, false));
                int orientation = DataSource.this.getOrientation();
                i = this.rotation;
                Pipeline.Builder plus3 = plus2.plus(new VideoRenderer(orientation, i, outputFormat, true));
                MediaFormat mediaFormat = outputFormat;
                List<Long> list5 = arrayList3;
                final List<DefaultThumbnailsEngine.Stub> list6 = mutableList;
                final DefaultThumbnailsEngine defaultThumbnailsEngine = this;
                return plus3.plus(new VideoSnapshots(mediaFormat, list5, 50000L, new Function2<Long, Bitmap, Unit>() { // from class: com.otaliastudios.transcoder.internal.thumbnails.DefaultThumbnailsEngine$createPipeline$2.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Long l, Bitmap bitmap) {
                        invoke(l.longValue(), bitmap);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(long j, Bitmap bitmap) {
                        Logger logger;
                        Function1 function1;
                        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                        DefaultThumbnailsEngine.Stub stub2 = (DefaultThumbnailsEngine.Stub) CollectionsKt.removeFirst(list6);
                        stub2.setActualLocalizedUs(j);
                        logger = defaultThumbnailsEngine.log;
                        logger.i("Got snapshot. positionUs=" + stub2.getPositionUs() + " localizedUs=" + stub2.getLocalizedUs() + " actualLocalizedUs=" + stub2.getActualLocalizedUs() + " deltaUs=" + (stub2.getLocalizedUs() - stub2.getActualLocalizedUs()));
                        Thumbnail thumbnail = new Thumbnail(stub2.getRequest(), stub2.getPositionUs(), bitmap);
                        function1 = defaultThumbnailsEngine.progress;
                        if (function1 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("progress");
                            function1 = null;
                        }
                        function1.invoke(thumbnail);
                    }
                }));
            }
        });
    }

    @Override // com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsEngine
    public void thumbnails(Function1<? super Thumbnail, Unit> progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
        this.progress = progress;
        while (true) {
            Segment next = this.segments.next(TrackType.VIDEO);
            boolean z = false;
            boolean advance = next == null ? false : next.advance();
            if (!advance && !this.segments.hasNext()) {
                z = true;
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            if (z) {
                return;
            }
            if (!advance) {
                Thread.sleep(WAIT_MS);
            }
        }
    }

    @Override // com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsEngine
    public void cleanup() {
        try {
            Result.Companion companion = Result.INSTANCE;
            DefaultThumbnailsEngine defaultThumbnailsEngine = this;
            this.segments.release();
            Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            DefaultThumbnailsEngine defaultThumbnailsEngine2 = this;
            this.dataSources.release();
            Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
    }
}
