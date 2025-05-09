package org.apache.pdfbox.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.apache.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequence;
import org.apache.pdfbox.contentstream.operator.markedcontent.BeginMarkedContentSequenceWithProperties;
import org.apache.pdfbox.contentstream.operator.markedcontent.EndMarkedContentSequence;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;

/* loaded from: classes5.dex */
public class PDFMarkedContentExtractor extends PDFTextStreamEngine {
    private Map<String, List<TextPosition>> characterListMapping;
    private Stack<PDMarkedContent> currentMarkedContents;
    private List<PDMarkedContent> markedContents;
    private boolean suppressDuplicateOverlappingText;

    private boolean within(float f, float f2, float f3) {
        return f2 > f - f3 && f2 < f + f3;
    }

    @Override // org.apache.pdfbox.text.PDFTextStreamEngine, org.apache.pdfbox.contentstream.PDFStreamEngine
    public /* bridge */ /* synthetic */ void processPage(PDPage pDPage) throws IOException {
        super.processPage(pDPage);
    }

    public PDFMarkedContentExtractor() throws IOException {
        this(null);
    }

    public PDFMarkedContentExtractor(String str) throws IOException {
        this.suppressDuplicateOverlappingText = true;
        this.markedContents = new ArrayList();
        this.currentMarkedContents = new Stack<>();
        this.characterListMapping = new HashMap();
        addOperator(new BeginMarkedContentSequenceWithProperties());
        addOperator(new BeginMarkedContentSequence());
        addOperator(new EndMarkedContentSequence());
    }

    public void beginMarkedContentSequence(COSName cOSName, COSDictionary cOSDictionary) {
        PDMarkedContent create = PDMarkedContent.create(cOSName, cOSDictionary);
        if (this.currentMarkedContents.isEmpty()) {
            this.markedContents.add(create);
        } else {
            PDMarkedContent peek = this.currentMarkedContents.peek();
            if (peek != null) {
                peek.addMarkedContent(create);
            }
        }
        this.currentMarkedContents.push(create);
    }

    public void endMarkedContentSequence() {
        if (this.currentMarkedContents.isEmpty()) {
            return;
        }
        this.currentMarkedContents.pop();
    }

    public void xobject(PDXObject pDXObject) {
        if (this.currentMarkedContents.isEmpty()) {
            return;
        }
        this.currentMarkedContents.peek().addXObject(pDXObject);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    @Override // org.apache.pdfbox.text.PDFTextStreamEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void processTextPosition(TextPosition textPosition) {
        boolean z;
        boolean z2 = false;
        if (this.suppressDuplicateOverlappingText) {
            String unicode = textPosition.getUnicode();
            float x = textPosition.getX();
            float y = textPosition.getY();
            List<TextPosition> list = this.characterListMapping.get(unicode);
            if (list == null) {
                list = new ArrayList<>();
                this.characterListMapping.put(unicode, list);
            }
            float width = (textPosition.getWidth() / unicode.length()) / 3.0f;
            Iterator<TextPosition> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                TextPosition next = it.next();
                String unicode2 = next.getUnicode();
                float x2 = next.getX();
                float y2 = next.getY();
                if (unicode2 != null && within(x2, x, width) && within(y2, y, width)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                list.add(textPosition);
            }
            if (z2) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (arrayList.isEmpty()) {
                arrayList.add(textPosition);
            } else {
                TextPosition textPosition2 = (TextPosition) arrayList.get(arrayList.size() - 1);
                if (textPosition.isDiacritic() && textPosition2.contains(textPosition)) {
                    textPosition2.mergeDiacritic(textPosition);
                } else if (textPosition2.isDiacritic() && textPosition.contains(textPosition2)) {
                    textPosition.mergeDiacritic(textPosition2);
                    arrayList.remove(arrayList.size() - 1);
                    arrayList.add(textPosition);
                } else {
                    arrayList.add(textPosition);
                }
            }
            if (this.currentMarkedContents.isEmpty()) {
                return;
            }
            this.currentMarkedContents.peek().addText(textPosition);
            return;
        }
        z2 = true;
        if (z2) {
        }
    }

    public List<PDMarkedContent> getMarkedContents() {
        return this.markedContents;
    }
}
