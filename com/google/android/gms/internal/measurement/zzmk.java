package com.google.android.gms.internal.measurement;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzmk {
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzmi zzmiVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzmiVar, sb, 0);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        zzc(i, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i2 = 1; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (Character.isUpperCase(charAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(charAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzni.zza(new zzjx(((String) obj).getBytes(zzlj.zzb))));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzka) {
            sb.append(": \"");
            sb.append(zzni.zza((zzka) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzlb) {
            sb.append(" {");
            zzd((zzlb) obj, sb, i + 2);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            zzc(i, sb);
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
            sb.append(" {");
            Map.Entry entry = (Map.Entry) obj;
            int i3 = i + 2;
            zzb(sb, i3, "key", entry.getKey());
            zzb(sb, i3, "value", entry.getValue());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            zzc(i, sb);
            sb.append("}");
            return;
        }
        sb.append(": ");
        sb.append(obj);
    }

    private static void zzc(int i, StringBuilder sb) {
        while (i > 0) {
            int i2 = 80;
            if (i <= 80) {
                i2 = i;
            }
            sb.append(zza, 0, i2);
            i -= i2;
        }
    }

    private static void zzd(zzmi zzmiVar, StringBuilder sb, int i) {
        int i2;
        boolean equals;
        Method method;
        Method method2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzmiVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i3 = 0;
        while (true) {
            i2 = 3;
            if (i3 >= length) {
                break;
            }
            Method method3 = declaredMethods[i3];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith(WorkflowRequestType.Method.GET)) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i3++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i2);
            if (!substring.endsWith(PDListAttributeObject.OWNER_LIST) || substring.endsWith("OrBuilderList") || substring.equals(PDListAttributeObject.OWNER_LIST) || (method2 = (Method) entry.getValue()) == null || !method2.getReturnType().equals(List.class)) {
                if (!substring.endsWith("Map") || substring.equals("Map") || (method = (Method) entry.getValue()) == null || !method.getReturnType().equals(Map.class) || method.isAnnotationPresent(Deprecated.class) || !Modifier.isPublic(method.getModifiers())) {
                    if (hashSet.contains("set".concat(String.valueOf(substring))) && (!substring.endsWith("Bytes") || !treeMap.containsKey(WorkflowRequestType.Method.GET.concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                        Method method4 = (Method) entry.getValue();
                        Method method5 = (Method) hashMap.get("has".concat(String.valueOf(substring)));
                        if (method4 != null) {
                            Object zzbK = zzlb.zzbK(method4, zzmiVar, new Object[0]);
                            if (method5 == null) {
                                if (zzbK instanceof Boolean) {
                                    if (!((Boolean) zzbK).booleanValue()) {
                                    }
                                    zzb(sb, i, substring, zzbK);
                                } else if (zzbK instanceof Integer) {
                                    if (((Integer) zzbK).intValue() == 0) {
                                    }
                                    zzb(sb, i, substring, zzbK);
                                } else if (zzbK instanceof Float) {
                                    if (Float.floatToRawIntBits(((Float) zzbK).floatValue()) == 0) {
                                    }
                                    zzb(sb, i, substring, zzbK);
                                } else if (!(zzbK instanceof Double)) {
                                    if (zzbK instanceof String) {
                                        equals = zzbK.equals("");
                                    } else if (zzbK instanceof zzka) {
                                        equals = zzbK.equals(zzka.zzb);
                                    } else if (zzbK instanceof zzmi) {
                                        if (zzbK == ((zzmi) zzbK).zzbV()) {
                                        }
                                        zzb(sb, i, substring, zzbK);
                                    } else {
                                        if ((zzbK instanceof Enum) && ((Enum) zzbK).ordinal() == 0) {
                                        }
                                        zzb(sb, i, substring, zzbK);
                                    }
                                    if (equals) {
                                    }
                                    zzb(sb, i, substring, zzbK);
                                } else {
                                    if (Double.doubleToRawLongBits(((Double) zzbK).doubleValue()) == 0) {
                                    }
                                    zzb(sb, i, substring, zzbK);
                                }
                            } else {
                                if (!((Boolean) zzlb.zzbK(method5, zzmiVar, new Object[0])).booleanValue()) {
                                }
                                zzb(sb, i, substring, zzbK);
                            }
                        }
                    }
                } else {
                    zzb(sb, i, substring.substring(0, substring.length() - 3), zzlb.zzbK(method, zzmiVar, new Object[0]));
                }
            } else {
                zzb(sb, i, substring.substring(0, substring.length() - 4), zzlb.zzbK(method2, zzmiVar, new Object[0]));
            }
            i2 = 3;
        }
        if (!(zzmiVar instanceof zzky)) {
            zznl zznlVar = ((zzlb) zzmiVar).zzc;
            if (zznlVar != null) {
                zznlVar.zzi(sb, i);
                return;
            }
            return;
        }
        zzks zzksVar = ((zzky) zzmiVar).zza;
        throw null;
    }
}
