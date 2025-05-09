package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.crypto.tink.signature.EcdsaParameters;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
/* loaded from: classes4.dex */
public final class EcdsaPublicKey extends SignaturePublicKey {

    @Nullable
    private final Integer idRequirement;
    private final Bytes outputPrefix;
    private final EcdsaParameters parameters;
    private final ECPoint publicPoint;

    /* loaded from: classes4.dex */
    public static class Builder {

        @Nullable
        private Integer idRequirement;

        @Nullable
        private EcdsaParameters parameters;

        @Nullable
        private ECPoint publicPoint;

        private Builder() {
            this.parameters = null;
            this.publicPoint = null;
            this.idRequirement = null;
        }

        public Builder setParameters(EcdsaParameters parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder setPublicPoint(ECPoint publicPoint) {
            this.publicPoint = publicPoint;
            return this;
        }

        public Builder setIdRequirement(@Nullable Integer idRequirement) {
            this.idRequirement = idRequirement;
            return this;
        }

        private Bytes getOutputPrefix() {
            if (this.parameters.getVariant() == EcdsaParameters.Variant.NO_PREFIX) {
                return Bytes.copyFrom(new byte[0]);
            }
            if (this.parameters.getVariant() == EcdsaParameters.Variant.LEGACY || this.parameters.getVariant() == EcdsaParameters.Variant.CRUNCHY) {
                return Bytes.copyFrom(ByteBuffer.allocate(5).put((byte) 0).putInt(this.idRequirement.intValue()).array());
            }
            if (this.parameters.getVariant() == EcdsaParameters.Variant.TINK) {
                return Bytes.copyFrom(ByteBuffer.allocate(5).put((byte) 1).putInt(this.idRequirement.intValue()).array());
            }
            throw new IllegalStateException("Unknown EcdsaParameters.Variant: " + this.parameters.getVariant());
        }

        public EcdsaPublicKey build() throws GeneralSecurityException {
            EcdsaParameters ecdsaParameters = this.parameters;
            if (ecdsaParameters == null) {
                throw new GeneralSecurityException("Cannot build without parameters");
            }
            ECPoint eCPoint = this.publicPoint;
            if (eCPoint == null) {
                throw new GeneralSecurityException("Cannot build without public point");
            }
            EllipticCurvesUtil.checkPointOnCurve(eCPoint, ecdsaParameters.getCurveType().toParameterSpec().getCurve());
            if (this.parameters.hasIdRequirement() && this.idRequirement == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if (!this.parameters.hasIdRequirement() && this.idRequirement != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            return new EcdsaPublicKey(this.parameters, this.publicPoint, getOutputPrefix(), this.idRequirement);
        }
    }

    private EcdsaPublicKey(EcdsaParameters parameters, ECPoint publicPoint, Bytes outputPrefix, @Nullable Integer idRequirement) {
        this.parameters = parameters;
        this.publicPoint = publicPoint;
        this.outputPrefix = outputPrefix;
        this.idRequirement = idRequirement;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ECPoint getPublicPoint() {
        return this.publicPoint;
    }

    @Override // com.google.crypto.tink.signature.SignaturePublicKey
    public Bytes getOutputPrefix() {
        return this.outputPrefix;
    }

    @Override // com.google.crypto.tink.signature.SignaturePublicKey, com.google.crypto.tink.Key
    public EcdsaParameters getParameters() {
        return this.parameters;
    }

    @Override // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    @Override // com.google.crypto.tink.Key
    public boolean equalsKey(Key o) {
        if (!(o instanceof EcdsaPublicKey)) {
            return false;
        }
        EcdsaPublicKey ecdsaPublicKey = (EcdsaPublicKey) o;
        return ecdsaPublicKey.parameters.equals(this.parameters) && ecdsaPublicKey.publicPoint.equals(this.publicPoint) && Objects.equals(ecdsaPublicKey.idRequirement, this.idRequirement);
    }
}
