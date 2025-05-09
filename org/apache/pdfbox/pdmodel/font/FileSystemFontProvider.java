package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.fontbox.cff.CFFFont;
import org.apache.fontbox.cff.CFFParser;
import org.apache.fontbox.ttf.CFFTable;
import org.apache.fontbox.ttf.NamingTable;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.fontbox.type1.Type1Font;
import org.apache.fontbox.util.autodetect.FontFileFinder;
import org.apache.pdfbox.io.IOUtils;

/* loaded from: classes5.dex */
final class FileSystemFontProvider extends FontProvider {
    private final Map<String, File> ttfFontFiles = new HashMap();
    private final Map<String, File> cffFontFiles = new HashMap();
    private final Map<String, File> type1FontFiles = new HashMap();
    private final Map<String, TrueTypeFont> ttfFonts = new HashMap();
    private final Map<String, CFFFont> cffFonts = new HashMap();
    private final Map<String, Type1Font> type1Fonts = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileSystemFontProvider() {
        Log.v("PdfBoxAndroid", "Will search the local system for fonts");
        Iterator<URI> it = new FontFileFinder().find().iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            File file = new File(it.next());
            try {
            } catch (IOException e) {
                Log.e("PdfBoxAndroid", "Error parsing font " + file.getPath(), e);
            }
            if (!file.getPath().toLowerCase().endsWith(".ttf") && !file.getPath().toLowerCase().endsWith(".otf")) {
                if (file.getPath().toLowerCase().endsWith(".pfb")) {
                    addType1Font(file);
                }
            }
            addOpenTypeFont(file);
        }
        Log.v("PdfBoxAndroid", "Found " + i + " fonts on the local system");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005d A[Catch: all -> 0x0042, TryCatch #0 {all -> 0x0042, blocks: (B:21:0x003d, B:6:0x0047, B:12:0x005d, B:14:0x0063, B:16:0x0073, B:17:0x0092, B:18:0x0083, B:19:0x00c3), top: B:20:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0047 A[Catch: all -> 0x0042, TryCatch #0 {all -> 0x0042, blocks: (B:21:0x003d, B:6:0x0047, B:12:0x005d, B:14:0x0063, B:16:0x0073, B:17:0x0092, B:18:0x0083, B:19:0x00c3), top: B:20:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void addOpenTypeFont(File file) throws IOException {
        TrueTypeFont trueTypeFont;
        String str;
        NamingTable namingTable = null;
        try {
            trueTypeFont = new TTFParser(false, true).parse(file);
        } catch (IOException e) {
            Log.e("PdfBoxAndroid", "Could not load font file: " + file, e);
            trueTypeFont = null;
            if (trueTypeFont != null) {
            }
            if (namingTable == null) {
            }
            if (trueTypeFont != null) {
            }
        } catch (NullPointerException e2) {
            Log.e("PdfBoxAndroid", "Could not load font file: " + file, e2);
            trueTypeFont = null;
            if (trueTypeFont != null) {
            }
            if (namingTable == null) {
            }
            if (trueTypeFont != null) {
            }
        }
        if (trueTypeFont != null) {
            try {
                namingTable = trueTypeFont.getNaming();
            } catch (Throwable th) {
                if (trueTypeFont != null) {
                    trueTypeFont.close();
                }
                throw th;
            }
        }
        if (namingTable == null) {
            Log.w("PdfBoxAndroid", "Missing 'name' table in font " + file);
        } else if (namingTable.getPostScriptName() != null) {
            String postScriptName = namingTable.getPostScriptName();
            if (trueTypeFont.getTableMap().get(CFFTable.TAG) != null) {
                str = "OTF";
                this.cffFontFiles.putAll(toMap(getNames(trueTypeFont), file));
            } else {
                str = "TTF";
                this.ttfFontFiles.putAll(toMap(getNames(trueTypeFont), file));
            }
            Log.v("PdfBoxAndroid", str + ": '" + postScriptName + "' / '" + namingTable.getFontFamily() + "' / '" + namingTable.getFontSubFamily() + "'");
        } else {
            Log.w("PdfBoxAndroid", "Missing 'name' entry for PostScript name in font " + file);
        }
        if (trueTypeFont != null) {
            trueTypeFont.close();
        }
    }

