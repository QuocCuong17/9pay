package com.facebook.appevents.gps;

import android.content.Context;
import android.os.Bundle;
import com.facebook.appevents.InternalAppEventsLogger;
import io.flutter.plugins.firebase.analytics.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;

/* compiled from: GpsDebugLogger.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/appevents/gps/GpsDebugLogger;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "internalAppEventsLogger", "Lcom/facebook/appevents/InternalAppEventsLogger;", "isGPSDebugEvent", "", Constants.EVENT_NAME, "", "log", "", "parameters", "Landroid/os/Bundle;", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GpsDebugLogger {
    private static final String GPS_PREFIX = "gps";
    private static final double LOGGING_SAMPLING_RATE = 1.0E-4d;
    private static final boolean shouldLog;
    private final InternalAppEventsLogger internalAppEventsLogger;

    public GpsDebugLogger(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.internalAppEventsLogger = new InternalAppEventsLogger(context);
    }

    public final void log(String eventName, Bundle parameters) {
        if (shouldLog && isGPSDebugEvent(eventName)) {
            this.internalAppEventsLogger.logEventImplicitly(eventName, parameters);
        }
    }

    private final boolean isGPSDebugEvent(String eventName) {
        if (eventName != null) {
            return StringsKt.contains$default((CharSequence) eventName, (CharSequence) GPS_PREFIX, false, 2, (Object) null);
        }
        return false;
    }

    static {
        shouldLog = Random.INSTANCE.nextDouble() <= LOGGING_SAMPLING_RATE;
    }
}
