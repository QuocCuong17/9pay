package androidx.window.area;

import io.sentry.cache.EnvelopeCache;
import kotlin.Metadata;

/* compiled from: WindowAreaSessionCallback.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/window/area/WindowAreaSessionCallback;", "", "onSessionEnded", "", "t", "", "onSessionStarted", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Landroidx/window/area/WindowAreaSession;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface WindowAreaSessionCallback {
    void onSessionEnded(Throwable t);

    void onSessionStarted(WindowAreaSession session);
}
