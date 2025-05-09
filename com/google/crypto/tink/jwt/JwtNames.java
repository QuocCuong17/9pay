package com.google.crypto.tink.jwt;

/* loaded from: classes4.dex */
final class JwtNames {
    static final String CLAIM_AUDIENCE = "aud";
    static final String CLAIM_EXPIRATION = "exp";
    static final String CLAIM_ISSUED_AT = "iat";
    static final String CLAIM_ISSUER = "iss";
    static final String CLAIM_JWT_ID = "jti";
    static final String CLAIM_NOT_BEFORE = "nbf";
    static final String CLAIM_SUBJECT = "sub";
    static final String HEADER_ALGORITHM = "alg";
    static final String HEADER_CRITICAL = "crit";
    static final String HEADER_KEY_ID = "kid";
    static final String HEADER_TYPE = "typ";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(String name) {
        if (isRegisteredName(name)) {
            throw new IllegalArgumentException(String.format("claim '%s' is invalid because it's a registered name; use the corresponding setter method.", name));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isRegisteredName(String name) {
        return name.equals("iss") || name.equals("sub") || name.equals("aud") || name.equals("exp") || name.equals(CLAIM_NOT_BEFORE) || name.equals("iat") || name.equals("jti");
    }

    private JwtNames() {
    }
}
