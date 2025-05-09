package org.apache.pdfbox.text;

import android.graphics.RectF;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.pdfbox.pdmodel.PDPage;

/* loaded from: classes5.dex */
public class PDFTextStripperByArea extends PDFTextStripper {
    private final List<String> regions = new ArrayList();
    private final Map<String, RectF> regionArea = new HashMap();
    private final Map<String, Vector<List<TextPosition>>> regionCharacterList = new HashMap();
    private final Map<String, StringWriter> regionText = new HashMap();

    public void addRegion(String str, RectF rectF) {
        this.regions.add(str);
        this.regionArea.put(str, rectF);
    }

    public List<String> getRegions() {
        return this.regions;
    }

    public String getTextForRegion(String str) {
        return this.regionText.get(str).toString();
    }

    public void extractRegions(PDPage pDPage) throws IOException {
        for (String str : this.regions) {
            setStartPage(getCurrentPageNo());
            setEndPage(getCurrentPageNo());
            Vector<List<TextPosition>> vector = new Vector<>();
            vector.add(new ArrayList());
            this.regionCharacterList.put(str, vector);
            this.regionText.put(str, new StringWriter());
        }
        if (pDPage.getStream() != null) {
            processPage(pDPage);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.text.PDFTextStripper, org.apache.pdfbox.text.PDFTextStreamEngine
    public void processTextPosition(TextPosition textPosition) {
        for (String str : this.regionArea.keySet()) {
            if (this.regionArea.get(str).contains(textPosition.getX(), textPosition.getY())) {
                this.charactersByArticle = this.regionCharacterList.get(str);
                super.processTextPosition(textPosition);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.text.PDFTextStripper
    public void writePage() throws IOException {
        for (String str : this.regionArea.keySet()) {
            this.charactersByArticle = this.regionCharacterList.get(str);
            this.output = this.regionText.get(str);
            super.writePage();
        }
    }
}
