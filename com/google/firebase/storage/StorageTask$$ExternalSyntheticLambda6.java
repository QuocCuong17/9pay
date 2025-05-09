package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes4.dex */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda6 implements OnFailureListener {
    public final /* synthetic */ TaskCompletionSource f$0;

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.f$0.setException(exc);
    }
}
