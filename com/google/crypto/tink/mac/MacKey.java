package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.util.Bytes;

/* loaded from: classes4.dex */
public abstract class MacKey extends Key {
    public abstract Bytes getOutputPrefix();

    @Override // com.google.crypto.tink.Key
    public abstract MacParameters getParameters();
}
