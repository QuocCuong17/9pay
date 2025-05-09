package co.hyperverge.hypersnapsdk.listeners;

import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import java.io.Serializable;

/* loaded from: classes2.dex */
public interface CaptureCompletionHandler extends Serializable {
    void onResult(HVError hVError, HVResponse hVResponse);
}
