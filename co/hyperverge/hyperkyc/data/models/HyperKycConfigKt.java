package co.hyperverge.hyperkyc.data.models;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.HyperKyc;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HyperKycConfig.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"replaceLargeValuesWithFiles", "", "Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "context", "Landroid/content/Context;", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperKycConfigKt {
    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(3:260|(1:262)(1:265)|(1:264))|7|(1:9)|10|(1:14)|15|(1:17)|18|(6:224|225|226|(1:228)|229|(11:231|(9:233|(3:251|(1:253)(1:256)|(1:255))|239|(1:241)|242|(1:246)|247|(1:249)|250)|21|22|(6:25|26|(19:28|(1:210)(3:32|33|34)|198|199|(1:201)(1:205)|(1:203)(1:204)|36|(1:38)|39|40|(1:45)|46|(1:48)|49|(6:150|151|152|(1:154)|155|(7:157|158|159|(13:161|162|163|(3:183|(1:185)(1:188)|(1:187))(1:169)|170|(1:172)|173|(1:178)|179|(1:181)|182|53|(3:58|59|(1:61)(1:62))(1:55))|52|53|(0)(0)))|51|52|53|(0)(0))(1:211)|56|57|23)|215|216|217|218|66|(21:68|(1:147)(1:72)|74|(1:76)(1:80)|(1:78)(1:79)|81|(1:83)|84|(1:88)|89|(1:91)|92|(1:94)(1:146)|(1:96)(1:145)|97|98|99|100|(1:102)|103|(2:105|(17:107|(1:139)(1:111)|113|(1:115)(1:137)|(12:117|118|(1:120)|121|(1:125)|126|(1:128)|129|(1:131)|(1:133)|134|135)|138|118|(0)|121|(2:123|125)|126|(0)|129|(0)|(0)|134|135)(1:140))(1:141))(1:148)))|20|21|22|(1:23)|215|216|217|218|66|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x058d, code lost:
    
        if (r0 != null) goto L239;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x0431, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x0433, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0434, code lost:
    
        r2 = r7;
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x023b, code lost:
    
        if (r4 != null) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x047b, code lost:
    
        if (r11 != null) goto L194;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0601  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0607  */
    /* JADX WARN: Removed duplicated region for block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x03e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0448  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void replaceLargeValuesWithFiles(HyperKycConfig hyperKycConfig, Context context) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String canonicalName2;
        String className;
        String str3;
        String str4;
        Object obj;
        Throwable m1205exceptionOrNullimpl;
        String str5;
        String str6;
        String str7;
        Object m1202constructorimpl2;
        String str8;
        String str9;
        String str10;
        Matcher matcher;
        String str11;
        String className2;
        String className3;
        Iterator it;
        LinkedHashMap linkedHashMap;
        Iterator it2;
        String str12;
        Context context2;
        String str13;
        String str14;
        Object m1202constructorimpl3;
        String canonicalName3;
        String className4;
        String className5;
        String className6;
        HyperKycConfig hyperKycConfig2 = hyperKycConfig;
        Context context3 = context;
        Intrinsics.checkNotNullParameter(hyperKycConfig2, "<this>");
        Intrinsics.checkNotNullParameter(context3, "context");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String str15 = "Throwable().stackTrace";
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = hyperKycConfig.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str16 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str17 = hyperKycConfig2 + ".replaceLargeValuesWithFiles() called with: context = " + context3;
        if (str17 == null) {
            str17 = "null ";
        }
        sb.append(str17);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                str = "packageName";
                str2 = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = hyperKycConfig.getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str2;
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str18 = hyperKycConfig2 + ".replaceLargeValuesWithFiles() called with: context = " + context3;
                    if (str18 == null) {
                        str18 = "null ";
                    }
                    sb2.append(str18);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                Result.Companion companion4 = Result.INSTANCE;
                HashMap inputs = hyperKycConfig.getInputs();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(inputs.size()));
                it = inputs.entrySet().iterator();
                while (it.hasNext()) {
                    try {
                        Object next = it.next();
                        Object key = ((Map.Entry) next).getKey();
                        Map.Entry entry = (Map.Entry) next;
                        String str19 = (String) entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof String) {
                            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                            it2 = it;
                            HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb3 = new StringBuilder();
                            linkedHashMap = linkedHashMap2;
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, str15);
                            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null) {
                                str13 = str15;
                                str12 = key;
                            } else {
                                str13 = str15;
                                str12 = key;
                                try {
                                    str14 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                } catch (Throwable th2) {
                                    th = th2;
                                    str4 = str;
                                    str3 = str13;
                                    Result.Companion companion6 = Result.INSTANCE;
                                    obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                }
                            }
                            try {
                                Class<?> cls3 = hyperKycConfig.getClass();
                                String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                                str14 = canonicalName4 == null ? str2 : canonicalName4;
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
                                if (matcher4.find()) {
                                    str14 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str14, "replaceAll(\"\")");
                                }
                                Unit unit3 = Unit.INSTANCE;
                                if (str14.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str14 = str14.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                sb3.append(str14);
                                sb3.append(" - ");
                                String str20 = "createIntent length of " + str19 + " = " + ((String) value).length() + " bytes";
                                if (str20 == null) {
                                    str20 = "null ";
                                }
                                sb3.append(str20);
                                sb3.append(' ');
                                sb3.append("");
                                companion5.log(level2, sb3.toString());
                                if (!CoreExtsKt.isRelease()) {
                                    try {
                                        Result.Companion companion7 = Result.INSTANCE;
                                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                    } catch (Throwable th3) {
                                        Result.Companion companion8 = Result.INSTANCE;
                                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                        m1202constructorimpl3 = "";
                                    }
                                    String str21 = (String) m1202constructorimpl3;
                                    if (CoreExtsKt.isDebug()) {
                                        str4 = str;
                                        try {
                                            Intrinsics.checkNotNullExpressionValue(str21, str4);
                                            if (StringsKt.contains$default((CharSequence) str21, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                str3 = str13;
                                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                    Class<?> cls4 = hyperKycConfig.getClass();
                                                    canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                                    if (canonicalName3 == null) {
                                                        canonicalName3 = str2;
                                                    }
                                                }
                                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                                if (matcher5.find()) {
                                                    canonicalName3 = matcher5.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                                }
                                                Unit unit4 = Unit.INSTANCE;
                                                if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    canonicalName3 = canonicalName3.substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                StringBuilder sb4 = new StringBuilder();
                                                String str22 = "createIntent length of " + str19 + " = " + ((String) value).length() + " bytes";
                                                if (str22 == null) {
                                                    str22 = "null ";
                                                }
                                                sb4.append(str22);
                                                sb4.append(' ');
                                                sb4.append("");
                                                Log.println(3, canonicalName3, sb4.toString());
                                                if (((String) value).length() > HyperKyc.INSTANCE.getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release()) {
                                                    try {
                                                        context2 = context;
                                                        String saveToInternalStorage = FileExtsKt.saveToInternalStorage(context2, HyperKyc.INPUTS_CACHE_DIR, str19, (String) value);
                                                        if (saveToInternalStorage != null) {
                                                            value = saveToInternalStorage;
                                                        }
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        Result.Companion companion62 = Result.INSTANCE;
                                                        obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                                        if (m1205exceptionOrNullimpl != null) {
                                                        }
                                                    }
                                                } else {
                                                    context2 = context;
                                                }
                                            }
                                            str3 = str13;
                                            if (((String) value).length() > HyperKyc.INSTANCE.getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release()) {
                                            }
                                        } catch (Throwable th5) {
                                            th = th5;
                                            str3 = str13;
                                            Result.Companion companion622 = Result.INSTANCE;
                                            obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                        }
                                    }
                                }
                                str4 = str;
                                str3 = str13;
                                if (((String) value).length() > HyperKyc.INSTANCE.getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release()) {
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                str4 = str;
                                str3 = str13;
                                Result.Companion companion6222 = Result.INSTANCE;
                                obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                if (m1205exceptionOrNullimpl != null) {
                                }
                            }
                        } else {
                            linkedHashMap = linkedHashMap2;
                            it2 = it;
                            str12 = key;
                            str4 = str;
                            context2 = context3;
                            str3 = str15;
                        }
                        linkedHashMap2 = linkedHashMap;
                        linkedHashMap2.put(str12, value);
                        str = str4;
                        str15 = str3;
                        context3 = context2;
                        it = it2;
                        hyperKycConfig2 = hyperKycConfig;
                    } catch (Throwable th7) {
                        th = th7;
                        str3 = str15;
                        str4 = str;
                    }
                }
                str3 = str15;
                HyperKycConfig hyperKycConfig3 = hyperKycConfig2;
                str4 = str;
                hyperKycConfig3.setInputs(linkedHashMap2);
                obj = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                if (m1205exceptionOrNullimpl != null) {
                    HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                    HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, str3);
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null) {
                        str5 = str3;
                    } else {
                        str5 = str3;
                        str6 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls5 = hyperKycConfig.getClass();
                    String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                    str6 = canonicalName5 == null ? str2 : canonicalName5;
                    Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                    if (matcher6.find()) {
                        str6 = matcher6.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str6 = str6.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb5.append(str6);
                    sb5.append(" - ");
                    String str23 = "Failed shrinking inputs that were above " + HyperKyc.INSTANCE.getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release() + " bytes";
                    if (str23 == null) {
                        str23 = "null ";
                    }
                    sb5.append(str23);
                    sb5.append(' ');
                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str7 = '\n' + localizedMessage;
                    } else {
                        str7 = "";
                    }
                    sb5.append(str7);
                    companion9.log(level3, sb5.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion10 = Result.INSTANCE;
                        Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                    } catch (Throwable th8) {
                        Result.Companion companion11 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String str24 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str24, str4);
                        if (StringsKt.contains$default((CharSequence) str24, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace6, str5);
                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                            if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null) {
                                str8 = null;
                            } else {
                                str8 = null;
                                str9 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls6 = hyperKycConfig.getClass();
                            str9 = cls6 != null ? cls6.getCanonicalName() : str8;
                            if (str9 == null) {
                                str10 = str2;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
                                if (matcher.find()) {
                                    str10 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str10, "replaceAll(\"\")");
                                }
                                Unit unit6 = Unit.INSTANCE;
                                if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str10 = str10.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb6 = new StringBuilder();
                                str11 = "Failed shrinking inputs that were above " + HyperKyc.INSTANCE.getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release() + " bytes";
                                if (str11 == null) {
                                    str11 = "null ";
                                }
                                sb6.append(str11);
                                sb6.append(' ');
                                if (m1205exceptionOrNullimpl != null) {
                                    str8 = m1205exceptionOrNullimpl.getLocalizedMessage();
                                }
                                if (str8 != null) {
                                    str16 = '\n' + str8;
                                }
                                sb6.append(str16);
                                Log.println(6, str10, sb6.toString());
                                return;
                            }
                            str10 = str9;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
                            if (matcher.find()) {
                            }
                            Unit unit62 = Unit.INSTANCE;
                            if (str10.length() > 23) {
                                str10 = str10.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb62 = new StringBuilder();
                            str11 = "Failed shrinking inputs that were above " + HyperKyc.INSTANCE.getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release() + " bytes";
                            if (str11 == null) {
                            }
                            sb62.append(str11);
                            sb62.append(' ');
                            if (m1205exceptionOrNullimpl != null) {
                            }
                            if (str8 != null) {
                            }
                            sb62.append(str16);
                            Log.println(6, str10, sb62.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
        str = "packageName";
        str2 = "N/A";
        Result.Companion companion42 = Result.INSTANCE;
        HashMap inputs2 = hyperKycConfig.getInputs();
        LinkedHashMap linkedHashMap22 = new LinkedHashMap(MapsKt.mapCapacity(inputs2.size()));
        it = inputs2.entrySet().iterator();
        while (it.hasNext()) {
        }
        str3 = str15;
        HyperKycConfig hyperKycConfig32 = hyperKycConfig2;
        str4 = str;
        hyperKycConfig32.setInputs(linkedHashMap22);
        obj = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
        if (m1205exceptionOrNullimpl != null) {
        }
    }
}
