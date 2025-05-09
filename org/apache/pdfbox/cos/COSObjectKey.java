package org.apache.pdfbox.cos;

/* loaded from: classes5.dex */
public class COSObjectKey implements Comparable<COSObjectKey> {
    private final int generation;
    private final long number;

    public COSObjectKey(COSObject cOSObject) {
        this(cOSObject.getObjectNumber(), cOSObject.getGenerationNumber());
    }

    public COSObjectKey(long j, int i) {
        this.number = j;
        this.generation = i;
    }

    public boolean equals(Object obj) {
        COSObjectKey cOSObjectKey = obj instanceof COSObjectKey ? (COSObjectKey) obj : null;
        return cOSObjectKey != null && cOSObjectKey.getNumber() == getNumber() && cOSObjectKey.getGeneration() == getGeneration();
    }

    public int getGeneration() {
        return this.generation;
    }

    public long getNumber() {
        return this.number;
    }

    public int hashCode() {
        return Long.valueOf(this.number + this.generation).hashCode();
    }

    public String toString() {
        return Long.toString(this.number) + " " + Integer.toString(this.generation) + " R";
    }

    @Override // java.lang.Comparable
    public int compareTo(COSObjectKey cOSObjectKey) {
        if (getNumber() < cOSObjectKey.getNumber()) {
            return -1;
        }
        if (getNumber() > cOSObjectKey.getNumber()) {
            return 1;
        }
        if (getGeneration() < cOSObjectKey.getGeneration()) {
            return -1;
        }
        return getGeneration() > cOSObjectKey.getGeneration() ? 1 : 0;
    }
}
