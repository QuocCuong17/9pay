package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.interactive.form.PlainText;

/* loaded from: classes5.dex */
class PlainTextFormatter {
    private AppearanceStyle appearanceStyle;
    private final AppearancePrimitivesComposer composer;
    private float horizontalOffset;
    private final TextAlign textAlignment;
    private final PlainText textContent;
    private float verticalOffset;
    private final float width;
    private final boolean wrapLines;

    /* synthetic */ PlainTextFormatter(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum TextAlign {
        LEFT(0),
        CENTER(1),
        RIGHT(2),
        JUSTIFY(4);

        private final int alignment;

        TextAlign(int i) {
            this.alignment = i;
        }

        int getTextAlign() {
            return this.alignment;
        }

        public static TextAlign valueOf(int i) {
            for (TextAlign textAlign : values()) {
                if (textAlign.getTextAlign() == i) {
                    return textAlign;
                }
            }
            return LEFT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Builder {
        private AppearanceStyle appearanceStyle;
        private AppearancePrimitivesComposer composer;
        private PlainText textContent;
        private boolean wrapLines = false;
        private float width = 0.0f;
        private TextAlign textAlignment = TextAlign.LEFT;
        private float horizontalOffset = 0.0f;
        private float verticalOffset = 0.0f;

        public Builder(AppearancePrimitivesComposer appearancePrimitivesComposer) {
            this.composer = appearancePrimitivesComposer;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder style(AppearanceStyle appearanceStyle) {
            this.appearanceStyle = appearanceStyle;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder wrapLines(boolean z) {
            this.wrapLines = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder width(float f) {
            this.width = f;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder textAlign(int i) {
            this.textAlignment = TextAlign.valueOf(i);
            return this;
        }

        Builder textAlign(TextAlign textAlign) {
            this.textAlignment = textAlign;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder text(PlainText plainText) {
            this.textContent = plainText;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder initialOffset(float f, float f2) {
            this.horizontalOffset = f;
            this.verticalOffset = f2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PlainTextFormatter build() {
            return new PlainTextFormatter(this, null);
        }
    }

    private PlainTextFormatter(Builder builder) {
        this.appearanceStyle = builder.appearanceStyle;
        this.wrapLines = builder.wrapLines;
        this.width = builder.width;
        this.composer = builder.composer;
        this.textContent = builder.textContent;
        this.textAlignment = builder.textAlignment;
        this.horizontalOffset = builder.horizontalOffset;
        this.verticalOffset = builder.verticalOffset;
    }

    public void format() throws IOException {
        PlainText plainText = this.textContent;
        if (plainText == null || plainText.getParagraphs().isEmpty()) {
            return;
        }
        for (PlainText.Paragraph paragraph : this.textContent.getParagraphs()) {
            if (this.wrapLines) {
                processLines(paragraph.getLines(this.appearanceStyle.getFont(), this.appearanceStyle.getFontSize(), this.width));
            } else {
                this.composer.showText(paragraph.getText(), this.appearanceStyle.getFont());
            }
        }
    }

    private void processLines(List<PlainText.Line> list) throws IOException {
        PDFont font = this.appearanceStyle.getFont();
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (PlainText.Line line : list) {
            int i = AnonymousClass1.$SwitchMap$org$apache$pdfbox$pdmodel$interactive$form$PlainTextFormatter$TextAlign[this.textAlignment.ordinal()];
            if (i == 1) {
                f2 = (this.width - line.getWidth()) / 2.0f;
            } else if (i == 2) {
                f2 = this.width - line.getWidth();
            } else if (i != 3) {
                f2 = 0.0f;
            } else if (list.indexOf(line) != list.size() - 1) {
                f3 = line.getInterWordSpacing(this.width);
            }
            float f4 = (-f) + f2 + this.horizontalOffset;
            if (list.indexOf(line) == 0) {
                this.composer.newLineAtOffset(f4, this.verticalOffset);
                this.verticalOffset = 0.0f;
                this.horizontalOffset = 0.0f;
            } else {
                this.verticalOffset -= this.appearanceStyle.getLeading();
                this.composer.newLineAtOffset(f4, -this.appearanceStyle.getLeading());
            }
            List<PlainText.Word> words = line.getWords();
            float f5 = f2;
            for (PlainText.Word word : words) {
                this.composer.showText(word.getText(), font);
                float floatValue = ((Float) word.getAttributes().getIterator().getAttribute(PlainText.TextAttribute.WIDTH)).floatValue();
                if (words.indexOf(word) != words.size() - 1) {
                    this.composer.newLineAtOffset(floatValue + f3, 0.0f);
                    f5 = f5 + floatValue + f3;
                }
            }
            f = f5;
        }
        this.horizontalOffset -= f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.pdfbox.pdmodel.interactive.form.PlainTextFormatter$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$pdfbox$pdmodel$interactive$form$PlainTextFormatter$TextAlign;

        static {
            int[] iArr = new int[TextAlign.values().length];
            $SwitchMap$org$apache$pdfbox$pdmodel$interactive$form$PlainTextFormatter$TextAlign = iArr;
            try {
                iArr[TextAlign.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$pdfbox$pdmodel$interactive$form$PlainTextFormatter$TextAlign[TextAlign.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$pdfbox$pdmodel$interactive$form$PlainTextFormatter$TextAlign[TextAlign.JUSTIFY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
