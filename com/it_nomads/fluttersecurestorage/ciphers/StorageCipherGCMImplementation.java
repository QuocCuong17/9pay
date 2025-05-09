package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;

/* loaded from: classes4.dex */
public class StorageCipherGCMImplementation extends StorageCipher18Implementation {
    private static final int AUTHENTICATION_TAG_SIZE = 128;

    @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipher18Implementation
    protected String getAESPreferencesKey() {
        return "VGhpcyBpcyB0aGUga2V5IGZvcihBIHNlY3XyZZBzdG9yYWdlIEFFUyBLZXkK";
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipher18Implementation
    protected int getIvSize() {
        return 12;
    }

    public StorageCipherGCMImplementation(Context context, KeyCipher keyCipher) throws Exception {
        super(context, keyCipher);
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipher18Implementation
    protected Cipher getCipher() throws Exception {
        return Cipher.getInstance("AES/GCM/NoPadding");
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipher18Implementation
    protected AlgorithmParameterSpec getParameterSpec(byte[] bArr) {
        return new GCMParameterSpec(128, bArr);
    }
}
