package org.ejbca.cvc.exception;

/* loaded from: classes6.dex */
public class ParseException extends CvcException {
    private static final long serialVersionUID = 1;

    public ParseException() {
    }

    public ParseException(String str) {
        super(str);
    }

    public ParseException(Throwable th) {
        super(th);
    }

    public ParseException(String str, Throwable th) {
        super(str, th);
    }
}
