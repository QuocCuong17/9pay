package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes6.dex */
public class ParametersWithID implements CipherParameters {

    /* renamed from: id, reason: collision with root package name */
    private byte[] f146id;
    private CipherParameters parameters;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.f146id = bArr;
    }

    public byte[] getID() {
        return this.f146id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
