package com.google.firebase.storage;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCanceledListener;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes4.dex */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda0 implements OnCanceledListener {
    public final /* synthetic */ CancellationTokenSource f$0;

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.f$0.cancel();
    }
}
