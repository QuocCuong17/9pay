package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.flutter.plugins.firebase.database.Constants;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Typography;
import net.sf.scuba.smartcards.ISO7816;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.Sink;
import okio.Timeout;
import okio._UtilKt;
import okio.buffer;

/* compiled from: -RealBufferedSource.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a%\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0080\b\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0080\b\u001a\u001d\u0010\r\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0080\b\u001a-\u0010\u0011\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u0016\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u0010\u001a\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u001bH\u0080\b\u001a\r\u0010\u001c\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\u001d\u001a\u00020\u0018*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u001d\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u001e\u001a\u00020\f*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u001f\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0080\b\u001a\u001d\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010!\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010\"\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\r\u0010#\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\r\u0010$\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010%\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010&\u001a\u00020'*\u00020\u0002H\u0080\b\u001a\r\u0010(\u001a\u00020'*\u00020\u0002H\u0080\b\u001a\r\u0010)\u001a\u00020**\u00020\u0002H\u0080\b\u001a\u0015\u0010)\u001a\u00020**\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010+\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\u000f\u0010,\u001a\u0004\u0018\u00010**\u00020\u0002H\u0080\b\u001a\u0015\u0010-\u001a\u00020**\u00020\u00022\u0006\u0010.\u001a\u00020\u0006H\u0080\b\u001a\u0015\u0010/\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u00100\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u00101\u001a\u00020\u0014*\u00020\u00022\u0006\u00102\u001a\u000203H\u0080\b\u001a\u0015\u00104\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u00105\u001a\u000206*\u00020\u0002H\u0080\b\u001a\r\u00107\u001a\u00020**\u00020\u0002H\u0080\b¨\u00068"}, d2 = {"commonClose", "", "Lokio/RealBufferedSource;", "commonExhausted", "", "commonIndexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonPeek", "Lokio/BufferedSource;", "commonRangeEquals", TypedValues.CycleType.S_WAVE_OFFSET, "bytesOffset", "", "byteCount", "commonRead", "sink", "", "Lokio/Buffer;", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadIntLe", "commonReadLong", "commonReadLongLe", "commonReadShort", "", "commonReadShortLe", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", Constants.LIMIT, "commonRequest", "commonRequire", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonTimeout", "Lokio/Timeout;", "commonToString", "okio"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class _RealBufferedSourceKt {
    public static final long commonRead(buffer bufferVar, Buffer sink, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j)).toString());
        }
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (bufferVar.bufferField.size() == 0 && bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return bufferVar.bufferField.read(sink, Math.min(j, bufferVar.bufferField.size()));
    }

    public static final boolean commonExhausted(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!bufferVar.closed) {
            return bufferVar.bufferField.exhausted() && bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final void commonRequire(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!bufferVar.request(j)) {
            throw new EOFException();
        }
    }

    public static final boolean commonRequest(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j)).toString());
        }
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (bufferVar.bufferField.size() < j) {
            if (bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return false;
            }
        }
        return true;
    }

    public static final byte commonReadByte(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(1L);
        return bufferVar.bufferField.readByte();
    }

    public static final ByteString commonReadByteString(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(j);
        return bufferVar.bufferField.readByteString(j);
    }

    public static final int commonSelect(buffer bufferVar, Options options) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        do {
            int selectPrefix = _BufferKt.selectPrefix(bufferVar.bufferField, options, true);
            if (selectPrefix != -2) {
                if (selectPrefix == -1) {
                    return -1;
                }
                bufferVar.bufferField.skip(options.getByteStrings()[selectPrefix].size());
                return selectPrefix;
            }
        } while (bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return -1;
    }

    public static final byte[] commonReadByteArray(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(j);
        return bufferVar.bufferField.readByteArray(j);
    }

    public static final void commonReadFully(buffer bufferVar, byte[] sink) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            bufferVar.require(sink.length);
            bufferVar.bufferField.readFully(sink);
        } catch (EOFException e) {
            int i = 0;
            while (bufferVar.bufferField.size() > 0) {
                int read = bufferVar.bufferField.read(sink, i, (int) bufferVar.bufferField.size());
                if (read == -1) {
                    throw new AssertionError();
                }
                i += read;
            }
            throw e;
        }
    }

    public static final int commonRead(buffer bufferVar, byte[] sink, int i, int i2) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j = i2;
        _UtilKt.checkOffsetAndCount(sink.length, i, j);
        if (bufferVar.bufferField.size() == 0 && bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return bufferVar.bufferField.read(sink, i, (int) Math.min(j, bufferVar.bufferField.size()));
    }

    public static final void commonReadFully(buffer bufferVar, Buffer sink, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            bufferVar.require(j);
            bufferVar.bufferField.readFully(sink, j);
        } catch (EOFException e) {
            sink.writeAll(bufferVar.bufferField);
            throw e;
        }
    }

    public static final long commonReadAll(buffer bufferVar, Sink sink) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j = 0;
        while (bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
            long completeSegmentByteCount = bufferVar.bufferField.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                j += completeSegmentByteCount;
                sink.write(bufferVar.bufferField, completeSegmentByteCount);
            }
        }
        if (bufferVar.bufferField.size() <= 0) {
            return j;
        }
        long size = j + bufferVar.bufferField.size();
        sink.write(bufferVar.bufferField, bufferVar.bufferField.size());
        return size;
    }

    public static final String commonReadUtf8(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(j);
        return bufferVar.bufferField.readUtf8(j);
    }

    public static final String commonReadUtf8Line(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        long indexOf = bufferVar.indexOf((byte) 10);
        if (indexOf != -1) {
            return _BufferKt.readUtf8Line(bufferVar.bufferField, indexOf);
        }
        if (bufferVar.bufferField.size() != 0) {
            return bufferVar.readUtf8(bufferVar.bufferField.size());
        }
        return null;
    }

    public static final String commonReadUtf8LineStrict(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("limit < 0: ", Long.valueOf(j)).toString());
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        byte b = (byte) 10;
        long indexOf = bufferVar.indexOf(b, 0L, j2);
        if (indexOf != -1) {
            return _BufferKt.readUtf8Line(bufferVar.bufferField, indexOf);
        }
        if (j2 < Long.MAX_VALUE && bufferVar.request(j2) && bufferVar.bufferField.getByte(j2 - 1) == ((byte) 13) && bufferVar.request(1 + j2) && bufferVar.bufferField.getByte(j2) == b) {
            return _BufferKt.readUtf8Line(bufferVar.bufferField, j2);
        }
        Buffer buffer = new Buffer();
        bufferVar.bufferField.copyTo(buffer, 0L, Math.min(32, bufferVar.bufferField.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(bufferVar.bufferField.size(), j) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
    }

    public static final int commonReadUtf8CodePoint(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(1L);
        byte b = bufferVar.bufferField.getByte(0L);
        if ((b & ISO7816.INS_CREATE_FILE) == 192) {
            bufferVar.require(2L);
        } else if ((b & 240) == 224) {
            bufferVar.require(3L);
        } else if ((b & 248) == 240) {
            bufferVar.require(4L);
        }
        return bufferVar.bufferField.readUtf8CodePoint();
    }

    public static final short commonReadShort(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(2L);
        return bufferVar.bufferField.readShort();
    }

    public static final short commonReadShortLe(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(2L);
        return bufferVar.bufferField.readShortLe();
    }

    public static final int commonReadInt(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(4L);
        return bufferVar.bufferField.readInt();
    }

    public static final int commonReadIntLe(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(4L);
        return bufferVar.bufferField.readIntLe();
    }

    public static final long commonReadLong(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(8L);
        return bufferVar.bufferField.readLong();
    }

    public static final long commonReadLongLe(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(8L);
        return bufferVar.bufferField.readLongLe();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r4 == 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
    
        r0 = java.lang.Integer.toString(r8, kotlin.text.CharsKt.checkRadix(kotlin.text.CharsKt.checkRadix(16)));
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "java.lang.Integer.toStri…(this, checkRadix(radix))");
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
    
        throw new java.lang.NumberFormatException(kotlin.jvm.internal.Intrinsics.stringPlus("Expected a digit or '-' but was 0x", r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final long commonReadDecimalLong(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(1L);
        long j = 0;
        while (true) {
            long j2 = j + 1;
            if (!bufferVar.request(j2)) {
                break;
            }
            byte b = bufferVar.bufferField.getByte(j);
            if ((b < ((byte) 48) || b > ((byte) 57)) && !(j == 0 && b == ((byte) 45))) {
                break;
            }
            j = j2;
        }
        return bufferVar.bufferField.readDecimalLong();
    }

    public static final long commonReadHexadecimalUnsignedLong(buffer bufferVar) {
        byte b;
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.require(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!bufferVar.request(i2)) {
                break;
            }
            b = bufferVar.bufferField.getByte(i);
            if ((b < ((byte) 48) || b > ((byte) 57)) && ((b < ((byte) 97) || b > ((byte) 102)) && (b < ((byte) 65) || b > ((byte) 70)))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            String num = Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            Intrinsics.checkNotNullExpressionValue(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
            throw new NumberFormatException(Intrinsics.stringPlus("Expected leading [0-9a-fA-F] character but was 0x", num));
        }
        return bufferVar.bufferField.readHexadecimalUnsignedLong();
    }

    public static final void commonSkip(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j > 0) {
            if (bufferVar.bufferField.size() == 0 && bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, bufferVar.bufferField.size());
            bufferVar.bufferField.skip(min);
            j -= min;
        }
    }

    public static final long commonIndexOf(buffer bufferVar, byte b, long j, long j2) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (!(0 <= j && j <= j2)) {
            throw new IllegalArgumentException(("fromIndex=" + j + " toIndex=" + j2).toString());
        }
        while (j < j2) {
            long indexOf = bufferVar.bufferField.indexOf(b, j, j2);
            if (indexOf == -1) {
                long size = bufferVar.bufferField.size();
                if (size >= j2 || bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    break;
                }
                j = Math.max(j, size);
            } else {
                return indexOf;
            }
        }
        return -1L;
    }

    public static final long commonIndexOf(buffer bufferVar, ByteString bytes, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            long indexOf = bufferVar.bufferField.indexOf(bytes, j);
            if (indexOf != -1) {
                return indexOf;
            }
            long size = bufferVar.bufferField.size();
            if (bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, (size - bytes.size()) + 1);
        }
    }

    public static final long commonIndexOfElement(buffer bufferVar, ByteString targetBytes, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            long indexOfElement = bufferVar.bufferField.indexOfElement(targetBytes, j);
            if (indexOfElement != -1) {
                return indexOfElement;
            }
            long size = bufferVar.bufferField.size();
            if (bufferVar.source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, size);
        }
    }

    public static final boolean commonRangeEquals(buffer bufferVar, long j, ByteString bytes, int i, int i2) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (j < 0 || i < 0 || i2 < 0 || bytes.size() - i < i2) {
            return false;
        }
        if (i2 > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                long j2 = i3 + j;
                if (!bufferVar.request(1 + j2) || bufferVar.bufferField.getByte(j2) != bytes.getByte(i3 + i)) {
                    return false;
                }
                if (i4 >= i2) {
                    break;
                }
                i3 = i4;
            }
        }
        return true;
    }

    public static final BufferedSource commonPeek(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        return Okio.buffer(new PeekSource(bufferVar));
    }

    public static final void commonClose(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (bufferVar.closed) {
            return;
        }
        bufferVar.closed = true;
        bufferVar.source.close();
        bufferVar.bufferField.clear();
    }

    public static final Timeout commonTimeout(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        return bufferVar.source.getTimeout();
    }

    public static final String commonToString(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        return "buffer(" + bufferVar.source + ')';
    }

    public static final ByteString commonReadByteString(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.bufferField.writeAll(bufferVar.source);
        return bufferVar.bufferField.readByteString();
    }

    public static final byte[] commonReadByteArray(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.bufferField.writeAll(bufferVar.source);
        return bufferVar.bufferField.readByteArray();
    }

    public static final String commonReadUtf8(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        bufferVar.bufferField.writeAll(bufferVar.source);
        return bufferVar.bufferField.readUtf8();
    }
}
