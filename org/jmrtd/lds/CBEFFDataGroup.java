package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.tlv.TLVOutputStream;
import org.jmrtd.cbeff.BiometricDataBlock;

/* loaded from: classes6.dex */
public abstract class CBEFFDataGroup<R extends BiometricDataBlock> extends DataGroup {
    protected static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 2702959939408371946L;
    private Random random;
    private List<R> subRecords;

    /* JADX INFO: Access modifiers changed from: protected */
    public CBEFFDataGroup(int i, List<R> list) {
        super(i);
        addAll(list);
        this.random = new Random();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CBEFFDataGroup(int i, InputStream inputStream) throws IOException {
        super(i, inputStream);
        this.random = new Random();
    }

    public void add(R r) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.add(r);
    }

    public void addAll(List<R> list) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.addAll(list);
    }

    public void remove(int i) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.remove(i);
    }

    @Override // org.jmrtd.lds.DataGroup, org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CBEFFDataGroup [");
        List<R> list = this.subRecords;
        if (list == null) {
            sb.append("null");
        } else {
            boolean z = true;
            Iterator<R> it = list.iterator();
            while (it.hasNext()) {
                R next = it.next();
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                sb.append(next == null ? "null" : next.toString());
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public List<R> getSubRecords() {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        return new ArrayList(this.subRecords);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CBEFFDataGroup)) {
            return false;
        }
        try {
            List<R> subRecords = getSubRecords();
            List<R> subRecords2 = ((CBEFFDataGroup) obj).getSubRecords();
            int size = subRecords.size();
            if (size != subRecords2.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                R r = subRecords.get(i);
                R r2 = subRecords2.get(i);
                if (r == null) {
                    if (r2 != null) {
                        return false;
                    }
                } else if (!r.equals(r2)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            LOGGER.log(Level.WARNING, "Wrong class", (Throwable) e);
            return false;
        }
    }

    public int hashCode() {
        Iterator<R> it = getSubRecords().iterator();
        int i = 1234567891;
        while (it.hasNext()) {
            R next = it.next();
            i = next == null ? (i * 3) + 5 : ((i + next.hashCode()) * 5) + 7;
        }
        return (i * 7) + 11;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeOptionalRandomData(OutputStream outputStream) throws IOException {
        if (this.subRecords.isEmpty()) {
            TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
            tLVOutputStream.writeTag(83);
            byte[] bArr = new byte[8];
            this.random.nextBytes(bArr);
            tLVOutputStream.writeValue(bArr);
        }
    }
}
