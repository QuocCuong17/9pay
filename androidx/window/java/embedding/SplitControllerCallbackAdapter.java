package androidx.window.java.embedding;

import android.app.Activity;
import androidx.core.util.Consumer;
import androidx.window.embedding.SplitController;
import androidx.window.embedding.SplitInfo;
import androidx.window.java.core.CallbackToFlowAdapter;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplitControllerCallbackAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fJ\u001a\u0010\u0012\u001a\u00020\t2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/window/java/embedding/SplitControllerCallbackAdapter;", "", "controller", "Landroidx/window/embedding/SplitController;", "(Landroidx/window/embedding/SplitController;)V", "callbackToFlowAdapter", "Landroidx/window/java/core/CallbackToFlowAdapter;", "(Landroidx/window/embedding/SplitController;Landroidx/window/java/core/CallbackToFlowAdapter;)V", "addSplitListener", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "consumer", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "removeSplitListener", "window-java_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SplitControllerCallbackAdapter {
    private final CallbackToFlowAdapter callbackToFlowAdapter;
    private final SplitController controller;

    private SplitControllerCallbackAdapter(SplitController splitController, CallbackToFlowAdapter callbackToFlowAdapter) {
        this.controller = splitController;
        this.callbackToFlowAdapter = callbackToFlowAdapter;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SplitControllerCallbackAdapter(SplitController controller) {
        this(controller, new CallbackToFlowAdapter());
        Intrinsics.checkNotNullParameter(controller, "controller");
    }

    public final void addSplitListener(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        this.callbackToFlowAdapter.connect(executor, consumer, this.controller.splitInfoList(activity));
    }

    public final void removeSplitListener(Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        this.callbackToFlowAdapter.disconnect(consumer);
    }
}
