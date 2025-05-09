package co.hyperverge.hypersnapsdk.listeners;

import co.hyperverge.hypersnapsdk.objects.HVError;

/* loaded from: classes2.dex */
public interface InitializerCallback {
    void onError(HVError hVError);

    void onSuccess();
}
