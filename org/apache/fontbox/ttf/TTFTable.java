package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class TTFTable {
    private long checkSum;
    protected boolean initialized = false;
    private long length;
    private long offset;
    private String tag;

    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
    }

    public long getCheckSum() {
        return this.checkSum;
    }

    public void setCheckSum(long j) {
        this.checkSum = j;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long j) {
        this.length = j;
    }

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public boolean getInitialized() {
        return this.initialized;
    }
}
