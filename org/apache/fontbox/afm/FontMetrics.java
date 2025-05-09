package org.apache.fontbox.afm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.fontbox.util.BoundingBox;

/* loaded from: classes5.dex */
public class FontMetrics {
    private float afmVersion;
    private float ascender;
    private float capHeight;
    private float[] charWidth;
    private String characterSet;
    private int characters;
    private float descender;
    private String encodingScheme;
    private int escChar;
    private String familyName;
    private BoundingBox fontBBox;
    private String fontName;
    private String fontVersion;
    private String fullName;
    private boolean isBaseFont;
    private boolean isFixedPitch;
    private boolean isFixedV;
    private float italicAngle;
    private int mappingScheme;
    private String notice;
    private float standardHorizontalWidth;
    private float standardVerticalWidth;
    private float underlinePosition;
    private float underlineThickness;
    private float[] vVector;
    private String weight;
    private float xHeight;
    private int metricSets = 0;
    private final List<String> comments = new ArrayList();
    private List<CharMetric> charMetrics = new ArrayList();
    private final Map<String, CharMetric> charMetricsMap = new HashMap();
    private List<TrackKern> trackKern = new ArrayList();
    private List<Composite> composites = new ArrayList();
    private List<KernPair> kernPairs = new ArrayList();
    private List<KernPair> kernPairs0 = new ArrayList();
    private List<KernPair> kernPairs1 = new ArrayList();

    public float getCharacterWidth(String str) {
        CharMetric charMetric = this.charMetricsMap.get(str);
        if (charMetric == null) {
            return 0.0f;
        }
        return charMetric.getWx();
    }

    public float getCharacterHeight(String str) {
        CharMetric charMetric = this.charMetricsMap.get(str);
        if (charMetric == null) {
            return 0.0f;
        }
        if (charMetric.getWy() == 0.0f) {
            return charMetric.getBoundingBox().getHeight();
        }
        return charMetric.getWy();
    }

    public float getAverageCharacterWidth() {
        float f = 0.0f;
        float f2 = 0.0f;
        for (CharMetric charMetric : this.charMetricsMap.values()) {
            if (charMetric.getWx() > 0.0f) {
                f += charMetric.getWx();
                f2 += 1.0f;
            }
        }
        if (f > 0.0f) {
            return f / f2;
        }
        return 0.0f;
    }

    public void addComment(String str) {
        this.comments.add(str);
    }

    public List<String> getComments() {
        return this.comments;
    }

    public float getAFMVersion() {
        return this.afmVersion;
    }

    public int getMetricSets() {
        return this.metricSets;
    }

    public void setAFMVersion(float f) {
        this.afmVersion = f;
    }

