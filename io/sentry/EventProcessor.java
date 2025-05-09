package io.sentry;

import io.sentry.protocol.SentryTransaction;

/* loaded from: classes5.dex */
public interface EventProcessor {

    /* renamed from: io.sentry.EventProcessor$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static SentryEvent $default$process(EventProcessor _this, SentryEvent sentryEvent, Hint hint) {
            return sentryEvent;
        }

        public static SentryTransaction $default$process(EventProcessor _this, SentryTransaction sentryTransaction, Hint hint) {
            return sentryTransaction;
        }
    }

    SentryEvent process(SentryEvent sentryEvent, Hint hint);

    SentryTransaction process(SentryTransaction sentryTransaction, Hint hint);
}
