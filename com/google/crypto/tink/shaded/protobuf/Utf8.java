package com.google.crypto.tink.shaded.protobuf;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;
import net.sf.scuba.smartcards.ISO7816;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    static final int COMPLETE = 0;
    static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1) {
        if (byte1 > -12) {
            return -1;
        }
        return byte1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1, int byte2) {
        if (byte1 > -12 || byte2 > -65) {
            return -1;
        }
        return byte1 ^ (byte2 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1, int byte2, int byte3) {
        if (byte1 > -12 || byte2 > -65 || byte3 > -65) {
            return -1;
        }
        return (byte1 ^ (byte2 << 8)) ^ (byte3 << 16);
    }

    static {
        Processor safeProcessor;
        if (UnsafeProcessor.isAvailable() && !Android.isOnAndroidDevice()) {
            safeProcessor = new UnsafeProcessor();
        } else {
            safeProcessor = new SafeProcessor();
        }
        processor = safeProcessor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(byte[] bytes) {
        return processor.isValidUtf8(bytes, 0, bytes.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(byte[] bytes, int index, int limit) {
        return processor.isValidUtf8(bytes, index, limit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int partialIsValidUtf8(int state, byte[] bytes, int index, int limit) {
        return processor.partialIsValidUtf8(state, bytes, index, limit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bytes, int index, int limit) {
        byte b = bytes[index - 1];
        int i = limit - index;
        if (i == 0) {
            return incompleteStateFor(b);
        }
        if (i == 1) {
            return incompleteStateFor(b, bytes[index]);
        }
        if (i == 2) {
            return incompleteStateFor(b, bytes[index], bytes[index + 1]);
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(final ByteBuffer buffer, final int byte1, final int index, final int remaining) {
        if (remaining == 0) {
            return incompleteStateFor(byte1);
        }
        if (remaining == 1) {
            return incompleteStateFor(byte1, buffer.get(index));
        }
        if (remaining == 2) {
            return incompleteStateFor(byte1, buffer.get(index), buffer.get(index + 1));
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        /* JADX INFO: Access modifiers changed from: package-private */
        public UnpairedSurrogateException(int index, int length) {
            super("Unpaired surrogate at index " + index + " of " + length);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodedLength(CharSequence sequence) {
        int length = sequence.length();
        int i = 0;
        while (i < length && sequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = sequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += encodedLengthGeneral(sequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i2 + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence sequence, int start) {
        int length = sequence.length();
        int i = 0;
        while (start < length) {
            char charAt = sequence.charAt(start);
            if (charAt < 2048) {
                i += (127 - charAt) >>> 31;
            } else {
                i += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(sequence, start) < 65536) {
                        throw new UnpairedSurrogateException(start, length);
                    }
                    start++;
                }
            }
            start++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encode(CharSequence in, byte[] out, int offset, int length) {
        return processor.encodeUtf8(in, out, offset, length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(ByteBuffer buffer) {
        return processor.isValidUtf8(buffer, buffer.position(), buffer.remaining());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int partialIsValidUtf8(int state, ByteBuffer buffer, int index, int limit) {
        return processor.partialIsValidUtf8(state, buffer, index, limit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeUtf8(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
        return processor.decodeUtf8(buffer, index, size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
        return processor.decodeUtf8(bytes, index, size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void encodeUtf8(CharSequence in, ByteBuffer out) {
        processor.encodeUtf8(in, out);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer buffer, int index, int limit) {
        int i = limit - 7;
        int i2 = index;
        while (i2 < i && (buffer.getLong(i2) & ASCII_MASK_LONG) == 0) {
            i2 += 8;
        }
        return i2 - index;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static abstract class Processor {
        abstract String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException;

        abstract String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException;

        abstract int encodeUtf8(CharSequence in, byte[] out, int offset, int length);

        abstract void encodeUtf8Direct(CharSequence in, ByteBuffer out);

        abstract int partialIsValidUtf8(int state, byte[] bytes, int index, int limit);

        abstract int partialIsValidUtf8Direct(final int state, final ByteBuffer buffer, int index, final int limit);

        Processor() {
        }

        final boolean isValidUtf8(byte[] bytes, int index, int limit) {
            return partialIsValidUtf8(0, bytes, index, limit) == 0;
        }

        final boolean isValidUtf8(ByteBuffer buffer, int index, int limit) {
            return partialIsValidUtf8(0, buffer, index, limit) == 0;
        }

        final int partialIsValidUtf8(final int state, final ByteBuffer buffer, int index, final int limit) {
            if (buffer.hasArray()) {
                int arrayOffset = buffer.arrayOffset();
                return partialIsValidUtf8(state, buffer.array(), index + arrayOffset, arrayOffset + limit);
            }
            if (buffer.isDirect()) {
                return partialIsValidUtf8Direct(state, buffer, index, limit);
            }
            return partialIsValidUtf8Default(state, buffer, index, limit);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
        
            if (r8.get(r9) > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x004c, code lost:
        
            if (r8.get(r9) > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x008b, code lost:
        
            if (r8.get(r9) > (-65)) goto L53;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        final int partialIsValidUtf8Default(final int state, final ByteBuffer buffer, int index, final int limit) {
            int i;
            if (state != 0) {
                if (index >= limit) {
                    return state;
                }
                byte b = (byte) state;
                if (b < -32) {
                    if (b >= -62) {
                        i = index + 1;
                    }
                    return -1;
                }
                if (b < -16) {
                    byte b2 = (byte) (~(state >> 8));
                    if (b2 == 0) {
                        int i2 = index + 1;
                        byte b3 = buffer.get(index);
                        if (i2 >= limit) {
                            return Utf8.incompleteStateFor(b, b3);
                        }
                        index = i2;
                        b2 = b3;
                    }
                    if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                        i = index + 1;
                    }
                    return -1;
                }
                byte b4 = (byte) (~(state >> 8));
                byte b5 = 0;
                if (b4 == 0) {
                    int i3 = index + 1;
                    b4 = buffer.get(index);
                    if (i3 >= limit) {
                        return Utf8.incompleteStateFor(b, b4);
                    }
                    index = i3;
                } else {
                    b5 = (byte) (state >> 16);
                }
                if (b5 == 0) {
                    int i4 = index + 1;
                    b5 = buffer.get(index);
                    if (i4 >= limit) {
                        return Utf8.incompleteStateFor(b, b4, b5);
                    }
                    index = i4;
                }
                if (b4 <= -65 && (((b << 28) + (b4 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) == 0 && b5 <= -65) {
                    i = index + 1;
                }
                return -1;
                index = i;
            }
            return partialIsValidUtf8(buffer, index, limit);
        }

        private static int partialIsValidUtf8(final ByteBuffer buffer, int index, final int limit) {
            int estimateConsecutiveAscii = index + Utf8.estimateConsecutiveAscii(buffer, index, limit);
            while (estimateConsecutiveAscii < limit) {
                int i = estimateConsecutiveAscii + 1;
                byte b = buffer.get(estimateConsecutiveAscii);
                if (b < 0) {
                    if (b < -32) {
                        if (i >= limit) {
                            return b;
                        }
                        if (b < -62 || buffer.get(i) > -65) {
                            return -1;
                        }
                        i++;
                    } else {
                        if (b >= -16) {
                            if (i >= limit - 2) {
                                return Utf8.incompleteStateFor(buffer, b, i, limit - i);
                            }
                            int i2 = i + 1;
                            byte b2 = buffer.get(i);
                            if (b2 <= -65 && (((b << 28) + (b2 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) == 0) {
                                int i3 = i2 + 1;
                                if (buffer.get(i2) <= -65) {
                                    i = i3 + 1;
                                    if (buffer.get(i3) > -65) {
                                    }
                                }
                            }
                            return -1;
                        }
                        if (i >= limit - 1) {
                            return Utf8.incompleteStateFor(buffer, b, i, limit - i);
                        }
                        int i4 = i + 1;
                        byte b3 = buffer.get(i);
                        if (b3 > -65 || ((b == -32 && b3 < -96) || ((b == -19 && b3 >= -96) || buffer.get(i4) > -65))) {
                            return -1;
                        }
                        estimateConsecutiveAscii = i4 + 1;
                    }
                }
                estimateConsecutiveAscii = i;
            }
            return 0;
        }

        final String decodeUtf8(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            if (buffer.hasArray()) {
                return decodeUtf8(buffer.array(), buffer.arrayOffset() + index, size);
            }
            if (buffer.isDirect()) {
                return decodeUtf8Direct(buffer, index, size);
            }
            return decodeUtf8Default(buffer, index, size);
        }

        final String decodeUtf8Default(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            if ((index | size | ((buffer.limit() - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(size)));
            }
            int i = index + size;
            char[] cArr = new char[size];
            int i2 = 0;
            while (index < i) {
                byte b = buffer.get(index);
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                index++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (index < i) {
                int i4 = index + 1;
                byte b2 = buffer.get(index);
                if (DecodeUtil.isOneByte(b2)) {
                    int i5 = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (i4 < i) {
                        byte b3 = buffer.get(i4);
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i4++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                    index = i4;
                    i3 = i5;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i4 >= i) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b2, buffer.get(i4), cArr, i3);
                    index = i4 + 1;
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i4 >= i - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i6 = i4 + 1;
                    DecodeUtil.handleThreeBytes(b2, buffer.get(i4), buffer.get(i6), cArr, i3);
                    index = i6 + 1;
                    i3++;
                } else {
                    if (i4 >= i - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i7 = i4 + 1;
                    byte b4 = buffer.get(i4);
                    int i8 = i7 + 1;
                    DecodeUtil.handleFourBytes(b2, b4, buffer.get(i7), buffer.get(i8), cArr, i3);
                    index = i8 + 1;
                    i3 = i3 + 1 + 1;
                }
            }
            return new String(cArr, 0, i3);
        }

        final void encodeUtf8(CharSequence in, ByteBuffer out) {
            if (out.hasArray()) {
                int arrayOffset = out.arrayOffset();
                out.position(Utf8.encode(in, out.array(), out.position() + arrayOffset, out.remaining()) - arrayOffset);
            } else if (out.isDirect()) {
                encodeUtf8Direct(in, out);
            } else {
                encodeUtf8Default(in, out);
            }
        }

        final void encodeUtf8Default(CharSequence in, ByteBuffer out) {
            int length = in.length();
            int position = out.position();
            int i = 0;
            while (i < length) {
                try {
                    char charAt = in.charAt(i);
                    if (charAt >= 128) {
                        break;
                    }
                    out.put(position + i, (byte) charAt);
                    i++;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(i) + " at index " + (out.position() + Math.max(i, (position - out.position()) + 1)));
                }
            }
            if (i == length) {
                out.position(position + i);
                return;
            }
            position += i;
            while (i < length) {
                char charAt2 = in.charAt(i);
                if (charAt2 < 128) {
                    out.put(position, (byte) charAt2);
                } else if (charAt2 < 2048) {
                    int i2 = position + 1;
                    try {
                        out.put(position, (byte) ((charAt2 >>> 6) | 192));
                        out.put(i2, (byte) ((charAt2 & '?') | 128));
                        position = i2;
                    } catch (IndexOutOfBoundsException unused2) {
                        position = i2;
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(i) + " at index " + (out.position() + Math.max(i, (position - out.position()) + 1)));
                    }
                } else if (charAt2 < 55296 || 57343 < charAt2) {
                    int i3 = position + 1;
                    out.put(position, (byte) ((charAt2 >>> '\f') | 224));
                    position = i3 + 1;
                    out.put(i3, (byte) (((charAt2 >>> 6) & 63) | 128));
                    out.put(position, (byte) ((charAt2 & '?') | 128));
                } else {
                    int i4 = i + 1;
                    if (i4 != length) {
                        try {
                            char charAt3 = in.charAt(i4);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                int i5 = position + 1;
                                try {
                                    out.put(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                    int i6 = i5 + 1;
                                    out.put(i5, (byte) (((codePoint >>> 12) & 63) | 128));
                                    int i7 = i6 + 1;
                                    out.put(i6, (byte) (((codePoint >>> 6) & 63) | 128));
                                    out.put(i7, (byte) ((codePoint & 63) | 128));
                                    position = i7;
                                    i = i4;
                                } catch (IndexOutOfBoundsException unused3) {
                                    position = i5;
                                    i = i4;
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(i) + " at index " + (out.position() + Math.max(i, (position - out.position()) + 1)));
                                }
                            } else {
                                i = i4;
                            }
                        } catch (IndexOutOfBoundsException unused4) {
                        }
                    }
                    throw new UnpairedSurrogateException(i, length);
                }
                i++;
                position++;
            }
            out.position(position);
        }
    }

    /* loaded from: classes4.dex */
    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
        
            if (r8[r9] > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
        
            if (r8[r9] > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x007f, code lost:
        
            if (r8[r9] > (-65)) goto L53;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int partialIsValidUtf8(int state, byte[] bytes, int index, int limit) {
            int i;
            if (state != 0) {
                if (index >= limit) {
                    return state;
                }
                byte b = (byte) state;
                if (b < -32) {
                    if (b >= -62) {
                        i = index + 1;
                    }
                    return -1;
                }
                if (b < -16) {
                    byte b2 = (byte) (~(state >> 8));
                    if (b2 == 0) {
                        int i2 = index + 1;
                        byte b3 = bytes[index];
                        if (i2 >= limit) {
                            return Utf8.incompleteStateFor(b, b3);
                        }
                        index = i2;
                        b2 = b3;
                    }
                    if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                        i = index + 1;
                    }
                    return -1;
                }
                byte b4 = (byte) (~(state >> 8));
                byte b5 = 0;
                if (b4 == 0) {
                    int i3 = index + 1;
                    b4 = bytes[index];
                    if (i3 >= limit) {
                        return Utf8.incompleteStateFor(b, b4);
                    }
                    index = i3;
                } else {
                    b5 = (byte) (state >> 16);
                }
                if (b5 == 0) {
                    int i4 = index + 1;
                    b5 = bytes[index];
                    if (i4 >= limit) {
                        return Utf8.incompleteStateFor(b, b4, b5);
                    }
                    index = i4;
                }
                if (b4 <= -65 && (((b << 28) + (b4 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) == 0 && b5 <= -65) {
                    i = index + 1;
                }
                return -1;
                index = i;
            }
            return partialIsValidUtf8(bytes, index, limit);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        int partialIsValidUtf8Direct(int state, ByteBuffer buffer, int index, int limit) {
            return partialIsValidUtf8Default(state, buffer, index, limit);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
            if ((index | size | ((bytes.length - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bytes.length), Integer.valueOf(index), Integer.valueOf(size)));
            }
            int i = index + size;
            char[] cArr = new char[size];
            int i2 = 0;
            while (index < i) {
                byte b = bytes[index];
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                index++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (index < i) {
                int i4 = index + 1;
                byte b2 = bytes[index];
                if (DecodeUtil.isOneByte(b2)) {
                    int i5 = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (i4 < i) {
                        byte b3 = bytes[i4];
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i4++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                    index = i4;
                    i3 = i5;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i4 >= i) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b2, bytes[i4], cArr, i3);
                    index = i4 + 1;
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i4 >= i - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i6 = i4 + 1;
                    DecodeUtil.handleThreeBytes(b2, bytes[i4], bytes[i6], cArr, i3);
                    index = i6 + 1;
                    i3++;
                } else {
                    if (i4 >= i - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i7 = i4 + 1;
                    byte b4 = bytes[i4];
                    int i8 = i7 + 1;
                    DecodeUtil.handleFourBytes(b2, b4, bytes[i7], bytes[i8], cArr, i3);
                    index = i8 + 1;
                    i3 = i3 + 1 + 1;
                }
            }
            return new String(cArr, 0, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            return decodeUtf8Default(buffer, index, size);
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        
            return r10 + r0;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int encodeUtf8(CharSequence in, byte[] out, int offset, int length) {
            int i;
            int i2;
            int i3;
            char charAt;
            int length2 = in.length();
            int i4 = length + offset;
            int i5 = 0;
            while (i5 < length2 && (i3 = i5 + offset) < i4 && (charAt = in.charAt(i5)) < 128) {
                out[i3] = (byte) charAt;
                i5++;
            }
            int i6 = offset + i5;
            while (i5 < length2) {
                char charAt2 = in.charAt(i5);
                if (charAt2 >= 128 || i6 >= i4) {
                    if (charAt2 < 2048 && i6 <= i4 - 2) {
                        int i7 = i6 + 1;
                        out[i6] = (byte) ((charAt2 >>> 6) | 960);
                        i6 = i7 + 1;
                        out[i7] = (byte) ((charAt2 & '?') | 128);
                    } else {
                        if ((charAt2 >= 55296 && 57343 >= charAt2) || i6 > i4 - 3) {
                            if (i6 <= i4 - 4) {
                                int i8 = i5 + 1;
                                if (i8 != in.length()) {
                                    char charAt3 = in.charAt(i8);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        int i9 = i6 + 1;
                                        out[i6] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                        int i10 = i9 + 1;
                                        out[i9] = (byte) (((codePoint >>> 12) & 63) | 128);
                                        int i11 = i10 + 1;
                                        out[i10] = (byte) (((codePoint >>> 6) & 63) | 128);
                                        i6 = i11 + 1;
                                        out[i11] = (byte) ((codePoint & 63) | 128);
                                        i5 = i8;
                                    } else {
                                        i5 = i8;
                                    }
                                }
                                throw new UnpairedSurrogateException(i5 - 1, length2);
                            }
                            if (55296 <= charAt2 && charAt2 <= 57343 && ((i2 = i5 + 1) == in.length() || !Character.isSurrogatePair(charAt2, in.charAt(i2)))) {
                                throw new UnpairedSurrogateException(i5, length2);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i6);
                        }
                        int i12 = i6 + 1;
                        out[i6] = (byte) ((charAt2 >>> '\f') | 480);
                        int i13 = i12 + 1;
                        out[i12] = (byte) (((charAt2 >>> 6) & 63) | 128);
                        i = i13 + 1;
                        out[i13] = (byte) ((charAt2 & '?') | 128);
                    }
                    i5++;
                } else {
                    i = i6 + 1;
                    out[i6] = (byte) charAt2;
                }
                i6 = i;
                i5++;
            }
            return i6;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        void encodeUtf8Direct(CharSequence in, ByteBuffer out) {
            encodeUtf8Default(in, out);
        }

        private static int partialIsValidUtf8(byte[] bytes, int index, int limit) {
            while (index < limit && bytes[index] >= 0) {
                index++;
            }
            if (index >= limit) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bytes, index, limit);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bytes, int index, int limit) {
            while (index < limit) {
                int i = index + 1;
                byte b = bytes[index];
                if (b < 0) {
                    if (b < -32) {
                        if (i >= limit) {
                            return b;
                        }
                        if (b >= -62) {
                            index = i + 1;
                            if (bytes[i] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (b >= -16) {
                        if (i >= limit - 2) {
                            return Utf8.incompleteStateFor(bytes, i, limit);
                        }
                        int i2 = i + 1;
                        byte b2 = bytes[i];
                        if (b2 <= -65 && (((b << 28) + (b2 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) == 0) {
                            int i3 = i2 + 1;
                            if (bytes[i2] <= -65) {
                                i = i3 + 1;
                                if (bytes[i3] > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    if (i >= limit - 1) {
                        return Utf8.incompleteStateFor(bytes, i, limit);
                    }
                    int i4 = i + 1;
                    byte b3 = bytes[i];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        index = i4 + 1;
                        if (bytes[i4] > -65) {
                        }
                    }
                    return -1;
                }
                index = i;
            }
            return 0;
        }
    }

    /* loaded from: classes4.dex */
    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x009e, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L59;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int partialIsValidUtf8(int state, byte[] bytes, final int index, final int limit) {
            long j;
            byte b = 0;
            if ((index | limit | (bytes.length - limit)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bytes.length), Integer.valueOf(index), Integer.valueOf(limit)));
            }
            long j2 = index;
            long j3 = limit;
            if (state != 0) {
                if (j2 >= j3) {
                    return state;
                }
                byte b2 = (byte) state;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        long j4 = 1 + j2;
                        if (UnsafeUtil.getByte(bytes, j2) <= -65) {
                            j2 = j4;
                        }
                    }
                    return -1;
                }
                if (b2 < -16) {
                    byte b3 = (byte) (~(state >> 8));
                    if (b3 == 0) {
                        long j5 = j2 + 1;
                        b3 = UnsafeUtil.getByte(bytes, j2);
                        if (j5 >= j3) {
                            return Utf8.incompleteStateFor(b2, b3);
                        }
                        j2 = j5;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j = j2 + 1;
                    }
                    return -1;
                }
                byte b4 = (byte) (~(state >> 8));
                if (b4 == 0) {
                    long j6 = j2 + 1;
                    b4 = UnsafeUtil.getByte(bytes, j2);
                    if (j6 >= j3) {
                        return Utf8.incompleteStateFor(b2, b4);
                    }
                    j2 = j6;
                } else {
                    b = (byte) (state >> 16);
                }
                if (b == 0) {
                    long j7 = j2 + 1;
                    b = UnsafeUtil.getByte(bytes, j2);
                    if (j7 >= j3) {
                        return Utf8.incompleteStateFor(b2, b4, b);
                    }
                    j2 = j7;
                }
                if (b4 <= -65 && (((b2 << 28) + (b4 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) == 0 && b <= -65) {
                    j = j2 + 1;
                }
                return -1;
                j2 = j;
            }
            return partialIsValidUtf8(bytes, j2, (int) (j3 - j2));
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0063, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00a8, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L59;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int partialIsValidUtf8Direct(final int state, ByteBuffer buffer, final int index, final int limit) {
            long j;
            byte b = 0;
            if ((index | limit | (buffer.limit() - limit)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(limit)));
            }
            long addressOffset = UnsafeUtil.addressOffset(buffer) + index;
            long j2 = (limit - index) + addressOffset;
            if (state != 0) {
                if (addressOffset >= j2) {
                    return state;
                }
                byte b2 = (byte) state;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        long j3 = 1 + addressOffset;
                        if (UnsafeUtil.getByte(addressOffset) <= -65) {
                            addressOffset = j3;
                        }
                    }
                    return -1;
                }
                if (b2 < -16) {
                    byte b3 = (byte) (~(state >> 8));
                    if (b3 == 0) {
                        long j4 = addressOffset + 1;
                        b3 = UnsafeUtil.getByte(addressOffset);
                        if (j4 >= j2) {
                            return Utf8.incompleteStateFor(b2, b3);
                        }
                        addressOffset = j4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j = addressOffset + 1;
                    }
                    return -1;
                }
                byte b4 = (byte) (~(state >> 8));
                if (b4 == 0) {
                    long j5 = addressOffset + 1;
                    b4 = UnsafeUtil.getByte(addressOffset);
                    if (j5 >= j2) {
                        return Utf8.incompleteStateFor(b2, b4);
                    }
                    addressOffset = j5;
                } else {
                    b = (byte) (state >> 16);
                }
                if (b == 0) {
                    long j6 = addressOffset + 1;
                    b = UnsafeUtil.getByte(addressOffset);
                    if (j6 >= j2) {
                        return Utf8.incompleteStateFor(b2, b4, b);
                    }
                    addressOffset = j6;
                }
                if (b4 <= -65 && (((b2 << 28) + (b4 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) == 0 && b <= -65) {
                    j = addressOffset + 1;
                }
                return -1;
                addressOffset = j;
            }
            return partialIsValidUtf8(addressOffset, (int) (j2 - addressOffset));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
            String str = new String(bytes, index, size, Internal.UTF_8);
            if (str.contains("�") && !Arrays.equals(str.getBytes(Internal.UTF_8), Arrays.copyOfRange(bytes, index, size + index))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return str;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            if ((index | size | ((buffer.limit() - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(size)));
            }
            long addressOffset = UnsafeUtil.addressOffset(buffer) + index;
            long j = size + addressOffset;
            char[] cArr = new char[size];
            int i = 0;
            while (addressOffset < j) {
                byte b = UnsafeUtil.getByte(addressOffset);
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                addressOffset++;
                DecodeUtil.handleOneByte(b, cArr, i);
                i++;
            }
            while (true) {
                int i2 = i;
                while (addressOffset < j) {
                    long j2 = addressOffset + 1;
                    byte b2 = UnsafeUtil.getByte(addressOffset);
                    if (DecodeUtil.isOneByte(b2)) {
                        int i3 = i2 + 1;
                        DecodeUtil.handleOneByte(b2, cArr, i2);
                        while (j2 < j) {
                            byte b3 = UnsafeUtil.getByte(j2);
                            if (!DecodeUtil.isOneByte(b3)) {
                                break;
                            }
                            j2++;
                            DecodeUtil.handleOneByte(b3, cArr, i3);
                            i3++;
                        }
                        i2 = i3;
                        addressOffset = j2;
                    } else if (DecodeUtil.isTwoBytes(b2)) {
                        if (j2 >= j) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        addressOffset = j2 + 1;
                        DecodeUtil.handleTwoBytes(b2, UnsafeUtil.getByte(j2), cArr, i2);
                        i2++;
                    } else if (DecodeUtil.isThreeBytes(b2)) {
                        if (j2 >= j - 1) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j3 = j2 + 1;
                        DecodeUtil.handleThreeBytes(b2, UnsafeUtil.getByte(j2), UnsafeUtil.getByte(j3), cArr, i2);
                        i2++;
                        addressOffset = j3 + 1;
                    } else {
                        if (j2 >= j - 2) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j4 = j2 + 1;
                        byte b4 = UnsafeUtil.getByte(j2);
                        long j5 = j4 + 1;
                        byte b5 = UnsafeUtil.getByte(j4);
                        addressOffset = j5 + 1;
                        DecodeUtil.handleFourBytes(b2, b4, b5, UnsafeUtil.getByte(j5), cArr, i2);
                        i = i2 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        int encodeUtf8(final CharSequence in, final byte[] out, final int offset, final int length) {
            char c;
            long j;
            long j2;
            long j3;
            char c2;
            int i;
            char charAt;
            long j4 = offset;
            long j5 = length + j4;
            int length2 = in.length();
            if (length2 > length || out.length - length < offset) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(length2 - 1) + " at index " + (offset + length));
            }
            int i2 = 0;
            while (true) {
                c = 128;
                j = 1;
                if (i2 >= length2 || (charAt = in.charAt(i2)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(out, j4, (byte) charAt);
                i2++;
                j4 = 1 + j4;
            }
            if (i2 == length2) {
                return (int) j4;
            }
            while (i2 < length2) {
                char charAt2 = in.charAt(i2);
                if (charAt2 < c && j4 < j5) {
                    long j6 = j4 + j;
                    UnsafeUtil.putByte(out, j4, (byte) charAt2);
                    j3 = j;
                    j2 = j6;
                    c2 = c;
                } else if (charAt2 < 2048 && j4 <= j5 - 2) {
                    long j7 = j4 + j;
                    UnsafeUtil.putByte(out, j4, (byte) ((charAt2 >>> 6) | 960));
                    long j8 = j7 + j;
                    UnsafeUtil.putByte(out, j7, (byte) ((charAt2 & '?') | 128));
                    long j9 = j;
                    c2 = 128;
                    j2 = j8;
                    j3 = j9;
                } else {
                    if ((charAt2 >= 55296 && 57343 >= charAt2) || j4 > j5 - 3) {
                        if (j4 <= j5 - 4) {
                            int i3 = i2 + 1;
                            if (i3 != length2) {
                                char charAt3 = in.charAt(i3);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    long j10 = j4 + 1;
                                    UnsafeUtil.putByte(out, j4, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                    long j11 = j10 + 1;
                                    c2 = 128;
                                    UnsafeUtil.putByte(out, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j12 = j11 + 1;
                                    UnsafeUtil.putByte(out, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                                    j3 = 1;
                                    j2 = j12 + 1;
                                    UnsafeUtil.putByte(out, j12, (byte) ((codePoint & 63) | 128));
                                    i2 = i3;
                                } else {
                                    i2 = i3;
                                }
                            }
                            throw new UnpairedSurrogateException(i2 - 1, length2);
                        }
                        if (55296 <= charAt2 && charAt2 <= 57343 && ((i = i2 + 1) == length2 || !Character.isSurrogatePair(charAt2, in.charAt(i)))) {
                            throw new UnpairedSurrogateException(i2, length2);
                        }
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j4);
                    }
                    long j13 = j4 + j;
                    UnsafeUtil.putByte(out, j4, (byte) ((charAt2 >>> '\f') | 480));
                    long j14 = j13 + j;
                    UnsafeUtil.putByte(out, j13, (byte) (((charAt2 >>> 6) & 63) | 128));
                    UnsafeUtil.putByte(out, j14, (byte) ((charAt2 & '?') | 128));
                    j2 = j14 + 1;
                    j3 = 1;
                    c2 = 128;
                }
                i2++;
                c = c2;
                long j15 = j3;
                j4 = j2;
                j = j15;
            }
            return (int) j4;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        void encodeUtf8Direct(CharSequence in, ByteBuffer out) {
            char c;
            long j;
            int i;
            int i2;
            long j2;
            char c2;
            char charAt;
            long addressOffset = UnsafeUtil.addressOffset(out);
            long position = out.position() + addressOffset;
            long limit = out.limit() + addressOffset;
            int length = in.length();
            if (length > limit - position) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(length - 1) + " at index " + out.limit());
            }
            int i3 = 0;
            while (true) {
                c = 128;
                if (i3 >= length || (charAt = in.charAt(i3)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(position, (byte) charAt);
                i3++;
                position++;
            }
            if (i3 == length) {
                out.position((int) (position - addressOffset));
                return;
            }
            while (i3 < length) {
                char charAt2 = in.charAt(i3);
                if (charAt2 >= c || position >= limit) {
                    if (charAt2 >= 2048 || position > limit - 2) {
                        j = addressOffset;
                        if ((charAt2 >= 55296 && 57343 >= charAt2) || position > limit - 3) {
                            if (position <= limit - 4) {
                                i2 = i3 + 1;
                                if (i2 != length) {
                                    char charAt3 = in.charAt(i2);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        j2 = limit;
                                        long j3 = position + 1;
                                        UnsafeUtil.putByte(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                        long j4 = j3 + 1;
                                        c2 = 128;
                                        UnsafeUtil.putByte(j3, (byte) (((codePoint >>> 12) & 63) | 128));
                                        long j5 = j4 + 1;
                                        UnsafeUtil.putByte(j4, (byte) (((codePoint >>> 6) & 63) | 128));
                                        UnsafeUtil.putByte(j5, (byte) ((codePoint & 63) | 128));
                                        position = j5 + 1;
                                    } else {
                                        i3 = i2;
                                    }
                                }
                                throw new UnpairedSurrogateException(i3 - 1, length);
                            }
                            if (55296 <= charAt2 && charAt2 <= 57343 && ((i = i3 + 1) == length || !Character.isSurrogatePair(charAt2, in.charAt(i)))) {
                                throw new UnpairedSurrogateException(i3, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + position);
                        }
                        long j6 = position + 1;
                        UnsafeUtil.putByte(position, (byte) ((charAt2 >>> '\f') | 480));
                        long j7 = j6 + 1;
                        UnsafeUtil.putByte(j6, (byte) (((charAt2 >>> 6) & 63) | 128));
                        UnsafeUtil.putByte(j7, (byte) ((charAt2 & '?') | 128));
                        position = j7 + 1;
                    } else {
                        j = addressOffset;
                        long j8 = position + 1;
                        UnsafeUtil.putByte(position, (byte) ((charAt2 >>> 6) | 960));
                        UnsafeUtil.putByte(j8, (byte) ((charAt2 & '?') | 128));
                        position = j8 + 1;
                    }
                    j2 = limit;
                    i2 = i3;
                    c2 = 128;
                } else {
                    UnsafeUtil.putByte(position, (byte) charAt2);
                    j2 = limit;
                    i2 = i3;
                    c2 = c;
                    position++;
                    j = addressOffset;
                }
                c = c2;
                addressOffset = j;
                limit = j2;
                i3 = i2 + 1;
            }
            out.position((int) (position - addressOffset));
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bytes, long offset, final int maxChars) {
            int i = 0;
            if (maxChars < 16) {
                return 0;
            }
            int i2 = 8 - (((int) offset) & 7);
            while (i < i2) {
                long j = 1 + offset;
                if (UnsafeUtil.getByte(bytes, offset) < 0) {
                    return i;
                }
                i++;
                offset = j;
            }
            while (true) {
                int i3 = i + 8;
                if (i3 > maxChars || (UnsafeUtil.getLong((Object) bytes, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + offset) & Utf8.ASCII_MASK_LONG) != 0) {
                    break;
                }
                offset += 8;
                i = i3;
            }
            while (i < maxChars) {
                long j2 = offset + 1;
                if (UnsafeUtil.getByte(bytes, offset) < 0) {
                    return i;
                }
                i++;
                offset = j2;
            }
            return maxChars;
        }

        private static int unsafeEstimateConsecutiveAscii(long address, final int maxChars) {
            if (maxChars < 16) {
                return 0;
            }
            int i = (int) ((-address) & 7);
            int i2 = i;
            while (i2 > 0) {
                long j = 1 + address;
                if (UnsafeUtil.getByte(address) < 0) {
                    return i - i2;
                }
                i2--;
                address = j;
            }
            int i3 = maxChars - i;
            while (i3 >= 8 && (UnsafeUtil.getLong(address) & Utf8.ASCII_MASK_LONG) == 0) {
                address += 8;
                i3 -= 8;
            }
            return maxChars - i3;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x008e, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(final byte[] bytes, long offset, int remaining) {
            long j;
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bytes, offset, remaining);
            int i = remaining - unsafeEstimateConsecutiveAscii;
            long j2 = offset + unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b = 0;
                while (true) {
                    if (i <= 0) {
                        break;
                    }
                    long j3 = j2 + 1;
                    b = UnsafeUtil.getByte(bytes, j2);
                    if (b < 0) {
                        j2 = j3;
                        break;
                    }
                    i--;
                    j2 = j3;
                }
                if (i == 0) {
                    return 0;
                }
                int i2 = i - 1;
                if (b >= -32) {
                    if (b >= -16) {
                        if (i2 < 3) {
                            return unsafeIncompleteStateFor(bytes, b, j2, i2);
                        }
                        i = i2 - 3;
                        long j4 = j2 + 1;
                        byte b2 = UnsafeUtil.getByte(bytes, j2);
                        if (b2 > -65 || (((b << 28) + (b2 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) != 0) {
                            break;
                        }
                        long j5 = j4 + 1;
                        if (UnsafeUtil.getByte(bytes, j4) > -65) {
                            break;
                        }
                        j = 1 + j5;
                        if (UnsafeUtil.getByte(bytes, j5) > -65) {
                            break;
                        }
                    } else {
                        if (i2 < 2) {
                            return unsafeIncompleteStateFor(bytes, b, j2, i2);
                        }
                        i = i2 - 2;
                        long j6 = j2 + 1;
                        byte b3 = UnsafeUtil.getByte(bytes, j2);
                        if (b3 > -65 || ((b == -32 && b3 < -96) || (b == -19 && b3 >= -96))) {
                            break;
                        }
                        j = 1 + j6;
                        if (UnsafeUtil.getByte(bytes, j6) > -65) {
                            break;
                        }
                    }
                } else if (i2 != 0) {
                    i = i2 - 1;
                    if (b < -62) {
                        break;
                    }
                    j = 1 + j2;
                    if (UnsafeUtil.getByte(bytes, j2) > -65) {
                        break;
                    }
                } else {
                    return b;
                }
                j2 = j;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x008e, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(long address, int remaining) {
            long j;
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(address, remaining);
            long j2 = address + unsafeEstimateConsecutiveAscii;
            int i = remaining - unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b = 0;
                while (true) {
                    if (i <= 0) {
                        break;
                    }
                    long j3 = j2 + 1;
                    b = UnsafeUtil.getByte(j2);
                    if (b < 0) {
                        j2 = j3;
                        break;
                    }
                    i--;
                    j2 = j3;
                }
                if (i == 0) {
                    return 0;
                }
                int i2 = i - 1;
                if (b >= -32) {
                    if (b >= -16) {
                        if (i2 < 3) {
                            return unsafeIncompleteStateFor(j2, b, i2);
                        }
                        i = i2 - 3;
                        long j4 = j2 + 1;
                        byte b2 = UnsafeUtil.getByte(j2);
                        if (b2 > -65 || (((b << 28) + (b2 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) != 0) {
                            break;
                        }
                        long j5 = j4 + 1;
                        if (UnsafeUtil.getByte(j4) > -65) {
                            break;
                        }
                        j = 1 + j5;
                        if (UnsafeUtil.getByte(j5) > -65) {
                            break;
                        }
                    } else {
                        if (i2 < 2) {
                            return unsafeIncompleteStateFor(j2, b, i2);
                        }
                        i = i2 - 2;
                        long j6 = j2 + 1;
                        byte b3 = UnsafeUtil.getByte(j2);
                        if (b3 > -65 || ((b == -32 && b3 < -96) || (b == -19 && b3 >= -96))) {
                            break;
                        }
                        j = 1 + j6;
                        if (UnsafeUtil.getByte(j6) > -65) {
                            break;
                        }
                    }
                } else if (i2 != 0) {
                    i = i2 - 1;
                    if (b < -62) {
                        break;
                    }
                    j = 1 + j2;
                    if (UnsafeUtil.getByte(j2) > -65) {
                        break;
                    }
                } else {
                    return b;
                }
                j2 = j;
            }
        }

        private static int unsafeIncompleteStateFor(byte[] bytes, int byte1, long offset, int remaining) {
            if (remaining == 0) {
                return Utf8.incompleteStateFor(byte1);
            }
            if (remaining == 1) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(bytes, offset));
            }
            if (remaining == 2) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(bytes, offset), UnsafeUtil.getByte(bytes, offset + 1));
            }
            throw new AssertionError();
        }

        private static int unsafeIncompleteStateFor(long address, final int byte1, int remaining) {
            if (remaining == 0) {
                return Utf8.incompleteStateFor(byte1);
            }
            if (remaining == 1) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(address));
            }
            if (remaining == 2) {
                return Utf8.incompleteStateFor(byte1, UnsafeUtil.getByte(address), UnsafeUtil.getByte(address + 1));
            }
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class DecodeUtil {
        private static char highSurrogate(int codePoint) {
            return (char) ((codePoint >>> 10) + okio.Utf8.HIGH_SURROGATE_HEADER);
        }

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isOneByte(byte b) {
            return b >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isTwoBytes(byte b) {
            return b < -32;
        }

        private static char lowSurrogate(int codePoint) {
            return (char) ((codePoint & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
        }

        private static int trailingByteValue(byte b) {
            return b & okio.Utf8.REPLACEMENT_BYTE;
        }

        private DecodeUtil() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleOneByte(byte byte1, char[] resultArr, int resultPos) {
            resultArr[resultPos] = (char) byte1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleTwoBytes(byte byte1, byte byte2, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (byte1 < -62 || isNotTrailingByte(byte2)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            resultArr[resultPos] = (char) (((byte1 & Ascii.US) << 6) | trailingByteValue(byte2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleThreeBytes(byte byte1, byte byte2, byte byte3, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(byte2) || ((byte1 == -32 && byte2 < -96) || ((byte1 == -19 && byte2 >= -96) || isNotTrailingByte(byte3)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            resultArr[resultPos] = (char) (((byte1 & 15) << 12) | (trailingByteValue(byte2) << 6) | trailingByteValue(byte3));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleFourBytes(byte byte1, byte byte2, byte byte3, byte byte4, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(byte2) || (((byte1 << 28) + (byte2 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) != 0 || isNotTrailingByte(byte3) || isNotTrailingByte(byte4)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            int trailingByteValue = ((byte1 & 7) << 18) | (trailingByteValue(byte2) << 12) | (trailingByteValue(byte3) << 6) | trailingByteValue(byte4);
            resultArr[resultPos] = highSurrogate(trailingByteValue);
            resultArr[resultPos + 1] = lowSurrogate(trailingByteValue);
        }
    }

    private Utf8() {
    }
}
