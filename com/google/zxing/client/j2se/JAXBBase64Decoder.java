package com.google.zxing.client.j2se;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
final class JAXBBase64Decoder extends Base64Decoder {
    @Override // com.google.zxing.client.j2se.Base64Decoder
    byte[] decode(String str) {
        try {
            return (byte[]) Class.forName("javax.xml.bind.DatatypeConverter").getMethod("parseBase64Binary", String.class).invoke(null, str);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
