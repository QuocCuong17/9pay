package org.bouncycastle.crypto;

/* loaded from: classes2.dex */
public interface CharToByteConverter {
    byte[] convert(char[] cArr);

    String getType();
}
