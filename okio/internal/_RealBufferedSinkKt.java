package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.flutter.plugin.editing.SpellCheckPlugin;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import okio.buffer;

/* compiled from: -RealBufferedSink.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0005\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\t\u001a\u00020\n*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u0016\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0015H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010!\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010#\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\nH\u0080\b\u001a%\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010(\u001a\u00020\u0004*\u00020\u00022\u0006\u0010)\u001a\u00020\u000fH\u0080\b¨\u0006*"}, d2 = {"commonClose", "", "Lokio/RealBufferedSink;", "commonEmit", "Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "commonTimeout", "Lokio/Timeout;", "commonToString", "", "commonWrite", "source", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "byteCount", "Lokio/Buffer;", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "b", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "commonWriteShort", CmcdData.Factory.STREAMING_FORMAT_SS, "commonWriteShortLe", "commonWriteUtf8", "string", "beginIndex", SpellCheckPlugin.END_INDEX_KEY, "commonWriteUtf8CodePoint", "codePoint", "okio"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class _RealBufferedSinkKt {
    public static final void commonWrite(buffer bufferVar, Buffer source, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.write(source, j);
        bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(buffer bufferVar, ByteString byteString) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.write(byteString);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(buffer bufferVar, ByteString byteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.write(byteString, i, i2);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(buffer bufferVar, String string) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeUtf8(string);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(buffer bufferVar, String string, int i, int i2) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeUtf8(string, i, i2);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8CodePoint(buffer bufferVar, int i) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeUtf8CodePoint(i);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(buffer bufferVar, byte[] source) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.write(source);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(buffer bufferVar, byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.write(source, i, i2);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteByte(buffer bufferVar, int i) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeByte(i);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShort(buffer bufferVar, int i) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeShort(i);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShortLe(buffer bufferVar, int i) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeShortLe(i);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteInt(buffer bufferVar, int i) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeInt(i);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteIntLe(buffer bufferVar, int i) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeIntLe(i);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLong(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeLong(j);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLongLe(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeLongLe(j);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteDecimalLong(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeDecimalLong(j);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteHexadecimalUnsignedLong(buffer bufferVar, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        bufferVar.bufferField.writeHexadecimalUnsignedLong(j);
        return bufferVar.emitCompleteSegments();
    }

    public static final BufferedSink commonEmitCompleteSegments(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long completeSegmentByteCount = bufferVar.bufferField.completeSegmentByteCount();
        if (completeSegmentByteCount > 0) {
            bufferVar.sink.write(bufferVar.bufferField, completeSegmentByteCount);
        }
        return bufferVar;
    }

    public static final BufferedSink commonEmit(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long size = bufferVar.bufferField.size();
        if (size > 0) {
            bufferVar.sink.write(bufferVar.bufferField, size);
        }
        return bufferVar;
    }

    public static final void commonFlush(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (!(!bufferVar.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (bufferVar.bufferField.size() > 0) {
            bufferVar.sink.write(bufferVar.bufferField, bufferVar.bufferField.size());
        }
        bufferVar.sink.flush();
    }

    public static final void commonClose(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        if (bufferVar.closed) {
            return;
        }
        Throwable th = null;
        try {
            if (bufferVar.bufferField.size() > 0) {
                bufferVar.sink.write(bufferVar.bufferField, bufferVar.bufferField.size());
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferVar.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        bufferVar.closed = true;
        if (th != null) {
            throw th;
        }
    }

    public static final Timeout commonTimeout(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        return bufferVar.sink.getTimeout();
    }

    public static final String commonToString(buffer bufferVar) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        return "buffer(" + bufferVar.sink + ')';
    }

    public static final long commonWriteAll(buffer bufferVar, Source source) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(bufferVar.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
            bufferVar.emitCompleteSegments();
        }
    }

    public static final BufferedSink commonWrite(buffer bufferVar, Source source, long j) {
        Intrinsics.checkNotNullParameter(bufferVar, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        while (j > 0) {
            long read = source.read(bufferVar.bufferField, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
            bufferVar.emitCompleteSegments();
        }
        return bufferVar;
    }
}
