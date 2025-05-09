package org.ejbca.cvc;

/* loaded from: classes6.dex */
public interface AuthorizationRole {
    byte getValue();

    boolean isAccreditationBodyDV();

    boolean isAuthenticationTerminal();

    boolean isCVCA();

    boolean isCertificationServiceProviderDV();

    boolean isDV();

    boolean isDomesticDV();

    boolean isForeignDV();

    boolean isIS();

    boolean isSignatureTerminal();

    String name();
}
