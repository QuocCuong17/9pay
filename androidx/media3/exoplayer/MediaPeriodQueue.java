package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.MediaPeriodHolder;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MediaPeriodQueue {
    public static final long INITIAL_RENDERER_POSITION_OFFSET_US = 1000000000000L;
    private static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    private final AnalyticsCollector analyticsCollector;
    private final HandlerWrapper analyticsCollectorHandler;
    private int length;
    private MediaPeriodHolder loading;
    private final MediaPeriodHolder.Factory mediaPeriodHolderFactory;
    private long nextWindowSequenceNumber;
    private Object oldFrontPeriodUid;
    private long oldFrontPeriodWindowSequenceNumber;
    private MediaPeriodHolder playing;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private final Timeline.Period period = new Timeline.Period();
    private final Timeline.Window window = new Timeline.Window();
    private List<MediaPeriodHolder> preloadPriorityList = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean areDurationsCompatible(long j, long j2) {
        return j == C.TIME_UNSET || j == j2;
    }

    public MediaPeriodQueue(AnalyticsCollector analyticsCollector, HandlerWrapper handlerWrapper, MediaPeriodHolder.Factory factory, ExoPlayer.PreloadConfiguration preloadConfiguration) {
        this.analyticsCollector = analyticsCollector;
        this.analyticsCollectorHandler = handlerWrapper;
        this.mediaPeriodHolderFactory = factory;
        this.preloadConfiguration = preloadConfiguration;
    }

    public boolean updateRepeatMode(Timeline timeline, int i) {
        this.repeatMode = i;
        return updateForPlaybackModeChange(timeline);
    }

    public boolean updateShuffleModeEnabled(Timeline timeline, boolean z) {
        this.shuffleModeEnabled = z;
        return updateForPlaybackModeChange(timeline);
    }

    public void updatePreloadConfiguration(Timeline timeline, ExoPlayer.PreloadConfiguration preloadConfiguration) {
        this.preloadConfiguration = preloadConfiguration;
        invalidatePreloadPool(timeline);
    }

    public boolean isLoading(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder != null && mediaPeriodHolder.mediaPeriod == mediaPeriod;
    }

    public void reevaluateBuffer(long j) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.reevaluateBuffer(j);
        }
    }

    public boolean shouldLoadNextMediaPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder == null || (!mediaPeriodHolder.info.isFinal && this.loading.isFullyBuffered() && this.loading.info.durationUs != C.TIME_UNSET && this.length < 100);
    }

    public MediaPeriodInfo getNextMediaPeriodInfo(long j, PlaybackInfo playbackInfo) {
        if (this.loading == null) {
            return getFirstMediaPeriodInfo(playbackInfo);
        }
        return getFollowingMediaPeriodInfo(playbackInfo.timeline, this.loading, j);
    }

    public MediaPeriodHolder enqueueNextMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        long rendererOffset = mediaPeriodHolder == null ? INITIAL_RENDERER_POSITION_OFFSET_US : (mediaPeriodHolder.getRendererOffset() + this.loading.info.durationUs) - mediaPeriodInfo.startPositionUs;
        MediaPeriodHolder removePreloadedMediaPeriodHolder = removePreloadedMediaPeriodHolder(mediaPeriodInfo);
        if (removePreloadedMediaPeriodHolder == null) {
            removePreloadedMediaPeriodHolder = this.mediaPeriodHolderFactory.create(mediaPeriodInfo, rendererOffset);
        } else {
            removePreloadedMediaPeriodHolder.info = mediaPeriodInfo;
            removePreloadedMediaPeriodHolder.setRendererOffset(rendererOffset);
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.loading;
        if (mediaPeriodHolder2 != null) {
            mediaPeriodHolder2.setNext(removePreloadedMediaPeriodHolder);
        } else {
            this.playing = removePreloadedMediaPeriodHolder;
            this.reading = removePreloadedMediaPeriodHolder;
        }
        this.oldFrontPeriodUid = null;
        this.loading = removePreloadedMediaPeriodHolder;
        this.length++;
        notifyQueueUpdate();
        return removePreloadedMediaPeriodHolder;
    }

    public void invalidatePreloadPool(Timeline timeline) {
        MediaPeriodHolder mediaPeriodHolder;
        if (this.preloadConfiguration.targetPreloadDurationUs == C.TIME_UNSET || (mediaPeriodHolder = this.loading) == null) {
            releasePreloadPool();
            return;
        }
        ArrayList arrayList = new ArrayList();
        Pair<Object, Long> defaultPeriodPositionOfNextWindow = getDefaultPeriodPositionOfNextWindow(timeline, mediaPeriodHolder.info.f24id.periodUid, 0L);
        if (defaultPeriodPositionOfNextWindow != null && !timeline.getWindow(timeline.getPeriodByUid(defaultPeriodPositionOfNextWindow.first, this.period).windowIndex, this.window).isLive()) {
            long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(defaultPeriodPositionOfNextWindow.first);
            if (resolvePeriodUidToWindowSequenceNumberInPreloadPeriods == -1) {
                resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = this.nextWindowSequenceNumber;
                this.nextWindowSequenceNumber = 1 + resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
            }
            MediaPeriodInfo mediaPeriodInfoForPeriodPosition = getMediaPeriodInfoForPeriodPosition(timeline, defaultPeriodPositionOfNextWindow.first, ((Long) defaultPeriodPositionOfNextWindow.second).longValue(), resolvePeriodUidToWindowSequenceNumberInPreloadPeriods);
            MediaPeriodHolder removePreloadedMediaPeriodHolder = removePreloadedMediaPeriodHolder(mediaPeriodInfoForPeriodPosition);
            if (removePreloadedMediaPeriodHolder == null) {
                removePreloadedMediaPeriodHolder = this.mediaPeriodHolderFactory.create(mediaPeriodInfoForPeriodPosition, (mediaPeriodHolder.getRendererOffset() + mediaPeriodHolder.info.durationUs) - mediaPeriodInfoForPeriodPosition.startPositionUs);
            }
            arrayList.add(removePreloadedMediaPeriodHolder);
        }
        releaseAndResetPreloadPriorityList(arrayList);
    }

    public void releasePreloadPool() {
        if (this.preloadPriorityList.isEmpty()) {
            return;
        }
        releaseAndResetPreloadPriorityList(new ArrayList());
    }

    private MediaPeriodHolder removePreloadedMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo) {
        for (int i = 0; i < this.preloadPriorityList.size(); i++) {
            if (this.preloadPriorityList.get(i).canBeUsedForMediaPeriodInfo(mediaPeriodInfo)) {
                return this.preloadPriorityList.remove(i);
            }
        }
        return null;
    }

    private void releaseAndResetPreloadPriorityList(List<MediaPeriodHolder> list) {
        for (int i = 0; i < this.preloadPriorityList.size(); i++) {
            this.preloadPriorityList.get(i).release();
        }
        this.preloadPriorityList = list;
    }

    private MediaPeriodInfo getMediaPeriodInfoForPeriodPosition(Timeline timeline, Object obj, long j, long j2) {
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAds = resolveMediaPeriodIdForAds(timeline, obj, j, j2, this.window, this.period);
        if (resolveMediaPeriodIdForAds.isAd()) {
            return getMediaPeriodInfoForAd(timeline, resolveMediaPeriodIdForAds.periodUid, resolveMediaPeriodIdForAds.adGroupIndex, resolveMediaPeriodIdForAds.adIndexInAdGroup, j, resolveMediaPeriodIdForAds.windowSequenceNumber);
        }
        return getMediaPeriodInfoForContent(timeline, resolveMediaPeriodIdForAds.periodUid, j, C.TIME_UNSET, resolveMediaPeriodIdForAds.windowSequenceNumber);
    }

    private Pair<Object, Long> getDefaultPeriodPositionOfNextWindow(Timeline timeline, Object obj, long j) {
        int nextWindowIndex = timeline.getNextWindowIndex(timeline.getPeriodByUid(obj, this.period).windowIndex, this.repeatMode, this.shuffleModeEnabled);
        if (nextWindowIndex != -1) {
            return timeline.getPeriodPositionUs(this.window, this.period, nextWindowIndex, C.TIME_UNSET, j);
        }
        return null;
    }

    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        this.reading = ((MediaPeriodHolder) Assertions.checkStateNotNull(this.reading)).getNext();
        notifyQueueUpdate();
        return (MediaPeriodHolder) Assertions.checkStateNotNull(this.reading);
    }

    public MediaPeriodHolder advancePlayingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder == null) {
            return null;
        }
        if (mediaPeriodHolder == this.reading) {
            this.reading = mediaPeriodHolder.getNext();
        }
        this.playing.release();
        int i = this.length - 1;
        this.length = i;
        if (i == 0) {
            this.loading = null;
            this.oldFrontPeriodUid = this.playing.uid;
            this.oldFrontPeriodWindowSequenceNumber = this.playing.info.f24id.windowSequenceNumber;
        }
        this.playing = this.playing.getNext();
        notifyQueueUpdate();
        return this.playing;
    }

    public boolean removeAfter(MediaPeriodHolder mediaPeriodHolder) {
        Assertions.checkStateNotNull(mediaPeriodHolder);
        boolean z = false;
        if (mediaPeriodHolder.equals(this.loading)) {
            return false;
        }
        this.loading = mediaPeriodHolder;
        while (mediaPeriodHolder.getNext() != null) {
            mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(mediaPeriodHolder.getNext());
            if (mediaPeriodHolder == this.reading) {
                this.reading = this.playing;
                z = true;
            }
            mediaPeriodHolder.release();
            this.length--;
        }
        ((MediaPeriodHolder) Assertions.checkNotNull(this.loading)).setNext(null);
        notifyQueueUpdate();
        return z;
    }

    public void clear() {
        if (this.length == 0) {
            return;
        }
        MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkStateNotNull(this.playing);
        this.oldFrontPeriodUid = mediaPeriodHolder.uid;
        this.oldFrontPeriodWindowSequenceNumber = mediaPeriodHolder.info.f24id.windowSequenceNumber;
        while (mediaPeriodHolder != null) {
            mediaPeriodHolder.release();
            mediaPeriodHolder = mediaPeriodHolder.getNext();
        }
        this.playing = null;
        this.loading = null;
        this.reading = null;
        this.length = 0;
        notifyQueueUpdate();
    }

    public boolean updateQueuedPeriods(Timeline timeline, long j, long j2) {
        MediaPeriodInfo mediaPeriodInfo;
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        MediaPeriodHolder mediaPeriodHolder2 = null;
        while (mediaPeriodHolder != null) {
            MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodHolder.info;
            if (mediaPeriodHolder2 == null) {
                mediaPeriodInfo = getUpdatedMediaPeriodInfo(timeline, mediaPeriodInfo2);
            } else {
                MediaPeriodInfo followingMediaPeriodInfo = getFollowingMediaPeriodInfo(timeline, mediaPeriodHolder2, j);
                if (followingMediaPeriodInfo == null) {
                    return !removeAfter(mediaPeriodHolder2);
                }
                if (!canKeepMediaPeriodHolder(mediaPeriodInfo2, followingMediaPeriodInfo)) {
                    return !removeAfter(mediaPeriodHolder2);
                }
                mediaPeriodInfo = followingMediaPeriodInfo;
            }
            mediaPeriodHolder.info = mediaPeriodInfo.copyWithRequestedContentPositionUs(mediaPeriodInfo2.requestedContentPositionUs);
            if (!areDurationsCompatible(mediaPeriodInfo2.durationUs, mediaPeriodInfo.durationUs)) {
                mediaPeriodHolder.updateClipping();
                return (removeAfter(mediaPeriodHolder) || (mediaPeriodHolder == this.reading && !mediaPeriodHolder.info.isFollowedByTransitionToSameStream && ((j2 > Long.MIN_VALUE ? 1 : (j2 == Long.MIN_VALUE ? 0 : -1)) == 0 || (j2 > ((mediaPeriodInfo.durationUs > C.TIME_UNSET ? 1 : (mediaPeriodInfo.durationUs == C.TIME_UNSET ? 0 : -1)) == 0 ? Long.MAX_VALUE : mediaPeriodHolder.toRendererTime(mediaPeriodInfo.durationUs)) ? 1 : (j2 == ((mediaPeriodInfo.durationUs > C.TIME_UNSET ? 1 : (mediaPeriodInfo.durationUs == C.TIME_UNSET ? 0 : -1)) == 0 ? Long.MAX_VALUE : mediaPeriodHolder.toRendererTime(mediaPeriodInfo.durationUs)) ? 0 : -1)) >= 0))) ? false : true;
            }
            mediaPeriodHolder2 = mediaPeriodHolder;
            mediaPeriodHolder = mediaPeriodHolder.getNext();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public MediaPeriodInfo getUpdatedMediaPeriodInfo(Timeline timeline, MediaPeriodInfo mediaPeriodInfo) {
        long durationUs;
        long j;
        boolean z;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.f24id;
        boolean isLastInPeriod = isLastInPeriod(mediaPeriodId);
        boolean isLastInWindow = isLastInWindow(timeline, mediaPeriodId);
        boolean isLastInTimeline = isLastInTimeline(timeline, mediaPeriodId, isLastInPeriod);
        timeline.getPeriodByUid(mediaPeriodInfo.f24id.periodUid, this.period);
        long adGroupTimeUs = (mediaPeriodId.isAd() || mediaPeriodId.nextAdGroupIndex == -1) ? -9223372036854775807L : this.period.getAdGroupTimeUs(mediaPeriodId.nextAdGroupIndex);
        if (mediaPeriodId.isAd()) {
            durationUs = this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        } else if (adGroupTimeUs == C.TIME_UNSET || adGroupTimeUs == Long.MIN_VALUE) {
            durationUs = this.period.getDurationUs();
        } else {
            j = adGroupTimeUs;
            if (!mediaPeriodId.isAd()) {
                z = this.period.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex);
            } else {
                z = mediaPeriodId.nextAdGroupIndex != -1 && this.period.isServerSideInsertedAdGroup(mediaPeriodId.nextAdGroupIndex);
            }
            return new MediaPeriodInfo(mediaPeriodId, mediaPeriodInfo.startPositionUs, mediaPeriodInfo.requestedContentPositionUs, adGroupTimeUs, j, z, isLastInPeriod, isLastInWindow, isLastInTimeline);
        }
        j = durationUs;
        if (!mediaPeriodId.isAd()) {
        }
        return new MediaPeriodInfo(mediaPeriodId, mediaPeriodInfo.startPositionUs, mediaPeriodInfo.requestedContentPositionUs, adGroupTimeUs, j, z, isLastInPeriod, isLastInWindow, isLastInTimeline);
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline timeline, Object obj, long j) {
        return resolveMediaPeriodIdForAds(timeline, obj, j, resolvePeriodUidToWindowSequenceNumber(timeline, obj), this.window, this.period);
    }

    private static MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline timeline, Object obj, long j, long j2, Timeline.Window window, Timeline.Period period) {
        timeline.getPeriodByUid(obj, period);
        timeline.getWindow(period.windowIndex, window);
        Object obj2 = obj;
        for (int indexOfPeriod = timeline.getIndexOfPeriod(obj); isSkippableAdPeriod(period) && indexOfPeriod <= window.lastPeriodIndex; indexOfPeriod++) {
            timeline.getPeriod(indexOfPeriod, period, true);
            obj2 = Assertions.checkNotNull(period.uid);
        }
        timeline.getPeriodByUid(obj2, period);
        int adGroupIndexForPositionUs = period.getAdGroupIndexForPositionUs(j);
        if (adGroupIndexForPositionUs == -1) {
            return new MediaSource.MediaPeriodId(obj2, j2, period.getAdGroupIndexAfterPositionUs(j));
        }
        return new MediaSource.MediaPeriodId(obj2, adGroupIndexForPositionUs, period.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j2);
    }

    private static boolean isSkippableAdPeriod(Timeline.Period period) {
        int adGroupCount = period.getAdGroupCount();
        if (adGroupCount == 0) {
            return false;
        }
        if ((adGroupCount == 1 && period.isLivePostrollPlaceholder(0)) || !period.isServerSideInsertedAdGroup(period.getRemovedAdGroupCount())) {
            return false;
        }
        long j = 0;
        if (period.getAdGroupIndexForPositionUs(0L) != -1) {
            return false;
        }
        if (period.durationUs == 0) {
            return true;
        }
        int i = adGroupCount - (period.isLivePostrollPlaceholder(adGroupCount + (-1)) ? 2 : 1);
        for (int i2 = 0; i2 <= i; i2++) {
            j += period.getContentResumeOffsetUs(i2);
        }
        return period.durationUs <= j;
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange(Timeline timeline, Object obj, long j) {
        long resolvePeriodUidToWindowSequenceNumber = resolvePeriodUidToWindowSequenceNumber(timeline, obj);
        timeline.getPeriodByUid(obj, this.period);
        timeline.getWindow(this.period.windowIndex, this.window);
        boolean z = false;
        for (int indexOfPeriod = timeline.getIndexOfPeriod(obj); indexOfPeriod >= this.window.firstPeriodIndex; indexOfPeriod--) {
            timeline.getPeriod(indexOfPeriod, this.period, true);
            boolean z2 = this.period.getAdGroupCount() > 0;
            z |= z2;
            Timeline.Period period = this.period;
            if (period.getAdGroupIndexForPositionUs(period.durationUs) != -1) {
                obj = Assertions.checkNotNull(this.period.uid);
            }
            if (z && (!z2 || this.period.durationUs != 0)) {
                break;
            }
        }
        return resolveMediaPeriodIdForAds(timeline, obj, j, resolvePeriodUidToWindowSequenceNumber, this.window, this.period);
    }

    private void notifyQueueUpdate() {
        final ImmutableList.Builder builder = ImmutableList.builder();
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.getNext()) {
            builder.add((ImmutableList.Builder) mediaPeriodHolder.info.f24id);
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.reading;
        final MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodHolder2 == null ? null : mediaPeriodHolder2.info.f24id;
        this.analyticsCollectorHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.MediaPeriodQueue$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MediaPeriodQueue.this.m262x6b40a91a(builder, mediaPeriodId);
            }
        });
    }

    /* renamed from: lambda$notifyQueueUpdate$0$androidx-media3-exoplayer-MediaPeriodQueue, reason: not valid java name */
    public /* synthetic */ void m262x6b40a91a(ImmutableList.Builder builder, MediaSource.MediaPeriodId mediaPeriodId) {
        this.analyticsCollector.updateMediaPeriodQueueInfo(builder.build(), mediaPeriodId);
    }

    private long resolvePeriodUidToWindowSequenceNumber(Timeline timeline, Object obj) {
        int indexOfPeriod;
        int i = timeline.getPeriodByUid(obj, this.period).windowIndex;
        Object obj2 = this.oldFrontPeriodUid;
        if (obj2 != null && (indexOfPeriod = timeline.getIndexOfPeriod(obj2)) != -1 && timeline.getPeriod(indexOfPeriod, this.period).windowIndex == i) {
            return this.oldFrontPeriodWindowSequenceNumber;
        }
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.getNext()) {
            if (mediaPeriodHolder.uid.equals(obj)) {
                return mediaPeriodHolder.info.f24id.windowSequenceNumber;
            }
        }
        for (MediaPeriodHolder mediaPeriodHolder2 = this.playing; mediaPeriodHolder2 != null; mediaPeriodHolder2 = mediaPeriodHolder2.getNext()) {
            int indexOfPeriod2 = timeline.getIndexOfPeriod(mediaPeriodHolder2.uid);
            if (indexOfPeriod2 != -1 && timeline.getPeriod(indexOfPeriod2, this.period).windowIndex == i) {
                return mediaPeriodHolder2.info.f24id.windowSequenceNumber;
            }
        }
        long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(obj);
        if (resolvePeriodUidToWindowSequenceNumberInPreloadPeriods != -1) {
            return resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
        }
        long j = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + j;
        if (this.playing == null) {
            this.oldFrontPeriodUid = obj;
            this.oldFrontPeriodWindowSequenceNumber = j;
        }
        return j;
    }

    private long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(Object obj) {
        for (int i = 0; i < this.preloadPriorityList.size(); i++) {
            MediaPeriodHolder mediaPeriodHolder = this.preloadPriorityList.get(i);
            if (mediaPeriodHolder.uid.equals(obj)) {
                return mediaPeriodHolder.info.f24id.windowSequenceNumber;
            }
        }
        return -1L;
    }

    private boolean canKeepMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo, MediaPeriodInfo mediaPeriodInfo2) {
        return mediaPeriodInfo.startPositionUs == mediaPeriodInfo2.startPositionUs && mediaPeriodInfo.f24id.equals(mediaPeriodInfo2.f24id);
    }

    private boolean updateForPlaybackModeChange(Timeline timeline) {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder == null) {
            return true;
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodHolder.uid);
        while (true) {
            indexOfPeriod = timeline.getNextPeriodIndex(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while (((MediaPeriodHolder) Assertions.checkNotNull(mediaPeriodHolder)).getNext() != null && !mediaPeriodHolder.info.isLastInTimelinePeriod) {
                mediaPeriodHolder = mediaPeriodHolder.getNext();
            }
            MediaPeriodHolder next = mediaPeriodHolder.getNext();
            if (indexOfPeriod == -1 || next == null || timeline.getIndexOfPeriod(next.uid) != indexOfPeriod) {
                break;
            }
            mediaPeriodHolder = next;
        }
        boolean removeAfter = removeAfter(mediaPeriodHolder);
        mediaPeriodHolder.info = getUpdatedMediaPeriodInfo(timeline, mediaPeriodHolder.info);
        return !removeAfter;
    }

    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.timeline, playbackInfo.periodId, playbackInfo.requestedContentPositionUs, playbackInfo.positionUs);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfo(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j) {
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        long rendererOffset = (mediaPeriodHolder.getRendererOffset() + mediaPeriodInfo.durationUs) - j;
        if (mediaPeriodInfo.isLastInTimelinePeriod) {
            return getFirstMediaPeriodInfoOfNextPeriod(timeline, mediaPeriodHolder, rendererOffset);
        }
        return getFollowingMediaPeriodInfoOfCurrentPeriod(timeline, mediaPeriodHolder, rendererOffset);
    }

    private MediaPeriodInfo getFirstMediaPeriodInfoOfNextPeriod(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j) {
        MediaPeriodInfo mediaPeriodInfo;
        long j2;
        long j3;
        Object obj;
        long j4;
        long j5;
        long resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
        MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodHolder.info;
        int nextPeriodIndex = timeline.getNextPeriodIndex(timeline.getIndexOfPeriod(mediaPeriodInfo2.f24id.periodUid), this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
        if (nextPeriodIndex == -1) {
            return null;
        }
        int i = timeline.getPeriod(nextPeriodIndex, this.period, true).windowIndex;
        Object checkNotNull = Assertions.checkNotNull(this.period.uid);
        long j6 = mediaPeriodInfo2.f24id.windowSequenceNumber;
        if (timeline.getWindow(i, this.window).firstPeriodIndex == nextPeriodIndex) {
            mediaPeriodInfo = mediaPeriodInfo2;
            Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(this.window, this.period, i, C.TIME_UNSET, Math.max(0L, j));
            if (periodPositionUs == null) {
                return null;
            }
            Object obj2 = periodPositionUs.first;
            long longValue = ((Long) periodPositionUs.second).longValue();
            MediaPeriodHolder next = mediaPeriodHolder.getNext();
            if (next != null && next.uid.equals(obj2)) {
                resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = next.info.f24id.windowSequenceNumber;
            } else {
                resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods(obj2);
                if (resolvePeriodUidToWindowSequenceNumberInPreloadPeriods == -1) {
                    resolvePeriodUidToWindowSequenceNumberInPreloadPeriods = this.nextWindowSequenceNumber;
                    this.nextWindowSequenceNumber = 1 + resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
                }
            }
            j2 = resolvePeriodUidToWindowSequenceNumberInPreloadPeriods;
            j3 = -9223372036854775807L;
            obj = obj2;
            j4 = longValue;
        } else {
            mediaPeriodInfo = mediaPeriodInfo2;
            j2 = j6;
            j3 = 0;
            obj = checkNotNull;
            j4 = 0;
        }
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAds = resolveMediaPeriodIdForAds(timeline, obj, j4, j2, this.window, this.period);
        if (j3 != C.TIME_UNSET && mediaPeriodInfo.requestedContentPositionUs != C.TIME_UNSET) {
            boolean hasServerSideInsertedAds = hasServerSideInsertedAds(mediaPeriodInfo.f24id.periodUid, timeline);
            if (resolveMediaPeriodIdForAds.isAd() && hasServerSideInsertedAds) {
                j3 = mediaPeriodInfo.requestedContentPositionUs;
            } else if (hasServerSideInsertedAds) {
                j5 = mediaPeriodInfo.requestedContentPositionUs;
                return getMediaPeriodInfo(timeline, resolveMediaPeriodIdForAds, j3, j5);
            }
        }
        j5 = j4;
        return getMediaPeriodInfo(timeline, resolveMediaPeriodIdForAds, j3, j5);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfoOfCurrentPeriod(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j) {
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.f24id;
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        if (mediaPeriodId.isAd()) {
            int i = mediaPeriodId.adGroupIndex;
            int adCountInAdGroup = this.period.getAdCountInAdGroup(i);
            if (adCountInAdGroup == -1) {
                return null;
            }
            int nextAdIndexToPlay = this.period.getNextAdIndexToPlay(i, mediaPeriodId.adIndexInAdGroup);
            if (nextAdIndexToPlay < adCountInAdGroup) {
                return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, i, nextAdIndexToPlay, mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            long j2 = mediaPeriodInfo.requestedContentPositionUs;
            if (j2 == C.TIME_UNSET) {
                Timeline.Window window = this.window;
                Timeline.Period period = this.period;
                Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window, period, period.windowIndex, C.TIME_UNSET, Math.max(0L, j));
                if (periodPositionUs == null) {
                    return null;
                }
                j2 = ((Long) periodPositionUs.second).longValue();
            }
            return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, Math.max(getMinStartPositionAfterAdGroupUs(timeline, mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex), j2), mediaPeriodInfo.requestedContentPositionUs, mediaPeriodId.windowSequenceNumber);
        }
        if (mediaPeriodId.nextAdGroupIndex != -1 && this.period.isLivePostrollPlaceholder(mediaPeriodId.nextAdGroupIndex)) {
            return getFirstMediaPeriodInfoOfNextPeriod(timeline, mediaPeriodHolder, j);
        }
        int firstAdIndexToPlay = this.period.getFirstAdIndexToPlay(mediaPeriodId.nextAdGroupIndex);
        boolean z = this.period.isServerSideInsertedAdGroup(mediaPeriodId.nextAdGroupIndex) && this.period.getAdState(mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay) == 3;
        if (firstAdIndexToPlay == this.period.getAdCountInAdGroup(mediaPeriodId.nextAdGroupIndex) || z) {
            return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, getMinStartPositionAfterAdGroupUs(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex), mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber);
        }
        return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, mediaPeriodId.nextAdGroupIndex, firstAdIndexToPlay, mediaPeriodInfo.durationUs, mediaPeriodId.windowSequenceNumber);
    }

    private boolean hasServerSideInsertedAds(Object obj, Timeline timeline) {
        int adGroupCount = timeline.getPeriodByUid(obj, this.period).getAdGroupCount();
        int removedAdGroupCount = this.period.getRemovedAdGroupCount();
        return adGroupCount > 0 && this.period.isServerSideInsertedAdGroup(removedAdGroupCount) && (adGroupCount > 1 || this.period.getAdGroupTimeUs(removedAdGroupCount) != Long.MIN_VALUE);
    }

    private MediaPeriodInfo getMediaPeriodInfo(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j, long j2) {
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        if (mediaPeriodId.isAd()) {
            return getMediaPeriodInfoForAd(timeline, mediaPeriodId.periodUid, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, j, mediaPeriodId.windowSequenceNumber);
        }
        return getMediaPeriodInfoForContent(timeline, mediaPeriodId.periodUid, j2, j, mediaPeriodId.windowSequenceNumber);
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(Timeline timeline, Object obj, int i, int i2, long j, long j2) {
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, i, i2, j2);
        long adDurationUs = timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        long adResumePositionUs = i2 == this.period.getFirstAdIndexToPlay(i) ? this.period.getAdResumePositionUs() : 0L;
        return new MediaPeriodInfo(mediaPeriodId, (adDurationUs == C.TIME_UNSET || adResumePositionUs < adDurationUs) ? adResumePositionUs : Math.max(0L, adDurationUs - 1), j, C.TIME_UNSET, adDurationUs, this.period.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex), false, false, false);
    }

    private MediaPeriodInfo getMediaPeriodInfoForContent(Timeline timeline, Object obj, long j, long j2, long j3) {
        boolean z;
        long j4;
        long j5;
        long j6;
        long j7 = j;
        timeline.getPeriodByUid(obj, this.period);
        int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j7);
        int i = 1;
        boolean z2 = adGroupIndexAfterPositionUs != -1 && this.period.isLivePostrollPlaceholder(adGroupIndexAfterPositionUs);
        if (adGroupIndexAfterPositionUs == -1) {
            if (this.period.getAdGroupCount() > 0) {
                Timeline.Period period = this.period;
                if (period.isServerSideInsertedAdGroup(period.getRemovedAdGroupCount())) {
                    z = true;
                }
            }
            z = false;
        } else {
            if (this.period.isServerSideInsertedAdGroup(adGroupIndexAfterPositionUs) && this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs) == this.period.durationUs && this.period.hasPlayedAdGroup(adGroupIndexAfterPositionUs)) {
                z = true;
                adGroupIndexAfterPositionUs = -1;
            }
            z = false;
        }
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, j3, adGroupIndexAfterPositionUs);
        boolean isLastInPeriod = isLastInPeriod(mediaPeriodId);
        boolean isLastInWindow = isLastInWindow(timeline, mediaPeriodId);
        boolean isLastInTimeline = isLastInTimeline(timeline, mediaPeriodId, isLastInPeriod);
        boolean z3 = (adGroupIndexAfterPositionUs == -1 || !this.period.isServerSideInsertedAdGroup(adGroupIndexAfterPositionUs) || z2) ? false : true;
        if (adGroupIndexAfterPositionUs != -1 && !z2) {
            j5 = this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs);
        } else if (z) {
            j5 = this.period.durationUs;
        } else {
            j4 = -9223372036854775807L;
            j6 = (j4 != C.TIME_UNSET || j4 == Long.MIN_VALUE) ? this.period.durationUs : j4;
            if (j6 != C.TIME_UNSET && j7 >= j6) {
                if (!isLastInTimeline && z) {
                    i = 0;
                }
                j7 = Math.max(0L, j6 - i);
            }
            return new MediaPeriodInfo(mediaPeriodId, j7, j2, j4, j6, z3, isLastInPeriod, isLastInWindow, isLastInTimeline);
        }
        j4 = j5;
        if (j4 != C.TIME_UNSET) {
        }
        if (j6 != C.TIME_UNSET) {
            if (!isLastInTimeline) {
                i = 0;
            }
            j7 = Math.max(0L, j6 - i);
        }
        return new MediaPeriodInfo(mediaPeriodId, j7, j2, j4, j6, z3, isLastInPeriod, isLastInWindow, isLastInTimeline);
    }

    private boolean isLastInPeriod(MediaSource.MediaPeriodId mediaPeriodId) {
        return !mediaPeriodId.isAd() && mediaPeriodId.nextAdGroupIndex == -1;
    }

    private boolean isLastInWindow(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (isLastInPeriod(mediaPeriodId)) {
            return timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window).lastPeriodIndex == timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        }
        return false;
    }

    private boolean isLastInTimeline(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, boolean z) {
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        return !timeline.getWindow(timeline.getPeriod(indexOfPeriod, this.period).windowIndex, this.window).isDynamic && timeline.isLastPeriod(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) && z;
    }

    private long getMinStartPositionAfterAdGroupUs(Timeline timeline, Object obj, int i) {
        timeline.getPeriodByUid(obj, this.period);
        long adGroupTimeUs = this.period.getAdGroupTimeUs(i);
        if (adGroupTimeUs == Long.MIN_VALUE) {
            return this.period.durationUs;
        }
        return adGroupTimeUs + this.period.getContentResumeOffsetUs(i);
    }
}
