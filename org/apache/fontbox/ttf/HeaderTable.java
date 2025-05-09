package org.apache.fontbox.ttf;

import java.io.IOException;
import java.util.Calendar;

/* loaded from: classes5.dex */
public class HeaderTable extends TTFTable {
    public static final String TAG = "head";
    private long checkSumAdjustment;
    private Calendar created;
    private int flags;
    private short fontDirectionHint;
    private float fontRevision;
    private short glyphDataFormat;
    private short indexToLocFormat;
    private int lowestRecPPEM;
    private int macStyle;
    private long magicNumber;
    private Calendar modified;
    private int unitsPerEm;
    private float version;
    private short xMax;
    private short xMin;
    private short yMax;
    private short yMin;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.version = tTFDataStream.read32Fixed();
        this.fontRevision = tTFDataStream.read32Fixed();
        this.checkSumAdjustment = tTFDataStream.readUnsignedInt();
        this.magicNumber = tTFDataStream.readUnsignedInt();
        this.flags = tTFDataStream.readUnsignedShort();
        this.unitsPerEm = tTFDataStream.readUnsignedShort();
        this.created = tTFDataStream.readInternationalDate();
        this.modified = tTFDataStream.readInternationalDate();
        this.xMin = tTFDataStream.readSignedShort();
        this.yMin = tTFDataStream.readSignedShort();
        this.xMax = tTFDataStream.readSignedShort();
        this.yMax = tTFDataStream.readSignedShort();
        this.macStyle = tTFDataStream.readUnsignedShort();
        this.lowestRecPPEM = tTFDataStream.readUnsignedShort();
        this.fontDirectionHint = tTFDataStream.readSignedShort();
        this.indexToLocFormat = tTFDataStream.readSignedShort();
        this.glyphDataFormat = tTFDataStream.readSignedShort();
        this.initialized = true;
    }

    public long getCheckSumAdjustment() {
        return this.checkSumAdjustment;
    }

    public void setCheckSumAdjustment(long j) {
        this.checkSumAdjustment = j;
    }

    public Calendar getCreated() {
        return this.created;
    }

    public void setCreated(Calendar calendar) {
        this.created = calendar;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public short getFontDirectionHint() {
        return this.fontDirectionHint;
    }

    public void setFontDirectionHint(short s) {
        this.fontDirectionHint = s;
    }

    public float getFontRevision() {
        return this.fontRevision;
    }

    public void setFontRevision(float f) {
        this.fontRevision = f;
    }

    public short getGlyphDataFormat() {
        return this.glyphDataFormat;
    }

    public void setGlyphDataFormat(short s) {
        this.glyphDataFormat = s;
    }

    public short getIndexToLocFormat() {
        return this.indexToLocFormat;
    }

    public void setIndexToLocFormat(short s) {
        this.indexToLocFormat = s;
    }

    public int getLowestRecPPEM() {
        return this.lowestRecPPEM;
    }

    public void setLowestRecPPEM(int i) {
        this.lowestRecPPEM = i;
    }

    public int getMacStyle() {
        return this.macStyle;
    }

    public void setMacStyle(int i) {
        this.macStyle = i;
    }

    public long getMagicNumber() {
        return this.magicNumber;
    }

    public void setMagicNumber(long j) {
        this.magicNumber = j;
    }

    public Calendar getModified() {
        return this.modified;
    }

    public void setModified(Calendar calendar) {
        this.modified = calendar;
    }

    public int getUnitsPerEm() {
        return this.unitsPerEm;
    }

    public void setUnitsPerEm(int i) {
        this.unitsPerEm = i;
    }

    public float getVersion() {
        return this.version;
    }

    public void setVersion(float f) {
        this.version = f;
    }

    public short getXMax() {
        return this.xMax;
    }

    public void setXMax(short s) {
        this.xMax = s;
    }

    public short getXMin() {
        return this.xMin;
    }

    public void setXMin(short s) {
        this.xMin = s;
    }

    public short getYMax() {
        return this.yMax;
    }

    public void setYMax(short s) {
        this.yMax = s;
    }

    public short getYMin() {
        return this.yMin;
    }

    public void setYMin(short s) {
        this.yMin = s;
    }
}
