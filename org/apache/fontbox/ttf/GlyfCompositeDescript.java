package org.apache.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class GlyfCompositeDescript extends GlyfDescript {
    private boolean beingResolved;
    private final List<GlyfCompositeComp> components;
    private GlyphTable glyphTable;
    private boolean resolved;

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public boolean isComposite() {
        return true;
    }

    public GlyfCompositeDescript(TTFDataStream tTFDataStream, GlyphTable glyphTable) throws IOException {
        super((short) -1, tTFDataStream);
        GlyfCompositeComp glyfCompositeComp;
        this.components = new ArrayList();
        this.glyphTable = null;
        this.beingResolved = false;
        this.resolved = false;
        this.glyphTable = glyphTable;
        do {
            glyfCompositeComp = new GlyfCompositeComp(tTFDataStream);
            this.components.add(glyfCompositeComp);
        } while ((glyfCompositeComp.getFlags() & 32) != 0);
        if ((glyfCompositeComp.getFlags() & OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING) != 0) {
            readInstructions(tTFDataStream, tTFDataStream.readUnsignedShort());
        }
    }

    @Override // org.apache.fontbox.ttf.GlyfDescript, org.apache.fontbox.ttf.GlyphDescription
    public void resolve() {
        if (this.resolved) {
            return;
        }
        if (this.beingResolved) {
            Log.e("PdfBoxAndroid", "Circular reference in GlyfCompositeDesc");
            return;
        }
        this.beingResolved = true;
        int i = 0;
        int i2 = 0;
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            glyfCompositeComp.setFirstIndex(i);
            glyfCompositeComp.setFirstContour(i2);
            GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
            if (glypDescription != null) {
                glypDescription.resolve();
                i += glypDescription.getPointCount();
                i2 += glypDescription.getContourCount();
            }
        }
        this.resolved = true;
        this.beingResolved = false;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getEndPtOfContours(int i) {
        GlyfCompositeComp compositeCompEndPt = getCompositeCompEndPt(i);
        if (compositeCompEndPt != null) {
            return getGlypDescription(compositeCompEndPt.getGlyphIndex()).getEndPtOfContours(i - compositeCompEndPt.getFirstContour()) + compositeCompEndPt.getFirstIndex();
        }
        return 0;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public byte getFlags(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp != null) {
            return getGlypDescription(compositeComp.getGlyphIndex()).getFlags(i - compositeComp.getFirstIndex());
        }
        return (byte) 0;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public short getXCoordinate(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp == null) {
            return (short) 0;
        }
        GlyphDescription glypDescription = getGlypDescription(compositeComp.getGlyphIndex());
        int firstIndex = i - compositeComp.getFirstIndex();
        return (short) (((short) compositeComp.scaleX(glypDescription.getXCoordinate(firstIndex), glypDescription.getYCoordinate(firstIndex))) + compositeComp.getXTranslate());
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public short getYCoordinate(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp == null) {
            return (short) 0;
        }
        GlyphDescription glypDescription = getGlypDescription(compositeComp.getGlyphIndex());
        int firstIndex = i - compositeComp.getFirstIndex();
        return (short) (((short) compositeComp.scaleY(glypDescription.getXCoordinate(firstIndex), glypDescription.getYCoordinate(firstIndex))) + compositeComp.getYTranslate());
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getPointCount() {
        if (!this.resolved) {
            Log.e("PdfBoxAndroid", "getPointCount called on unresolved GlyfCompositeDescript");
        }
        GlyfCompositeComp glyfCompositeComp = this.components.get(r0.size() - 1);
        GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
        if (glypDescription == null) {
            Log.e("PdfBoxAndroid", "getGlypDescription(" + glyfCompositeComp.getGlyphIndex() + ") is null, returning 0");
            return 0;
        }
        return glyfCompositeComp.getFirstIndex() + glypDescription.getPointCount();
    }

    @Override // org.apache.fontbox.ttf.GlyfDescript, org.apache.fontbox.ttf.GlyphDescription
    public int getContourCount() {
        if (!this.resolved) {
            Log.e("PdfBoxAndroid", "getContourCount called on unresolved GlyfCompositeDescript");
        }
        GlyfCompositeComp glyfCompositeComp = this.components.get(r0.size() - 1);
        return glyfCompositeComp.getFirstContour() + getGlypDescription(glyfCompositeComp.getGlyphIndex()).getContourCount();
    }

    public int getComponentCount() {
        return this.components.size();
    }

    private GlyfCompositeComp getCompositeComp(int i) {
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
            if (glyfCompositeComp.getFirstIndex() <= i && i < glyfCompositeComp.getFirstIndex() + glypDescription.getPointCount()) {
                return glyfCompositeComp;
            }
        }
        return null;
    }

    private GlyfCompositeComp getCompositeCompEndPt(int i) {
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
            if (glyfCompositeComp.getFirstContour() <= i && i < glyfCompositeComp.getFirstContour() + glypDescription.getContourCount()) {
                return glyfCompositeComp;
            }
        }
        return null;
    }

    private GlyphDescription getGlypDescription(int i) {
        try {
            GlyphData glyph = this.glyphTable.getGlyph(i);
            if (glyph != null) {
                return glyph.getDescription();
            }
            return null;
        } catch (IOException e) {
            Log.e("PdfBoxAndroid", e.getMessage());
            return null;
        }
    }
}
