package org.apache.pdfbox.pdmodel.font.encoding;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public abstract class Encoding implements COSObjectable {
    protected final Map<Integer, String> codeToName = new HashMap();
    protected final Set<String> names = new HashSet();

    public static Encoding getInstance(COSName cOSName) {
        if (COSName.STANDARD_ENCODING.equals(cOSName)) {
            return StandardEncoding.INSTANCE;
        }
        if (COSName.WIN_ANSI_ENCODING.equals(cOSName)) {
            return WinAnsiEncoding.INSTANCE;
        }
        if (COSName.MAC_ROMAN_ENCODING.equals(cOSName)) {
            return MacRomanEncoding.INSTANCE;
        }
        return null;
    }

    public Map<Integer, String> getCodeToNameMap() {
        return Collections.unmodifiableMap(this.codeToName);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void add(int i, String str) {
        this.codeToName.put(Integer.valueOf(i), str);
        this.names.add(str);
    }

    public boolean contains(String str) {
        return this.names.contains(str);
    }

    public boolean contains(int i) {
        return this.codeToName.containsKey(Integer.valueOf(i));
    }

    public String getName(int i) {
        String str = this.codeToName.get(Integer.valueOf(i));
        return str != null ? str : ".notdef";
    }
}
