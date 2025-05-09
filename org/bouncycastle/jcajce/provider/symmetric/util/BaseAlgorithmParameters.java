package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Objects;

/* loaded from: classes6.dex */
public abstract class BaseAlgorithmParameters extends AlgorithmParametersSpi {
    @Override // java.security.AlgorithmParametersSpi
    protected AlgorithmParameterSpec engineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
        Objects.requireNonNull(cls, "argument to getParameterSpec must not be null");
        return localEngineGetParameterSpec(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isASN1FormatString(String str) {
        return str == null || str.equals("ASN.1");
    }

    protected abstract AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException;
}
