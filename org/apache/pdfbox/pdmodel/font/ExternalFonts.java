package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.beust.jcommander.Parameters;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.fontbox.cff.CFFCIDFont;
import org.apache.fontbox.cff.CFFFont;
import org.apache.fontbox.cff.CFFParser;
import org.apache.fontbox.cff.CFFType1Font;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.fontbox.ttf.Type1Equivalent;
import org.apache.fontbox.type1.Type1Font;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.util.PDFBoxResourceLoader;

/* loaded from: classes5.dex */
public final class ExternalFonts {
    private static final CFFCIDFont cidFallbackFont;
    private static FontProvider fontProvider;
    private static final Map<String, List<String>> substitutes;
    private static final TrueTypeFont ttfFallbackFont;

    private ExternalFonts() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DefaultFontProvider {
        private static final FontProvider INSTANCE = new FileSystemFontProvider();

        private DefaultFontProvider() {
        }
    }

    static {
        InputStream openStream;
        InputStream openStream2;
        try {
            if (PDFBoxResourceLoader.isReady()) {
                openStream = PDFBoxResourceLoader.getStream("org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
                if (openStream == null) {
                    throw new IOException("Error loading resource: " + openStream);
                }
            } else {
                URL resource = ExternalFonts.class.getClassLoader().getResource("org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
                if (resource == null) {
                    throw new IOException("Error loading resource: org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
                }
                openStream = resource.openStream();
            }
            ttfFallbackFont = new TTFParser().parse(openStream);
            if (PDFBoxResourceLoader.isReady()) {
                openStream2 = PDFBoxResourceLoader.getStream("org/apache/pdfbox/resources/otf/AdobeBlank.otf");
                if (openStream2 == null) {
                    throw new IOException("Error loading resource: org/apache/pdfbox/resources/otf/AdobeBlank.otf");
                }
            } else {
                URL resource2 = ExternalFonts.class.getClassLoader().getResource("org/apache/pdfbox/resources/otf/AdobeBlank.otf");
                if (resource2 == null) {
                    throw new IOException("Error loading resource: org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
                }
                openStream2 = resource2.openStream();
            }
            cidFallbackFont = (CFFCIDFont) new CFFParser().parse(IOUtils.toByteArray(openStream2)).get(0);
            HashMap hashMap = new HashMap();
            substitutes = hashMap;
            hashMap.put("Courier", Arrays.asList("CourierNew", "CourierNewPSMT", "LiberationMono", "NimbusMonL-Regu", "DroidSansMono"));
            hashMap.put("Courier-Bold", Arrays.asList("CourierNewPS-BoldMT", "CourierNew-Bold", "LiberationMono-Bold", "NimbusMonL-Bold", "DroidSansMono"));
            hashMap.put("Courier-Oblique", Arrays.asList("CourierNewPS-ItalicMT", "CourierNew-Italic", "LiberationMono-Italic", "NimbusMonL-ReguObli", "DroidSansMono"));
            hashMap.put("Courier-BoldOblique", Arrays.asList("CourierNewPS-BoldItalicMT", "CourierNew-BoldItalic", "LiberationMono-BoldItalic", "NimbusMonL-BoldObli", "DroidSansMono"));
            hashMap.put("Helvetica", Arrays.asList("ArialMT", "Arial", "LiberationSans", "NimbusSanL-Regu", "Roboto-Regular"));
            hashMap.put("Helvetica-Bold", Arrays.asList("Arial-BoldMT", "Arial-Bold", "LiberationSans-Bold", "NimbusSanL-Bold", "Roboto-Bold"));
            hashMap.put("Helvetica-Oblique", Arrays.asList("Arial-ItalicMT", "Arial-Italic", "Helvetica-Italic", "LiberationSans-Italic", "NimbusSanL-ReguItal", "Roboto-Italic"));
            hashMap.put("Helvetica-BoldOblique", Arrays.asList("Arial-BoldItalicMT", "Helvetica-BoldItalic", "LiberationSans-BoldItalic", "NimbusSanL-BoldItal", "Roboto-BoldItalic"));
            hashMap.put("Times-Roman", Arrays.asList("TimesNewRomanPSMT", "TimesNewRoman", "TimesNewRomanPS", "LiberationSerif", "NimbusRomNo9L-Regu", "DroidSerif-Regular"));
            hashMap.put("Times-Bold", Arrays.asList("TimesNewRomanPS-BoldMT", "TimesNewRomanPS-Bold", "TimesNewRoman-Bold", "LiberationSerif-Bold", "NimbusRomNo9L-Medi", "DroidSerif-Bold"));
            hashMap.put("Times-Italic", Arrays.asList("TimesNewRomanPS-ItalicMT", "TimesNewRomanPS-Italic", "TimesNewRoman-Italic", "LiberationSerif-Italic", "NimbusRomNo9L-ReguItal", "DroidSerif-Italic"));
            hashMap.put("Times-BoldItalic", Arrays.asList("TimesNewRomanPS-BoldItalicMT", "TimesNewRomanPS-BoldItalic", "TimesNewRoman-BoldItalic", "LiberationSerif-BoldItalic", "NimbusRomNo9L-MediItal", "DroidSerif-BoldItalic"));
            hashMap.put("Symbol", Arrays.asList("SymbolMT", "StandardSymL"));
            hashMap.put("ZapfDingbats", Arrays.asList("ZapfDingbatsITC", "Dingbats"));
            hashMap.put("$Adobe-CNS1", Arrays.asList("AdobeMingStd-Light"));
            hashMap.put("$Adobe-Japan1", Arrays.asList("KozMinPr6N-Regular"));
            hashMap.put("$Adobe-Korea1", Arrays.asList("AdobeGothicStd-Bold"));
            hashMap.put("$Adobe-GB1", Arrays.asList("AdobeHeitiStd-Regular"));
            for (String str : Standard14Fonts.getNames()) {
                Map<String, List<String>> map = substitutes;
                if (!map.containsKey(str)) {
                    map.put(str, copySubstitutes(Standard14Fonts.getMappedFontName(str)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setProvider(FontProvider fontProvider2) {
        fontProvider = fontProvider2;
    }

    public static FontProvider getProvider() {
        if (fontProvider == null) {
            fontProvider = DefaultFontProvider.INSTANCE;
        }
        return fontProvider;
    }

    private static List<String> copySubstitutes(String str) {
        return new ArrayList(substitutes.get(str));
    }

    public static void addSubstitute(String str, String str2) {
        Map<String, List<String>> map = substitutes;
        if (!map.containsKey(str)) {
            map.put(str, new ArrayList());
        }
        map.get(str).add(str2);
    }

    private static List<String> getSubstitutes(String str) {
        List<String> list = substitutes.get(str.replaceAll(" ", ""));
        return list != null ? list : Collections.emptyList();
    }

    private static String windowsToPs(String str) {
        return str.replaceAll(",", Parameters.DEFAULT_OPTION_PREFIXES);
    }

    public static CFFCIDFont getCFFCIDFontFallback(String str, PDFontDescriptor pDFontDescriptor) {
        if (str != null) {
            Iterator<String> it = getSubstitutes("$" + str).iterator();
            while (it.hasNext()) {
                CFFFont cFFFont = getProvider().getCFFFont(it.next());
                if (cFFFont instanceof CFFCIDFont) {
                    return (CFFCIDFont) cFFFont;
                }
            }
        }
        return cidFallbackFont;
    }

    public static Type1Equivalent getType1FallbackFont(PDFontDescriptor pDFontDescriptor) {
        String fallbackFontName = getFallbackFontName(pDFontDescriptor);
        Type1Equivalent type1EquivalentFont = getType1EquivalentFont(fallbackFontName);
        if (type1EquivalentFont != null) {
            return type1EquivalentFont;
        }
        Log.e("PdfBoxAndroid", "No fallback font for '" + fallbackFontName + "'");
        return ttfFallbackFont;
    }

    public static TrueTypeFont getTrueTypeFallbackFont(PDFontDescriptor pDFontDescriptor) {
        String fallbackFontName = getFallbackFontName(pDFontDescriptor);
        TrueTypeFont trueTypeFont = getTrueTypeFont(fallbackFontName);
        if (trueTypeFont != null) {
            return trueTypeFont;
        }
        Log.e("PdfBoxAndroid", "No TTF fallback font for '" + fallbackFontName + "'");
        return ttfFallbackFont;
    }

    private static String getFallbackFontName(PDFontDescriptor pDFontDescriptor) {
        if (pDFontDescriptor == null) {
            return "Times-Roman";
        }
        boolean z = false;
        if (pDFontDescriptor.getFontName() != null) {
            String lowerCase = pDFontDescriptor.getFontName().toLowerCase();
            if (lowerCase.contains(TtmlNode.BOLD) || lowerCase.contains("black") || lowerCase.contains("heavy")) {
                z = true;
            }
        }
        if (pDFontDescriptor.isFixedPitch()) {
            if (z && pDFontDescriptor.isItalic()) {
                return "Courier-BoldOblique";
            }
            if (z) {
                return "Courier-Bold";
            }
            if (!pDFontDescriptor.isItalic()) {
                return "Courier";
            }
            return "Courier-Oblique";
        }
        if (pDFontDescriptor.isSerif()) {
            if (z && pDFontDescriptor.isItalic()) {
                return "Times-BoldItalic";
            }
            if (z) {
                return "Times-Bold";
            }
            if (pDFontDescriptor.isItalic()) {
                return "Times-Italic";
            }
            return "Times-Roman";
        }
        if (z && pDFontDescriptor.isItalic()) {
            return "Helvetica-BoldOblique";
        }
        if (z) {
            return "Helvetica-Bold";
        }
        if (!pDFontDescriptor.isItalic()) {
            return "Helvetica";
        }
        return "Helvetica-Oblique";
    }

    public static TrueTypeFont getTrueTypeFont(String str) {
        TrueTypeFont trueTypeFont = getProvider().getTrueTypeFont(str);
        if (trueTypeFont != null) {
            return trueTypeFont;
        }
        Iterator<String> it = getSubstitutes(str).iterator();
        while (it.hasNext()) {
            TrueTypeFont trueTypeFont2 = getProvider().getTrueTypeFont(it.next());
            if (trueTypeFont2 != null) {
                return trueTypeFont2;
            }
        }
        return getProvider().getTrueTypeFont(windowsToPs(str));
    }

    public static Type1Font getType1Font(String str) {
        Type1Font type1Font = getProvider().getType1Font(str);
        if (type1Font != null) {
            return type1Font;
        }
        Iterator<String> it = getSubstitutes(str).iterator();
        while (it.hasNext()) {
            Type1Font type1Font2 = getProvider().getType1Font(it.next());
            if (type1Font2 != null) {
                return type1Font2;
            }
        }
        return getProvider().getType1Font(windowsToPs(str));
    }

    public static CFFType1Font getCFFType1Font(String str) {
        CFFFont cFFFont = getCFFFont(str);
        if (cFFFont instanceof CFFType1Font) {
            return (CFFType1Font) cFFFont;
        }
        return null;
    }

    public static CFFCIDFont getCFFCIDFont(String str) {
        CFFFont cFFFont = getCFFFont(str);
        if (cFFFont instanceof CFFCIDFont) {
            return (CFFCIDFont) cFFFont;
        }
        return null;
    }

    private static CFFFont getCFFFont(String str) {
        CFFFont cFFFont = getProvider().getCFFFont(str);
        if (cFFFont != null) {
            return cFFFont;
        }
        Iterator<String> it = getSubstitutes(str).iterator();
        while (it.hasNext()) {
            CFFFont cFFFont2 = getProvider().getCFFFont(it.next());
            if (cFFFont2 != null) {
                return cFFFont2;
            }
        }
        return getProvider().getCFFFont(windowsToPs(str));
    }

    public static Type1Equivalent getType1EquivalentFont(String str) {
        Type1Font type1Font = getType1Font(str);
        if (type1Font != null) {
            return type1Font;
        }
        CFFType1Font cFFType1Font = getCFFType1Font(str);
        if (cFFType1Font != null) {
            return cFFType1Font;
        }
        TrueTypeFont trueTypeFont = getTrueTypeFont(str);
        if (trueTypeFont != null) {
            return trueTypeFont;
        }
        return null;
    }
}
