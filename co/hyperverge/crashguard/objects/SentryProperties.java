package co.hyperverge.crashguard.objects;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import io.sentry.EventProcessor;
import io.sentry.Sentry;
import io.sentry.protocol.Mechanism;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashguardConfig.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\u0006\u0010\u001e\u001a\u00020\u001fJ\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\r¨\u0006#"}, d2 = {"Lco/hyperverge/crashguard/objects/SentryProperties;", "", "dsn", "", "(Ljava/lang/String;)V", "getDsn", "()Ljava/lang/String;", "setDsn", "shouldAttachScreenshot", "", "getShouldAttachScreenshot", "()Z", "setShouldAttachScreenshot", "(Z)V", "shouldEnableBreadcrumbs", "getShouldEnableBreadcrumbs", "setShouldEnableBreadcrumbs", "shouldEnableEventDeduplicationCheck", "getShouldEnableEventDeduplicationCheck", "setShouldEnableEventDeduplicationCheck", "shouldPrintSentryDebugLogs", "getShouldPrintSentryDebugLogs", "setShouldPrintSentryDebugLogs", "component1", "copy", "equals", "other", "getSentryEventProcessors", "", "Lio/sentry/EventProcessor;", "getSentryMechanism", "Lio/sentry/protocol/Mechanism;", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "crashguard_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class SentryProperties {
    private String dsn;
    private boolean shouldAttachScreenshot;
    private boolean shouldEnableBreadcrumbs;
    private boolean shouldEnableEventDeduplicationCheck;
    private boolean shouldPrintSentryDebugLogs;

    public static /* synthetic */ SentryProperties copy$default(SentryProperties sentryProperties, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sentryProperties.dsn;
        }
        return sentryProperties.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDsn() {
        return this.dsn;
    }

    public final SentryProperties copy(String dsn) {
        Intrinsics.checkNotNullParameter(dsn, "dsn");
        return new SentryProperties(dsn);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SentryProperties) && Intrinsics.areEqual(this.dsn, ((SentryProperties) other).dsn);
    }

    public int hashCode() {
        return this.dsn.hashCode();
    }

    public String toString() {
        return "SentryProperties(dsn=" + this.dsn + ')';
    }

    public SentryProperties(String dsn) {
        Intrinsics.checkNotNullParameter(dsn, "dsn");
        this.dsn = dsn;
        this.shouldAttachScreenshot = true;
        this.shouldEnableBreadcrumbs = true;
    }

    public final String getDsn() {
        return this.dsn;
    }

    public final void setDsn(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dsn = str;
    }

    public final boolean getShouldAttachScreenshot() {
        return this.shouldAttachScreenshot;
    }

    public final void setShouldAttachScreenshot(boolean z) {
        this.shouldAttachScreenshot = z;
    }

    public final boolean getShouldEnableBreadcrumbs() {
        return this.shouldEnableBreadcrumbs;
    }

    public final void setShouldEnableBreadcrumbs(boolean z) {
        this.shouldEnableBreadcrumbs = z;
    }

    public final boolean getShouldPrintSentryDebugLogs() {
        return this.shouldPrintSentryDebugLogs;
    }

    public final void setShouldPrintSentryDebugLogs(boolean z) {
        this.shouldPrintSentryDebugLogs = z;
    }

    public final boolean getShouldEnableEventDeduplicationCheck() {
        return this.shouldEnableEventDeduplicationCheck;
    }

    public final void setShouldEnableEventDeduplicationCheck(boolean z) {
        this.shouldEnableEventDeduplicationCheck = z;
    }

    public final Mechanism getSentryMechanism() {
        Mechanism mechanism = new Mechanism();
        mechanism.setType("UncaughtExceptionHandler");
        mechanism.setHandled(false);
        return mechanism;
    }

    public final List<EventProcessor> getSentryEventProcessors() {
        List<EventProcessor> eventProcessors = Sentry.cloneMainHub().getOptions().getEventProcessors();
        Intrinsics.checkNotNullExpressionValue(eventProcessors, "cloneMainHub().options.eventProcessors");
        return eventProcessors;
    }
}
