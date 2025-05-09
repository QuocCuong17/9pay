package org.jmrtd.cbeff;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ComplexCBEFFInfo implements CBEFFInfo {
    private List<CBEFFInfo> subRecords = new ArrayList();

    public List<CBEFFInfo> getSubRecords() {
        return new ArrayList(this.subRecords);
    }

    public void add(CBEFFInfo cBEFFInfo) {
        this.subRecords.add(cBEFFInfo);
    }

    public void addAll(List<CBEFFInfo> list) {
        this.subRecords.addAll(list);
    }

    public void remove(int i) {
        this.subRecords.remove(i);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass().equals(ComplexCBEFFInfo.class)) {
            return this.subRecords.equals(((ComplexCBEFFInfo) obj).getSubRecords());
        }
        return false;
    }

    public int hashCode() {
        return (this.subRecords.hashCode() * 7) + 11;
    }
}
