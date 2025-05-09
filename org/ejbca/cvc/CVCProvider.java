package org.ejbca.cvc;

import java.security.Provider;

/* loaded from: classes6.dex */
public class CVCProvider extends Provider {
    private static final long serialVersionUID = 1;
    static double version = 1.0d;
    static String INFO = "CVC Security Provider " + version + " (supports Card Verifiable Certificates for ePassports)";
    public static String PROVIDER_NAME = "CVC";

    public CVCProvider() {
        super(PROVIDER_NAME, version, INFO);
        put("CertificateFactory.CVC", JDKCVCertificateFactory.class.getName());
        put("Alg.Alias.CertificateFactory.CVC", "CVC");
    }
}
