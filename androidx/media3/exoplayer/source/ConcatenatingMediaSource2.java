package androidx.media3.exoplayer.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class ConcatenatingMediaSource2 extends CompositeMediaSource<Integer> {
    private static final int MSG_UPDATE_TIMELINE = 1;
    private MediaItem mediaItem;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final ImmutableList<MediaSourceHolder> mediaSourceHolders;
    private Handler playbackThreadHandler;
    private boolean timelineUpdateScheduled;

    private static long getChildWindowSequenceNumber(long j, int i, int i2) {
        return (j * i) + i2;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return true;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    protected void enableInternal() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public int getWindowIndexForChildWindowIndex(Integer num, int i) {
        return 0;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private int index;
        private MediaItem mediaItem;
        private MediaSource.Factory mediaSourceFactory;
        private final ImmutableList.Builder<MediaSourceHolder> mediaSourceHoldersBuilder = ImmutableList.builder();

        public Builder useDefaultMediaSourceFactory(Context context) {
            return setMediaSourceFactory(new DefaultMediaSourceFactory(context));
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            this.mediaSourceFactory = (MediaSource.Factory) Assertions.checkNotNull(factory);
            return this;
        }

        public Builder setMediaItem(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
            return this;
        }

        public Builder add(MediaItem mediaItem) {
            return add(mediaItem, C.TIME_UNSET);
        }

        public Builder add(MediaItem mediaItem, long j) {
            Assertions.checkNotNull(mediaItem);
            if (j == C.TIME_UNSET && mediaItem.clippingConfiguration.endPositionMs != Long.MIN_VALUE) {
                j = Util.usToMs(mediaItem.clippingConfiguration.endPositionUs - mediaItem.clippingConfiguration.startPositionUs);
            }
            Assertions.checkStateNotNull(this.mediaSourceFactory, "Must use useDefaultMediaSourceFactory or setMediaSourceFactory first.");
            return add(this.mediaSourceFactory.createMediaSource(mediaItem), j);
        }

        public Builder add(MediaSource mediaSource) {
            return add(mediaSource, C.TIME_UNSET);
        }

        public Builder add(MediaSource mediaSource, long j) {
            Assertions.checkNotNull(mediaSource);
            Assertions.checkState(((mediaSource instanceof ProgressiveMediaSource) && j == C.TIME_UNSET) ? false : true, "Progressive media source must define an initial placeholder duration.");
            ImmutableList.Builder<MediaSourceHolder> builder = this.mediaSourceHoldersBuilder;
            int i = this.index;
            this.index = i + 1;
            builder.add((ImmutableList.Builder<MediaSourceHolder>) new MediaSourceHolder(mediaSource, i, Util.msToUs(j)));
            return this;
        }

        public ConcatenatingMediaSource2 build() {
            Assertions.checkArgument(this.index > 0, "Must add at least one source to the concatenation.");
            if (this.mediaItem == null) {
                this.mediaItem = MediaItem.fromUri(Uri.EMPTY);
            }
            return new ConcatenatingMediaSource2(this.mediaItem, this.mediaSourceHoldersBuilder.build());
        }
    }

    private ConcatenatingMediaSource2(MediaItem mediaItem, ImmutableList<MediaSourceHolder> immutableList) {
        this.mediaItem = mediaItem;
        this.mediaSourceHolders = immutableList;
        this.mediaSourceByMediaPeriod = new IdentityHashMap<>();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public Timeline getInitialTimeline() {
        return maybeCreateConcatenatedTimeline();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new Handler.Callback() { // from class: androidx.media3.exoplayer.source.ConcatenatingMediaSource2$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean handleMessage;
                handleMessage = ConcatenatingMediaSource2.this.handleMessage(message);
                return handleMessage;
            }
        });
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            prepareChildSource(Integer.valueOf(i), this.mediaSourceHolders.get(i).mediaSource);
        }
        scheduleTimelineUpdate();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(getChildIndex(mediaPeriodId.periodUid));
        MediaSource.MediaPeriodId copyWithWindowSequenceNumber = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size(), mediaSourceHolder.index));
        enableChildSource(Integer.valueOf(mediaSourceHolder.index));
        mediaSourceHolder.activeMediaPeriods++;
        long longValue = mediaPeriodId.isAd() ? 0L : ((Long) Assertions.checkNotNull(mediaSourceHolder.periodTimeOffsetsByUid.get(copyWithWindowSequenceNumber.periodUid))).longValue();
        TimeOffsetMediaPeriod timeOffsetMediaPeriod = new TimeOffsetMediaPeriod(mediaSourceHolder.mediaSource.createPeriod(copyWithWindowSequenceNumber, allocator, j - longValue), longValue);
        this.mediaSourceByMediaPeriod.put(timeOffsetMediaPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return timeOffsetMediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod))).mediaSource.releasePeriod(((TimeOffsetMediaPeriod) mediaPeriod).getWrappedMediaPeriod());
        r0.activeMediaPeriods--;
        if (this.mediaSourceByMediaPeriod.isEmpty()) {
            return;
        }
        disableUnusedMediaSources();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Handler handler = this.playbackThreadHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.playbackThreadHandler = null;
        }
        this.timelineUpdateScheduled = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    /* renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void m324x28f9175(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        if (num.intValue() != getChildIndexFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size())) {
            return null;
        }
        return mediaPeriodId.copyWithPeriodUid(getPeriodUid(num.intValue(), mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getWindowSequenceNumberFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public long getMediaTimeForChildMediaTime(Integer num, long j, MediaSource.MediaPeriodId mediaPeriodId) {
        Long l;
        return (j == C.TIME_UNSET || mediaPeriodId == null || mediaPeriodId.isAd() || (l = this.mediaSourceHolders.get(num.intValue()).periodTimeOffsetsByUid.get(mediaPeriodId.periodUid)) == null) ? j : j + Util.usToMs(l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            updateTimeline();
        }
        return true;
    }

    private void scheduleTimelineUpdate() {
        if (this.timelineUpdateScheduled) {
            return;
        }
        ((Handler) Assertions.checkNotNull(this.playbackThreadHandler)).obtainMessage(1).sendToTarget();
        this.timelineUpdateScheduled = true;
    }

    private void updateTimeline() {
        this.timelineUpdateScheduled = false;
        ConcatenatedTimeline maybeCreateConcatenatedTimeline = maybeCreateConcatenatedTimeline();
        if (maybeCreateConcatenatedTimeline != null) {
            refreshSourceInfo(maybeCreateConcatenatedTimeline);
        }
    }

    private void disableUnusedMediaSources() {
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i);
            if (mediaSourceHolder.activeMediaPeriods == 0) {
                disableChildSource(Integer.valueOf(mediaSourceHolder.index));
            }
        }
    }

    private ConcatenatedTimeline maybeCreateConcatenatedTimeline() {
        MediaSourceHolder mediaSourceHolder;
        boolean z;
        boolean z2;
        Object obj;
        int i;
        long j;
        long j2;
        Timeline.Window window;
        boolean z3;
        ConcatenatingMediaSource2 concatenatingMediaSource2 = this;
        Timeline.Window window2 = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        ImmutableList.Builder builder3 = ImmutableList.builder();
        int size = concatenatingMediaSource2.mediaSourceHolders.size();
        boolean z4 = true;
        boolean z5 = true;
        int i2 = 0;
        Object obj2 = null;
        int i3 = 0;
        boolean z6 = false;
        boolean z7 = false;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        while (i2 < size) {
            MediaSourceHolder mediaSourceHolder2 = concatenatingMediaSource2.mediaSourceHolders.get(i2);
            Timeline timeline = mediaSourceHolder2.mediaSource.getTimeline();
            Assertions.checkArgument(!timeline.isEmpty(), "Can't concatenate empty child Timeline.");
            builder.add((ImmutableList.Builder) timeline);
            builder2.add((ImmutableList.Builder) Integer.valueOf(i3));
            i3 += timeline.getPeriodCount();
            int i4 = 0;
            while (i4 < timeline.getWindowCount()) {
                timeline.getWindow(i4, window2);
                if (!z6) {
                    obj2 = window2.manifest;
                    z6 = true;
                }
                if (z4 && Util.areEqual(obj2, window2.manifest)) {
                    mediaSourceHolder = mediaSourceHolder2;
                    z = true;
                } else {
                    mediaSourceHolder = mediaSourceHolder2;
                    z = false;
                }
                long j6 = window2.durationUs;
                MediaSourceHolder mediaSourceHolder3 = mediaSourceHolder;
                if (j6 == C.TIME_UNSET) {
                    if (mediaSourceHolder3.initialPlaceholderDurationUs == C.TIME_UNSET) {
                        return null;
                    }
                    j6 = mediaSourceHolder3.initialPlaceholderDurationUs;
                }
                j3 += j6;
                if (mediaSourceHolder3.index == 0 && i4 == 0) {
                    z2 = z;
                    obj = obj2;
                    j4 = window2.defaultPositionUs;
                    j5 = -window2.positionInFirstPeriodUs;
                } else {
                    z2 = z;
                    obj = obj2;
                }
                z5 &= window2.isSeekable || window2.isPlaceholder;
                z7 |= window2.isDynamic;
                int i5 = window2.firstPeriodIndex;
                while (i5 <= window2.lastPeriodIndex) {
                    builder3.add((ImmutableList.Builder) Long.valueOf(j5));
                    timeline.getPeriod(i5, period, true);
                    int i6 = i3;
                    long j7 = period.durationUs;
                    if (j7 == C.TIME_UNSET) {
                        Assertions.checkArgument(window2.firstPeriodIndex == window2.lastPeriodIndex, "Can't apply placeholder duration to multiple periods with unknown duration in a single window.");
                        j7 = window2.positionInFirstPeriodUs + j6;
                    }
                    Object obj3 = obj;
                    if (!(i5 == window2.firstPeriodIndex && !(mediaSourceHolder3.index == 0 && i4 == 0)) || j7 == C.TIME_UNSET) {
                        i = size;
                        j = j6;
                        j2 = 0;
                    } else {
                        i = size;
                        j = j6;
                        j2 = -window2.positionInFirstPeriodUs;
                        j7 += j2;
                    }
                    Object checkNotNull = Assertions.checkNotNull(period.uid);
                    int i7 = i;
                    if (mediaSourceHolder3.activeMediaPeriods == 0 || !mediaSourceHolder3.periodTimeOffsetsByUid.containsKey(checkNotNull)) {
                        window = window2;
                    } else {
                        window = window2;
                        if (!mediaSourceHolder3.periodTimeOffsetsByUid.get(checkNotNull).equals(Long.valueOf(j2))) {
                            z3 = false;
                            Assertions.checkArgument(z3, "Can't handle windows with changing offset in first period.");
                            mediaSourceHolder3.periodTimeOffsetsByUid.put(checkNotNull, Long.valueOf(j2));
                            j5 += j7;
                            i5++;
                            i3 = i6;
                            obj = obj3;
                            j6 = j;
                            size = i7;
                            window2 = window;
                        }
                    }
                    z3 = true;
                    Assertions.checkArgument(z3, "Can't handle windows with changing offset in first period.");
                    mediaSourceHolder3.periodTimeOffsetsByUid.put(checkNotNull, Long.valueOf(j2));
                    j5 += j7;
                    i5++;
                    i3 = i6;
                    obj = obj3;
                    j6 = j;
                    size = i7;
                    window2 = window;
                }
                i4++;
                mediaSourceHolder2 = mediaSourceHolder3;
                z4 = z2;
                obj2 = obj;
            }
            i2++;
            concatenatingMediaSource2 = this;
        }
        return new ConcatenatedTimeline(getMediaItem(), builder.build(), builder2.build(), builder3.build(), z5, z7, j3, j4, z4 ? obj2 : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object getPeriodUid(int i, Object obj) {
        return Pair.create(Integer.valueOf(i), obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getChildIndex(Object obj) {
        return ((Integer) ((Pair) obj).first).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object getChildPeriodUid(Object obj) {
        return ((Pair) obj).second;
    }

    private static int getChildIndexFromChildWindowSequenceNumber(long j, int i) {
        return (int) (j % i);
    }

    private static long getWindowSequenceNumberFromChildWindowSequenceNumber(long j, int i) {
        return j / i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class MediaSourceHolder {
        public int activeMediaPeriods;
        public final int index;
        public final long initialPlaceholderDurationUs;
        public final MaskingMediaSource mediaSource;
        public final HashMap<Object, Long> periodTimeOffsetsByUid = new HashMap<>();

        public MediaSourceHolder(MediaSource mediaSource, int i, long j) {
            this.mediaSource = new MaskingMediaSource(mediaSource, false);
            this.index = i;
            this.initialPlaceholderDurationUs = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ConcatenatedTimeline extends Timeline {
        private final long defaultPositionUs;
        private final long durationUs;
        private final ImmutableList<Integer> firstPeriodIndices;
        private final boolean isDynamic;
        private final boolean isSeekable;
        private final Object manifest;
        private final MediaItem mediaItem;
        private final ImmutableList<Long> periodOffsetsInWindowUs;
        private final ImmutableList<Timeline> timelines;

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }

        public ConcatenatedTimeline(MediaItem mediaItem, ImmutableList<Timeline> immutableList, ImmutableList<Integer> immutableList2, ImmutableList<Long> immutableList3, boolean z, boolean z2, long j, long j2, Object obj) {
            this.mediaItem = mediaItem;
            this.timelines = immutableList;
            this.firstPeriodIndices = immutableList2;
            this.periodOffsetsInWindowUs = immutableList3;
            this.isSeekable = z;
            this.isDynamic = z2;
            this.durationUs = j;
            this.defaultPositionUs = j2;
            this.manifest = obj;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.periodOffsetsInWindowUs.size();
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            return window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, this.manifest, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, this.isSeekable, this.isDynamic, null, this.defaultPositionUs, this.durationUs, 0, getPeriodCount() - 1, -this.periodOffsetsInWindowUs.get(0).longValue());
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            int childIndex = ConcatenatingMediaSource2.getChildIndex(obj);
            Object childPeriodUid = ConcatenatingMediaSource2.getChildPeriodUid(obj);
            Timeline timeline = this.timelines.get(childIndex);
            int intValue = this.firstPeriodIndices.get(childIndex).intValue() + timeline.getIndexOfPeriod(childPeriodUid);
            timeline.getPeriodByUid(childPeriodUid, period);
            period.windowIndex = 0;
            period.positionInWindowUs = this.periodOffsetsInWindowUs.get(intValue).longValue();
            period.durationUs = getPeriodDurationUs(period, intValue);
            period.uid = obj;
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            this.timelines.get(childIndexByPeriodIndex).getPeriod(i - this.firstPeriodIndices.get(childIndexByPeriodIndex).intValue(), period, z);
            period.windowIndex = 0;
            period.positionInWindowUs = this.periodOffsetsInWindowUs.get(i).longValue();
            period.durationUs = getPeriodDurationUs(period, i);
            if (z) {
                period.uid = ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, Assertions.checkNotNull(period.uid));
            }
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            if (!(obj instanceof Pair) || !(((Pair) obj).first instanceof Integer)) {
                return -1;
            }
            int childIndex = ConcatenatingMediaSource2.getChildIndex(obj);
            int indexOfPeriod = this.timelines.get(childIndex).getIndexOfPeriod(ConcatenatingMediaSource2.getChildPeriodUid(obj));
            if (indexOfPeriod == -1) {
                return -1;
            }
            return this.firstPeriodIndices.get(childIndex).intValue() + indexOfPeriod;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            return ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, this.timelines.get(childIndexByPeriodIndex).getUidOfPeriod(i - this.firstPeriodIndices.get(childIndexByPeriodIndex).intValue()));
        }

        private int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor((List<? extends Comparable<? super Integer>>) this.firstPeriodIndices, Integer.valueOf(i + 1), false, false);
        }

        private long getPeriodDurationUs(Timeline.Period period, int i) {
            long longValue;
            if (period.durationUs == C.TIME_UNSET) {
                return C.TIME_UNSET;
            }
            long longValue2 = this.periodOffsetsInWindowUs.get(i).longValue();
            if (i == this.periodOffsetsInWindowUs.size() - 1) {
                longValue = this.durationUs;
            } else {
                longValue = this.periodOffsetsInWindowUs.get(i + 1).longValue();
            }
            return longValue - longValue2;
        }
    }
}
