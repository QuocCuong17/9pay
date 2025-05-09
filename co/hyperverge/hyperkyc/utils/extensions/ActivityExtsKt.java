package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity;
import co.hyperverge.hyperlogger.HyperLogger;
import io.sentry.protocol.Request;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: ActivityExts.kt */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0007H\u0000\u001a\u0016\u0010\b\u001a\u00020\t*\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u0004*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u001e\u0010\u000f\u001a\u0002H\u0010\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0011*\u00020\fH\u0080\b¢\u0006\u0002\u0010\u0012\u001a\u001e\u0010\u000f\u001a\u0002H\u0010\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0011*\u00020\u0013H\u0080\b¢\u0006\u0002\u0010\u0014\u001a{\u0010\u0015\u001a\u00020\t*\u00020\f2#\b\u0002\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\t0\u00172#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\t0\u00172#\b\u0002\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\t0\u0017H\u0000\u001a\u0016\u0010\u001d\u001a\u00020\t*\u00020\f2\b\b\u0003\u0010\u001e\u001a\u00020\u001fH\u0000\u001a@\u0010 \u001a\u00020\t*\u00020\f2\u0006\u0010!\u001a\u00020\u00042\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010$\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0003\u0010\u001e\u001a\u00020\u001fH\u0000\u001a\f\u0010%\u001a\u00020\t*\u00020\fH\u0000\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"activityLifecycleCallbacks", "Landroid/app/Application$ActivityLifecycleCallbacks;", "isFragmentAdded", "", "Landroidx/fragment/app/Fragment;", "isFragmentAliveAndAttached", "isHKWebCoreActivity", "Landroid/app/Activity;", "makeSecure", "", "secure", "obtainFragmentByTag", "Landroidx/appcompat/app/AppCompatActivity;", "fragmentTag", "", "obtainViewModel", "T", "Landroidx/lifecycle/ViewModel;", "(Landroidx/appcompat/app/AppCompatActivity;)Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)Landroidx/lifecycle/ViewModel;", "registerActivityCallbacks", "onActivityCreated", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "activity", "onActivityStarted", "onActivityStopped", "removeFragment", "containerId", "", "replaceContent", Request.JsonKeys.FRAGMENT, "args", "Landroid/os/Bundle;", "addToBackStack", "unregisterActivityCallbacks", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ActivityExtsKt {
    private static /* synthetic */ Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

    public static /* synthetic */ void replaceContent$default(AppCompatActivity appCompatActivity, Fragment fragment, Bundle bundle, boolean z, String str, int i, int i2, Object obj) {
        Bundle bundle2 = (i2 & 2) != 0 ? null : bundle;
        if ((i2 & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        String str2 = (i2 & 8) != 0 ? null : str;
        if ((i2 & 16) != 0) {
            i = R.id.flContent;
        }
        replaceContent(appCompatActivity, fragment, bundle2, z2, str2, i);
    }

    public static /* synthetic */ void removeFragment$default(AppCompatActivity appCompatActivity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = R.id.flContent;
        }
        removeFragment(appCompatActivity, i);
    }

    public static final /* synthetic */ Fragment obtainFragmentByTag(AppCompatActivity appCompatActivity, String fragmentTag) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(fragmentTag, "fragmentTag");
        return appCompatActivity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }

    public static /* synthetic */ void registerActivityCallbacks$default(AppCompatActivity appCompatActivity, Function1 function1, Function1 function12, Function1 function13, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Activity, Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt$registerActivityCallbacks$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Activity it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Activity activity) {
                    invoke2(activity);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            function12 = new Function1<Activity, Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt$registerActivityCallbacks$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Activity it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Activity activity) {
                    invoke2(activity);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function13 = new Function1<Activity, Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt$registerActivityCallbacks$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Activity it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Activity activity) {
                    invoke2(activity);
                    return Unit.INSTANCE;
                }
            };
        }
        registerActivityCallbacks(appCompatActivity, function1, function12, function13);
    }

    public static /* synthetic */ void makeSecure$default(Activity activity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        makeSecure(activity, z);
    }

    public static final boolean isFragmentAdded(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return fragment.getActivity() != null && fragment.isAdded();
    }

    public static final boolean isFragmentAliveAndAttached(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return fragment.getView() != null && isFragmentAdded(fragment);
    }

    public static final boolean isHKWebCoreActivity(Activity activity) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(activity, "<this>");
        boolean z = activity instanceof HKWebCoreActivity;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = activity.getClass();
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
        String str2 = "isHKWebCoreActivity() returned = " + z;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = activity.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "isHKWebCoreActivity() returned = " + z;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0211  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void replaceContent(AppCompatActivity appCompatActivity, Fragment fragment, Bundle bundle, boolean z, String str, int i) {
        String canonicalName;
        Object m1202constructorimpl;
        String str2;
        boolean z2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = appCompatActivity.getClass();
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
        String str3 = appCompatActivity + ".replaceContent() called with: fragment = " + fragment + ", args = " + bundle + ", addToBackStack = " + z + ", containerId = " + i;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = appCompatActivity.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str2 = canonicalName2 == null ? "N/A" : canonicalName2;
                    }
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
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(appCompatActivity);
                    sb3.append(".replaceContent() called with: fragment = ");
                    sb3.append(fragment);
                    sb3.append(", args = ");
                    sb3.append(bundle);
                    sb3.append(", addToBackStack = ");
                    z2 = z;
                    sb3.append(z2);
                    sb3.append(", containerId = ");
                    sb3.append(i);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                    FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                    FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction()");
                    if (bundle != null) {
                        fragment.setArguments(bundle);
                    }
                    beginTransaction.setCustomAnimations(R.anim.hs_fade_in, R.anim.hs_fade_out);
                    beginTransaction.replace(i, fragment, str);
                    if (z2) {
                        beginTransaction.addToBackStack(null);
                    }
                    beginTransaction.commitAllowingStateLoss();
                }
            }
        }
        z2 = z;
        FragmentManager supportFragmentManager2 = appCompatActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "supportFragmentManager");
        FragmentTransaction beginTransaction2 = supportFragmentManager2.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction2, "beginTransaction()");
        if (bundle != null) {
        }
        beginTransaction2.setCustomAnimations(R.anim.hs_fade_in, R.anim.hs_fade_out);
        beginTransaction2.replace(i, fragment, str);
        if (z2) {
        }
        beginTransaction2.commitAllowingStateLoss();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:1|(3:136|(1:138)(1:141)|(1:140))|7|(1:9)|10|(1:14)|15|(1:17)|18|(6:100|101|102|(1:104)|105|(8:107|(9:109|(3:127|(1:129)(1:132)|(1:131))|115|(1:117)|118|(1:122)|123|(1:125)|126)|21|22|(1:24)|25|26|(15:28|(3:89|(1:91)(1:95)|(1:93)(1:94))|34|(1:36)|37|(1:41)|42|(1:44)|45|46|47|48|(1:50)|51|(2:53|(13:55|(1:83)(2:59|(9:61|62|(1:64)|65|(1:69)|70|(1:72)|73|74))|76|(1:78)(1:82)|(1:80)(1:81)|62|(0)|65|(2:67|69)|70|(0)|73|74)(1:84))(1:85))(1:96)))|20|21|22|(0)|25|26|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01de, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01df, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01ce A[Catch: all -> 0x01de, TryCatch #2 {all -> 0x01de, blocks: (B:22:0x01b4, B:24:0x01ce, B:25:0x01d1), top: B:21:0x01b4 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void removeFragment(AppCompatActivity appCompatActivity, int i) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        Throwable m1205exceptionOrNullimpl;
        String str2;
        Object m1202constructorimpl2;
        String str3;
        String str4;
        Matcher matcher;
        String str5;
        String className2;
        String className3;
        Fragment findFragmentById;
        String className4;
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = appCompatActivity.getClass();
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
        String str6 = appCompatActivity + ".removeFragment() called with: containerId = " + i;
        if (str6 == null) {
            str6 = "null ";
        }
        sb.append(str6);
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
                charSequence = "co.hyperverge";
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = appCompatActivity.getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
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
                    String str7 = appCompatActivity + ".removeFragment() called with: containerId = " + i;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb2.append(str7);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                Result.Companion companion4 = Result.INSTANCE;
                FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
                Intrinsics.checkNotNullExpressionValue(beginTransaction, "fragmentManager.beginTransaction()");
                findFragmentById = supportFragmentManager.findFragmentById(i);
                if (findFragmentById != null) {
                    beginTransaction.remove(findFragmentById);
                }
                Object m1202constructorimpl3 = Result.m1202constructorimpl(Integer.valueOf(beginTransaction.commit()));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = appCompatActivity.getClass();
                        String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                        str2 = canonicalName3 == null ? str : canonicalName3;
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher4.find()) {
                        str2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str2);
                    sb3.append(" - ");
                    String str8 = "removeFragment() failed with: " + m1205exceptionOrNullimpl;
                    if (str8 == null) {
                        str8 = "null ";
                    }
                    sb3.append(str8);
                    sb3.append(' ');
                    sb3.append("");
                    companion5.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str3 = null;
                            } else {
                                str3 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str4 = substringAfterLast$default;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                    if (matcher.find()) {
                                        str4 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                    }
                                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str4 = str4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    str5 = "removeFragment() failed with: " + m1205exceptionOrNullimpl;
                                    if (str5 == null) {
                                        str5 = "null ";
                                    }
                                    sb4.append(str5);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(6, str4, sb4.toString());
                                    return;
                                }
                            }
                            Class<?> cls4 = appCompatActivity.getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str3;
                            str4 = canonicalName4 == null ? str : canonicalName4;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher.find()) {
                            }
                            if (str4.length() > 23) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb42 = new StringBuilder();
                            str5 = "removeFragment() failed with: " + m1205exceptionOrNullimpl;
                            if (str5 == null) {
                            }
                            sb42.append(str5);
                            sb42.append(' ');
                            sb42.append("");
                            Log.println(6, str4, sb42.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        Result.Companion companion42 = Result.INSTANCE;
        FragmentManager supportFragmentManager2 = appCompatActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "supportFragmentManager");
        FragmentTransaction beginTransaction2 = supportFragmentManager2.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction2, "fragmentManager.beginTransaction()");
        findFragmentById = supportFragmentManager2.findFragmentById(i);
        if (findFragmentById != null) {
        }
        Object m1202constructorimpl32 = Result.m1202constructorimpl(Integer.valueOf(beginTransaction2.commit()));
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
        if (m1205exceptionOrNullimpl == null) {
        }
    }

    public static final /* synthetic */ <T extends ViewModel> T obtainViewModel(AppCompatActivity appCompatActivity) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = appCompatActivity.getClass();
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
        String str2 = appCompatActivity + ".obtainViewModel() called";
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = appCompatActivity.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = appCompatActivity + ".obtainViewModel() called";
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        ViewModelProvider viewModelProvider = new ViewModelProvider(appCompatActivity);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) viewModelProvider.get(ViewModel.class);
    }

    public static final /* synthetic */ <T extends ViewModel> T obtainViewModel(FragmentActivity fragmentActivity) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = fragmentActivity.getClass();
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
        String str2 = fragmentActivity + ".obtainViewModel() called";
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = fragmentActivity.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = fragmentActivity + ".obtainViewModel() called";
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        ViewModelProvider viewModelProvider = new ViewModelProvider(fragmentActivity);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) viewModelProvider.get(ViewModel.class);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0160, code lost:
    
        if (r0 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0170, code lost:
    
        if (r0 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0174, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0183, code lost:
    
        if (r0.find() == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0185, code lost:
    
        r9 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0192, code lost:
    
        if (r9.length() <= 23) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0198, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x019b, code lost:
    
        r9 = r9.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01a3, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "registerActivityCallbacks() called with: onActivityCreated = [" + r18 + "], onActivityStarted = [" + r19 + "], onActivityStopped = [" + r20 + ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01c8, code lost:
    
        if (r4 != null) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01ca, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01cc, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r9, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0173, code lost:
    
        r9 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void registerActivityCallbacks(AppCompatActivity appCompatActivity, final Function1 onActivityCreated, final Function1 onActivityStarted, final Function1 onActivityStopped) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(onActivityCreated, "onActivityCreated");
        Intrinsics.checkNotNullParameter(onActivityStarted, "onActivityStarted");
        Intrinsics.checkNotNullParameter(onActivityStopped, "onActivityStopped");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = appCompatActivity.getClass();
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
        String str4 = "registerActivityCallbacks() called with: onActivityCreated = [" + onActivityCreated + "], onActivityStarted = [" + onActivityStarted + "], onActivityStopped = [" + onActivityStopped + ']';
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
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
                    Class<?> cls2 = appCompatActivity.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt$registerActivityCallbacks$5
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(outState, "outState");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                String canonicalName2;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(activity, "activity");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str5 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                    if (canonicalName2 == null) {
                        canonicalName2 = "N/A";
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
                sb2.append(canonicalName2);
                sb2.append(" - ");
                String str6 = "registerActivityCallbacks: onActivityCreated() called with: activity = [" + activity + "], savedInstanceState = [" + savedInstanceState + ']';
                if (str6 == null) {
                    str6 = "null ";
                }
                sb2.append(str6);
                sb2.append(' ');
                sb2.append("");
                companion4.log(level2, sb2.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                if (canonicalName3 != null) {
                                    str5 = canonicalName3;
                                }
                            } else {
                                str5 = substringAfterLast$default;
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
                            StringBuilder sb3 = new StringBuilder();
                            String str7 = "registerActivityCallbacks: onActivityCreated() called with: activity = [" + activity + "], savedInstanceState = [" + savedInstanceState + ']';
                            if (str7 == null) {
                                str7 = "null ";
                            }
                            sb3.append(str7);
                            sb3.append(' ');
                            sb3.append("");
                            Log.println(3, str5, sb3.toString());
                        }
                    }
                }
                onActivityCreated.invoke(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                String canonicalName2;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(activity, "activity");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str5 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                    if (canonicalName2 == null) {
                        canonicalName2 = "N/A";
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
                sb2.append(canonicalName2);
                sb2.append(" - ");
                String str6 = "registerActivityCallbacks: onActivityStarted() called with: activity = [" + activity + ']';
                if (str6 == null) {
                    str6 = "null ";
                }
                sb2.append(str6);
                sb2.append(' ');
                sb2.append("");
                companion4.log(level2, sb2.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                if (canonicalName3 != null) {
                                    str5 = canonicalName3;
                                }
                            } else {
                                str5 = substringAfterLast$default;
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
                            StringBuilder sb3 = new StringBuilder();
                            String str7 = "registerActivityCallbacks: onActivityStarted() called with: activity = [" + activity + ']';
                            if (str7 == null) {
                                str7 = "null ";
                            }
                            sb3.append(str7);
                            sb3.append(' ');
                            sb3.append("");
                            Log.println(3, str5, sb3.toString());
                        }
                    }
                }
                onActivityStarted.invoke(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                String canonicalName2;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(activity, "activity");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str5 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                    if (canonicalName2 == null) {
                        canonicalName2 = "N/A";
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
                sb2.append(canonicalName2);
                sb2.append(" - ");
                String str6 = "registerActivityCallbacks: onActivityStopped() called with: activity = [" + activity + ']';
                if (str6 == null) {
                    str6 = "null ";
                }
                sb2.append(str6);
                sb2.append(' ');
                sb2.append("");
                companion4.log(level2, sb2.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                if (canonicalName3 != null) {
                                    str5 = canonicalName3;
                                }
                            } else {
                                str5 = substringAfterLast$default;
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
                            StringBuilder sb3 = new StringBuilder();
                            String str7 = "registerActivityCallbacks: onActivityStopped() called with: activity = [" + activity + ']';
                            if (str7 == null) {
                                str7 = "null ";
                            }
                            sb3.append(str7);
                            sb3.append(' ');
                            sb3.append("");
                            Log.println(3, str5, sb3.toString());
                        }
                    }
                }
                onActivityStopped.invoke(activity);
            }
        };
        appCompatActivity.getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x0142, code lost:
    
        if (r0 == null) goto L131;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void unregisterActivityCallbacks(AppCompatActivity appCompatActivity) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = appCompatActivity.getClass();
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
        String str2 = appCompatActivity + ".unregisterActivityCallbacks() called, activityLifecycleCallbacks: " + activityLifecycleCallbacks;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
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
                        Class<?> cls2 = appCompatActivity.getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = appCompatActivity + ".unregisterActivityCallbacks() called, activityLifecycleCallbacks: " + activityLifecycleCallbacks;
                    sb2.append(str3 != null ? str3 : "null ");
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks2 = activityLifecycleCallbacks;
        if (activityLifecycleCallbacks2 != null) {
            appCompatActivity.getApplication().unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks2);
        }
        activityLifecycleCallbacks = null;
    }

    public static final /* synthetic */ void makeSecure(Activity activity, boolean z) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(activity, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = activity.getClass();
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
        String str2 = activity + ".makeSecure() called with: secure = " + z;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = activity.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = activity + ".makeSecure() called with: secure = " + z;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        if (z) {
            activity.getWindow().setFlags(8192, 8192);
        } else {
            activity.getWindow().clearFlags(8192);
        }
        if (CoreExtsKt.isAPI33OrAbove()) {
            activity.setRecentsScreenshotEnabled(!z);
        }
    }
}
