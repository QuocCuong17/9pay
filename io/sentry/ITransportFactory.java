package io.sentry;

import io.sentry.transport.ITransport;

/* loaded from: classes5.dex */
public interface ITransportFactory {
    ITransport create(SentryOptions sentryOptions, RequestDetails requestDetails);
}
