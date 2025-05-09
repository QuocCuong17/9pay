package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;

/* compiled from: StorageCipherFactory.java */
/* loaded from: classes4.dex */
enum StorageCipherAlgorithm {
    AES_CBC_PKCS7Padding(new StorageCipherFunction() { // from class: com.it_nomads.fluttersecurestorage.ciphers.StorageCipherAlgorithm$$ExternalSyntheticLambda0
        @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipherFunction
        public final StorageCipher apply(Context context, KeyCipher keyCipher) {
            return new StorageCipher18Implementation(context, keyCipher);
        }
    }, 1),
    AES_GCM_NoPadding(new StorageCipherFunction() { // from class: com.it_nomads.fluttersecurestorage.ciphers.StorageCipherAlgorithm$$ExternalSyntheticLambda1
        @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipherFunction
        public final StorageCipher apply(Context context, KeyCipher keyCipher) {
            return new StorageCipherGCMImplementation(context, keyCipher);
        }
    }, 23);

    final int minVersionCode;
    final StorageCipherFunction storageCipher;

    StorageCipherAlgorithm(StorageCipherFunction storageCipherFunction, int i) {
        this.storageCipher = storageCipherFunction;
        this.minVersionCode = i;
    }
}
