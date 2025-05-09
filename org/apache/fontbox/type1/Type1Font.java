package org.apache.fontbox.type1;

import android.graphics.Path;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.fontbox.cff.Type1CharString;
import org.apache.fontbox.cff.Type1CharStringParser;
import org.apache.fontbox.encoding.Encoding;
import org.apache.fontbox.pfb.PfbParser;
import org.apache.fontbox.ttf.Type1Equivalent;
import org.apache.fontbox.util.BoundingBox;

/* loaded from: classes5.dex */
public final class Type1Font implements Type1CharStringReader, Type1Equivalent {
    int blueFuzz;
    float blueScale;
    int blueShift;
    int fontType;
    boolean forceBold;
    boolean isFixedPitch;
    float italicAngle;
    int languageGroup;
    int paintType;
    float strokeWidth;
    float underlinePosition;
    float underlineThickness;
    int uniqueID;
    String fontName = "";
    Encoding encoding = null;
    List<Number> fontMatrix = new ArrayList();
    List<Number> fontBBox = new ArrayList();
    String fontID = "";
    String version = "";
    String notice = "";
    String fullName = "";
    String familyName = "";
    String weight = "";
    List<Number> blueValues = new ArrayList();
    List<Number> otherBlues = new ArrayList();
    List<Number> familyBlues = new ArrayList();
    List<Number> familyOtherBlues = new ArrayList();
    List<Number> stdHW = new ArrayList();
    List<Number> stdVW = new ArrayList();
    List<Number> stemSnapH = new ArrayList();
    List<Number> stemSnapV = new ArrayList();
    final List<byte[]> subrs = new ArrayList();
    final Map<String, byte[]> charstrings = new LinkedHashMap();
    private final Map<String, Type1CharString> charStringCache = new ConcurrentHashMap();

    public static Type1Font createWithPFB(InputStream inputStream) throws IOException {
        PfbParser pfbParser = new PfbParser(inputStream);
        return new Type1Parser().parse(pfbParser.getSegment1(), pfbParser.getSegment2());
    }

    public static Type1Font createWithPFB(byte[] bArr) throws IOException {
        PfbParser pfbParser = new PfbParser(bArr);
        return new Type1Parser().parse(pfbParser.getSegment1(), pfbParser.getSegment2());
    }

    public static Type1Font createWithSegments(byte[] bArr, byte[] bArr2) throws IOException {
        return new Type1Parser().parse(bArr, bArr2);
    }

    public List<byte[]> getSubrsArray() {
        return Collections.unmodifiableList(this.subrs);
    }

    public Map<String, byte[]> getCharStringsDict() {
        return Collections.unmodifiableMap(this.charstrings);
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public String getName() {
        return this.fontName;
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public Path getPath(String str) throws IOException {
        return getType1CharString(str).getPath();
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public float getWidth(String str) throws IOException {
        return getType1CharString(str).getWidth();
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public boolean hasGlyph(String str) {
        return this.charstrings.get(str) != null;
    }

    @Override // org.apache.fontbox.type1.Type1CharStringReader
    public Type1CharString getType1CharString(String str) throws IOException {
        Type1CharString type1CharString = this.charStringCache.get(str);
        if (type1CharString != null) {
            return type1CharString;
        }
        byte[] bArr = this.charstrings.get(str);
        if (bArr == null) {
            bArr = this.charstrings.get(".notdef");
        }
        Type1CharString type1CharString2 = new Type1CharString(this, this.fontName, str, new Type1CharStringParser(this.fontName, str).parse(bArr, this.subrs));
        this.charStringCache.put(str, type1CharString2);
        return type1CharString2;
    }

    public String getFontName() {
        return this.fontName;
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public Encoding getEncoding() {
        return this.encoding;
    }

    public int getPaintType() {
        return this.paintType;
    }

    public int getFontType() {
        return this.fontType;
    }

    public List<Number> getFontMatrix() {
        return Collections.unmodifiableList(this.fontMatrix);
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public BoundingBox getFontBBox() {
        return new BoundingBox(this.fontBBox);
    }

    public int getUniqueID() {
        return this.uniqueID;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public String getFontID() {
        return this.fontID;
    }

    public String getVersion() {
        return this.version;
    }

    public String getNotice() {
        return this.notice;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getWeight() {
        return this.weight;
    }

    public float getItalicAngle() {
        return this.italicAngle;
    }

    public boolean isFixedPitch() {
        return this.isFixedPitch;
    }

    public float getUnderlinePosition() {
        return this.underlinePosition;
    }

    public float getUnderlineThickness() {
        return this.underlineThickness;
    }

    public List<Number> getBlueValues() {
        return Collections.unmodifiableList(this.blueValues);
    }

    public List<Number> getOtherBlues() {
        return Collections.unmodifiableList(this.otherBlues);
    }

    public List<Number> getFamilyBlues() {
        return Collections.unmodifiableList(this.familyBlues);
    }

    public List<Number> getFamilyOtherBlues() {
        return Collections.unmodifiableList(this.familyOtherBlues);
    }

    public float getBlueScale() {
        return this.blueScale;
    }

    public int getBlueShift() {
        return this.blueShift;
    }

    public int getBlueFuzz() {
        return this.blueFuzz;
    }

    public List<Number> getStdHW() {
        return Collections.unmodifiableList(this.stdHW);
    }

    public List<Number> getStdVW() {
        return Collections.unmodifiableList(this.stdVW);
    }

    public List<Number> getStemSnapH() {
        return Collections.unmodifiableList(this.stemSnapH);
    }

    public List<Number> getStemSnapV() {
        return Collections.unmodifiableList(this.stemSnapV);
    }

    public boolean isForceBold() {
        return this.forceBold;
    }

    public int getLanguageGroup() {
        return this.languageGroup;
    }

    public String toString() {
        return getClass().getName() + "[fontName=" + this.fontName + ", fullName=" + this.fullName + ", encoding=" + this.encoding + ", charStringsDict=" + this.charstrings + "]";
    }
}
