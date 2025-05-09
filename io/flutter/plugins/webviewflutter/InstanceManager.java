package io.flutter.plugins.webviewflutter;

import android.os.Handler;
import android.os.Looper;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
public class InstanceManager {
    private static final long CLEAR_FINALIZED_WEAK_REFERENCES_INTERVAL = 30000;
    private static final long MIN_HOST_CREATED_IDENTIFIER = 65536;
    private final FinalizationListener finalizationListener;
    private final Handler handler;
    private boolean isClosed;
    private long nextIdentifier;
    private final WeakHashMap<Object, Long> identifiers = new WeakHashMap<>();
    private final HashMap<Long, WeakReference<Object>> weakInstances = new HashMap<>();
    private final HashMap<Long, Object> strongInstances = new HashMap<>();
    private final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    private final HashMap<WeakReference<Object>, Long> weakReferencesToIdentifiers = new HashMap<>();

    /* loaded from: classes5.dex */
    public interface FinalizationListener {
        void onFinalize(long j);
    }

    public static InstanceManager open(FinalizationListener finalizationListener) {
        return new InstanceManager(finalizationListener);
    }

    private InstanceManager(FinalizationListener finalizationListener) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.handler = handler;
        this.nextIdentifier = 65536L;
        this.isClosed = false;
        this.finalizationListener = finalizationListener;
        handler.postDelayed(new InstanceManager$$ExternalSyntheticLambda0(this), 30000L);
    }

    public <T> T remove(long j) {
        assertManagerIsNotClosed();
        return (T) this.strongInstances.remove(Long.valueOf(j));
    }

    public Long getIdentifierForStrongReference(Object obj) {
        assertManagerIsNotClosed();
        Long l = this.identifiers.get(obj);
        if (l != null) {
            this.strongInstances.put(l, obj);
        }
        return l;
    }

    public void addDartCreatedInstance(Object obj, long j) {
        assertManagerIsNotClosed();
        addInstance(obj, j);
    }

    public long addHostCreatedInstance(Object obj) {
        assertManagerIsNotClosed();
        long j = this.nextIdentifier;
        this.nextIdentifier = 1 + j;
        addInstance(obj, j);
        return j;
    }

    public <T> T getInstance(long j) {
        assertManagerIsNotClosed();
        WeakReference<Object> weakReference = this.weakInstances.get(Long.valueOf(j));
        if (weakReference != null) {
            return (T) weakReference.get();
        }
        return (T) this.strongInstances.get(Long.valueOf(j));
    }

    public boolean containsInstance(Object obj) {
        assertManagerIsNotClosed();
        return this.identifiers.containsKey(obj);
    }

    public void close() {
        this.handler.removeCallbacks(new InstanceManager$$ExternalSyntheticLambda0(this));
        this.isClosed = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAllFinalizedInstances() {
        while (true) {
            WeakReference weakReference = (WeakReference) this.referenceQueue.poll();
            if (weakReference != null) {
                Long remove = this.weakReferencesToIdentifiers.remove(weakReference);
                if (remove != null) {
                    this.weakInstances.remove(remove);
                    this.strongInstances.remove(remove);
                    this.finalizationListener.onFinalize(remove.longValue());
                }
            } else {
                this.handler.postDelayed(new InstanceManager$$ExternalSyntheticLambda0(this), 30000L);
                return;
            }
        }
    }

    private void addInstance(Object obj, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Identifier must be >= 0.");
        }
        WeakReference<Object> weakReference = new WeakReference<>(obj, this.referenceQueue);
        this.identifiers.put(obj, Long.valueOf(j));
        this.weakInstances.put(Long.valueOf(j), weakReference);
        this.weakReferencesToIdentifiers.put(weakReference, Long.valueOf(j));
        this.strongInstances.put(Long.valueOf(j), obj);
    }

    private void assertManagerIsNotClosed() {
        if (this.isClosed) {
            throw new AssertionError("Manager has already been closed.");
        }
    }
}
