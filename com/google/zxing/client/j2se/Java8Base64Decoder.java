package com.google.zxing.client.j2se;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
final class Java8Base64Decoder extends Base64Decoder {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.zxing.client.j2se.Base64Decoder
    public byte[] decode(String str) {
        try {
            return (byte[]) Class.forName("java.util.Base64.Decoder").getMethod("decode", String.class).invoke(Class.forName("java.util.Base64").getMethod("getDecoder", new Class[0]).invoke(null, new Object[0]), str);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
