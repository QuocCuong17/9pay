package androidx.media3.extractor;

import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import com.google.common.primitives.SignedBytes;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class Ac4Util {
    public static final int AC40_SYNCWORD = 44096;
    public static final int AC41_SYNCWORD = 44097;
    private static final int CHANNEL_COUNT_2 = 2;
    public static final int HEADER_SIZE_FOR_PARSER = 16;
    public static final int MAX_RATE_BYTES_PER_SECOND = 336000;
    private static final int[] SAMPLE_COUNT = {PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT, 2000, 1920, 1601, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};
    public static final int SAMPLE_HEADER_SIZE = 7;

    /* loaded from: classes.dex */
    public static final class SyncFrameInfo {
        public final int bitstreamVersion;
        public final int channelCount;
        public final int frameSize;
        public final int sampleCount;
        public final int sampleRate;

        private SyncFrameInfo(int i, int i2, int i3, int i4, int i5) {
            this.bitstreamVersion = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.frameSize = i4;
            this.sampleCount = i5;
        }
    }

    public static Format parseAc4AnnexEFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        parsableByteArray.skipBytes(1);
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.AUDIO_AC4).setChannelCount(2).setSampleRate(((parsableByteArray.readUnsignedByte() & 32) >> 5) == 1 ? OpusUtil.SAMPLE_RATE : 44100).setDrmInitData(drmInitData).setLanguage(str2).build();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0082, code lost:
    
        if (r10 != 11) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0087, code lost:
    
        if (r10 != 11) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008c, code lost:
    
        if (r10 != 8) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static SyncFrameInfo parseAc4SyncframeInfo(ParsableBitArray parsableBitArray) {
        int i;
        int i2;
        int readBits = parsableBitArray.readBits(16);
        int readBits2 = parsableBitArray.readBits(16);
        if (readBits2 == 65535) {
            readBits2 = parsableBitArray.readBits(24);
            i = 7;
        } else {
            i = 4;
        }
        int i3 = readBits2 + i;
        if (readBits == 44097) {
            i3 += 2;
        }
        int i4 = i3;
        int readBits3 = parsableBitArray.readBits(2);
        if (readBits3 == 3) {
            readBits3 += readVariableBits(parsableBitArray, 2);
        }
        int i5 = readBits3;
        int readBits4 = parsableBitArray.readBits(10);
        if (parsableBitArray.readBit() && parsableBitArray.readBits(3) > 0) {
            parsableBitArray.skipBits(2);
        }
        int i6 = parsableBitArray.readBit() ? 48000 : 44100;
        int readBits5 = parsableBitArray.readBits(4);
        int i7 = 0;
        if (i6 == 44100 && readBits5 == 13) {
            i2 = SAMPLE_COUNT[readBits5];
        } else {
            if (i6 == 48000) {
                int[] iArr = SAMPLE_COUNT;
                if (readBits5 < iArr.length) {
                    i7 = iArr[readBits5];
                    int i8 = readBits4 % 5;
                    if (i8 != 1) {
                        if (i8 == 2) {
                            if (readBits5 != 8) {
                            }
                            i7++;
                        } else if (i8 != 3) {
                            if (i8 == 4) {
                                if (readBits5 != 3) {
                                    if (readBits5 != 8) {
                                    }
                                }
                                i7++;
                            }
                        }
                    }
                    if (readBits5 != 3) {
                    }
                    i7++;
                }
            }
            i2 = i7;
        }
        return new SyncFrameInfo(i5, 2, i6, i4, i2);
    }

    public static int parseAc4SyncframeSize(byte[] bArr, int i) {
        int i2 = 7;
        if (bArr.length < 7) {
            return -1;
        }
        int i3 = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        if (i3 == 65535) {
            i3 = ((bArr[4] & 255) << 16) | ((bArr[5] & 255) << 8) | (bArr[6] & 255);
        } else {
            i2 = 4;
        }
        if (i == 44097) {
            i2 += 2;
        }
        return i3 + i2;
    }

    public static int parseAc4SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[16];
        int position = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(position);
        return parseAc4SyncframeInfo(new ParsableBitArray(bArr)).sampleCount;
    }

    public static void getAc4SampleHeader(int i, ParsableByteArray parsableByteArray) {
        parsableByteArray.reset(7);
        byte[] data = parsableByteArray.getData();
        data[0] = -84;
        data[1] = SignedBytes.MAX_POWER_OF_TWO;
        data[2] = -1;
        data[3] = -1;
        data[4] = (byte) ((i >> 16) & 255);
        data[5] = (byte) ((i >> 8) & 255);
        data[6] = (byte) (i & 255);
    }

    private static int readVariableBits(ParsableBitArray parsableBitArray, int i) {
        int i2 = 0;
        while (true) {
            int readBits = i2 + parsableBitArray.readBits(i);
            if (!parsableBitArray.readBit()) {
                return readBits;
            }
            i2 = (readBits + 1) << i;
        }
    }

    private Ac4Util() {
    }
}
