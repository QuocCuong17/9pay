package com.google.android.gms.internal.mlkit_vision_face;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzik implements ObjectEncoder {
    static final zzik zza = new zzik();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfo");
        zzcq zzcqVar = new zzcq();
        zzcqVar.zza(1);
        zzb = builder.withProperty(zzcqVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("nnapiInfo");
        zzcq zzcqVar2 = new zzcq();
        zzcqVar2.zza(2);
        zzc = builder2.withProperty(zzcqVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("gpuInfo");
        zzcq zzcqVar3 = new zzcq();
        zzcqVar3.zza(3);
        zzd = builder3.withProperty(zzcqVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("pipelineIdentifier");
        zzcq zzcqVar4 = new zzcq();
        zzcqVar4.zza(4);
        zze = builder4.withProperty(zzcqVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("acceptedConfigurations");
        zzcq zzcqVar5 = new zzcq();
        zzcqVar5.zza(5);
        zzf = builder5.withProperty(zzcqVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("action");
        zzcq zzcqVar6 = new zzcq();
        zzcqVar6.zza(6);
        zzg = builder6.withProperty(zzcqVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("status");
        zzcq zzcqVar7 = new zzcq();
        zzcqVar7.zza(7);
        zzh = builder7.withProperty(zzcqVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customErrors");
        zzcq zzcqVar8 = new zzcq();
        zzcqVar8.zza(8);
        zzi = builder8.withProperty(zzcqVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("benchmarkStatus");
        zzcq zzcqVar9 = new zzcq();
        zzcqVar9.zza(9);
        zzj = builder9.withProperty(zzcqVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("validationTestResult");
        zzcq zzcqVar10 = new zzcq();
        zzcqVar10.zza(10);
        zzk = builder10.withProperty(zzcqVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("timestampUs");
        zzcq zzcqVar11 = new zzcq();
        zzcqVar11.zza(11);
        zzl = builder11.withProperty(zzcqVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("elapsedUs");
        zzcq zzcqVar12 = new zzcq();
        zzcqVar12.zza(12);
        zzm = builder12.withProperty(zzcqVar12.zzb()).build();
    }

    private zzik() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
