package co.hyperverge.hypersnapsdk.service.security;

import android.util.Base64;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/* loaded from: classes2.dex */
public class HVSecurity {
    private static final String TAG = "HVSecurity";
    private String data;
    private PublicKey publicKey;
    private SignatureAlgorithm signatureAlgorithm;

    /* loaded from: classes2.dex */
    public enum SignatureAlgorithm {
        SHA256withRSA
    }

    /* loaded from: classes2.dex */
    public static class HVSecurityBuilder {
        private String data;
        private PublicKey publicKey;
        private SignatureAlgorithm signatureAlgorithm;

        HVSecurityBuilder() {
        }

        public HVSecurity build() {
            return new HVSecurity(this.data, this.publicKey, this.signatureAlgorithm);
        }

        public HVSecurityBuilder data(String str) {
            this.data = str;
            return this;
        }

        public HVSecurityBuilder publicKey(PublicKey publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public HVSecurityBuilder signatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
            this.signatureAlgorithm = signatureAlgorithm;
            return this;
        }

        public String toString() {
            return "HVSecurity.HVSecurityBuilder(data=" + this.data + ", publicKey=" + this.publicKey + ", signatureAlgorithm=" + this.signatureAlgorithm + ")";
        }
    }

    HVSecurity(String str, PublicKey publicKey, SignatureAlgorithm signatureAlgorithm) {
        this.data = str;
        this.publicKey = publicKey;
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public static HVSecurityBuilder builder() {
        return new HVSecurityBuilder();
    }

    public boolean verifySignature(String str) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, HVSecurityException {
        HVLogUtils.d(TAG, "verifySignature() called with: signature = [" + str + "]");
        if (this.publicKey == null) {
            HVLogUtils.d(TAG, "verifySignature(): Public key is null");
            throw new HVSecurityException("Public key is null");
        }
        if (this.signatureAlgorithm == null) {
            HVLogUtils.d(TAG, "verifySignature(): Signature Algo is null");
            throw new HVSecurityException("Signature Algorithm is null");
        }
        String str2 = this.data;
        if (str2 == null || str2.isEmpty()) {
            HVLogUtils.d(TAG, "verifySignature(): Data is empty or null");
            throw new HVSecurityException("Data is empty or null");
        }
        if (str == null || str.isEmpty()) {
            HVLogUtils.d(TAG, "verifySignature(): Signature is empty or null");
            throw new HVSecurityException("Signature is empty or null");
        }
        Signature signature = Signature.getInstance(this.signatureAlgorithm.name());
        signature.initVerify(this.publicKey);
        signature.update(this.data.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64.decode(str, 0));
    }
}
