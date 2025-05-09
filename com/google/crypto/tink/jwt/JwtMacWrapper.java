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
class JwtMacWrapper implements PrimitiveWrapper<JwtMacInternal, JwtMac> {
    private static final JwtMacWrapper WRAPPER = new JwtMacWrapper();

    private static void validate(PrimitiveSet<JwtMacInternal> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("Primitive set has no primary.");
        }
        Iterator<List<PrimitiveSet.Entry<JwtMacInternal>>> it = primitiveSet.getAll().iterator();
        while (it.hasNext()) {
            for (PrimitiveSet.Entry<JwtMacInternal> entry : it.next()) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes4.dex */
    public static class WrappedJwtMac implements JwtMac {
        private final PrimitiveSet<JwtMacInternal> primitives;

        private WrappedJwtMac(PrimitiveSet<JwtMacInternal> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.jwt.JwtMac
        public String computeMacAndEncode(RawJwt token) throws GeneralSecurityException {
            PrimitiveSet.Entry<JwtMacInternal> primary = this.primitives.getPrimary();
            return primary.getPrimitive().computeMacAndEncodeWithKid(token, JwtFormat.getKid(primary.getKeyId(), primary.getOutputPrefixType()));
        }

        @Override // com.google.crypto.tink.jwt.JwtMac
        public VerifiedJwt verifyMacAndDecode(String compact, JwtValidator validator) throws GeneralSecurityException {
            Iterator<List<PrimitiveSet.Entry<JwtMacInternal>>> it = this.primitives.getAll().iterator();
            GeneralSecurityException generalSecurityException = null;
            while (it.hasNext()) {
                for (PrimitiveSet.Entry<JwtMacInternal> entry : it.next()) {
                    try {
                        return entry.getPrimitive().verifyMacAndDecodeWithKid(compact, validator, JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType()));
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
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    JwtMacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtMac wrap(final PrimitiveSet<JwtMacInternal> primitives) throws GeneralSecurityException {
        validate(primitives);
        return new WrappedJwtMac(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtMac> getPrimitiveClass() {
        return JwtMac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtMacInternal> getInputPrimitiveClass() {
        return JwtMacInternal.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
