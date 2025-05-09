package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Immutable
/* loaded from: classes4.dex */
interface JwtMacInternal {
    String computeMacAndEncodeWithKid(RawJwt token, Optional<String> kid) throws GeneralSecurityException;

    VerifiedJwt verifyMacAndDecodeWithKid(String compact, JwtValidator validator, Optional<String> kid) throws GeneralSecurityException;
}
