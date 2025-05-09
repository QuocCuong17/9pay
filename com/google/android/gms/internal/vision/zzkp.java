package com.google.android.gms.internal.vision;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.google.android.gms.internal.vision.zzjb;
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
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public final class zzkp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzkk zzkkVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzkkVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x0227, code lost:
    
        if (((java.lang.Double) r6).doubleValue() == 0.0d) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01f1, code lost:
    
        if (((java.lang.Boolean) r6).booleanValue() == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01f3, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0204, code lost:
    
        if (((java.lang.Integer) r6).intValue() == 0) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0215, code lost:
    
        if (((java.lang.Float) r6).floatValue() == 0.0f) goto L84;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void zza(zzkk zzkkVar, StringBuilder sb, int i) {
        boolean equals;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzkkVar.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith(WorkflowRequestType.Method.GET)) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String substring = str.startsWith(WorkflowRequestType.Method.GET) ? str.substring(3) : str;
            boolean z = true;
            if (substring.endsWith(PDListAttributeObject.OWNER_LIST) && !substring.endsWith("OrBuilderList") && !substring.equals(PDListAttributeObject.OWNER_LIST)) {
                String valueOf = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(substring.substring(1, substring.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(sb, i, zza(concat), zzjb.zza(method2, zzkkVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zza(sb, i, zza(concat2), zzjb.zza(method3, zzkkVar, new Object[0]));
                }
            }
            String valueOf5 = String.valueOf(substring);
            if (((Method) hashMap2.get(valueOf5.length() != 0 ? "set".concat(valueOf5) : new String("set"))) != null) {
                if (substring.endsWith("Bytes")) {
                    String valueOf6 = String.valueOf(substring.substring(0, substring.length() - 5));
                    if (!hashMap.containsKey(valueOf6.length() != 0 ? WorkflowRequestType.Method.GET.concat(valueOf6) : new String(WorkflowRequestType.Method.GET))) {
                    }
                }
                String valueOf7 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(substring.substring(1));
                String concat3 = valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7);
                String valueOf9 = String.valueOf(substring);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? WorkflowRequestType.Method.GET.concat(valueOf9) : new String(WorkflowRequestType.Method.GET));
                String valueOf10 = String.valueOf(substring);
                Method method5 = (Method) hashMap.get(valueOf10.length() != 0 ? "has".concat(valueOf10) : new String("has"));
                if (method4 != null) {
                    Object zza = zzjb.zza(method4, zzkkVar, new Object[0]);
                    if (method5 == null) {
                        if (!(zza instanceof Boolean)) {
                            if (!(zza instanceof Integer)) {
                                if (!(zza instanceof Float)) {
                                    if (!(zza instanceof Double)) {
                                        if (zza instanceof String) {
                                            equals = zza.equals("");
                                        } else if (zza instanceof zzht) {
                                            equals = zza.equals(zzht.zza);
                                        } else {
                                            equals = !(zza instanceof zzkk) ? false : false;
                                        }
                                    }
                                }
                            }
                        }
                        if (equals) {
                            z = false;
                        }
                    } else {
                        z = ((Boolean) zzjb.zza(method5, zzkkVar, new Object[0])).booleanValue();
                    }
                    if (z) {
                        zza(sb, i, zza(concat3), zza);
                    }
                }
            }
        }
        if (zzkkVar instanceof zzjb.zzc) {
            Iterator<Map.Entry<zzjb.zzf, Object>> zzd = ((zzjb.zzc) zzkkVar).zzc.zzd();
            while (zzd.hasNext()) {
                Map.Entry<zzjb.zzf, Object> next = zzd.next();
                int i2 = next.getKey().zzb;
                StringBuilder sb2 = new StringBuilder(13);
                sb2.append("[");
                sb2.append(i2);
                sb2.append("]");
                zza(sb, i, sb2.toString(), next.getValue());
            }
        }
        zzjb zzjbVar = (zzjb) zzkkVar;
        if (zzjbVar.zzb != null) {
            zzjbVar.zzb.zza(sb, i);
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
            sb.append(zzlq.zza(zzht.zza((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzht) {
            sb.append(": \"");
            sb.append(zzlq.zza((zzht) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzjb) {
            sb.append(" {");
            zza((zzjb) obj, sb, i + 2);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
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
            return;
        }
        sb.append(": ");
        sb.append(obj.toString());
    }

    private static final String zza(String str) {
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
