package org.apache.fontbox.cmap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class CMap {
    private static final String SPACE = " ";
    private int wmode = 0;
    private String cmapName = null;
    private String cmapVersion = null;
    private int cmapType = -1;
    private String registry = null;
    private String ordering = null;
    private int supplement = 0;
    private final List<CodespaceRange> codespaceRanges = new ArrayList();
    private final Map<Integer, String> charToUnicode = new HashMap();
    private final Map<Integer, Integer> codeToCid = new HashMap();
    private final List<CIDRange> codeToCidRanges = new LinkedList();
    private int spaceMapping = -1;

    public boolean hasCIDMappings() {
        return (this.codeToCid.isEmpty() && this.codeToCidRanges.isEmpty()) ? false : true;
    }

    public boolean hasUnicodeMappings() {
        return !this.charToUnicode.isEmpty();
    }

    public String toUnicode(int i) {
        return this.charToUnicode.get(Integer.valueOf(i));
    }

    public int readCode(InputStream inputStream) throws IOException {
        inputStream.mark(4);
        ArrayList arrayList = new ArrayList(4);
        for (int i = 0; i < 4; i++) {
            arrayList.add(Byte.valueOf((byte) inputStream.read()));
            Iterator<CodespaceRange> it = this.codespaceRanges.iterator();
            while (it.hasNext()) {
                if (it.next().isFullMatch(arrayList)) {
                    return toInt(arrayList);
                }
            }
        }
        inputStream.reset();
        ArrayList arrayList2 = new ArrayList(4);
        for (int i2 = 0; i2 < 4; i2++) {
            arrayList2.add(Byte.valueOf((byte) inputStream.read()));
            CodespaceRange codespaceRange = null;
            CodespaceRange codespaceRange2 = null;
            for (CodespaceRange codespaceRange3 : this.codespaceRanges) {
                if (codespaceRange3.isPartialMatch(((Byte) arrayList2.get(i2)).byteValue(), i2) && (codespaceRange == null || codespaceRange3.getStart().length < codespaceRange.getStart().length)) {
                    codespaceRange = codespaceRange3;
                }
                if (codespaceRange2 == null || codespaceRange3.getStart().length < codespaceRange2.getStart().length) {
                    codespaceRange2 = codespaceRange3;
                }
            }
            if (codespaceRange == null) {
                codespaceRange = codespaceRange2;
            }
            if (codespaceRange != null && codespaceRange.getStart().length == arrayList2.size()) {
                return toInt(arrayList2);
            }
        }
        throw new IOException("CMap is invalid");
    }

    private static int toInt(List<Byte> list) {
        Iterator<Byte> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = (i << 8) | ((it.next().byteValue() + 256) % 256);
        }
        return i;
    }

    public int toCID(int i) {
        if (this.codeToCid.containsKey(Integer.valueOf(i))) {
            return this.codeToCid.get(Integer.valueOf(i)).intValue();
        }
        Iterator<CIDRange> it = this.codeToCidRanges.iterator();
        while (it.hasNext()) {
            int map = it.next().map((char) i);
            if (map != -1) {
                return map;
            }
        }
        return 0;
    }

    private static int getCodeFromArray(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 << 8) | ((bArr[i + i4] + 256) % 256);
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCharMapping(byte[] bArr, String str) {
        int codeFromArray = getCodeFromArray(bArr, 0, bArr.length);
        this.charToUnicode.put(Integer.valueOf(codeFromArray), str);
        if (SPACE.equals(str)) {
            this.spaceMapping = codeFromArray;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCIDMapping(int i, int i2) {
        this.codeToCid.put(Integer.valueOf(i2), Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCIDRange(char c, char c2, int i) {
        this.codeToCidRanges.add(0, new CIDRange(c, c2, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCodespaceRange(CodespaceRange codespaceRange) {
        this.codespaceRanges.add(codespaceRange);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void useCmap(CMap cMap) {
        this.codespaceRanges.addAll(cMap.codespaceRanges);
        this.charToUnicode.putAll(cMap.charToUnicode);
        this.codeToCid.putAll(cMap.codeToCid);
        this.codeToCidRanges.addAll(cMap.codeToCidRanges);
    }

    public int getWMode() {
        return this.wmode;
    }

    public void setWMode(int i) {
        this.wmode = i;
    }

    public String getName() {
        return this.cmapName;
    }

    public void setName(String str) {
        this.cmapName = str;
    }

    public String getVersion() {
        return this.cmapVersion;
    }

    public void setVersion(String str) {
        this.cmapVersion = str;
    }

    public int getType() {
        return this.cmapType;
    }

    public void setType(int i) {
        this.cmapType = i;
    }

    public String getRegistry() {
        return this.registry;
    }

    public void setRegistry(String str) {
        this.registry = str;
    }

    public String getOrdering() {
        return this.ordering;
    }

    public void setOrdering(String str) {
        this.ordering = str;
    }

    public int getSupplement() {
        return this.supplement;
    }

    public void setSupplement(int i) {
        this.supplement = i;
    }

    public int getSpaceMapping() {
        return this.spaceMapping;
    }

    public String toString() {
        return this.cmapName;
    }
}
