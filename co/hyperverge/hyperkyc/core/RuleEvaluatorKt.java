package co.hyperverge.hyperkyc.core;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: RuleEvaluator.kt */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0002\u001aH\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00160\u0001H\u0002\u001a4\u0010\u0017\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0018\u001a\u00020\u00022\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001aH\u0002\u001a\u0016\u0010\u001b\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\tH\u0000\u001a \u0010\u001d\u001a\u00020\t*\u00020\u00022\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00160\u0001H\u0002\u001a\f\u0010\u001e\u001a\u00020\t*\u00020\u0002H\u0000\u001aD\u0010\u001f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00110\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00160\u0001H\u0002\u001aL\u0010 \u001a\u00020\r*\u00020\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00160\u0001H\u0002\u001a\f\u0010!\u001a\u00020\t*\u00020\"H\u0002\u001a\u001c\u0010#\u001a\u00020\r*\u00020\t2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u0014H\u0002\u001a\"\u0010&\u001a\u00020\u0011*\u00020\u00022\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00160\u0001H\u0000\u001a\u0016\u0010'\u001a\u00020(*\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020(H\u0002\u001a\u0012\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00020**\u00020\u0002H\u0000\u001a\f\u0010+\u001a\u00020\"*\u00020\u0002H\u0002\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"DEFAULT_OPERATORS_MAP", "", "", "Lco/hyperverge/hyperkyc/core/BinaryBooleanOperator;", "REGEX_STR", "TOKENIZE_STR", "VALUE_CHARS", "VALUE_STR", "checkPrecedence", "", "topBooleanOperator", "currentBooleanOperator", "parseOnRightParenthesis", "", "operatorStack", "", "outputQueue", "Lco/hyperverge/hyperkyc/core/Node;", "tokensToString", "currentPos", "", "operators", "Lco/hyperverge/hyperkyc/core/Operator;", "addOperator", "opLabel", "operator", "Lkotlin/Function2;", "eval", "default", "isBinaryOperator", "isValue", "offerOperator", "parseAsOperator", "stringToBooleanStrict", "", "throwIfInvalid", "token", "pos", "toAbstractSyntaxTree", "toSafeDouble", "", "tokenize", "", "trimQuotes", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RuleEvaluatorKt {
    private static final Map<String, BinaryBooleanOperator> DEFAULT_OPERATORS_MAP = MapsKt.mapOf(TuplesKt.to("==", new BinaryBooleanOperator("==", 2, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$1
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Object trimQuotes;
            Object trimQuotes2;
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            trimQuotes = RuleEvaluatorKt.trimQuotes(a);
            trimQuotes2 = RuleEvaluatorKt.trimQuotes(b);
            return String.valueOf(Intrinsics.areEqual(trimQuotes, trimQuotes2));
        }
    }, 4, null)), TuplesKt.to("!=", new BinaryBooleanOperator("!=", 2, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$2
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Object trimQuotes;
            Object trimQuotes2;
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            trimQuotes = RuleEvaluatorKt.trimQuotes(a);
            trimQuotes2 = RuleEvaluatorKt.trimQuotes(b);
            return String.valueOf(!Intrinsics.areEqual(trimQuotes, trimQuotes2));
        }
    }, 4, null)), TuplesKt.to(">", new BinaryBooleanOperator(">", 2, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$3
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            return String.valueOf(RuleEvaluatorKt.toSafeDouble$default(a, 0.0d, 1, null) > RuleEvaluatorKt.toSafeDouble$default(b, 0.0d, 1, null));
        }
    }, 4, null)), TuplesKt.to("<", new BinaryBooleanOperator("<", 2, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$4
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            return String.valueOf(RuleEvaluatorKt.toSafeDouble$default(a, 0.0d, 1, null) < RuleEvaluatorKt.toSafeDouble$default(b, 0.0d, 1, null));
        }
    }, 4, null)), TuplesKt.to(">=", new BinaryBooleanOperator(">=", 2, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$5
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            return String.valueOf(RuleEvaluatorKt.toSafeDouble$default(a, 0.0d, 1, null) >= RuleEvaluatorKt.toSafeDouble$default(b, 0.0d, 1, null));
        }
    }, 4, null)), TuplesKt.to("<=", new BinaryBooleanOperator("<=", 2, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$6
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            return String.valueOf(RuleEvaluatorKt.toSafeDouble$default(a, 0.0d, 1, null) <= RuleEvaluatorKt.toSafeDouble$default(b, 0.0d, 1, null));
        }
    }, 4, null)), TuplesKt.to("&&", new BinaryBooleanOperator("&&", 1, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$7
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Object trimQuotes;
            boolean stringToBooleanStrict;
            boolean z;
            Object trimQuotes2;
            boolean stringToBooleanStrict2;
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            trimQuotes = RuleEvaluatorKt.trimQuotes(a);
            stringToBooleanStrict = RuleEvaluatorKt.stringToBooleanStrict(trimQuotes);
            if (stringToBooleanStrict) {
                trimQuotes2 = RuleEvaluatorKt.trimQuotes(b);
                stringToBooleanStrict2 = RuleEvaluatorKt.stringToBooleanStrict(trimQuotes2);
                if (stringToBooleanStrict2) {
                    z = true;
                    return String.valueOf(z);
                }
            }
            z = false;
            return String.valueOf(z);
        }
    }, 4, null)), TuplesKt.to("||", new BinaryBooleanOperator("||", 1, false, new Function2<String, String, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$DEFAULT_OPERATORS_MAP$8
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String a, String b) {
            Object trimQuotes;
            boolean stringToBooleanStrict;
            boolean z;
            Object trimQuotes2;
            boolean stringToBooleanStrict2;
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            trimQuotes = RuleEvaluatorKt.trimQuotes(a);
            stringToBooleanStrict = RuleEvaluatorKt.stringToBooleanStrict(trimQuotes);
            if (!stringToBooleanStrict) {
                trimQuotes2 = RuleEvaluatorKt.trimQuotes(b);
                stringToBooleanStrict2 = RuleEvaluatorKt.stringToBooleanStrict(trimQuotes2);
                if (!stringToBooleanStrict2) {
                    z = false;
                    return String.valueOf(z);
                }
            }
            z = true;
            return String.valueOf(z);
        }
    }, 4, null)));
    private static final String REGEX_STR = "[\\w\\s.,:#@$;'/\\\\+-=]+(&[\\w\\s.,:#@$;'/\\\\+-=]+)?|[\\w\\s.,:#@$;'/\\\\+-=]+";
    private static final String TOKENIZE_STR = "[\\w\\s.,:#@$;'/\\\\+-]+";
    private static final String VALUE_CHARS = "\\w\\s.,:#@$;'/\\\\+-";
    private static final String VALUE_STR = "[\\w\\s.,:#@$;'/\\\\+-=]+";

    public static /* synthetic */ boolean eval$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return eval(str, z);
    }

    public static /* synthetic */ Node toAbstractSyntaxTree$default(String str, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = DEFAULT_OPERATORS_MAP;
        }
        return toAbstractSyntaxTree(str, map);
    }

    private static final void parseAsOperator(String str, List<String> list, List<Node> list2, String str2, int i, Map<String, ? extends Operator> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, ? extends Operator> entry : map.entrySet()) {
            String key = entry.getKey();
            Operator value = entry.getValue();
            if (value instanceof BinaryBooleanOperator) {
                Pair pair = TuplesKt.to(key, value);
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            arrayList.add(Unit.INSTANCE);
        }
        List<String> list3 = list;
        if (!list3.isEmpty()) {
            BinaryBooleanOperator binaryBooleanOperator = (BinaryBooleanOperator) linkedHashMap.get(str);
            if (binaryBooleanOperator == null) {
                throw new InvalidSymbolException(str, str2, i);
            }
            while ((!list3.isEmpty()) && isBinaryOperator((String) CollectionsKt.last((List) list), map)) {
                String str3 = (String) CollectionsKt.last((List) list);
                BinaryBooleanOperator binaryBooleanOperator2 = (BinaryBooleanOperator) linkedHashMap.get(str3);
                if (binaryBooleanOperator2 == null) {
                    throw new InvalidSymbolException(str3, str2, i);
                }
                if (!checkPrecedence(binaryBooleanOperator2, binaryBooleanOperator) || Intrinsics.areEqual(str3, "(")) {
                    break;
                } else {
                    offerOperator(list2, list, str2, i, linkedHashMap);
                }
            }
        }
        list.add(str);
    }

    private static final void parseOnRightParenthesis(List<String> list, List<Node> list2, String str, int i, Map<String, ? extends Operator> map) {
        while (!Intrinsics.areEqual(CollectionsKt.last((List) list), "(")) {
            try {
                if (list.isEmpty()) {
                    throw new InvalidExpressionException(str, i);
                }
                offerOperator(list2, list, str, i, map);
            } catch (NoSuchElementException unused) {
                throw new InvalidExpressionException(str, i);
            }
        }
        if ((!list.isEmpty()) && Intrinsics.areEqual(CollectionsKt.last((List) list), "(")) {
            CollectionsKt.removeLast(list);
        }
    }

    private static final boolean checkPrecedence(BinaryBooleanOperator binaryBooleanOperator, BinaryBooleanOperator binaryBooleanOperator2) {
        return (binaryBooleanOperator.getPrecedence() > binaryBooleanOperator2.getPrecedence()) || (binaryBooleanOperator.getPrecedence() == binaryBooleanOperator2.getPrecedence() && binaryBooleanOperator2.isLeftAssociative());
    }

    private static final void offerOperator(List<Node> list, List<String> list2, String str, int i, Map<String, ? extends Operator> map) {
        Operator operator = map.get(CollectionsKt.removeLast(list2));
        if (operator == null) {
            throw new InvalidExpressionException(str, i);
        }
        if (!(operator instanceof BinaryBooleanOperator)) {
            throw new NoWhenBranchMatchedException();
        }
        BinaryBooleanOperator binaryBooleanOperator = (BinaryBooleanOperator) operator;
        throwIfInvalid(addOperator(list, binaryBooleanOperator.getLabel(), binaryBooleanOperator.getAction()), str, i);
    }

    private static final void throwIfInvalid(boolean z, String str, int i) {
        if (!z) {
            throw new InvalidExpressionException(str, i);
        }
    }

    public static final Object trimQuotes(String str) {
        if (!str.contentEquals("''")) {
            str = StringsKt.trim(str, '\'');
        }
        return new Regex("-?\\d+(\\.\\d+)?").matches(str) ? Double.valueOf(toSafeDouble$default(str, 0.0d, 1, null)) : str;
    }

    public static final boolean stringToBooleanStrict(Object obj) {
        return StringsKt.toBooleanStrict(obj.toString());
    }

    public static /* synthetic */ double toSafeDouble$default(String str, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            d = 0.0d;
        }
        return toSafeDouble(str, d);
    }

    private static final double toSafeDouble(String str, double d) {
        Object m1202constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(Double.valueOf(Double.parseDouble(str)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Double valueOf = Double.valueOf(d);
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = valueOf;
        }
        return ((Number) m1202constructorimpl).doubleValue();
    }

    public static final /* synthetic */ boolean isValue(String str) {
        boolean z;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (!new Regex(REGEX_STR).matches(str)) {
            return false;
        }
        Set<String> keySet = DEFAULT_OPERATORS_MAP.keySet();
        if (!(keySet instanceof Collection) || !keySet.isEmpty()) {
            Iterator<T> it = keySet.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual((String) it.next(), str)) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        return z;
    }

    private static final boolean isBinaryOperator(String str, Map<String, ? extends Operator> map) {
        return map.keySet().contains(str) && (map.get(str) instanceof BinaryBooleanOperator);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(21:1|(3:218|(1:220)(1:223)|(1:222))|7|(1:9)|10|(1:14)|15|(1:17)|18|(6:182|183|184|(1:186)|187|(12:189|(9:191|(3:209|(1:211)(1:214)|(1:213))|197|(1:199)|200|(1:204)|205|(1:207)|208)|21|22|23|(21:25|(1:177)(1:29)|31|(1:33)(1:37)|(1:35)(1:36)|38|(1:40)|41|(1:45)|46|(1:48)|49|(1:51)(1:176)|(1:53)(1:175)|54|55|56|57|(1:59)|60|(2:62|(12:64|(3:164|(1:166)(1:169)|(1:168))|70|(1:72)|73|(1:77)|78|(1:80)|81|(1:83)(1:163)|(1:85)(1:162)|86)(1:170))(1:171))(1:178)|87|(13:89|(1:154)(1:93)|95|(1:97)(1:101)|(1:99)(1:100)|102|(1:104)|105|(1:109)|110|(1:112)|113|(6:115|116|117|(1:119)|120|(2:122|(12:124|(1:150)(2:128|(8:130|131|(1:133)|134|(1:138)|139|(1:141)|142))|143|(1:145)(1:149)|(1:147)(1:148)|131|(0)|134|(2:136|138)|139|(0)|142))))|155|(1:157)(1:161)|158|159))|20|21|22|23|(0)(0)|87|(0)|155|(0)(0)|158|159) */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x01c3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x01c4, code lost:
    
        r7 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x020c, code lost:
    
        if (r9 != null) goto L330;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0404, code lost:
    
        if (r10 != null) goto L414;
     */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0584  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean eval(String str, boolean z) {
        String canonicalName;
        Object m1202constructorimpl;
        String str2;
        String canonicalName2;
        String className;
        Throwable m1205exceptionOrNullimpl;
        Object obj;
        String str3;
        String str4;
        String str5;
        Object m1202constructorimpl2;
        String str6;
        String str7;
        Matcher matcher;
        String str8;
        String className2;
        String className3;
        String str9;
        String str10;
        String str11;
        String str12;
        Object m1202constructorimpl3;
        String canonicalName3;
        String str13;
        String className4;
        String className5;
        String className6;
        Intrinsics.checkNotNullParameter(str, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = str.getClass();
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
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str14 = str + ".eval() called";
        if (str14 == null) {
            str14 = "null ";
        }
        sb.append(str14);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        String str15 = "packageName";
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
                str2 = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = str.getClass();
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
                    String str16 = str + ".eval() called";
                    if (str16 == null) {
                        str16 = "null ";
                    }
                    sb2.append(str16);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                Result.Companion companion4 = Result.INSTANCE;
                Object m1202constructorimpl4 = Result.m1202constructorimpl(Boolean.valueOf(StringsKt.toBooleanStrict(toAbstractSyntaxTree$default(str, null, 1, null).eval())));
                Object obj2 = m1202constructorimpl4;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null) {
                        str9 = "packageName";
                        str10 = "Throwable().stackTrace";
                        obj = obj2;
                    } else {
                        str9 = "packageName";
                        str10 = "Throwable().stackTrace";
                        obj = obj2;
                        str11 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls3 = str.getClass();
                    String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                    str11 = canonicalName4 == null ? str2 : canonicalName4;
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str11);
                    if (matcher4.find()) {
                        str11 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str11, "replaceAll(\"\")");
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (str11.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str11 = str11.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str11);
                    sb3.append(" - ");
                    String str17 = "eval(): failed evaluating rule: " + str + ", returning default: " + z;
                    if (str17 == null) {
                        str17 = "null ";
                    }
                    sb3.append(str17);
                    sb3.append(' ');
                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str12 = '\n' + localizedMessage;
                    } else {
                        str12 = "";
                    }
                    sb3.append(str12);
                    companion5.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        m1202constructorimpl3 = "";
                    }
                    String str18 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                        str15 = str9;
                        Intrinsics.checkNotNullExpressionValue(str18, str15);
                        if (StringsKt.contains$default((CharSequence) str18, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            str3 = str10;
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = str.getClass();
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
                            String str19 = "eval(): failed evaluating rule: " + str + ", returning default: " + z;
                            if (str19 == null) {
                                str19 = "null ";
                            }
                            sb4.append(str19);
                            sb4.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage2 != null) {
                                str13 = '\n' + localizedMessage2;
                            } else {
                                str13 = "";
                            }
                            sb4.append(str13);
                            Log.println(6, canonicalName3, sb4.toString());
                        } else {
                            str3 = str10;
                        }
                    } else {
                        str3 = str10;
                        str15 = str9;
                    }
                } else {
                    obj = obj2;
                    str3 = "Throwable().stackTrace";
                }
                if (Result.m1209isSuccessimpl(obj)) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                    HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, str3);
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null) {
                        str4 = str3;
                    } else {
                        str4 = str3;
                        str5 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls5 = str.getClass();
                    String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                    str5 = canonicalName5 == null ? str2 : canonicalName5;
                    Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                    if (matcher6.find()) {
                        str5 = matcher6.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str5 = str5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb5.append(str5);
                    sb5.append(" - ");
                    String str20 = "eval: success evaluating rule: " + str + ", returning : " + booleanValue;
                    if (str20 == null) {
                        str20 = "null ";
                    }
                    sb5.append(str20);
                    sb5.append(' ');
                    sb5.append("");
                    companion8.log(level3, sb5.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion9 = Result.INSTANCE;
                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion10 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String str21 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str21, str15);
                            if (StringsKt.contains$default((CharSequence) str21, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str4);
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null) {
                                    str6 = null;
                                } else {
                                    str6 = null;
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        str7 = substringAfterLast$default;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                        if (matcher.find()) {
                                            str7 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                                        }
                                        Unit unit6 = Unit.INSTANCE;
                                        if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str7 = str7.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb6 = new StringBuilder();
                                        str8 = "eval: success evaluating rule: " + str + ", returning : " + booleanValue;
                                        if (str8 == null) {
                                            str8 = "null ";
                                        }
                                        sb6.append(str8);
                                        sb6.append(' ');
                                        sb6.append("");
                                        Log.println(3, str7, sb6.toString());
                                    }
                                }
                                Class<?> cls6 = str.getClass();
                                String canonicalName6 = cls6 != null ? cls6.getCanonicalName() : str6;
                                str7 = canonicalName6 == null ? str2 : canonicalName6;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                if (matcher.find()) {
                                }
                                Unit unit62 = Unit.INSTANCE;
                                if (str7.length() > 23) {
                                    str7 = str7.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb62 = new StringBuilder();
                                str8 = "eval: success evaluating rule: " + str + ", returning : " + booleanValue;
                                if (str8 == null) {
                                }
                                sb62.append(str8);
                                sb62.append(' ');
                                sb62.append("");
                                Log.println(3, str7, sb62.toString());
                            }
                        }
                    }
                }
                return ((Boolean) (!Result.m1208isFailureimpl(obj) ? Boolean.valueOf(z) : obj)).booleanValue();
            }
        }
        str2 = "N/A";
        Result.Companion companion42 = Result.INSTANCE;
        Object m1202constructorimpl42 = Result.m1202constructorimpl(Boolean.valueOf(StringsKt.toBooleanStrict(toAbstractSyntaxTree$default(str, null, 1, null).eval())));
        Object obj22 = m1202constructorimpl42;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22);
        if (m1205exceptionOrNullimpl == null) {
        }
        if (Result.m1209isSuccessimpl(obj)) {
        }
        return ((Boolean) (!Result.m1208isFailureimpl(obj) ? Boolean.valueOf(z) : obj)).booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0225, code lost:
    
        if (r1 != null) goto L274;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0315, code lost:
    
        if (r0 != null) goto L315;
     */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x041e A[LOOP:1: B:76:0x0413->B:78:0x041e, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Node toAbstractSyntaxTree(String str, Map operators) {
        String canonicalName;
        String str2;
        Object m1202constructorimpl;
        String str3;
        String canonicalName2;
        String className;
        String str4;
        ArrayList arrayList;
        ArrayList arrayList2;
        String str5;
        Object m1202constructorimpl2;
        String str6;
        String str7;
        int i;
        String className2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(operators, "operators");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = str.getClass();
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
        String str8 = str + ".toAbstractSyntaxTree() called with: operators = " + operators.keySet();
        if (str8 == null) {
            str8 = "null ";
        }
        sb.append(str8);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            str2 = "N/A";
        } else {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                str2 = "N/A";
                try {
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                    }
                    str3 = "null ";
                    if (StringsKt.isBlank(new Regex("[()]").replace(str, ""))) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = "N/A";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                str3 = "null ";
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = str.getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str2;
                        }
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher2.find()) {
                        canonicalName2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str9 = str + ".toAbstractSyntaxTree() called with: operators = " + operators.keySet();
                    if (str9 == null) {
                        str9 = str3;
                    }
                    sb2.append(str9);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                if (StringsKt.isBlank(new Regex("[()]").replace(str, ""))) {
                    throw new InvalidExpressionException("", -1);
                }
                ArrayList arrayList6 = new ArrayList();
                ArrayList arrayList7 = new ArrayList();
                List list = tokenize(str);
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                    str4 = "Throwable().stackTrace";
                    arrayList = arrayList6;
                    arrayList2 = arrayList7;
                } else {
                    str4 = "Throwable().stackTrace";
                    arrayList = arrayList6;
                    arrayList2 = arrayList7;
                    str5 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls3 = str.getClass();
                str5 = cls3 != null ? cls3.getCanonicalName() : null;
                if (str5 == null) {
                    str5 = str2;
                }
                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                if (matcher3.find()) {
                    str5 = matcher3.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                }
                if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str5 = str5.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb3.append(str5);
                sb3.append(" - ");
                String str10 = "tokens: " + list;
                if (str10 == null) {
                    str10 = str3;
                }
                sb3.append(str10);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th3) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName3 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str4);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str6 = null;
                            } else {
                                str6 = null;
                                str7 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls4 = str.getClass();
                            String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : str6;
                            str7 = canonicalName3 == null ? str2 : canonicalName3;
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                            if (matcher4.find()) {
                                str7 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                            }
                            if (str7.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                i = 0;
                            } else {
                                i = 0;
                                str7 = str7.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb4 = new StringBuilder();
                            String str11 = "tokens: " + list;
                            sb4.append(str11 == null ? str3 : str11);
                            sb4.append(' ');
                            sb4.append("");
                            Log.println(3, str7, sb4.toString());
                            List list2 = list;
                            String joinToString$default = CollectionsKt.joinToString$default(list2, "", null, null, 0, null, null, 62, null);
                            int i2 = i;
                            int i3 = i2;
                            for (Object obj : list2) {
                                int i4 = i3 + 1;
                                if (i3 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                String str12 = (String) obj;
                                if (isValue(str12)) {
                                    arrayList4 = arrayList;
                                    arrayList4.add(new ValueNode(str12));
                                } else {
                                    arrayList4 = arrayList;
                                    if (isBinaryOperator(str12, operators)) {
                                        parseAsOperator(str12, arrayList2, arrayList4, joinToString$default, i2, operators);
                                    } else {
                                        if (Intrinsics.areEqual(str12, "(")) {
                                            arrayList5 = arrayList2;
                                            arrayList5.add(str12);
                                        } else {
                                            arrayList5 = arrayList2;
                                            if (Intrinsics.areEqual(str12, ")")) {
                                                parseOnRightParenthesis(arrayList5, arrayList4, joinToString$default, i2, operators);
                                            }
                                        }
                                        i2 += str12.length();
                                        arrayList2 = arrayList5;
                                        i3 = i4;
                                        arrayList = arrayList4;
                                    }
                                }
                                arrayList5 = arrayList2;
                                i2 += str12.length();
                                arrayList2 = arrayList5;
                                i3 = i4;
                                arrayList = arrayList4;
                            }
                            arrayList3 = arrayList2;
                            ArrayList arrayList8 = arrayList;
                            while (!arrayList3.isEmpty()) {
                                offerOperator(arrayList8, arrayList3, joinToString$default, i2, operators);
                            }
                            return (Node) CollectionsKt.last((List) arrayList8);
                        }
                    }
                }
                i = 0;
                List list22 = list;
                String joinToString$default2 = CollectionsKt.joinToString$default(list22, "", null, null, 0, null, null, 62, null);
                int i22 = i;
                int i32 = i22;
                while (r7.hasNext()) {
                }
                arrayList3 = arrayList2;
                ArrayList arrayList82 = arrayList;
                while (!arrayList3.isEmpty()) {
                }
                return (Node) CollectionsKt.last((List) arrayList82);
            }
        }
        str3 = "null ";
        if (StringsKt.isBlank(new Regex("[()]").replace(str, ""))) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0140, code lost:
    
        if (r0 != null) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0152, code lost:
    
        if (r0 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0156, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0165, code lost:
    
        if (r0.find() == false) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0167, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0172, code lost:
    
        if (r8.length() <= 23) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0178, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x017b, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0182, code lost:
    
        r0 = new java.lang.StringBuilder();
        r2 = r17 + ".addOperator() called with: opLabel = " + r18 + ", operator = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x019f, code lost:
    
        if (r2 != null) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01a1, code lost:
    
        r2 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01a3, code lost:
    
        r0.append(r2);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0155, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final boolean addOperator(List<Node> list, String str, Function2<? super String, ? super String, String> function2) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str2;
        String str3;
        Class<?> cls2;
        String className;
        Node node;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str4 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (list == null || (cls = list.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str5 = list + ".addOperator() called with: opLabel = " + str + ", operator = " + function2;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str2 = null;
                    } else {
                        str2 = null;
                        str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    str3 = (list == null || (cls2 = list.getClass()) == null) ? str2 : cls2.getCanonicalName();
                }
            }
        }
        Node node2 = (Node) CollectionsKt.removeLastOrNull(list);
        if (node2 == null || (node = (Node) CollectionsKt.removeLastOrNull(list)) == null) {
            return false;
        }
        list.add(new OperatorNode(node, function2, node2));
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x013e, code lost:
    
        if (r0 == null) goto L127;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ List tokenize(String str) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = str.getClass();
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
        String str3 = str + ".tokenize() called";
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = str.getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str2 = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = str + ".tokenize() called";
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                }
            }
        }
        List list = SequencesKt.toList(SequencesKt.filter(SequencesKt.map(Regex.findAll$default(new Regex("('?(?=[[\\w\\s.,:#@$;'/\\\\+-]+])[[\\w\\s.,:#@$;'/\\\\+-]+]+(?<=[[\\w\\s.,:#@$;'/\\\\+-]+])'?|==|&&|\\|\\||!=|<=|>=|>|<|''|\\(|\\))"), str, 0, 2, null), new Function1<MatchResult, String>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$tokenize$tokens$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(MatchResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return StringsKt.trim((CharSequence) it.getValue()).toString();
            }
        }), new Function1<String, Boolean>() { // from class: co.hyperverge.hyperkyc.core.RuleEvaluatorKt$tokenize$tokens$2
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(!StringsKt.isBlank(it));
            }
        }));
        CollectionsKt.joinToString$default(list, "", null, null, 0, null, null, 62, null);
        return list;
    }
}
