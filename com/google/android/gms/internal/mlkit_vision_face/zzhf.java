package com.google.android.gms.internal.mlkit_vision_face;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzhf implements ObjectEncoder {
    static final zzhf zza = new zzhf();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzcq zzcqVar = new zzcq();
        zzcqVar.zza(1);
        zzb = builder.withProperty(zzcqVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("options");
        zzcq zzcqVar2 = new zzcq();
        zzcqVar2.zza(2);
        zzc = builder2.withProperty(zzcqVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("imageInfo");
        zzcq zzcqVar3 = new zzcq();
        zzcqVar3.zza(3);
        zzd = builder3.withProperty(zzcqVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("detectorOptions");
        zzcq zzcqVar4 = new zzcq();
        zzcqVar4.zza(4);
        zze = builder4.withProperty(zzcqVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("contourDetectedFaces");
        zzcq zzcqVar5 = new zzcq();
        zzcqVar5.zza(5);
        zzf = builder5.withProperty(zzcqVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("nonContourDetectedFaces");
        zzcq zzcqVar6 = new zzcq();
        zzcqVar6.zza(6);
        zzg = builder6.withProperty(zzcqVar6.zzb()).build();
    }

    private zzhf() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzll zzllVar = (zzll) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzllVar.zzc());
        objectEncoderContext2.add(zzc, (Object) null);
        objectEncoderContext2.add(zzd, zzllVar.zzb());
        objectEncoderContext2.add(zze, zzllVar.zza());
        objectEncoderContext2.add(zzf, zzllVar.zzd());
        objectEncoderContext2.add(zzg, zzllVar.zze());
    }
}
