package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;

/* compiled from: StorageCipherFactory.java */
/* loaded from: classes4.dex */
enum KeyCipherAlgorithm {
    RSA_ECB_PKCS1Padding(new KeyCipherFunction() { // from class: com.it_nomads.fluttersecurestorage.ciphers.KeyCipherAlgorithm$$ExternalSyntheticLambda0
        @Override // com.it_nomads.fluttersecurestorage.ciphers.KeyCipherFunction
        public final KeyCipher apply(Context context) {
            return new RSACipher18Implementation(context);
        }
    }, 1),
    RSA_ECB_OAEPwithSHA_256andMGF1Padding(new KeyCipherFunction() { // from class: com.it_nomads.fluttersecurestorage.ciphers.KeyCipherAlgorithm$$ExternalSyntheticLambda1
        @Override // com.it_nomads.fluttersecurestorage.ciphers.KeyCipherFunction
        public final KeyCipher apply(Context context) {
            return new RSACipherOAEPImplementation(context);
        }
    }, 23);

    final KeyCipherFunction keyCipher;
    final int minVersionCode;

    KeyCipherAlgorithm(KeyCipherFunction keyCipherFunction, int i) {
        this.keyCipher = keyCipherFunction;
        this.minVersionCode = i;
    }
}
