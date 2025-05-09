package org.jmrtd.protocol;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/* loaded from: classes6.dex */
public class PACEIMMappingResult extends PACEMappingResult {
    private static final long serialVersionUID = -6415752866407346050L;
    private byte[] pcdNonce;

    public PACEIMMappingResult(AlgorithmParameterSpec algorithmParameterSpec, byte[] bArr, byte[] bArr2, AlgorithmParameterSpec algorithmParameterSpec2) {
        super(algorithmParameterSpec, bArr, algorithmParameterSpec2);
        this.pcdNonce = bArr2 == null ? null : Arrays.copyOf(bArr2, bArr2.length);
    }

    public byte[] getPCDNonce() {
        byte[] bArr = this.pcdNonce;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    @Override // org.jmrtd.protocol.PACEMappingResult
    public int hashCode() {
        return (super.hashCode() * 31) + Arrays.hashCode(this.pcdNonce);
    }

    @Override // org.jmrtd.protocol.PACEMappingResult
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && getClass() == obj.getClass()) {
            return Arrays.equals(this.pcdNonce, ((PACEIMMappingResult) obj).pcdNonce);
        }
        return false;
    }
}
