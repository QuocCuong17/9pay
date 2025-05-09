package com.otaliastudios.transcoder.strategy;

import android.media.MediaFormat;
import android.os.Build;
import com.otaliastudios.transcoder.common.ExactSize;
import com.otaliastudios.transcoder.common.Size;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.internal.media.MediaFormatConstants;
import com.otaliastudios.transcoder.internal.utils.BitRates;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.resize.AspectRatioResizer;
import com.otaliastudios.transcoder.resize.AtMostResizer;
import com.otaliastudios.transcoder.resize.ExactResizer;
import com.otaliastudios.transcoder.resize.FractionResizer;
import com.otaliastudios.transcoder.resize.MultiResizer;
import com.otaliastudios.transcoder.resize.Resizer;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class DefaultVideoStrategy implements TrackStrategy {
    public static final long BITRATE_UNKNOWN = Long.MIN_VALUE;
    public static final int DEFAULT_FRAME_RATE = 30;
    public static final float DEFAULT_KEY_FRAME_INTERVAL = 3.0f;
    private static final Logger LOG = new Logger("DefaultVideoStrategy");
    private final Options options;

    /* loaded from: classes4.dex */
    public static class Options {
        private Resizer resizer;
        private long targetBitRate;
        private int targetFrameRate;
        private float targetKeyFrameInterval;
        private String targetMimeType;

        private Options() {
        }
    }

    public static Builder exact(int i, int i2) {
        return new Builder(new ExactResizer(i, i2));
    }

    public static Builder fraction(float f) {
        return new Builder(new FractionResizer(f));
    }

    public static Builder aspectRatio(float f) {
        return new Builder(new AspectRatioResizer(f));
    }

    public static Builder atMost(int i) {
        return new Builder(new AtMostResizer(i));
    }

    public static Builder atMost(int i, int i2) {
        return new Builder(new AtMostResizer(i, i2));
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        private MultiResizer resizer;
        private long targetBitRate;
        private int targetFrameRate;
        private float targetKeyFrameInterval;
        private String targetMimeType;

        public Builder() {
            this.resizer = new MultiResizer();
            this.targetFrameRate = 30;
            this.targetBitRate = Long.MIN_VALUE;
            this.targetKeyFrameInterval = 3.0f;
            this.targetMimeType = "video/avc";
        }

        public Builder(Resizer resizer) {
            MultiResizer multiResizer = new MultiResizer();
            this.resizer = multiResizer;
            this.targetFrameRate = 30;
            this.targetBitRate = Long.MIN_VALUE;
            this.targetKeyFrameInterval = 3.0f;
            this.targetMimeType = "video/avc";
            multiResizer.addResizer(resizer);
        }

        public Builder addResizer(Resizer resizer) {
            this.resizer.addResizer(resizer);
            return this;
        }

        public Builder bitRate(long j) {
            this.targetBitRate = j;
            return this;
        }

        public Builder frameRate(int i) {
            this.targetFrameRate = i;
            return this;
        }

        public Builder keyFrameInterval(float f) {
            this.targetKeyFrameInterval = f;
            return this;
        }

        public Builder mimeType(String str) {
            this.targetMimeType = str;
            return this;
        }

        public Options options() {
            Options options = new Options();
            options.resizer = this.resizer;
            options.targetFrameRate = this.targetFrameRate;
            options.targetBitRate = this.targetBitRate;
            options.targetKeyFrameInterval = this.targetKeyFrameInterval;
            options.targetMimeType = this.targetMimeType;
            return options;
        }

        public DefaultVideoStrategy build() {
            return new DefaultVideoStrategy(options());
        }
    }

    public DefaultVideoStrategy(Options options) {
        this.options = options;
    }

    @Override // com.otaliastudios.transcoder.strategy.TrackStrategy
    public TrackStatus createOutputFormat(List<MediaFormat> list, MediaFormat mediaFormat) {
        int minor;
        int major;
        long j;
        boolean checkMimeType = checkMimeType(list);
        ExactSize bestInputSize = getBestInputSize(list);
        int width = bestInputSize.getWidth();
        int height = bestInputSize.getHeight();
        Logger logger = LOG;
        logger.i("Input width&height: " + width + ViewHierarchyNode.JsonKeys.X + height);
        try {
            Size outputSize = this.options.resizer.getOutputSize(bestInputSize);
            if (outputSize instanceof ExactSize) {
                ExactSize exactSize = (ExactSize) outputSize;
                minor = exactSize.getWidth();
                major = exactSize.getHeight();
            } else if (width >= height) {
                minor = outputSize.getMajor();
                major = outputSize.getMinor();
            } else {
                minor = outputSize.getMinor();
                major = outputSize.getMajor();
            }
            logger.i("Output width&height: " + minor + ViewHierarchyNode.JsonKeys.X + major);
            boolean z = bestInputSize.getMinor() <= outputSize.getMinor();
            int minFrameRate = getMinFrameRate(list);
            int min = minFrameRate > 0 ? Math.min(minFrameRate, this.options.targetFrameRate) : this.options.targetFrameRate;
            boolean z2 = minFrameRate <= min;
            int averageIFrameInterval = getAverageIFrameInterval(list);
            boolean z3 = ((float) averageIFrameInterval) >= this.options.targetKeyFrameInterval;
            if (!(list.size() == 1) || !checkMimeType || !z || !z2 || !z3) {
                mediaFormat.setString("mime", this.options.targetMimeType);
                mediaFormat.setInteger("width", minor);
                mediaFormat.setInteger("height", major);
                mediaFormat.setInteger(MediaFormatConstants.KEY_ROTATION_DEGREES, 0);
                mediaFormat.setInteger("frame-rate", min);
                if (Build.VERSION.SDK_INT >= 25) {
                    mediaFormat.setFloat("i-frame-interval", this.options.targetKeyFrameInterval);
                } else {
                    mediaFormat.setInteger("i-frame-interval", (int) Math.ceil(this.options.targetKeyFrameInterval));
                }
                mediaFormat.setInteger("color-format", 2130708361);
                if (this.options.targetBitRate != Long.MIN_VALUE) {
                    j = this.options.targetBitRate;
                } else {
                    j = BitRates.estimateVideoBitRate(minor, major, min);
                }
                mediaFormat.setInteger("bitrate", (int) j);
                return TrackStatus.COMPRESSING;
            }
            logger.i("Input minSize: " + bestInputSize.getMinor() + ", desired minSize: " + outputSize.getMinor() + "\nInput frameRate: " + minFrameRate + ", desired frameRate: " + min + "\nInput iFrameInterval: " + averageIFrameInterval + ", desired iFrameInterval: " + this.options.targetKeyFrameInterval);
            return TrackStatus.PASS_THROUGH;
        } catch (Exception e) {
            throw new RuntimeException("Resizer error:", e);
        }
    }

    private boolean checkMimeType(List<MediaFormat> list) {
        Iterator<MediaFormat> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().getString("mime").equalsIgnoreCase(this.options.targetMimeType)) {
                return false;
            }
        }
        return true;
    }

    private ExactSize getBestInputSize(List<MediaFormat> list) {
        int size = list.size();
        float[] fArr = new float[size];
        boolean[] zArr = new boolean[size];
        float f = 0.0f;
        for (int i = 0; i < size; i++) {
            MediaFormat mediaFormat = list.get(i);
            float integer = mediaFormat.getInteger("width");
            float integer2 = mediaFormat.getInteger("height");
            boolean z = (mediaFormat.containsKey(MediaFormatConstants.KEY_ROTATION_DEGREES) ? mediaFormat.getInteger(MediaFormatConstants.KEY_ROTATION_DEGREES) : 0) % 180 != 0;
            zArr[i] = z;
            fArr[i] = z ? integer2 / integer : integer / integer2;
            f += fArr[i];
        }
        float f2 = f / size;
        float f3 = Float.MAX_VALUE;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            float abs = Math.abs(fArr[i3] - f2);
            if (abs < f3) {
                i2 = i3;
                f3 = abs;
            }
        }
        MediaFormat mediaFormat2 = list.get(i2);
        int integer3 = mediaFormat2.getInteger("width");
        int integer4 = mediaFormat2.getInteger("height");
        int i4 = zArr[i2] ? integer4 : integer3;
        if (!zArr[i2]) {
            integer3 = integer4;
        }
        return new ExactSize(i4, integer3);
    }

    private int getMinFrameRate(List<MediaFormat> list) {
        int i = Integer.MAX_VALUE;
        for (MediaFormat mediaFormat : list) {
            if (mediaFormat.containsKey("frame-rate")) {
                i = Math.min(i, mediaFormat.getInteger("frame-rate"));
            }
        }
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i;
    }

    private int getAverageIFrameInterval(List<MediaFormat> list) {
        int i = 0;
        int i2 = 0;
        for (MediaFormat mediaFormat : list) {
            if (mediaFormat.containsKey("i-frame-interval")) {
                i++;
                i2 += mediaFormat.getInteger("i-frame-interval");
            }
        }
        if (i > 0) {
            return Math.round(i2 / i);
        }
        return -1;
    }
}
