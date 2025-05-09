package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractServerStream;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.okhttp.OkHttpServerStream;
import io.grpc.okhttp.OkHttpServerTransport;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.sentry.protocol.SentryStackFrame;
import java.util.List;
import okio.Buffer;

/* loaded from: classes5.dex */
class OkHttpServerStream extends AbstractServerStream {
    private final Attributes attributes;
    private final String authority;
    private final Sink sink;
    private final TransportState state;
    private final TransportTracer transportTracer;

    public OkHttpServerStream(TransportState transportState, Attributes attributes, String str, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
        super(new OkHttpWritableBufferAllocator(), statsTraceContext);
        this.sink = new Sink();
        this.state = (TransportState) Preconditions.checkNotNull(transportState, "state");
        this.attributes = (Attributes) Preconditions.checkNotNull(attributes, "transportAttrs");
        this.authority = str;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.AbstractStream
    public TransportState transportState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractServerStream
    public Sink abstractServerStreamSink() {
        return this.sink;
    }

    @Override // io.grpc.internal.ServerStream
    public int streamId() {
        return this.state.streamId;
    }

    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.ServerStream
    public String getAuthority() {
        return this.authority;
    }

    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.ServerStream
    public Attributes getAttributes() {
        return this.attributes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class Sink implements AbstractServerStream.Sink {
        Sink() {
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeHeaders(Metadata metadata) {
            PerfMark.startTask("OkHttpServerStream$Sink.writeHeaders");
            try {
                List<Header> createResponseHeaders = Headers.createResponseHeaders(metadata);
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendHeaders(createResponseHeaders);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.writeHeaders");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeFrame(WritableBuffer writableBuffer, boolean z, int i) {
            PerfMark.startTask("OkHttpServerStream$Sink.writeFrame");
            Buffer buffer = ((OkHttpWritableBuffer) writableBuffer).buffer();
            int size = (int) buffer.size();
            if (size > 0) {
                OkHttpServerStream.this.onSendingBytes(size);
            }
            try {
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendBuffer(buffer, z);
                    OkHttpServerStream.this.transportTracer.reportMessageSent(i);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.writeFrame");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeTrailers(Metadata metadata, boolean z, Status status) {
            PerfMark.startTask("OkHttpServerStream$Sink.writeTrailers");
            try {
                List<Header> createResponseTrailers = Headers.createResponseTrailers(metadata, z);
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendTrailers(createResponseTrailers);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.writeTrailers");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void cancel(Status status) {
            PerfMark.startTask("OkHttpServerStream$Sink.cancel");
            try {
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.cancel(ErrorCode.CANCEL, status);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.cancel");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class TransportState extends AbstractServerStream.TransportState implements OutboundFlowController.Stream, OkHttpServerTransport.StreamState {
        private boolean cancelSent;
        private final ExceptionHandlingFrameWriter frameWriter;
        private final int initialWindowSize;
        private final Object lock;
        private final OutboundFlowController outboundFlow;
        private final OutboundFlowController.StreamState outboundFlowState;
        private int processedWindow;
        private boolean receivedEndOfStream;
        private final int streamId;
        private final Tag tag;
        private final OkHttpServerTransport transport;
        private int window;

        public TransportState(OkHttpServerTransport okHttpServerTransport, int i, int i2, StatsTraceContext statsTraceContext, Object obj, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OutboundFlowController outboundFlowController, int i3, TransportTracer transportTracer, String str) {
            super(i2, statsTraceContext, transportTracer);
            this.cancelSent = false;
            this.transport = (OkHttpServerTransport) Preconditions.checkNotNull(okHttpServerTransport, NotificationCompat.CATEGORY_TRANSPORT);
            this.streamId = i;
            this.lock = Preconditions.checkNotNull(obj, SentryStackFrame.JsonKeys.LOCK);
            this.frameWriter = exceptionHandlingFrameWriter;
            this.outboundFlow = outboundFlowController;
            this.window = i3;
            this.processedWindow = i3;
            this.initialWindowSize = i3;
            this.tag = PerfMark.createTag(str);
            this.outboundFlowState = outboundFlowController.createState(this, i);
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframeFailed(Throwable th) {
            cancel(ErrorCode.INTERNAL_ERROR, Status.fromThrowable(th));
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void bytesRead(int i) {
            int i2 = this.processedWindow - i;
            this.processedWindow = i2;
            float f = i2;
            int i3 = this.initialWindowSize;
            if (f <= i3 * 0.5f) {
                int i4 = i3 - i2;
                this.window += i4;
                this.processedWindow = i2 + i4;
                this.frameWriter.windowUpdate(this.streamId, i4);
                this.frameWriter.flush();
            }
        }

        @Override // io.grpc.internal.ApplicationThreadDeframerListener.TransportExecutor
        public void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public void inboundDataReceived(Buffer buffer, int i, boolean z) {
            synchronized (this.lock) {
                PerfMark.event("OkHttpServerTransport$FrameHandler.data", this.tag);
                if (z) {
                    this.receivedEndOfStream = true;
                }
                this.window -= i;
                super.inboundDataReceived(new OkHttpReadableBuffer(buffer), z);
            }
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public void inboundRstReceived(Status status) {
            PerfMark.event("OkHttpServerTransport$FrameHandler.rstStream", this.tag);
            transportReportStatus(status);
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public boolean hasReceivedEndOfStream() {
            boolean z;
            synchronized (this.lock) {
                z = this.receivedEndOfStream;
            }
            return z;
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public int inboundWindowAvailable() {
            int i;
            synchronized (this.lock) {
                i = this.window;
            }
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendBuffer(Buffer buffer, boolean z) {
            if (this.cancelSent) {
                return;
            }
            this.outboundFlow.data(false, this.outboundFlowState, buffer, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendHeaders(List<Header> list) {
            this.frameWriter.synReply(false, this.streamId, list);
            this.frameWriter.flush();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendTrailers(final List<Header> list) {
            this.outboundFlow.notifyWhenNoPendingData(this.outboundFlowState, new Runnable() { // from class: io.grpc.okhttp.OkHttpServerStream$TransportState$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OkHttpServerStream.TransportState.this.m1121x223b3517(list);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: sendTrailersAfterFlowControlled, reason: merged with bridge method [inline-methods] */
        public void m1121x223b3517(List<Header> list) {
            synchronized (this.lock) {
                this.frameWriter.synReply(true, this.streamId, list);
                if (!this.receivedEndOfStream) {
                    this.frameWriter.rstStream(this.streamId, ErrorCode.NO_ERROR);
                }
                this.transport.streamClosed(this.streamId, true);
                complete();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel(ErrorCode errorCode, Status status) {
            if (this.cancelSent) {
                return;
            }
            this.cancelSent = true;
            this.frameWriter.rstStream(this.streamId, errorCode);
            transportReportStatus(status);
            this.transport.streamClosed(this.streamId, true);
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public OutboundFlowController.StreamState getOutboundFlowState() {
            return this.outboundFlowState;
        }
    }
}
