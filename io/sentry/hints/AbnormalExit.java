package io.sentry.hints;

/* loaded from: classes5.dex */
public interface AbnormalExit {

    /* renamed from: io.sentry.hints.AbnormalExit$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static boolean $default$ignoreCurrentThread(AbnormalExit _this) {
            return false;
        }

        public static Long $default$timestamp(AbnormalExit _this) {
            return null;
        }
    }

    boolean ignoreCurrentThread();

    String mechanism();

    Long timestamp();
}
