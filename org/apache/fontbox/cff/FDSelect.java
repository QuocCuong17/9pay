package org.apache.fontbox.cff;

/* loaded from: classes5.dex */
public abstract class FDSelect {
    protected final CFFCIDFont owner;

    public abstract int getFDIndex(int i);

    public FDSelect(CFFCIDFont cFFCIDFont) {
        this.owner = cFFCIDFont;
    }
}
