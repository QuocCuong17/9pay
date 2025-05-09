package org.apache.pdfbox.exceptions;

/* loaded from: classes5.dex */
public class CryptographyException extends Exception {
    private Exception embedded;

    public CryptographyException(String str) {
        super(str);
    }

    public CryptographyException(Exception exc) {
        super(exc.getMessage());
        setEmbedded(exc);
    }

    public Exception getEmbedded() {
        return this.embedded;
    }

    private void setEmbedded(Exception exc) {
        this.embedded = exc;
    }
}
