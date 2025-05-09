package com.github.jaiimageio.stream;

/* compiled from: SegmentedImageInputStream.java */
/* loaded from: classes3.dex */
class SectorStreamSegmentMapper implements StreamSegmentMapper {
    int lastSegmentLength;
    int segmentLength;
    long[] segmentPositions;
    int totalLength;

    public SectorStreamSegmentMapper(long[] jArr, int i, int i2) {
        this.segmentPositions = (long[]) jArr.clone();
        this.segmentLength = i;
        this.totalLength = i2;
        this.lastSegmentLength = i2 - ((jArr.length - 1) * i);
    }

    @Override // com.github.jaiimageio.stream.StreamSegmentMapper
    public StreamSegment getStreamSegment(long j, int i) {
        int i2 = (int) (j / this.segmentLength);
        long[] jArr = this.segmentPositions;
        long j2 = j - (r0 * i2);
        int i3 = (int) ((i2 == jArr.length + (-1) ? this.lastSegmentLength : r0) - j2);
        if (i3 <= i) {
            i = i3;
        }
        return new StreamSegment(jArr[i2] + j2, i);
    }

    @Override // com.github.jaiimageio.stream.StreamSegmentMapper
    public void getStreamSegment(long j, int i, StreamSegment streamSegment) {
        int i2 = (int) (j / this.segmentLength);
        long[] jArr = this.segmentPositions;
        long j2 = j - (r0 * i2);
        int i3 = (int) ((i2 == jArr.length + (-1) ? this.lastSegmentLength : r0) - j2);
        if (i3 <= i) {
            i = i3;
        }
        streamSegment.setStartPos(jArr[i2] + j2);
        streamSegment.setSegmentLength(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long length() {
        return this.totalLength;
    }
}
