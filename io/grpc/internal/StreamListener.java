package io.grpc.internal;

import java.io.InputStream;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public interface StreamListener {

    /* loaded from: classes5.dex */
    public interface MessageProducer {
        @Nullable
        InputStream next();
    }

    void messagesAvailable(MessageProducer messageProducer);

    void onReady();
}
