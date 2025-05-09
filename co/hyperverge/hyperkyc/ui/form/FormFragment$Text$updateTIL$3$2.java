package co.hyperverge.hyperkyc.ui.form;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.ui.custom.IMEAwareTextInputEditText;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$Text$updateTIL$3$2", f = "FormFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FormFragment$Text$updateTIL$3$2 extends SuspendLambda implements Function2<CharSequence, Continuation<? super Unit>, Object> {
    final /* synthetic */ IMEAwareTextInputEditText $this_apply;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FormFragment.Text this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$Text$updateTIL$3$2(IMEAwareTextInputEditText iMEAwareTextInputEditText, FormFragment.Text text, Continuation<? super FormFragment$Text$updateTIL$3$2> continuation) {
        super(2, continuation);
        this.$this_apply = iMEAwareTextInputEditText;
        this.this$0 = text;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FormFragment$Text$updateTIL$3$2 formFragment$Text$updateTIL$3$2 = new FormFragment$Text$updateTIL$3$2(this.$this_apply, this.this$0, continuation);
        formFragment$Text$updateTIL$3$2.L$0 = obj;
        return formFragment$Text$updateTIL$3$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CharSequence charSequence, Continuation<? super Unit> continuation) {
        return ((FormFragment$Text$updateTIL$3$2) create(charSequence, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x0474, code lost:
    
        if (r3 != 0) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0260, code lost:
    
        if (r12 != null) goto L93;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:145:0x05b6  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0602  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0605  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13, types: [T] */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [T] */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v9, types: [T] */
    /* JADX WARN: Type inference failed for: r1v101, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v113, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v118 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v17, types: [T] */
    /* JADX WARN: Type inference failed for: r1v27, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v29, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v59, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v16, types: [T] */
    /* JADX WARN: Type inference failed for: r3v41, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v48, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r8v10, types: [T] */
    /* JADX WARN: Type inference failed for: r8v34, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v37 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? canonicalName;
        Class<?> cls;
        String str;
        boolean z;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str2;
        ?? canonicalName2;
        Class<?> cls2;
        String str3;
        boolean z2;
        String className;
        boolean z3;
        String str4;
        String str5;
        String str6;
        ?? r3;
        String str7;
        Object m1202constructorimpl2;
        String str8;
        String str9;
        Class<?> cls3;
        Matcher matcher;
        String str10;
        String className2;
        FormFragment$Text$updateTIL$3$2 formFragment$Text$updateTIL$3$2;
        Class<?> cls4;
        String className3;
        String str11;
        String str12;
        String str13;
        Object m1202constructorimpl3;
        String str14;
        Class<?> cls5;
        String str15;
        String className4;
        String substringAfterLast$default;
        Class<?> cls6;
        String className5;
        String className6;
        FormFragment$Text$updateTIL$3$2 formFragment$Text$updateTIL$3$22 = this;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (formFragment$Text$updateTIL$3$22.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CharSequence charSequence2 = (CharSequence) formFragment$Text$updateTIL$3$22.L$0;
        IMEAwareTextInputEditText iMEAwareTextInputEditText = formFragment$Text$updateTIL$3$22.$this_apply;
        FormFragment.Text text = formFragment$Text$updateTIL$3$22.this$0;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
            canonicalName = (iMEAwareTextInputEditText == null || (cls = iMEAwareTextInputEditText.getClass()) == null) ? 0 : cls.getCanonicalName();
            if (canonicalName == 0) {
                canonicalName = "N/A";
            }
        }
        objectRef.element = canonicalName;
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
        if (matcher2.find()) {
            ?? replaceAll = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
            objectRef.element = replaceAll;
        }
        if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
            str = (String) objectRef.element;
        } else {
            str = ((String) objectRef.element).substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(str);
        sb.append(" - ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(text.getCompLogTag());
        sb2.append(" updateTIL: textChangesFlow called with ");
        sb2.append((Object) charSequence2);
        sb2.append(", textChangedByUser: ");
        z = text.textChangedByUser;
        sb2.append(z);
        String sb3 = sb2.toString();
        if (sb3 == null) {
            sb3 = "null ";
        }
        sb.append(sb3);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            charSequence = "co.hyperverge";
            str2 = "packageName";
        } else {
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
                charSequence = "co.hyperverge";
                str2 = "packageName";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName2 = (iMEAwareTextInputEditText == null || (cls2 = iMEAwareTextInputEditText.getClass()) == null) ? 0 : cls2.getCanonicalName();
                        if (canonicalName2 == 0) {
                            canonicalName2 = "N/A";
                        }
                    }
                    objectRef2.element = canonicalName2;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher3.find()) {
                        ?? replaceAll2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                        objectRef2.element = replaceAll2;
                    }
                    if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str3 = (String) objectRef2.element;
                    } else {
                        str3 = ((String) objectRef2.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb4 = new StringBuilder();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(text.getCompLogTag());
                    sb5.append(" updateTIL: textChangesFlow called with ");
                    sb5.append((Object) charSequence2);
                    sb5.append(", textChangedByUser: ");
                    z2 = text.textChangedByUser;
                    sb5.append(z2);
                    String sb6 = sb5.toString();
                    if (sb6 == null) {
                        sb6 = "null ";
                    }
                    sb4.append(sb6);
                    sb4.append(' ');
                    sb4.append("");
                    Log.println(3, str3, sb4.toString());
                }
            } else {
                charSequence = "co.hyperverge";
                str2 = "packageName";
            }
            formFragment$Text$updateTIL$3$22 = this;
        }
        z3 = formFragment$Text$updateTIL$3$22.this$0.textChangedByUser;
        if (z3) {
            IMEAwareTextInputEditText iMEAwareTextInputEditText2 = formFragment$Text$updateTIL$3$22.$this_apply;
            FormFragment.Text text2 = formFragment$Text$updateTIL$3$22.this$0;
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb7 = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null) {
                str11 = "Throwable().stackTrace";
            } else {
                str11 = "Throwable().stackTrace";
                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str12 = substringAfterLast$default2;
            }
            String canonicalName3 = (iMEAwareTextInputEditText2 == null || (cls6 = iMEAwareTextInputEditText2.getClass()) == null) ? null : cls6.getCanonicalName();
            str12 = canonicalName3 == null ? "N/A" : canonicalName3;
            objectRef3.element = str12;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher4.find()) {
                ?? replaceAll3 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                objectRef3.element = replaceAll3;
            }
            if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str13 = (String) objectRef3.element;
            } else {
                str13 = ((String) objectRef3.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb7.append(str13);
            sb7.append(" - ");
            String str16 = text2.getCompLogTag() + " updateTIL: textChangesFlow updating oldValue: " + text2.getValue() + ", newValue: " + ((Object) charSequence2);
            if (str16 == null) {
                str16 = "null ";
            }
            sb7.append(str16);
            sb7.append(' ');
            sb7.append("");
            companion4.log(level2, sb7.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion6 = Result.INSTANCE;
                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str17 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str17, str2);
                    if (StringsKt.contains$default((CharSequence) str17, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str11);
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            String canonicalName4 = (iMEAwareTextInputEditText2 == null || (cls5 = iMEAwareTextInputEditText2.getClass()) == null) ? null : cls5.getCanonicalName();
                            str14 = canonicalName4 == null ? "N/A" : canonicalName4;
                        } else {
                            str14 = substringAfterLast$default;
                        }
                        objectRef4.element = str14;
                        Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                        if (matcher5.find()) {
                            ?? replaceAll4 = matcher5.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                            objectRef4.element = replaceAll4;
                        }
                        if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str15 = (String) objectRef4.element;
                        } else {
                            str15 = ((String) objectRef4.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str15, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb8 = new StringBuilder();
                        String str18 = text2.getCompLogTag() + " updateTIL: textChangesFlow updating oldValue: " + text2.getValue() + ", newValue: " + ((Object) charSequence2);
                        sb8.append(str18 == null ? "null " : str18);
                        sb8.append(' ');
                        sb8.append("");
                        Log.println(3, str15, sb8.toString());
                    }
                }
            }
            this.this$0.updateValue(String.valueOf(charSequence2));
            formFragment$Text$updateTIL$3$2 = this;
        } else {
            FormFragment$Text$updateTIL$3$2 formFragment$Text$updateTIL$3$23 = formFragment$Text$updateTIL$3$22;
            String str19 = str2;
            IMEAwareTextInputEditText iMEAwareTextInputEditText3 = formFragment$Text$updateTIL$3$23.$this_apply;
            FormFragment.Text text3 = formFragment$Text$updateTIL$3$23.this$0;
            HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
            HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb9 = new StringBuilder();
            Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
            if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null) {
                str4 = "null cannot be cast to non-null type android.app.Application";
                str5 = str19;
                str6 = "Throwable().stackTrace";
            } else {
                str4 = "null cannot be cast to non-null type android.app.Application";
                str5 = str19;
                str6 = "Throwable().stackTrace";
                r3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            r3 = (iMEAwareTextInputEditText3 == null || (cls4 = iMEAwareTextInputEditText3.getClass()) == null) ? 0 : cls4.getCanonicalName();
            if (r3 == 0) {
                r3 = "N/A";
            }
            objectRef5.element = r3;
            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
            if (matcher6.find()) {
                ?? replaceAll5 = matcher6.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll5, "replaceAll(\"\")");
                objectRef5.element = replaceAll5;
            }
            if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str7 = (String) objectRef5.element;
            } else {
                str7 = ((String) objectRef5.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb9.append(str7);
            sb9.append(" - ");
            String str20 = text3.getCompLogTag() + " updateTIL: textChangesFlow skipping " + ((Object) charSequence2);
            if (str20 == null) {
                str20 = "null ";
            }
            sb9.append(str20);
            sb9.append(' ');
            sb9.append("");
            companion7.log(level3, sb9.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion8 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke3, str4);
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                } catch (Throwable th3) {
                    Result.Companion companion9 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String str21 = (String) m1202constructorimpl2;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str21, str5);
                    if (StringsKt.contains$default((CharSequence) str21, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str6);
                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                        if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null) {
                            str8 = null;
                        } else {
                            str8 = null;
                            String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default3 != null) {
                                str9 = substringAfterLast$default3;
                                objectRef6.element = str9;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                if (matcher.find()) {
                                    ?? replaceAll6 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll6, "replaceAll(\"\")");
                                    objectRef6.element = replaceAll6;
                                }
                                if (((String) objectRef6.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                    str10 = (String) objectRef6.element;
                                } else {
                                    str10 = ((String) objectRef6.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb10 = new StringBuilder();
                                String str22 = text3.getCompLogTag() + " updateTIL: textChangesFlow skipping " + ((Object) charSequence2);
                                sb10.append(str22 != null ? "null " : str22);
                                sb10.append(' ');
                                sb10.append("");
                                Log.println(3, str10, sb10.toString());
                            }
                        }
                        String canonicalName5 = (iMEAwareTextInputEditText3 == null || (cls3 = iMEAwareTextInputEditText3.getClass()) == null) ? str8 : cls3.getCanonicalName();
                        str9 = canonicalName5 == null ? "N/A" : canonicalName5;
                        objectRef6.element = str9;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                        if (matcher.find()) {
                        }
                        if (((String) objectRef6.element).length() > 23) {
                        }
                        str10 = (String) objectRef6.element;
                        StringBuilder sb102 = new StringBuilder();
                        String str222 = text3.getCompLogTag() + " updateTIL: textChangesFlow skipping " + ((Object) charSequence2);
                        sb102.append(str222 != null ? "null " : str222);
                        sb102.append(' ');
                        sb102.append("");
                        Log.println(3, str10, sb102.toString());
                    }
                }
            }
            formFragment$Text$updateTIL$3$2 = this;
        }
        formFragment$Text$updateTIL$3$2.this$0.textChangedByUser = true;
        return Unit.INSTANCE;
    }
}
