package org.jmrtd.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class FragmentBuffer implements Serializable {
    private static final int DEFAULT_SIZE = 2000;
    private static final long serialVersionUID = -3510872461790499721L;
    private byte[] buffer;
    private Collection<Fragment> fragments;

    public FragmentBuffer() {
        this(2000);
    }

    public FragmentBuffer(int i) {
        this.buffer = new byte[i];
        this.fragments = new HashSet();
    }

    public synchronized void updateFrom(FragmentBuffer fragmentBuffer) {
        for (Fragment fragment : fragmentBuffer.fragments) {
            addFragment(fragment.offset, fragmentBuffer.buffer, fragment.offset, fragment.length);
        }
    }

    public synchronized void addFragment(int i, byte b) {
        addFragment(i, new byte[]{b});
    }

    public synchronized void addFragment(int i, byte[] bArr) {
        addFragment(i, bArr, 0, bArr.length);
    }

    public synchronized void addFragment(int i, byte[] bArr, int i2, int i3) {
        int i4 = i + i3;
        byte[] bArr2 = this.buffer;
        if (i4 > bArr2.length) {
            setLength(Math.max(i4, bArr2.length) * 2);
        }
        System.arraycopy(bArr, i2, this.buffer, i, i3);
        for (Fragment fragment : new ArrayList(this.fragments)) {
            if (fragment.getOffset() <= i && i + i3 <= fragment.getOffset() + fragment.getLength()) {
                return;
            }
            if (fragment.getOffset() <= i && i <= fragment.getOffset() + fragment.getLength()) {
                int offset = (i + i3) - fragment.getOffset();
                int offset2 = fragment.getOffset();
                this.fragments.remove(fragment);
                i3 = offset;
                i = offset2;
            } else if (i <= fragment.getOffset() && fragment.getOffset() + fragment.getLength() <= i + i3) {
                this.fragments.remove(fragment);
            } else if (i <= fragment.getOffset() && fragment.getOffset() <= i + i3) {
                i3 = (fragment.getOffset() + fragment.getLength()) - i;
                this.fragments.remove(fragment);
            }
        }
        this.fragments.add(Fragment.getInstance(i, i3));
    }

    public synchronized int getPosition() {
        int i;
        i = 0;
        for (int i2 = 0; i2 < this.buffer.length; i2++) {
            if (isCoveredByFragment(i2)) {
                i = i2 + 1;
            }
        }
        return i;
    }

    public synchronized int getBytesBuffered() {
        int i;
        i = 0;
        for (int i2 = 0; i2 < this.buffer.length; i2++) {
            if (isCoveredByFragment(i2)) {
                i++;
            }
        }
        return i;
    }

    public synchronized boolean isCoveredByFragment(int i) {
        return isCoveredByFragment(i, 1);
    }

    public synchronized boolean isCoveredByFragment(int i, int i2) {
        for (Fragment fragment : this.fragments) {
            int offset = fragment.getOffset();
            int offset2 = fragment.getOffset() + fragment.getLength();
            if (offset <= i && i + i2 <= offset2) {
                return true;
            }
        }
        return false;
    }

    public synchronized int getBufferedLength(int i) {
        int i2;
        int i3 = 0;
        if (i >= this.buffer.length) {
            return 0;
        }
        for (Fragment fragment : this.fragments) {
            int offset = fragment.getOffset();
            int offset2 = fragment.getOffset() + fragment.getLength();
            if (offset <= i && i < offset2 && (i2 = offset2 - i) > i3) {
                i3 = i2;
            }
        }
        return i3;
    }

    public Collection<Fragment> getFragments() {
        return this.fragments;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public int getLength() {
        int length;
        synchronized (this) {
            length = this.buffer.length;
        }
        return length;
    }

    public synchronized Fragment getSmallestUnbufferedFragment(int i, int i2) {
        int i3;
        Iterator<Fragment> it = this.fragments.iterator();
        i3 = i;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Fragment next = it.next();
            if (next.getOffset() <= i3 && i3 + i2 <= next.getOffset() + next.getLength()) {
                i2 = 0;
                break;
            }
            if (next.getOffset() <= i3 && i3 < next.getOffset() + next.getLength()) {
                int offset = next.getOffset() + next.getLength();
                i2 = (i3 + i2) - offset;
                i3 = offset;
            } else if (i3 > next.getOffset() || next.getOffset() + next.getLength() > i3 + i2) {
                if (i <= next.getOffset() && next.getOffset() < i3 + i2) {
                    i2 = next.getOffset() - i3;
                }
            }
        }
        return Fragment.getInstance(i3, i2);
    }

    public synchronized String toString() {
        return "FragmentBuffer [" + this.buffer.length + ", " + this.fragments + "]";
    }

    public synchronized boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(FragmentBuffer.class)) {
            return false;
        }
        FragmentBuffer fragmentBuffer = (FragmentBuffer) obj;
        byte[] bArr = fragmentBuffer.buffer;
        if (bArr == null && this.buffer != null) {
            return false;
        }
        if (bArr != null && this.buffer == null) {
            return false;
        }
        Collection<Fragment> collection = fragmentBuffer.fragments;
        if (collection == null && this.fragments != null) {
            return false;
        }
        if (collection != null && this.fragments == null) {
            return false;
        }
        if (Arrays.equals(bArr, this.buffer)) {
            if (fragmentBuffer.fragments.equals(this.fragments)) {
                z = true;
            }
        }
        return z;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.buffer) * 3) + (this.fragments.hashCode() * 2) + 7;
    }

    private void setLength(int i) {
        synchronized (this) {
            byte[] bArr = this.buffer;
            if (i <= bArr.length) {
                return;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.buffer = bArr2;
        }
    }

    /* loaded from: classes6.dex */
    public static class Fragment implements Serializable {
        private static final long serialVersionUID = -3795931618553980328L;
        private int length;
        private int offset;

        private Fragment(int i, int i2) {
            this.offset = i;
            this.length = i2;
        }

        public static Fragment getInstance(int i, int i2) {
            return new Fragment(i, i2);
        }

        public int getOffset() {
            return this.offset;
        }

        public int getLength() {
            return this.length;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(this.offset);
            sb.append(" .. ");
            sb.append((this.offset + this.length) - 1);
            sb.append(" (");
            sb.append(this.length);
            sb.append(")]");
            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!obj.getClass().equals(Fragment.class)) {
                return false;
            }
            Fragment fragment = (Fragment) obj;
            return fragment.offset == this.offset && fragment.length == this.length;
        }

        public int hashCode() {
            return (this.offset * 2) + (this.length * 3) + 5;
        }
    }
}
