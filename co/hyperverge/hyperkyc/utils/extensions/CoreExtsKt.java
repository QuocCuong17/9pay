package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* compiled from: CoreExts.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\u001a\u0010\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00020\u0002H\u0000\u001a\u0016\u0010\u0010\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0000\u001a\u0016\u0010\u0014\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0000\u001a\b\u0010\u0015\u001a\u00020\u0004H\u0000\u001a\b\u0010\u0016\u001a\u00020\u0004H\u0001\u001a\b\u0010\u0017\u001a\u00020\u0002H\u0000\u001a:\u0010\u0018\u001a\u00020\u00132\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u001a\"\u00020\u001b2\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00130\u001d¢\u0006\u0002\b\u001eH\u0000¢\u0006\u0002\u0010\u001f\u001a8\u0010 \u001a\"\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\"H\"0!j\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\"H\"`#\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u0001H\u0000\u001a\u000e\u0010$\u001a\u0004\u0018\u00010\u0002*\u00020%H\u0000\u001a*\u0010&\u001a\u00020\u0002\"\b\b\u0000\u0010'*\u00020(*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H'0)2\u0006\u0010*\u001a\u00020\u0002H\u0000\u001a\u001a\u0010+\u001a\u00020\u0013*\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0000\u001a\f\u0010,\u001a\u00020\u0004*\u00020-H\u0000\u001a/\u0010.\u001a\u0004\u0018\u0001H\u000b\"\u0004\b\u0000\u0010\u000b*\u0004\u0018\u0001H\u000b2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00040\u001dH\u0000¢\u0006\u0002\u00100\u001a\u0010\u00101\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u0002H\u0000\u001am\u00102\u001a\u0004\u0018\u0001H3\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u00103*\b\u0012\u0004\u0012\u0002H\u000b042\u0006\u00105\u001a\u0002H32:\u00106\u001a6\u0012\u0015\u0012\u0013\u0018\u0001H3¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(;\u0012\u0006\u0012\u0004\u0018\u0001H307H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010<\u001a.\u0010=\u001a\u00020\u0004*\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020>2\b\u0010*\u001a\u0004\u0018\u00010\u00022\b\u0010?\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\f\u0010@\u001a\u00020\u0002*\u00020\u0002H\u0000\u001a\f\u0010A\u001a\u00020\u0002*\u00020\u0002H\u0000\u001a\f\u0010B\u001a\u00020\u0002*\u00020CH\u0000\u001a\f\u0010D\u001a\u00020\u0002*\u00020\u0002H\u0000\u001a*\u0010E\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00020\u00020\u0001\"\b\b\u0000\u0010\u000b*\u00020(*\b\u0012\u0004\u0012\u0002H\u000b0FH\u0000\"\u001c\u0010\u0000\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001b\u0010\u0003\u001a\u00020\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0003\u0010\u0005\"\u001b\u0010\b\u001a\u00020\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\u0007\u001a\u0004\b\b\u0010\u0005\"\u001e\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\u0002H\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006G"}, d2 = {"alpha3Codes", "", "", "isDebug", "", "()Z", "isDebug$delegate", "Lkotlin/Lazy;", "isRelease", "isRelease$delegate", "exhaustive", "T", "getExhaustive", "(Ljava/lang/Object;)Ljava/lang/Object;", "deviceLanguage", "kotlin.jvm.PlatformType", "ifDebug", "block", "Lkotlin/Function0;", "", "ifRelease", "isAPI24OrAbove", "isAPI33OrAbove", "randomUUID", "withViews", "views", "", "Landroid/view/View;", "action", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "([Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "copy", "Ljava/util/ArrayList;", "E", "Lkotlin/collections/ArrayList;", "getCountryId", "Landroid/content/Context;", "getStringValue", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, "", "", "key", "ifNot", "isNetworkError", "", "nullIf", "predicate", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "nullIfBlank", "nullableFold", "R", "", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "acc", "element", "(Ljava/lang/Iterable;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "putIfNotNull", "", "value", "removeCurlyBraces", "titleCase", "toHexString", "", "toMD5Hash", "typeNames", "", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CoreExtsKt {
    private static /* synthetic */ List<? extends List<String>> alpha3Codes;
    private static final Lazy isDebug$delegate = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt$isDebug$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return false;
        }
    });
    private static final Lazy isRelease$delegate = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt$isRelease$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(!CoreExtsKt.isDebug());
        }
    });

    public static final /* synthetic */ Object getExhaustive(Object obj) {
        return obj;
    }

    public static final /* synthetic */ ArrayList copy(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ArrayList(list);
    }

    public static final /* synthetic */ List typeNames(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Collection collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getClass().getSimpleName());
        }
        return arrayList;
    }

    public static final /* synthetic */ String titleCase(String str) {
        String valueOf;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (!(str.length() > 0)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char charAt = str.charAt(0);
        if (Character.isLowerCase(charAt)) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            valueOf = CharsKt.titlecase(charAt, locale);
        } else {
            valueOf = String.valueOf(charAt);
        }
        sb.append((Object) valueOf);
        String substring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        sb.append(substring);
        return sb.toString();
    }

    public static final /* synthetic */ Object nullIf(Object obj, Function1 predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (obj == null || ((Boolean) predicate.invoke(obj)).booleanValue()) {
            return null;
        }
        return obj;
    }

    public static final /* synthetic */ String nullIfBlank(String str) {
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return null;
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:178:0x0151, code lost:
    
        if (r0 != null) goto L303;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02e4, code lost:
    
        if (r10 != null) goto L365;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0406 A[Catch: all -> 0x046a, TryCatch #6 {all -> 0x046a, blocks: (B:120:0x03db, B:124:0x03f5, B:126:0x0406, B:127:0x040d, B:129:0x0415, B:132:0x041c, B:133:0x0424, B:136:0x0448, B:138:0x03e3, B:140:0x03e9, B:153:0x0461, B:154:0x0469), top: B:68:0x026c }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0415 A[Catch: all -> 0x046a, TryCatch #6 {all -> 0x046a, blocks: (B:120:0x03db, B:124:0x03f5, B:126:0x0406, B:127:0x040d, B:129:0x0415, B:132:0x041c, B:133:0x0424, B:136:0x0448, B:138:0x03e3, B:140:0x03e9, B:153:0x0461, B:154:0x0469), top: B:68:0x026c }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0118 A[Catch: all -> 0x008e, TRY_LEAVE, TryCatch #10 {all -> 0x008e, blocks: (B:209:0x007d, B:212:0x0084, B:18:0x0092, B:22:0x00ac, B:27:0x01cb, B:29:0x01e0, B:31:0x01fe, B:32:0x0207, B:37:0x0216, B:38:0x0229, B:40:0x022f, B:42:0x024f, B:61:0x0259, B:62:0x025c, B:65:0x0201, B:66:0x025d, B:67:0x0268, B:70:0x026e, B:74:0x0295, B:76:0x02d4, B:82:0x02ed, B:84:0x02f3, B:89:0x02ff, B:91:0x0310, B:92:0x0317, B:94:0x031f, B:97:0x0326, B:98:0x032e, B:101:0x0351, B:106:0x0398, B:109:0x039f, B:111:0x03a7, B:115:0x03b9, B:117:0x03d1, B:148:0x038e, B:161:0x0109, B:164:0x0110, B:166:0x0118, B:171:0x012d, B:173:0x0143, B:179:0x0168, B:181:0x0179, B:182:0x0180, B:184:0x0188, B:187:0x018f, B:188:0x0197, B:191:0x01af, B:195:0x0158, B:197:0x015e, B:204:0x00ff, B:34:0x0209, B:57:0x0256, B:105:0x036b), top: B:208:0x007d, inners: #2, #4, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01e0 A[Catch: all -> 0x008e, TryCatch #10 {all -> 0x008e, blocks: (B:209:0x007d, B:212:0x0084, B:18:0x0092, B:22:0x00ac, B:27:0x01cb, B:29:0x01e0, B:31:0x01fe, B:32:0x0207, B:37:0x0216, B:38:0x0229, B:40:0x022f, B:42:0x024f, B:61:0x0259, B:62:0x025c, B:65:0x0201, B:66:0x025d, B:67:0x0268, B:70:0x026e, B:74:0x0295, B:76:0x02d4, B:82:0x02ed, B:84:0x02f3, B:89:0x02ff, B:91:0x0310, B:92:0x0317, B:94:0x031f, B:97:0x0326, B:98:0x032e, B:101:0x0351, B:106:0x0398, B:109:0x039f, B:111:0x03a7, B:115:0x03b9, B:117:0x03d1, B:148:0x038e, B:161:0x0109, B:164:0x0110, B:166:0x0118, B:171:0x012d, B:173:0x0143, B:179:0x0168, B:181:0x0179, B:182:0x0180, B:184:0x0188, B:187:0x018f, B:188:0x0197, B:191:0x01af, B:195:0x0158, B:197:0x015e, B:204:0x00ff, B:34:0x0209, B:57:0x0256, B:105:0x036b), top: B:208:0x007d, inners: #2, #4, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x026e A[Catch: all -> 0x008e, TryCatch #10 {all -> 0x008e, blocks: (B:209:0x007d, B:212:0x0084, B:18:0x0092, B:22:0x00ac, B:27:0x01cb, B:29:0x01e0, B:31:0x01fe, B:32:0x0207, B:37:0x0216, B:38:0x0229, B:40:0x022f, B:42:0x024f, B:61:0x0259, B:62:0x025c, B:65:0x0201, B:66:0x025d, B:67:0x0268, B:70:0x026e, B:74:0x0295, B:76:0x02d4, B:82:0x02ed, B:84:0x02f3, B:89:0x02ff, B:91:0x0310, B:92:0x0317, B:94:0x031f, B:97:0x0326, B:98:0x032e, B:101:0x0351, B:106:0x0398, B:109:0x039f, B:111:0x03a7, B:115:0x03b9, B:117:0x03d1, B:148:0x038e, B:161:0x0109, B:164:0x0110, B:166:0x0118, B:171:0x012d, B:173:0x0143, B:179:0x0168, B:181:0x0179, B:182:0x0180, B:184:0x0188, B:187:0x018f, B:188:0x0197, B:191:0x01af, B:195:0x0158, B:197:0x015e, B:204:0x00ff, B:34:0x0209, B:57:0x0256, B:105:0x036b), top: B:208:0x007d, inners: #2, #4, #9 }] */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v28 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ String getCountryId(Context context) {
        Object obj;
        String canonicalName;
        String str;
        Object m1202constructorimpl;
        boolean z;
        CharSequence charSequence;
        String str2;
        ?? r12;
        String canonicalName2;
        String className;
        String str3;
        Object obj2;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        String canonicalName3;
        String str6;
        Matcher matcher;
        String className2;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = context.getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            if (matcher2.find()) {
                canonicalName = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            boolean z2 = false;
            boolean z3 = false;
            if (canonicalName.length() > 23) {
                try {
                    if (Build.VERSION.SDK_INT < 26) {
                        canonicalName = canonicalName.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                } catch (Throwable th) {
                    th = th;
                    obj = null;
                    Result.Companion companion3 = Result.INSTANCE;
                    Object m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    return (String) (Result.m1208isFailureimpl(m1202constructorimpl3) ? obj : m1202constructorimpl3);
                }
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str7 = context + ".getCountryId() called";
            if (str7 == null) {
                str7 = "null ";
            }
            sb.append(str7);
            sb.append(' ');
            sb.append("");
            companion2.log(level, sb.toString());
            if (isRelease()) {
                charSequence = "co.hyperverge";
                str = "null ";
            } else {
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    str = "null ";
                    z2 = false;
                    try {
                        z = false;
                        z2 = false;
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th2) {
                        th = th2;
                        Result.Companion companion5 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        z = z2;
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (isDebug()) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str = "null ";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (isDebug()) {
                    charSequence = "co.hyperverge";
                    z3 = z;
                } else {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str2 = "N/A";
                    Object obj3 = null;
                    r12 = 2;
                    r12 = 2;
                    try {
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null) {
                                try {
                                    canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                } catch (Throwable th4) {
                                    th = th4;
                                    obj = null;
                                    Result.Companion companion32 = Result.INSTANCE;
                                    Object m1202constructorimpl32 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    return (String) (Result.m1208isFailureimpl(m1202constructorimpl32) ? obj : m1202constructorimpl32);
                                }
                            }
                            Class<?> cls2 = context.getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str2;
                            }
                            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                            if (matcher3.find()) {
                                canonicalName2 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                            }
                            if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName2 = canonicalName2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String str8 = context + ".getCountryId() called";
                            if (str8 == null) {
                                str8 = str;
                            }
                            sb2.append(str8);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, canonicalName2, sb2.toString());
                        }
                        Object systemService = context.getSystemService(WorkflowModule.Properties.Section.Component.Keyboard.PHONE);
                        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                        String networkCountryIso = ((TelephonyManager) systemService).getNetworkCountryIso();
                        if (alpha3Codes == null) {
                            InputStream openRawResource = context.getResources().openRawResource(R.raw.country_codes);
                            Intrinsics.checkNotNullExpressionValue(openRawResource, "resources.openRawResource(R.raw.country_codes)");
                            Reader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
                            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                            try {
                                List<String> readLines = TextStreamsKt.readLines(bufferedReader);
                                obj3 = null;
                                CloseableKt.closeFinally(bufferedReader, null);
                                List<String> list = readLines;
                                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    arrayList.add(StringsKt.split$default((CharSequence) it.next(), new String[]{","}, false, 0, 6, (Object) null));
                                }
                                alpha3Codes = arrayList;
                            } finally {
                            }
                        }
                        List<? extends List<String>> list2 = alpha3Codes;
                        Intrinsics.checkNotNull(list2);
                        obj = r12;
                        for (Object obj4 : list2) {
                            try {
                                String lowerCase = ((String) ((List) obj4).get(0)).toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                                if (Intrinsics.areEqual(StringsKt.trim((CharSequence) lowerCase).toString(), networkCountryIso)) {
                                    String lowerCase2 = ((String) ((List) obj4).get(1)).toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                                    String obj5 = StringsKt.trim((CharSequence) lowerCase2).toString();
                                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb3 = new StringBuilder();
                                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                                        str3 = "Throwable().stackTrace";
                                    } else {
                                        str3 = "Throwable().stackTrace";
                                        obj2 = null;
                                        try {
                                            str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        } catch (Throwable th5) {
                                            th = th5;
                                            obj = obj2;
                                            Result.Companion companion322 = Result.INSTANCE;
                                            Object m1202constructorimpl322 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            return (String) (Result.m1208isFailureimpl(m1202constructorimpl322) ? obj : m1202constructorimpl322);
                                        }
                                    }
                                    Class<?> cls3 = context.getClass();
                                    String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                                    str4 = canonicalName4 == null ? str2 : canonicalName4;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                    if (matcher4.find()) {
                                        str4 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                    }
                                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str4 = str4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb3.append(str4);
                                    sb3.append(" - ");
                                    String str9 = "getCountryId() alpha2Code: " + networkCountryIso + ", alpha3Code: " + obj5;
                                    if (str9 == null) {
                                        str9 = str;
                                    }
                                    sb3.append(str9);
                                    sb3.append(' ');
                                    sb3.append("");
                                    companion6.log(level2, sb3.toString());
                                    if (!isRelease()) {
                                        try {
                                            Result.Companion companion7 = Result.INSTANCE;
                                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                        } catch (Throwable th6) {
                                            Result.Companion companion8 = Result.INSTANCE;
                                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                                        }
                                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                            m1202constructorimpl2 = "";
                                        }
                                        String packageName3 = (String) m1202constructorimpl2;
                                        if (isDebug()) {
                                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                            obj2 = null;
                                            if (StringsKt.contains$default((CharSequence) packageName3, charSequence, false, 2, (Object) null)) {
                                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                                    str5 = null;
                                                } else {
                                                    str5 = null;
                                                    canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                    if (canonicalName3 != null) {
                                                        str6 = canonicalName3;
                                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                                        if (matcher.find()) {
                                                            str6 = matcher.replaceAll("");
                                                            Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                                        }
                                                        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                            str6 = str6.substring(0, 23);
                                                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                                        }
                                                        StringBuilder sb4 = new StringBuilder();
                                                        String str10 = "getCountryId() alpha2Code: " + networkCountryIso + ", alpha3Code: " + obj5;
                                                        sb4.append(str10 == null ? str : str10);
                                                        sb4.append(' ');
                                                        sb4.append("");
                                                        Log.println(3, str6, sb4.toString());
                                                    }
                                                }
                                                Class<?> cls4 = context.getClass();
                                                canonicalName3 = cls4 != null ? cls4.getCanonicalName() : str5;
                                                if (canonicalName3 == null) {
                                                    str6 = str2;
                                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                                    if (matcher.find()) {
                                                    }
                                                    if (str6.length() > 23) {
                                                        str6 = str6.substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    }
                                                    StringBuilder sb42 = new StringBuilder();
                                                    String str102 = "getCountryId() alpha2Code: " + networkCountryIso + ", alpha3Code: " + obj5;
                                                    sb42.append(str102 == null ? str : str102);
                                                    sb42.append(' ');
                                                    sb42.append("");
                                                    Log.println(3, str6, sb42.toString());
                                                }
                                                str6 = canonicalName3;
                                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                                if (matcher.find()) {
                                                }
                                                if (str6.length() > 23) {
                                                }
                                                StringBuilder sb422 = new StringBuilder();
                                                String str1022 = "getCountryId() alpha2Code: " + networkCountryIso + ", alpha3Code: " + obj5;
                                                sb422.append(str1022 == null ? str : str1022);
                                                sb422.append(' ');
                                                sb422.append("");
                                                Log.println(3, str6, sb422.toString());
                                            }
                                        }
                                    }
                                    return obj5;
                                }
                                obj = null;
                            } catch (Throwable th7) {
                                th = th7;
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    } catch (Throwable th8) {
                        th = th8;
                        obj = obj3;
                    }
                }
            }
            str2 = "N/A";
            r12 = z3;
            Object systemService2 = context.getSystemService(WorkflowModule.Properties.Section.Component.Keyboard.PHONE);
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            String networkCountryIso2 = ((TelephonyManager) systemService2).getNetworkCountryIso();
            if (alpha3Codes == null) {
            }
            List<? extends List<String>> list22 = alpha3Codes;
            Intrinsics.checkNotNull(list22);
            obj = r12;
            while (r0.hasNext()) {
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Throwable th9) {
            th = th9;
            obj = null;
        }
    }

    public static final /* synthetic */ String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        return uuid;
    }

    public static final /* synthetic */ boolean isDebug() {
        return ((Boolean) isDebug$delegate.getValue()).booleanValue();
    }

    public static final /* synthetic */ boolean isRelease() {
        return ((Boolean) isRelease$delegate.getValue()).booleanValue();
    }

    public static final /* synthetic */ boolean ifDebug(Function0 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (!isDebug()) {
            return false;
        }
        block.invoke();
        return true;
    }

    public static final /* synthetic */ boolean ifRelease(Function0 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (!isRelease()) {
            return false;
        }
        block.invoke();
        return true;
    }

    public static final /* synthetic */ void ifNot(boolean z, Function0 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (z) {
            return;
        }
        block.invoke();
    }

    public static final /* synthetic */ String removeCurlyBraces(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new Regex("[{}]").replace(str, "");
    }

    public static final /* synthetic */ boolean isAPI24OrAbove() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static final /* synthetic */ boolean isAPI33OrAbove() {
        return Build.VERSION.SDK_INT >= 33;
    }

    public static final /* synthetic */ String getStringValue(Map map, String key) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = map.get(key);
        return obj instanceof String ? (String) obj : "";
    }

    public static final /* synthetic */ <T, R> R nullableFold(Iterable<? extends T> iterable, R r, Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            r = operation.invoke(r, it.next());
        }
        return r;
    }

    public static final /* synthetic */ String toHexString(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt$toHexString$1
            public final CharSequence invoke(byte b) {
                String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
                return format;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }
        }, 30, (Object) null);
    }

    public static final /* synthetic */ String toMD5Hash(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        messageDigest.update(bytes);
        messageDigest.digest();
        byte[] bytes2 = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        String bigInteger = new BigInteger(1, messageDigest.digest(bytes2)).toString(16);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger(1, digest.dig…yteArray())).toString(16)");
        return StringsKt.padStart(bigInteger, 32, '0');
    }

    public static final /* synthetic */ boolean isNetworkError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        return (th instanceof StackOverflowError) || (th instanceof UnknownHostException) || (th instanceof ConnectException) || (th instanceof InterruptedIOException) || (th instanceof SocketException);
    }

    public static final /* synthetic */ void withViews(View[] views, Function1 action) {
        Intrinsics.checkNotNullParameter(views, "views");
        Intrinsics.checkNotNullParameter(action, "action");
        for (View view : views) {
            action.invoke(view);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0145, code lost:
    
        if (r0 != null) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0155, code lost:
    
        if (r0 == null) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0159, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0168, code lost:
    
        if (r0.find() == false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x016a, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0177, code lost:
    
        if (r8.length() <= 23) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x017d, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0180, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0187, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "putIfKeyNotNull() called with: key = [" + r18 + "], value = [" + r19 + ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01a4, code lost:
    
        if (r4 != null) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01a6, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01a8, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0158, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ boolean putIfNotNull(Map map, String str, String str2) {
        String canonicalName;
        Object m1202constructorimpl;
        String str3;
        String str4;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(map, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str5 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = map.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str6 = "putIfKeyNotNull() called with: key = [" + str + "], value = [" + str2 + ']';
        if (str6 == null) {
            str6 = "null ";
        }
        sb.append(str6);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!isRelease()) {
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
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        str4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = map.getClass();
                    str4 = cls2 != null ? cls2.getCanonicalName() : str3;
                }
            }
        }
        if (str == null || str2 == null) {
            return false;
        }
        map.put(str, str2);
        return true;
    }
}
