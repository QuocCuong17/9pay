package com.google.android.gms.internal.vision;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzi' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public class zzml {
    public static final zzml zza;
    public static final zzml zzb;
    public static final zzml zzc;
    public static final zzml zzd;
    public static final zzml zze;
    public static final zzml zzf;
    public static final zzml zzg;
    public static final zzml zzh;
    public static final zzml zzi;
    public static final zzml zzj;
    public static final zzml zzk;
    public static final zzml zzl;
    public static final zzml zzm;
    public static final zzml zzn;
    public static final zzml zzo;
    public static final zzml zzp;
    public static final zzml zzq;
    public static final zzml zzr;
    private static final /* synthetic */ zzml[] zzu;
    private final zzmo zzs;
    private final int zzt;

    public static zzml[] values() {
        return (zzml[]) zzu.clone();
    }

    private zzml(String str, int i, zzmo zzmoVar, int i2) {
        this.zzs = zzmoVar;
        this.zzt = i2;
    }

    public final zzmo zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzml(String str, int i, zzmo zzmoVar, int i2, zzmi zzmiVar) {
        this(str, i, zzmoVar, i2);
    }

    static {
        zzml zzmlVar = new zzml("DOUBLE", 0, zzmo.DOUBLE, 1);
        zza = zzmlVar;
        zzml zzmlVar2 = new zzml("FLOAT", 1, zzmo.FLOAT, 5);
        zzb = zzmlVar2;
        final int i = 2;
        zzml zzmlVar3 = new zzml("INT64", 2, zzmo.LONG, 0);
        zzc = zzmlVar3;
        final int i2 = 3;
        zzml zzmlVar4 = new zzml("UINT64", 3, zzmo.LONG, 0);
        zzd = zzmlVar4;
        zzml zzmlVar5 = new zzml("INT32", 4, zzmo.INT, 0);
        zze = zzmlVar5;
        zzml zzmlVar6 = new zzml("FIXED64", 5, zzmo.LONG, 1);
        zzf = zzmlVar6;
        zzml zzmlVar7 = new zzml("FIXED32", 6, zzmo.INT, 5);
        zzg = zzmlVar7;
        zzml zzmlVar8 = new zzml("BOOL", 7, zzmo.BOOLEAN, 0);
        zzh = zzmlVar8;
        final zzmo zzmoVar = zzmo.STRING;
        final String str = "STRING";
        final int i3 = 8;
        zzml zzmlVar9 = new zzml(str, i3, zzmoVar, i) { // from class: com.google.android.gms.internal.vision.zzmk
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i4 = 8;
                int i5 = 2;
                zzmi zzmiVar = null;
            }
        };
        zzi = zzmlVar9;
        final zzmo zzmoVar2 = zzmo.MESSAGE;
        final String str2 = "GROUP";
        final int i4 = 9;
        zzml zzmlVar10 = new zzml(str2, i4, zzmoVar2, i2) { // from class: com.google.android.gms.internal.vision.zzmn
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i5 = 9;
                int i6 = 3;
                zzmi zzmiVar = null;
            }
        };
        zzj = zzmlVar10;
        final zzmo zzmoVar3 = zzmo.MESSAGE;
        final String str3 = "MESSAGE";
        final int i5 = 10;
        zzml zzmlVar11 = new zzml(str3, i5, zzmoVar3, i) { // from class: com.google.android.gms.internal.vision.zzmm
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i6 = 10;
                int i7 = 2;
                zzmi zzmiVar = null;
            }
        };
        zzk = zzmlVar11;
        final zzmo zzmoVar4 = zzmo.BYTE_STRING;
        final String str4 = "BYTES";
        final int i6 = 11;
        zzml zzmlVar12 = new zzml(str4, i6, zzmoVar4, i) { // from class: com.google.android.gms.internal.vision.zzmp
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i7 = 11;
                int i8 = 2;
                zzmi zzmiVar = null;
            }
        };
        zzl = zzmlVar12;
        zzml zzmlVar13 = new zzml("UINT32", 12, zzmo.INT, 0);
        zzm = zzmlVar13;
        zzml zzmlVar14 = new zzml("ENUM", 13, zzmo.ENUM, 0);
        zzn = zzmlVar14;
        zzml zzmlVar15 = new zzml("SFIXED32", 14, zzmo.INT, 5);
        zzo = zzmlVar15;
        zzml zzmlVar16 = new zzml("SFIXED64", 15, zzmo.LONG, 1);
        zzp = zzmlVar16;
        zzml zzmlVar17 = new zzml("SINT32", 16, zzmo.INT, 0);
        zzq = zzmlVar17;
        zzml zzmlVar18 = new zzml("SINT64", 17, zzmo.LONG, 0);
        zzr = zzmlVar18;
        zzu = new zzml[]{zzmlVar, zzmlVar2, zzmlVar3, zzmlVar4, zzmlVar5, zzmlVar6, zzmlVar7, zzmlVar8, zzmlVar9, zzmlVar10, zzmlVar11, zzmlVar12, zzmlVar13, zzmlVar14, zzmlVar15, zzmlVar16, zzmlVar17, zzmlVar18};
    }
}
