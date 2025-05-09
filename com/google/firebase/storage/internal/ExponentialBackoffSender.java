package com.google.firebase.storage.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.network.NetworkRequest;
import java.util.Random;

/* loaded from: classes4.dex */
public class ExponentialBackoffSender {
    private static final int MAXIMUM_WAIT_TIME_MILLI = 30000;
    private static final int NETWORK_STATUS_POLL_INTERVAL = 1000;
    public static final int RND_MAX = 250;
    private static final String TAG = "ExponenentialBackoff";
    private final InteropAppCheckTokenProvider appCheckProvider;
    private final InternalAuthProvider authProvider;
    private volatile boolean canceled;
    private final Context context;
    private long retryTime;
    private static final Random random = new Random();
    static Sleeper sleeper = new SleeperImpl();
    static Clock clock = DefaultClock.getInstance();

    public boolean isRetryableError(int i) {
        return (i >= 500 && i < 600) || i == -2 || i == 429 || i == 408;
    }

    public ExponentialBackoffSender(Context context, InternalAuthProvider internalAuthProvider, InteropAppCheckTokenProvider interopAppCheckTokenProvider, long j) {
        this.context = context;
        this.authProvider = internalAuthProvider;
        this.appCheckProvider = interopAppCheckTokenProvider;
        this.retryTime = j;
    }

    public void sendWithExponentialBackoff(NetworkRequest networkRequest) {
        sendWithExponentialBackoff(networkRequest, true);
    }

    public void sendWithExponentialBackoff(NetworkRequest networkRequest, boolean z) {
        Preconditions.checkNotNull(networkRequest);
        long elapsedRealtime = clock.elapsedRealtime() + this.retryTime;
        if (z) {
            networkRequest.performRequest(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider), this.context);
        } else {
            networkRequest.performRequestStart(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider));
        }
        int i = 1000;
        while (clock.elapsedRealtime() + i <= elapsedRealtime && !networkRequest.isResultSuccess() && isRetryableError(networkRequest.getResultCode())) {
            try {
                sleeper.sleep(random.nextInt(250) + i);
                if (i < MAXIMUM_WAIT_TIME_MILLI) {
                    if (networkRequest.getResultCode() != -2) {
                        i *= 2;
                        Log.w(TAG, "network error occurred, backing off/sleeping.");
                    } else {
                        Log.w(TAG, "network unavailable, sleeping.");
                        i = 1000;
                    }
                }
                if (this.canceled) {
                    return;
                }
                networkRequest.reset();
                if (z) {
                    networkRequest.performRequest(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider), this.context);
                } else {
                    networkRequest.performRequestStart(Util.getCurrentAuthToken(this.authProvider), Util.getCurrentAppCheckToken(this.appCheckProvider));
                }
            } catch (InterruptedException unused) {
                Log.w(TAG, "thread interrupted during exponential backoff.");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void cancel() {
        this.canceled = true;
    }

    public void reset() {
        this.canceled = false;
    }
}
