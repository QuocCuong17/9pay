package com.google.zxing.client.j2se;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class Base64Decoder {
    private static final Base64Decoder INSTANCE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] decode(String str);

    static {
        Base64Decoder jAXBBase64Decoder;
        try {
            Class.forName("java.util.Base64");
            jAXBBase64Decoder = new Java8Base64Decoder();
        } catch (ClassNotFoundException unused) {
            jAXBBase64Decoder = new JAXBBase64Decoder();
        }
        INSTANCE = jAXBBase64Decoder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Base64Decoder getInstance() {
        return INSTANCE;
    }
}
