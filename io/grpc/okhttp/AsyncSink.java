package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class AsyncSink implements Sink {
    private boolean controlFramesExceeded;
    private int controlFramesInWrite;
    private final int maxQueuedControlFrames;
    private int queuedControlFrames;
    private final SerializingExecutor serializingExecutor;

    @Nullable
    private Sink sink;

    @Nullable
    private Socket socket;
    private final ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler;
    private final Object lock = new Object();
    private final Buffer buffer = new Buffer();
    private boolean writeEnqueued = false;
    private boolean flushEnqueued = false;
    private boolean closed = false;

    static /* synthetic */ int access$420(AsyncSink asyncSink, int i) {
        int i2 = asyncSink.queuedControlFrames - i;
        asyncSink.queuedControlFrames = i2;
        return i2;
    }

    static /* synthetic */ int access$908(AsyncSink asyncSink) {
        int i = asyncSink.controlFramesInWrite;
        asyncSink.controlFramesInWrite = i + 1;
        return i;
    }

    private AsyncSink(SerializingExecutor serializingExecutor, ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler, int i) {
        this.serializingExecutor = (SerializingExecutor) Preconditions.checkNotNull(serializingExecutor, "executor");
        this.transportExceptionHandler = (ExceptionHandlingFrameWriter.TransportExceptionHandler) Preconditions.checkNotNull(transportExceptionHandler, "exceptionHandler");
        this.maxQueuedControlFrames = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AsyncSink sink(SerializingExecutor serializingExecutor, ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler, int i) {
        return new AsyncSink(serializingExecutor, transportExceptionHandler, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void becomeConnected(Sink sink, Socket socket) {
        Preconditions.checkState(this.sink == null, "AsyncSink's becomeConnected should only be called once.");
        this.sink = (Sink) Preconditions.checkNotNull(sink, "sink");
        this.socket = (Socket) Preconditions.checkNotNull(socket, "socket");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameWriter limitControlFramesWriter(FrameWriter frameWriter) {
        return new LimitControlFramesWriter(frameWriter);
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        Preconditions.checkNotNull(buffer, "source");
        if (this.closed) {
            throw new IOException("closed");
        }
        PerfMark.startTask("AsyncSink.write");
        try {
            synchronized (this.lock) {
                this.buffer.write(buffer, j);
                int i = this.queuedControlFrames + this.controlFramesInWrite;
                this.queuedControlFrames = i;
                boolean z = false;
                this.controlFramesInWrite = 0;
                if (!this.controlFramesExceeded && i > this.maxQueuedControlFrames) {
                    this.controlFramesExceeded = true;
                    z = true;
                } else {
                    if (!this.writeEnqueued && !this.flushEnqueued && this.buffer.completeSegmentByteCount() > 0) {
                        this.writeEnqueued = true;
                    }
                    return;
                }
                if (z) {
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        this.transportExceptionHandler.onException(e);
                    }
                    return;
                }
                this.serializingExecutor.execute(new WriteRunnable() { // from class: io.grpc.okhttp.AsyncSink.1
                    final Link link = PerfMark.linkOut();

                    @Override // io.grpc.okhttp.AsyncSink.WriteRunnable
                    public void doRun() throws IOException {
                        int i2;
                        PerfMark.startTask("WriteRunnable.runWrite");
                        PerfMark.linkIn(this.link);
                        Buffer buffer2 = new Buffer();
                        try {
                            synchronized (AsyncSink.this.lock) {
                                buffer2.write(AsyncSink.this.buffer, AsyncSink.this.buffer.completeSegmentByteCount());
                                AsyncSink.this.writeEnqueued = false;
                                i2 = AsyncSink.this.queuedControlFrames;
                            }
                            AsyncSink.this.sink.write(buffer2, buffer2.size());
                            synchronized (AsyncSink.this.lock) {
                                AsyncSink.access$420(AsyncSink.this, i2);
                            }
                        } finally {
                            PerfMark.stopTask("WriteRunnable.runWrite");
                        }
                    }
                });
            }
        } finally {
            PerfMark.stopTask("AsyncSink.write");
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        PerfMark.startTask("AsyncSink.flush");
        try {
            synchronized (this.lock) {
                if (this.flushEnqueued) {
                    return;
                }
                this.flushEnqueued = true;
                this.serializingExecutor.execute(new WriteRunnable() { // from class: io.grpc.okhttp.AsyncSink.2
                    final Link link = PerfMark.linkOut();

                    @Override // io.grpc.okhttp.AsyncSink.WriteRunnable
                    public void doRun() throws IOException {
                        PerfMark.startTask("WriteRunnable.runFlush");
                        PerfMark.linkIn(this.link);
                        Buffer buffer = new Buffer();
                        try {
                            synchronized (AsyncSink.this.lock) {
                                buffer.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                                AsyncSink.this.flushEnqueued = false;
                            }
                            AsyncSink.this.sink.write(buffer, buffer.size());
                            AsyncSink.this.sink.flush();
                        } finally {
                            PerfMark.stopTask("WriteRunnable.runFlush");
                        }
                    }
                });
            }
        } finally {
            PerfMark.stopTask("AsyncSink.flush");
        }
    }

    @Override // okio.Sink
    /* renamed from: timeout */
    public Timeout getTimeout() {
        return Timeout.NONE;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.serializingExecutor.execute(new Runnable() { // from class: io.grpc.okhttp.AsyncSink.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AsyncSink.this.sink != null && AsyncSink.this.buffer.size() > 0) {
                        AsyncSink.this.sink.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                    }
                } catch (IOException e) {
                    AsyncSink.this.transportExceptionHandler.onException(e);
                }
                AsyncSink.this.buffer.close();
                try {
                    if (AsyncSink.this.sink != null) {
                        AsyncSink.this.sink.close();
                    }
                } catch (IOException e2) {
                    AsyncSink.this.transportExceptionHandler.onException(e2);
                }
                try {
                    if (AsyncSink.this.socket != null) {
                        AsyncSink.this.socket.close();
                    }
                } catch (IOException e3) {
                    AsyncSink.this.transportExceptionHandler.onException(e3);
                }
            }
        });
    }

    /* loaded from: classes5.dex */
    private abstract class WriteRunnable implements Runnable {
        public abstract void doRun() throws IOException;

        private WriteRunnable() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (AsyncSink.this.sink == null) {
                    throw new IOException("Unable to perform write due to unavailable sink.");
                }
                doRun();
            } catch (Exception e) {
                AsyncSink.this.transportExceptionHandler.onException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class LimitControlFramesWriter extends ForwardingFrameWriter {
        public LimitControlFramesWriter(FrameWriter frameWriter) {
            super(frameWriter);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public void ackSettings(Settings settings) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.ackSettings(settings);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public void rstStream(int i, ErrorCode errorCode) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.rstStream(i, errorCode);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public void ping(boolean z, int i, int i2) throws IOException {
            if (z) {
                AsyncSink.access$908(AsyncSink.this);
            }
            super.ping(z, i, i2);
        }
    }
}
