package org.apache.pdfbox.pdmodel.font.encoding;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.util.PDFBoxResourceLoader;

/* loaded from: classes5.dex */
public final class GlyphList {
    private static final GlyphList DEFAULT;
    private static final GlyphList ZAPF_DINGBATS;
    private final Map<String, String> nameToUnicode;
    private final Map<String, String> unicodeToName;

    public static GlyphList getAdobeGlyphList() {
        return DEFAULT;
    }

    public static GlyphList getZapfDingbats() {
        return ZAPF_DINGBATS;
    }

    static {
        try {
            if (PDFBoxResourceLoader.isReady()) {
                DEFAULT = new GlyphList(PDFBoxResourceLoader.getStream("org/apache/pdfbox/resources/glyphlist/glyphlist.txt"));
            } else {
                DEFAULT = new GlyphList(GlyphList.class.getClassLoader().getResourceAsStream("org/apache/pdfbox/resources/glyphlist/glyphlist.txt"));
            }
            if (PDFBoxResourceLoader.isReady()) {
                ZAPF_DINGBATS = new GlyphList(PDFBoxResourceLoader.getStream("org/apache/pdfbox/resources/glyphlist/zapfdingbats.txt"));
            } else {
                ZAPF_DINGBATS = new GlyphList(GlyphList.class.getClassLoader().getResourceAsStream("org/apache/pdfbox/resources/glyphlist/zapfdingbats.txt"));
            }
            try {
                if (System.getProperty("glyphlist_ext") == null) {
                } else {
                    throw new UnsupportedOperationException("glyphlist_ext is no longer supported, use GlyphList.DEFAULT.addGlyphs(Properties) instead");
                }
            } catch (SecurityException unused) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GlyphList(InputStream inputStream) throws IOException {
        this.nameToUnicode = new HashMap();
        this.unicodeToName = new HashMap();
        loadList(inputStream);
    }

    public GlyphList(GlyphList glyphList, InputStream inputStream) throws IOException {
        this.nameToUnicode = new HashMap(glyphList.nameToUnicode);
        this.unicodeToName = new HashMap(glyphList.unicodeToName);
        loadList(inputStream);
    }

    private void loadList(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (bufferedReader.ready()) {
            try {
                String readLine = bufferedReader.readLine();
                if (!readLine.startsWith("#")) {
                    String[] split = readLine.split(";");
                    if (split.length < 2) {
                        throw new IOException("Invalid glyph list entry: " + readLine);
                    }
                    String str = split[0];
                    String[] split2 = split[1].split(" ");
                    if (this.nameToUnicode.containsKey(str)) {
                        Log.w("PdfBoxAndroid", "duplicate value for " + str + " -> " + split[1] + " " + this.nameToUnicode.get(str));
                    }
                    int length = split2.length;
                    int[] iArr = new int[length];
                    int length2 = split2.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length2) {
                        iArr[i2] = Integer.parseInt(split2[i], 16);
                        i++;
                        i2++;
                    }
                    String str2 = new String(iArr, 0, length);
                    this.nameToUnicode.put(str, str2);
                    if (!this.unicodeToName.containsKey(str2)) {
                        this.unicodeToName.put(str2, str);
                    }
                }
            } finally {
                bufferedReader.close();
            }
        }
    }

    public String codePointToName(int i) {
        String str = this.unicodeToName.get(new String(new int[]{i}, 0, 1));
        return str == null ? ".notdef" : str;
    }

    public String sequenceToName(String str) {
        String str2 = this.unicodeToName.get(str);
        return str2 == null ? ".notdef" : str2;
    }

    public String toUnicode(String str) {
        if (str == null) {
            return null;
        }
        String str2 = this.nameToUnicode.get(str);
        if (str2 == null) {
            if (str.indexOf(46) > 0) {
                str2 = toUnicode(str.substring(0, str.indexOf(46)));
            } else if (str.startsWith("uni") && str.length() == 7) {
                int length = str.length();
                StringBuilder sb = new StringBuilder();
                int i = 3;
                while (true) {
                    int i2 = i + 4;
                    if (i2 > length) {
                        break;
                    }
                    try {
                        int parseInt = Integer.parseInt(str.substring(i, i2), 16);
                        if (parseInt > 55295 && parseInt < 57344) {
                            Log.w("PdfBoxAndroid", "Unicode character name with disallowed code area: " + str);
                        } else {
                            sb.append((char) parseInt);
                        }
                        i = i2;
                    } catch (NumberFormatException unused) {
                        Log.w("PdfBoxAndroid", "Not a number in Unicode character name: " + str);
                    }
                    Log.w("PdfBoxAndroid", "Not a number in Unicode character name: " + str);
                }
                str2 = sb.toString();
            } else if (str.startsWith("u") && str.length() == 5) {
                try {
                    int parseInt2 = Integer.parseInt(str.substring(1), 16);
                    if (parseInt2 > 55295 && parseInt2 < 57344) {
                        Log.w("PdfBoxAndroid", "Unicode character name with disallowed code area: " + str);
                    } else {
                        str2 = String.valueOf((char) parseInt2);
                    }
                } catch (NumberFormatException unused2) {
                    Log.w("PdfBoxAndroid", "Not a number in Unicode character name: " + str);
                }
            }
            this.nameToUnicode.put(str, str2);
        }
        return str2;
    }
}
