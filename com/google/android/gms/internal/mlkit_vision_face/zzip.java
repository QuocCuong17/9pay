package com.google.android.gms.internal.mlkit_vision_face;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzip implements ObjectEncoder {
    static final zzip zza = new zzip();
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
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("appId");
        zzcq zzcqVar = new zzcq();
        zzcqVar.zza(1);
        zzb = builder.withProperty(zzcqVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("appVersion");
        zzcq zzcqVar2 = new zzcq();
        zzcqVar2.zza(2);
        zzc = builder2.withProperty(zzcqVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzcq zzcqVar3 = new zzcq();
        zzcqVar3.zza(3);
        zzd = builder3.withProperty(zzcqVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzcq zzcqVar4 = new zzcq();
        zzcqVar4.zza(4);
        zze = builder4.withProperty(zzcqVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzcq zzcqVar5 = new zzcq();
        zzcqVar5.zza(5);
        zzf = builder5.withProperty(zzcqVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzcq zzcqVar6 = new zzcq();
        zzcqVar6.zza(6);
        zzg = builder6.withProperty(zzcqVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(DynamicLink.Builder.KEY_API_KEY);
        zzcq zzcqVar7 = new zzcq();
        zzcqVar7.zza(7);
        zzh = builder7.withProperty(zzcqVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzcq zzcqVar8 = new zzcq();
        zzcqVar8.zza(8);
        zzi = builder8.withProperty(zzcqVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzcq zzcqVar9 = new zzcq();
        zzcqVar9.zza(9);
        zzj = builder9.withProperty(zzcqVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzcq zzcqVar10 = new zzcq();
        zzcqVar10.zza(10);
        zzk = builder10.withProperty(zzcqVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzcq zzcqVar11 = new zzcq();
        zzcqVar11.zza(11);
        zzl = builder11.withProperty(zzcqVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzcq zzcqVar12 = new zzcq();
        zzcqVar12.zza(12);
        zzm = builder12.withProperty(zzcqVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzcq zzcqVar13 = new zzcq();
        zzcqVar13.zza(13);
        zzn = builder13.withProperty(zzcqVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzcq zzcqVar14 = new zzcq();
        zzcqVar14.zza(14);
        zzo = builder14.withProperty(zzcqVar14.zzb()).build();
    }

    private zzip() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzmv zzmvVar = (zzmv) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzmvVar.zzg());
        objectEncoderContext2.add(zzc, zzmvVar.zzh());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, zzmvVar.zzj());
        objectEncoderContext2.add(zzf, zzmvVar.zzk());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, zzmvVar.zza());
        objectEncoderContext2.add(zzj, zzmvVar.zzi());
        objectEncoderContext2.add(zzk, zzmvVar.zzb());
        objectEncoderContext2.add(zzl, zzmvVar.zzd());
        objectEncoderContext2.add(zzm, zzmvVar.zzc());
        objectEncoderContext2.add(zzn, zzmvVar.zze());
        objectEncoderContext2.add(zzo, zzmvVar.zzf());
    }
}
