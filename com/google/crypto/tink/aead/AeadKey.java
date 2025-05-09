package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.util.Bytes;

/* loaded from: classes4.dex */
public abstract class AeadKey extends Key {
    public abstract Bytes getOutputPrefix();

    @Override // com.google.crypto.tink.Key
    public abstract AeadParameters getParameters();
}
