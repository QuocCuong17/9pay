package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class GlyfDescript implements GlyphDescription {
    public static final byte ON_CURVE = 1;
    public static final byte REPEAT = 8;
    public static final byte X_DUAL = 16;
    public static final byte X_SHORT_VECTOR = 2;
    public static final byte Y_DUAL = 32;
    public static final byte Y_SHORT_VECTOR = 4;
    private final int contourCount;
    private int[] instructions;

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public void resolve() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GlyfDescript(short s, TTFDataStream tTFDataStream) throws IOException {
        this.contourCount = s;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getContourCount() {
        return this.contourCount;
    }

    public int[] getInstructions() {
        return this.instructions;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void readInstructions(TTFDataStream tTFDataStream, int i) throws IOException {
        this.instructions = tTFDataStream.readUnsignedByteArray(i);
    }
}
