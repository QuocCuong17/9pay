package org.apache.pdfbox.pdmodel.interactive.form;

import org.apache.pdfbox.pdmodel.font.PDFont;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class AppearanceStyle {
    private PDFont font;
    private float fontSize = 12.0f;
    private float leading = 14.4f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFont getFont() {
        return this.font;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFont(PDFont pDFont) {
        this.font = pDFont;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFontSize() {
        return this.fontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFontSize(float f) {
        this.fontSize = f;
        this.leading = f * 1.2f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getLeading() {
        return this.leading;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLeading(float f) {
        this.leading = f;
    }
}
