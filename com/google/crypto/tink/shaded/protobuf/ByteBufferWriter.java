package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes4.dex */
final class ByteBufferWriter {
    private static final ThreadLocal<SoftReference<byte[]>> BUFFER = new ThreadLocal<>();
    private static final float BUFFER_REALLOCATION_THRESHOLD = 0.5f;
    private static final long CHANNEL_FIELD_OFFSET;
    private static final Class<?> FILE_OUTPUT_STREAM_CLASS;
    private static final int MAX_CACHED_BUFFER_SIZE = 16384;
    private static final int MIN_CACHED_BUFFER_SIZE = 1024;

    private static boolean needToReallocate(int requestedSize, int bufferLength) {
        return bufferLength < requestedSize && ((float) bufferLength) < ((float) requestedSize) * 0.5f;
    }

    private ByteBufferWriter() {
    }

    static {
        Class<?> safeGetClass = safeGetClass("java.io.FileOutputStream");
        FILE_OUTPUT_STREAM_CLASS = safeGetClass;
        CHANNEL_FIELD_OFFSET = getChannelFieldOffset(safeGetClass);
    }

    static void clearCachedBuffer() {
        BUFFER.set(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void write(ByteBuffer buffer, OutputStream output) throws IOException {
        int position = buffer.position();
        try {
            if (buffer.hasArray()) {
                output.write(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
            } else if (!writeToChannel(buffer, output)) {
                byte[] orCreateBuffer = getOrCreateBuffer(buffer.remaining());
                while (buffer.hasRemaining()) {
                    int min = Math.min(buffer.remaining(), orCreateBuffer.length);
                    buffer.get(orCreateBuffer, 0, min);
                    output.write(orCreateBuffer, 0, min);
                }
            }
        } finally {
            buffer.position(position);
        }
    }

    private static byte[] getOrCreateBuffer(int requestedSize) {
        int max = Math.max(requestedSize, 1024);
        byte[] buffer = getBuffer();
        if (buffer == null || needToReallocate(max, buffer.length)) {
            buffer = new byte[max];
            if (max <= 16384) {
                setBuffer(buffer);
            }
        }
        return buffer;
    }

    private static byte[] getBuffer() {
        SoftReference<byte[]> softReference = BUFFER.get();
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    private static void setBuffer(byte[] value) {
        BUFFER.set(new SoftReference<>(value));
    }

    private static boolean writeToChannel(ByteBuffer buffer, OutputStream output) throws IOException {
        long j = CHANNEL_FIELD_OFFSET;
        if (j < 0 || !FILE_OUTPUT_STREAM_CLASS.isInstance(output)) {
            return false;
        }
        WritableByteChannel writableByteChannel = null;
        try {
            writableByteChannel = (WritableByteChannel) UnsafeUtil.getObject(output, j);
        } catch (ClassCastException unused) {
        }
        if (writableByteChannel == null) {
            return false;
        }
        writableByteChannel.write(buffer);
        return true;
    }

    private static Class<?> safeGetClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private static long getChannelFieldOffset(Class<?> clazz) {
        if (clazz == null) {
            return -1L;
        }
        try {
            if (UnsafeUtil.hasUnsafeArrayOperations()) {
                return UnsafeUtil.objectFieldOffset(clazz.getDeclaredField("channel"));
            }
            return -1L;
        } catch (Throwable unused) {
            return -1L;
        }
    }
}
