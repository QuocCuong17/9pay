package org.jmrtd;

import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes6.dex */
public class PACESecretKeySpec extends SecretKeySpec implements AccessKeySpec {
    private static final long serialVersionUID = -5181060361947453857L;
    private byte keyReference;

    public PACESecretKeySpec(byte[] bArr, int i, int i2, String str, byte b) {
        super(bArr, i, i2, str);
        this.keyReference = b;
    }

    public PACESecretKeySpec(byte[] bArr, String str, byte b) {
        super(bArr, str);
        this.keyReference = b;
    }

    public byte getKeyReference() {
        return this.keyReference;
    }

    @Override // javax.crypto.spec.SecretKeySpec
    public int hashCode() {
        return (super.hashCode() * 31) + this.keyReference;
    }

    @Override // javax.crypto.spec.SecretKeySpec
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj) && getClass() == obj.getClass() && this.keyReference == ((PACESecretKeySpec) obj).keyReference;
    }

    @Override // org.jmrtd.AccessKeySpec
    public byte[] getKey() {
        return super.getEncoded();
    }
}
