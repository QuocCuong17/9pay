package co.hyperverge.hyperkyc.utils;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: PermissionHandler.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006JF\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/utils/PermissionHandler;", "", "()V", "bsd", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "closeBottomSheet", "", "showPermissionBS", "permissionType", "", "textConfigs", "", "activity", "Landroid/app/Activity;", "callback", "Lco/hyperverge/hyperkyc/utils/PermDialogCallback;", "shouldShowBranding", "", "shouldBeCancellable", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PermissionHandler {
    private BottomSheetDialog bsd;

    public static /* synthetic */ void showPermissionBS$default(PermissionHandler permissionHandler, String str, Map map, Activity activity, PermDialogCallback permDialogCallback, boolean z, boolean z2, int i, Object obj) {
        if ((i & 32) != 0) {
            z2 = true;
        }
        permissionHandler.showPermissionBS(str, map, activity, permDialogCallback, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPermissionBS$lambda$4(PermissionHandler this$0, PermDialogCallback callback, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        BottomSheetDialog bottomSheetDialog = this$0.bsd;
        if (bottomSheetDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bsd");
            bottomSheetDialog = null;
        }
        bottomSheetDialog.dismiss();
        callback.onActionClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPermissionBS$lambda$5(PermDialogCallback callback, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        callback.onCancel();
    }

    public final void closeBottomSheet() {
        BottomSheetDialog bottomSheetDialog = this.bsd;
        if (bottomSheetDialog != null) {
            BottomSheetDialog bottomSheetDialog2 = null;
            if (bottomSheetDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bsd");
                bottomSheetDialog = null;
            }
            if (bottomSheetDialog.isShowing()) {
                BottomSheetDialog bottomSheetDialog3 = this.bsd;
                if (bottomSheetDialog3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bsd");
                } else {
                    bottomSheetDialog2 = bottomSheetDialog3;
                }
                bottomSheetDialog2.dismiss();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0294  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void showPermissionBS(String permissionType, Map<String, String> textConfigs, Activity activity, PermDialogCallback callback, boolean shouldShowBranding, boolean shouldBeCancellable) {
        String canonicalName;
        Object m1202constructorimpl;
        final PermDialogCallback permDialogCallback;
        String str;
        String className;
        int hashCode;
        String str2;
        String nullIfBlank;
        String str3;
        String nullIfBlank2;
        String str4;
        String nullIfBlank3;
        BottomSheetDialog bottomSheetDialog;
        BottomSheetDialog bottomSheetDialog2;
        BottomSheetDialog bottomSheetDialog3;
        BottomSheetDialog bottomSheetDialog4;
        boolean z;
        BottomSheetDialog bottomSheetDialog5;
        BottomSheetDialog bottomSheetDialog6;
        String className2;
        Intrinsics.checkNotNullParameter(permissionType, "permissionType");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        String str5 = "showPermissionBS() called with: permissionType = " + permissionType + ", textConfigs = " + textConfigs + ", activity = " + activity + ", callback = " + callback;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str = canonicalName2 == null ? "N/A" : canonicalName2;
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
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("showPermissionBS() called with: permissionType = ");
                    sb3.append(permissionType);
                    sb3.append(", textConfigs = ");
                    sb3.append(textConfigs);
                    sb3.append(", activity = ");
                    sb3.append(activity);
                    sb3.append(", callback = ");
                    permDialogCallback = callback;
                    sb3.append(permDialogCallback);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                } else {
                    permDialogCallback = callback;
                }
                BottomSheetDialog bottomSheetDialog7 = new BottomSheetDialog(activity);
                this.bsd = bottomSheetDialog7;
                bottomSheetDialog7.setDismissWithAnimation(true);
                View inflate = activity.getLayoutInflater().inflate(R.layout.hv_dialog_perm_prompt_layout, (ViewGroup) null, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "activity.layoutInflater.…ompt_layout, null, false)");
                TextView textView = (TextView) inflate.findViewById(R.id.tvTitle);
                TextView textView2 = (TextView) inflate.findViewById(R.id.tvSubtitle);
                Button button = (Button) inflate.findViewById(R.id.btnAllow);
                hashCode = permissionType.hashCode();
                String str6 = "cam";
                if (hashCode == -1324895669) {
                    if (hashCode != 463403621) {
                        if (hashCode == 1831139720 && permissionType.equals("android.permission.RECORD_AUDIO")) {
                            textView.setText(activity.getString(R.string.hk_mic_perm_title));
                            textView2.setText(activity.getString(R.string.hk_mic_perm_subtitle));
                            button.setText(activity.getString(R.string.hk_mic_perm_button));
                            str6 = "mic";
                        }
                    } else if (permissionType.equals("android.permission.CAMERA")) {
                        textView.setText(activity.getString(R.string.hk_cam_perm_title));
                        textView2.setText(activity.getString(R.string.hk_cam_perm_subtitle));
                        button.setText(activity.getString(R.string.hk_cam_perm_button));
                    }
                } else if (permissionType.equals("android.permission.NFC")) {
                    textView.setText(activity.getString(R.string.hk_nfc_access_title));
                    textView2.setText(activity.getString(R.string.hk_nfc_access_desc));
                    button.setText(activity.getString(R.string.hk_nfc_access_button));
                    str6 = "nfcScreen_nfc";
                }
                if (textConfigs == null) {
                    str2 = textConfigs.get(str6 + "_access_title");
                } else {
                    str2 = null;
                }
                nullIfBlank = CoreExtsKt.nullIfBlank(str2);
                if (nullIfBlank != null) {
                    textView.setText(UIExtsKt.fromHTML(nullIfBlank));
                }
                if (textConfigs == null) {
                    str3 = textConfigs.get(str6 + "_access_desc");
                } else {
                    str3 = null;
                }
                nullIfBlank2 = CoreExtsKt.nullIfBlank(str3);
                if (nullIfBlank2 != null) {
                    textView2.setText(UIExtsKt.fromHTML(nullIfBlank2));
                }
                if (textConfigs == null) {
                    str4 = textConfigs.get(str6 + "_access_button");
                } else {
                    str4 = null;
                }
                nullIfBlank3 = CoreExtsKt.nullIfBlank(str4);
                if (nullIfBlank3 != null) {
                    button.setText(UIExtsKt.fromHTML(nullIfBlank3));
                }
                HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(textView);
                HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(textView2);
                HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(button);
                View findViewById = inflate.findViewById(R.id.tvBranding);
                Intrinsics.checkNotNullExpressionValue(findViewById, "rootView.findViewById<TextView>(R.id.tvBranding)");
                findViewById.setVisibility(!shouldShowBranding ? 0 : 8);
                button.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.PermissionHandler$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PermissionHandler.showPermissionBS$lambda$4(PermissionHandler.this, permDialogCallback, view);
                    }
                });
                bottomSheetDialog = this.bsd;
                if (bottomSheetDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bsd");
                    bottomSheetDialog = null;
                }
                bottomSheetDialog.setContentView(inflate);
                bottomSheetDialog2 = this.bsd;
                if (bottomSheetDialog2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bsd");
                    bottomSheetDialog2 = null;
                }
                bottomSheetDialog2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: co.hyperverge.hyperkyc.utils.PermissionHandler$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        PermissionHandler.showPermissionBS$lambda$5(PermDialogCallback.this, dialogInterface);
                    }
                });
                bottomSheetDialog3 = this.bsd;
                if (bottomSheetDialog3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bsd");
                    bottomSheetDialog3 = null;
                }
                bottomSheetDialog3.show();
                bottomSheetDialog4 = this.bsd;
                if (bottomSheetDialog4 != null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bsd");
                    z = shouldBeCancellable;
                    bottomSheetDialog4 = null;
                } else {
                    z = shouldBeCancellable;
                }
                bottomSheetDialog4.setCancelable(z);
                bottomSheetDialog5 = this.bsd;
                if (bottomSheetDialog5 != null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bsd");
                    bottomSheetDialog6 = null;
                } else {
                    bottomSheetDialog6 = bottomSheetDialog5;
                }
                bottomSheetDialog6.setCanceledOnTouchOutside(z);
            }
        }
        permDialogCallback = callback;
        BottomSheetDialog bottomSheetDialog72 = new BottomSheetDialog(activity);
        this.bsd = bottomSheetDialog72;
        bottomSheetDialog72.setDismissWithAnimation(true);
        View inflate2 = activity.getLayoutInflater().inflate(R.layout.hv_dialog_perm_prompt_layout, (ViewGroup) null, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "activity.layoutInflater.…ompt_layout, null, false)");
        TextView textView3 = (TextView) inflate2.findViewById(R.id.tvTitle);
        TextView textView22 = (TextView) inflate2.findViewById(R.id.tvSubtitle);
        Button button2 = (Button) inflate2.findViewById(R.id.btnAllow);
        hashCode = permissionType.hashCode();
        String str62 = "cam";
        if (hashCode == -1324895669) {
        }
        if (textConfigs == null) {
        }
        nullIfBlank = CoreExtsKt.nullIfBlank(str2);
        if (nullIfBlank != null) {
        }
        if (textConfigs == null) {
        }
        nullIfBlank2 = CoreExtsKt.nullIfBlank(str3);
        if (nullIfBlank2 != null) {
        }
        if (textConfigs == null) {
        }
        nullIfBlank3 = CoreExtsKt.nullIfBlank(str4);
        if (nullIfBlank3 != null) {
        }
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(textView3);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(textView22);
        HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(button2);
        View findViewById2 = inflate2.findViewById(R.id.tvBranding);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootView.findViewById<TextView>(R.id.tvBranding)");
        findViewById2.setVisibility(!shouldShowBranding ? 0 : 8);
        button2.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.PermissionHandler$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PermissionHandler.showPermissionBS$lambda$4(PermissionHandler.this, permDialogCallback, view);
            }
        });
        bottomSheetDialog = this.bsd;
        if (bottomSheetDialog == null) {
        }
        bottomSheetDialog.setContentView(inflate2);
        bottomSheetDialog2 = this.bsd;
        if (bottomSheetDialog2 == null) {
        }
        bottomSheetDialog2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: co.hyperverge.hyperkyc.utils.PermissionHandler$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                PermissionHandler.showPermissionBS$lambda$5(PermDialogCallback.this, dialogInterface);
            }
        });
        bottomSheetDialog3 = this.bsd;
        if (bottomSheetDialog3 == null) {
        }
        bottomSheetDialog3.show();
        bottomSheetDialog4 = this.bsd;
        if (bottomSheetDialog4 != null) {
        }
        bottomSheetDialog4.setCancelable(z);
        bottomSheetDialog5 = this.bsd;
        if (bottomSheetDialog5 != null) {
        }
        bottomSheetDialog6.setCanceledOnTouchOutside(z);
    }
}
