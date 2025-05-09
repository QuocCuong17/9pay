package io.sentry.transport;

import io.sentry.Hint;
import io.sentry.SentryEnvelope;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes5.dex */
public interface ITransport extends Closeable {
    void flush(long j);

    void send(SentryEnvelope sentryEnvelope) throws IOException;

    void send(SentryEnvelope sentryEnvelope, Hint hint) throws IOException;

    /* renamed from: io.sentry.transport.ITransport$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
    }
}
