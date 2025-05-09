package androidx.window.java.layout;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.java.core.CallbackToFlowAdapter;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: WindowInfoTrackerCallbackAdapter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ$\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0014\u0010\u0012\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00142\u0006\u0010\t\u001a\u00020\nH\u0096\u0001J\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/window/java/layout/WindowInfoTrackerCallbackAdapter;", "Landroidx/window/layout/WindowInfoTracker;", "tracker", "(Landroidx/window/layout/WindowInfoTracker;)V", "callbackToFlowAdapter", "Landroidx/window/java/core/CallbackToFlowAdapter;", "(Landroidx/window/layout/WindowInfoTracker;Landroidx/window/java/core/CallbackToFlowAdapter;)V", "addWindowLayoutInfoListener", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "consumer", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "removeWindowLayoutInfoListener", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "window-java_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WindowInfoTrackerCallbackAdapter implements WindowInfoTracker {
    private final CallbackToFlowAdapter callbackToFlowAdapter;
    private final WindowInfoTracker tracker;

    @Override // androidx.window.layout.WindowInfoTracker
    public Flow<WindowLayoutInfo> windowLayoutInfo(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.tracker.windowLayoutInfo(activity);
    }

    @Override // androidx.window.layout.WindowInfoTracker
    public Flow<WindowLayoutInfo> windowLayoutInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.tracker.windowLayoutInfo(context);
    }

    private WindowInfoTrackerCallbackAdapter(WindowInfoTracker windowInfoTracker, CallbackToFlowAdapter callbackToFlowAdapter) {
        this.tracker = windowInfoTracker;
        this.callbackToFlowAdapter = callbackToFlowAdapter;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WindowInfoTrackerCallbackAdapter(WindowInfoTracker tracker) {
        this(tracker, new CallbackToFlowAdapter());
        Intrinsics.checkNotNullParameter(tracker, "tracker");
    }

    public final void addWindowLayoutInfoListener(Activity activity, Executor executor, Consumer<WindowLayoutInfo> consumer) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        this.callbackToFlowAdapter.connect(executor, consumer, this.tracker.windowLayoutInfo(activity));
    }

    public final void addWindowLayoutInfoListener(Context context, Executor executor, Consumer<WindowLayoutInfo> consumer) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        this.callbackToFlowAdapter.connect(executor, consumer, this.tracker.windowLayoutInfo(context));
    }

    public final void removeWindowLayoutInfoListener(Consumer<WindowLayoutInfo> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        this.callbackToFlowAdapter.disconnect(consumer);
    }
}
