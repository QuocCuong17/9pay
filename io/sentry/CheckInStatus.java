package io.sentry;

import java.util.Locale;

/* loaded from: classes5.dex */
public enum CheckInStatus {
    IN_PROGRESS,
    OK,
    ERROR;

    public String apiName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
