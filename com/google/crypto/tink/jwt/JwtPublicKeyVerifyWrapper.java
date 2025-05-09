package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
class JwtPublicKeyVerifyWrapper implements PrimitiveWrapper<JwtPublicKeyVerifyInternal, JwtPublicKeyVerify> {
    private static final JwtPublicKeyVerifyWrapper WRAPPER = new JwtPublicKeyVerifyWrapper();

    JwtPublicKeyVerifyWrapper() {
    }

    private static void validate(PrimitiveSet<JwtPublicKeyVerifyInternal> primitiveSet) throws GeneralSecurityException {
        Iterator<List<PrimitiveSet.Entry<JwtPublicKeyVerifyInternal>>> it = primitiveSet.getAll().iterator();
        while (it.hasNext()) {
            for (PrimitiveSet.Entry<JwtPublicKeyVerifyInternal> entry : it.next()) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes4.dex */
    public static class WrappedJwtPublicKeyVerify implements JwtPublicKeyVerify {
        private final PrimitiveSet<JwtPublicKeyVerifyInternal> primitives;

        public WrappedJwtPublicKeyVerify(PrimitiveSet<JwtPublicKeyVerifyInternal> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.jwt.JwtPublicKeyVerify
        public VerifiedJwt verifyAndDecode(String compact, JwtValidator validator) throws GeneralSecurityException {
            Iterator<List<PrimitiveSet.Entry<JwtPublicKeyVerifyInternal>>> it = this.primitives.getAll().iterator();
            GeneralSecurityException generalSecurityException = null;
            while (it.hasNext()) {
                for (PrimitiveSet.Entry<JwtPublicKeyVerifyInternal> entry : it.next()) {
                    try {
                        return entry.getPrimitive().verifyAndDecodeWithKid(compact, validator, JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType()));
                    } catch (GeneralSecurityException e) {
                        if (e instanceof JwtInvalidException) {
                            generalSecurityException = e;
                        }
                    }
                }
            }
            if (generalSecurityException != null) {
                throw generalSecurityException;
            }
            throw new GeneralSecurityException("invalid JWT");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtPublicKeyVerify wrap(final PrimitiveSet<JwtPublicKeyVerifyInternal> primitives) throws GeneralSecurityException {
        validate(primitives);
        return new WrappedJwtPublicKeyVerify(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeyVerify> getPrimitiveClass() {
        return JwtPublicKeyVerify.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeyVerifyInternal> getInputPrimitiveClass() {
        return JwtPublicKeyVerifyInternal.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
