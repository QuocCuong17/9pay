package org.apache.fontbox.util.autodetect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class UnixFontDirFinder extends NativeFontDirFinder {
    @Override // org.apache.fontbox.util.autodetect.NativeFontDirFinder
    protected String[] getSearchableDirectories() {
        return new String[]{System.getProperty("user.home") + "/.fonts", "/usr/local/fonts", "/usr/local/share/fonts", "/usr/share/fonts", "/usr/X11R6/lib/X11/fonts"};
    }

    public Map<String, String> getCommonTTFMapping() {
        HashMap hashMap = new HashMap();
        hashMap.put("TimesNewRoman,BoldItalic", "LiberationSerif-BoldItalic");
        hashMap.put("TimesNewRoman,Bold", "LiberationSerif-Bold");
        hashMap.put("TimesNewRoman,Italic", "LiberationSerif-Italic");
        hashMap.put("TimesNewRoman", "LiberationSerif");
        hashMap.put("Arial,BoldItalic", "LiberationSans-BoldItalic");
        hashMap.put("Arial,Italic", "LiberationSans-Italic");
        hashMap.put("Arial,Bold", "LiberationSans-Bold");
        hashMap.put("Arial", "LiberationSans");
        hashMap.put("Courier,BoldItalic", "LiberationMono-BoldItalic");
        hashMap.put("Courier,Italic", "LiberationMono-Italic");
        hashMap.put("Courier,Bold", "LiberationMono-Bold");
        hashMap.put("Courier", "LiberationMono");
        hashMap.put("Symbol", "OpenSymbol");
        hashMap.put("ZapfDingbats", "Dingbats");
        return Collections.unmodifiableMap(hashMap);
    }
}
