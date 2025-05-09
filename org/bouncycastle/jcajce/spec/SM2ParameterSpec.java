package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;
import org.bouncycastle.util.Arrays;

/* loaded from: classes6.dex */
public class SM2ParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: id, reason: collision with root package name */
    private byte[] f149id;

    public SM2ParameterSpec(byte[] bArr) {
        Objects.requireNonNull(bArr, "id string cannot be null");
        this.f149id = Arrays.clone(bArr);
    }

    public byte[] getID() {
        return Arrays.clone(this.f149id);
    }
}