    public void setMetricSets(int i) {
        if (i < 0 || i > 2) {
            throw new RuntimeException("The metricSets attribute must be in the set {0,1,2} and not '" + i + "'");
        }
        this.metricSets = i;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void setFontName(String str) {
        this.fontName = str;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String str) {
        this.fullName = str;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String str) {
        this.familyName = str;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public BoundingBox getFontBBox() {
        return this.fontBBox;
    }

    public void setFontBBox(BoundingBox boundingBox) {
        this.fontBBox = boundingBox;
    }

    public String getNotice() {
        return this.notice;
    }

    public void setNotice(String str) {
        this.notice = str;
    }

    public String getEncodingScheme() {
        return this.encodingScheme;
    }

    public void setEncodingScheme(String str) {
        this.encodingScheme = str;
    }

    public int getMappingScheme() {
        return this.mappingScheme;
    }

    public void setMappingScheme(int i) {
        this.mappingScheme = i;
    }

    public int getEscChar() {
        return this.escChar;
    }

    public void setEscChar(int i) {
        this.escChar = i;
    }

    public String getCharacterSet() {
        return this.characterSet;
    }

    public void setCharacterSet(String str) {
        this.characterSet = str;
    }

    public int getCharacters() {
        return this.characters;
    }

    public void setCharacters(int i) {
        this.characters = i;
    }

    public boolean isBaseFont() {
        return this.isBaseFont;
    }

    public void setIsBaseFont(boolean z) {
        this.isBaseFont = z;
    }

    public float[] getVVector() {
        return this.vVector;
    }

    public void setVVector(float[] fArr) {
        this.vVector = fArr;
    }

    public boolean isFixedV() {
        return this.isFixedV;
    }

    public void setIsFixedV(boolean z) {
        this.isFixedV = z;
    }

    public float getCapHeight() {
        return this.capHeight;
    }

    public void setCapHeight(float f) {
        this.capHeight = f;
    }

    public float getXHeight() {
        return this.xHeight;
    }

    public void setXHeight(float f) {
        this.xHeight = f;
    }

    public float getAscender() {
        return this.ascender;
    }

    public void setAscender(float f) {
        this.ascender = f;
    }

    public float getDescender() {
        return this.descender;
    }

    public void setDescender(float f) {
        this.descender = f;
    }

    public String getFontVersion() {
        return this.fontVersion;
    }

    public void setFontVersion(String str) {
        this.fontVersion = str;
    }

    public float getUnderlinePosition() {
        return this.underlinePosition;
    }

    public void setUnderlinePosition(float f) {
        this.underlinePosition = f;
    }

    public float getUnderlineThickness() {
        return this.underlineThickness;
    }

    public void setUnderlineThickness(float f) {
        this.underlineThickness = f;
    }

    public float getItalicAngle() {
        return this.italicAngle;
    }

    public void setItalicAngle(float f) {
        this.italicAngle = f;
    }

    public float[] getCharWidth() {
        return this.charWidth;
    }

    public void setCharWidth(float[] fArr) {
        this.charWidth = fArr;
    }

    public boolean isFixedPitch() {
        return this.isFixedPitch;
    }

    public void setFixedPitch(boolean z) {
        this.isFixedPitch = z;
    }

    public List<CharMetric> getCharMetrics() {
        return this.charMetrics;
    }

    public void setCharMetrics(List<CharMetric> list) {
        this.charMetrics = list;
    }

    public void addCharMetric(CharMetric charMetric) {
        this.charMetrics.add(charMetric);
        this.charMetricsMap.put(charMetric.getName(), charMetric);
    }

    public List<TrackKern> getTrackKern() {
        return this.trackKern;
    }

    public void setTrackKern(List<TrackKern> list) {
        this.trackKern = list;
    }

    public void addTrackKern(TrackKern trackKern) {
        this.trackKern.add(trackKern);
    }

    public List<Composite> getComposites() {
        return this.composites;
    }

    public void setComposites(List<Composite> list) {
        this.composites = list;
    }

    public void addComposite(Composite composite) {
        this.composites.add(composite);
    }

    public List<KernPair> getKernPairs() {
        return this.kernPairs;
    }

    public void addKernPair(KernPair kernPair) {
        this.kernPairs.add(kernPair);
    }

    public void setKernPairs(List<KernPair> list) {
        this.kernPairs = list;
    }

    public List<KernPair> getKernPairs0() {
        return this.kernPairs0;
    }

    public void addKernPair0(KernPair kernPair) {
        this.kernPairs0.add(kernPair);
    }

    public void setKernPairs0(List<KernPair> list) {
        this.kernPairs0 = list;
    }

    public List<KernPair> getKernPairs1() {
        return this.kernPairs1;
    }

    public void addKernPair1(KernPair kernPair) {
        this.kernPairs1.add(kernPair);
    }

    public void setKernPairs1(List<KernPair> list) {
        this.kernPairs1 = list;
    }

    public float getStandardHorizontalWidth() {
        return this.standardHorizontalWidth;
    }

    public void setStandardHorizontalWidth(float f) {
        this.standardHorizontalWidth = f;
    }

    public float getStandardVerticalWidth() {
        return this.standardVerticalWidth;
    }

    public void setStandardVerticalWidth(float f) {
        this.standardVerticalWidth = f;
    }
}
