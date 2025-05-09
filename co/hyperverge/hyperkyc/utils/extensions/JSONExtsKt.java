package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperlogger.HyperLogger;
import io.flutter.plugins.firebase.database.Constants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: JSONExts.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a4\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0004j\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001`\u0005*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006H\u0000\u001a\u000e\u0010\b\u001a\u00020\t*\u0004\u0018\u00010\u0001H\u0000\u001a\u001a\u0010\n\u001a\u0004\u0018\u00010\u0007*\u0004\u0018\u00010\u000b2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000\u001a\u001e\u0010\f\u001a\u0004\u0018\u00010\u000b*\u0012\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006H\u0000\u001a\u0016\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00010\u0001*\u0004\u0018\u00010\u0007H\u0000\u001a\u001a\u0010\r\u001a\u0004\u0018\u00010\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006H\u0000\u001a\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0002\b\u0003\u0018\u00010\u0006*\u0004\u0018\u00010\u000bH\u0000\u001a(\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006*\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0000¨\u0006\u0011"}, d2 = {"extractJsonValue", "", Constants.PATH, "flattenMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "", "", "isJson", "", "recursiveGet", "Lorg/json/JSONObject;", "toJSONObject", "toJSONString", "kotlin.jvm.PlatformType", "toMap", "toNestedMap", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JSONExtsKt {
    public static final /* synthetic */ Map toMap(JSONObject jSONObject) {
        Iterator<String> keys;
        Sequence asSequence;
        Collection values;
        if (jSONObject == null || (keys = jSONObject.keys()) == null || (asSequence = SequencesKt.asSequence(keys)) == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : asSequence) {
            LinkedHashMap linkedHashMap2 = linkedHashMap;
            Object obj2 = jSONObject.get((String) obj);
            if (obj2 instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj2;
                IntRange until = RangesKt.until(0, jSONArray.length());
                LinkedHashMap linkedHashMap3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(until, 10)), 16));
                Iterator<Integer> it = until.iterator();
                while (it.hasNext()) {
                    int nextInt = ((IntIterator) it).nextInt();
                    Pair pair = new Pair(String.valueOf(nextInt), jSONArray.get(nextInt));
                    linkedHashMap3.put(pair.getFirst(), pair.getSecond());
                }
                Map map = toMap(new JSONObject(linkedHashMap3));
                if (map != null && (values = map.values()) != null) {
                    obj2 = CollectionsKt.toList(values);
                }
                obj2 = null;
            } else if (obj2 instanceof JSONObject) {
                obj2 = toMap((JSONObject) obj2);
            } else {
                if (!Intrinsics.areEqual(obj2, JSONObject.NULL)) {
                }
                obj2 = null;
            }
            linkedHashMap2.put(obj, obj2);
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ JSONObject toJSONObject(Map map) {
        if (map != null) {
            return new JSONObject(map);
        }
        return null;
    }

    public static final /* synthetic */ String toJSONString(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        JSONObject jSONObject = toJSONObject(map);
        if (jSONObject != null) {
            return jSONObject.toString();
        }
        return null;
    }

    public static final /* synthetic */ String extractJsonValue(String str, String path) {
        Object m1202constructorimpl;
        String optString;
        String optString2;
        JSONObject optJSONObject;
        Intrinsics.checkNotNullParameter(path, "path");
        if (str == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(new JSONObject(str));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = null;
        }
        JSONObject jSONObject = (JSONObject) m1202constructorimpl;
        if (jSONObject == null) {
            return null;
        }
        List<String> split$default = StringsKt.split$default((CharSequence) path, new String[]{"."}, false, 0, 6, (Object) null);
        for (String str2 : split$default) {
            if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str2)) == null) {
                if (jSONObject == null || (optString2 = jSONObject.optString(str2)) == null) {
                    return null;
                }
                return CoreExtsKt.nullIfBlank(optString2);
            }
            jSONObject = optJSONObject;
        }
        if (jSONObject == null || (optString = jSONObject.optString((String) CollectionsKt.last(split$default))) == null) {
            return null;
        }
        return CoreExtsKt.nullIfBlank(optString);
    }

    public static final /* synthetic */ boolean isJson(String str) {
        Object m1202constructorimpl;
        Object m1202constructorimpl2;
        if (str == null) {
            return false;
        }
        String replace$default = StringsKt.replace$default(str, "\\", "", false, 4, (Object) null);
        try {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(new JSONObject(replace$default));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1205exceptionOrNullimpl(m1202constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(new JSONArray(replace$default));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            m1202constructorimpl = m1202constructorimpl2;
        }
        return Result.m1209isSuccessimpl(m1202constructorimpl);
    }

    public static final /* synthetic */ Map toNestedMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            String str2 = str;
            if (StringsKt.contains$default((CharSequence) str2, FilenameUtils.EXTENSION_SEPARATOR, false, 2, (Object) null)) {
                List split$default = StringsKt.split$default((CharSequence) str2, new char[]{FilenameUtils.EXTENSION_SEPARATOR}, false, 0, 6, (Object) null);
                String str3 = (String) CollectionsKt.last(split$default);
                Map map2 = linkedHashMap;
                for (String str4 : CollectionsKt.dropLast(split$default, 1)) {
                    LinkedHashMap linkedHashMap2 = map2.get(str4);
                    if (linkedHashMap2 == null) {
                        linkedHashMap2 = new LinkedHashMap();
                        map2.put(str4, linkedHashMap2);
                    }
                    map2 = TypeIntrinsics.asMutableMap(linkedHashMap2);
                }
                if (value != null) {
                    map2.put(str3, value);
                }
            } else if (value != null) {
                linkedHashMap.put(str, value);
            }
        }
        return linkedHashMap;
    }

    public static final /* synthetic */ HashMap flattenMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        HashMap hashMap = new HashMap();
        flattenMap$flatten$default(hashMap, map, null, 4, null);
        return hashMap;
    }

    static /* synthetic */ void flattenMap$flatten$default(HashMap hashMap, Map map, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "";
        }
        flattenMap$flatten(hashMap, map, str);
    }

    private static final void flattenMap$flatten(HashMap<String, String> hashMap, Map<?, ?> map, String str) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            String valueOf = str.length() == 0 ? String.valueOf(key) : str + FilenameUtils.EXTENSION_SEPARATOR + key;
            if (value instanceof Map) {
                flattenMap$flatten(hashMap, (Map) value, valueOf);
            } else {
                hashMap.put(valueOf, String.valueOf(value));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ Object recursiveGet(JSONObject jSONObject, String str) {
        T t;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (jSONObject == null || str == null) {
            objectRef.element = null;
        } else {
            int i = 0;
            for (Object obj : StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null)) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str2 = (String) obj;
                if (i < r2.size() - 1) {
                    if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "[", false, 2, (Object) null)) {
                        JSONArray optJSONArray = jSONObject.optJSONArray(StringsKt.substringBefore$default(str2, "[", (String) null, 2, (Object) null));
                        jSONObject = (JSONObject) recursiveGet$logErrorIfNull(optJSONArray != null ? optJSONArray.optJSONObject(recursiveGet$lambda$15$lambda$14$getArrayPos(str2)) : null, str, str2);
                    } else {
                        jSONObject = (JSONObject) recursiveGet$logErrorIfNull(jSONObject.optJSONObject(str2), str, str2);
                    }
                } else {
                    if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "[", false, 2, (Object) null)) {
                        JSONArray optJSONArray2 = jSONObject.optJSONArray(StringsKt.substringBefore$default(str2, "[", (String) null, 2, (Object) null));
                        if (optJSONArray2 != null) {
                            JSONObject optJSONObject = optJSONArray2.optJSONObject(recursiveGet$lambda$15$lambda$14$getArrayPos(str2));
                            t = optJSONObject;
                            if (optJSONObject == null) {
                                t = optJSONArray2.optString(recursiveGet$lambda$15$lambda$14$getArrayPos(str2));
                            }
                        } else {
                            t = 0;
                        }
                    } else {
                        t = jSONObject.opt(str2);
                    }
                    objectRef.element = t;
                    recursiveGet$logErrorIfNull(objectRef.element, str, str2);
                }
                if (jSONObject == null) {
                    break;
                }
                i = i2;
            }
        }
        return objectRef.element;
    }

    private static final int recursiveGet$lambda$15$lambda$14$getArrayPos(String str) {
        return Integer.parseInt(StringsKt.substringBefore$default(StringsKt.substringAfter$default(str, "[", (String) null, 2, (Object) null), "]", (String) null, 2, (Object) null));
    }

    private static final <T> T recursiveGet$logErrorIfNull(T t, String str, String str2) {
        String str3;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        if (t == null) {
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str4 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                str3 = "N/A";
            }
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
            if (matcher.find()) {
                str3 = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
            }
            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str3 = str3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str3);
            sb.append(" - ");
            String str5 = str + " cannot be inferred, " + str2 + " not found";
            if (str5 == null) {
                str5 = "null ";
            }
            sb.append(str5);
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
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null && (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) != null) {
                            str4 = substringAfterLast$default;
                        }
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher2.find()) {
                            str4 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str6 = str + " cannot be inferred, " + str2 + " not found";
                        if (str6 == null) {
                            str6 = "null ";
                        }
                        sb2.append(str6);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(5, str4, sb2.toString());
                    }
                }
            }
        }
        return t;
    }
}
