package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

/* loaded from: classes5.dex */
public abstract class ClientStreamTracer extends StreamTracer {
    public void inboundHeaders() {
    }

    public void inboundTrailers(Metadata metadata) {
    }

    public void outboundHeaders() {
    }

    public void streamCreated(Attributes attributes, Metadata metadata) {
    }

    /* loaded from: classes5.dex */
    public static abstract class Factory {
        public ClientStreamTracer newClientStreamTracer(StreamInfo streamInfo, Metadata metadata) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    /* loaded from: classes5.dex */
    public static final class StreamInfo {
        private final CallOptions callOptions;
        private final boolean isTransparentRetry;
        private final int previousAttempts;

        StreamInfo(CallOptions callOptions, int i, boolean z) {
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions");
            this.previousAttempts = i;
            this.isTransparentRetry = z;
        }

        public CallOptions getCallOptions() {
            return this.callOptions;
        }

        public int getPreviousAttempts() {
            return this.previousAttempts;
        }

        public boolean isTransparentRetry() {
            return this.isTransparentRetry;
        }

        public Builder toBuilder() {
            return new Builder().setCallOptions(this.callOptions).setPreviousAttempts(this.previousAttempts).setIsTransparentRetry(this.isTransparentRetry);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("callOptions", this.callOptions).add("previousAttempts", this.previousAttempts).add("isTransparentRetry", this.isTransparentRetry).toString();
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private CallOptions callOptions = CallOptions.DEFAULT;
            private boolean isTransparentRetry;
            private int previousAttempts;

            Builder() {
            }

            public Builder setCallOptions(CallOptions callOptions) {
                this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions cannot be null");
                return this;
            }

            public Builder setPreviousAttempts(int i) {
                this.previousAttempts = i;
                return this;
            }

            public Builder setIsTransparentRetry(boolean z) {
                this.isTransparentRetry = z;
                return this;
            }

            public StreamInfo build() {
                return new StreamInfo(this.callOptions, this.previousAttempts, this.isTransparentRetry);
            }
        }
    }
}
