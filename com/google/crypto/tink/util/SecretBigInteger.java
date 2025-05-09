package com.google.crypto.tink.util;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Objects;

@Immutable
/* loaded from: classes4.dex */
public final class SecretBigInteger {
    private final BigInteger value;

    private SecretBigInteger(BigInteger value) {
        this.value = value;
    }

    public static SecretBigInteger fromBigInteger(BigInteger value, SecretKeyAccess access) {
        Objects.requireNonNull(access, "SecretKeyAccess required");
        return new SecretBigInteger(value);
    }

    public BigInteger getBigInteger(SecretKeyAccess access) {
        Objects.requireNonNull(access, "SecretKeyAccess required");
        return this.value;
    }

    public boolean equalsSecretBigInteger(SecretBigInteger other) {
        return MessageDigest.isEqual(this.value.toByteArray(), other.value.toByteArray());
    }
}
