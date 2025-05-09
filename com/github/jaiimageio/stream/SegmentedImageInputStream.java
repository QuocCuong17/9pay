package com.github.jaiimageio.stream;

import java.io.IOException;
import java.util.Objects;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageInputStreamImpl;

/* loaded from: classes3.dex */
public class SegmentedImageInputStream extends ImageInputStreamImpl {
    private StreamSegmentMapper mapper;
    private ImageInputStream stream;
    private StreamSegment streamSegment;

    public SegmentedImageInputStream(ImageInputStream imageInputStream, StreamSegmentMapper streamSegmentMapper) {
        this.streamSegment = new StreamSegment();
        this.stream = imageInputStream;
        this.mapper = streamSegmentMapper;
    }

    public SegmentedImageInputStream(ImageInputStream imageInputStream, long[] jArr, int[] iArr) {
        this(imageInputStream, new StreamSegmentMapperImpl(jArr, iArr));
    }

    public SegmentedImageInputStream(ImageInputStream imageInputStream, long[] jArr, int i, int i2) {
        this(imageInputStream, new SectorStreamSegmentMapper(jArr, i, i2));
    }

    public int read() throws IOException {
        this.mapper.getStreamSegment(this.streamPos, 1, this.streamSegment);
        if (this.streamSegment.getSegmentLength() < 0) {
            return -1;
        }
        this.stream.seek(this.streamSegment.getStartPos());
        int read = this.stream.read();
        this.streamPos++;
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        Objects.requireNonNull(bArr);
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        this.mapper.getStreamSegment(this.streamPos, i2, this.streamSegment);
        int segmentLength = this.streamSegment.getSegmentLength();
        if (segmentLength < 0) {
            return -1;
        }
        this.stream.seek(this.streamSegment.getStartPos());
        int read = this.stream.read(bArr, i, segmentLength);
        this.streamPos += read;
        return read;
    }

    public long length() {
        StreamSegmentMapper streamSegmentMapper = this.mapper;
        if (streamSegmentMapper instanceof StreamSegmentMapperImpl) {
            return ((StreamSegmentMapperImpl) streamSegmentMapper).length();
        }
        if (streamSegmentMapper instanceof SectorStreamSegmentMapper) {
            return ((SectorStreamSegmentMapper) streamSegmentMapper).length();
        }
        if (streamSegmentMapper != null) {
            StreamSegment streamSegment = streamSegmentMapper.getStreamSegment(0L, Integer.MAX_VALUE);
            long j = 0;
            while (true) {
                long segmentLength = streamSegment.getSegmentLength();
                if (segmentLength <= 0) {
                    return j;
                }
                j += segmentLength;
                streamSegment.setSegmentLength(0);
                this.mapper.getStreamSegment(j, Integer.MAX_VALUE, streamSegment);
            }
        } else {
            return super.length();
        }
    }
}
