package com.google.firebase.firestore.remote;

import io.grpc.Metadata;
import io.grpc.Status;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public interface IncomingStreamObserver<RespT> {
    void onClose(Status status);

    void onHeaders(Metadata metadata);

    void onNext(RespT respt);

    void onOpen();
}
