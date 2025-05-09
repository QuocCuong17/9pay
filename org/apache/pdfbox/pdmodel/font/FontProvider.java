package org.apache.pdfbox.pdmodel.font;

import com.beust.jcommander.Parameters;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.fontbox.cff.CFFFont;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.fontbox.type1.Type1Font;

/* loaded from: classes5.dex */
public abstract class FontProvider {
    public abstract CFFFont getCFFFont(String str);

    public abstract TrueTypeFont getTrueTypeFont(String str);

    public abstract Type1Font getType1Font(String str);

    public abstract String toDebugString();

    /* JADX INFO: Access modifiers changed from: protected */
    public final Set<String> getNames(TrueTypeFont trueTypeFont) throws IOException {
        return getPostScriptNames(trueTypeFont.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Set<String> getNames(Type1Font type1Font) throws IOException {
        return getPostScriptNames(type1Font.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Set<String> getNames(CFFFont cFFFont) throws IOException {
        return getPostScriptNames(cFFFont.getName());
    }

    private Set<String> getPostScriptNames(String str) throws IOException {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        hashSet.add(str.replaceAll(Parameters.DEFAULT_OPTION_PREFIXES, ""));
        return hashSet;
    }
}
