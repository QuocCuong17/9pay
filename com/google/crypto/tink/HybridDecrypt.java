package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public interface HybridDecrypt {
    byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException;
}
