package com.google.crypto.tink.aead;

import com.google.errorprone.annotations.Immutable;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class XChaCha20Poly1305Parameters extends AeadParameters {
    private final Variant variant;

    @Immutable
    /* loaded from: classes4.dex */
    public static final class Variant {
        private final String name;
        public static final Variant TINK = new Variant("TINK");
        public static final Variant CRUNCHY = new Variant("CRUNCHY");
        public static final Variant NO_PREFIX = new Variant("NO_PREFIX");

        private Variant(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

    public static XChaCha20Poly1305Parameters create() {
        return new XChaCha20Poly1305Parameters(Variant.NO_PREFIX);
    }

    public static XChaCha20Poly1305Parameters create(Variant variant) {
        return new XChaCha20Poly1305Parameters(variant);
    }

    private XChaCha20Poly1305Parameters(Variant variant) {
        this.variant = variant;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public boolean equals(Object o) {
        return (o instanceof XChaCha20Poly1305Parameters) && ((XChaCha20Poly1305Parameters) o).getVariant() == getVariant();
    }

    public int hashCode() {
        return Objects.hashCode(this.variant);
    }

    @Override // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public String toString() {
        return "XChaCha20Poly1305 Parameters (variant: " + this.variant + ")";
    }
}
