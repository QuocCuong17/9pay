package org.ejbca.cvc;

import org.bouncycastle.crypto.tls.CipherSuite;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes6.dex */
public enum CVCTagEnum {
    CV_CERTIFICATE(32545, true),
    CERTIFICATE_BODY(32590, true),
    PROFILE_IDENTIFIER(24361),
    PUBLIC_KEY(32585, true),
    HOLDER_REFERENCE(24352),
    HOLDER_AUTH_TEMPLATE(32588, true),
    EFFECTIVE_DATE(24357),
    EXPIRATION_DATE(24356),
    SIGNATURE(24375),
    OID(6),
    CA_REFERENCE(66),
    REQ_AUTHENTICATION(103, true),
    ROLE_AND_ACCESS_RIGHTS(83),
    MODULUS(129),
    EXPONENT(130),
    COEFFICIENT_A(130),
    COEFFICIENT_B(ISO781611.CREATION_DATE_AND_TIME_TAG),
    BASE_POINT_G(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA),
    BASE_POINT_R_ORDER(133),
    PUBLIC_POINT_Y(134),
    COFACTOR_F(135);

    private boolean isSequence;
    private int value;

    CVCTagEnum(int i) {
        this(i, false);
    }

    CVCTagEnum(int i, boolean z) {
        this.value = i;
        this.isSequence = z;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isSequence() {
        return this.isSequence;
    }
}
