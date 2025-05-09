package co.hyperverge.hyperkyc.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.GravityCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.DynamicFormUIConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.Margin;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: DynamicFormUtils.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\t\u001a\u0004\u0018\u00010\nJ(\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0013H\u0002J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\u0018\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010\nJ0\u0010%\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u0010+\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ \u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\b\u0010\t\u001a\u0004\u0018\u00010\nJ \u00100\u001a\u00020\u00062\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u0002072\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020:2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020=2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020@2\b\u0010\t\u001a\u0004\u0018\u00010\nJ \u0010A\u001a\u00020\u00062\u0006\u00101\u001a\u0002022\u0006\u0010B\u001a\u00020C2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u0010D\u001a\u00020\u00062\u0006\u00109\u001a\u00020:2\b\u0010\t\u001a\u0004\u0018\u00010\nJ2\u0010E\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010F\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020G2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lco/hyperverge/hyperkyc/utils/DynamicFormUtils;", "", "displayMetrics", "Landroid/util/DisplayMetrics;", "(Landroid/util/DisplayMetrics;)V", "applyMargin", "", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "dynamicFormUIConfig", "Lco/hyperverge/hyperkyc/core/hv/models/DynamicFormUIConfig;", "margin", "Lco/hyperverge/hyperkyc/utils/extensions/Margin;", "applyPadding", "createCustomDrawable", "Landroid/graphics/drawable/GradientDrawable;", "cornerRadius", "", "strokeColor", "", "fillColor", "createCustomRadioButtonDrawable", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "createOvalDrawable", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "strokeWidth", "size", "createSolidOvalDrawable", "color", "customiseButton", "materialButton", "Lcom/google/android/material/button/MaterialButton;", "customiseCheckBox", "checkBox", "Landroid/widget/CheckBox;", "customiseChip", WorkflowModule.Properties.Section.Component.Type.CHIP, "Lcom/google/android/material/chip/Chip;", "isSelected", "", "isEnabled", "customiseContainer", "customiseDivider", "dividerView", "dividerTextView", "Landroid/widget/TextView;", "customiseDropdown", "inputLayout", "Lcom/google/android/material/textfield/TextInputLayout;", "dropDown", "Landroid/widget/AutoCompleteTextView;", "customiseImage", "imageView", "Landroid/widget/ImageView;", "customiseLabel", "textView", "Lcom/google/android/material/textview/MaterialTextView;", "customiseLoader", WorkflowModule.Properties.Section.Component.Type.LOADER, "Landroid/widget/ProgressBar;", "customiseRadioButton", "radioButton", "Landroid/widget/RadioButton;", "customiseTIL", "inputEditText", "Landroid/widget/EditText;", "customiseTimerLabel", "setChipStateBasedConfigs", "setLinearLayoutGravity", "Landroid/widget/LinearLayout;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DynamicFormUtils {
    private final DisplayMetrics displayMetrics;

    public final void customiseImage(ImageView imageView, DynamicFormUIConfig dynamicFormUIConfig) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
    }

    public DynamicFormUtils(DisplayMetrics displayMetrics) {
        Intrinsics.checkNotNullParameter(displayMetrics, "displayMetrics");
        this.displayMetrics = displayMetrics;
    }

    private final GradientDrawable createCustomDrawable(float cornerRadius, int strokeColor, int fillColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setStroke(1, strokeColor);
        gradientDrawable.setColor(fillColor);
        return gradientDrawable;
    }

    private static final int applyMargin$getMargin(DynamicFormUIConfig dynamicFormUIConfig, Margin margin, Function1<? super DynamicFormUIConfig, String> function1, Function1<? super Margin, Integer> function12) {
        int i;
        String invoke;
        String nullIfBlank;
        if (dynamicFormUIConfig == null || (invoke = function1.invoke(dynamicFormUIConfig)) == null || (nullIfBlank = CoreExtsKt.nullIfBlank(invoke)) == null) {
            Integer invoke2 = margin != null ? function12.invoke(margin) : null;
            if (invoke2 != null) {
                return invoke2.intValue();
            }
            i = 0;
        } else {
            i = Integer.parseInt(nullIfBlank);
        }
        return ViewExtsKt.getDp(i);
    }

    private static final int applyPadding$getPadding(DynamicFormUIConfig dynamicFormUIConfig, View view, Function1<? super DynamicFormUIConfig, String> function1, Function1<? super View, Integer> function12) {
        String invoke;
        String nullIfBlank;
        return (dynamicFormUIConfig == null || (invoke = function1.invoke(dynamicFormUIConfig)) == null || (nullIfBlank = CoreExtsKt.nullIfBlank(invoke)) == null) ? function12.invoke(view).intValue() : ViewExtsKt.getDp(Integer.parseInt(nullIfBlank));
    }

    public final Drawable createCustomRadioButtonDrawable(Context context, DynamicFormUIConfig dynamicFormUIConfig) {
        int alphaComponent;
        String disabledBorderColor;
        Integer rgbaToColorRes;
        String selectedBorderColor;
        Integer rgbaToColorRes2;
        String borderColor;
        Integer rgbaToColorRes3;
        Intrinsics.checkNotNullParameter(context, "context");
        int color = (dynamicFormUIConfig == null || (borderColor = dynamicFormUIConfig.getBorderColor()) == null || (rgbaToColorRes3 = UIExtsKt.rgbaToColorRes(borderColor)) == null) ? ContextCompat.getColor(context, R.color.hk_radio_unselected_border_color) : rgbaToColorRes3.intValue();
        int color2 = (dynamicFormUIConfig == null || (selectedBorderColor = dynamicFormUIConfig.getSelectedBorderColor()) == null || (rgbaToColorRes2 = UIExtsKt.rgbaToColorRes(selectedBorderColor)) == null) ? ContextCompat.getColor(context, R.color.hv_primary) : rgbaToColorRes2.intValue();
        int color3 = (dynamicFormUIConfig == null || (disabledBorderColor = dynamicFormUIConfig.getDisabledBorderColor()) == null || (rgbaToColorRes = UIExtsKt.rgbaToColorRes(disabledBorderColor)) == null) ? ContextCompat.getColor(context, R.color.hk_radio_unselected_border_color) : rgbaToColorRes.intValue();
        int color4 = ContextCompat.getColor(context, R.color.hk_radio_background_color);
        float applyDimension = TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 24.0f, context.getResources().getDisplayMetrics());
        int applyDimension3 = (int) TypedValue.applyDimension(1, 6.0f, context.getResources().getDisplayMetrics());
        int i = (int) applyDimension;
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{createOvalDrawable(color4, color2, i, applyDimension2), new InsetDrawable(createSolidOvalDrawable(color2), applyDimension3, applyDimension3, applyDimension3, applyDimension3)});
        Drawable createOvalDrawable = createOvalDrawable(color4, color, i, applyDimension2);
        LayerDrawable layerDrawable2 = new LayerDrawable(new Drawable[]{createOvalDrawable(color4, color3, i, applyDimension2), new InsetDrawable(createSolidOvalDrawable(color3), applyDimension3, applyDimension3, applyDimension3, applyDimension3)});
        alphaComponent = ColorUtils.setAlphaComponent(color3, 128);
        Drawable createOvalDrawable2 = createOvalDrawable(alphaComponent, color3, i, applyDimension2);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_enabled}, layerDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, layerDrawable2);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, createOvalDrawable);
        stateListDrawable.addState(new int[0], createOvalDrawable2);
        return stateListDrawable;
    }

    private final Drawable createOvalDrawable(int backgroundColor, int strokeColor, int strokeWidth, int size) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setSize(size, size);
        gradientDrawable.setColor(backgroundColor);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

    private final Drawable createSolidOvalDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    private final void setChipStateBasedConfigs(Context context, Chip chip, boolean isSelected, boolean isEnabled, DynamicFormUIConfig dynamicFormUIConfig) {
        Triple triple;
        String backgroundColor;
        Integer rgbaToColorRes;
        String borderColor;
        Integer rgbaToColorRes2;
        String color;
        Integer rgbaToColorRes3;
        String selectedBackgroundColor;
        Integer rgbaToColorRes4;
        String selectedBorderColor;
        Integer rgbaToColorRes5;
        String selectedTextColor;
        Integer rgbaToColorRes6;
        if (!isEnabled) {
            triple = new Triple(Integer.valueOf(ContextCompat.getColor(context, R.color.hk_chip_disabled_text_color)), Integer.valueOf(ContextCompat.getColor(context, R.color.hk_chip_disabled_border_color)), Integer.valueOf(ContextCompat.getColor(context, R.color.hk_chip_disabled_background_color)));
        } else if (isSelected) {
            triple = new Triple(Integer.valueOf((dynamicFormUIConfig == null || (selectedTextColor = dynamicFormUIConfig.getSelectedTextColor()) == null || (rgbaToColorRes6 = UIExtsKt.rgbaToColorRes(selectedTextColor)) == null) ? ContextCompat.getColor(context, R.color.hv_primary) : rgbaToColorRes6.intValue()), Integer.valueOf((dynamicFormUIConfig == null || (selectedBorderColor = dynamicFormUIConfig.getSelectedBorderColor()) == null || (rgbaToColorRes5 = UIExtsKt.rgbaToColorRes(selectedBorderColor)) == null) ? ContextCompat.getColor(context, R.color.hk_chip_selected_border_color) : rgbaToColorRes5.intValue()), Integer.valueOf((dynamicFormUIConfig == null || (selectedBackgroundColor = dynamicFormUIConfig.getSelectedBackgroundColor()) == null || (rgbaToColorRes4 = UIExtsKt.rgbaToColorRes(selectedBackgroundColor)) == null) ? ContextCompat.getColor(context, R.color.hk_chip_selected_background_color) : rgbaToColorRes4.intValue()));
        } else {
            triple = new Triple(Integer.valueOf((dynamicFormUIConfig == null || (color = dynamicFormUIConfig.getColor()) == null || (rgbaToColorRes3 = UIExtsKt.rgbaToColorRes(color)) == null) ? ContextCompat.getColor(context, R.color.hk_title_text_color) : rgbaToColorRes3.intValue()), Integer.valueOf((dynamicFormUIConfig == null || (borderColor = dynamicFormUIConfig.getBorderColor()) == null || (rgbaToColorRes2 = UIExtsKt.rgbaToColorRes(borderColor)) == null) ? ContextCompat.getColor(context, R.color.hk_chip_unselected_border_color) : rgbaToColorRes2.intValue()), Integer.valueOf((dynamicFormUIConfig == null || (backgroundColor = dynamicFormUIConfig.getBackgroundColor()) == null || (rgbaToColorRes = UIExtsKt.rgbaToColorRes(backgroundColor)) == null) ? ContextCompat.getColor(context, R.color.hk_chip_unselected_background_color) : rgbaToColorRes.intValue()));
        }
        int intValue = ((Number) triple.component1()).intValue();
        int intValue2 = ((Number) triple.component2()).intValue();
        int intValue3 = ((Number) triple.component3()).intValue();
        chip.setTextColor(intValue);
        chip.setChipBackgroundColor(ColorStateList.valueOf(intValue3));
        chip.setChipStrokeColor(ColorStateList.valueOf(intValue2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0140, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0150, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0154, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0163, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0165, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0170, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0176, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0179, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0180, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "customiseLabel() called with: textView = " + r18 + ", dynamicFormUIConfig = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x019a, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x019c, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x019e, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0153, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseLabel(MaterialTextView textView, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String fontWeight;
        String className2;
        Intrinsics.checkNotNullParameter(textView, "textView");
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
        String str4 = "customiseLabel() called with: textView = " + textView + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        if (dynamicFormUIConfig != null) {
            String color = dynamicFormUIConfig.getColor();
            if (color != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextColor(textView, color);
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), textView);
            }
            String alignment = dynamicFormUIConfig.getAlignment();
            if (alignment != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setGravity(alignment, textView);
            }
            String font = dynamicFormUIConfig.getFont();
            if (font != null && (fontWeight = dynamicFormUIConfig.getFontWeight()) != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(textView, font, fontWeight);
            }
            String backgroundColor = dynamicFormUIConfig.getBackgroundColor();
            if (backgroundColor != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextBackgroundColor(backgroundColor, textView);
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(textView, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(textView, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0140, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0150, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0154, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0163, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0165, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0170, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0176, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0179, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0180, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "customiseLabel() called with: textView = " + r18 + ", dynamicFormUIConfig = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x019a, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x019c, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x019e, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0153, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseTimerLabel(MaterialTextView textView, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String fontWeight;
        String className2;
        Intrinsics.checkNotNullParameter(textView, "textView");
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
        String str4 = "customiseLabel() called with: textView = " + textView + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        if (dynamicFormUIConfig != null) {
            String color = dynamicFormUIConfig.getColor();
            if (color != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextColor(textView, color);
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), textView);
            }
            String alignment = dynamicFormUIConfig.getAlignment();
            if (alignment != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setGravity(alignment, textView);
            }
            String font = dynamicFormUIConfig.getFont();
            if (font != null && (fontWeight = dynamicFormUIConfig.getFontWeight()) != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(textView, font, fontWeight);
            }
            String backgroundColor = dynamicFormUIConfig.getBackgroundColor();
            if (backgroundColor != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextBackgroundColor(backgroundColor, textView);
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(textView, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(textView, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
        }
    }

    public final void setLinearLayoutGravity(LinearLayout view, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String alignment;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
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
        String str2 = "setLinearLayoutGravity() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                        Class<?> cls2 = getClass();
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
                    String str3 = "setLinearLayoutGravity() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        if (dynamicFormUIConfig == null || (alignment = dynamicFormUIConfig.getAlignment()) == null) {
            return;
        }
        HyperSnapBridgeKt.getUiConfigUtil().setLinearLayoutGravity(alignment, view);
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0140, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0150, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0154, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0163, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0165, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0170, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0176, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0179, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0180, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "customiseButton() called with: materialButton = " + r18 + ", dynamicFormUIConfig = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x019a, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x019c, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x019e, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0153, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseButton(MaterialButton materialButton, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        Map<String, Object> map;
        String className2;
        Intrinsics.checkNotNullParameter(materialButton, "materialButton");
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
        String str4 = "customiseButton() called with: materialButton = " + materialButton + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        if (dynamicFormUIConfig != null) {
            MaterialButton materialButton2 = materialButton;
            Map<String, Map<String, Object>> icons = dynamicFormUIConfig.getIcons();
            if (icons != null && (map = icons.get("primaryButtonIcon")) != null) {
                String str5 = (String) map.get("url");
                Boolean bool = (Boolean) map.get("shouldShow");
                HyperSnapBridgeKt.getUiConfigUtil().setPrimaryButtonIcon(materialButton2, str5, bool != null ? bool.booleanValue() : true);
            }
            String backgroundColor = dynamicFormUIConfig.getBackgroundColor();
            if (backgroundColor != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setButtonBackgroundColor(backgroundColor, materialButton2);
            }
            String color = dynamicFormUIConfig.getColor();
            if (color != null) {
                HyperSnapUIConfigUtil uiConfigUtil = HyperSnapBridgeKt.getUiConfigUtil();
                if (!materialButton2.isEnabled()) {
                    color = "#66050552";
                }
                uiConfigUtil.setTextColor(materialButton2, color, true);
            }
            String borderColor = dynamicFormUIConfig.getBorderColor();
            if (borderColor != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBorderColor(borderColor, (Button) materialButton2);
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), (Button) materialButton2);
            }
            if (dynamicFormUIConfig.getBorderRadius() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBorderRadius(ViewExtsKt.getDp(Integer.parseInt(r2)), materialButton2);
            }
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont((Button) materialButton2, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight((Button) materialButton2, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing((Button) materialButton2, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x014a, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseContainer(View view, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String borderRadius;
        String backgroundColor;
        Integer rgbaToColorRes;
        String borderColor;
        Integer rgbaToColorRes2;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        Integer num = null;
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
        String str2 = "customiseContainer() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                        Class<?> cls2 = getClass();
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
                    String str3 = "customiseContainer() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        int colorRes = (dynamicFormUIConfig == null || (borderColor = dynamicFormUIConfig.getBorderColor()) == null || (rgbaToColorRes2 = UIExtsKt.rgbaToColorRes(borderColor)) == null) ? UIExtsKt.toColorRes("#1A050582") : rgbaToColorRes2.intValue();
        int colorRes2 = (dynamicFormUIConfig == null || (backgroundColor = dynamicFormUIConfig.getBackgroundColor()) == null || (rgbaToColorRes = UIExtsKt.rgbaToColorRes(backgroundColor)) == null) ? UIExtsKt.toColorRes("#00000000") : rgbaToColorRes.intValue();
        float dp = (dynamicFormUIConfig == null || (borderRadius = dynamicFormUIConfig.getBorderRadius()) == null) ? 6.0f : ViewExtsKt.getDp(Integer.parseInt(borderRadius));
        String alignment = dynamicFormUIConfig != null ? dynamicFormUIConfig.getAlignment() : null;
        if (alignment != null) {
            switch (alignment.hashCode()) {
                case -1383228885:
                    if (alignment.equals("bottom")) {
                        num = 80;
                        break;
                    }
                    break;
                case -1364013995:
                    if (alignment.equals(TtmlNode.CENTER)) {
                        num = 17;
                        break;
                    }
                    break;
                case -348726240:
                    if (alignment.equals("center_vertical")) {
                        num = 16;
                        break;
                    }
                    break;
                case 115029:
                    if (alignment.equals(ViewHierarchyConstants.DIMENSION_TOP_KEY)) {
                        num = 48;
                        break;
                    }
                    break;
                case 3317767:
                    if (alignment.equals("left")) {
                        num = Integer.valueOf(GravityCompat.START);
                        break;
                    }
                    break;
                case 108511772:
                    if (alignment.equals(TtmlNode.RIGHT)) {
                        num = Integer.valueOf(GravityCompat.END);
                        break;
                    }
                    break;
                case 1063616078:
                    if (alignment.equals("center_horizontal")) {
                        num = 1;
                        break;
                    }
                    break;
            }
        }
        if (num != null) {
            ((LinearLayout) view).setGravity(num.intValue());
        }
        ((LinearLayout) view).setBackground(createCustomDrawable(dp, colorRes, colorRes2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0153, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:78:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseDivider(View dividerView, TextView dividerTextView, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className;
        String str5;
        String className2;
        Intrinsics.checkNotNullParameter(dividerView, "dividerView");
        Intrinsics.checkNotNullParameter(dividerTextView, "dividerTextView");
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
        String str6 = "customiseDivider() called with: dividerView = " + dividerView + ", dividerTextView = " + dividerTextView + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                        str4 = "customiseDivider() called with: dividerView = " + dividerView + ", dividerTextView = " + dividerTextView + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                    str4 = "customiseDivider() called with: dividerView = " + dividerView + ", dividerTextView = " + dividerTextView + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        if (dynamicFormUIConfig == null || (str5 = dynamicFormUIConfig.getBorderColor()) == null) {
            str5 = "#0505821A";
        }
        ColorStateList colorStateList = UIExtsKt.toColorStateList(str5);
        if (dynamicFormUIConfig != null) {
            String color = dynamicFormUIConfig.getColor();
            if (color != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextColor(dividerTextView, color);
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), dividerTextView);
            }
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(dividerTextView, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            Drawable background = dividerTextView.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
            LayerDrawable layerDrawable = (LayerDrawable) background;
            String circleBorderColor = dynamicFormUIConfig.getCircleBorderColor();
            if (circleBorderColor != null) {
                Drawable drawable = layerDrawable.getDrawable(0);
                if (drawable instanceof GradientDrawable) {
                    ((GradientDrawable) drawable).setStroke(1, UIExtsKt.toColorStateList(circleBorderColor));
                }
            }
            String backgroundColor = dynamicFormUIConfig.getBackgroundColor();
            if (backgroundColor != null) {
                Drawable drawable2 = layerDrawable.getDrawable(1);
                if (drawable2 instanceof GradientDrawable) {
                    ((GradientDrawable) drawable2).setColor(UIExtsKt.toColorStateList(backgroundColor));
                }
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(dividerTextView, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(dividerTextView, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
        }
        FrameLayout frameLayout = (FrameLayout) dividerView;
        Drawable background2 = frameLayout.getBackground();
        Intrinsics.checkNotNull(background2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        ((GradientDrawable) background2).setStroke(1, colorStateList, ViewExtsKt.getDp(4), ViewExtsKt.getDp(4));
        frameLayout.invalidate();
        dividerTextView.invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseTIL(TextInputLayout inputLayout, EditText inputEditText, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        Matcher matcher;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(inputLayout, "inputLayout");
        Intrinsics.checkNotNullParameter(inputEditText, "inputEditText");
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
        String str4 = "customiseTIL() called with: editText = " + inputEditText + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str3 = substringAfterLast$default;
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
                            str2 = "customiseTIL() called with: editText = " + inputEditText + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
                            if (str2 == null) {
                                str2 = "null ";
                            }
                            sb2.append(str2);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str3, sb2.toString());
                        }
                    }
                    Class<?> cls2 = getClass();
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str;
                    if (canonicalName2 != null) {
                        str3 = canonicalName2;
                    }
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str2 = "customiseTIL() called with: editText = " + inputEditText + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
                    if (str2 == null) {
                    }
                    sb22.append(str2);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        if (dynamicFormUIConfig != null) {
            int color = MaterialColors.getColor(inputLayout, R.attr.colorOnSurface);
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(inputLayout, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), inputEditText);
            }
            String color2 = dynamicFormUIConfig.getColor();
            if (color2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setHintColor(inputLayout, color2);
            }
            String borderColor = dynamicFormUIConfig.getBorderColor();
            if (borderColor != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBoxStrokeColorStateList(inputLayout, new int[][]{new int[]{android.R.attr.state_enabled, -16842908}, new int[]{-16842910, android.R.attr.state_focused}, new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}}, borderColor, color);
            }
            if (dynamicFormUIConfig.getBorderRadius() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBorderRadius(ViewExtsKt.getDp(Integer.parseInt(r5)), inputLayout);
            }
            String borderColor2 = dynamicFormUIConfig.getBorderColor();
            if (borderColor2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBorderColor(borderColor2, inputLayout);
            }
            inputEditText.setTextColor(UIExtsKt.toColorStateList(color));
            String color3 = dynamicFormUIConfig.getColor();
            if (color3 != null) {
                inputEditText.setHintTextColor(UIExtsKt.toColorStateList(color3));
                HyperSnapBridgeKt.getUiConfigUtil().setEditTextColor(inputEditText, color3);
                HyperSnapBridgeKt.getUiConfigUtil().setEditTextHintColor(inputEditText, color3);
            }
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(inputEditText, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String fontSize2 = dynamicFormUIConfig.getFontSize();
            if (fontSize2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize2), inputEditText);
            }
            String alignment = dynamicFormUIConfig.getAlignment();
            if (alignment != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setGravity(alignment, inputEditText);
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(inputLayout, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(inputLayout, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
            String lineHeight2 = dynamicFormUIConfig.getLineHeight();
            if (lineHeight2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(inputEditText, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight2)));
            }
            String charSpacing2 = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(inputEditText, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing2)));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0140, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0150, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0154, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0163, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0165, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0170, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0176, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0179, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0180, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "customiseCheckBox() called with: checkBox = " + r18 + ", dynamicFormUIConfig = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x019a, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x019c, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x019e, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0153, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseCheckBox(CheckBox checkBox, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(checkBox, "checkBox");
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
        String str4 = "customiseCheckBox() called with: checkBox = " + checkBox + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        if (dynamicFormUIConfig != null) {
            String borderColor = dynamicFormUIConfig.getBorderColor();
            if (borderColor != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBorderColor(borderColor, checkBox);
            }
            String color = dynamicFormUIConfig.getColor();
            if (color != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextColor(checkBox, color);
            }
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(checkBox, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), checkBox);
            }
            String alignment = dynamicFormUIConfig.getAlignment();
            if (alignment != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setGravity(alignment, checkBox);
            }
            String borderColor2 = dynamicFormUIConfig.getBorderColor();
            if (borderColor2 != null) {
                checkBox.setButtonTintList(UIExtsKt.toColorStateList(borderColor2));
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(checkBox, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(checkBox, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0140, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0150, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0154, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0163, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0165, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0170, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0176, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0179, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0180, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "customiseCheckBox() called with: checkBox = " + r18 + ", dynamicFormUIConfig = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x019a, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x019c, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x019e, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0153, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseRadioButton(RadioButton radioButton, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(radioButton, "radioButton");
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
        String str4 = "customiseCheckBox() called with: checkBox = " + radioButton + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        if (dynamicFormUIConfig != null) {
            String color = dynamicFormUIConfig.getColor();
            if (color != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextColor(radioButton, color);
            }
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(radioButton, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), radioButton);
            }
            String alignment = dynamicFormUIConfig.getAlignment();
            if (alignment != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setGravity(alignment, radioButton);
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(radioButton, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(radioButton, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseChip(Context context, Chip chip, boolean isSelected, boolean isEnabled, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        Matcher matcher;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(chip, "chip");
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
        String str4 = "customiseCheckBox() called with: checkBox = " + chip + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str3 = substringAfterLast$default;
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
                            str2 = "customiseCheckBox() called with: checkBox = " + chip + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
                            if (str2 == null) {
                                str2 = "null ";
                            }
                            sb2.append(str2);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str3, sb2.toString());
                        }
                    }
                    Class<?> cls2 = getClass();
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str;
                    if (canonicalName2 != null) {
                        str3 = canonicalName2;
                    }
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str2 = "customiseCheckBox() called with: checkBox = " + chip + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
                    if (str2 == null) {
                    }
                    sb22.append(str2);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        setChipStateBasedConfigs(context, chip, isSelected, isEnabled, dynamicFormUIConfig);
        if (dynamicFormUIConfig != null) {
            if (dynamicFormUIConfig.getBorderRadius() != null) {
                ShapeAppearanceModel build = ShapeAppearanceModel.builder().setAllCorners(0, UIExtsKt.dpToPx(context, Float.parseFloat(r0))).build();
                Intrinsics.checkNotNullExpressionValue(build, "builder()\n              …                 .build()");
                chip.setShapeAppearanceModel(build);
            }
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(chip, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), chip);
            }
            String alignment = dynamicFormUIConfig.getAlignment();
            if (alignment != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setGravity(alignment, chip);
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(chip, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(chip, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0153, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:90:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void customiseDropdown(TextInputLayout inputLayout, AutoCompleteTextView dropDown, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(inputLayout, "inputLayout");
        Intrinsics.checkNotNullParameter(dropDown, "dropDown");
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
        String str5 = "customiseDropdown() called with: inputLayout = " + inputLayout + ", dropDown = " + dropDown + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                    Class<?> cls2 = getClass();
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
                        str4 = "customiseDropdown() called with: inputLayout = " + inputLayout + ", dropDown = " + dropDown + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                    str4 = "customiseDropdown() called with: inputLayout = " + inputLayout + ", dropDown = " + dropDown + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        if (dynamicFormUIConfig != null) {
            int color = MaterialColors.getColor(dropDown, R.attr.colorOnSurface);
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(inputLayout, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String fontSize = dynamicFormUIConfig.getFontSize();
            if (fontSize != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize), inputLayout);
            }
            String color2 = dynamicFormUIConfig.getColor();
            if (color2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setHintColor(inputLayout, color2);
                ColorStateList colorStateList = UIExtsKt.toColorStateList(color2);
                inputLayout.setEndIconTintList(colorStateList);
                inputLayout.setBackgroundTintList(colorStateList);
                HyperSnapBridgeKt.getUiConfigUtil().setEditTextColor(dropDown, color2);
            }
            String borderColor = dynamicFormUIConfig.getBorderColor();
            if (borderColor != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBoxStrokeColorStateList(inputLayout, new int[][]{new int[]{android.R.attr.state_enabled, -16842908}, new int[]{-16842910, android.R.attr.state_focused}, new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}}, borderColor, color);
            }
            if (dynamicFormUIConfig.getBorderRadius() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBorderRadius(ViewExtsKt.getDp(Integer.parseInt(r0)), inputLayout);
            }
            if (dynamicFormUIConfig.getFont() != null && dynamicFormUIConfig.getFontWeight() != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setFont(dropDown, dynamicFormUIConfig.getFont(), dynamicFormUIConfig.getFontWeight());
            }
            String fontSize2 = dynamicFormUIConfig.getFontSize();
            if (fontSize2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setTextSize(Float.parseFloat(fontSize2), dropDown);
            }
            String borderColor2 = dynamicFormUIConfig.getBorderColor();
            if (borderColor2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setBorderColor(borderColor2, dropDown);
            }
            String alignment = dynamicFormUIConfig.getAlignment();
            if (alignment != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setGravity(alignment, dropDown);
            }
            String lineHeight = dynamicFormUIConfig.getLineHeight();
            if (lineHeight != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(inputLayout, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight)));
            }
            String charSpacing = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(inputLayout, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing)));
            }
            String lineHeight2 = dynamicFormUIConfig.getLineHeight();
            if (lineHeight2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setLineHeight(dropDown, UIExtsKt.applyDimension(this.displayMetrics, 1, Float.parseFloat(lineHeight2)));
            }
            String charSpacing2 = dynamicFormUIConfig.getCharSpacing();
            if (charSpacing2 != null) {
                HyperSnapBridgeKt.getUiConfigUtil().setCharacterSpacing(dropDown, UIExtsKt.toEm(this.displayMetrics, Float.parseFloat(charSpacing2)));
            }
        }
    }

    public final void customiseLoader(ProgressBar loader, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String color;
        String className2;
        Intrinsics.checkNotNullParameter(loader, "loader");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
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
        String str2 = "customiseLoader() called with: customiseLoader = " + loader + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                        Class<?> cls2 = getClass();
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
                    String str3 = "customiseLoader() called with: customiseLoader = " + loader + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        if (dynamicFormUIConfig == null || (color = dynamicFormUIConfig.getColor()) == null) {
            return;
        }
        loader.setIndeterminateTintList(UIExtsKt.toColorStateList(color));
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0124  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void applyMargin(View view, DynamicFormUIConfig dynamicFormUIConfig, Margin margin) {
        String canonicalName;
        String str;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str2;
        String canonicalName2;
        String className;
        StackTraceElement stackTraceElement;
        String str3;
        Matcher matcher;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        String str6;
        Matcher matcher2;
        String str7;
        String className2;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(view, "view");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher3.find()) {
            canonicalName = matcher3.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str8 = "applyMargin() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig + ", margin = " + margin;
        if (str8 == null) {
            str8 = "null ";
        }
        sb.append(str8);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            charSequence = "co.hyperverge";
            str2 = "packageName";
            str = " - ";
        } else {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                str = " - ";
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
                    int applyMargin$getMargin = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$leftMargin$1
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(DynamicFormUIConfig getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return getMargin.getMarginLeft();
                        }
                    }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$leftMargin$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Integer invoke(Margin getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return Integer.valueOf(getMargin.getLeft());
                        }
                    });
                    int applyMargin$getMargin2 = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$rightMargin$1
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(DynamicFormUIConfig getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return getMargin.getMarginRight();
                        }
                    }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$rightMargin$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Integer invoke(Margin getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return Integer.valueOf(getMargin.getRight());
                        }
                    });
                    int applyMargin$getMargin3 = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$topMargin$1
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(DynamicFormUIConfig getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return getMargin.getMarginTop();
                        }
                    }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$topMargin$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Integer invoke(Margin getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return Integer.valueOf(getMargin.getTop());
                        }
                    });
                    int applyMargin$getMargin4 = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$bottomMargin$1
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(DynamicFormUIConfig getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return getMargin.getMarginBottom();
                        }
                    }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$bottomMargin$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Integer invoke(Margin getMargin) {
                            Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                            return Integer.valueOf(getMargin.getBottom());
                        }
                    });
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement != null) {
                    }
                    Class<?> cls2 = getClass();
                    if (cls2 == null) {
                    }
                    if (r1 != null) {
                    }
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(str3);
                    sb2.append(str);
                    str4 = "bottomMargin = " + applyMargin$getMargin4;
                    if (str4 == null) {
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    companion4.log(level2, sb2.toString());
                    if (!CoreExtsKt.isRelease()) {
                    }
                    ViewExtsKt.addMargin(view, new Margin(applyMargin$getMargin, applyMargin$getMargin3, applyMargin$getMargin2, applyMargin$getMargin4));
                }
            } catch (Throwable th2) {
                th = th2;
                str = " - ";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                charSequence = "co.hyperverge";
                str2 = "packageName";
            } else {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                charSequence = "co.hyperverge";
                str2 = "packageName";
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
                        }
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher4.find()) {
                        canonicalName2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb3 = new StringBuilder();
                    String str9 = "applyMargin() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig + ", margin = " + margin;
                    if (str9 == null) {
                        str9 = "null ";
                    }
                    sb3.append(str9);
                    sb3.append(' ');
                    sb3.append("");
                    Log.println(3, canonicalName2, sb3.toString());
                }
            }
        }
        int applyMargin$getMargin5 = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$leftMargin$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return getMargin.getMarginLeft();
            }
        }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$leftMargin$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(Margin getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return Integer.valueOf(getMargin.getLeft());
            }
        });
        int applyMargin$getMargin22 = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$rightMargin$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return getMargin.getMarginRight();
            }
        }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$rightMargin$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(Margin getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return Integer.valueOf(getMargin.getRight());
            }
        });
        int applyMargin$getMargin32 = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$topMargin$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return getMargin.getMarginTop();
            }
        }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$topMargin$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(Margin getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return Integer.valueOf(getMargin.getTop());
            }
        });
        int applyMargin$getMargin42 = applyMargin$getMargin(dynamicFormUIConfig, margin, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$bottomMargin$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return getMargin.getMarginBottom();
            }
        }, new Function1<Margin, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyMargin$bottomMargin$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(Margin getMargin) {
                Intrinsics.checkNotNullParameter(getMargin, "$this$getMargin");
                return Integer.valueOf(getMargin.getBottom());
            }
        });
        HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
        HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb22 = new StringBuilder();
        StackTraceElement[] stackTrace22 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace22, "Throwable().stackTrace");
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace22);
        if (stackTraceElement != null || (className3 = stackTraceElement.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls22 = getClass();
            String canonicalName3 = cls22 == null ? cls22.getCanonicalName() : null;
            str3 = canonicalName3 != null ? "N/A" : canonicalName3;
        }
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
        if (matcher.find()) {
            str3 = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
        }
        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str3 = str3.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb22.append(str3);
        sb22.append(str);
        str4 = "bottomMargin = " + applyMargin$getMargin42;
        if (str4 == null) {
            str4 = "null ";
        }
        sb22.append(str4);
        sb22.append(' ');
        sb22.append("");
        companion42.log(level22, sb22.toString());
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
            String str10 = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(str10, str2);
                if (StringsKt.contains$default((CharSequence) str10, charSequence, false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                        str5 = null;
                    } else {
                        str5 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str6 = substringAfterLast$default;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                            if (matcher2.find()) {
                                str6 = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                            }
                            if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str6 = str6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb4 = new StringBuilder();
                            str7 = "bottomMargin = " + applyMargin$getMargin42;
                            if (str7 == null) {
                                str7 = "null ";
                            }
                            sb4.append(str7);
                            sb4.append(' ');
                            sb4.append("");
                            Log.println(3, str6, sb4.toString());
                        }
                    }
                    Class<?> cls4 = getClass();
                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str5;
                    str6 = canonicalName4 == null ? "N/A" : canonicalName4;
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                    if (matcher2.find()) {
                    }
                    if (str6.length() > 23) {
                        str6 = str6.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb42 = new StringBuilder();
                    str7 = "bottomMargin = " + applyMargin$getMargin42;
                    if (str7 == null) {
                    }
                    sb42.append(str7);
                    sb42.append(' ');
                    sb42.append("");
                    Log.println(3, str6, sb42.toString());
                }
            }
        }
        ViewExtsKt.addMargin(view, new Margin(applyMargin$getMargin5, applyMargin$getMargin32, applyMargin$getMargin22, applyMargin$getMargin42));
    }

    public final void applyPadding(View view, DynamicFormUIConfig dynamicFormUIConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
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
        String str2 = "applyPadding() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
                        Class<?> cls2 = getClass();
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
                    String str3 = "applyPadding() called with: view = " + view + ", dynamicFormUIConfig = " + dynamicFormUIConfig;
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
        view.setPadding(applyPadding$getPadding(dynamicFormUIConfig, view, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingLeft$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return getPadding.getPaddingLeft();
            }
        }, new Function1<View, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingLeft$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(View getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return Integer.valueOf(getPadding.getPaddingLeft());
            }
        }), applyPadding$getPadding(dynamicFormUIConfig, view, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingTop$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return getPadding.getPaddingTop();
            }
        }, new Function1<View, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingTop$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(View getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return Integer.valueOf(getPadding.getPaddingTop());
            }
        }), applyPadding$getPadding(dynamicFormUIConfig, view, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingRight$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return getPadding.getPaddingRight();
            }
        }, new Function1<View, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingRight$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(View getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return Integer.valueOf(getPadding.getPaddingRight());
            }
        }), applyPadding$getPadding(dynamicFormUIConfig, view, new Function1<DynamicFormUIConfig, String>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingBottom$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(DynamicFormUIConfig getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return getPadding.getPaddingBottom();
            }
        }, new Function1<View, Integer>() { // from class: co.hyperverge.hyperkyc.utils.DynamicFormUtils$applyPadding$paddingBottom$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(View getPadding) {
                Intrinsics.checkNotNullParameter(getPadding, "$this$getPadding");
                return Integer.valueOf(getPadding.getPaddingBottom());
            }
        }));
    }
}
