package com.github.jaiimageio.stream;

/* compiled from: SegmentedImageInputStream.java */
/* loaded from: classes3.dex */
class StreamSegmentMapperImpl implements StreamSegmentMapper {
    private int[] segmentLengths;
    private long[] segmentPositions;

    public StreamSegmentMapperImpl(long[] jArr, int[] iArr) {
        this.segmentPositions = (long[]) jArr.clone();
        this.segmentLengths = (int[]) iArr.clone();
    }

    @Override // com.github.jaiimageio.stream.StreamSegmentMapper
    public StreamSegment getStreamSegment(long j, int i) {
        int length = this.segmentLengths.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = this.segmentLengths[i2];
            long j2 = i3;
            if (j < j2) {
                return new StreamSegment(this.segmentPositions[i2] + j, Math.min(i3 - ((int) j), i));
            }
            j -= j2;
        }
        return null;
    }

    @Override // com.github.jaiimageio.stream.StreamSegmentMapper
    public void getStreamSegment(long j, int i, StreamSegment streamSegment) {
        int length = this.segmentLengths.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = this.segmentLengths[i2];
            long j2 = i3;
            if (j < j2) {
                streamSegment.setStartPos(this.segmentPositions[i2] + j);
                streamSegment.setSegmentLength(Math.min(i3 - ((int) j), i));
                return;
            }
            j -= j2;
        }
        streamSegment.setStartPos(-1L);
        streamSegment.setSegmentLength(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long length() {
        long j = 0;
        for (int i = 0; i < this.segmentLengths.length; i++) {
            j += this.segmentLengths[i];
        }
        return j;
    }
}
