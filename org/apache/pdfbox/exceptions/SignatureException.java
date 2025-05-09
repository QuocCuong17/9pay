package org.apache.pdfbox.exceptions;

/* loaded from: classes5.dex */
public class SignatureException extends Exception {
    public static final int CERT_PATH_CHECK_INVALID = 3;
    public static final int INVALID_PAGE_FOR_SIGNATURE = 5;
    public static final int NO_SUCH_ALGORITHM = 4;
    public static final int UNSUPPORTED_OPERATION = 2;
    public static final int VISUAL_SIGNATURE_INVALID = 6;
    public static final int WRONG_PASSWORD = 1;
    private int no;

    public SignatureException(String str) {
        super(str);
    }

    public SignatureException(int i, String str) {
        super(str);
        this.no = i;
    }

    public SignatureException(Throwable th) {
        super(th);
    }

    public SignatureException(int i, Throwable th) {
        super(th);
    }

    public int getErrNo() {
        return this.no;
    }
}
