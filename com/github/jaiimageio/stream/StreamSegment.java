package com.github.jaiimageio.stream;

/* loaded from: classes3.dex */
public class StreamSegment {
    private int segmentLength;
    private long startPos;

    public StreamSegment() {
        this.startPos = 0L;
        this.segmentLength = 0;
    }

    public StreamSegment(long j, int i) {
        this.startPos = 0L;
        this.segmentLength = 0;
        this.startPos = j;
        this.segmentLength = i;
    }

    public final long getStartPos() {
        return this.startPos;
    }

    public final void setStartPos(long j) {
        this.startPos = j;
    }

    public final int getSegmentLength() {
        return this.segmentLength;
    }

    public final void setSegmentLength(int i) {
        this.segmentLength = i;
    }
}
