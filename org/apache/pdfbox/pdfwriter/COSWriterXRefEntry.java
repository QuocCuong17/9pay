package org.apache.pdfbox.pdfwriter;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSObjectKey;

/* loaded from: classes5.dex */
public class COSWriterXRefEntry implements Comparable<COSWriterXRefEntry> {
    private static final COSWriterXRefEntry NULLENTRY;
    private boolean free = false;
    private COSObjectKey key;
    private COSBase object;
    private long offset;

    static {
        COSWriterXRefEntry cOSWriterXRefEntry = new COSWriterXRefEntry(0L, null, new COSObjectKey(0L, 65535));
        NULLENTRY = cOSWriterXRefEntry;
        cOSWriterXRefEntry.setFree(true);
    }

    @Override // java.lang.Comparable
    public int compareTo(COSWriterXRefEntry cOSWriterXRefEntry) {
        if (cOSWriterXRefEntry == null || getKey().getNumber() < cOSWriterXRefEntry.getKey().getNumber()) {
            return -1;
        }
        return getKey().getNumber() > cOSWriterXRefEntry.getKey().getNumber() ? 1 : 0;
    }

    public static COSWriterXRefEntry getNullEntry() {
        return NULLENTRY;
    }

    public COSObjectKey getKey() {
        return this.key;
    }

    public long getOffset() {
        return this.offset;
    }

    public boolean isFree() {
        return this.free;
    }

    public void setFree(boolean z) {
        this.free = z;
    }

    private void setKey(COSObjectKey cOSObjectKey) {
        this.key = cOSObjectKey;
    }

    public final void setOffset(long j) {
        this.offset = j;
    }

    public COSWriterXRefEntry(long j, COSBase cOSBase, COSObjectKey cOSObjectKey) {
        setOffset(j);
        setObject(cOSBase);
        setKey(cOSObjectKey);
    }

    public COSBase getObject() {
        return this.object;
    }

    private void setObject(COSBase cOSBase) {
        this.object = cOSBase;
    }
}
