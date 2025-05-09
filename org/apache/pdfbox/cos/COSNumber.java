package org.apache.pdfbox.cos;

import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class COSNumber extends COSBase {

    @Deprecated
    public static final COSInteger ZERO = COSInteger.ZERO;

    @Deprecated
    public static final COSInteger ONE = COSInteger.ONE;

    public abstract double doubleValue();

    public abstract float floatValue();

    public abstract int intValue();

    public abstract long longValue();

    public static COSNumber get(String str) throws IOException {
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if ('0' <= charAt && charAt <= '9') {
                return COSInteger.get(charAt - '0');
            }
            if (charAt == '-' || charAt == '.') {
                return COSInteger.ZERO;
            }
            throw new IOException("Not a number: " + str);
        }
        if (str.indexOf(46) == -1 && str.toLowerCase().indexOf(101) == -1) {
            try {
                if (str.charAt(0) == '+') {
                    return COSInteger.get(Long.parseLong(str.substring(1)));
                }
                return COSInteger.get(Long.parseLong(str));
            } catch (NumberFormatException e) {
                throw new IOException("Value is not an integer: " + str, e);
            }
        }
        return new COSFloat(str);
    }
}
