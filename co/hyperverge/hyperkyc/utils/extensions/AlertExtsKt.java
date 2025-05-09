package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: AlertExts.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aR\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0000\u001aR\u0010\u0000\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0000\u001a.\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0000\u001a.\u0010\f\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0000¨\u0006\u000f"}, d2 = {"showAlert", "", "Landroid/app/Activity;", "msg", "", "title", "positiveButtonText", "negativeButtonText", "negativeAction", "Lkotlin/Function0;", "positiveAction", "Landroidx/fragment/app/Fragment;", "showPrompt", "confirmButtonText", "confirmAction", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AlertExtsKt {
    public static /* synthetic */ void showPrompt$default(Activity activity, String str, String str2, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "Got it";
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$showPrompt$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        showPrompt(activity, str, str2, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPrompt$lambda$1(Function0 confirmAction, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(confirmAction, "$confirmAction");
        confirmAction.invoke();
    }

    public static /* synthetic */ void showPrompt$default(Fragment fragment, String str, String str2, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "Got it";
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$showPrompt$4
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        showPrompt(fragment, str, str2, function0);
    }

    public static final /* synthetic */ void showPrompt(Fragment fragment, String msg, String confirmButtonText, Function0 confirmAction) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(confirmButtonText, "confirmButtonText");
        Intrinsics.checkNotNullParameter(confirmAction, "confirmAction");
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        showPrompt(requireActivity, msg, confirmButtonText, confirmAction);
    }

    public static /* synthetic */ void showAlert$default(Activity activity, String str, String str2, String str3, String str4, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = "Confirm";
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = "Cancel";
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$showAlert$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function03 = function0;
        if ((i & 32) != 0) {
            function02 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$showAlert$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        showAlert(activity, str, str5, str6, str7, function03, function02);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlert$lambda$3(Function0 positiveAction, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(positiveAction, "$positiveAction");
        positiveAction.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlert$lambda$4(Function0 negativeAction, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(negativeAction, "$negativeAction");
        negativeAction.invoke();
    }

    public static /* synthetic */ void showAlert$default(Fragment fragment, String str, String str2, String str3, String str4, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = "Confirm";
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = "Cancel";
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$showAlert$6
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function03 = function0;
        if ((i & 32) != 0) {
            function02 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$showAlert$7
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        showAlert(fragment, str, str5, str6, str7, function03, function02);
    }

    public static final /* synthetic */ void showAlert(Fragment fragment, String msg, String title, String positiveButtonText, String negativeButtonText, Function0 negativeAction, Function0 positiveAction) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(positiveButtonText, "positiveButtonText");
        Intrinsics.checkNotNullParameter(negativeButtonText, "negativeButtonText");
        Intrinsics.checkNotNullParameter(negativeAction, "negativeAction");
        Intrinsics.checkNotNullParameter(positiveAction, "positiveAction");
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        showAlert(requireActivity, msg, title, positiveButtonText, negativeButtonText, negativeAction, positiveAction);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0160, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void showPrompt(Activity activity, String msg, String confirmButtonText, final Function0 confirmAction) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(confirmButtonText, "confirmButtonText");
        Intrinsics.checkNotNullParameter(confirmAction, "confirmAction");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = activity.getClass();
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
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str5 = activity + ".showPrompt() called with: msg = " + msg + ", confirmButtonText = " + confirmButtonText + ", confirmAction = " + confirmAction;
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
                        str = null;
                    } else {
                        str = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = activity.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                    if (str2 == null) {
                        str3 = "N/A";
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                            str3 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        str4 = activity + ".showPrompt() called with: msg = " + msg + ", confirmButtonText = " + confirmButtonText + ", confirmAction = " + confirmAction;
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str3, sb2.toString());
                    }
                    str3 = str2;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str4 = activity + ".showPrompt() called with: msg = " + msg + ", confirmButtonText = " + confirmButtonText + ", confirmAction = " + confirmAction;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        new AlertDialog.Builder(activity).setMessage(msg).setPositiveButton(confirmButtonText, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertExtsKt.showPrompt$lambda$1(Function0.this, dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0224  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void showAlert(Activity activity, String msg, String title, String positiveButtonText, String negativeButtonText, Function0 negativeAction, final Function0 positiveAction) {
        String canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        Matcher matcher;
        String str3;
        final Function0 function0;
        String sb;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(positiveButtonText, "positiveButtonText");
        Intrinsics.checkNotNullParameter(negativeButtonText, "negativeButtonText");
        Intrinsics.checkNotNullParameter(negativeAction, "negativeAction");
        Intrinsics.checkNotNullParameter(positiveAction, "positiveAction");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb2 = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = activity.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str4 = canonicalName;
        if (matcher2.find()) {
            str = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
        } else {
            str = str4;
        }
        if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str = str.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb2.append(str);
        sb2.append(" - ");
        String str5 = activity + ".showAlert() called with: msg = " + msg + ", title = " + title + ", positiveButtonText = " + positiveButtonText + ", negativeButtonText = " + negativeButtonText + ", negativeAction = " + negativeAction + ", positiveAction = " + positiveAction;
        if (str5 == null) {
            str5 = "null ";
        }
        sb2.append(str5);
        sb2.append(' ');
        sb2.append("");
        companion.log(level, sb2.toString());
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
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str2 = substringAfterLast$default;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                            if (matcher.find()) {
                                str2 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                            }
                            if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str2 = str2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb3 = new StringBuilder();
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(activity);
                            sb4.append(".showAlert() called with: msg = ");
                            sb4.append(msg);
                            sb4.append(", title = ");
                            sb4.append(title);
                            sb4.append(", positiveButtonText = ");
                            str3 = positiveButtonText;
                            sb4.append(str3);
                            sb4.append(", negativeButtonText = ");
                            sb4.append(negativeButtonText);
                            sb4.append(", negativeAction = ");
                            function0 = negativeAction;
                            sb4.append(function0);
                            sb4.append(", positiveAction = ");
                            sb4.append(positiveAction);
                            sb = sb4.toString();
                            if (sb == null) {
                                sb = "null ";
                            }
                            sb3.append(sb);
                            sb3.append(' ');
                            sb3.append("");
                            Log.println(3, str2, sb3.toString());
                            new AlertDialog.Builder(activity).setTitle(title).setMessage(msg).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$$ExternalSyntheticLambda2
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i) {
                                    AlertExtsKt.showAlert$lambda$3(Function0.this, dialogInterface, i);
                                }
                            }).setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$$ExternalSyntheticLambda0
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i) {
                                    AlertExtsKt.showAlert$lambda$4(Function0.this, dialogInterface, i);
                                }
                            }).create().show();
                        }
                    }
                    Class<?> cls2 = activity.getClass();
                    if (cls2 != null) {
                        str2 = cls2.getCanonicalName();
                    }
                    if (str2 == null) {
                        str2 = "N/A";
                    }
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher.find()) {
                    }
                    if (str2.length() > 23) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb32 = new StringBuilder();
                    StringBuilder sb42 = new StringBuilder();
                    sb42.append(activity);
                    sb42.append(".showAlert() called with: msg = ");
                    sb42.append(msg);
                    sb42.append(", title = ");
                    sb42.append(title);
                    sb42.append(", positiveButtonText = ");
                    str3 = positiveButtonText;
                    sb42.append(str3);
                    sb42.append(", negativeButtonText = ");
                    sb42.append(negativeButtonText);
                    sb42.append(", negativeAction = ");
                    function0 = negativeAction;
                    sb42.append(function0);
                    sb42.append(", positiveAction = ");
                    sb42.append(positiveAction);
                    sb = sb42.toString();
                    if (sb == null) {
                    }
                    sb32.append(sb);
                    sb32.append(' ');
                    sb32.append("");
                    Log.println(3, str2, sb32.toString());
                    new AlertDialog.Builder(activity).setTitle(title).setMessage(msg).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$$ExternalSyntheticLambda2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            AlertExtsKt.showAlert$lambda$3(Function0.this, dialogInterface, i);
                        }
                    }).setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$$ExternalSyntheticLambda0
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            AlertExtsKt.showAlert$lambda$4(Function0.this, dialogInterface, i);
                        }
                    }).create().show();
                }
            }
        }
        str3 = positiveButtonText;
        function0 = negativeAction;
        new AlertDialog.Builder(activity).setTitle(title).setMessage(msg).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertExtsKt.showAlert$lambda$3(Function0.this, dialogInterface, i);
            }
        }).setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.AlertExtsKt$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertExtsKt.showAlert$lambda$4(Function0.this, dialogInterface, i);
            }
        }).create().show();
    }
}
