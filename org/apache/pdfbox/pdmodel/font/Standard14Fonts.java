package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.fontbox.afm.AFMParser;
import org.apache.fontbox.afm.FontMetrics;
import org.apache.pdfbox.util.PDFBoxResourceLoader;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class Standard14Fonts {
    private static final Map<String, FontMetrics> STANDARD14_AFM_MAP;
    private static final Set<String> STANDARD_14_NAMES = new HashSet();
    private static final Map<String, String> STANDARD_14_MAPPING = new HashMap();

    private Standard14Fonts() {
    }

    static {
        try {
            STANDARD14_AFM_MAP = new HashMap();
            addAFM("Courier-Bold");
            addAFM("Courier-BoldOblique");
            addAFM("Courier");
            addAFM("Courier-Oblique");
            addAFM("Helvetica");
            addAFM("Helvetica-Bold");
            addAFM("Helvetica-BoldOblique");
            addAFM("Helvetica-Oblique");
            addAFM("Symbol");
            addAFM("Times-Bold");
            addAFM("Times-BoldItalic");
            addAFM("Times-Italic");
            addAFM("Times-Roman");
            addAFM("ZapfDingbats");
            addAFM("CourierCourierNew", "Courier");
            addAFM("CourierNew", "Courier");
            addAFM("CourierNew,Italic", "Courier-Oblique");
            addAFM("CourierNew,Bold", "Courier-Bold");
            addAFM("CourierNew,BoldItalic", "Courier-BoldOblique");
            addAFM("Arial", "Helvetica");
            addAFM("Arial,Italic", "Helvetica-Oblique");
            addAFM("Arial,Bold", "Helvetica-Bold");
            addAFM("Arial,BoldItalic", "Helvetica-BoldOblique");
            addAFM("TimesNewRoman", "Times-Roman");
            addAFM("TimesNewRoman,Italic", "Times-Italic");
            addAFM("TimesNewRoman,Bold", "Times-Bold");
            addAFM("TimesNewRoman,BoldItalic", "Times-BoldItalic");
            addAFM("Symbol,Italic", "Symbol");
            addAFM("Symbol,Bold", "Symbol");
            addAFM("Symbol,BoldItalic", "Symbol");
            addAFM("Times", "Times-Roman");
            addAFM("Times,Italic", "Times-Italic");
            addAFM("Times,Bold", "Times-Bold");
            addAFM("Times,BoldItalic", "Times-BoldItalic");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addAFM(String str) throws IOException {
        addAFM(str, str);
    }

    private static void addAFM(String str, String str2) throws IOException {
        InputStream openStream;
        STANDARD_14_NAMES.add(str);
        STANDARD_14_MAPPING.put(str, str2);
        Map<String, FontMetrics> map = STANDARD14_AFM_MAP;
        if (map.containsKey(str2)) {
            map.put(str, map.get(str2));
        }
        String str3 = "org/apache/pdfbox/resources/afm/" + str2 + ".afm";
        if (PDFBoxResourceLoader.isReady()) {
            openStream = PDFBoxResourceLoader.getStream(str3);
        } else {
            URL resource = PDType1Font.class.getClassLoader().getResource(str3);
            if (resource != null) {
                openStream = resource.openStream();
            } else {
                throw new IOException(str3 + " not found");
            }
        }
        try {
            map.put(str, new AFMParser(openStream).parse());
        } finally {
            openStream.close();
        }
    }

    public static FontMetrics getAFM(String str) {
        return STANDARD14_AFM_MAP.get(str);
    }

    public static boolean containsName(String str) {
        return STANDARD_14_NAMES.contains(str);
    }

    public static Set<String> getNames() {
        return Collections.unmodifiableSet(STANDARD_14_NAMES);
    }

    public static String getMappedFontName(String str) {
        return STANDARD_14_MAPPING.get(str);
    }
}
