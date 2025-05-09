package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class HorizontalHeaderTable extends TTFTable {
    public static final String TAG = "hhea";
    private int advanceWidthMax;
    private short ascender;
    private short caretSlopeRise;
    private short caretSlopeRun;
    private short descender;
    private short lineGap;
    private short metricDataFormat;
    private short minLeftSideBearing;
    private short minRightSideBearing;
    private int numberOfHMetrics;
    private short reserved1;
    private short reserved2;
    private short reserved3;
    private short reserved4;
    private short reserved5;
    private float version;
    private short xMaxExtent;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.version = tTFDataStream.read32Fixed();
        this.ascender = tTFDataStream.readSignedShort();
        this.descender = tTFDataStream.readSignedShort();
        this.lineGap = tTFDataStream.readSignedShort();
        this.advanceWidthMax = tTFDataStream.readUnsignedShort();
        this.minLeftSideBearing = tTFDataStream.readSignedShort();
        this.minRightSideBearing = tTFDataStream.readSignedShort();
        this.xMaxExtent = tTFDataStream.readSignedShort();
        this.caretSlopeRise = tTFDataStream.readSignedShort();
        this.caretSlopeRun = tTFDataStream.readSignedShort();
        this.reserved1 = tTFDataStream.readSignedShort();
        this.reserved2 = tTFDataStream.readSignedShort();
        this.reserved3 = tTFDataStream.readSignedShort();
        this.reserved4 = tTFDataStream.readSignedShort();
        this.reserved5 = tTFDataStream.readSignedShort();
        this.metricDataFormat = tTFDataStream.readSignedShort();
        this.numberOfHMetrics = tTFDataStream.readUnsignedShort();
        this.initialized = true;
    }

    public int getAdvanceWidthMax() {
        return this.advanceWidthMax;
    }

    public void setAdvanceWidthMax(int i) {
        this.advanceWidthMax = i;
    }

    public short getAscender() {
        return this.ascender;
    }

    public void setAscender(short s) {
        this.ascender = s;
    }

    public short getCaretSlopeRise() {
        return this.caretSlopeRise;
    }

    public void setCaretSlopeRise(short s) {
        this.caretSlopeRise = s;
    }

    public short getCaretSlopeRun() {
        return this.caretSlopeRun;
    }

    public void setCaretSlopeRun(short s) {
        this.caretSlopeRun = s;
    }

    public short getDescender() {
        return this.descender;
    }

    public void setDescender(short s) {
        this.descender = s;
    }

    public short getLineGap() {
        return this.lineGap;
    }

    public void setLineGap(short s) {
        this.lineGap = s;
    }

    public short getMetricDataFormat() {
        return this.metricDataFormat;
    }

    public void setMetricDataFormat(short s) {
        this.metricDataFormat = s;
    }

    public short getMinLeftSideBearing() {
        return this.minLeftSideBearing;
    }

    public void setMinLeftSideBearing(short s) {
        this.minLeftSideBearing = s;
    }

    public short getMinRightSideBearing() {
        return this.minRightSideBearing;
    }

    public void setMinRightSideBearing(short s) {
        this.minRightSideBearing = s;
    }

    public int getNumberOfHMetrics() {
        return this.numberOfHMetrics;
    }

    public void setNumberOfHMetrics(int i) {
        this.numberOfHMetrics = i;
    }

    public short getReserved1() {
        return this.reserved1;
    }

    public void setReserved1(short s) {
        this.reserved1 = s;
    }

    public short getReserved2() {
        return this.reserved2;
    }

    public void setReserved2(short s) {
        this.reserved2 = s;
    }

    public short getReserved3() {
        return this.reserved3;
    }

    public void setReserved3(short s) {
        this.reserved3 = s;
    }

    public short getReserved4() {
        return this.reserved4;
    }

    public void setReserved4(short s) {
        this.reserved4 = s;
    }

    public short getReserved5() {
        return this.reserved5;
    }

    public void setReserved5(short s) {
        this.reserved5 = s;
    }

    public float getVersion() {
        return this.version;
    }

    public void setVersion(float f) {
        this.version = f;
    }

    public short getXMaxExtent() {
        return this.xMaxExtent;
    }

    public void setXMaxExtent(short s) {
        this.xMaxExtent = s;
    }
}
