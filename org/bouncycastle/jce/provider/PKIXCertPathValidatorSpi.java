package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;

/* loaded from: classes6.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi() {
        this(false);
    }

    public PKIXCertPathValidatorSpi(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        if (x509Certificate instanceof BCX509Certificate) {
            RuntimeException runtimeException = null;
            try {
                if (((BCX509Certificate) x509Certificate).getTBSCertificateNative() != null) {
                    return;
                }
            } catch (RuntimeException e) {
                runtimeException = e;
            }
            throw new AnnotatedException("unable to process TBSCertificate", runtimeException);
        }
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e2) {
            throw new AnnotatedException(e2.getMessage());
        } catch (CertificateEncodingException e3) {
            throw new AnnotatedException("unable to process TBSCertificate", e3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6, types: [int] */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.bouncycastle.asn1.x509.AlgorithmIdentifier] */
    /* JADX WARN: Type inference failed for: r5v4 */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        List<? extends Certificate> list;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        List list2;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else {
            if (!(certPathParameters instanceof PKIXExtendedParameters)) {
                throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
            }
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (pKIXExtendedParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        ?? r3 = -1;
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
        try {
            TrustAnchor findTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
            if (findTrustAnchor == null) {
                list = certificates;
                try {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                } catch (AnnotatedException e) {
                    e = e;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - 1);
                }
            }
            checkCertificate(findTrustAnchor.getTrustedCert());
            PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(findTrustAnchor).build();
            int i = size + 1;
            ArrayList[] arrayListArr2 = new ArrayList[i];
            for (int i2 = 0; i2 < i; i2++) {
                arrayListArr2[i2] = new ArrayList();
            }
            HashSet hashSet3 = new HashSet();
            hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
            arrayListArr2[0].add(new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false));
            PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
            HashSet hashSet4 = new HashSet();
            int i3 = build.isExplicitPolicyRequired() ? 0 : i;
            int i4 = build.isAnyPolicyInhibited() ? 0 : i;
            if (build.isPolicyMappingInhibited()) {
                i = 0;
            }
            X509Certificate trustedCert = findTrustAnchor.getTrustedCert();
            try {
                if (trustedCert != null) {
                    ca = PrincipalUtils.getSubjectPrincipal(trustedCert);
                    cAPublicKey = trustedCert.getPublicKey();
                } else {
                    ca = PrincipalUtils.getCA(findTrustAnchor);
                    cAPublicKey = findTrustAnchor.getCAPublicKey();
                }
                try {
                    r3 = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                    r3.getAlgorithm();
                    r3.getParameters();
                    if (build.getTargetConstraints() != null && !build.getTargetConstraints().match((Certificate) certificates.get(0))) {
                        throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                    }
                    List certPathCheckers = build.getCertPathCheckers();
                    Iterator it = certPathCheckers.iterator();
                    while (it.hasNext()) {
                        ((PKIXCertPathChecker) it.next()).init(false);
                    }
                    int i5 = size;
                    X509Certificate x509Certificate = null;
                    ?? r5 = i;
                    int i6 = i4;
                    PKIXPolicyNode pKIXPolicyNode = r5;
                    int i7 = i3;
                    int size2 = certificates.size() - 1;
                    int i8 = i7;
                    int i9 = r5;
                    while (size2 >= 0) {
                        int i10 = size - size2;
                        Set set = initialPolicies;
                        X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                        boolean z = size2 == certificates.size() + (-1);
                        try {
                            checkCertificate(x509Certificate2);
                            TrustAnchor trustAnchor = findTrustAnchor;
                            int i11 = i6;
                            List<? extends Certificate> list3 = certificates;
                            int i12 = i8;
                            PKIXExtendedParameters pKIXExtendedParameters2 = build;
                            int i13 = size2;
                            PKIXExtendedParameters pKIXExtendedParameters3 = build;
                            int i14 = i9;
                            List list4 = certPathCheckers;
                            PKIXNameConstraintValidator pKIXNameConstraintValidator2 = pKIXNameConstraintValidator;
                            ArrayList[] arrayListArr3 = arrayListArr2;
                            RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters2, size2, cAPublicKey, z, ca, trustedCert, this.helper);
                            RFC3280CertPathUtilities.processCertBC(certPath, i13, pKIXNameConstraintValidator2, this.isForCRLCheck);
                            PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath, i13, RFC3280CertPathUtilities.processCertD(certPath, i13, hashSet4, pKIXPolicyNode, arrayListArr3, i11, this.isForCRLCheck));
                            RFC3280CertPathUtilities.processCertF(certPath, i13, processCertE, i12);
                            if (i10 != size) {
                                if (x509Certificate2 == null || x509Certificate2.getVersion() != 1) {
                                    RFC3280CertPathUtilities.prepareNextCertA(certPath, i13);
                                    arrayListArr = arrayListArr3;
                                    PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i13, arrayListArr, processCertE, i14);
                                    RFC3280CertPathUtilities.prepareNextCertG(certPath, i13, pKIXNameConstraintValidator2);
                                    int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i13, i12);
                                    int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i13, i14);
                                    int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i13, i11);
                                    i8 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i13, prepareNextCertH1);
                                    i14 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i13, prepareNextCertH2);
                                    int prepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i13, prepareNextCertH3);
                                    RFC3280CertPathUtilities.prepareNextCertK(certPath, i13);
                                    i5 = RFC3280CertPathUtilities.prepareNextCertM(certPath, i13, RFC3280CertPathUtilities.prepareNextCertL(certPath, i13, i5));
                                    RFC3280CertPathUtilities.prepareNextCertN(certPath, i13);
                                    Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                    if (criticalExtensionOIDs != null) {
                                        hashSet2 = new HashSet(criticalExtensionOIDs);
                                        hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                        hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                        hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                        hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                        hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                        hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    } else {
                                        hashSet2 = new HashSet();
                                    }
                                    list2 = list4;
                                    RFC3280CertPathUtilities.prepareNextCertO(certPath, i13, hashSet2, list2);
                                    X500Name subjectPrincipal = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                    try {
                                        PublicKey nextWorkingKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i13, this.helper);
                                        AlgorithmIdentifier algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(nextWorkingKey);
                                        algorithmIdentifier.getAlgorithm();
                                        algorithmIdentifier.getParameters();
                                        pKIXPolicyNode = prepareCertB;
                                        i6 = prepareNextCertJ;
                                        ca = subjectPrincipal;
                                        cAPublicKey = nextWorkingKey;
                                        trustedCert = x509Certificate2;
                                        i9 = i14;
                                        size2 = i13 - 1;
                                        x509Certificate = x509Certificate2;
                                        initialPolicies = set;
                                        certificates = list3;
                                        build = pKIXExtendedParameters3;
                                        findTrustAnchor = trustAnchor;
                                        List list5 = list2;
                                        arrayListArr2 = arrayListArr;
                                        pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                        certPathCheckers = list5;
                                    } catch (CertPathValidatorException e2) {
                                        throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath, i13);
                                    }
                                } else if (i10 != 1 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                    throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i13);
                                }
                            }
                            list2 = list4;
                            arrayListArr = arrayListArr3;
                            pKIXPolicyNode = processCertE;
                            i6 = i11;
                            i5 = i5;
                            i8 = i12;
                            i9 = i14;
                            size2 = i13 - 1;
                            x509Certificate = x509Certificate2;
                            initialPolicies = set;
                            certificates = list3;
                            build = pKIXExtendedParameters3;
                            findTrustAnchor = trustAnchor;
                            List list52 = list2;
                            arrayListArr2 = arrayListArr;
                            pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                            certPathCheckers = list52;
                        } catch (AnnotatedException e3) {
                            throw new CertPathValidatorException(e3.getMessage(), e3.getUnderlyingException(), certPath, size2);
                        }
                    }
                    PKIXExtendedParameters pKIXExtendedParameters4 = build;
                    ArrayList[] arrayListArr4 = arrayListArr2;
                    TrustAnchor trustAnchor2 = findTrustAnchor;
                    Set set2 = initialPolicies;
                    List list6 = certPathCheckers;
                    X509Certificate x509Certificate3 = x509Certificate;
                    int i15 = size2;
                    int i16 = i15 + 1;
                    int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i16, RFC3280CertPathUtilities.wrapupCertA(i8, x509Certificate3));
                    Set<String> criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                    if (criticalExtensionOIDs2 != null) {
                        hashSet = new HashSet(criticalExtensionOIDs2);
                        hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                        hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                        hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                        hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                        hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                        hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                        hashSet.remove(Extension.extendedKeyUsage.getId());
                    } else {
                        hashSet = new HashSet();
                    }
                    RFC3280CertPathUtilities.wrapupCertF(certPath, i16, list6, hashSet);
                    PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters4, set2, i16, arrayListArr4, pKIXPolicyNode, hashSet4);
                    if (wrapupCertB > 0 || wrapupCertG != null) {
                        return new PKIXCertPathValidatorResult(trustAnchor2, wrapupCertG, x509Certificate3.getPublicKey());
                    }
                    throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i15);
                } catch (CertPathValidatorException e4) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e4, certPath, -1);
                }
            } catch (RuntimeException e5) {
                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e5, certPath, r3);
            }
        } catch (AnnotatedException e6) {
            e = e6;
            list = certificates;
        }
    }
}
