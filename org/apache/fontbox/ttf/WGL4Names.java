package org.apache.fontbox.ttf;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import io.sentry.profilemeasurements.ProfileMeasurement;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes5.dex */
public final class WGL4Names {
    public static final String[] MAC_GLYPH_NAMES = {".notdef", ".null", "nonmarkingreturn", "space", "exclam", "quotedbl", "numbersign", "dollar", ProfileMeasurement.UNIT_PERCENT, "ampersand", "quotesingle", "parenleft", "parenright", "asterisk", "plus", "comma", "hyphen", TypedValues.CycleType.S_WAVE_PERIOD, "slash", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "colon", "semicolon", "less", "equal", "greater", "question", DynamicLink.ItunesConnectAnalyticsParameters.KEY_ITUNES_CONNECT_AT, "A", "B", "C", "D", "E", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, "G", StandardStructureTypes.H, "I", "J", "K", "L", "M", "N", PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, "P", "Q", "R", "S", "T", PDBorderStyleDictionary.STYLE_UNDERLINE, "V", "W", "X", "Y", "Z", "bracketleft", "backslash", "bracketright", "asciicircum", "underscore", "grave", "a", "b", "c", "d", "e", "f", "g", CmcdData.Factory.STREAMING_FORMAT_HLS, CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "j", "k", CmcdData.Factory.STREAM_TYPE_LIVE, "m", "n", "o", TtmlNode.TAG_P, "q", PDPageLabelRange.STYLE_ROMAN_LOWER, CmcdData.Factory.STREAMING_FORMAT_SS, "t", "u", "v", "w", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "z", "braceleft", "bar", "braceright", "asciitilde", "Adieresis", "Aring", "Ccedilla", "Eacute", "Ntilde", "Odieresis", "Udieresis", "aacute", "agrave", "acircumflex", "adieresis", "atilde", "aring", "ccedilla", "eacute", "egrave", "ecircumflex", "edieresis", "iacute", "igrave", "icircumflex", "idieresis", "ntilde", "oacute", "ograve", "ocircumflex", "odieresis", "otilde", "uacute", "ugrave", "ucircumflex", "udieresis", "dagger", "degree", "cent", "sterling", "section", "bullet", "paragraph", "germandbls", "registered", "copyright", "trademark", "acute", "dieresis", "notequal", "AE", "Oslash", "infinity", "plusminus", "lessequal", "greaterequal", "yen", "mu", "partialdiff", "summation", "product", "pi", "integral", "ordfeminine", "ordmasculine", "Omega", "ae", "oslash", "questiondown", "exclamdown", "logicalnot", "radical", "florin", "approxequal", "Delta", "guillemotleft", "guillemotright", "ellipsis", "nonbreakingspace", "Agrave", "Atilde", "Otilde", "OE", "oe", "endash", "emdash", "quotedblleft", "quotedblright", "quoteleft", "quoteright", "divide", "lozenge", "ydieresis", "Ydieresis", "fraction", FirebaseAnalytics.Param.CURRENCY, "guilsinglleft", "guilsinglright", "fi", "fl", "daggerdbl", "periodcentered", "quotesinglbase", "quotedblbase", "perthousand", "Acircumflex", "Ecircumflex", "Aacute", "Edieresis", "Egrave", "Iacute", "Icircumflex", "Idieresis", "Igrave", "Oacute", "Ocircumflex", "apple", "Ograve", "Uacute", "Ucircumflex", "Ugrave", "dotlessi", "circumflex", "tilde", "macron", "breve", "dotaccent", "ring", "cedilla", "hungarumlaut", "ogonek", "caron", "Lslash", "lslash", "Scaron", "scaron", "Zcaron", "zcaron", "brokenbar", "Eth", "eth", "Yacute", "yacute", "Thorn", "thorn", "minus", "multiply", "onesuperior", "twosuperior", "threesuperior", "onehalf", "onequarter", "threequarters", "franc", "Gbreve", "gbreve", "Idotaccent", "Scedilla", "scedilla", "Cacute", "cacute", "Ccaron", "ccaron", "dcroat"};
    public static final Map<String, Integer> MAC_GLYPH_NAMES_INDICES = new HashMap();
    public static final int NUMBER_OF_MAC_GLYPHS = 258;

    static {
        for (int i = 0; i < 258; i++) {
            MAC_GLYPH_NAMES_INDICES.put(MAC_GLYPH_NAMES[i], Integer.valueOf(i));
        }
    }

    private WGL4Names() {
    }
}
