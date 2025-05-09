package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDFont;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class PlainText {
    private static final float FONTSCALE = 1000.0f;
    private final List<Paragraph> paragraphs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlainText(String str) {
        List asList = Arrays.asList(str.split("\\n"));
        this.paragraphs = new ArrayList();
        Iterator it = asList.iterator();
        while (it.hasNext()) {
            this.paragraphs.add(new Paragraph((String) it.next()));
        }
    }

    PlainText(List<String> list) {
        this.paragraphs = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.paragraphs.add(new Paragraph(it.next()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class TextAttribute extends AttributedCharacterIterator.Attribute {
        public static final AttributedCharacterIterator.Attribute WIDTH = new TextAttribute("width");
        private static final long serialVersionUID = -3138885145941283005L;

        protected TextAttribute(String str) {
            super(str);
        }
    }

    /* loaded from: classes5.dex */
    static class Paragraph {
        private String textContent;

        Paragraph(String str) {
            this.textContent = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getText() {
            return this.textContent;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public List<Line> getLines(PDFont pDFont, float f, float f2) throws IOException {
            BreakIterator lineInstance = BreakIterator.getLineInstance();
            lineInstance.setText(this.textContent);
            float f3 = f / PlainText.FONTSCALE;
            int first = lineInstance.first();
            int next = lineInstance.next();
            ArrayList arrayList = new ArrayList();
            Line line = new Line();
            float f4 = 0.0f;
            while (true) {
                int i = next;
                int i2 = first;
                first = i;
                if (first != -1) {
                    String substring = this.textContent.substring(i2, first);
                    float stringWidth = pDFont.getStringWidth(substring) * f3;
                    f4 += stringWidth;
                    if (f4 >= f2 && Character.isWhitespace(substring.charAt(substring.length() - 1))) {
                        f4 -= pDFont.getStringWidth(substring.substring(substring.length() - 1)) * f3;
                    }
                    if (f4 >= f2) {
                        line.setWidth(line.calculateWidth(pDFont, f));
                        arrayList.add(line);
                        line = new Line();
                        f4 = pDFont.getStringWidth(substring) * f3;
                    }
                    AttributedString attributedString = new AttributedString(substring);
                    attributedString.addAttribute(TextAttribute.WIDTH, Float.valueOf(stringWidth));
                    Word word = new Word(substring);
                    word.setAttributes(attributedString);
                    line.addWord(word);
                    next = lineInstance.next();
                } else {
                    arrayList.add(line);
                    return arrayList;
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    static class Line {
        private float lineWidth;
        private List<Word> words = new ArrayList();

        Line() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public float getWidth() {
            return this.lineWidth;
        }

        void setWidth(float f) {
            this.lineWidth = f;
        }

        float calculateWidth(PDFont pDFont, float f) throws IOException {
            float f2 = f / PlainText.FONTSCALE;
            float f3 = 0.0f;
            for (Word word : this.words) {
                f3 += ((Float) word.getAttributes().getIterator().getAttribute(TextAttribute.WIDTH)).floatValue();
                String text = word.getText();
                if (this.words.indexOf(word) == this.words.size() - 1 && Character.isWhitespace(text.charAt(text.length() - 1))) {
                    f3 -= pDFont.getStringWidth(text.substring(text.length() - 1)) * f2;
                }
            }
            return f3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public List<Word> getWords() {
            return this.words;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public float getInterWordSpacing(float f) {
            return (f - this.lineWidth) / (this.words.size() - 1);
        }

        void addWord(Word word) {
            this.words.add(word);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Word {
        private AttributedString attributedString;
        private String textContent;

        Word(String str) {
            this.textContent = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getText() {
            return this.textContent;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public AttributedString getAttributes() {
            return this.attributedString;
        }

        void setAttributes(AttributedString attributedString) {
            this.attributedString = attributedString;
        }
    }
}
