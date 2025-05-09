package com.otaliastudios.transcoder.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.otaliastudios.transcoder.ThumbnailerOptions;
import com.otaliastudios.transcoder.TranscoderOptions;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.source.BlankAudioDataSource;
import com.otaliastudios.transcoder.source.DataSource;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataSources.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0007¢\u0006\u0002\u0010\bB#\b\u0002\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\u0010\u000bJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0016\u001a\u00020\u0017J\f\u0010\u0018\u001a\u00020\u0017*\u00020\u0003H\u0002J\u0012\u0010\u0018\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\f\u0010\u0019\u001a\u00020\u0017*\u00020\u0003H\u0002J\u0012\u0010\u0019\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/otaliastudios/transcoder/internal/DataSources;", "Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "", "Lcom/otaliastudios/transcoder/source/DataSource;", "options", "Lcom/otaliastudios/transcoder/TranscoderOptions;", "(Lcom/otaliastudios/transcoder/TranscoderOptions;)V", "Lcom/otaliastudios/transcoder/ThumbnailerOptions;", "(Lcom/otaliastudios/transcoder/ThumbnailerOptions;)V", "videoSources", "audioSources", "(Ljava/util/List;Ljava/util/List;)V", "discarded", "", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", TtmlNode.COMBINE_ALL, WorkflowRequestType.Method.GET, "type", "Lcom/otaliastudios/transcoder/common/TrackType;", "has", "", "release", "", "deinit", Session.JsonKeys.INIT, "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DataSources implements TrackMap<List<? extends DataSource>> {
    private final List<DataSource> audioSources;
    private final List<DataSource> discarded;
    private final Logger log;
    private final List<DataSource> videoSources;

    /* compiled from: DataSources.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TrackType.values().length];
            iArr[TrackType.AUDIO.ordinal()] = 1;
            iArr[TrackType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private DataSources(List<? extends DataSource> list, List<? extends DataSource> list2) {
        int i;
        List list3;
        List list4;
        Logger logger = new Logger("DataSources");
        this.log = logger;
        logger.i("initializing videoSources...");
        init(list);
        logger.i("initializing audioSources...");
        init(list2);
        this.discarded = new ArrayList();
        List<? extends DataSource> list5 = list;
        int i2 = 0;
        if ((list5 instanceof Collection) && list5.isEmpty()) {
            i = 0;
        } else {
            Iterator<T> it = list5.iterator();
            i = 0;
            while (it.hasNext()) {
                if ((((DataSource) it.next()).getTrackFormat(TrackType.VIDEO) != null) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (i == 0) {
            List emptyList = CollectionsKt.emptyList();
            CollectionsKt.addAll(this.discarded, list5);
            list3 = emptyList;
        } else {
            list.size();
            list3 = list;
        }
        this.videoSources = list3;
        List<? extends DataSource> list6 = list2;
        if (!(list6 instanceof Collection) || !list6.isEmpty()) {
            Iterator<T> it2 = list6.iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                if ((((DataSource) it2.next()).getTrackFormat(TrackType.AUDIO) != null) && (i3 = i3 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            i2 = i3;
        }
        this.log.i(Intrinsics.stringPlus("computing audioSources, valid=", Integer.valueOf(i2)));
        if (i2 == 0) {
            List emptyList2 = CollectionsKt.emptyList();
            CollectionsKt.addAll(this.discarded, list6);
            list4 = emptyList2;
        } else {
            int size = list2.size();
            list4 = list2;
            if (i2 != size) {
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
                for (BlankAudioDataSource blankAudioDataSource : list6) {
                    if (blankAudioDataSource.getTrackFormat(TrackType.AUDIO) == null) {
                        BlankAudioDataSource blankAudioDataSource2 = new BlankAudioDataSource(blankAudioDataSource.getDurationUs());
                        this.discarded.add(blankAudioDataSource);
                        blankAudioDataSource = blankAudioDataSource2;
                    }
                    arrayList.add(blankAudioDataSource);
                }
                list4 = arrayList;
            }
        }
        this.audioSources = list4;
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public List<? extends DataSource> audioOrNull() {
        return (List) TrackMap.DefaultImpls.audioOrNull(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public List<? extends DataSource> getAudio() {
        return (List) TrackMap.DefaultImpls.getAudio(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public boolean getHasAudio() {
        return TrackMap.DefaultImpls.getHasAudio(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public boolean getHasVideo() {
        return TrackMap.DefaultImpls.getHasVideo(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public List<? extends DataSource> getOrNull(TrackType trackType) {
        return (List) TrackMap.DefaultImpls.getOrNull(this, trackType);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public int getSize() {
        return TrackMap.DefaultImpls.getSize(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public List<? extends DataSource> getVideo() {
        return (List) TrackMap.DefaultImpls.getVideo(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
    public Iterator<List<DataSource>> iterator() {
        return TrackMap.DefaultImpls.iterator(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public List<? extends DataSource> videoOrNull() {
        return (List) TrackMap.DefaultImpls.videoOrNull(this);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DataSources(TranscoderOptions options) {
        this(r0, r3);
        Intrinsics.checkNotNullParameter(options, "options");
        List<DataSource> videoDataSources = options.getVideoDataSources();
        Intrinsics.checkNotNullExpressionValue(videoDataSources, "options.videoDataSources");
        List<DataSource> audioDataSources = options.getAudioDataSources();
        Intrinsics.checkNotNullExpressionValue(audioDataSources, "options.audioDataSources");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DataSources(ThumbnailerOptions options) {
        this(options.getDataSources(), CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(options, "options");
    }

    private final void init(DataSource dataSource) {
        if (dataSource.isInitialized()) {
            return;
        }
        dataSource.initialize();
    }

    private final void deinit(DataSource dataSource) {
        if (dataSource.isInitialized()) {
            dataSource.deinitialize();
        }
    }

    private final void init(List<? extends DataSource> list) {
        for (DataSource dataSource : list) {
            this.log.i("initializing " + dataSource + "... (isInit=" + dataSource.isInitialized() + ')');
            init(dataSource);
        }
    }

    private final void deinit(List<? extends DataSource> list) {
        for (DataSource dataSource : list) {
            this.log.i("deinitializing " + dataSource + "... (isInit=" + dataSource.isInitialized() + ')');
            deinit(dataSource);
        }
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public List<? extends DataSource> get(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            return this.audioSources;
        }
        if (i == 2) {
            return this.videoSources;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public boolean has(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return !get(type).isEmpty();
    }

    public final List<DataSource> all() {
        return CollectionsKt.distinct(CollectionsKt.plus((Collection) getAudio(), (Iterable) getVideo()));
    }

    public final void release() {
        this.log.i("release(): releasing...");
        deinit(getVideo());
        deinit(getAudio());
        deinit(this.discarded);
        this.log.i("release(): released.");
    }
}
