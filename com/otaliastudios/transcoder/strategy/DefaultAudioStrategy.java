package com.otaliastudios.transcoder.strategy;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.internal.utils.BitRates;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class DefaultAudioStrategy implements TrackStrategy {
    public static final long BITRATE_UNKNOWN = Long.MIN_VALUE;
    public static final int CHANNELS_AS_INPUT = -1;
    public static final int SAMPLE_RATE_AS_INPUT = -1;
    private Options options;

    /* loaded from: classes4.dex */
    public static class Options {
        private long targetBitRate;
        private int targetChannels;
        private String targetMimeType;
        private int targetSampleRate;

        private Options() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        private int targetChannels = -1;
        private int targetSampleRate = -1;
        private long targetBitRate = Long.MIN_VALUE;
        private String targetMimeType = "audio/mp4a-latm";

        public Builder channels(int i) {
            this.targetChannels = i;
            return this;
        }

        public Builder sampleRate(int i) {
            this.targetSampleRate = i;
            return this;
        }

        public Builder bitRate(long j) {
            this.targetBitRate = j;
            return this;
        }

        public Builder mimeType(String str) {
            this.targetMimeType = str;
            return this;
        }

        public Options options() {
            Options options = new Options();
            options.targetChannels = this.targetChannels;
            options.targetSampleRate = this.targetSampleRate;
            options.targetMimeType = this.targetMimeType;
            options.targetBitRate = this.targetBitRate;
            return options;
        }

        public DefaultAudioStrategy build() {
            return new DefaultAudioStrategy(options());
        }
    }

    public DefaultAudioStrategy(Options options) {
        this.options = options;
    }

    @Override // com.otaliastudios.transcoder.strategy.TrackStrategy
    public TrackStatus createOutputFormat(List<MediaFormat> list, MediaFormat mediaFormat) {
        int i;
        int i2;
        long j;
        if (this.options.targetChannels != -1) {
            i = this.options.targetChannels;
        } else {
            i = getInputChannelCount(list);
        }
        if (this.options.targetSampleRate != -1) {
            i2 = this.options.targetSampleRate;
        } else {
            i2 = getInputSampleRate(list);
        }
        if (list.size() == 1 && this.options.targetChannels == -1 && this.options.targetSampleRate == -1 && this.options.targetBitRate == Long.MIN_VALUE && list.get(0).containsKey("bitrate")) {
            j = list.get(0).getInteger("bitrate");
        } else if (this.options.targetBitRate != Long.MIN_VALUE) {
            j = this.options.targetBitRate;
        } else {
            j = BitRates.estimateAudioBitRate(i, i2);
        }
        mediaFormat.setString("mime", this.options.targetMimeType);
        mediaFormat.setInteger("sample-rate", i2);
        mediaFormat.setInteger("channel-count", i);
        mediaFormat.setInteger("bitrate", (int) j);
        if ("audio/mp4a-latm".equalsIgnoreCase(this.options.targetMimeType)) {
            mediaFormat.setInteger("aac-profile", 2);
        }
        return TrackStatus.COMPRESSING;
    }

    private int getInputChannelCount(List<MediaFormat> list) {
        Iterator<MediaFormat> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = Math.max(i, it.next().getInteger("channel-count"));
        }
        return i;
    }

    private int getInputSampleRate(List<MediaFormat> list) {
        Iterator<MediaFormat> it = list.iterator();
        int i = Integer.MAX_VALUE;
        while (it.hasNext()) {
            i = Math.min(i, it.next().getInteger("sample-rate"));
        }
        return i;
    }
}
