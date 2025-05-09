package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class ScopedExecutor implements Executor {
    private final Executor executor;
    private final AtomicBoolean shutdown = new AtomicBoolean();

    public ScopedExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        if (this.shutdown.get()) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.ScopedExecutor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ScopedExecutor.this.m2991x5e6aa2ec(runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$execute$0$vn-ai-faceauth-sdk-presentation-presentation-widgets-ScopedExecutor, reason: not valid java name */
    public /* synthetic */ void m2991x5e6aa2ec(Runnable runnable) {
        if (this.shutdown.get()) {
            return;
        }
        runnable.run();
    }

    public void shutdown() {
        this.shutdown.set(true);
    }
}
