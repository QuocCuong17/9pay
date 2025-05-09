package com.it_nomads.fluttersecurestorage.ciphers;

/* loaded from: classes4.dex */
public interface StorageCipher {
    byte[] decrypt(byte[] bArr) throws Exception;

    byte[] encrypt(byte[] bArr) throws Exception;
}
