package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;

/* compiled from: StorageCipherFactory.java */
@FunctionalInterface
/* loaded from: classes4.dex */
interface StorageCipherFunction {
    StorageCipher apply(Context context, KeyCipher keyCipher) throws Exception;
}
