package org.tensorflow.lite.support.audio;

import android.media.AudioFormat;
import android.media.AudioRecord;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.audio.AutoValue_TensorAudio_TensorAudioFormat;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class TensorAudio {
    private static final String TAG = "TensorAudio";
    private final FloatRingBuffer buffer;
    private final TensorAudioFormat format;

    public static TensorAudio create(TensorAudioFormat format, int sampleCounts) {
        return new TensorAudio(format, sampleCounts);
    }

    public static TensorAudio create(AudioFormat format, int sampleCounts) {
        return new TensorAudio(TensorAudioFormat.create(format), sampleCounts);
    }

    /* loaded from: classes6.dex */
    public static abstract class TensorAudioFormat {
        private static final int DEFAULT_CHANNELS = 1;

        public abstract int getChannels();

        public abstract int getSampleRate();

        public static TensorAudioFormat create(AudioFormat format) {
            return builder().setChannels(format.getChannelCount()).setSampleRate(format.getSampleRate()).build();
        }

        public static Builder builder() {
            return new AutoValue_TensorAudio_TensorAudioFormat.Builder().setChannels(1);
        }

        /* loaded from: classes6.dex */
        public static abstract class Builder {
            abstract TensorAudioFormat autoBuild();

            public abstract Builder setChannels(int value);

            public abstract Builder setSampleRate(int value);

            public TensorAudioFormat build() {
                TensorAudioFormat autoBuild = autoBuild();
                SupportPreconditions.checkArgument(autoBuild.getChannels() > 0, "Number of channels should be greater than 0");
                SupportPreconditions.checkArgument(autoBuild.getSampleRate() > 0, "Sample rate should be greater than 0");
                return autoBuild;
            }
        }
    }

    public void load(float[] src) {
        load(src, 0, src.length);
    }

    public void load(float[] src, int offsetInFloat, int sizeInFloat) {
        SupportPreconditions.checkArgument(sizeInFloat % this.format.getChannels() == 0, String.format("Size (%d) needs to be a multiplier of the number of channels (%d)", Integer.valueOf(sizeInFloat), Integer.valueOf(this.format.getChannels())));
        this.buffer.load(src, offsetInFloat, sizeInFloat);
    }

    public void load(short[] src) {
        load(src, 0, src.length);
    }

    public void load(short[] src, int offsetInShort, int sizeInShort) {
        SupportPreconditions.checkArgument(offsetInShort + sizeInShort <= src.length, String.format("Index out of range. offset (%d) + size (%d) should <= newData.length (%d)", Integer.valueOf(offsetInShort), Integer.valueOf(sizeInShort), Integer.valueOf(src.length)));
        float[] fArr = new float[sizeInShort];
        for (int i = 0; i < sizeInShort; i++) {
            fArr[i] = (src[i + offsetInShort] * 1.0f) / 32767.0f;
        }
        load(fArr);
    }

    public int load(AudioRecord record) {
        int read;
        SupportPreconditions.checkArgument(this.format.equals(TensorAudioFormat.create(record.getFormat())), "Incompatible audio format.");
        if (record.getAudioFormat() == 4) {
            int channelCount = record.getChannelCount() * record.getBufferSizeInFrames();
            float[] fArr = new float[channelCount];
            read = record.read(fArr, 0, channelCount, 1);
            if (read > 0) {
                load(fArr, 0, read);
                return read;
            }
        } else if (record.getAudioFormat() == 2) {
            int channelCount2 = record.getChannelCount() * record.getBufferSizeInFrames();
            short[] sArr = new short[channelCount2];
            read = record.read(sArr, 0, channelCount2, 1);
            if (read > 0) {
                load(sArr, 0, read);
                return read;
            }
        } else {
            throw new IllegalArgumentException("Unsupported encoding. Requires ENCODING_PCM_16BIT or ENCODING_PCM_FLOAT.");
        }
        if (read == -6) {
            throw new IllegalStateException("AudioRecord.ERROR_DEAD_OBJECT");
        }
        if (read == -3) {
            throw new IllegalStateException("AudioRecord.ERROR_INVALID_OPERATION");
        }
        if (read == -2) {
            throw new IllegalStateException("AudioRecord.ERROR_BAD_VALUE");
        }
        if (read != -1) {
            return 0;
        }
        throw new IllegalStateException("AudioRecord.ERROR");
    }

    public TensorBuffer getTensorBuffer() {
        ByteBuffer buffer = this.buffer.getBuffer();
        TensorBuffer createFixedSize = TensorBuffer.createFixedSize(new int[]{1, buffer.asFloatBuffer().limit()}, DataType.FLOAT32);
        createFixedSize.loadBuffer(buffer);
        return createFixedSize;
    }

    public TensorAudioFormat getFormat() {
        return this.format;
    }

    private TensorAudio(TensorAudioFormat format, int sampleCounts) {
        this.format = format;
        this.buffer = new FloatRingBuffer(sampleCounts * format.getChannels());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class FloatRingBuffer {
        private final float[] buffer;
        private int nextIndex = 0;

        public FloatRingBuffer(int flatSize) {
            this.buffer = new float[flatSize];
        }

        public void load(float[] newData) {
            load(newData, 0, newData.length);
        }

        public void load(float[] newData, int offset, int size) {
            SupportPreconditions.checkArgument(offset + size <= newData.length, String.format("Index out of range. offset (%d) + size (%d) should <= newData.length (%d)", Integer.valueOf(offset), Integer.valueOf(size), Integer.valueOf(newData.length)));
            float[] fArr = this.buffer;
            if (size > fArr.length) {
                offset += size - fArr.length;
                size = fArr.length;
            }
            int i = this.nextIndex;
            if (i + size < fArr.length) {
                System.arraycopy(newData, offset, fArr, i, size);
            } else {
                int length = fArr.length - i;
                System.arraycopy(newData, offset, fArr, i, length);
                System.arraycopy(newData, offset + length, this.buffer, 0, size - length);
            }
            this.nextIndex = (this.nextIndex + size) % this.buffer.length;
        }

        public ByteBuffer getBuffer() {
            ByteBuffer allocate = ByteBuffer.allocate(DataType.FLOAT32.byteSize() * this.buffer.length);
            allocate.order(ByteOrder.nativeOrder());
            FloatBuffer asFloatBuffer = allocate.asFloatBuffer();
            float[] fArr = this.buffer;
            int i = this.nextIndex;
            asFloatBuffer.put(fArr, i, fArr.length - i);
            asFloatBuffer.put(this.buffer, 0, this.nextIndex);
            allocate.rewind();
            return allocate;
        }

        public int getCapacity() {
            return this.buffer.length;
        }
    }
}
