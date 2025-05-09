package org.apache.fontbox.cff;

import java.util.HashMap;
import java.util.Map;
import org.apache.fontbox.encoding.Encoding;

/* loaded from: classes5.dex */
public abstract class CFFEncoding extends Encoding {
    private final Map<Integer, String> codeToName = new HashMap();

    @Override // org.apache.fontbox.encoding.Encoding
    public String getName(int i) {
        String str = this.codeToName.get(Integer.valueOf(i));
        return str == null ? ".notdef" : str;
    }

    public void add(int i, int i2, String str) {
        this.codeToName.put(Integer.valueOf(i), str);
        addCharacterEncoding(i, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void add(int i, int i2) {
        String name = CFFStandardString.getName(i2);
        this.codeToName.put(Integer.valueOf(i), name);
        addCharacterEncoding(i, name);
    }
}
