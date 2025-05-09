package com.google.android.gms.internal.mlkit_vision_face;

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

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzok implements zzob {
    private Provider zza;
    private final Provider zzb;
    private final zznt zzc;

    public zzok(Context context, zznt zzntVar) {
        this.zzc = zzntVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of(WorkflowRequestType.JSON))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_face.zzoh
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of(WorkflowRequestType.JSON), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_face.zzoj
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_face.zzoi
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_face.zzog
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static Event zzb(zznt zzntVar, zznr zznrVar) {
        int zza = zzntVar.zza();
        if (zznrVar.zza() == 0) {
            return Event.ofTelemetry(zznrVar.zze(zza, false));
        }
        return Event.ofData(zznrVar.zze(zza, false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzob
    public final void zza(zznr zznrVar) {
        if (this.zzc.zza() != 0) {
            ((Transport) this.zzb.get()).send(zzb(this.zzc, zznrVar));
            return;
        }
        Provider provider = this.zza;
        if (provider != null) {
            ((Transport) provider.get()).send(zzb(this.zzc, zznrVar));
        }
    }
}
