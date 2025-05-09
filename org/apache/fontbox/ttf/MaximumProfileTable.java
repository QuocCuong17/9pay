package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class MaximumProfileTable extends TTFTable {
    public static final String TAG = "maxp";
    private int maxComponentDepth;
    private int maxComponentElements;
    private int maxCompositeContours;
    private int maxCompositePoints;
    private int maxContours;
    private int maxFunctionDefs;
    private int maxInstructionDefs;
    private int maxPoints;
    private int maxSizeOfInstructions;
    private int maxStackElements;
    private int maxStorage;
    private int maxTwilightPoints;
    private int maxZones;
    private int numGlyphs;
    private float version;

    public int getMaxComponentDepth() {
        return this.maxComponentDepth;
    }

    public void setMaxComponentDepth(int i) {
        this.maxComponentDepth = i;
    }

    public int getMaxComponentElements() {
        return this.maxComponentElements;
    }

    public void setMaxComponentElements(int i) {
        this.maxComponentElements = i;
    }

    public int getMaxCompositeContours() {
        return this.maxCompositeContours;
    }

    public void setMaxCompositeContours(int i) {
        this.maxCompositeContours = i;
    }

    public int getMaxCompositePoints() {
        return this.maxCompositePoints;
    }

    public void setMaxCompositePoints(int i) {
        this.maxCompositePoints = i;
    }

    public int getMaxContours() {
        return this.maxContours;
    }

    public void setMaxContours(int i) {
        this.maxContours = i;
    }

    public int getMaxFunctionDefs() {
        return this.maxFunctionDefs;
    }

    public void setMaxFunctionDefs(int i) {
        this.maxFunctionDefs = i;
    }

    public int getMaxInstructionDefs() {
        return this.maxInstructionDefs;
    }

    public void setMaxInstructionDefs(int i) {
        this.maxInstructionDefs = i;
    }

    public int getMaxPoints() {
        return this.maxPoints;
    }

    public void setMaxPoints(int i) {
        this.maxPoints = i;
    }

    public int getMaxSizeOfInstructions() {
        return this.maxSizeOfInstructions;
    }

    public void setMaxSizeOfInstructions(int i) {
        this.maxSizeOfInstructions = i;
    }

    public int getMaxStackElements() {
        return this.maxStackElements;
    }

    public void setMaxStackElements(int i) {
        this.maxStackElements = i;
    }

    public int getMaxStorage() {
        return this.maxStorage;
    }

    public void setMaxStorage(int i) {
        this.maxStorage = i;
    }

    public int getMaxTwilightPoints() {
        return this.maxTwilightPoints;
    }

    public void setMaxTwilightPoints(int i) {
        this.maxTwilightPoints = i;
    }

    public int getMaxZones() {
        return this.maxZones;
    }

    public void setMaxZones(int i) {
        this.maxZones = i;
    }

    public int getNumGlyphs() {
        return this.numGlyphs;
    }

    public void setNumGlyphs(int i) {
        this.numGlyphs = i;
    }

    public float getVersion() {
        return this.version;
    }

    public void setVersion(float f) {
        this.version = f;
    }

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.version = tTFDataStream.read32Fixed();
        this.numGlyphs = tTFDataStream.readUnsignedShort();
        this.maxPoints = tTFDataStream.readUnsignedShort();
        this.maxContours = tTFDataStream.readUnsignedShort();
        this.maxCompositePoints = tTFDataStream.readUnsignedShort();
        this.maxCompositeContours = tTFDataStream.readUnsignedShort();
        this.maxZones = tTFDataStream.readUnsignedShort();
        this.maxTwilightPoints = tTFDataStream.readUnsignedShort();
        this.maxStorage = tTFDataStream.readUnsignedShort();
        this.maxFunctionDefs = tTFDataStream.readUnsignedShort();
        this.maxInstructionDefs = tTFDataStream.readUnsignedShort();
        this.maxStackElements = tTFDataStream.readUnsignedShort();
        this.maxSizeOfInstructions = tTFDataStream.readUnsignedShort();
        this.maxComponentElements = tTFDataStream.readUnsignedShort();
        this.maxComponentDepth = tTFDataStream.readUnsignedShort();
        this.initialized = true;
    }
}
