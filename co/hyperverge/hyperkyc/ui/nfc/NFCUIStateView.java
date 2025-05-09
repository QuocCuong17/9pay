package co.hyperverge.hyperkyc.ui.nfc;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.databinding.HkViewNfcUiStateBinding;
import co.hyperverge.hyperkyc.ui.nfc.NFCUIFlowModel;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import io.sentry.Session;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: NFCUIStateView.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B'\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB+\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCUIStateView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "textConfig", "", "", "(Landroid/content/Context;Ljava/util/Map;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkViewNfcUiStateBinding;", "counter", "progressBorderColor", "progressTextColor", "rightDrawable", "text", "getText", "key", "defaultText", "initView", "", "setUIModel", "nfcUIFlowModel", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel;", "setValues", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NFCUIStateView extends LinearLayout {
    private HkViewNfcUiStateBinding binding;
    private int counter;
    private int progressBorderColor;
    private int progressTextColor;
    private int rightDrawable;
    private String text;
    private Map<String, String> textConfig;

    /* compiled from: NFCUIStateView.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NFCUIFlowModel.NFCUIStatus.values().length];
            try {
                iArr[NFCUIFlowModel.NFCUIStatus.Success.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NFCUIFlowModel.NFCUIStatus.Failure.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NFCUIFlowModel.NFCUIStatus.Processing.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[NFCUIFlowModel.NFCUIStatus.Disabled.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NFCUIStateView(Context context, Map<String, String> map) {
        super(context);
        HkViewNfcUiStateBinding inflate = HkViewNfcUiStateBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.binding = inflate;
        this.progressBorderColor = -1;
        this.rightDrawable = -1;
        this.text = "";
        this.textConfig = MapsKt.emptyMap();
        this.textConfig = map == null ? MapsKt.emptyMap() : map;
        initView();
    }

    public NFCUIStateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        HkViewNfcUiStateBinding inflate = HkViewNfcUiStateBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.binding = inflate;
        this.progressBorderColor = -1;
        this.rightDrawable = -1;
        this.text = "";
        this.textConfig = MapsKt.emptyMap();
        initView();
    }

    public NFCUIStateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        HkViewNfcUiStateBinding inflate = HkViewNfcUiStateBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.binding = inflate;
        this.progressBorderColor = -1;
        this.rightDrawable = -1;
        this.text = "";
        this.textConfig = MapsKt.emptyMap();
        initView();
    }

    public NFCUIStateView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        HkViewNfcUiStateBinding inflate = HkViewNfcUiStateBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.binding = inflate;
        this.progressBorderColor = -1;
        this.rightDrawable = -1;
        this.text = "";
        this.textConfig = MapsKt.emptyMap();
        initView();
    }

    private final void initView() {
        HkViewNfcUiStateBinding hkViewNfcUiStateBinding = this.binding;
        hkViewNfcUiStateBinding.statusProgress.setBarLength(ViewExtsKt.getDp(24));
        hkViewNfcUiStateBinding.statusProgress.setBarWidth(ViewExtsKt.getDp(2));
        hkViewNfcUiStateBinding.statusProgress.setBarColor(R.color.hk_progress_wheel_color);
        hkViewNfcUiStateBinding.statusProgress.setRimColor(R.color.hk_border_disabled_color);
        hkViewNfcUiStateBinding.statusProgress.setRimWidth(ViewExtsKt.getDp(1));
        hkViewNfcUiStateBinding.statusProgress.setTextColor(R.color.hk_progress_text_color);
        hkViewNfcUiStateBinding.statusProgress.setTextSize(ViewExtsKt.getDp(10));
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0159, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0169, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x016d, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x017c, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x017e, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x018b, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0191, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0194, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x019b, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "setUIModel called with  uiState : [ " + r18.getUiState() + " ], status : [ " + r18.getStatus() + " ]";
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01c0, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01c2, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01c4, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x016c, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setUIModel(NFCUIFlowModel nfcUIFlowModel) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String text;
        String className2;
        Intrinsics.checkNotNullParameter(nfcUIFlowModel, "nfcUIFlowModel");
        NFCUIFlowModel.NFCUIState uiState = nfcUIFlowModel.getUiState();
        Intrinsics.checkNotNull(uiState);
        this.rightDrawable = uiState.getRightIconDrawable();
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
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
        String str4 = "setUIModel called with  uiState : [ " + nfcUIFlowModel.getUiState() + " ], status : [ " + nfcUIFlowModel.getStatus() + " ]";
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
                    Class<?> cls2 = getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        str = null;
        HkViewNfcUiStateBinding hkViewNfcUiStateBinding = this.binding;
        hkViewNfcUiStateBinding.statusImage.setVisibility(0);
        hkViewNfcUiStateBinding.statusProgress.setVisibility(8);
        NFCUIFlowModel.NFCUIStatus status = nfcUIFlowModel.getStatus();
        int i = status == null ? -1 : WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i == 1) {
            ImageView statusImage = hkViewNfcUiStateBinding.statusImage;
            Intrinsics.checkNotNullExpressionValue(statusImage, "statusImage");
            UIExtsKt.setImage(statusImage, R.drawable.hk_ic_green_tick_26);
            String str5 = uiState.getTextConfigKey() + "_success";
            String string = getResources().getString(uiState.getSuccessMessageId());
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(nfcUIState.successMessageId)");
            text = getText(str5, string);
        } else if (i == 2) {
            ImageView statusImage2 = hkViewNfcUiStateBinding.statusImage;
            Intrinsics.checkNotNullExpressionValue(statusImage2, "statusImage");
            UIExtsKt.setImage(statusImage2, R.drawable.hk_ic_alert);
            String str6 = uiState.getTextConfigKey() + "_failure";
            String string2 = getResources().getString(uiState.getFailureMessageId());
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(nfcUIState.failureMessageId)");
            text = getText(str6, string2);
        } else if (i == 3) {
            hkViewNfcUiStateBinding.statusProgress.setVisibility(0);
            hkViewNfcUiStateBinding.statusImage.setVisibility(8);
            hkViewNfcUiStateBinding.statusProgress.startSpinning();
            this.progressBorderColor = R.color.hk_border_color;
            this.progressTextColor = R.color.hk_progress_text_color;
            String str7 = uiState.getTextConfigKey() + "_processing";
            String string3 = getResources().getString(uiState.getProcessingMessageId());
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(nfcU…tate.processingMessageId)");
            text = getText(str7, string3);
        } else if (i != 4) {
            text = str;
        } else {
            hkViewNfcUiStateBinding.statusProgress.setVisibility(0);
            hkViewNfcUiStateBinding.statusImage.setVisibility(8);
            this.progressBorderColor = R.color.hk_border_disabled_color;
            this.progressTextColor = R.color.hk_progress_text_disabled_color;
            if (hkViewNfcUiStateBinding.statusProgress.getIsSpinning()) {
                hkViewNfcUiStateBinding.statusProgress.stopSpinning();
            }
            String textConfigKey = uiState.getTextConfigKey();
            String string4 = getResources().getString(uiState.getDisabledMessageId());
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(nfcUIState.disabledMessageId)");
            text = getText(textConfigKey, string4);
        }
        if (text != null) {
            this.text = text;
        }
        this.counter = nfcUIFlowModel.getCounter();
        setValues(nfcUIFlowModel);
    }

    private final String getText(String key, String defaultText) {
        String str = this.textConfig.get(key);
        if (str != null) {
            if (str.length() > 0) {
                return str;
            }
        }
        return defaultText;
    }

    private final void setValues(NFCUIFlowModel nfcUIFlowModel) {
        HkViewNfcUiStateBinding hkViewNfcUiStateBinding = this.binding;
        hkViewNfcUiStateBinding.statusText.setText(this.text);
        HyperSnapUIConfigUtil uiConfigUtil = HyperSnapBridgeKt.getUiConfigUtil();
        TextView textView = hkViewNfcUiStateBinding.statusText;
        NFCUIFlowModel.NFCUIStatus status = nfcUIFlowModel.getStatus();
        Intrinsics.checkNotNull(status);
        uiConfigUtil.customiseNfcStatusTextView(textView, status.name());
        if (this.rightDrawable != -1) {
            hkViewNfcUiStateBinding.statusText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, this.rightDrawable, 0);
            hkViewNfcUiStateBinding.statusText.setCompoundDrawablePadding(20);
        }
        hkViewNfcUiStateBinding.statusProgress.setText(String.valueOf(this.counter));
        hkViewNfcUiStateBinding.statusProgress.setTextColor(this.progressTextColor);
        if (this.progressBorderColor != -1) {
            hkViewNfcUiStateBinding.statusProgress.setRimColor(this.progressBorderColor);
        }
    }
}
