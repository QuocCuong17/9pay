package org.tensorflow.lite.support.audio;

import org.tensorflow.lite.support.audio.TensorAudio;

/* loaded from: classes6.dex */
final class AutoValue_TensorAudio_TensorAudioFormat extends TensorAudio.TensorAudioFormat {
    private final int channels;
    private final int sampleRate;

    private AutoValue_TensorAudio_TensorAudioFormat(int channels, int sampleRate) {
        this.channels = channels;
        this.sampleRate = sampleRate;
    }

    @Override // org.tensorflow.lite.support.audio.TensorAudio.TensorAudioFormat
    public int getChannels() {
        return this.channels;
    }

    @Override // org.tensorflow.lite.support.audio.TensorAudio.TensorAudioFormat
    public int getSampleRate() {
        return this.sampleRate;
    }

    public String toString() {
        return "TensorAudioFormat{channels=" + this.channels + ", sampleRate=" + this.sampleRate + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TensorAudio.TensorAudioFormat)) {
            return false;
        }
        TensorAudio.TensorAudioFormat tensorAudioFormat = (TensorAudio.TensorAudioFormat) o;
        return this.channels == tensorAudioFormat.getChannels() && this.sampleRate == tensorAudioFormat.getSampleRate();
    }

    public int hashCode() {
        return ((this.channels ^ 1000003) * 1000003) ^ this.sampleRate;
    }

    /* loaded from: classes6.dex */
    static final class Builder extends TensorAudio.TensorAudioFormat.Builder {
        private Integer channels;
        private Integer sampleRate;

        @Override // org.tensorflow.lite.support.audio.TensorAudio.TensorAudioFormat.Builder
        public TensorAudio.TensorAudioFormat.Builder setChannels(int channels) {
            this.channels = Integer.valueOf(channels);
            return this;
        }

        @Override // org.tensorflow.lite.support.audio.TensorAudio.TensorAudioFormat.Builder
        public TensorAudio.TensorAudioFormat.Builder setSampleRate(int sampleRate) {
            this.sampleRate = Integer.valueOf(sampleRate);
            return this;
        }

        @Override // org.tensorflow.lite.support.audio.TensorAudio.TensorAudioFormat.Builder
        TensorAudio.TensorAudioFormat autoBuild() {
            String str = "";
            if (this.channels == null) {
                str = " channels";
            }
            if (this.sampleRate == null) {
                str = str + " sampleRate";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_TensorAudio_TensorAudioFormat(this.channels.intValue(), this.sampleRate.intValue());
        }
    }
}
