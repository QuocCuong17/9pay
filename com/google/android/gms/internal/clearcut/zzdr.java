package com.google.android.gms.internal.clearcut;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.google.android.gms.internal.clearcut.zzcg;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class zzdr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzdo zzdoVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzdoVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0227, code lost:
    
        if (((java.lang.Double) r11).doubleValue() == 0.0d) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01f2, code lost:
    
        if (((java.lang.Boolean) r11).booleanValue() == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01f4, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0204, code lost:
    
        if (((java.lang.Integer) r11).intValue() == 0) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0215, code lost:
    
        if (((java.lang.Float) r11).floatValue() == 0.0f) goto L80;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void zza(zzdo zzdoVar, StringBuilder sb, int i) {
        boolean equals;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzdoVar.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith(WorkflowRequestType.Method.GET)) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            Object obj = "";
            String replaceFirst = str.replaceFirst(WorkflowRequestType.Method.GET, "");
            boolean z = true;
            if (replaceFirst.endsWith(PDListAttributeObject.OWNER_LIST) && !replaceFirst.endsWith("OrBuilderList") && !replaceFirst.equals(PDListAttributeObject.OWNER_LIST)) {
                String valueOf = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(sb, i, zzj(concat), zzcg.zza(method2, zzdoVar, new Object[0]));
                }
            }
            if (replaceFirst.endsWith("Map") && !replaceFirst.equals("Map")) {
                String valueOf3 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zza(sb, i, zzj(concat2), zzcg.zza(method3, zzdoVar, new Object[0]));
                }
            }
            String valueOf5 = String.valueOf(replaceFirst);
            if (((Method) hashMap2.get(valueOf5.length() != 0 ? "set".concat(valueOf5) : new String("set"))) != null) {
                if (replaceFirst.endsWith("Bytes")) {
                    String valueOf6 = String.valueOf(replaceFirst.substring(0, replaceFirst.length() - 5));
                    if (!hashMap.containsKey(valueOf6.length() != 0 ? WorkflowRequestType.Method.GET.concat(valueOf6) : new String(WorkflowRequestType.Method.GET))) {
                    }
                }
                String valueOf7 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(replaceFirst.substring(1));
                String concat3 = valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7);
                String valueOf9 = String.valueOf(replaceFirst);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? WorkflowRequestType.Method.GET.concat(valueOf9) : new String(WorkflowRequestType.Method.GET));
                String valueOf10 = String.valueOf(replaceFirst);
                Method method5 = (Method) hashMap.get(valueOf10.length() != 0 ? "has".concat(valueOf10) : new String("has"));
                if (method4 != null) {
                    Object zza = zzcg.zza(method4, zzdoVar, new Object[0]);
                    if (method5 == null) {
                        if (!(zza instanceof Boolean)) {
                            if (!(zza instanceof Integer)) {
                                if (!(zza instanceof Float)) {
                                    if (!(zza instanceof Double)) {
                                        if (!(zza instanceof String)) {
                                            if (zza instanceof zzbb) {
                                                obj = zzbb.zzfi;
                                            } else {
                                                equals = !(zza instanceof zzdo) ? false : false;
                                            }
                                        }
                                        equals = zza.equals(obj);
                                    }
                                }
                            }
                        }
                        if (equals) {
                            z = false;
                        }
                    } else {
                        z = ((Boolean) zzcg.zza(method5, zzdoVar, new Object[0])).booleanValue();
                    }
                    if (z) {
                        zza(sb, i, zzj(concat3), zza);
                    }
                }
            }
        }
        if (zzdoVar instanceof zzcg.zzd) {
            Iterator<Map.Entry<zzcg.zze, Object>> it = ((zzcg.zzd) zzdoVar).zzjv.iterator();
            while (it.hasNext()) {
                Map.Entry<zzcg.zze, Object> next = it.next();
                int i2 = next.getKey().number;
                StringBuilder sb2 = new StringBuilder(13);
                sb2.append("[");
                sb2.append(i2);
                sb2.append("]");
                zza(sb, i, sb2.toString(), next.getValue());
            }
        }
        zzcg zzcgVar = (zzcg) zzdoVar;
        if (zzcgVar.zzjp != null) {
            zzcgVar.zzjp.zza(sb, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zza(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zza(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzet.zzc(zzbb.zzf((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzbb) {
            sb.append(": \"");
            sb.append(zzet.zzc((zzbb) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzcg) {
            sb.append(" {");
            zza((zzcg) obj, sb, i + 2);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj.toString());
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        zza(sb, i4, "key", entry.getKey());
        zza(sb, i4, "value", entry.getValue());
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        while (i2 < i) {
            sb.append(' ');
            i2++;
        }
        sb.append("}");
    }

    private static final String zzj(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
