package io.grpc.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import com.google.common.base.Preconditions;
import dev.fluttercommunity.plus.connectivity.ConnectivityBroadcastReceiver;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.InternalManagedChannelProvider;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannelProvider;
import io.grpc.MethodDescriptor;
import io.grpc.internal.GrpcUtil;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public final class AndroidChannelBuilder extends ForwardingChannelBuilder<AndroidChannelBuilder> {
    private static final String LOG_TAG = "AndroidChannelBuilder";

    @Nullable
    private static final ManagedChannelProvider OKHTTP_CHANNEL_PROVIDER = findOkHttp();

    @Nullable
    private Context context;
    private final ManagedChannelBuilder<?> delegateBuilder;

    private static ManagedChannelProvider findOkHttp() {
        try {
            try {
                try {
                    ManagedChannelProvider managedChannelProvider = (ManagedChannelProvider) Class.forName("io.grpc.okhttp.OkHttpChannelProvider").asSubclass(ManagedChannelProvider.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (InternalManagedChannelProvider.isAvailable(managedChannelProvider)) {
                        return managedChannelProvider;
                    }
                    Log.w(LOG_TAG, "OkHttpChannelProvider.isAvailable() returned false");
                    return null;
                } catch (Exception e) {
                    Log.w(LOG_TAG, "Failed to construct OkHttpChannelProvider", e);
                    return null;
                }
            } catch (ClassCastException e2) {
                Log.w(LOG_TAG, "Couldn't cast OkHttpChannelProvider to ManagedChannelProvider", e2);
                return null;
            }
        } catch (ClassNotFoundException e3) {
            Log.w(LOG_TAG, "Failed to find OkHttpChannelProvider", e3);
            return null;
        }
    }

    public static AndroidChannelBuilder forTarget(String target) {
        return new AndroidChannelBuilder(target);
    }

    public static AndroidChannelBuilder forAddress(String name, int port) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(name, port));
    }

    @Deprecated
    public static AndroidChannelBuilder fromBuilder(ManagedChannelBuilder<?> builder) {
        return usingBuilder(builder);
    }

    public static AndroidChannelBuilder usingBuilder(ManagedChannelBuilder<?> builder) {
        return new AndroidChannelBuilder(builder);
    }

    private AndroidChannelBuilder(String target) {
        ManagedChannelProvider managedChannelProvider = OKHTTP_CHANNEL_PROVIDER;
        if (managedChannelProvider == null) {
            throw new UnsupportedOperationException("Unable to load OkHttpChannelProvider");
        }
        this.delegateBuilder = InternalManagedChannelProvider.builderForTarget(managedChannelProvider, target);
    }

    private AndroidChannelBuilder(ManagedChannelBuilder<?> delegateBuilder) {
        this.delegateBuilder = (ManagedChannelBuilder) Preconditions.checkNotNull(delegateBuilder, "delegateBuilder");
    }

    public AndroidChannelBuilder context(Context context) {
        this.context = context;
        return this;
    }

    @Override // io.grpc.ForwardingChannelBuilder
    protected ManagedChannelBuilder<?> delegate() {
        return this.delegateBuilder;
    }

    @Override // io.grpc.ForwardingChannelBuilder, io.grpc.ManagedChannelBuilder
    public ManagedChannel build() {
        return new AndroidChannel(this.delegateBuilder.build(), this.context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class AndroidChannel extends ManagedChannel {

        @Nullable
        private final ConnectivityManager connectivityManager;

        @Nullable
        private final Context context;
        private final ManagedChannel delegate;
        private final Object lock = new Object();
        private Runnable unregisterRunnable;

        AndroidChannel(final ManagedChannel delegate, @Nullable Context context) {
            this.delegate = delegate;
            this.context = context;
            if (context != null) {
                this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                try {
                    configureNetworkMonitoring();
                    return;
                } catch (SecurityException e) {
                    Log.w(AndroidChannelBuilder.LOG_TAG, "Failed to configure network monitoring. Does app have ACCESS_NETWORK_STATE permission?", e);
                    return;
                }
            }
            this.connectivityManager = null;
        }

        private void configureNetworkMonitoring() {
            if (Build.VERSION.SDK_INT >= 24 && this.connectivityManager != null) {
                final DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback();
                this.connectivityManager.registerDefaultNetworkCallback(defaultNetworkCallback);
                this.unregisterRunnable = new Runnable() { // from class: io.grpc.android.AndroidChannelBuilder.AndroidChannel.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AndroidChannel.this.connectivityManager.unregisterNetworkCallback(defaultNetworkCallback);
                    }
                };
            } else {
                final NetworkReceiver networkReceiver = new NetworkReceiver();
                this.context.registerReceiver(networkReceiver, new IntentFilter(ConnectivityBroadcastReceiver.CONNECTIVITY_ACTION));
                this.unregisterRunnable = new Runnable() { // from class: io.grpc.android.AndroidChannelBuilder.AndroidChannel.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AndroidChannel.this.context.unregisterReceiver(networkReceiver);
                    }
                };
            }
        }

        private void unregisterNetworkListener() {
            synchronized (this.lock) {
                Runnable runnable = this.unregisterRunnable;
                if (runnable != null) {
                    runnable.run();
                    this.unregisterRunnable = null;
                }
            }
        }

        @Override // io.grpc.ManagedChannel
        public ManagedChannel shutdown() {
            unregisterNetworkListener();
            return this.delegate.shutdown();
        }

        @Override // io.grpc.ManagedChannel
        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override // io.grpc.ManagedChannel
        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override // io.grpc.ManagedChannel
        public ManagedChannel shutdownNow() {
            unregisterNetworkListener();
            return this.delegate.shutdownNow();
        }

        @Override // io.grpc.ManagedChannel
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return this.delegate.awaitTermination(timeout, unit);
        }

        @Override // io.grpc.Channel
        public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
            return this.delegate.newCall(methodDescriptor, callOptions);
        }

        @Override // io.grpc.Channel
        public String authority() {
            return this.delegate.authority();
        }

        @Override // io.grpc.ManagedChannel
        public ConnectivityState getState(boolean requestConnection) {
            return this.delegate.getState(requestConnection);
        }

        @Override // io.grpc.ManagedChannel
        public void notifyWhenStateChanged(ConnectivityState source, Runnable callback) {
            this.delegate.notifyWhenStateChanged(source, callback);
        }

        @Override // io.grpc.ManagedChannel
        public void resetConnectBackoff() {
            this.delegate.resetConnectBackoff();
        }

        @Override // io.grpc.ManagedChannel
        public void enterIdle() {
            this.delegate.enterIdle();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
            private DefaultNetworkCallback() {
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                AndroidChannel.this.delegate.enterIdle();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onBlockedStatusChanged(Network network, boolean blocked) {
                if (blocked) {
                    return;
                }
                AndroidChannel.this.delegate.enterIdle();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public class NetworkReceiver extends BroadcastReceiver {
            private boolean isConnected;

            private NetworkReceiver() {
                this.isConnected = false;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                boolean z = this.isConnected;
                boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
                this.isConnected = z2;
                if (!z2 || z) {
                    return;
                }
                AndroidChannel.this.delegate.enterIdle();
            }
        }
    }
}
