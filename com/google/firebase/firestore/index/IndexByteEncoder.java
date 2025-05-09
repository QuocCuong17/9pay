package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.FieldIndex;
import com.google.protobuf.ByteString;

/* loaded from: classes4.dex */
public class IndexByteEncoder {
    private final OrderedCodeWriter orderedCode = new OrderedCodeWriter();
    private final AscendingIndexByteEncoder ascending = new AscendingIndexByteEncoder();
    private final DescendingIndexByteEncoder descending = new DescendingIndexByteEncoder();

    /* loaded from: classes4.dex */
    class AscendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        AscendingIndexByteEncoder() {
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeBytes(ByteString byteString) {
            IndexByteEncoder.this.orderedCode.writeBytesAscending(byteString);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeString(String str) {
            IndexByteEncoder.this.orderedCode.writeUtf8Ascending(str);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeLong(long j) {
            IndexByteEncoder.this.orderedCode.writeSignedLongAscending(j);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeDouble(double d) {
            IndexByteEncoder.this.orderedCode.writeDoubleAscending(d);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityAscending();
        }
    }

    /* loaded from: classes4.dex */
    class DescendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        DescendingIndexByteEncoder() {
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeBytes(ByteString byteString) {
            IndexByteEncoder.this.orderedCode.writeBytesDescending(byteString);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeString(String str) {
            IndexByteEncoder.this.orderedCode.writeUtf8Descending(str);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeLong(long j) {
            IndexByteEncoder.this.orderedCode.writeSignedLongDescending(j);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeDouble(double d) {
            IndexByteEncoder.this.orderedCode.writeDoubleDescending(d);
        }

        @Override // com.google.firebase.firestore.index.DirectionalIndexByteEncoder
        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityDescending();
        }
    }

    public void seed(byte[] bArr) {
        this.orderedCode.seed(bArr);
    }

    public DirectionalIndexByteEncoder forKind(FieldIndex.Segment.Kind kind) {
        if (kind.equals(FieldIndex.Segment.Kind.DESCENDING)) {
            return this.descending;
        }
        return this.ascending;
    }

    public byte[] getEncodedBytes() {
        return this.orderedCode.encodedBytes();
    }

    public void reset() {
        this.orderedCode.reset();
    }
}
