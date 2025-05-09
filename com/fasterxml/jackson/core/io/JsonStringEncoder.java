package com.fasterxml.jackson.core.io;

import androidx.media3.extractor.ts.PsExtractor;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;

/* loaded from: classes2.dex */
public final class JsonStringEncoder {
    static final int MAX_BYTE_BUFFER_SIZE = 32000;
    static final int MAX_CHAR_BUFFER_SIZE = 32000;
    static final int MIN_BYTE_BUFFER_SIZE = 24;
    static final int MIN_CHAR_BUFFER_SIZE = 16;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private char[] _qbuf() {
        return new char[]{IOUtils.DIR_SEPARATOR_WINDOWS, 0, '0', '0'};
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r2[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r10 >= 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
    
        r10 = r8 + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
    
        if (r10 <= r1.length) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        r10 = r1.length - r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
    
        if (r10 <= 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
    
        java.lang.System.arraycopy(r6, 0, r1, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        if (r4 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0047, code lost:
    
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        r1 = r4.finishCurrentSegment();
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r1, 0, r7);
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0055, code lost:
    
        java.lang.System.arraycopy(r6, 0, r1, r8, r7);
        r8 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0035, code lost:
    
        r7 = _appendNamed(r10, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
    
        if (r6 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        r6 = _qbuf();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public char[] quoteAsString(String str) {
        int i;
        int length = str.length();
        char[] cArr = new char[_initialCharBufSize(length)];
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length2 = iArr.length;
        TextBuffer textBuffer = null;
        char[] cArr2 = null;
        int i2 = 0;
        int i3 = 0;
        loop0: while (true) {
            if (i2 >= length) {
                break;
            }
            while (true) {
                char charAt = str.charAt(i2);
                if (charAt < length2 && iArr[charAt] != 0) {
                    break;
                }
                if (i3 >= cArr.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(cArr);
                    }
                    cArr = textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                int i4 = i3 + 1;
                cArr[i3] = charAt;
                i2++;
                if (i2 >= length) {
                    i3 = i4;
                    break loop0;
                }
                i3 = i4;
            }
            i2 = i;
        }
        if (textBuffer == null) {
            return Arrays.copyOfRange(cArr, 0, i3);
        }
        textBuffer.setCurrentLength(i3);
        return textBuffer.contentsAsArray();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        if (r6 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        r6 = _qbuf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r2[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
    
        if (r10 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
    
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
    
        r10 = r8 + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0047, code lost:
    
        if (r10 <= r1.length) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0049, code lost:
    
        r10 = r1.length - r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
    
        if (r10 <= 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004d, code lost:
    
        java.lang.System.arraycopy(r6, 0, r1, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0050, code lost:
    
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
    
        r1 = r4.finishCurrentSegment();
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r1, 0, r7);
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
    
        java.lang.System.arraycopy(r6, 0, r1, r8, r7);
        r8 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0040, code lost:
    
        r7 = _appendNamed(r10, r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public char[] quoteAsString(CharSequence charSequence) {
        int i;
        if (charSequence instanceof String) {
            return quoteAsString((String) charSequence);
        }
        int length = charSequence.length();
        char[] cArr = new char[_initialCharBufSize(length)];
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length2 = iArr.length;
        TextBuffer textBuffer = null;
        char[] cArr2 = null;
        int i2 = 0;
        int i3 = 0;
        loop0: while (true) {
            if (i2 >= length) {
                break;
            }
            while (true) {
                char charAt = charSequence.charAt(i2);
                if (charAt < length2 && iArr[charAt] != 0) {
                    break;
                }
                if (i3 >= cArr.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(cArr);
                    }
                    cArr = textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                int i4 = i3 + 1;
                cArr[i3] = charAt;
                i2++;
                if (i2 >= length) {
                    i3 = i4;
                    break loop0;
                }
                i3 = i4;
            }
            i2 = i;
        }
        if (textBuffer == null) {
            return Arrays.copyOfRange(cArr, 0, i3);
        }
        textBuffer.setCurrentLength(i3);
        return textBuffer.contentsAsArray();
    }

    public void quoteAsString(CharSequence charSequence, StringBuilder sb) {
        int _appendNamed;
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = charSequence.length();
        char[] cArr = null;
        int i = 0;
        while (i < length2) {
            do {
                char charAt = charSequence.charAt(i);
                if (charAt >= length || iArr[charAt] == 0) {
                    sb.append(charAt);
                    i++;
                } else {
                    if (cArr == null) {
                        cArr = _qbuf();
                    }
                    int i2 = i + 1;
                    char charAt2 = charSequence.charAt(i);
                    int i3 = iArr[charAt2];
                    if (i3 < 0) {
                        _appendNamed = _appendNumeric(charAt2, cArr);
                    } else {
                        _appendNamed = _appendNamed(i3, cArr);
                    }
                    sb.append(cArr, 0, _appendNamed);
                    i = i2;
                }
            } while (i < length2);
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        int i3;
        int length = str.length();
        byte[] bArr = new byte[_initialByteBufSize(length)];
        ByteArrayBuilder byteArrayBuilder = null;
        int i4 = 0;
        int i5 = 0;
        loop0: while (true) {
            if (i4 >= length) {
                break;
            }
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char charAt = str.charAt(i4);
                if (charAt > 127 || iArr[charAt] != 0) {
                    break;
                }
                if (i5 >= bArr.length) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i5);
                    }
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i5 = 0;
                }
                int i6 = i5 + 1;
                bArr[i5] = (byte) charAt;
                i4++;
                if (i4 >= length) {
                    i5 = i6;
                    break loop0;
                }
                i5 = i6;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i5);
            }
            if (i5 >= bArr.length) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i5 = 0;
            }
            int i7 = i4 + 1;
            char charAt2 = str.charAt(i4);
            if (charAt2 <= 127) {
                i5 = _appendByte(charAt2, iArr[charAt2], byteArrayBuilder, i5);
                bArr = byteArrayBuilder.getCurrentSegment();
                i4 = i7;
            } else {
                if (charAt2 <= 2047) {
                    i2 = i5 + 1;
                    bArr[i5] = (byte) ((charAt2 >> 6) | 192);
                    i = (charAt2 & '?') | 128;
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    int i8 = i5 + 1;
                    bArr[i5] = (byte) ((charAt2 >> '\f') | 224);
                    if (i8 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i8 = 0;
                    }
                    bArr[i8] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i = (charAt2 & '?') | 128;
                    i2 = i8 + 1;
                } else {
                    if (charAt2 > 56319) {
                        _illegal(charAt2);
                    }
                    if (i7 >= length) {
                        _illegal(charAt2);
                    }
                    int i9 = i7 + 1;
                    int _convert = _convert(charAt2, str.charAt(i7));
                    if (_convert > 1114111) {
                        _illegal(_convert);
                    }
                    int i10 = i5 + 1;
                    bArr[i5] = (byte) ((_convert >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                    if (i10 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i10 = 0;
                    }
                    int i11 = i10 + 1;
                    bArr[i10] = (byte) (((_convert >> 12) & 63) | 128);
                    if (i11 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i11 = 0;
                    }
                    int i12 = i11 + 1;
                    bArr[i11] = (byte) (((_convert >> 6) & 63) | 128);
                    i3 = (_convert & 63) | 128;
                    i4 = i9;
                    i2 = i12;
                    if (i2 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i2 = 0;
                    }
                    bArr[i2] = (byte) i3;
                    i5 = i2 + 1;
                }
                i3 = i;
                i4 = i7;
                if (i2 >= bArr.length) {
                }
                bArr[i2] = (byte) i3;
                i5 = i2 + 1;
            }
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr, 0, i5);
        }
        return byteArrayBuilder.completeAndCoalesce(i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] encodeAsUTF8(String str) {
        int i;
        int i2;
        int length = str.length();
        int _initialByteBufSize = _initialByteBufSize(length);
        byte[] bArr = new byte[_initialByteBufSize];
        ByteArrayBuilder byteArrayBuilder = null;
        int i3 = 0;
        int i4 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int i5 = i3 + 1;
            int charAt = str.charAt(i3);
            while (charAt <= 127) {
                if (i4 >= _initialByteBufSize) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i4);
                    }
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i4 = 0;
                    bArr = finishCurrentSegment;
                    _initialByteBufSize = finishCurrentSegment.length;
                }
                int i6 = i4 + 1;
                bArr[i4] = (byte) charAt;
                if (i5 >= length) {
                    i4 = i6;
                    break loop0;
                }
                int charAt2 = str.charAt(i5);
                i5++;
                charAt = charAt2;
                i4 = i6;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i4);
            }
            if (i4 >= _initialByteBufSize) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                _initialByteBufSize = bArr.length;
                i4 = 0;
            }
            if (charAt < 2048) {
                i = i4 + 1;
                bArr[i4] = (byte) ((charAt >> 6) | 192);
            } else if (charAt < 55296 || charAt > 57343) {
                int i7 = i4 + 1;
                bArr[i4] = (byte) ((charAt >> 12) | 224);
                if (i7 >= _initialByteBufSize) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    _initialByteBufSize = bArr.length;
                    i7 = 0;
                }
                bArr[i7] = (byte) (((charAt >> 6) & 63) | 128);
                i = i7 + 1;
            } else {
                if (charAt > 56319) {
                    _illegal(charAt);
                }
                if (i5 >= length) {
                    _illegal(charAt);
                }
                int i8 = i5 + 1;
                int _convert = _convert(charAt, str.charAt(i5));
                if (_convert > 1114111) {
                    _illegal(_convert);
                }
                int i9 = i4 + 1;
                bArr[i4] = (byte) ((_convert >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                if (i9 >= _initialByteBufSize) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    _initialByteBufSize = bArr.length;
                    i9 = 0;
                }
                int i10 = i9 + 1;
                bArr[i9] = (byte) (((_convert >> 12) & 63) | 128);
                if (i10 >= _initialByteBufSize) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i10 = 0;
                    bArr = finishCurrentSegment2;
                    _initialByteBufSize = finishCurrentSegment2.length;
                }
                int i11 = i10 + 1;
                bArr[i10] = (byte) (((_convert >> 6) & 63) | 128);
                i2 = _convert;
                i3 = i8;
                i = i11;
                if (i < _initialByteBufSize) {
                    byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                    i = 0;
                    bArr = finishCurrentSegment3;
                    _initialByteBufSize = finishCurrentSegment3.length;
                }
                bArr[i] = (byte) ((i2 & 63) | 128);
                i4 = i + 1;
            }
            i2 = charAt;
            i3 = i5;
            if (i < _initialByteBufSize) {
            }
            bArr[i] = (byte) ((i2 & 63) | 128);
            i4 = i + 1;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr, 0, i4);
        }
        return byteArrayBuilder.completeAndCoalesce(i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] encodeAsUTF8(CharSequence charSequence) {
        int i;
        int i2;
        int length = charSequence.length();
        int _initialByteBufSize = _initialByteBufSize(length);
        byte[] bArr = new byte[_initialByteBufSize];
        ByteArrayBuilder byteArrayBuilder = null;
        int i3 = 0;
        int i4 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int i5 = i3 + 1;
            int charAt = charSequence.charAt(i3);
            while (charAt <= 127) {
                if (i4 >= _initialByteBufSize) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i4);
                    }
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i4 = 0;
                    bArr = finishCurrentSegment;
                    _initialByteBufSize = finishCurrentSegment.length;
                }
                int i6 = i4 + 1;
                bArr[i4] = (byte) charAt;
                if (i5 >= length) {
                    i4 = i6;
                    break loop0;
                }
                int charAt2 = charSequence.charAt(i5);
                i5++;
                charAt = charAt2;
                i4 = i6;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i4);
            }
            if (i4 >= _initialByteBufSize) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                _initialByteBufSize = bArr.length;
                i4 = 0;
            }
            if (charAt < 2048) {
                i = i4 + 1;
                bArr[i4] = (byte) ((charAt >> 6) | 192);
            } else if (charAt < 55296 || charAt > 57343) {
                int i7 = i4 + 1;
                bArr[i4] = (byte) ((charAt >> 12) | 224);
                if (i7 >= _initialByteBufSize) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    _initialByteBufSize = bArr.length;
                    i7 = 0;
                }
                bArr[i7] = (byte) (((charAt >> 6) & 63) | 128);
                i = i7 + 1;
            } else {
                if (charAt > 56319) {
                    _illegal(charAt);
                }
                if (i5 >= length) {
                    _illegal(charAt);
                }
                int i8 = i5 + 1;
                int _convert = _convert(charAt, charSequence.charAt(i5));
                if (_convert > 1114111) {
                    _illegal(_convert);
                }
                int i9 = i4 + 1;
                bArr[i4] = (byte) ((_convert >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                if (i9 >= _initialByteBufSize) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    _initialByteBufSize = bArr.length;
                    i9 = 0;
                }
                int i10 = i9 + 1;
                bArr[i9] = (byte) (((_convert >> 12) & 63) | 128);
                if (i10 >= _initialByteBufSize) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i10 = 0;
                    bArr = finishCurrentSegment2;
                    _initialByteBufSize = finishCurrentSegment2.length;
                }
                int i11 = i10 + 1;
                bArr[i10] = (byte) (((_convert >> 6) & 63) | 128);
                i2 = _convert;
                i3 = i8;
                i = i11;
                if (i < _initialByteBufSize) {
                    byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                    i = 0;
                    bArr = finishCurrentSegment3;
                    _initialByteBufSize = finishCurrentSegment3.length;
                }
                bArr[i] = (byte) ((i2 & 63) | 128);
                i4 = i + 1;
            }
            i2 = charAt;
            i3 = i5;
            if (i < _initialByteBufSize) {
            }
            bArr[i] = (byte) ((i2 & 63) | 128);
            i4 = i + 1;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr, 0, i4);
        }
        return byteArrayBuilder.completeAndCoalesce(i4);
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byte[] bArr = HB;
                byteArrayBuilder.append(bArr[i4 >> 4]);
                byteArrayBuilder.append(bArr[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byte[] bArr2 = HB;
            byteArrayBuilder.append(bArr2[i >> 4]);
            byteArrayBuilder.append(bArr2[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private static int _convert(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + 65536 + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }

    static int _initialCharBufSize(int i) {
        return Math.min(Math.max(16, i + Math.min((i >> 3) + 6, 1000)), 32000);
    }

    static int _initialByteBufSize(int i) {
        return Math.min(Math.max(24, i + 6 + (i >> 1)), 32000);
    }
}
