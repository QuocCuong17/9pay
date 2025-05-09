package co.hyperverge.crashguard.utils;

import android.content.Context;
import android.util.Log;
import co.hyperverge.crashguard.objects.CrashguardConfig;
import co.hyperverge.crashguard.objects.SentryProperties;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import io.sentry.Attachment;
import io.sentry.EventProcessor;
import io.sentry.Hint;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryException;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: extensions.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u001a\u001c\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u001a\u001a\u0010\t\u001a\u00020\u0001*\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\u001a\f\u0010\n\u001a\u00020\u0001*\u00020\u000bH\u0000\u001a\u001c\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0000\u001a\u001a\u0010\u0012\u001a\u00020\u0001*\u00020\u00132\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¨\u0006\u0014"}, d2 = {"doesANRThreadDumpContainFilter", "", "bytes", "", "filters", "", "", "doesEventContainFilter", "stackTraceToString", "containsFilter", "isReleaseBuild", "Landroid/content/Context;", "setConfig", "", "Lio/sentry/android/core/SentryAndroidOptions;", Request.JsonKeys.ENV, HVRetakeActivity.CONFIG_TAG, "Lco/hyperverge/crashguard/objects/CrashguardConfig;", "shouldReportCrash", "", "crashguard_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ExtensionsKt {
    public static final boolean isReleaseBuild(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return !((context.getApplicationContext().getApplicationInfo().flags & 2) != 0);
    }

    public static final boolean shouldReportCrash(Throwable th, List<String> filters) {
        Object m1202constructorimpl;
        boolean z;
        Intrinsics.checkNotNullParameter(th, "<this>");
        Intrinsics.checkNotNullParameter(filters, "filters");
        try {
            Result.Companion companion = Result.INSTANCE;
            boolean z2 = true;
            if (!filters.isEmpty()) {
                List<String> list = filters;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        if (StringsKt.contains$default((CharSequence) ExceptionsKt.stackTraceToString(th), (CharSequence) it.next(), false, 2, (Object) null)) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                    z2 = false;
                }
            }
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z2));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = false;
        }
        return ((Boolean) m1202constructorimpl).booleanValue();
    }

    public static final boolean containsFilter(String str, List<String> filters) {
        boolean z;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(filters, "filters");
        if (filters.isEmpty()) {
            return true;
        }
        List<String> list = filters;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) it.next(), false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        return z;
    }

    public static final void setConfig(final SentryAndroidOptions sentryAndroidOptions, String env, final CrashguardConfig config) {
        Intrinsics.checkNotNullParameter(sentryAndroidOptions, "<this>");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(config, "config");
        final SentryProperties sentryProperties = config.getSentryProperties();
        sentryAndroidOptions.setDsn(sentryProperties.getDsn());
        sentryAndroidOptions.setDebug(sentryProperties.getShouldPrintSentryDebugLogs());
        sentryAndroidOptions.setAttachScreenshot(sentryProperties.getShouldAttachScreenshot());
        sentryAndroidOptions.enableAllAutoBreadcrumbs(sentryProperties.getShouldEnableBreadcrumbs());
        sentryAndroidOptions.setEnableDeduplication(sentryProperties.getShouldEnableEventDeduplicationCheck());
        sentryAndroidOptions.setEnvironment(env);
        sentryAndroidOptions.setRelease(config.getTags().get("release"));
        for (Map.Entry<String, String> entry : config.getTags().entrySet()) {
            sentryAndroidOptions.setTag(entry.getKey(), entry.getValue());
        }
        sentryAndroidOptions.setBeforeSend(new SentryOptions.BeforeSendCallback() { // from class: co.hyperverge.crashguard.utils.ExtensionsKt$$ExternalSyntheticLambda0
            @Override // io.sentry.SentryOptions.BeforeSendCallback
            public final SentryEvent execute(SentryEvent sentryEvent, Hint hint) {
                SentryEvent m392setConfig$lambda9;
                m392setConfig$lambda9 = ExtensionsKt.m392setConfig$lambda9(CrashguardConfig.this, sentryAndroidOptions, sentryProperties, sentryEvent, hint);
                return m392setConfig$lambda9;
            }
        });
        Iterator<T> it = sentryProperties.getSentryEventProcessors().iterator();
        while (it.hasNext()) {
            sentryAndroidOptions.addEventProcessor((EventProcessor) it.next());
        }
        sentryAndroidOptions.setEnableAutoSessionTracking(false);
        sentryAndroidOptions.setAnrEnabled(true);
        sentryAndroidOptions.setAttachAnrThreadDump(true);
        sentryAndroidOptions.setMaxAttachmentSize(524288000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setConfig$lambda-9, reason: not valid java name */
    public static final SentryEvent m392setConfig$lambda9(CrashguardConfig config, SentryAndroidOptions this_setConfig, SentryProperties sentryProperties, SentryEvent event, Hint hint) {
        String stackTraceToString;
        byte[] bytes;
        Intrinsics.checkNotNullParameter(config, "$config");
        Intrinsics.checkNotNullParameter(this_setConfig, "$this_setConfig");
        Intrinsics.checkNotNullParameter(sentryProperties, "$sentryProperties");
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(hint, "hint");
        Attachment threadDump = hint.getThreadDump();
        if (threadDump != null && (bytes = threadDump.getBytes()) != null && !doesANRThreadDumpContainFilter(bytes, config.getFilters())) {
            return null;
        }
        Throwable throwable = event.getThrowable();
        boolean z = false;
        if (throwable != null && shouldReportCrash(throwable, config.getFilters())) {
            z = true;
        }
        if (!z) {
            return null;
        }
        this_setConfig.setRelease(config.getTags().get("release"));
        Map<String, String> tags = event.getTags();
        if (tags != null) {
            for (Map.Entry<String, String> entry : tags.entrySet()) {
                this_setConfig.setTag(entry.getKey(), entry.getValue());
            }
        }
        Throwable throwable2 = event.getThrowable();
        if (throwable2 != null && (stackTraceToString = ExceptionsKt.stackTraceToString(throwable2)) != null) {
            HyperLogger.INSTANCE.getInstance().log(HyperLogger.Level.ERROR, stackTraceToString);
        }
        event.setLevel(SentryLevel.FATAL);
        List<SentryException> exceptions = event.getExceptions();
        if (exceptions != null) {
            Iterator<SentryException> it = exceptions.iterator();
            while (it.hasNext()) {
                it.next().setMechanism(sentryProperties.getSentryMechanism());
            }
        }
        SentryException unhandledException = event.getUnhandledException();
        if (unhandledException != null) {
            unhandledException.setMechanism(sentryProperties.getSentryMechanism());
        }
        File currentLogZipFile = HyperLogger.INSTANCE.getInstance().getCurrentLogZipFile();
        String path = currentLogZipFile != null ? currentLogZipFile.getPath() : null;
        if (path != null) {
            hint.addAttachment(new Attachment(path));
        }
        return event;
    }

    public static final boolean doesEventContainFilter(String stackTraceToString, List<String> filters) {
        Intrinsics.checkNotNullParameter(stackTraceToString, "stackTraceToString");
        Intrinsics.checkNotNullParameter(filters, "filters");
        return containsFilter(stackTraceToString, filters);
    }

    public static final boolean doesANRThreadDumpContainFilter(byte[] bytes, List<String> filters) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(filters, "filters");
        try {
            Result.Companion companion = Result.INSTANCE;
            StringBuilder sb = new StringBuilder();
            int length = bytes.length;
            int i = 0;
            while (i < length) {
                byte b = bytes[i];
                i++;
                sb.append((char) b);
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(containsFilter(sb2, filters)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            if (m1205exceptionOrNullimpl instanceof OutOfMemoryError) {
                Log.e("OutOfMemoryError", Intrinsics.stringPlus("Out of memory error occurred: ", m1205exceptionOrNullimpl.getMessage()));
                m1202constructorimpl = false;
            } else {
                throw m1205exceptionOrNullimpl;
            }
        }
        return ((Boolean) m1202constructorimpl).booleanValue();
    }
}
