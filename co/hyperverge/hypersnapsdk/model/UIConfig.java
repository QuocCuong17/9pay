package co.hyperverge.hypersnapsdk.model;

import android.util.DisplayMetrics;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UIConfig implements Serializable {
    private String backgroundImage;
    private UIColors colors = new UIColors();
    private UIFont font = new UIFont();
    private UIFontWeight fontWeight = new UIFontWeight();
    private UIFontSize fontSize = new UIFontSize();
    private UIAlignment alignment = new UIAlignment();
    private UIBorderRadius borderRadius = new UIBorderRadius();
    private UIAnimation animation = new UIAnimation();
    private UILogos logos = new UILogos();
    private UIIcons icons = new UIIcons();
    private UILineHeight lineHeight = new UILineHeight();
    private UICharacterSpacing charSpacing = new UICharacterSpacing();

    protected boolean canEqual(Object obj) {
        return obj instanceof UIConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UIConfig)) {
            return false;
        }
        UIConfig uIConfig = (UIConfig) obj;
        if (!uIConfig.canEqual(this)) {
            return false;
        }
        UIColors colors = getColors();
        UIColors colors2 = uIConfig.getColors();
        if (colors != null ? !colors.equals(colors2) : colors2 != null) {
            return false;
        }
        UIFont font = getFont();
        UIFont font2 = uIConfig.getFont();
        if (font != null ? !font.equals(font2) : font2 != null) {
            return false;
        }
        UIFontWeight fontWeight = getFontWeight();
        UIFontWeight fontWeight2 = uIConfig.getFontWeight();
        if (fontWeight != null ? !fontWeight.equals(fontWeight2) : fontWeight2 != null) {
            return false;
        }
        UIFontSize fontSize = getFontSize();
        UIFontSize fontSize2 = uIConfig.getFontSize();
        if (fontSize != null ? !fontSize.equals(fontSize2) : fontSize2 != null) {
            return false;
        }
        UIAlignment alignment = getAlignment();
        UIAlignment alignment2 = uIConfig.getAlignment();
        if (alignment != null ? !alignment.equals(alignment2) : alignment2 != null) {
            return false;
        }
        UIBorderRadius borderRadius = getBorderRadius();
        UIBorderRadius borderRadius2 = uIConfig.getBorderRadius();
        if (borderRadius != null ? !borderRadius.equals(borderRadius2) : borderRadius2 != null) {
            return false;
        }
        UIAnimation animation = getAnimation();
        UIAnimation animation2 = uIConfig.getAnimation();
        if (animation != null ? !animation.equals(animation2) : animation2 != null) {
            return false;
        }
        UILogos logos = getLogos();
        UILogos logos2 = uIConfig.getLogos();
        if (logos != null ? !logos.equals(logos2) : logos2 != null) {
            return false;
        }
        UIIcons icons = getIcons();
        UIIcons icons2 = uIConfig.getIcons();
        if (icons != null ? !icons.equals(icons2) : icons2 != null) {
            return false;
        }
        UILineHeight lineHeight = getLineHeight();
        UILineHeight lineHeight2 = uIConfig.getLineHeight();
        if (lineHeight != null ? !lineHeight.equals(lineHeight2) : lineHeight2 != null) {
            return false;
        }
        UICharacterSpacing charSpacing = getCharSpacing();
        UICharacterSpacing charSpacing2 = uIConfig.getCharSpacing();
        if (charSpacing != null ? !charSpacing.equals(charSpacing2) : charSpacing2 != null) {
            return false;
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = uIConfig.getBackgroundImage();
        return backgroundImage != null ? backgroundImage.equals(backgroundImage2) : backgroundImage2 == null;
    }

    public int hashCode() {
        UIColors colors = getColors();
        int hashCode = colors == null ? 43 : colors.hashCode();
        UIFont font = getFont();
        int hashCode2 = ((hashCode + 59) * 59) + (font == null ? 43 : font.hashCode());
        UIFontWeight fontWeight = getFontWeight();
        int hashCode3 = (hashCode2 * 59) + (fontWeight == null ? 43 : fontWeight.hashCode());
        UIFontSize fontSize = getFontSize();
        int hashCode4 = (hashCode3 * 59) + (fontSize == null ? 43 : fontSize.hashCode());
        UIAlignment alignment = getAlignment();
        int hashCode5 = (hashCode4 * 59) + (alignment == null ? 43 : alignment.hashCode());
        UIBorderRadius borderRadius = getBorderRadius();
        int hashCode6 = (hashCode5 * 59) + (borderRadius == null ? 43 : borderRadius.hashCode());
        UIAnimation animation = getAnimation();
        int hashCode7 = (hashCode6 * 59) + (animation == null ? 43 : animation.hashCode());
        UILogos logos = getLogos();
        int hashCode8 = (hashCode7 * 59) + (logos == null ? 43 : logos.hashCode());
        UIIcons icons = getIcons();
        int hashCode9 = (hashCode8 * 59) + (icons == null ? 43 : icons.hashCode());
        UILineHeight lineHeight = getLineHeight();
        int hashCode10 = (hashCode9 * 59) + (lineHeight == null ? 43 : lineHeight.hashCode());
        UICharacterSpacing charSpacing = getCharSpacing();
        int hashCode11 = (hashCode10 * 59) + (charSpacing == null ? 43 : charSpacing.hashCode());
        String backgroundImage = getBackgroundImage();
        return (hashCode11 * 59) + (backgroundImage != null ? backgroundImage.hashCode() : 43);
    }

    public void setAlignment(UIAlignment uIAlignment) {
        this.alignment = uIAlignment;
    }

    public void setAnimation(UIAnimation uIAnimation) {
        this.animation = uIAnimation;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setBorderRadius(UIBorderRadius uIBorderRadius) {
        this.borderRadius = uIBorderRadius;
    }

    public void setCharSpacing(UICharacterSpacing uICharacterSpacing) {
        this.charSpacing = uICharacterSpacing;
    }

    public void setColors(UIColors uIColors) {
        this.colors = uIColors;
    }

    public void setFont(UIFont uIFont) {
        this.font = uIFont;
    }

    public void setFontSize(UIFontSize uIFontSize) {
        this.fontSize = uIFontSize;
    }

    public void setFontWeight(UIFontWeight uIFontWeight) {
        this.fontWeight = uIFontWeight;
    }

    public void setIcons(UIIcons uIIcons) {
        this.icons = uIIcons;
    }

    public void setLineHeight(UILineHeight uILineHeight) {
        this.lineHeight = uILineHeight;
    }

    public void setLogos(UILogos uILogos) {
        this.logos = uILogos;
    }

    public String toString() {
        return "UIConfig(colors=" + getColors() + ", font=" + getFont() + ", fontWeight=" + getFontWeight() + ", fontSize=" + getFontSize() + ", alignment=" + getAlignment() + ", borderRadius=" + getBorderRadius() + ", animation=" + getAnimation() + ", logos=" + getLogos() + ", icons=" + getIcons() + ", lineHeight=" + getLineHeight() + ", charSpacing=" + getCharSpacing() + ", backgroundImage=" + getBackgroundImage() + ")";
    }

    public UIColors getColors() {
        return this.colors;
    }

    public UIFont getFont() {
        return this.font;
    }

    public UIFontWeight getFontWeight() {
        return this.fontWeight;
    }

    public UIFontSize getFontSize() {
        return this.fontSize;
    }

    public UIAlignment getAlignment() {
        return this.alignment;
    }

    public UIBorderRadius getBorderRadius() {
        return this.borderRadius;
    }

    public UIAnimation getAnimation() {
        return this.animation;
    }

    public UILogos getLogos() {
        return this.logos;
    }

    public UIIcons getIcons() {
        return this.icons;
    }

    public UILineHeight getLineHeight() {
        return this.lineHeight;
    }

    public UICharacterSpacing getCharSpacing() {
        return this.charSpacing;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public void init(DisplayMetrics displayMetrics) {
        HVLogUtils.d("UIConfig", "init() called with: metrics = [" + displayMetrics + "]");
        getColors().convertColors();
        getBorderRadius().applyDimension(displayMetrics);
        getCharSpacing().applyDimension(displayMetrics);
        getLineHeight().applyDimension(displayMetrics);
    }
}
