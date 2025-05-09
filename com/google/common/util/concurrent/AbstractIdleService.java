package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public abstract class AbstractIdleService implements Service {
    private final Service delegate;
    private final Supplier<String> threadNameSupplier;

    protected abstract void shutDown() throws Exception;

    protected abstract void startUp() throws Exception;

    /* loaded from: classes4.dex */
    private final class ThreadNameSupplier implements Supplier<String> {
        private ThreadNameSupplier() {
        }

        @Override // com.google.common.base.Supplier
        public String get() {
            return AbstractIdleService.this.serviceName() + " " + AbstractIdleService.this.state();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public final class DelegateService extends AbstractService {
        private DelegateService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected final void doStart() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), (Supplier<String>) AbstractIdleService.this.threadNameSupplier).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService$DelegateService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractIdleService.DelegateService.this.m687x2ed323e8();
                }
            });
        }

        /* renamed from: lambda$doStart$0$com-google-common-util-concurrent-AbstractIdleService$DelegateService, reason: not valid java name */
        public /* synthetic */ void m687x2ed323e8() {
            try {
                AbstractIdleService.this.startUp();
                notifyStarted();
            } catch (Throwable th) {
                Platform.restoreInterruptIfIsInterruptedException(th);
                notifyFailed(th);
            }
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected final void doStop() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), (Supplier<String>) AbstractIdleService.this.threadNameSupplier).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService$DelegateService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractIdleService.DelegateService.this.m688xb13e6319();
                }
            });
        }

        /* renamed from: lambda$doStop$1$com-google-common-util-concurrent-AbstractIdleService$DelegateService, reason: not valid java name */
        public /* synthetic */ void m688xb13e6319() {
            try {
                AbstractIdleService.this.shutDown();
                notifyStopped();
            } catch (Throwable th) {
                Platform.restoreInterruptIfIsInterruptedException(th);
                notifyFailed(th);
            }
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractIdleService.this.toString();
        }
    }

    protected AbstractIdleService() {
        this.threadNameSupplier = new ThreadNameSupplier();
        this.delegate = new DelegateService();
    }

    protected Executor executor() {
        return new Executor() { // from class: com.google.common.util.concurrent.AbstractIdleService$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                AbstractIdleService.this.m686xc998c392(runnable);
            }
        };
    }

    /* renamed from: lambda$executor$0$com-google-common-util-concurrent-AbstractIdleService, reason: not valid java name */
    public /* synthetic */ void m686xc998c392(Runnable runnable) {
        MoreExecutors.newThread(this.threadNameSupplier.get(), runnable).start();
    }

    public String toString() {
        return serviceName() + " [" + state() + "]";
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }

    protected String serviceName() {
        return getClass().getSimpleName();
    }
}