    private void addType1Font(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Type1Font createWithPFB = Type1Font.createWithPFB(fileInputStream);
            String fontName = createWithPFB.getFontName();
            this.type1FontFiles.putAll(toMap(getNames(createWithPFB), file));
            Log.v("PdfBoxAndroid", "PFB: '" + fontName + "' / '" + createWithPFB.getFamilyName() + "' / '" + createWithPFB.getWeight() + "'");
        } finally {
            fileInputStream.close();
        }
    }

    @Override // org.apache.pdfbox.pdmodel.font.FontProvider
    public synchronized TrueTypeFont getTrueTypeFont(String str) {
        TrueTypeFont trueTypeFont = this.ttfFonts.get(str);
        if (trueTypeFont != null) {
            return trueTypeFont;
        }
        File file = this.ttfFontFiles.get(str);
        if (file != null) {
            try {
                TrueTypeFont parse = new TTFParser(false, true).parse(file);
                Iterator<String> it = getNames(parse).iterator();
                while (it.hasNext()) {
                    this.ttfFonts.put(it.next(), parse);
                }
                Log.d("PdfBoxAndroid", "Loaded " + str + " from " + file);
                return parse;
            } catch (IOException e) {
                Log.e("PdfBoxAndroid", "Could not load font file: " + file, e);
            } catch (NullPointerException e2) {
                Log.e("PdfBoxAndroid", "Could not load font file: " + file, e2);
            }
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.font.FontProvider
    public synchronized CFFFont getCFFFont(String str) {
        FileInputStream fileInputStream;
        CFFFont cFFFont = this.cffFonts.get(str);
        if (cFFFont != null) {
            return cFFFont;
        }
        File file = this.cffFontFiles.get(str);
        FileInputStream fileInputStream2 = null;
        if (file != null) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        CFFFont cFFFont2 = new CFFParser().parse(IOUtils.toByteArray(fileInputStream)).get(0);
                        Iterator<String> it = getNames(cFFFont2).iterator();
                        while (it.hasNext()) {
                            this.cffFonts.put(it.next(), cFFFont2);
                        }
                        Log.d("PdfBoxAndroid", "Loaded " + str + " from " + file);
                        IOUtils.closeQuietly(fileInputStream);
                        return cFFFont2;
                    } catch (IOException e) {
                        e = e;
                        Log.e("PdfBoxAndroid", "Could not load font file: " + file, e);
                        IOUtils.closeQuietly(fileInputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    IOUtils.closeQuietly(fileInputStream2);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(fileInputStream2);
                throw th;
            }
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.font.FontProvider
    public synchronized Type1Font getType1Font(String str) {
        FileInputStream fileInputStream;
        Type1Font type1Font = this.type1Fonts.get(str);
        if (type1Font != null) {
            return type1Font;
        }
        File file = this.type1FontFiles.get(str);
        FileInputStream fileInputStream2 = null;
        if (file != null) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        Type1Font createWithPFB = Type1Font.createWithPFB(fileInputStream);
                        Iterator<String> it = getNames(createWithPFB).iterator();
                        while (it.hasNext()) {
                            this.type1Fonts.put(it.next(), createWithPFB);
                        }
                        Log.d("PdfBoxAndroid", "Loaded " + str + " from " + file);
                        IOUtils.closeQuietly(fileInputStream);
                        return createWithPFB;
                    } catch (IOException e) {
                        e = e;
                        Log.e("PdfBoxAndroid", "Could not load font file: " + file, e);
                        IOUtils.closeQuietly(fileInputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    IOUtils.closeQuietly(fileInputStream2);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(fileInputStream2);
                throw th;
            }
        }
        return null;
    }

    private Map<String, File> toMap(Set<String> set, File file) {
        HashMap hashMap = new HashMap();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), file);
        }
        return hashMap;
    }

    @Override // org.apache.pdfbox.pdmodel.font.FontProvider
    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, File> entry : this.ttfFontFiles.entrySet()) {
            sb.append("TTF: ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue().getPath());
            sb.append('\n');
        }
        for (Map.Entry<String, File> entry2 : this.cffFontFiles.entrySet()) {
            sb.append("OTF: ");
            sb.append(entry2.getKey());
            sb.append(": ");
            sb.append(entry2.getValue().getPath());
            sb.append('\n');
        }
        for (Map.Entry<String, File> entry3 : this.type1FontFiles.entrySet()) {
            sb.append("PFB: ");
            sb.append(entry3.getKey());
            sb.append(": ");
            sb.append(entry3.getValue().getPath());
            sb.append('\n');
        }
        return sb.toString();
    }
}
