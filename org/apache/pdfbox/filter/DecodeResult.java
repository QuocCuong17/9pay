package org.apache.pdfbox.filter;

import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public final class DecodeResult {
    public static final DecodeResult DEFAULT = new DecodeResult(new COSDictionary());
    private final COSDictionary parameters;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodeResult(COSDictionary cOSDictionary) {
        this.parameters = cOSDictionary;
    }

    public COSDictionary getParameters() {
        return this.parameters;
    }
}
