package org.apache.fontbox.ttf;

import android.graphics.Path;
import java.io.IOException;
import org.apache.fontbox.util.BoundingBox;

/* loaded from: classes5.dex */
public class GlyphData {
    private BoundingBox boundingBox = null;
    private GlyfDescript glyphDescription = null;
    private short numberOfContours;
    private short xMax;
    private short xMin;
    private short yMax;
    private short yMin;

    public void initData(GlyphTable glyphTable, TTFDataStream tTFDataStream) throws IOException {
        this.numberOfContours = tTFDataStream.readSignedShort();
        this.xMin = tTFDataStream.readSignedShort();
        this.yMin = tTFDataStream.readSignedShort();
        this.xMax = tTFDataStream.readSignedShort();
        short readSignedShort = tTFDataStream.readSignedShort();
        this.yMax = readSignedShort;
        this.boundingBox = new BoundingBox(this.xMin, this.yMin, this.xMax, readSignedShort);
        short s = this.numberOfContours;
        if (s >= 0) {
            this.glyphDescription = new GlyfSimpleDescript(s, tTFDataStream);
        } else {
            this.glyphDescription = new GlyfCompositeDescript(tTFDataStream, glyphTable);
        }
    }

    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public short getNumberOfContours() {
        return this.numberOfContours;
    }

    public void setNumberOfContours(short s) {
        this.numberOfContours = s;
    }

    public GlyphDescription getDescription() {
        return this.glyphDescription;
    }

    public Path getPath() {
        return new GlyphRenderer(this.glyphDescription).getPath();
    }

    public short getXMaximum() {
        return this.xMax;
    }

    public short getXMinimum() {
        return this.xMin;
    }

    public short getYMaximum() {
        return this.yMax;
    }

    public short getYMinimum() {
        return this.yMin;
    }
}
