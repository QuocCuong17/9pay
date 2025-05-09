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
class JwtPublicKeySignWrapper implements PrimitiveWrapper<JwtPublicKeySignInternal, JwtPublicKeySign> {
    private static final JwtPublicKeySignWrapper WRAPPER = new JwtPublicKeySignWrapper();

    private static void validate(PrimitiveSet<JwtPublicKeySignInternal> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("Primitive set has no primary.");
        }
        Iterator<List<PrimitiveSet.Entry<JwtPublicKeySignInternal>>> it = primitiveSet.getAll().iterator();
        while (it.hasNext()) {
            for (PrimitiveSet.Entry<JwtPublicKeySignInternal> entry : it.next()) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes4.dex */
    public static class WrappedJwtPublicKeySign implements JwtPublicKeySign {
        private final PrimitiveSet<JwtPublicKeySignInternal> primitives;

        public WrappedJwtPublicKeySign(final PrimitiveSet<JwtPublicKeySignInternal> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.jwt.JwtPublicKeySign
        public String signAndEncode(RawJwt token) throws GeneralSecurityException {
            PrimitiveSet.Entry<JwtPublicKeySignInternal> primary = this.primitives.getPrimary();
            return this.primitives.getPrimary().getPrimitive().signAndEncodeWithKid(token, JwtFormat.getKid(primary.getKeyId(), primary.getOutputPrefixType()));
        }
    }

    JwtPublicKeySignWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtPublicKeySign wrap(final PrimitiveSet<JwtPublicKeySignInternal> primitives) throws GeneralSecurityException {
        validate(primitives);
        return new WrappedJwtPublicKeySign(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeySign> getPrimitiveClass() {
        return JwtPublicKeySign.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeySignInternal> getInputPrimitiveClass() {
        return JwtPublicKeySignInternal.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
