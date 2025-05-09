package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes6.dex */
public abstract class AbstractListInfo<R extends Serializable> extends AbstractLDSInfo {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 2970076896364365191L;
    private List<R> subRecords;

    public abstract void readObject(InputStream inputStream) throws IOException;

    @Override // org.jmrtd.lds.AbstractLDSInfo
    public abstract void writeObject(OutputStream outputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public List<R> getSubRecords() {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        return new ArrayList(this.subRecords);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void add(R r) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.add(r);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addAll(List<R> list) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.addAll(list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void remove(int i) {
        if (this.subRecords == null) {
            this.subRecords = new ArrayList();
        }
        this.subRecords.remove(i);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractListInfo)) {
            return false;
        }
        try {
            List<R> subRecords = getSubRecords();
            List<R> subRecords2 = ((AbstractListInfo) obj).getSubRecords();
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
}
