package io.flutter.plugins.videoplayer;

import androidx.core.app.NotificationCompat;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import io.flutter.plugin.common.EventChannel;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes5.dex */
final class VideoPlayerEventCallbacks implements VideoPlayerCallbacks {
    private final EventChannel.EventSink eventSink;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static VideoPlayerEventCallbacks bindTo(EventChannel eventChannel) {
        final QueuingEventSink queuingEventSink = new QueuingEventSink();
        eventChannel.setStreamHandler(new EventChannel.StreamHandler() { // from class: io.flutter.plugins.videoplayer.VideoPlayerEventCallbacks.1
            @Override // io.flutter.plugin.common.EventChannel.StreamHandler
            public void onListen(Object obj, EventChannel.EventSink eventSink) {
                QueuingEventSink.this.setDelegate(eventSink);
            }

            @Override // io.flutter.plugin.common.EventChannel.StreamHandler
            public void onCancel(Object obj) {
                QueuingEventSink.this.setDelegate(null);
            }
        });
        return withSink(queuingEventSink);
    }

    static VideoPlayerEventCallbacks withSink(EventChannel.EventSink eventSink) {
        return new VideoPlayerEventCallbacks(eventSink);
    }

    private VideoPlayerEventCallbacks(EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerCallbacks
    public void onInitialized(int i, int i2, long j, int i3) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "initialized");
        hashMap.put("width", Integer.valueOf(i));
        hashMap.put("height", Integer.valueOf(i2));
        hashMap.put("duration", Long.valueOf(j));
        if (i3 != 0) {
            hashMap.put("rotationCorrection", Integer.valueOf(i3));
        }
        this.eventSink.success(hashMap);
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerCallbacks
    public void onBufferingStart() {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "bufferingStart");
        this.eventSink.success(hashMap);
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerCallbacks
    public void onBufferingUpdate(long j) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "bufferingUpdate");
        hashMap.put("values", Collections.singletonList(Arrays.asList(0, Long.valueOf(j))));
        this.eventSink.success(hashMap);
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerCallbacks
    public void onBufferingEnd() {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "bufferingEnd");
        this.eventSink.success(hashMap);
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerCallbacks
    public void onCompleted() {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, WorkflowModule.Properties.Section.Component.State.STATE_COMPLETED);
        this.eventSink.success(hashMap);
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerCallbacks
    public void onError(String str, String str2, Object obj) {
        this.eventSink.error(str, str2, obj);
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerCallbacks
    public void onIsPlayingStateUpdate(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "isPlayingStateUpdate");
        hashMap.put("isPlaying", Boolean.valueOf(z));
        this.eventSink.success(hashMap);
    }
}
