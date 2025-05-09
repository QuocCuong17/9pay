package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.mac.AesCmacParameters;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
/* loaded from: classes4.dex */
public final class AesCmacKey extends MacKey {
    private final SecretBytes aesKeyBytes;

    @Nullable
    private final Integer idRequirement;
    private final Bytes outputPrefix;
    private final AesCmacParameters parameters;

    /* loaded from: classes4.dex */
    public static class Builder {

        @Nullable
        private SecretBytes aesKeyBytes;

        @Nullable
        private Integer idRequirement;

        @Nullable
        private AesCmacParameters parameters;

        private Builder() {
            this.parameters = null;
            this.aesKeyBytes = null;
            this.idRequirement = null;
        }

        public Builder setParameters(AesCmacParameters parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder setAesKeyBytes(SecretBytes aesKeyBytes) throws GeneralSecurityException {
            this.aesKeyBytes = aesKeyBytes;
            return this;
        }

        public Builder setIdRequirement(@Nullable Integer idRequirement) {
            this.idRequirement = idRequirement;
            return this;
        }

        private Bytes getOutputPrefix() {
            if (this.parameters.getVariant() == AesCmacParameters.Variant.NO_PREFIX) {
                return Bytes.copyFrom(new byte[0]);
            }
            if (this.parameters.getVariant() == AesCmacParameters.Variant.LEGACY || this.parameters.getVariant() == AesCmacParameters.Variant.CRUNCHY) {
                return Bytes.copyFrom(ByteBuffer.allocate(5).put((byte) 0).putInt(this.idRequirement.intValue()).array());
            }
            if (this.parameters.getVariant() == AesCmacParameters.Variant.TINK) {
                return Bytes.copyFrom(ByteBuffer.allocate(5).put((byte) 1).putInt(this.idRequirement.intValue()).array());
            }
            throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: " + this.parameters.getVariant());
        }

        public AesCmacKey build() throws GeneralSecurityException {
            AesCmacParameters aesCmacParameters = this.parameters;
            if (aesCmacParameters == null || this.aesKeyBytes == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (aesCmacParameters.getKeySizeBytes() != this.aesKeyBytes.size()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            if (this.parameters.hasIdRequirement() && this.idRequirement == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if (!this.parameters.hasIdRequirement() && this.idRequirement != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            return new AesCmacKey(this.parameters, this.aesKeyBytes, getOutputPrefix(), this.idRequirement);
        }
    }

    private AesCmacKey(AesCmacParameters parameters, SecretBytes aesKeyBytes, Bytes outputPrefix, @Nullable Integer idRequirement) {
        this.parameters = parameters;
        this.aesKeyBytes = aesKeyBytes;
        this.outputPrefix = outputPrefix;
        this.idRequirement = idRequirement;
    }

    public static Builder builder() {
        return new Builder();
    }

    public SecretBytes getAesKey() {
        return this.aesKeyBytes;
    }

    @Override // com.google.crypto.tink.mac.MacKey
    public Bytes getOutputPrefix() {
        return this.outputPrefix;
    }

    @Override // com.google.crypto.tink.mac.MacKey, com.google.crypto.tink.Key
    public AesCmacParameters getParameters() {
        return this.parameters;
    }

    @Override // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    @Override // com.google.crypto.tink.Key
    public boolean equalsKey(Key o) {
        if (!(o instanceof AesCmacKey)) {
            return false;
        }
        AesCmacKey aesCmacKey = (AesCmacKey) o;
        return aesCmacKey.parameters.equals(this.parameters) && aesCmacKey.aesKeyBytes.equalsSecretBytes(this.aesKeyBytes) && Objects.equals(aesCmacKey.idRequirement, this.idRequirement);
    }
}
