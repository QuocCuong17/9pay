package com.google.firebase.firestore.remote;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import dev.fluttercommunity.plus.connectivity.ConnectivityBroadcastReceiver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public final class AndroidConnectivityMonitor implements ConnectivityMonitor {
    private static final String LOG_TAG = "AndroidConnectivityMonitor";
    private final List<Consumer<ConnectivityMonitor.NetworkStatus>> callbacks = new ArrayList();
    private final ConnectivityManager connectivityManager;
    private final Context context;
    private Runnable unregisterRunnable;

    public AndroidConnectivityMonitor(Context context) {
        Assert.hardAssert(context != null, "Context must be non-null", new Object[0]);
        this.context = context;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        configureBackgroundStateListener();
        configureNetworkMonitoring();
    }

    @Override // com.google.firebase.firestore.remote.ConnectivityMonitor
    public void addCallback(Consumer<ConnectivityMonitor.NetworkStatus> consumer) {
        synchronized (this.callbacks) {
            this.callbacks.add(consumer);
        }
    }

    @Override // com.google.firebase.firestore.remote.ConnectivityMonitor
    public void shutdown() {
        Runnable runnable = this.unregisterRunnable;
        if (runnable != null) {
            runnable.run();
            this.unregisterRunnable = null;
        }
    }

    private void configureNetworkMonitoring() {
        if (Build.VERSION.SDK_INT >= 24 && this.connectivityManager != null) {
            final DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback();
            this.connectivityManager.registerDefaultNetworkCallback(defaultNetworkCallback);
            this.unregisterRunnable = new Runnable() { // from class: com.google.firebase.firestore.remote.AndroidConnectivityMonitor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidConnectivityMonitor.this.m860xddc22e50(defaultNetworkCallback);
                }
            };
        } else {
            final NetworkReceiver networkReceiver = new NetworkReceiver();
            this.context.registerReceiver(networkReceiver, new IntentFilter(ConnectivityBroadcastReceiver.CONNECTIVITY_ACTION));
            this.unregisterRunnable = new Runnable() { // from class: com.google.firebase.firestore.remote.AndroidConnectivityMonitor$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidConnectivityMonitor.this.m861x2b81a651(networkReceiver);
                }
            };
        }
    }

    /* renamed from: lambda$configureNetworkMonitoring$0$com-google-firebase-firestore-remote-AndroidConnectivityMonitor, reason: not valid java name */
    public /* synthetic */ void m860xddc22e50(DefaultNetworkCallback defaultNetworkCallback) {
        this.connectivityManager.unregisterNetworkCallback(defaultNetworkCallback);
    }

    /* renamed from: lambda$configureNetworkMonitoring$1$com-google-firebase-firestore-remote-AndroidConnectivityMonitor, reason: not valid java name */
    public /* synthetic */ void m861x2b81a651(NetworkReceiver networkReceiver) {
        this.context.unregisterReceiver(networkReceiver);
    }

    private void configureBackgroundStateListener() {
        Application application = (Application) this.context.getApplicationContext();
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.google.firebase.firestore.remote.AndroidConnectivityMonitor.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }
        });
        application.registerComponentCallbacks(new ComponentCallbacks2() { // from class: com.google.firebase.firestore.remote.AndroidConnectivityMonitor.2
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks2
            public void onTrimMemory(int i) {
                if (i == 20) {
                    atomicBoolean.set(true);
                }
            }
        });
    }

    public void raiseForegroundNotification() {
        Logger.debug(LOG_TAG, "App has entered the foreground.", new Object[0]);
        if (isConnected()) {
            raiseCallbacks(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        private DefaultNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            AndroidConnectivityMonitor.this.raiseCallbacks(true);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            AndroidConnectivityMonitor.this.raiseCallbacks(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class NetworkReceiver extends BroadcastReceiver {
        private boolean wasConnected;

        private NetworkReceiver() {
            this.wasConnected = false;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean isConnected = AndroidConnectivityMonitor.this.isConnected();
            if (AndroidConnectivityMonitor.this.isConnected() && !this.wasConnected) {
                AndroidConnectivityMonitor.this.raiseCallbacks(true);
            } else if (!isConnected && this.wasConnected) {
                AndroidConnectivityMonitor.this.raiseCallbacks(false);
            }
            this.wasConnected = isConnected;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void raiseCallbacks(boolean z) {
        synchronized (this.callbacks) {
            Iterator<Consumer<ConnectivityMonitor.NetworkStatus>> it = this.callbacks.iterator();
            while (it.hasNext()) {
                it.next().accept(z ? ConnectivityMonitor.NetworkStatus.REACHABLE : ConnectivityMonitor.NetworkStatus.UNREACHABLE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
