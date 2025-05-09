package io.grpc;

import io.grpc.Metadata;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public final class InternalStatus {
    public static final Metadata.Key<String> MESSAGE_KEY = Status.MESSAGE_KEY;
    public static final Metadata.Key<Status> CODE_KEY = Status.CODE_KEY;

    private InternalStatus() {
    }

    public static final StatusRuntimeException asRuntimeException(Status status, @Nullable Metadata metadata, boolean z) {
        return new StatusRuntimeException(status, metadata, z);
    }
}
