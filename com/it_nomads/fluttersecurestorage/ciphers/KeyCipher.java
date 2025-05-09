package com.it_nomads.fluttersecurestorage.ciphers;

import java.security.Key;

/* loaded from: classes4.dex */
public interface KeyCipher {
    Key unwrap(byte[] bArr, String str) throws Exception;

    byte[] wrap(Key key) throws Exception;
}
