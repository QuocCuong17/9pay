package com.beust.jcommander;

/* loaded from: classes2.dex */
public class ParameterException extends RuntimeException {
    public ParameterException(Throwable th) {
        super(th);
    }

    public ParameterException(String str) {
        super(str);
    }

    public ParameterException(String str, Throwable th) {
        super(str, th);
    }
}
