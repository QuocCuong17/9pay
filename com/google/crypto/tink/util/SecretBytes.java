package com.google.crypto.tink.util;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.subtle.Random;
import com.google.errorprone.annotations.Immutable;
import java.security.MessageDigest;
import java.util.Objects;

@Immutable
/* loaded from: classes4.dex */
public final class SecretBytes {
    private final Bytes bytes;

    private SecretBytes(Bytes bytes) {
        this.bytes = bytes;
    }

    public static SecretBytes copyFrom(byte[] value, SecretKeyAccess access) {
        Objects.requireNonNull(access, "SecretKeyAccess required");
        return new SecretBytes(Bytes.copyFrom(value));
    }

    public static SecretBytes randomBytes(int length) {
        return new SecretBytes(Bytes.copyFrom(Random.randBytes(length)));
    }

    public byte[] toByteArray(SecretKeyAccess access) {
        Objects.requireNonNull(access, "SecretKeyAccess required");
        return this.bytes.toByteArray();
    }

    public int size() {
        return this.bytes.size();
    }

    public boolean equalsSecretBytes(SecretBytes other) {
        return MessageDigest.isEqual(this.bytes.toByteArray(), other.bytes.toByteArray());
    }
}
