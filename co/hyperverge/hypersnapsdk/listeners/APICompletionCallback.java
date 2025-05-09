package co.hyperverge.hypersnapsdk.listeners;

import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;

/* loaded from: classes2.dex */
public interface APICompletionCallback {
    void onResult(HVError hVError, HVResponse hVResponse);
}
