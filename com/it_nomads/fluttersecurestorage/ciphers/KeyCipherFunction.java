package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;

/* compiled from: StorageCipherFactory.java */
@FunctionalInterface
/* loaded from: classes4.dex */
interface KeyCipherFunction {
    KeyCipher apply(Context context) throws Exception;
}
