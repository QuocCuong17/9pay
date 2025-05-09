package co.hyperverge.hypersnapsdk.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.ImageViewCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import co.hyperverge.hypersnapsdk.model.UIFont;
import co.hyperverge.hypersnapsdk.model.UIFontWeight;
import co.hyperverge.hypersnapsdk.model.UIIcons;
import co.hyperverge.hypersnapsdk.model.UILogos;
import co.hyperverge.hypersnapsdk.utils.PicassoManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class HyperSnapUIConfigUtil {
    private static final String TAG = "co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil";
    private static HyperSnapUIConfigUtil hyperSnapUIConfigUtil;
    private Context appContext;
    private Map<TextKey, String> defaultFontMap;
    private final Set<String> fontFilePaths = new HashSet();
    private final HashMap<TextKey, Typeface> typefaceMap = new HashMap<>();
    private boolean isDefaultIconsUsed = true;

    private HyperSnapUIConfigUtil() {
    }

    public static HyperSnapUIConfigUtil getInstance() {
        if (hyperSnapUIConfigUtil == null) {
            hyperSnapUIConfigUtil = new HyperSnapUIConfigUtil();
        }
        return hyperSnapUIConfigUtil;
    }

    public void preloadFonts() {
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HyperSnapUIConfigUtil.this.m547x84624320();
            }
        });
    }

    /* renamed from: lambda$preloadFonts$0$co-hyperverge-hypersnapsdk-utils-HyperSnapUIConfigUtil, reason: not valid java name */
    public /* synthetic */ void m547x84624320() {
        createDefaultFontMap();
        createFontPaths("");
        createTypefaceMap();
    }

    public void init(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            this.appContext = applicationContext;
            PicassoManager.getInstance(applicationContext);
            getConfig().init(this.appContext.getResources().getDisplayMetrics());
        }
        preloadIcons();
        preloadLogos();
        preloadFonts();
        preloadAnimation();
        preloadBackgroundImage();
    }

    private void preloadAnimation() {
        String str = TAG;
        HVLogUtils.d(str, "preloadAnimation() called");
        if (getAppContext() == null) {
            Log.d(str, "preloadAnimation: context is null");
        } else if (getConfig().getAnimation() != null) {
            getConfig().getAnimation().preloadAnimation(getAppContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getAppContext() {
        Context context = this.appContext;
        if (context != null) {
            return context;
        }
        return null;
    }

    public UIConfig getConfig() {
        UIConfig uiConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getUiConfig();
        if (uiConfig == null) {
            return new UIConfig();
        }
        HVLogUtils.d(TAG, "getConfig() returned: " + uiConfig);
        return uiConfig;
    }

    public void createDefaultFontMap() {
        HVLogUtils.d(TAG, "createDefaultFontMap() called");
        HashMap hashMap = new HashMap();
        this.defaultFontMap = hashMap;
        hashMap.put(TextKey.TITLE_TEXT_KEY, "mulish_bold");
        this.defaultFontMap.put(TextKey.DESCRIPTION_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.NFC_STATUS_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.NFC_PROGRESS_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.API_LOADING_TITLE_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.API_LOADING_HINT_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.STATUS_TEXT_KEY, "mulish_bold");
        this.defaultFontMap.put(TextKey.DOCUMENT_SIDE_HINT_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.RETAKE_MESSAGE_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.PRIMARY_BUTTON_TEXT_KEY, "mulish_semibold");
        this.defaultFontMap.put(TextKey.SECONDARY_BUTTON_TEXT_KEY, "mulish_semibold");
        this.defaultFontMap.put(TextKey.ALERT_TEXT_BOX_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.PICKER_BUTTON_TEXT_KEY, "mulish_semibold");
        this.defaultFontMap.put(TextKey.COUNTRY_LIST_ITEM_TEXT_KEY, "mulish_regular");
        this.defaultFontMap.put(TextKey.COUNTRY_LIST_ITEM_SELECTED_TEXT_KEY, "mulish_bold");
        this.defaultFontMap.put(TextKey.COUNTRY_SEARCH_HINT_TEXT_KEY, "mulish_semibold");
    }

    public void customiseDescriptionTextView(TextView textView, boolean z) {
        HVLogUtils.d(TAG, "customiseDescriptionTextView() called with: textView = [" + textView + "], isCaptureScreen = [" + z + "]");
        String captureScreenDescriptionTextColor = z ? getConfig().getColors().getCaptureScreenDescriptionTextColor() : getConfig().getColors().getDescriptionTextColor();
        if (captureScreenDescriptionTextColor.isEmpty()) {
            captureScreenDescriptionTextColor = getConfig().getColors().getDescriptionTextColor();
        }
        setTextColor(textView, captureScreenDescriptionTextColor);
        setTextSize(getConfig().getFontSize().getDescriptionTextSize(), textView);
        setGravity(getConfig().getAlignment().getDescriptionTextAlignment(), textView);
        setFont(textView, TextKey.DESCRIPTION_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getDescriptionTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getDescriptionTextCharSpacing());
    }

    public void customiseDescriptionTextView(TextView textView) {
        customiseDescriptionTextView(textView, false);
    }

    public void customiseStatusTextView(TextView textView) {
        customiseStatusTextView(textView, false);
    }

    public void customiseStatusTextView(TextView textView, boolean z) {
        HVLogUtils.d(TAG, "customiseStatusTextView() called with: called with: textView = [" + textView + "], isCaptureScreen = [" + z + "]");
        String captureScreenStatusTextColor = z ? getConfig().getColors().getCaptureScreenStatusTextColor() : getConfig().getColors().getStatusTextColor();
        if (captureScreenStatusTextColor.isEmpty()) {
            captureScreenStatusTextColor = getConfig().getColors().getStatusTextColor();
        }
        setTextColor(textView, captureScreenStatusTextColor);
        setTextSize(getConfig().getFontSize().getStatusTextSize(), textView);
        setGravity(getConfig().getAlignment().getStatusTextAlignment(), textView);
        setFont(textView, TextKey.STATUS_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getTitleTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getTitleTextCharSpacing());
    }

    public void customiseDocumentSideTextView(TextView textView) {
        HVLogUtils.d(TAG, "customiseDocumentSideTextView() called with: textView = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getDocumentSideHintTextColor());
        setTextSize(getConfig().getFontSize().getDocumentSideHintTextSize(), textView);
        setFont(textView, TextKey.DOCUMENT_SIDE_HINT_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getDocumentSideHintTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getDocumentSideHintTextCharSpacing());
    }

    public void customiseRetakeMessageTextView(TextView textView) {
        HVLogUtils.d(TAG, "customiseRetakeMessageTextView() called with: textView = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getRetakeMessageColor());
        setTextSize(getConfig().getFontSize().getRetakeMessageTextSize(), textView);
        setFont(textView, TextKey.RETAKE_MESSAGE_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getRetakeMessageLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getRetakeMessageCharSpacing());
    }

    public void customiseCaptureButton(ImageView imageView) {
        HVLogUtils.d(TAG, "customiseCaptureButton() called with: imageView = [" + imageView + "]");
        if (isValidHexColor(getConfig().getColors().getCaptureButtonColor())) {
            ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(Color.parseColor(getConfig().getColors().getCaptureButtonColor())));
        }
    }

    public void customiseAlertBoxTextView(TextView textView) {
        HVLogUtils.d(TAG, "customiseAlertBoxTextView() called with: textView = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getAlertTextBoxTextColor());
        setBorderColor(getConfig().getColors().getAlertTextBoxBorderColor(), textView);
        setBackgroundColor(textView, getConfig().getColors().getAlertTextBoxBackgroundColor());
        setTextSize(getConfig().getFontSize().getAlertTextBoxTextSize(), textView);
        setFont(textView, TextKey.ALERT_TEXT_BOX_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getAlertTextBoxTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getAlertTextBoxTextCharSpacing());
    }

    public void customiseCountrySearchText(AppCompatEditText appCompatEditText) {
        HVLogUtils.d(TAG, "customiseCountrySearchText() called with: editText = [" + appCompatEditText + "]");
        setTextColor(appCompatEditText, getConfig().getColors().getCountrySearchTextColor());
        setTextSize(getConfig().getFontSize().getCountrySearchTextSize(), appCompatEditText);
        setFont(appCompatEditText, TextKey.COUNTRY_SEARCH_HINT_TEXT_KEY);
        setLineHeight((EditText) appCompatEditText, getConfig().getLineHeight().getCountrySearchTextLineHeight());
        setCharacterSpacing((EditText) appCompatEditText, getConfig().getCharSpacing().getAlertTextBoxTextCharSpacing());
    }

    public void customiseApiLoadingTitleText(TextView textView) {
        HVLogUtils.d(TAG, "customiseApiLoadingTitleText() called with: apiLoadingTitleText = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getApiLoadingTitleTextColor());
        setTextSize(getConfig().getFontSize().getApiLoadingTitleTextSize(), textView);
        setGravity(getConfig().getAlignment().getApiLoadingTitleTextAlignment(), textView);
        setFont(textView, TextKey.API_LOADING_TITLE_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getApiLoadingTitleTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getApiLoadingTitleTextCharSpacing());
    }

    public void customiseApiLoadingHintText(TextView textView) {
        HVLogUtils.d(TAG, "customiseApiLoadingHintText() called with: apiLoadingHintText = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getApiLoadingHintTextColor());
        setTextSize(getConfig().getFontSize().getApiLoadingHintTextSize(), textView);
        setGravity(getConfig().getAlignment().getApiLoadingHintTextAlignment(), textView);
        setFont(textView, TextKey.API_LOADING_HINT_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getApiLoadingHintTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getApiLoadingHintTextCharSpacing());
    }

    public void customiseEditText(EditText editText) {
        HVLogUtils.d(TAG, "customiseEditText() called with: editText = [" + editText + "]");
        setFont(editText, TextKey.TITLE_TEXT_KEY);
        setLineHeight(editText, getConfig().getLineHeight().getTitleTextLineHeight());
        setCharacterSpacing(editText, getConfig().getCharSpacing().getTitleTextCharSpacing());
    }

    public void customisePrimaryButton(Button button) {
        String primaryButtonDisabledBackgroundColor;
        String primaryButtonDisabledBorderColor;
        HVLogUtils.d(TAG, "customisePrimaryButton() called with: button = [" + button + "]");
        UIIcons.UIIconProperties primaryButtonIcon = getConfig().getIcons().getPrimaryButtonIcon();
        setPrimaryButtonIcon(button, primaryButtonIcon.getUrl(), primaryButtonIcon.isShouldShow());
        if (button.isEnabled()) {
            primaryButtonDisabledBackgroundColor = getConfig().getColors().getPrimaryButtonBackgroundColor();
        } else {
            primaryButtonDisabledBackgroundColor = getConfig().getColors().getPrimaryButtonDisabledBackgroundColor();
        }
        setButtonBackgroundColor(primaryButtonDisabledBackgroundColor, button);
        setTextColor(button, getConfig().getColors().getPrimaryButtonTextColor(), true);
        if (button.isEnabled()) {
            primaryButtonDisabledBorderColor = getConfig().getColors().getPrimaryButtonBorderColor();
        } else {
            primaryButtonDisabledBorderColor = getConfig().getColors().getPrimaryButtonDisabledBorderColor();
        }
        setBorderColor(primaryButtonDisabledBorderColor, button);
        setTextSize(getConfig().getFontSize().getPrimaryButtonTextSize(), button);
        setBorderRadius(getConfig().getBorderRadius().getPrimaryButtonRadius(), button);
        setFont(button, TextKey.PRIMARY_BUTTON_TEXT_KEY);
        setLineHeight(button, getConfig().getLineHeight().getPrimaryButtonTextLineHeight());
        setCharacterSpacing(button, getConfig().getCharSpacing().getPrimaryButtonTextCharSpacing());
    }

    public void customiseSecondaryButton(Button button) {
        HVLogUtils.d(TAG, "customiseSecondaryButton() called with: button = [" + button + "]");
        setButtonBackgroundColor(getConfig().getColors().getSecondaryButtonBackgroundColor(), button);
        setTextColor(button, getConfig().getColors().getSecondaryButtonTextColor(), false);
        setBorderColor(getConfig().getColors().getSecondaryButtonBorderColor(), button);
        setTextSize(getConfig().getFontSize().getSecondaryButtonTextSize(), button);
        setBorderRadius(getConfig().getBorderRadius().getSecondaryButtonRadius(), button);
        setFont(button, TextKey.SECONDARY_BUTTON_TEXT_KEY);
        setLineHeight(button, getConfig().getLineHeight().getSecondaryButtonTextLineHeight());
        setCharacterSpacing(button, getConfig().getCharSpacing().getSecondaryButtonTextCharSpacing());
    }

    public void customiseCountryListItem(TextView textView, boolean z) {
        HVLogUtils.d(TAG, "customiseCountryListItem() called with: textView = [" + textView + "], isSelected = [" + z + "]");
        setTextColor(textView, getConfig().getColors().getCountryListItemTextColor());
        setTextSize(getConfig().getFontSize().getCountryListItemTextSize(), textView);
        if (z) {
            setFont(textView, TextKey.COUNTRY_LIST_ITEM_SELECTED_TEXT_KEY);
            setLineHeight(textView, getConfig().getLineHeight().getCountryListItemSelectedTextLineHeight());
            setCharacterSpacing(textView, getConfig().getCharSpacing().getCountryListItemSelectedTextCharSpacing());
        } else {
            setFont(textView, TextKey.COUNTRY_LIST_ITEM_TEXT_KEY);
            setLineHeight(textView, getConfig().getLineHeight().getCountryListItemTextLineHeight());
            setCharacterSpacing(textView, getConfig().getCharSpacing().getCountryListItemTextCharSpacing());
        }
    }

    public void customisePickerButton(Button button) {
        HVLogUtils.d(TAG, "customisePickerButton() called with: button = [" + button + "]");
        setButtonBackgroundColor(getConfig().getColors().getPickerBackgroundColor(), button);
        setTextColor(button, getConfig().getColors().getPickerTextColor(), false);
        setBorderColor(getConfig().getColors().getPickerBorderColor(), button);
        setTextSize(getConfig().getFontSize().getPickerTextSize(), button);
        setBorderRadius(getConfig().getBorderRadius().getPickerBorderRadius(), button);
        setFont(button, TextKey.PICKER_BUTTON_TEXT_KEY);
        setLineHeight(button, getConfig().getLineHeight().getPickerTextLineHeight());
        setCharacterSpacing(button, getConfig().getCharSpacing().getPickerTextCharSpacing());
    }

    public void customiseSecondaryButton(TextView textView) {
        HVLogUtils.d(TAG, "customiseSecondaryButton() called with: textView = [" + textView + "]");
        setFont(textView, TextKey.SECONDARY_BUTTON_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getSecondaryButtonTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getSecondaryButtonTextCharSpacing());
    }

    public void customiseTitleTextView(TextView textView) {
        customiseTitleTextView(textView, false);
    }

    public void customiseTitleTextView(TextView textView, boolean z) {
        HVLogUtils.d(TAG, "customiseTitleTextView() called with: textView = [" + textView + "], isCaptureScreen = [" + z + "]");
        String captureScreenTitleTextColor = z ? getConfig().getColors().getCaptureScreenTitleTextColor() : getConfig().getColors().getTitleTextColor();
        if (captureScreenTitleTextColor.isEmpty()) {
            captureScreenTitleTextColor = getConfig().getColors().getTitleTextColor();
        }
        setTextColor(textView, captureScreenTitleTextColor);
        setTextSize(getConfig().getFontSize().getTitleTextSize(), textView);
        setGravity(getConfig().getAlignment().getTitleTextAlignment(), textView);
        setFont(textView, TextKey.TITLE_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getTitleTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getTitleTextCharSpacing());
    }

    public void customiseStatementHelperTextView(TextView textView) {
        HVLogUtils.d(TAG, "customiseStatementHelperTextView() called with: textView = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getStatementHelperTextColor());
        setTextSize(getConfig().getFontSize().getStatementHelperTextSize(), textView);
        setGravity(getConfig().getAlignment().getStatementHelperTextAlignment(), textView);
        setFont(textView, TextKey.STATEMENT_HELPER_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getTitleTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getTitleTextCharSpacing());
    }

    public void customiseStatementTextView(TextView textView) {
        HVLogUtils.d(TAG, "customiseStatementTextView() called with: textView = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getStatementTextColor());
        setTextSize(getConfig().getFontSize().getStatementTextSize(), textView);
        setGravity(getConfig().getAlignment().getStatementTextAlignment(), textView);
        setFont(textView, TextKey.STATEMENT_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getStatementHelperTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getStatementHelperTextCharSpacing());
    }

    public void customiseLoaderTextView(TextView textView) {
        HVLogUtils.d(TAG, "customiseLoaderTextView() called with: textView = [" + textView + "]");
        setTextColor(textView, getConfig().getColors().getLoaderTextColor());
        setTextSize(getConfig().getFontSize().getLoaderTextSize(), textView);
        setFont(textView, TextKey.LOADER_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getLoaderTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getLoaderTextCharSpacing());
    }

    public void customiseNfcProgressTextView(Paint paint) {
        setFont(paint);
    }

    public void customiseNfcStatusTextView(TextView textView, String str) {
        String nfcStatusTextColor;
        float f;
        HVLogUtils.d(TAG, "customiseNfcStatusTextView() called with: textView = [" + textView + "], status = [" + str + "]");
        if (str.equalsIgnoreCase("failure")) {
            nfcStatusTextColor = getConfig().getColors().getNfcStatusErrorTextColor();
        } else {
            nfcStatusTextColor = getConfig().getColors().getNfcStatusTextColor();
        }
        setTextColor(textView, nfcStatusTextColor);
        String lowerCase = str.toLowerCase();
        lowerCase.hashCode();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case -1867169789:
                if (lowerCase.equals("success")) {
                    c = 0;
                    break;
                }
                break;
            case 270940796:
                if (lowerCase.equals("disabled")) {
                    c = 1;
                    break;
                }
                break;
            case 422194963:
                if (lowerCase.equals(HyperKycData.APIResult.STATE_PROCESSING)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                f = 0.6f;
                break;
            case 1:
                f = 0.2f;
                break;
            case 2:
                f = 0.8f;
                break;
            default:
                f = 1.0f;
                break;
        }
        setTextSize(getConfig().getFontSize().getNfcStatusTextSize(), textView);
        setGravity(getConfig().getAlignment().getNfcStatusTextAlignment(), textView);
        setFont(textView, TextKey.NFC_STATUS_TEXT_KEY);
        setLineHeight(textView, getConfig().getLineHeight().getNfcStatusTextLineHeight());
        setCharacterSpacing(textView, getConfig().getCharSpacing().getNfcStatusTextCharSpacing());
        textView.setAlpha(f);
    }

    public void setFont(TextView textView, TextKey textKey) {
        HVLogUtils.d(TAG, "setFont() called with: textView = [" + textView + "], key = [" + textKey + "]");
        textView.setTypeface(this.typefaceMap.get(textKey));
    }

    public void setFont(Paint paint) {
        paint.setTypeface(this.typefaceMap.get(TextKey.NFC_PROGRESS_TEXT_KEY));
    }

    public Typeface getFont(String str, String str2) {
        Typeface fontFromAssets;
        HVLogUtils.d(TAG, "getFont() called with: fontFamily = [" + str + "], fontWeight = [" + str2 + "]");
        for (String str3 : this.fontFilePaths) {
            if (str3.contains(str) && str3.contains(str2) && (fontFromAssets = getFontFromAssets(str3)) != null) {
                return fontFromAssets;
            }
        }
        return getFontFromResources(str, str2);
    }

    public Typeface getFontFromAssets(String str) {
        String str2 = TAG;
        HVLogUtils.d(str2, "getFontFromAssets() called with: fontPath = [" + str + "]");
        if (getAppContext() == null) {
            Log.d(str2, "getFontFromAssets: context is null");
            return null;
        }
        try {
            return Typeface.createFromAsset(getAppContext().getAssets(), str);
        } catch (RuntimeException e) {
            String str3 = TAG;
            HVLogUtils.e(str3, "getFontFromAssets() with: fontPath = [" + str + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.d(str3, Utils.getErrorMessage(e));
            return null;
        }
    }

    @Deprecated
    public String getDocumentInstructionLottie() {
        HVLogUtils.d(TAG, "getDocumentInstructionLottie() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getDocumentInstructionLottie() : "";
    }

    public String getDocInstructionFrontSideAnimation() {
        HVLogUtils.d(TAG, "getDocInstructionFrontSideAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getDocumentInstructionFrontSideLottie() : "";
    }

    public String getDocumentInstructionBackSideAnimation() {
        HVLogUtils.d(TAG, "getDocumentInstructionBackSideAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getDocumentInstructionBackSideLottie() : "";
    }

    public String getDocProcessingAnimation() {
        HVLogUtils.d(TAG, "getDocProcessingAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getDocumentProcessingLottie() : "";
    }

    public String getDocumentSuccessAnimation() {
        HVLogUtils.d(TAG, "getDocumentSuccessAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getDocumentSuccessLottie() : "";
    }

    public String getDocumentFailureAnimation() {
        HVLogUtils.d(TAG, "getDocumentFailureAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getDocumentFailureLottie() : "";
    }

    public String getFaceInstructionAnimation() {
        HVLogUtils.d(TAG, "getFaceInstructionAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getFaceInstructionLottie() : "";
    }

    public String getFaceProcessingAnimation() {
        HVLogUtils.d(TAG, "getFaceProcessingAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getFaceProcessingLottie() : "";
    }

    public String getFaceSuccessAnimation() {
        HVLogUtils.d(TAG, "getFaceSuccessAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getFaceSuccessLottie() : "";
    }

    public String getFaceFailureAnimation() {
        HVLogUtils.d(TAG, "getFaceFailureAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getFaceFailureLottie() : "";
    }

    public String getEndStateProcessingAnimation() {
        HVLogUtils.d(TAG, "getEndStateProcessingAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getEndStateProcessing() : "";
    }

    public String getEndStateSuccessAnimation() {
        HVLogUtils.d(TAG, "getEndStateSuccessAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getEndStateSuccess() : "";
    }

    public String getEndStateFailureAnimation() {
        HVLogUtils.d(TAG, "getEndStateFailureAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getEndStateFailure() : "";
    }

    public String getUploadFailureAnimation() {
        HVLogUtils.d(TAG, "getUploadFailureAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getUploadFailure() : "";
    }

    public String getQrInstructionAnimation() {
        HVLogUtils.d(TAG, "getQrInstructionAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getQrInstruction() : "";
    }

    public String getLoaderAnimation() {
        HVLogUtils.d(TAG, "getLoaderAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getLoaderLottie() : "";
    }

    public String getNfcAnimation() {
        HVLogUtils.d(TAG, "getNfcAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getNfcLottie() : "";
    }

    public String getNfcInstructionAnimation() {
        HVLogUtils.d(TAG, "getNfcInstructionAnimation() called");
        return getConfig().getAnimation() != null ? getConfig().getAnimation().getNfcInstructionAndroid() : "";
    }

    public void setBorderColor(String str, TextView textView) {
        HVLogUtils.d(TAG, "setBorderColor() called with: borderColor = [" + str + "], textView = [" + textView + "]");
        if (isValidHexColor(str)) {
            int parseColor = Color.parseColor(str);
            Drawable background = textView.getBackground();
            if (background instanceof GradientDrawable) {
                ((GradientDrawable) background).setStroke(UIUtils.dpToPx(textView.getContext(), 1.0f), parseColor);
            }
        }
    }

    public void setPrimaryButtonIcon(Button button, String str, boolean z) {
        String str2 = TAG;
        HVLogUtils.d(str2, "setPrimaryButtonIcon() called with: button = [" + button + "], url = [" + str + "], shouldShow = [" + z + "]");
        if (getAppContext() == null) {
            Log.d(str2, "setPrimaryButtonIcon: context is null");
            return;
        }
        if (button instanceof MaterialButton) {
            final MaterialButton materialButton = (MaterialButton) button;
            if (z) {
                if (!StringUtils.isEmptyOrNull(str)) {
                    this.isDefaultIconsUsed = false;
                    PicassoManager.getInstance(getAppContext()).getBitmap(str, new PicassoManager.ImageDownloaderCallback() { // from class: co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil.1
                        @Override // co.hyperverge.hypersnapsdk.utils.PicassoManager.ImageDownloaderCallback
                        public void onSuccess(Bitmap bitmap) {
                            HVLogUtils.d(HyperSnapUIConfigUtil.TAG, "setPrimaryButtonIcon() ImageDownloaderCallback onSuccess() called with: bitmap = [" + bitmap + "]");
                            if (HyperSnapUIConfigUtil.this.getAppContext() == null) {
                                Log.d(HyperSnapUIConfigUtil.TAG, "PicassoManager.ImageDownloaderCallback#onSuccess: context is null");
                                return;
                            }
                            materialButton.setIcon(new BitmapDrawable(HyperSnapUIConfigUtil.this.getAppContext().getResources(), HyperSnapUIConfigUtil.this.scaleBitmap(bitmap)));
                            materialButton.setIconGravity(4);
                            materialButton.setIconTint(null);
                        }

                        @Override // co.hyperverge.hypersnapsdk.utils.PicassoManager.ImageDownloaderCallback
                        public void onError(String str3) {
                            HVLogUtils.d(HyperSnapUIConfigUtil.TAG, "setPrimaryButtonIcon() ImageDownloaderCallback onError() called with: errorMessage = [" + str3 + "]");
                            Log.d(HyperSnapUIConfigUtil.TAG, str3);
                        }
                    });
                    return;
                } else {
                    HVLogUtils.d(str2, "setPrimaryButtonIcon: showing default icon");
                    this.isDefaultIconsUsed = true;
                    materialButton.setIcon(AppCompatResources.getDrawable(getAppContext(), R.drawable.hv_ic_baseline_arrow_forward_18));
                    materialButton.setIconGravity(4);
                    return;
                }
            }
            HVLogUtils.d(str2, "setPrimaryButtonIcon: icons are not shown");
            materialButton.setIcon(null);
            materialButton.setIconTint(null);
        }
    }

    public void setTextBackgroundColor(String str, TextView textView) {
        HVLogUtils.d(TAG, "setTextBackgroundColor() called with: color = [" + str + "], textView = [" + textView + "]");
        if (isValidHexColor(str)) {
            int parseColor = Color.parseColor(str);
            if (Build.VERSION.SDK_INT >= 21) {
                textView.setBackgroundTintList(ColorStateList.valueOf(parseColor));
                return;
            }
            Drawable background = textView.getBackground();
            if (background instanceof ShapeDrawable) {
                ((ShapeDrawable) background).getPaint().setColor(parseColor);
            } else if (background instanceof GradientDrawable) {
                ((GradientDrawable) background).setColor(parseColor);
            } else if (background instanceof ColorDrawable) {
                ((ColorDrawable) background).setColor(parseColor);
            }
        }
    }

    public void setButtonBackgroundColor(String str, Button button) {
        HVLogUtils.d(TAG, "setButtonBackgroundColor() called with: color = [" + str + "], button = [" + button + "]");
        if (isValidHexColor(str)) {
            int parseColor = Color.parseColor(str);
            if ((button instanceof MaterialButton) && Build.VERSION.SDK_INT >= 21) {
                button.setBackgroundTintList(ColorStateList.valueOf(parseColor));
                return;
            }
            Drawable background = button.getBackground();
            if (background instanceof ShapeDrawable) {
                ((ShapeDrawable) background).getPaint().setColor(parseColor);
            } else if (background instanceof GradientDrawable) {
                ((GradientDrawable) background).setColor(parseColor);
            } else if (background instanceof ColorDrawable) {
                ((ColorDrawable) background).setColor(parseColor);
            }
        }
    }

    public boolean isValidHexColor(String str) {
        HVLogUtils.d(TAG, "isValidHexColor() called with: hexColor = [" + str + "]");
        if (StringUtils.isEmptyOrNull(str)) {
            return false;
        }
        return Pattern.compile("^#(?:(?:[\\da-fA-F]{6})|(?:[\\da-fA-F]{8}))$").matcher(str).matches();
    }

    public boolean isValidFontSize(float f) {
        HVLogUtils.d(TAG, "isValidFontSize() called with: fontSize = [" + f + "]");
        return f >= 6.0f && f <= 40.0f;
    }

    public void setGravity(String str, AutoCompleteTextView autoCompleteTextView) {
        int i;
        HVLogUtils.d(TAG, "setGravity() called with: gravity = [" + str + "], textView = [" + autoCompleteTextView + "]");
        if (str.equalsIgnoreCase(TtmlNode.CENTER)) {
            i = 17;
        } else {
            i = str.equalsIgnoreCase(TtmlNode.RIGHT) ? 5 : 3;
        }
        autoCompleteTextView.setGravity(i);
    }

    public void setGravity(String str, TextView textView) {
        int i;
        HVLogUtils.d(TAG, "setGravity() called with: gravity = [" + str + "], textView = [" + textView + "]");
        if (str.equalsIgnoreCase(TtmlNode.CENTER)) {
            i = 17;
        } else {
            i = str.equalsIgnoreCase(TtmlNode.RIGHT) ? 5 : 3;
        }
        textView.setGravity(i);
    }

    public void setLinearLayoutGravity(String str, LinearLayout linearLayout) {
        int i;
        HVLogUtils.d(TAG, "setLinearLayoutGravity() called with: gravity = [" + str + "], linearLayout = [" + linearLayout + "]");
        if (str.equalsIgnoreCase(TtmlNode.CENTER)) {
            i = 17;
        } else {
            i = str.equalsIgnoreCase(TtmlNode.RIGHT) ? 5 : 3;
        }
        linearLayout.setGravity(i);
    }

    public void setGravity(String str, CheckBox checkBox) {
        int i;
        HVLogUtils.d(TAG, "setGravity() called with: gravity = [" + str + "], checkBox = [" + checkBox + "]");
        if (str.equalsIgnoreCase(TtmlNode.CENTER)) {
            i = 17;
        } else {
            i = str.equalsIgnoreCase(TtmlNode.RIGHT) ? 5 : 3;
        }
        checkBox.setGravity(i);
    }

    public void setGravity(String str, RadioButton radioButton) {
        int i;
        HVLogUtils.d(TAG, "setGravity() called with: gravity = [" + str + "], radioButton = [" + radioButton + "]");
        if (str.equalsIgnoreCase(TtmlNode.CENTER)) {
            i = 17;
        } else {
            i = str.equalsIgnoreCase(TtmlNode.RIGHT) ? 5 : 3;
        }
        radioButton.setGravity(i);
    }

    public void setGravity(String str, Chip chip) {
        int i;
        HVLogUtils.d(TAG, "setGravity() called with: gravity = [" + str + "], chip = [" + chip + "]");
        if (str.equalsIgnoreCase(TtmlNode.CENTER)) {
            i = 17;
        } else {
            i = str.equalsIgnoreCase(TtmlNode.RIGHT) ? 5 : 3;
        }
        chip.setGravity(i);
    }

    public void setGravity(String str, EditText editText) {
        int i;
        HVLogUtils.d(TAG, "setGravity() called with: gravity = [" + str + "], editText = [" + editText + "]");
        if (str.equalsIgnoreCase(TtmlNode.CENTER)) {
            i = 17;
        } else {
            i = str.equalsIgnoreCase(TtmlNode.RIGHT) ? 5 : 3;
        }
        editText.setGravity(i);
    }

    public void setTextSize(float f, Button button) {
        HVLogUtils.d(TAG, "setTextSize() called with: size = [" + f + "], button = [" + button + "]");
        if (isValidFontSize(f)) {
            button.setTextSize(f);
        }
    }

    public void setTextSize(float f, TextView textView) {
        HVLogUtils.d(TAG, "setTextSize() called with: size = [" + f + "], textView = [" + textView + "]");
        if (isValidFontSize(f)) {
            textView.setTextSize(f);
        }
    }

    public void setTextSize(float f, CheckBox checkBox) {
        HVLogUtils.d(TAG, "setTextSize() called with: size = [" + f + "], checkBox = [" + checkBox + "]");
        if (isValidFontSize(f)) {
            checkBox.setTextSize(f);
        }
    }

    public void setTextSize(float f, RadioButton radioButton) {
        HVLogUtils.d(TAG, "setTextSize() called with: size = [" + f + "], radioButton = [" + radioButton + "]");
        if (isValidFontSize(f)) {
            radioButton.setTextSize(f);
        }
    }

    public void setTextSize(float f, Chip chip) {
        HVLogUtils.d(TAG, "setTextSize() called with: size = [" + f + "], chip = [" + chip + "]");
        if (isValidFontSize(f)) {
            chip.setTextSize(f);
        }
    }

    public void setTextSize(float f, TextInputLayout textInputLayout) {
        HVLogUtils.d(TAG, "setTextSize() called with: size = [" + f + "], inputLayout = [" + textInputLayout + "]");
        if (isValidFontSize(f)) {
            textInputLayout.getSuffixTextView().setTextSize(f);
            textInputLayout.getPrefixTextView().setTextSize(f);
        }
    }

    public void setBorderRadius(float f, Button button) {
        HVLogUtils.d(TAG, "setBorderRadius() called with: radius = [" + f + "], button = [" + button + "]");
        if (f > 0.0f) {
            Drawable background = button.getBackground();
            if (button instanceof MaterialButton) {
                ((MaterialButton) button).setCornerRadius((int) f);
            } else if (background instanceof GradientDrawable) {
                ((GradientDrawable) background).setCornerRadius(f);
            }
        }
    }

    public void setBorderRadius(float f, TextInputLayout textInputLayout) {
        HVLogUtils.d(TAG, "setBorderRadius() called with: radius = [" + f + "], textInputLayout = [" + textInputLayout + "]");
        if (f > 0.0f) {
            textInputLayout.setBoxCornerRadii(f, f, f, f);
        }
    }

    public void setTextColor(CheckBox checkBox, String str) {
        HVLogUtils.d(TAG, "setTextColor() called with: checkBox = [" + checkBox + "], color = [" + str + "]");
        if (isValidHexColor(str)) {
            checkBox.setTextColor(Color.parseColor(str));
        }
    }

    public void setTextColor(RadioButton radioButton, String str) {
        HVLogUtils.d(TAG, "setTextColor() called with: radioButton = [" + radioButton + "], color = [" + str + "]");
        if (isValidHexColor(str)) {
            radioButton.setTextColor(Color.parseColor(str));
        }
    }

    public void setHintColor(TextInputLayout textInputLayout, String str) {
        HVLogUtils.d(TAG, "setHintColor() called with: textInputLayout = [" + textInputLayout + "], color = [" + str + "]");
        if (isValidHexColor(str)) {
            textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(Color.parseColor(str)));
        }
    }

    public void setBoxStrokeColorStateList(TextInputLayout textInputLayout, int[][] iArr, String str, int i) {
        HVLogUtils.d(TAG, "setBoxStrokeColorStateList() called with: textInputLayout = [" + textInputLayout + "], states = [" + iArr + "], color = [" + str + "], colorOnSurface = [" + i + "]");
        if (isValidHexColor(str)) {
            int parseColor = Color.parseColor(str);
            textInputLayout.setBoxStrokeColorStateList(new ColorStateList(iArr, new int[]{ColorUtils.setAlphaComponent(parseColor, 25), i, parseColor}));
        }
    }

    public void setEditTextColor(EditText editText, String str) {
        HVLogUtils.d(TAG, "setEditTextColor() called with: editText = [" + editText + "], color = [" + str + "]");
        if (isValidHexColor(str)) {
            editText.setTextColor(ColorStateList.valueOf(Color.parseColor(str)));
        }
    }

    public void setEditTextHintColor(EditText editText, String str) {
        HVLogUtils.d(TAG, "setEditTextHintColor() called with: editText = [" + editText + "], color = [" + str + "]");
        if (isValidHexColor(str)) {
            editText.setHintTextColor(ColorStateList.valueOf(Color.parseColor(str)));
        }
    }

    public void setTextColor(TextView textView, String str) {
        HVLogUtils.d(TAG, "setTextColor() called with: textView = [" + textView + "], color = [" + str + "]");
        if (isValidHexColor(str)) {
            textView.setTextColor(Color.parseColor(str));
        }
    }

    public void setTextColor(Button button, String str, boolean z) {
        HVLogUtils.d(TAG, "setTextColor() called with: button = [" + button + "], textColor = [" + str + "], shouldShowButtonIcon = [" + z + "]");
        if (isValidHexColor(str)) {
            button.setTextColor(ColorStateList.valueOf(Color.parseColor(str)));
            if (this.isDefaultIconsUsed && z && (button instanceof MaterialButton)) {
                ((MaterialButton) button).setIconTint(ColorStateList.valueOf(Color.parseColor(str)));
            }
        }
    }

    public void setBorderColor(String str, Button button) {
        HVLogUtils.d(TAG, "setBorderColor() called with: borderColor = [" + str + "], button = [" + button + "]");
        if (isValidHexColor(str)) {
            Drawable background = button.getBackground();
            ColorStateList valueOf = ColorStateList.valueOf(Color.parseColor(str));
            int dpToPx = UIUtils.dpToPx(button.getContext(), 1.0f);
            if (button instanceof MaterialButton) {
                MaterialButton materialButton = (MaterialButton) button;
                materialButton.setStrokeColor(valueOf);
                materialButton.setStrokeWidth(dpToPx);
            } else {
                if (!(background instanceof GradientDrawable) || Build.VERSION.SDK_INT < 21) {
                    return;
                }
                ((GradientDrawable) background).setStroke(dpToPx, valueOf);
            }
        }
    }

    public void setBorderColor(String str, CheckBox checkBox) {
        HVLogUtils.d(TAG, "setBorderColor() called with: borderColor = [" + str + "], checkBox = [" + checkBox + "]");
        if (!isValidHexColor(str) || Build.VERSION.SDK_INT < 21) {
            return;
        }
        checkBox.setButtonTintList(ColorStateList.valueOf(Color.parseColor(str)));
    }

    public void setBorderColor(String str, TextInputLayout textInputLayout) {
        HVLogUtils.d(TAG, "setBorderColor() called with: borderColor = [" + str + "], textInputLayout = [" + textInputLayout + "]");
        if (isValidHexColor(str)) {
            textInputLayout.setBoxStrokeColorStateList(ColorStateList.valueOf(Color.parseColor(str)));
        }
    }

    public void setFont(AutoCompleteTextView autoCompleteTextView, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: autoCompleteTextView = [" + autoCompleteTextView + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            autoCompleteTextView.setTypeface(getFont(str, str2));
        }
    }

    public void setFont(TextInputLayout textInputLayout, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: textInputLayout = [" + textInputLayout + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            textInputLayout.getSuffixTextView().setTypeface(getFont(str, str2));
            textInputLayout.getPrefixTextView().setTypeface(getFont(str, str2));
            textInputLayout.setTypeface(getFont(str, str2));
        }
    }

    public void setFont(CheckBox checkBox, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: checkBox = [" + checkBox + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            checkBox.setTypeface(getFont(str, str2));
        }
    }

    public void setFont(RadioButton radioButton, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: radioButton = [" + radioButton + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            radioButton.setTypeface(getFont(str, str2));
        }
    }

    public void setFont(Chip chip, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: chip = [" + chip + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            chip.setTypeface(getFont(str, str2));
        }
    }

    public void setFont(TextView textView, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: textView = [" + textView + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            textView.setTypeface(getFont(str, str2));
        }
    }

    public void setFont(EditText editText, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: editText = [" + editText + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            editText.setTypeface(getFont(str, str2));
        }
    }

    public void setFont(Button button, String str, String str2) {
        HVLogUtils.d(TAG, "setFont() called with: button = [" + button + "], font = [" + str + "], fontWeight = [" + str2 + "]");
        if (isNonEmptyFontFamilyInput(str, str2)) {
            button.setTypeface(getFont(str, str2));
        }
    }

    public boolean isNonEmptyFontFamilyInput(String str, String str2) {
        HVLogUtils.d(TAG, "isNonEmptyFontFamilyInput() called with: font = [" + str + "], fontWeight = [" + str2 + "]");
        return (StringUtils.isEmptyOrNull(str) || StringUtils.isEmptyOrNull(str2)) ? false : true;
    }

    public void setBackgroundColor(TextView textView, String str) {
        HVLogUtils.d(TAG, "setBackgroundColor() called with: textView = [" + textView + "], color = [" + str + "]");
        if (isValidHexColor(str)) {
            int parseColor = Color.parseColor(str);
            Drawable background = textView.getBackground();
            if (background instanceof ShapeDrawable) {
                ((ShapeDrawable) background).getPaint().setColor(parseColor);
            } else if (background instanceof GradientDrawable) {
                ((GradientDrawable) background).setColor(parseColor);
            } else if (background instanceof ColorDrawable) {
                ((ColorDrawable) background).setColor(parseColor);
            }
        }
    }

    public void customiseClientLogo(ImageView imageView) {
        customiseClientLogo(imageView, false);
    }

    public void customiseClientLogo(ImageView imageView, boolean z) {
        String str = TAG;
        HVLogUtils.d(str, "customiseClientLogo() called with: imageView = [" + imageView + "]");
        if (getAppContext() == null) {
            Log.d(str, "customiseClientLogo: context is null");
            return;
        }
        String captureScreenClientLogo = z ? getConfig().getLogos().getCaptureScreenClientLogo() : getConfig().getLogos().getClientLogo();
        if (StringUtils.isEmptyOrNull(captureScreenClientLogo)) {
            captureScreenClientLogo = getConfig().getLogos().getClientLogo();
        }
        if (StringUtils.isEmptyOrNull(captureScreenClientLogo)) {
            return;
        }
        PicassoManager.getInstance(getAppContext()).loadInto(captureScreenClientLogo, null, null, imageView);
    }

    public void customiseBackButtonIcon(ImageView imageView) {
        customiseBackButtonIcon(imageView, false);
    }

    public void customiseBackButtonIcon(ImageView imageView, boolean z) {
        String str = TAG;
        HVLogUtils.d(str, "customiseBackButtonIcon() called with: imageView = [" + imageView + "]");
        if (getAppContext() == null) {
            HVLogUtils.d(str, "customiseBackButtonIcon: context is null");
        }
        String url = (z ? getConfig().getIcons().getCaptureScreenBackButtonIcon() : getConfig().getIcons().getBackButtonIcon()).getUrl();
        if (StringUtils.isEmptyOrNull(url)) {
            url = getConfig().getIcons().getBackButtonIcon().getUrl();
        }
        if (StringUtils.isEmptyOrNull(url)) {
            return;
        }
        PicassoManager.getInstance(getAppContext()).loadInto(url, AppCompatResources.getDrawable(getAppContext(), R.drawable.ic_back_button_32), AppCompatResources.getDrawable(getAppContext(), R.drawable.ic_back_button_32), imageView);
    }

    private void prefetchImage(String str) {
        String str2 = TAG;
        HVLogUtils.d(str2, "prefetchImage() called with: httpUrl = [" + str + "]");
        if (getAppContext() == null) {
            Log.d(str2, "prefetchImage: context is null");
        } else {
            PicassoManager.getInstance(getAppContext()).prefetchImage(str);
        }
    }

    private void preloadIcons() {
        HVLogUtils.d(TAG, "preloadIcons() called");
        UIIcons.UIIconProperties primaryButtonIcon = getConfig().getIcons().getPrimaryButtonIcon();
        String url = primaryButtonIcon.getUrl();
        if (!StringUtils.isEmptyOrNull(url) && primaryButtonIcon.isShouldShow()) {
            prefetchImage(url);
        }
        String url2 = getConfig().getIcons().getBackButtonIcon().getUrl();
        if (!StringUtils.isEmptyOrNull(url2)) {
            prefetchImage(url2);
        }
        String url3 = getConfig().getIcons().getCaptureScreenBackButtonIcon().getUrl();
        if (StringUtils.isEmptyOrNull(url3)) {
            return;
        }
        prefetchImage(url3);
    }

    private void preloadLogos() {
        HVLogUtils.d(TAG, "preloadLogos() called");
        UILogos logos = getConfig().getLogos();
        if (!StringUtils.isEmptyOrNull(logos.getClientLogo())) {
            prefetchImage(logos.getClientLogo());
        }
        if (StringUtils.isEmptyOrNull(logos.getCaptureScreenClientLogo())) {
            return;
        }
        prefetchImage(logos.getCaptureScreenClientLogo());
    }

    private void preloadBackgroundImage() {
        HVLogUtils.d(TAG, "preloadBackgroundImage() called");
        if (StringUtils.isEmptyOrNull(getConfig().getBackgroundImage())) {
            return;
        }
        prefetchImage(getConfig().getBackgroundImage());
    }

    public void customiseBackgroundImage(final View view) {
        String str = TAG;
        HVLogUtils.d(str, "customiseBackgroundImage() called with: view = [" + view + "]");
        if (getAppContext() == null) {
            Log.d(str, "customiseBackgroundImage: context is null");
            return;
        }
        String backgroundImage = getConfig().getBackgroundImage();
        if (StringUtils.isEmptyOrNull(backgroundImage)) {
            return;
        }
        PicassoManager.getInstance(getAppContext()).getBitmap(backgroundImage, new PicassoManager.ImageDownloaderCallback() { // from class: co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil.2
            @Override // co.hyperverge.hypersnapsdk.utils.PicassoManager.ImageDownloaderCallback
            public void onError(String str2) {
            }

            @Override // co.hyperverge.hypersnapsdk.utils.PicassoManager.ImageDownloaderCallback
            public void onSuccess(Bitmap bitmap) {
                view.setBackground(new BitmapDrawable(HyperSnapUIConfigUtil.this.getAppContext().getResources(), bitmap));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap scaleBitmap(Bitmap bitmap) {
        HVLogUtils.d(TAG, "scaleBitmap() called with: bitmap = [" + bitmap + "]");
        float primaryButtonTextSize = getConfig().getFontSize().getPrimaryButtonTextSize();
        float width = ((float) bitmap.getWidth()) * (primaryButtonTextSize / ((float) bitmap.getHeight()));
        Matrix matrix = new Matrix();
        matrix.postScale(width / ((float) bitmap.getWidth()), primaryButtonTextSize / ((float) bitmap.getHeight()));
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void createTypefaceMap() {
        String titleTextWeight;
        HVLogUtils.d(TAG, "createTypefaceMap() called");
        UIFont font = getConfig().getFont();
        UIFontWeight fontWeight = getConfig().getFontWeight();
        for (TextKey textKey : TextKey.values()) {
            String str = "";
            switch (AnonymousClass3.$SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[textKey.ordinal()]) {
                case 1:
                    str = font.getTitleTextFont();
                    titleTextWeight = fontWeight.getTitleTextWeight();
                    break;
                case 2:
                    str = font.getDescriptionTextFont();
                    titleTextWeight = fontWeight.getDescriptionTextWeight();
                    break;
                case 3:
                    str = font.getStatusTextFont();
                    titleTextWeight = fontWeight.getStatusTextWeight();
                    break;
                case 4:
                    str = font.getDocumentSideHintTextFont();
                    titleTextWeight = fontWeight.getDocumentSideHintTextWeight();
                    break;
                case 5:
                    str = font.getRetakeMessageFont();
                    titleTextWeight = fontWeight.getRetakeMessageWeight();
                    break;
                case 6:
                    str = font.getPrimaryButtonTextFont();
                    titleTextWeight = fontWeight.getPrimaryButtonTextWeight();
                    break;
                case 7:
                    str = font.getSecondaryButtonTextFont();
                    titleTextWeight = fontWeight.getSecondaryButtonTextWeight();
                    break;
                case 8:
                    str = font.getAlertTextBoxTextFont();
                    titleTextWeight = fontWeight.getAlertTextBoxTextWeight();
                    break;
                case 9:
                    str = font.getPickerTextFont();
                    titleTextWeight = fontWeight.getPickerTextWeight();
                    break;
                case 10:
                    str = font.getCountryListItemTextFont();
                    titleTextWeight = fontWeight.getCountryListItemTextFontWeight();
                    break;
                case 11:
                    str = font.getCountryListItemSelectedTextFont();
                    titleTextWeight = fontWeight.getCountryListItemSelectedTextFontWeight();
                    break;
                case 12:
                    str = font.getCountrySearchTextFont();
                    titleTextWeight = fontWeight.getCountrySearchTextFontWeight();
                    break;
                case 13:
                    str = font.getStatementHelperTextFont();
                    titleTextWeight = fontWeight.getStatementHelperTextWeight();
                    break;
                case 14:
                    str = font.getStatementTextFont();
                    titleTextWeight = fontWeight.getStatementTextWeight();
                    break;
                case 15:
                    str = font.getLoaderTextFont();
                    titleTextWeight = fontWeight.getLoaderTextWeight();
                    break;
                case 16:
                case 17:
                    str = font.getNfcStatusTextFont();
                    titleTextWeight = fontWeight.getNfcStatusTextWeight();
                    break;
                case 18:
                    str = font.getApiLoadingTitleTextFont();
                    titleTextWeight = fontWeight.getApiLoadingTitleTextWeight();
                    break;
                case 19:
                    str = font.getApiLoadingHintTextFont();
                    titleTextWeight = fontWeight.getApiLoadingHintTextWeight();
                    break;
                default:
                    titleTextWeight = "";
                    break;
            }
            Typeface font2 = getFont(str, titleTextWeight);
            if (font2 == null) {
                logFontError(textKey.getKeyValue(), str, titleTextWeight);
                font2 = getDefaultTypeface(this.defaultFontMap.get(textKey));
            }
            this.typefaceMap.put(textKey, font2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey;

        static {
            int[] iArr = new int[TextKey.values().length];
            $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey = iArr;
            try {
                iArr[TextKey.TITLE_TEXT_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.DESCRIPTION_TEXT_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.STATUS_TEXT_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.DOCUMENT_SIDE_HINT_TEXT_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.RETAKE_MESSAGE_TEXT_KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.PRIMARY_BUTTON_TEXT_KEY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.SECONDARY_BUTTON_TEXT_KEY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.ALERT_TEXT_BOX_TEXT_KEY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.PICKER_BUTTON_TEXT_KEY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.COUNTRY_LIST_ITEM_TEXT_KEY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.COUNTRY_LIST_ITEM_SELECTED_TEXT_KEY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.COUNTRY_SEARCH_HINT_TEXT_KEY.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.STATEMENT_HELPER_TEXT_KEY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.STATEMENT_TEXT_KEY.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.LOADER_TEXT_KEY.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.NFC_STATUS_TEXT_KEY.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.NFC_PROGRESS_TEXT_KEY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.API_LOADING_TITLE_TEXT_KEY.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$utils$HyperSnapUIConfigUtil$TextKey[TextKey.API_LOADING_HINT_TEXT_KEY.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    private void logFontError(String str, String str2, String str3) {
        String str4 = "Could not find fonts with FontFamily : " + str2 + " and FontWeight : " + str3 + " for " + str;
        HVLogUtils.d(TAG, str4);
        Log.e("Fonts", str4);
    }

    private void createFontPaths(String str) {
        String str2 = TAG;
        HVLogUtils.d(str2, "createFontPaths() called with: path = [" + str + "]");
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.d(str2, "createFontPaths: context is null");
            return;
        }
        try {
            String[] list = appContext.getAssets().list(str);
            if (list == null || list.length <= 0) {
                return;
            }
            for (String str3 : list) {
                if (!str3.contains(".")) {
                    if (str.equals("")) {
                        createFontPaths(str3);
                    } else {
                        createFontPaths(str + RemoteSettings.FORWARD_SLASH_STRING + str3);
                    }
                } else {
                    String substring = str3.substring(str3.lastIndexOf("."));
                    if (substring.equalsIgnoreCase(".ttf") || substring.equalsIgnoreCase(".otf")) {
                        if (str.isEmpty()) {
                            this.fontFilePaths.add(str3);
                        } else {
                            this.fontFilePaths.add(str + RemoteSettings.FORWARD_SLASH_STRING + str3);
                        }
                    }
                }
            }
        } catch (IOException e) {
            String str4 = TAG;
            HVLogUtils.e(str4, "createFontPaths() with: path = [" + str + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str4, Utils.getErrorMessage(e));
        }
    }

    private Typeface getFontFromResources(String str, String str2) {
        String str3 = TAG;
        HVLogUtils.d(str3, "getFontFromResources() called with: fontFamily = [" + str + "], fontWeight = [" + str2 + "]");
        if (getAppContext() == null) {
            Log.d(str3, "getFontFromResources: context is null");
            return null;
        }
        try {
            return ResourcesCompat.getFont(getAppContext(), getFontResourceIdentifier(str.toLowerCase() + "_" + str2.toLowerCase(), getAppContext()));
        } catch (Resources.NotFoundException e) {
            String str4 = TAG;
            HVLogUtils.e(str4, "getFontFromResources(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str4, Utils.getErrorMessage(e));
            return null;
        }
    }

    private Typeface getDefaultTypeface(String str) {
        String str2 = TAG;
        HVLogUtils.d(str2, "getDefaultTypeface() called with: fontResourceName = [" + str + "]");
        if (getAppContext() == null) {
            Log.d(str2, "getFontResourceIdentifier: context is null");
        }
        try {
            return ResourcesCompat.getFont(getAppContext(), getFontResourceIdentifier(str, getAppContext()));
        } catch (Resources.NotFoundException e) {
            String str3 = TAG;
            HVLogUtils.e(str3, "getDefaultTypeface(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str3, Utils.getErrorMessage(e));
            return null;
        }
    }

    private int getFontResourceIdentifier(String str, Context context) {
        String str2 = TAG;
        HVLogUtils.d(str2, "getFontResourceIdentifier() called with: resourceName = [" + str + "], context = [" + context + "]");
        if (getAppContext() == null) {
            Log.d(str2, "getFontResourceIdentifier: context is null");
        }
        return context.getResources().getIdentifier(str, "font", context.getPackageName());
    }

    public void setLineHeight(TextView textView, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: textView = [" + textView + "], lineHeight = [" + f + "]");
        if (f > 0.0f) {
            if (Build.VERSION.SDK_INT >= 28) {
                textView.setLineHeight((int) f);
                return;
            } else {
                textView.setLineSpacing(f, 1.0f);
                return;
            }
        }
        HVLogUtils.d(str, "Setting default textView line height = [" + textView.getLineHeight() + "]");
    }

    public void setCharacterSpacing(TextView textView, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCharacterSpacing() called with: textView = [" + textView + "], characterSpacing = [" + f + "]");
        if (Build.VERSION.SDK_INT >= 21) {
            if (f > 0.0f) {
                textView.setLetterSpacing(f);
                return;
            }
            HVLogUtils.d(str, "Setting default character spacing = [" + textView.getLetterSpacing() + "]");
        }
    }

    public void setLineHeight(TextInputLayout textInputLayout, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: textInputLayout = [" + textInputLayout + "], lineHeight = [" + f + "]");
        if (textInputLayout.getEditText() != null) {
            setLineHeight(textInputLayout.getEditText(), f);
        } else {
            HVLogUtils.d(str, "TextInputLayout EditText is null");
        }
    }

    public void setCharacterSpacing(TextInputLayout textInputLayout, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCharacterSpacing() called with: textInputLayout = [" + textInputLayout + "], characterSpacing = [" + f + "]");
        if (textInputLayout.getEditText() != null) {
            setCharacterSpacing(textInputLayout.getEditText(), f);
        } else {
            HVLogUtils.d(str, "TextInputLayout EditText is null");
        }
    }

    public void setLineHeight(EditText editText, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: editText = [" + editText + "], lineHeight = [" + f + "]");
        if (f > 0.0f) {
            if (Build.VERSION.SDK_INT >= 28) {
                editText.setLineHeight((int) f);
                return;
            } else {
                editText.setLineSpacing(f, 1.0f);
                return;
            }
        }
        HVLogUtils.d(str, "Setting default textView line height = [" + editText.getLineHeight() + "]");
    }

    public void setCharacterSpacing(EditText editText, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCharacterSpacing() called with: editText = [" + editText + "], characterSpacing = [" + f + "]");
        if (Build.VERSION.SDK_INT >= 21) {
            if (f > 0.0f) {
                editText.setLetterSpacing(f);
                return;
            }
            HVLogUtils.d(str, "Setting default character spacing = [" + editText.getLetterSpacing() + "]");
        }
    }

    public void setLineHeight(CheckBox checkBox, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: checkBox = [" + checkBox + "], lineHeight = [" + f + "]");
        if (f > 0.0f) {
            if (Build.VERSION.SDK_INT >= 28) {
                checkBox.setLineHeight((int) f);
                return;
            } else {
                checkBox.setLineSpacing(f, 1.0f);
                return;
            }
        }
        HVLogUtils.d(str, "Setting default textView line height = [" + checkBox.getLineHeight() + "]");
    }

    public void setCharacterSpacing(CheckBox checkBox, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCharacterSpacing() called with: checkBox = [" + checkBox + "], characterSpacing = [" + f + "]");
        if (Build.VERSION.SDK_INT >= 21) {
            if (f > 0.0f) {
                checkBox.setLetterSpacing(f);
                return;
            }
            HVLogUtils.d(str, "Setting default character spacing = [" + checkBox.getLetterSpacing() + "]");
        }
    }

    public void setLineHeight(RadioButton radioButton, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: radioButton = [" + radioButton + "], lineHeight = [" + f + "]");
        if (f > 0.0f) {
            if (Build.VERSION.SDK_INT >= 28) {
                radioButton.setLineHeight((int) f);
                return;
            } else {
                radioButton.setLineSpacing(f, 1.0f);
                return;
            }
        }
        HVLogUtils.d(str, "Setting default textView line height = [" + radioButton.getLineHeight() + "]");
    }

    public void setCharacterSpacing(RadioButton radioButton, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCharacterSpacing() called with: radioButton = [" + radioButton + "], characterSpacing = [" + f + "]");
        if (Build.VERSION.SDK_INT >= 21) {
            if (f > 0.0f) {
                radioButton.setLetterSpacing(f);
                return;
            }
            HVLogUtils.d(str, "Setting default character spacing = [" + radioButton.getLetterSpacing() + "]");
        }
    }

    public void setLineHeight(Chip chip, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: chip = [" + chip + "], lineHeight = [" + f + "]");
        if (f > 0.0f) {
            if (Build.VERSION.SDK_INT >= 28) {
                chip.setLineHeight((int) f);
                return;
            } else {
                chip.setLineSpacing(f, 1.0f);
                return;
            }
        }
        HVLogUtils.d(str, "Setting default textView line height = [" + chip.getLineHeight() + "]");
    }

    public void setCharacterSpacing(Chip chip, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCharacterSpacing() called with: chip = [" + chip + "], characterSpacing = [" + f + "]");
        if (Build.VERSION.SDK_INT >= 21) {
            if (f > 0.0f) {
                chip.setLetterSpacing(f);
                return;
            }
            HVLogUtils.d(str, "Setting default character spacing = [" + chip.getLetterSpacing() + "]");
        }
    }

    public void setLineHeight(AutoCompleteTextView autoCompleteTextView, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: autoCompleteTextView = [" + autoCompleteTextView + "], lineHeight = [" + f + "]");
        if (f > 0.0f) {
            if (Build.VERSION.SDK_INT >= 28) {
                autoCompleteTextView.setLineHeight((int) f);
                return;
            } else {
                autoCompleteTextView.setLineSpacing(f, 1.0f);
                return;
            }
        }
        HVLogUtils.d(str, "Setting default textView line height = [" + autoCompleteTextView.getLineHeight() + "]");
    }

    public void setCharacterSpacing(AutoCompleteTextView autoCompleteTextView, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCheckBoxCharacterSpacing() called with: checkBox = [" + autoCompleteTextView + "], characterSpacing = [" + f + "]");
        if (Build.VERSION.SDK_INT >= 21) {
            if (f > 0.0f) {
                autoCompleteTextView.setLetterSpacing(f);
                return;
            }
            HVLogUtils.d(str, "Setting default character spacing = [" + autoCompleteTextView.getLetterSpacing() + "]");
        }
    }

    public void setCharacterSpacing(Button button, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setCharacterSpacing() called with: button = [" + button + "], characterSpacing = [" + f + "]");
        if (Build.VERSION.SDK_INT >= 21) {
            if (f > 0.0f) {
                button.setLetterSpacing(f);
                return;
            }
            HVLogUtils.d(str, "Setting default character spacing = [" + button.getLetterSpacing() + "]");
        }
    }

    public void setLineHeight(Button button, float f) {
        String str = TAG;
        HVLogUtils.d(str, "setLineHeight() called with: button = [" + button + "], lineHeight = [" + f + "]");
        if (f > 0.0f) {
            if (Build.VERSION.SDK_INT >= 28) {
                button.setLineHeight((int) f);
                return;
            } else {
                button.setLineSpacing(f, 1.0f);
                return;
            }
        }
        HVLogUtils.d(str, "Setting default button line height = [" + button.getLineHeight() + "]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum TextKey {
        TITLE_TEXT_KEY("titleText"),
        DESCRIPTION_TEXT_KEY("descriptionText"),
        STATUS_TEXT_KEY("statusText"),
        DOCUMENT_SIDE_HINT_TEXT_KEY("documentSideHintText"),
        RETAKE_MESSAGE_TEXT_KEY("retakeMessageText"),
        PRIMARY_BUTTON_TEXT_KEY("primaryButtonText"),
        SECONDARY_BUTTON_TEXT_KEY("secondaryButtonText"),
        ALERT_TEXT_BOX_TEXT_KEY("alertTextBoxText"),
        PICKER_BUTTON_TEXT_KEY("pickerButtonText"),
        COUNTRY_LIST_ITEM_TEXT_KEY("countryListItemText"),
        COUNTRY_LIST_ITEM_SELECTED_TEXT_KEY("countryListItemSelectedText"),
        COUNTRY_SEARCH_HINT_TEXT_KEY("countrySearchHintText"),
        STATEMENT_HELPER_TEXT_KEY("statementHelperText"),
        STATEMENT_TEXT_KEY("statementText"),
        LOADER_TEXT_KEY("loaderText"),
        NFC_STATUS_TEXT_KEY("nfcStatusText"),
        NFC_PROGRESS_TEXT_KEY("nfcProgressText"),
        API_LOADING_TITLE_TEXT_KEY("apiLoadingTitleText"),
        API_LOADING_HINT_TEXT_KEY("apiLoadingHintText");

        private final String keyValue;

        TextKey(String str) {
            this.keyValue = str;
        }

        String getKeyValue() {
            return this.keyValue;
        }
    }
}
