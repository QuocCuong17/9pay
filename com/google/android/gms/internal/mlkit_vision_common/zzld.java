package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzld implements zzkw {
    private Provider zza;
    private final Provider zzb;
    private final zzkr zzc;

    public zzld(Context context, zzkr zzkrVar) {
        this.zzc = zzkrVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of(WorkflowRequestType.JSON))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzla
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of(WorkflowRequestType.JSON), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzlc
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzlb
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzkz
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static Event zzb(zzkr zzkrVar, zzkp zzkpVar) {
        return Event.ofTelemetry(zzkpVar.zzd(zzkrVar.zza(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkw
    public final void zza(zzkp zzkpVar) {
        if (this.zzc.zza() != 0) {
            ((Transport) this.zzb.get()).send(zzb(this.zzc, zzkpVar));
            return;
        }
        Provider provider = this.zza;
        if (provider != null) {
            ((Transport) provider.get()).send(zzb(this.zzc, zzkpVar));
        }
    }
}
