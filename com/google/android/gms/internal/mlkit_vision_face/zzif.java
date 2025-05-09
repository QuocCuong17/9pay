package com.google.android.gms.internal.mlkit_vision_face;

import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzif implements ObjectEncoder {
    static final zzif zza = new zzif();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzcq zzcqVar = new zzcq();
        zzcqVar.zza(3);
        zzb = builder.withProperty(zzcqVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzcq zzcqVar2 = new zzcq();
        zzcqVar2.zza(4);
        zzc = builder2.withProperty(zzcqVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(AnalyticsLogger.Keys.SDK_VERSION);
        zzcq zzcqVar3 = new zzcq();
        zzcqVar3.zza(5);
        zzd = builder3.withProperty(zzcqVar3.zzb()).build();
    }

    private zzif() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
