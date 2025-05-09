package com.google.android.gms.internal.mlkit_vision_common;

import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
final class zzfp implements ObjectEncoder {
    static final zzfp zza = new zzfp();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzae zzaeVar = new zzae();
        zzaeVar.zza(3);
        zzb = builder.withProperty(zzaeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.zza(4);
        zzc = builder2.withProperty(zzaeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(AnalyticsLogger.Keys.SDK_VERSION);
        zzae zzaeVar3 = new zzae();
        zzaeVar3.zza(5);
        zzd = builder3.withProperty(zzaeVar3.zzb()).build();
    }

    private zzfp() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
