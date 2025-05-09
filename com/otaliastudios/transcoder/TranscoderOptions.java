package com.otaliastudios.transcoder;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.resample.AudioResampler;
import com.otaliastudios.transcoder.resample.DefaultAudioResampler;
import com.otaliastudios.transcoder.sink.DataSink;
import com.otaliastudios.transcoder.sink.DefaultDataSink;
import com.otaliastudios.transcoder.source.AssetFileDescriptorDataSource;
import com.otaliastudios.transcoder.source.DataSource;
import com.otaliastudios.transcoder.source.FileDescriptorDataSource;
import com.otaliastudios.transcoder.source.FilePathDataSource;
import com.otaliastudios.transcoder.source.UriDataSource;
import com.otaliastudios.transcoder.strategy.DefaultAudioStrategy;
import com.otaliastudios.transcoder.strategy.DefaultVideoStrategies;
import com.otaliastudios.transcoder.strategy.TrackStrategy;
import com.otaliastudios.transcoder.stretch.AudioStretcher;
import com.otaliastudios.transcoder.stretch.DefaultAudioStretcher;
import com.otaliastudios.transcoder.time.DefaultTimeInterpolator;
import com.otaliastudios.transcoder.time.SpeedTimeInterpolator;
import com.otaliastudios.transcoder.time.TimeInterpolator;
import com.otaliastudios.transcoder.validator.DefaultValidator;
import com.otaliastudios.transcoder.validator.Validator;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class TranscoderOptions {
    private List<DataSource> audioDataSources;
    private AudioResampler audioResampler;
    private AudioStretcher audioStretcher;
    private TrackStrategy audioTrackStrategy;
    private DataSink dataSink;
    private TranscoderListener listener;
    private Handler listenerHandler;
    private TimeInterpolator timeInterpolator;
    private Validator validator;
    private List<DataSource> videoDataSources;
    private int videoRotation;
    private TrackStrategy videoTrackStrategy;

    private TranscoderOptions() {
    }

    public TranscoderListener getListener() {
        return this.listener;
    }

    public Handler getListenerHandler() {
        return this.listenerHandler;
    }

    public DataSink getDataSink() {
        return this.dataSink;
    }

    public List<DataSource> getAudioDataSources() {
        return this.audioDataSources;
    }

    public List<DataSource> getVideoDataSources() {
        return this.videoDataSources;
    }

    public TrackStrategy getAudioTrackStrategy() {
        return this.audioTrackStrategy;
    }

    public TrackStrategy getVideoTrackStrategy() {
        return this.videoTrackStrategy;
    }

    public Validator getValidator() {
        return this.validator;
    }

    public int getVideoRotation() {
        return this.videoRotation;
    }

    public TimeInterpolator getTimeInterpolator() {
        return this.timeInterpolator;
    }

    public AudioStretcher getAudioStretcher() {
        return this.audioStretcher;
    }

    public AudioResampler getAudioResampler() {
        return this.audioResampler;
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        private AudioResampler audioResampler;
        private AudioStretcher audioStretcher;
        private TrackStrategy audioTrackStrategy;
        private final DataSink dataSink;
        private TranscoderListener listener;
        private Handler listenerHandler;
        private TimeInterpolator timeInterpolator;
        private Validator validator;
        private int videoRotation;
        private TrackStrategy videoTrackStrategy;
        private final List<DataSource> audioDataSources = new ArrayList();
        private final List<DataSource> videoDataSources = new ArrayList();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(String str) {
            this.dataSink = new DefaultDataSink(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(FileDescriptor fileDescriptor) {
            this.dataSink = new DefaultDataSink(fileDescriptor);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(DataSink dataSink) {
            this.dataSink = dataSink;
        }

        public Builder addDataSource(DataSource dataSource) {
            this.audioDataSources.add(dataSource);
            this.videoDataSources.add(dataSource);
            return this;
        }

        public Builder addDataSource(TrackType trackType, DataSource dataSource) {
            if (trackType == TrackType.AUDIO) {
                this.audioDataSources.add(dataSource);
            } else if (trackType == TrackType.VIDEO) {
                this.videoDataSources.add(dataSource);
            }
            return this;
        }

        public Builder addDataSource(FileDescriptor fileDescriptor) {
            return addDataSource(new FileDescriptorDataSource(fileDescriptor));
        }

        public Builder addDataSource(TrackType trackType, FileDescriptor fileDescriptor) {
            return addDataSource(trackType, new FileDescriptorDataSource(fileDescriptor));
        }

        public Builder addDataSource(AssetFileDescriptor assetFileDescriptor) {
            return addDataSource(new AssetFileDescriptorDataSource(assetFileDescriptor));
        }

        public Builder addDataSource(TrackType trackType, AssetFileDescriptor assetFileDescriptor) {
            return addDataSource(trackType, new AssetFileDescriptorDataSource(assetFileDescriptor));
        }

        public Builder addDataSource(String str) {
            return addDataSource(new FilePathDataSource(str));
        }

        public Builder addDataSource(TrackType trackType, String str) {
            return addDataSource(trackType, new FilePathDataSource(str));
        }

        public Builder addDataSource(Context context, Uri uri) {
            return addDataSource(new UriDataSource(context, uri));
        }

        public Builder addDataSource(TrackType trackType, Context context, Uri uri) {
            return addDataSource(trackType, new UriDataSource(context, uri));
        }

        public Builder setAudioTrackStrategy(TrackStrategy trackStrategy) {
            this.audioTrackStrategy = trackStrategy;
            return this;
        }

        public Builder setVideoTrackStrategy(TrackStrategy trackStrategy) {
            this.videoTrackStrategy = trackStrategy;
            return this;
        }

        public Builder setListener(TranscoderListener transcoderListener) {
            this.listener = transcoderListener;
            return this;
        }

        public Builder setListenerHandler(Handler handler) {
            this.listenerHandler = handler;
            return this;
        }

        public Builder setValidator(Validator validator) {
            this.validator = validator;
            return this;
        }

        public Builder setVideoRotation(int i) {
            this.videoRotation = i;
            return this;
        }

        public Builder setTimeInterpolator(TimeInterpolator timeInterpolator) {
            this.timeInterpolator = timeInterpolator;
            return this;
        }

        public Builder setSpeed(float f) {
            return setTimeInterpolator(new SpeedTimeInterpolator(f));
        }

        public Builder setAudioStretcher(AudioStretcher audioStretcher) {
            this.audioStretcher = audioStretcher;
            return this;
        }

        public Builder setAudioResampler(AudioResampler audioResampler) {
            this.audioResampler = audioResampler;
            return this;
        }

        public TranscoderOptions build() {
            if (this.listener == null) {
                throw new IllegalStateException("listener can't be null");
            }
            if (this.audioDataSources.isEmpty() && this.videoDataSources.isEmpty()) {
                throw new IllegalStateException("we need at least one data source");
            }
            int i = this.videoRotation;
            if (i != 0 && i != 90 && i != 180 && i != 270) {
                throw new IllegalArgumentException("Accepted values for rotation are 0, 90, 180, 270");
            }
            if (this.listenerHandler == null) {
                Looper myLooper = Looper.myLooper();
                if (myLooper == null) {
                    myLooper = Looper.getMainLooper();
                }
                this.listenerHandler = new Handler(myLooper);
            }
            if (this.audioTrackStrategy == null) {
                this.audioTrackStrategy = DefaultAudioStrategy.builder().build();
            }
            if (this.videoTrackStrategy == null) {
                this.videoTrackStrategy = DefaultVideoStrategies.for720x1280();
            }
            if (this.validator == null) {
                this.validator = new DefaultValidator();
            }
            if (this.timeInterpolator == null) {
                this.timeInterpolator = new DefaultTimeInterpolator();
            }
            if (this.audioStretcher == null) {
                this.audioStretcher = new DefaultAudioStretcher();
            }
            if (this.audioResampler == null) {
                this.audioResampler = new DefaultAudioResampler();
            }
            TranscoderOptions transcoderOptions = new TranscoderOptions();
            transcoderOptions.listener = this.listener;
            transcoderOptions.audioDataSources = this.audioDataSources;
            transcoderOptions.videoDataSources = this.videoDataSources;
            transcoderOptions.dataSink = this.dataSink;
            transcoderOptions.listenerHandler = this.listenerHandler;
            transcoderOptions.audioTrackStrategy = this.audioTrackStrategy;
            transcoderOptions.videoTrackStrategy = this.videoTrackStrategy;
            transcoderOptions.validator = this.validator;
            transcoderOptions.videoRotation = this.videoRotation;
            transcoderOptions.timeInterpolator = this.timeInterpolator;
            transcoderOptions.audioStretcher = this.audioStretcher;
            transcoderOptions.audioResampler = this.audioResampler;
            return transcoderOptions;
        }

        public Future<Void> transcode() {
            return Transcoder.getInstance().transcode(build());
        }
    }
}
