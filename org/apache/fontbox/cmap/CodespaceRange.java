package org.apache.fontbox.cmap;

import java.util.List;

/* loaded from: classes5.dex */
public class CodespaceRange {
    private byte[] end;
    private byte[] start;

    public byte[] getEnd() {
        return this.end;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEnd(byte[] bArr) {
        this.end = bArr;
    }

    public byte[] getStart() {
        return this.start;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStart(byte[] bArr) {
        this.start = bArr;
    }

    public boolean matches(byte[] bArr) {
        if (bArr.length < this.start.length || bArr.length > this.end.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            int i2 = this.start[i] & 255;
            int i3 = this.end[i] & 255;
            int i4 = bArr[i] & 255;
            if (i4 > i3 || i4 < i2) {
                return false;
            }
        }
        return true;
    }

    public boolean isFullMatch(List<Byte> list) {
        if (list.size() < this.start.length || list.size() > this.end.length) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            int i2 = this.start[i] & 255;
            int i3 = this.end[i] & 255;
            int byteValue = list.get(i).byteValue() & 255;
            if (byteValue > i3 || byteValue < i2) {
                return false;
            }
        }
        return true;
    }

    public boolean isPartialMatch(byte b, int i) {
        int i2 = b & 255;
        return i2 <= (this.end[i] & 255) && i2 >= (this.start[i] & 255);
    }
}
