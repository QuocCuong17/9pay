package org.apache.fontbox.cff;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.fontbox.type1.Type1CharStringReader;

/* loaded from: classes5.dex */
public class CFFCIDFont extends CFFFont {
    private FDSelect fdSelect;
    private String ordering;
    private String registry;
    private int supplement;
    private List<Map<String, Object>> fontDictionaries = new LinkedList();
    private List<Map<String, Object>> privateDictionaries = new LinkedList();
    private final Map<Integer, CIDKeyedType2CharString> charStringCache = new ConcurrentHashMap();
    private final PrivateType1CharStringReader reader = new PrivateType1CharStringReader();

    public String getRegistry() {
        return this.registry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRegistry(String str) {
        this.registry = str;
    }

    public String getOrdering() {
        return this.ordering;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOrdering(String str) {
        this.ordering = str;
    }

    public int getSupplement() {
        return this.supplement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSupplement(int i) {
        this.supplement = i;
    }

    public List<Map<String, Object>> getFontDicts() {
        return this.fontDictionaries;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFontDict(List<Map<String, Object>> list) {
        this.fontDictionaries = list;
    }

    public List<Map<String, Object>> getPrivDicts() {
        return this.privateDictionaries;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPrivDict(List<Map<String, Object>> list) {
        this.privateDictionaries = list;
    }

    public FDSelect getFdSelect() {
        return this.fdSelect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFdSelect(FDSelect fDSelect) {
        this.fdSelect = fDSelect;
    }

    private int getDefaultWidthX(int i) {
        int fDIndex = this.fdSelect.getFDIndex(i);
        if (fDIndex == -1) {
            return 1000;
        }
        Map<String, Object> map = this.privateDictionaries.get(fDIndex);
        if (map.containsKey("defaultWidthX")) {
            return ((Number) map.get("defaultWidthX")).intValue();
        }
        return 1000;
    }

    private int getNominalWidthX(int i) {
        int fDIndex = this.fdSelect.getFDIndex(i);
        if (fDIndex == -1) {
            return 0;
        }
        Map<String, Object> map = this.privateDictionaries.get(fDIndex);
        if (map.containsKey("nominalWidthX")) {
            return ((Number) map.get("nominalWidthX")).intValue();
        }
        return 0;
    }

    private IndexData getLocalSubrIndex(int i) {
        int fDIndex = this.fdSelect.getFDIndex(i);
        if (fDIndex == -1) {
            return new IndexData(0);
        }
        return (IndexData) this.privateDictionaries.get(fDIndex).get("Subrs");
    }

    public CIDKeyedType2CharString getType2CharString(int i) throws IOException {
        CIDKeyedType2CharString cIDKeyedType2CharString = this.charStringCache.get(Integer.valueOf(i));
        if (cIDKeyedType2CharString != null) {
            return cIDKeyedType2CharString;
        }
        int gIDForCID = this.charset.getGIDForCID(i);
        byte[] bArr = this.charStrings.get(gIDForCID);
        if (bArr == null) {
            bArr = this.charStrings.get(0);
        }
        CIDKeyedType2CharString cIDKeyedType2CharString2 = new CIDKeyedType2CharString(this.reader, this.fontName, i, gIDForCID, new Type2CharStringParser(this.fontName, i).parse(bArr, this.globalSubrIndex, getLocalSubrIndex(gIDForCID)), getDefaultWidthX(i), getNominalWidthX(i));
        this.charStringCache.put(Integer.valueOf(i), cIDKeyedType2CharString2);
        return cIDKeyedType2CharString2;
    }

    @Override // org.apache.fontbox.cff.CFFFont
    public List<Number> getFontMatrix() {
        return (List) this.topDict.get("FontMatrix");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class PrivateType1CharStringReader implements Type1CharStringReader {
        private PrivateType1CharStringReader() {
        }

        @Override // org.apache.fontbox.type1.Type1CharStringReader
        public Type1CharString getType1CharString(String str) throws IOException {
            return CFFCIDFont.this.getType2CharString(0);
        }
    }
}
