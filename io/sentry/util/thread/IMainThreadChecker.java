package io.sentry.util.thread;

import io.sentry.protocol.SentryThread;

/* loaded from: classes5.dex */
public interface IMainThreadChecker {
    boolean isMainThread();

    boolean isMainThread(long j);

    boolean isMainThread(SentryThread sentryThread);

    boolean isMainThread(Thread thread);

    /* renamed from: io.sentry.util.thread.IMainThreadChecker$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static boolean $default$isMainThread(IMainThreadChecker _this, SentryThread sentryThread) {
            Long id2 = sentryThread.getId();
            return id2 != null && _this.isMainThread(id2.longValue());
        }
    }
}
