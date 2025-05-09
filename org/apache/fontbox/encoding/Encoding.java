package org.apache.fontbox.encoding;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class Encoding {
    private static final String NOTDEF = ".notdef";
    protected Map<Integer, String> codeToName = new HashMap();
    protected Map<String, Integer> nameToCode = new HashMap();
    private static final Map<String, String> NAME_TO_CHARACTER = new HashMap();
    private static final Map<String, String> CHARACTER_TO_NAME = new HashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    public void addCharacterEncoding(int i, String str) {
        this.codeToName.put(Integer.valueOf(i), str);
        this.nameToCode.put(str, Integer.valueOf(i));
    }

    public Integer getCode(String str) {
        return this.nameToCode.get(str);
    }

    public String getName(int i) {
        String str = this.codeToName.get(Integer.valueOf(i));
        return str == null ? NOTDEF : str;
    }

    public String getNameFromCharacter(char c) throws IOException {
        String str = CHARACTER_TO_NAME.get(Character.valueOf(c));
        if (str != null) {
            return str;
        }
        throw new IOException("No name for character '" + c + "'");
    }

    public String getCharacter(int i) throws IOException {
        return getCharacter(getName(i));
    }

    public static String getCharacter(String str) {
        String str2 = NAME_TO_CHARACTER.get(str);
        return str2 == null ? str : str2;
    }

    public Map<Integer, String> getCodeToNameMap() {
        return Collections.unmodifiableMap(this.codeToName);
    }
}
