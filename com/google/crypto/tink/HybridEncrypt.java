package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public interface HybridEncrypt {
    byte[] encrypt(final byte[] plaintext, final byte[] contextInfo) throws GeneralSecurityException;
}
