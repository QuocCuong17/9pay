package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;

@Immutable
/* loaded from: classes4.dex */
public abstract class SignaturePublicKey extends Key {
    public abstract Bytes getOutputPrefix();

    @Override // com.google.crypto.tink.Key
    public abstract SignatureParameters getParameters();
}
