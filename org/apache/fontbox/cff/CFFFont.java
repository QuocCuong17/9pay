package org.apache.fontbox.cff;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.fontbox.afm.AFMParser;
import org.apache.fontbox.util.BoundingBox;

/* loaded from: classes5.dex */
public abstract class CFFFont {
    protected CFFCharset charset;
    protected String fontName;
    protected IndexData globalSubrIndex;
    protected final Map<String, Object> topDict = new LinkedHashMap();
    protected final List<byte[]> charStrings = new ArrayList();

    public abstract List<Number> getFontMatrix();

    public String getName() {
        return this.fontName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setName(String str) {
        this.fontName = str;
    }

    public void addValueToTopDict(String str, Object obj) {
        if (obj != null) {
            this.topDict.put(str, obj);
        }
    }

    public Map<String, Object> getTopDict() {
        return this.topDict;
    }

    public BoundingBox getFontBBox() {
        return new BoundingBox((List) this.topDict.get(AFMParser.FONT_BBOX));
    }

    public CFFCharset getCharset() {
        return this.charset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCharset(CFFCharset cFFCharset) {
        this.charset = cFFCharset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<byte[]> getCharStringBytes() {
        return this.charStrings;
    }

    public int getNumCharStrings() {
        return this.charStrings.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGlobalSubrIndex(IndexData indexData) {
        this.globalSubrIndex = indexData;
    }

    public IndexData getGlobalSubrIndex() {
        return this.globalSubrIndex;
    }

    public String toString() {
        return getClass().getSimpleName() + "[name=" + this.fontName + ", topDict=" + this.topDict + ", charset=" + this.charset + ", charStrings=" + this.charStrings + "]";
    }
}
