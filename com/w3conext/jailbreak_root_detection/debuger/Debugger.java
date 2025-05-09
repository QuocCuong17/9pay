package com.w3conext.jailbreak_root_detection.debuger;

import android.content.Context;
import android.provider.Settings;
import kotlin.Metadata;

/* compiled from: Debugger.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/debuger/Debugger;", "", "()V", "isDebugged", "", "context", "Landroid/content/Context;", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Debugger {
    public static final Debugger INSTANCE = new Debugger();

    private Debugger() {
    }

    public final boolean isDebugged(Context context) {
        if (context == null) {
            return false;
        }
        return (Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) == 1) || (Settings.Secure.getInt(context.getContentResolver(), "wait_for_debugger", 0) == 1);
    }
}
