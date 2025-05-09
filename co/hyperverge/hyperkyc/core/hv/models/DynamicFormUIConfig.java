package co.hyperverge.hyperkyc.core.hv.models;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DynamicFormUIConfig.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010$\n\u0002\b?\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u008f\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\"\u0010\u0010\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J%\u0010<\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011\u0018\u00010\u0011HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÃ\u0002\u0010O\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032$\b\u0002\u0010\u0010\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010P\u001a\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010S\u001a\u00020THÖ\u0001J\t\u0010U\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR-\u0010\u0010\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001eR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001eR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001eR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001eR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001eR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001eR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001e¨\u0006V"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/DynamicFormUIConfig;", "", "font", "", TtmlNode.ATTR_TTS_FONT_SIZE, TtmlNode.ATTR_TTS_FONT_WEIGHT, "color", "selectedTextColor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "borderRadius", "borderColor", "selectedBorderColor", "disabledBorderColor", "selectedBackgroundColor", "circleBorderColor", "alignment", "icons", "", "marginTop", "marginBottom", "marginLeft", "marginRight", "paddingTop", "paddingBottom", "paddingLeft", "paddingRight", "lineHeight", "charSpacing", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAlignment", "()Ljava/lang/String;", "getBackgroundColor", "getBorderColor", "getBorderRadius", "getCharSpacing", "getCircleBorderColor", "getColor", "getDisabledBorderColor", "getFont", "getFontSize", "getFontWeight", "getIcons", "()Ljava/util/Map;", "getLineHeight", "getMarginBottom", "getMarginLeft", "getMarginRight", "getMarginTop", "getPaddingBottom", "getPaddingLeft", "getPaddingRight", "getPaddingTop", "getSelectedBackgroundColor", "getSelectedBorderColor", "getSelectedTextColor", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DynamicFormUIConfig {
    private final String alignment;
    private final String backgroundColor;
    private final String borderColor;
    private final String borderRadius;
    private final String charSpacing;
    private final String circleBorderColor;
    private final String color;
    private final String disabledBorderColor;
    private final String font;
    private final String fontSize;
    private final String fontWeight;
    private final Map<String, Map<String, Object>> icons;
    private final String lineHeight;
    private final String marginBottom;
    private final String marginLeft;
    private final String marginRight;
    private final String marginTop;
    private final String paddingBottom;
    private final String paddingLeft;
    private final String paddingRight;
    private final String paddingTop;
    private final String selectedBackgroundColor;
    private final String selectedBorderColor;
    private final String selectedTextColor;

    /* renamed from: component1, reason: from getter */
    public final String getFont() {
        return this.font;
    }

    /* renamed from: component10, reason: from getter */
    public final String getDisabledBorderColor() {
        return this.disabledBorderColor;
    }

    /* renamed from: component11, reason: from getter */
    public final String getSelectedBackgroundColor() {
        return this.selectedBackgroundColor;
    }

    /* renamed from: component12, reason: from getter */
    public final String getCircleBorderColor() {
        return this.circleBorderColor;
    }

    /* renamed from: component13, reason: from getter */
    public final String getAlignment() {
        return this.alignment;
    }

    public final Map<String, Map<String, Object>> component14() {
        return this.icons;
    }

    /* renamed from: component15, reason: from getter */
    public final String getMarginTop() {
        return this.marginTop;
    }

    /* renamed from: component16, reason: from getter */
    public final String getMarginBottom() {
        return this.marginBottom;
    }

    /* renamed from: component17, reason: from getter */
    public final String getMarginLeft() {
        return this.marginLeft;
    }

    /* renamed from: component18, reason: from getter */
    public final String getMarginRight() {
        return this.marginRight;
    }

    /* renamed from: component19, reason: from getter */
    public final String getPaddingTop() {
        return this.paddingTop;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFontSize() {
        return this.fontSize;
    }

    /* renamed from: component20, reason: from getter */
    public final String getPaddingBottom() {
        return this.paddingBottom;
    }

    /* renamed from: component21, reason: from getter */
    public final String getPaddingLeft() {
        return this.paddingLeft;
    }

    /* renamed from: component22, reason: from getter */
    public final String getPaddingRight() {
        return this.paddingRight;
    }

    /* renamed from: component23, reason: from getter */
    public final String getLineHeight() {
        return this.lineHeight;
    }

    /* renamed from: component24, reason: from getter */
    public final String getCharSpacing() {
        return this.charSpacing;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFontWeight() {
        return this.fontWeight;
    }

    /* renamed from: component4, reason: from getter */
    public final String getColor() {
        return this.color;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSelectedTextColor() {
        return this.selectedTextColor;
    }

    /* renamed from: component6, reason: from getter */
    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBorderRadius() {
        return this.borderRadius;
    }

    /* renamed from: component8, reason: from getter */
    public final String getBorderColor() {
        return this.borderColor;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSelectedBorderColor() {
        return this.selectedBorderColor;
    }

    public final DynamicFormUIConfig copy(String font, String fontSize, String fontWeight, String color, String selectedTextColor, String backgroundColor, String borderRadius, String borderColor, String selectedBorderColor, String disabledBorderColor, String selectedBackgroundColor, String circleBorderColor, String alignment, Map<String, ? extends Map<String, ? extends Object>> icons, String marginTop, String marginBottom, String marginLeft, String marginRight, String paddingTop, String paddingBottom, String paddingLeft, String paddingRight, String lineHeight, String charSpacing) {
        return new DynamicFormUIConfig(font, fontSize, fontWeight, color, selectedTextColor, backgroundColor, borderRadius, borderColor, selectedBorderColor, disabledBorderColor, selectedBackgroundColor, circleBorderColor, alignment, icons, marginTop, marginBottom, marginLeft, marginRight, paddingTop, paddingBottom, paddingLeft, paddingRight, lineHeight, charSpacing);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DynamicFormUIConfig)) {
            return false;
        }
        DynamicFormUIConfig dynamicFormUIConfig = (DynamicFormUIConfig) other;
        return Intrinsics.areEqual(this.font, dynamicFormUIConfig.font) && Intrinsics.areEqual(this.fontSize, dynamicFormUIConfig.fontSize) && Intrinsics.areEqual(this.fontWeight, dynamicFormUIConfig.fontWeight) && Intrinsics.areEqual(this.color, dynamicFormUIConfig.color) && Intrinsics.areEqual(this.selectedTextColor, dynamicFormUIConfig.selectedTextColor) && Intrinsics.areEqual(this.backgroundColor, dynamicFormUIConfig.backgroundColor) && Intrinsics.areEqual(this.borderRadius, dynamicFormUIConfig.borderRadius) && Intrinsics.areEqual(this.borderColor, dynamicFormUIConfig.borderColor) && Intrinsics.areEqual(this.selectedBorderColor, dynamicFormUIConfig.selectedBorderColor) && Intrinsics.areEqual(this.disabledBorderColor, dynamicFormUIConfig.disabledBorderColor) && Intrinsics.areEqual(this.selectedBackgroundColor, dynamicFormUIConfig.selectedBackgroundColor) && Intrinsics.areEqual(this.circleBorderColor, dynamicFormUIConfig.circleBorderColor) && Intrinsics.areEqual(this.alignment, dynamicFormUIConfig.alignment) && Intrinsics.areEqual(this.icons, dynamicFormUIConfig.icons) && Intrinsics.areEqual(this.marginTop, dynamicFormUIConfig.marginTop) && Intrinsics.areEqual(this.marginBottom, dynamicFormUIConfig.marginBottom) && Intrinsics.areEqual(this.marginLeft, dynamicFormUIConfig.marginLeft) && Intrinsics.areEqual(this.marginRight, dynamicFormUIConfig.marginRight) && Intrinsics.areEqual(this.paddingTop, dynamicFormUIConfig.paddingTop) && Intrinsics.areEqual(this.paddingBottom, dynamicFormUIConfig.paddingBottom) && Intrinsics.areEqual(this.paddingLeft, dynamicFormUIConfig.paddingLeft) && Intrinsics.areEqual(this.paddingRight, dynamicFormUIConfig.paddingRight) && Intrinsics.areEqual(this.lineHeight, dynamicFormUIConfig.lineHeight) && Intrinsics.areEqual(this.charSpacing, dynamicFormUIConfig.charSpacing);
    }

    public int hashCode() {
        String str = this.font;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.fontSize;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fontWeight;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.color;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.selectedTextColor;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.backgroundColor;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.borderRadius;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.borderColor;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.selectedBorderColor;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.disabledBorderColor;
        int hashCode10 = (hashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.selectedBackgroundColor;
        int hashCode11 = (hashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.circleBorderColor;
        int hashCode12 = (hashCode11 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.alignment;
        int hashCode13 = (hashCode12 + (str13 == null ? 0 : str13.hashCode())) * 31;
        Map<String, Map<String, Object>> map = this.icons;
        int hashCode14 = (hashCode13 + (map == null ? 0 : map.hashCode())) * 31;
        String str14 = this.marginTop;
        int hashCode15 = (hashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.marginBottom;
        int hashCode16 = (hashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.marginLeft;
        int hashCode17 = (hashCode16 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.marginRight;
        int hashCode18 = (hashCode17 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.paddingTop;
        int hashCode19 = (hashCode18 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.paddingBottom;
        int hashCode20 = (hashCode19 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.paddingLeft;
        int hashCode21 = (hashCode20 + (str20 == null ? 0 : str20.hashCode())) * 31;
        String str21 = this.paddingRight;
        int hashCode22 = (hashCode21 + (str21 == null ? 0 : str21.hashCode())) * 31;
        String str22 = this.lineHeight;
        int hashCode23 = (hashCode22 + (str22 == null ? 0 : str22.hashCode())) * 31;
        String str23 = this.charSpacing;
        return hashCode23 + (str23 != null ? str23.hashCode() : 0);
    }

    public String toString() {
        return "DynamicFormUIConfig(font=" + this.font + ", fontSize=" + this.fontSize + ", fontWeight=" + this.fontWeight + ", color=" + this.color + ", selectedTextColor=" + this.selectedTextColor + ", backgroundColor=" + this.backgroundColor + ", borderRadius=" + this.borderRadius + ", borderColor=" + this.borderColor + ", selectedBorderColor=" + this.selectedBorderColor + ", disabledBorderColor=" + this.disabledBorderColor + ", selectedBackgroundColor=" + this.selectedBackgroundColor + ", circleBorderColor=" + this.circleBorderColor + ", alignment=" + this.alignment + ", icons=" + this.icons + ", marginTop=" + this.marginTop + ", marginBottom=" + this.marginBottom + ", marginLeft=" + this.marginLeft + ", marginRight=" + this.marginRight + ", paddingTop=" + this.paddingTop + ", paddingBottom=" + this.paddingBottom + ", paddingLeft=" + this.paddingLeft + ", paddingRight=" + this.paddingRight + ", lineHeight=" + this.lineHeight + ", charSpacing=" + this.charSpacing + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DynamicFormUIConfig(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, Map<String, ? extends Map<String, ? extends Object>> map, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23) {
        this.font = str;
        this.fontSize = str2;
        this.fontWeight = str3;
        this.color = str4;
        this.selectedTextColor = str5;
        this.backgroundColor = str6;
        this.borderRadius = str7;
        this.borderColor = str8;
        this.selectedBorderColor = str9;
        this.disabledBorderColor = str10;
        this.selectedBackgroundColor = str11;
        this.circleBorderColor = str12;
        this.alignment = str13;
        this.icons = map;
        this.marginTop = str14;
        this.marginBottom = str15;
        this.marginLeft = str16;
        this.marginRight = str17;
        this.paddingTop = str18;
        this.paddingBottom = str19;
        this.paddingLeft = str20;
        this.paddingRight = str21;
        this.lineHeight = str22;
        this.charSpacing = str23;
    }

    public final String getFont() {
        return this.font;
    }

    public final String getFontSize() {
        return this.fontSize;
    }

    public final String getFontWeight() {
        return this.fontWeight;
    }

    public final String getColor() {
        return this.color;
    }

    public final String getSelectedTextColor() {
        return this.selectedTextColor;
    }

    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    public final String getBorderRadius() {
        return this.borderRadius;
    }

    public final String getBorderColor() {
        return this.borderColor;
    }

    public final String getSelectedBorderColor() {
        return this.selectedBorderColor;
    }

    public final String getDisabledBorderColor() {
        return this.disabledBorderColor;
    }

    public final String getSelectedBackgroundColor() {
        return this.selectedBackgroundColor;
    }

    public final String getCircleBorderColor() {
        return this.circleBorderColor;
    }

    public final String getAlignment() {
        return this.alignment;
    }

    public final Map<String, Map<String, Object>> getIcons() {
        return this.icons;
    }

    public final String getMarginTop() {
        return this.marginTop;
    }

    public final String getMarginBottom() {
        return this.marginBottom;
    }

    public final String getMarginLeft() {
        return this.marginLeft;
    }

    public final String getMarginRight() {
        return this.marginRight;
    }

    public final String getPaddingTop() {
        return this.paddingTop;
    }

    public final String getPaddingBottom() {
        return this.paddingBottom;
    }

    public final String getPaddingLeft() {
        return this.paddingLeft;
    }

    public final String getPaddingRight() {
        return this.paddingRight;
    }

    public final String getLineHeight() {
        return this.lineHeight;
    }

    public final String getCharSpacing() {
        return this.charSpacing;
    }
}
