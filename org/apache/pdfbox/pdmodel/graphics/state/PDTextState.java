package org.apache.pdfbox.pdmodel.graphics.state;

import org.apache.pdfbox.pdmodel.font.PDFont;

/* loaded from: classes5.dex */
public class PDTextState implements Cloneable {
    private PDFont font;
    private float fontSize;
    private float characterSpacing = 0.0f;
    private float wordSpacing = 0.0f;
    private float horizontalScaling = 100.0f;
    private float leading = 0.0f;
    private RenderingMode renderingMode = RenderingMode.FILL;
    private float rise = 0.0f;
    private boolean knockout = true;

    public float getCharacterSpacing() {
        return this.characterSpacing;
    }

    public void setCharacterSpacing(float f) {
        this.characterSpacing = f;
    }

    public float getWordSpacing() {
        return this.wordSpacing;
    }

    public void setWordSpacing(float f) {
        this.wordSpacing = f;
    }

    public float getHorizontalScaling() {
        return this.horizontalScaling;
    }

    public void setHorizontalScaling(float f) {
        this.horizontalScaling = f;
    }

    public float getLeading() {
        return this.leading;
    }

    public void setLeading(float f) {
        this.leading = f;
    }

    public PDFont getFont() {
        return this.font;
    }

    public void setFont(PDFont pDFont) {
        this.font = pDFont;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(float f) {
        this.fontSize = f;
    }

    public RenderingMode getRenderingMode() {
        return this.renderingMode;
    }

    public void setRenderingMode(RenderingMode renderingMode) {
        this.renderingMode = renderingMode;
    }

    public float getRise() {
        return this.rise;
    }

    public void setRise(float f) {
        this.rise = f;
    }

    public boolean getKnockoutFlag() {
        return this.knockout;
    }

    public void setKnockoutFlag(boolean z) {
        this.knockout = z;
    }

    public PDTextState clone() {
        try {
            return (PDTextState) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
