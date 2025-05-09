package org.apache.pdfbox.filter.ccitt;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public final class CCITTFaxG31DDecodeInputStream extends InputStream {
    private static final int[] BIT_POS_MASKS;
    private static final NonLeafLookupTreeNode BLACK_LOOKUP_TREE_ROOT;
    private static final int CODE_WORD = 0;
    private static final short EOL_STARTER = 2816;
    private static final int SIGNAL_EOD = -1;
    private static final int SIGNAL_EOL = -2;
    private static final NonLeafLookupTreeNode WHITE_LOOKUP_TREE_ROOT;
    private int accumulatedRunLength;
    private int bitPos;
    private int bits;
    private int columns;
    private PackedBitArray decodedLine;
    private int decodedReadPos;
    private int decodedWritePos;
    private boolean encodedByteAlign;
    private int rows;
    private InputStream source;
    private int y;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface CodeWord {
        int execute(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException;

        int getType();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    static {
        WHITE_LOOKUP_TREE_ROOT = new NonLeafLookupTreeNode();
        BLACK_LOOKUP_TREE_ROOT = new NonLeafLookupTreeNode();
        buildLookupTree();
        BIT_POS_MASKS = new int[]{128, 64, 32, 16, 8, 4, 2, 1};
    }

    public CCITTFaxG31DDecodeInputStream(InputStream inputStream, int i, int i2, boolean z) {
        this.bitPos = 8;
        this.y = -1;
        this.source = inputStream;
        this.columns = i;
        this.rows = i2;
        PackedBitArray packedBitArray = new PackedBitArray(i);
        this.decodedLine = packedBitArray;
        this.decodedReadPos = packedBitArray.getByteCount();
        this.encodedByteAlign = z;
    }

    public CCITTFaxG31DDecodeInputStream(InputStream inputStream, int i, boolean z) {
        this(inputStream, i, 0, z);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.decodedReadPos >= this.decodedLine.getByteCount() && !decodeLine()) {
            return -1;
        }
        byte[] data = this.decodedLine.getData();
        int i = this.decodedReadPos;
        this.decodedReadPos = i + 1;
        return data[i] & 255;
    }

    private boolean decodeLine() throws IOException {
        if (this.encodedByteAlign && this.bitPos != 0) {
            readByte();
        }
        if (this.bits < 0) {
            return false;
        }
        int i = this.y + 1;
        this.y = i;
        int i2 = this.rows;
        if (i2 > 0 && i >= i2) {
            return false;
        }
        this.decodedLine.clear();
        this.decodedWritePos = 0;
        int i3 = 6;
        int i4 = 0;
        boolean z = true;
        while (true) {
            if (i4 < this.columns || this.accumulatedRunLength > 0) {
                CodeWord nextCodeWord = (z ? WHITE_LOOKUP_TREE_ROOT : BLACK_LOOKUP_TREE_ROOT).getNextCodeWord(this);
                if (nextCodeWord == null) {
                    if (i4 <= 0) {
                        return false;
                    }
                    this.decodedReadPos = 0;
                    return true;
                }
                if (nextCodeWord.getType() == -2) {
                    i3--;
                    if (i3 == 0) {
                        return false;
                    }
                } else {
                    i4 += nextCodeWord.execute(this);
                    if (this.accumulatedRunLength == 0) {
                        z = !z;
                    }
                    i3 = -1;
                }
            } else {
                this.decodedReadPos = 0;
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeRun(int i, int i2) {
        int i3 = this.accumulatedRunLength + i2;
        this.accumulatedRunLength = i3;
        if (i != 0) {
            this.decodedLine.setBits(this.decodedWritePos, i3);
        }
        this.decodedWritePos += this.accumulatedRunLength;
        this.accumulatedRunLength = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeNonTerminating(int i) {
        this.accumulatedRunLength += i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int readBit() throws IOException {
        if (this.bitPos >= 8) {
            readByte();
            if (this.bits < 0) {
                return -1;
            }
        }
        int i = this.bits;
        int[] iArr = BIT_POS_MASKS;
        int i2 = this.bitPos;
        this.bitPos = i2 + 1;
        return (i & iArr[i2]) == 0 ? 0 : 1;
    }

    private void readByte() throws IOException {
        this.bits = this.source.read();
        this.bitPos = 0;
    }

    private static void buildLookupTree() {
        short[] sArr = CCITTFaxConstants.WHITE_TERMINATING;
        NonLeafLookupTreeNode nonLeafLookupTreeNode = WHITE_LOOKUP_TREE_ROOT;
        buildUpTerminating(sArr, nonLeafLookupTreeNode, true);
        short[] sArr2 = CCITTFaxConstants.BLACK_TERMINATING;
        NonLeafLookupTreeNode nonLeafLookupTreeNode2 = BLACK_LOOKUP_TREE_ROOT;
        buildUpTerminating(sArr2, nonLeafLookupTreeNode2, false);
        buildUpMakeUp(CCITTFaxConstants.WHITE_MAKE_UP, nonLeafLookupTreeNode);
        buildUpMakeUp(CCITTFaxConstants.BLACK_MAKE_UP, nonLeafLookupTreeNode2);
        buildUpMakeUpLong(CCITTFaxConstants.LONG_MAKE_UP, nonLeafLookupTreeNode);
        buildUpMakeUpLong(CCITTFaxConstants.LONG_MAKE_UP, nonLeafLookupTreeNode2);
        EndOfLineTreeNode endOfLineTreeNode = new EndOfLineTreeNode();
        addLookupTreeNode(EOL_STARTER, nonLeafLookupTreeNode, endOfLineTreeNode);
        addLookupTreeNode(EOL_STARTER, nonLeafLookupTreeNode2, endOfLineTreeNode);
    }

    private static void buildUpTerminating(short[] sArr, NonLeafLookupTreeNode nonLeafLookupTreeNode, boolean z) {
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            addLookupTreeNode(sArr[i], nonLeafLookupTreeNode, new RunLengthTreeNode(!z ? 1 : 0, i));
        }
    }

    private static void buildUpMakeUp(short[] sArr, NonLeafLookupTreeNode nonLeafLookupTreeNode) {
        int length = sArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            addLookupTreeNode(sArr[i], nonLeafLookupTreeNode, new MakeUpTreeNode(i2 * 64));
            i = i2;
        }
    }

    private static void buildUpMakeUpLong(short[] sArr, NonLeafLookupTreeNode nonLeafLookupTreeNode) {
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            addLookupTreeNode(sArr[i], nonLeafLookupTreeNode, new MakeUpTreeNode((i + 28) * 64));
        }
    }

    private static void addLookupTreeNode(short s, NonLeafLookupTreeNode nonLeafLookupTreeNode, LookupTreeNode lookupTreeNode) {
        int i = s >> 8;
        int i2 = s & 255;
        for (int i3 = i - 1; i3 > 0; i3--) {
            int i4 = (i2 >> i3) & 1;
            LookupTreeNode lookupTreeNode2 = nonLeafLookupTreeNode.get(i4);
            if (lookupTreeNode2 == null) {
                lookupTreeNode2 = new NonLeafLookupTreeNode();
                nonLeafLookupTreeNode.set(i4, lookupTreeNode2);
            }
            if (lookupTreeNode2 instanceof NonLeafLookupTreeNode) {
                nonLeafLookupTreeNode = lookupTreeNode2;
            } else {
                throw new IllegalStateException("NonLeafLookupTreeNode expected, was " + lookupTreeNode2.getClass().getName());
            }
        }
        int i5 = i2 & 1;
        if (nonLeafLookupTreeNode.get(i5) != null) {
            throw new IllegalStateException("Two codes conflicting in lookup tree");
        }
        nonLeafLookupTreeNode.set(i5, lookupTreeNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static abstract class LookupTreeNode {
        public abstract CodeWord getNextCodeWord(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException;

        private LookupTreeNode() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class NonLeafLookupTreeNode extends LookupTreeNode {
        private LookupTreeNode one;
        private LookupTreeNode zero;

        private NonLeafLookupTreeNode() {
            super();
        }

        public void set(int i, LookupTreeNode lookupTreeNode) {
            if (i == 0) {
                this.zero = lookupTreeNode;
            } else {
                this.one = lookupTreeNode;
            }
        }

        public LookupTreeNode get(int i) {
            return i == 0 ? this.zero : this.one;
        }

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.LookupTreeNode
        public CodeWord getNextCodeWord(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException {
            int readBit = cCITTFaxG31DDecodeInputStream.readBit();
            if (readBit < 0) {
                return null;
            }
            LookupTreeNode lookupTreeNode = get(readBit);
            if (lookupTreeNode != null) {
                return lookupTreeNode.getNextCodeWord(cCITTFaxG31DDecodeInputStream);
            }
            throw new IOException("Invalid code word encountered");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class RunLengthTreeNode extends LookupTreeNode implements CodeWord {
        private final int bit;
        private final int length;

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.LookupTreeNode
        public CodeWord getNextCodeWord(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException {
            return this;
        }

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.CodeWord
        public int getType() {
            return 0;
        }

        RunLengthTreeNode(int i, int i2) {
            super();
            this.bit = i;
            this.length = i2;
        }

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.CodeWord
        public int execute(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) {
            cCITTFaxG31DDecodeInputStream.writeRun(this.bit, this.length);
            return this.length;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Run Length for ");
            sb.append(this.length);
            sb.append(" bits of ");
            sb.append(this.bit == 0 ? "white" : "black");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class MakeUpTreeNode extends LookupTreeNode implements CodeWord {
        private final int length;

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.LookupTreeNode
        public CodeWord getNextCodeWord(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException {
            return this;
        }

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.CodeWord
        public int getType() {
            return 0;
        }

        MakeUpTreeNode(int i) {
            super();
            this.length = i;
        }

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.CodeWord
        public int execute(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException {
            cCITTFaxG31DDecodeInputStream.writeNonTerminating(this.length);
            return this.length;
        }

        public String toString() {
            return "Make up code for length " + this.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class EndOfLineTreeNode extends LookupTreeNode implements CodeWord {
        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.CodeWord
        public int execute(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException {
            return 0;
        }

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.CodeWord
        public int getType() {
            return -2;
        }

        public String toString() {
            return "EOL";
        }

        private EndOfLineTreeNode() {
            super();
        }

        @Override // org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream.LookupTreeNode
        public CodeWord getNextCodeWord(CCITTFaxG31DDecodeInputStream cCITTFaxG31DDecodeInputStream) throws IOException {
            int readBit;
            do {
                readBit = cCITTFaxG31DDecodeInputStream.readBit();
            } while (readBit == 0);
            if (readBit < 0) {
                return null;
            }
            return this;
        }
    }
}
