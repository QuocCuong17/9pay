package org.bouncycastle.x509;

import androidx.webkit.ProxyConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.LocaleString;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.i18n.filter.UntrustedUrlInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.jce.provider.PKIXPolicyNode;
import org.bouncycastle.jce.provider.RFC3280CertPathUtilities;
import org.bouncycastle.util.Integers;

/* loaded from: classes6.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected List[] errors;
    private boolean initialized;
    protected int n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        Iterator<PKIXCertPathChecker> it = certPathCheckers.iterator();
        while (it.hasNext()) {
            try {
                try {
                    it.next().init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(KEY_USAGE);
                criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                String str = QC_STATEMENT;
                if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(str);
                }
                Iterator<PKIXCertPathChecker> it2 = certPathCheckers.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it3 = criticalExtensionOIDs.iterator();
                    while (it3.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it3.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i = 0; i < aSN1Sequence2.size(); i++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i2 = 0; i2 != excludedSubtrees.length; i2++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i2]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i = this.n;
        int i2 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i--;
                i2++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < i) {
                i = intValue;
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i2)}));
    }

    /* JADX WARN: Removed duplicated region for block: B:226:0x013d A[Catch: CertPathReviewerException -> 0x05f9, TRY_LEAVE, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d0, B:201:0x03dd, B:203:0x03e7, B:107:0x0425, B:210:0x03ef, B:211:0x03fd, B:213:0x03fe, B:214:0x040c, B:221:0x040e, B:222:0x041c, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0434, B:279:0x0441, B:281:0x0442, B:286:0x0453, B:288:0x045d, B:289:0x0462, B:291:0x0468, B:294:0x0476, B:308:0x048b, B:315:0x05df, B:316:0x05eb, B:318:0x0496, B:319:0x04a2, B:320:0x04a3, B:322:0x04a9, B:324:0x04b1, B:326:0x04b7, B:329:0x04c1, B:330:0x04c4, B:332:0x04ca, B:334:0x04da, B:335:0x04de, B:337:0x04e4, B:339:0x04ec, B:342:0x04ef, B:344:0x04f4, B:345:0x04f8, B:347:0x04fe, B:350:0x050e, B:352:0x0516, B:353:0x0519, B:355:0x051f, B:357:0x052b, B:359:0x052f, B:362:0x0532, B:364:0x0535, B:365:0x0541, B:367:0x0546, B:369:0x0550, B:370:0x0553, B:372:0x0559, B:374:0x0569, B:375:0x056d, B:377:0x0573, B:380:0x0583, B:385:0x0587, B:388:0x058a, B:390:0x058d, B:391:0x0593, B:393:0x0599, B:395:0x05ab, B:401:0x05b5, B:403:0x05bb, B:404:0x05be, B:406:0x05c4, B:408:0x05d0, B:410:0x05d4, B:413:0x05d7, B:417:0x05ec, B:418:0x05f8), top: B:14:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0120 A[Catch: CertPathReviewerException -> 0x05f9, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d0, B:201:0x03dd, B:203:0x03e7, B:107:0x0425, B:210:0x03ef, B:211:0x03fd, B:213:0x03fe, B:214:0x040c, B:221:0x040e, B:222:0x041c, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0434, B:279:0x0441, B:281:0x0442, B:286:0x0453, B:288:0x045d, B:289:0x0462, B:291:0x0468, B:294:0x0476, B:308:0x048b, B:315:0x05df, B:316:0x05eb, B:318:0x0496, B:319:0x04a2, B:320:0x04a3, B:322:0x04a9, B:324:0x04b1, B:326:0x04b7, B:329:0x04c1, B:330:0x04c4, B:332:0x04ca, B:334:0x04da, B:335:0x04de, B:337:0x04e4, B:339:0x04ec, B:342:0x04ef, B:344:0x04f4, B:345:0x04f8, B:347:0x04fe, B:350:0x050e, B:352:0x0516, B:353:0x0519, B:355:0x051f, B:357:0x052b, B:359:0x052f, B:362:0x0532, B:364:0x0535, B:365:0x0541, B:367:0x0546, B:369:0x0550, B:370:0x0553, B:372:0x0559, B:374:0x0569, B:375:0x056d, B:377:0x0573, B:380:0x0583, B:385:0x0587, B:388:0x058a, B:390:0x058d, B:391:0x0593, B:393:0x0599, B:395:0x05ab, B:401:0x05b5, B:403:0x05bb, B:404:0x05be, B:406:0x05c4, B:408:0x05d0, B:410:0x05d4, B:413:0x05d7, B:417:0x05ec, B:418:0x05f8), top: B:14:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0216 A[Catch: CertPathReviewerException -> 0x05f9, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d0, B:201:0x03dd, B:203:0x03e7, B:107:0x0425, B:210:0x03ef, B:211:0x03fd, B:213:0x03fe, B:214:0x040c, B:221:0x040e, B:222:0x041c, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0434, B:279:0x0441, B:281:0x0442, B:286:0x0453, B:288:0x045d, B:289:0x0462, B:291:0x0468, B:294:0x0476, B:308:0x048b, B:315:0x05df, B:316:0x05eb, B:318:0x0496, B:319:0x04a2, B:320:0x04a3, B:322:0x04a9, B:324:0x04b1, B:326:0x04b7, B:329:0x04c1, B:330:0x04c4, B:332:0x04ca, B:334:0x04da, B:335:0x04de, B:337:0x04e4, B:339:0x04ec, B:342:0x04ef, B:344:0x04f4, B:345:0x04f8, B:347:0x04fe, B:350:0x050e, B:352:0x0516, B:353:0x0519, B:355:0x051f, B:357:0x052b, B:359:0x052f, B:362:0x0532, B:364:0x0535, B:365:0x0541, B:367:0x0546, B:369:0x0550, B:370:0x0553, B:372:0x0559, B:374:0x0569, B:375:0x056d, B:377:0x0573, B:380:0x0583, B:385:0x0587, B:388:0x058a, B:390:0x058d, B:391:0x0593, B:393:0x0599, B:395:0x05ab, B:401:0x05b5, B:403:0x05bb, B:404:0x05be, B:406:0x05c4, B:408:0x05d0, B:410:0x05d4, B:413:0x05d7, B:417:0x05ec, B:418:0x05f8), top: B:14:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x023f A[Catch: CertPathReviewerException -> 0x05f9, TryCatch #3 {CertPathReviewerException -> 0x05f9, blocks: (B:15:0x006f, B:19:0x007f, B:22:0x008c, B:26:0x009c, B:27:0x00a7, B:29:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:41:0x00e1, B:42:0x00ed, B:48:0x00f9, B:51:0x0100, B:52:0x0109, B:54:0x010f, B:57:0x0119, B:63:0x0120, B:65:0x0124, B:69:0x0210, B:71:0x0216, B:72:0x0219, B:74:0x021f, B:76:0x022b, B:83:0x0233, B:81:0x0236, B:87:0x0239, B:89:0x023f, B:90:0x0248, B:92:0x024e, B:101:0x0271, B:102:0x027d, B:103:0x027e, B:109:0x0282, B:111:0x028a, B:112:0x028e, B:114:0x0294, B:117:0x02b6, B:119:0x02c0, B:121:0x02c5, B:122:0x02d1, B:124:0x02d2, B:125:0x02de, B:128:0x02e1, B:129:0x02ee, B:131:0x02f4, B:133:0x031a, B:135:0x0332, B:136:0x0329, B:139:0x0339, B:140:0x033f, B:142:0x0345, B:151:0x034d, B:146:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:163:0x0380, B:172:0x039f, B:174:0x03a9, B:175:0x03ad, B:177:0x03b3, B:191:0x03c3, B:180:0x03d0, B:201:0x03dd, B:203:0x03e7, B:107:0x0425, B:210:0x03ef, B:211:0x03fd, B:213:0x03fe, B:214:0x040c, B:221:0x040e, B:222:0x041c, B:223:0x0133, B:224:0x0137, B:226:0x013d, B:229:0x0153, B:231:0x015d, B:232:0x0162, B:234:0x0168, B:235:0x0176, B:237:0x017c, B:263:0x0188, B:247:0x0195, B:248:0x019b, B:250:0x01a1, B:258:0x01ba, B:239:0x018b, B:246:0x018f, B:265:0x01f3, B:270:0x0203, B:271:0x020f, B:278:0x0434, B:279:0x0441, B:281:0x0442, B:286:0x0453, B:288:0x045d, B:289:0x0462, B:291:0x0468, B:294:0x0476, B:308:0x048b, B:315:0x05df, B:316:0x05eb, B:318:0x0496, B:319:0x04a2, B:320:0x04a3, B:322:0x04a9, B:324:0x04b1, B:326:0x04b7, B:329:0x04c1, B:330:0x04c4, B:332:0x04ca, B:334:0x04da, B:335:0x04de, B:337:0x04e4, B:339:0x04ec, B:342:0x04ef, B:344:0x04f4, B:345:0x04f8, B:347:0x04fe, B:350:0x050e, B:352:0x0516, B:353:0x0519, B:355:0x051f, B:357:0x052b, B:359:0x052f, B:362:0x0532, B:364:0x0535, B:365:0x0541, B:367:0x0546, B:369:0x0550, B:370:0x0553, B:372:0x0559, B:374:0x0569, B:375:0x056d, B:377:0x0573, B:380:0x0583, B:385:0x0587, B:388:0x058a, B:390:0x058d, B:391:0x0593, B:393:0x0599, B:395:0x05ab, B:401:0x05b5, B:403:0x05bb, B:404:0x05be, B:406:0x05c4, B:408:0x05d0, B:410:0x05d4, B:413:0x05d7, B:417:0x05ec, B:418:0x05f8), top: B:14:0x006f, inners: #0, #1, #2, #4, #5, #6, #7, #8, #9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkPolicy() {
        int i;
        int i2;
        int i3;
        PKIXPolicyNode pKIXPolicyNode;
        Set<String> set;
        String str;
        int i4;
        int i5;
        HashSet hashSet;
        String str2;
        int i6;
        int intValueExact;
        int intValueExact2;
        String str3;
        HashSet hashSet2;
        Enumeration objects;
        HashSet hashSet3;
        String str4;
        int i7;
        int i8;
        Set<String> criticalExtensionOIDs;
        String str5 = "CertPathReviewer.policyExtError";
        Set<String> initialPolicies = this.pkixParams.getInitialPolicies();
        int i9 = this.n + 1;
        ArrayList[] arrayListArr = new ArrayList[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            arrayListArr[i10] = new ArrayList();
        }
        HashSet hashSet4 = new HashSet();
        hashSet4.add(RFC3280CertPathUtilities.ANY_POLICY);
        PKIXPolicyNode pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), 0, hashSet4, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
        arrayListArr[0].add(pKIXPolicyNode2);
        if (this.pkixParams.isExplicitPolicyRequired()) {
            i2 = 0;
            i = 1;
        } else {
            i = 1;
            i2 = this.n + 1;
        }
        int i11 = this.pkixParams.isAnyPolicyInhibited() ? 0 : this.n + i;
        int i12 = this.pkixParams.isPolicyMappingInhibited() ? 0 : this.n + i;
        try {
            int size = this.certs.size() - i;
            PKIXPolicyNode pKIXPolicyNode3 = pKIXPolicyNode2;
            X509Certificate x509Certificate = null;
            HashSet hashSet5 = null;
            while (size >= 0) {
                int i13 = this.n - size;
                X509Certificate x509Certificate2 = (X509Certificate) this.certs.get(size);
                int i14 = i9;
                try {
                    ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate2, CERTIFICATE_POLICIES);
                    if (aSN1Sequence == null || pKIXPolicyNode3 == null) {
                        set = initialPolicies;
                        str = str5;
                        i4 = i11;
                        i5 = i12;
                        pKIXPolicyNode3 = pKIXPolicyNode3;
                    } else {
                        Enumeration objects2 = aSN1Sequence.getObjects();
                        set = initialPolicies;
                        HashSet hashSet6 = new HashSet();
                        while (objects2.hasMoreElements()) {
                            PolicyInformation policyInformation = PolicyInformation.getInstance(objects2.nextElement());
                            PKIXPolicyNode pKIXPolicyNode4 = pKIXPolicyNode3;
                            ASN1ObjectIdentifier policyIdentifier = policyInformation.getPolicyIdentifier();
                            String str6 = str5;
                            hashSet6.add(policyIdentifier.getId());
                            if (!RFC3280CertPathUtilities.ANY_POLICY.equals(policyIdentifier.getId())) {
                                try {
                                    Set qualifierSet = getQualifierSet(policyInformation.getPolicyQualifiers());
                                    if (!processCertD1i(i13, arrayListArr, policyIdentifier, qualifierSet)) {
                                        processCertD1ii(i13, arrayListArr, policyIdentifier, qualifierSet);
                                    }
                                } catch (CertPathValidatorException e) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e, this.certPath, size);
                                }
                            }
                            pKIXPolicyNode3 = pKIXPolicyNode4;
                            str5 = str6;
                        }
                        str = str5;
                        PKIXPolicyNode pKIXPolicyNode5 = pKIXPolicyNode3;
                        if (hashSet5 != null && !hashSet5.contains(RFC3280CertPathUtilities.ANY_POLICY)) {
                            hashSet2 = new HashSet();
                            for (Object obj : hashSet5) {
                                if (hashSet6.contains(obj)) {
                                    hashSet2.add(obj);
                                }
                            }
                            if (i11 <= 0) {
                                if (i13 < this.n && isSelfIssued(x509Certificate2)) {
                                }
                                i4 = i11;
                                i5 = i12;
                                hashSet3 = hashSet2;
                                pKIXPolicyNode3 = pKIXPolicyNode5;
                                for (i8 = i13 - 1; i8 >= 0; i8--) {
                                    ArrayList arrayList = arrayListArr[i8];
                                    for (int i15 = 0; i15 < arrayList.size(); i15++) {
                                        PKIXPolicyNode pKIXPolicyNode6 = (PKIXPolicyNode) arrayList.get(i15);
                                        if (!pKIXPolicyNode6.hasChildren()) {
                                            PKIXPolicyNode removePolicyNode = removePolicyNode(pKIXPolicyNode3, arrayListArr, pKIXPolicyNode6);
                                            pKIXPolicyNode3 = removePolicyNode;
                                            if (removePolicyNode == null) {
                                                break;
                                            }
                                        }
                                    }
                                }
                                criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                if (criticalExtensionOIDs != null) {
                                    boolean contains = criticalExtensionOIDs.contains(CERTIFICATE_POLICIES);
                                    ArrayList arrayList2 = arrayListArr[i13];
                                    for (int i16 = 0; i16 < arrayList2.size(); i16++) {
                                        ((PKIXPolicyNode) arrayList2.get(i16)).setCritical(contains);
                                    }
                                }
                                hashSet5 = hashSet3;
                            }
                            objects = aSN1Sequence.getObjects();
                            while (objects.hasMoreElements()) {
                                PolicyInformation policyInformation2 = PolicyInformation.getInstance(objects.nextElement());
                                if (RFC3280CertPathUtilities.ANY_POLICY.equals(policyInformation2.getPolicyIdentifier().getId())) {
                                    try {
                                        Set qualifierSet2 = getQualifierSet(policyInformation2.getPolicyQualifiers());
                                        ArrayList arrayList3 = arrayListArr[i13 - 1];
                                        hashSet3 = hashSet2;
                                        for (int i17 = 0; i17 < arrayList3.size(); i17++) {
                                            PKIXPolicyNode pKIXPolicyNode7 = (PKIXPolicyNode) arrayList3.get(i17);
                                            for (Object obj2 : pKIXPolicyNode7.getExpectedPolicies()) {
                                                ArrayList arrayList4 = arrayList3;
                                                int i18 = i11;
                                                if (obj2 instanceof String) {
                                                    str4 = (String) obj2;
                                                } else if (obj2 instanceof ASN1ObjectIdentifier) {
                                                    str4 = ((ASN1ObjectIdentifier) obj2).getId();
                                                } else {
                                                    arrayList3 = arrayList4;
                                                    i11 = i18;
                                                }
                                                Iterator children = pKIXPolicyNode7.getChildren();
                                                boolean z = false;
                                                while (children.hasNext()) {
                                                    Iterator it = children;
                                                    if (str4.equals(((PKIXPolicyNode) children.next()).getValidPolicy())) {
                                                        z = true;
                                                    }
                                                    children = it;
                                                }
                                                if (z) {
                                                    i7 = i12;
                                                } else {
                                                    HashSet hashSet7 = new HashSet();
                                                    hashSet7.add(str4);
                                                    i7 = i12;
                                                    PKIXPolicyNode pKIXPolicyNode8 = new PKIXPolicyNode(new ArrayList(), i13, hashSet7, pKIXPolicyNode7, qualifierSet2, str4, false);
                                                    pKIXPolicyNode7.addChild(pKIXPolicyNode8);
                                                    arrayListArr[i13].add(pKIXPolicyNode8);
                                                }
                                                arrayList3 = arrayList4;
                                                i11 = i18;
                                                i12 = i7;
                                            }
                                        }
                                        i4 = i11;
                                        i5 = i12;
                                        pKIXPolicyNode3 = pKIXPolicyNode5;
                                        while (i8 >= 0) {
                                        }
                                        criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                        if (criticalExtensionOIDs != null) {
                                        }
                                        hashSet5 = hashSet3;
                                    } catch (CertPathValidatorException e2) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e2, this.certPath, size);
                                    }
                                }
                            }
                            i4 = i11;
                            i5 = i12;
                            hashSet3 = hashSet2;
                            pKIXPolicyNode3 = pKIXPolicyNode5;
                            while (i8 >= 0) {
                            }
                            criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                            if (criticalExtensionOIDs != null) {
                            }
                            hashSet5 = hashSet3;
                        }
                        hashSet2 = hashSet6;
                        if (i11 <= 0) {
                        }
                        objects = aSN1Sequence.getObjects();
                        while (objects.hasMoreElements()) {
                        }
                        i4 = i11;
                        i5 = i12;
                        hashSet3 = hashSet2;
                        pKIXPolicyNode3 = pKIXPolicyNode5;
                        while (i8 >= 0) {
                        }
                        criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs != null) {
                        }
                        hashSet5 = hashSet3;
                    }
                    if (aSN1Sequence == null) {
                        pKIXPolicyNode3 = null;
                    }
                    if (i2 <= 0 && pKIXPolicyNode3 == null) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidPolicyTree"));
                    }
                    if (i13 != this.n) {
                        try {
                            ASN1Primitive extensionValue = getExtensionValue(x509Certificate2, POLICY_MAPPINGS);
                            if (extensionValue != null) {
                                ASN1Sequence aSN1Sequence2 = (ASN1Sequence) extensionValue;
                                int i19 = 0;
                                while (i19 < aSN1Sequence2.size()) {
                                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence2.getObjectAt(i19);
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(0);
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(1);
                                    boolean equals = RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier.getId());
                                    ASN1Sequence aSN1Sequence4 = aSN1Sequence2;
                                    if (equals) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                    }
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier2.getId())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                    }
                                    i19++;
                                    aSN1Sequence2 = aSN1Sequence4;
                                }
                            }
                            if (extensionValue != null) {
                                ASN1Sequence aSN1Sequence5 = (ASN1Sequence) extensionValue;
                                HashMap hashMap = new HashMap();
                                HashSet<String> hashSet8 = new HashSet();
                                int i20 = 0;
                                while (i20 < aSN1Sequence5.size()) {
                                    ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(i20);
                                    ASN1Sequence aSN1Sequence7 = aSN1Sequence5;
                                    String id2 = ((ASN1ObjectIdentifier) aSN1Sequence6.getObjectAt(0)).getId();
                                    HashSet hashSet9 = hashSet5;
                                    String id3 = ((ASN1ObjectIdentifier) aSN1Sequence6.getObjectAt(1)).getId();
                                    if (hashMap.containsKey(id2)) {
                                        ((Set) hashMap.get(id2)).add(id3);
                                    } else {
                                        HashSet hashSet10 = new HashSet();
                                        hashSet10.add(id3);
                                        hashMap.put(id2, hashSet10);
                                        hashSet8.add(id2);
                                    }
                                    i20++;
                                    aSN1Sequence5 = aSN1Sequence7;
                                    hashSet5 = hashSet9;
                                }
                                hashSet = hashSet5;
                                for (String str7 : hashSet8) {
                                    if (i5 > 0) {
                                        try {
                                            try {
                                                prepareNextCertB1(i13, arrayListArr, str7, hashMap, x509Certificate2);
                                                str3 = str;
                                            } catch (CertPathValidatorException e3) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e3, this.certPath, size);
                                            }
                                        } catch (AnnotatedException e4) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str), e4, this.certPath, size);
                                        }
                                    } else {
                                        str3 = str;
                                        if (i5 <= 0) {
                                            pKIXPolicyNode3 = prepareNextCertB2(i13, arrayListArr, str7, pKIXPolicyNode3);
                                        }
                                    }
                                    str = str3;
                                }
                            } else {
                                hashSet = hashSet5;
                            }
                            str2 = str;
                            if (isSelfIssued(x509Certificate2)) {
                                i6 = i4;
                                i12 = i5;
                            } else {
                                if (i2 != 0) {
                                    i2--;
                                }
                                i12 = i5 != 0 ? i5 - 1 : i5;
                                i6 = i4 != 0 ? i4 - 1 : i4;
                            }
                            try {
                                ASN1Sequence aSN1Sequence8 = (ASN1Sequence) getExtensionValue(x509Certificate2, POLICY_CONSTRAINTS);
                                if (aSN1Sequence8 != null) {
                                    Enumeration objects3 = aSN1Sequence8.getObjects();
                                    while (objects3.hasMoreElements()) {
                                        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects3.nextElement();
                                        int tagNo = aSN1TaggedObject.getTagNo();
                                        if (tagNo == 0) {
                                            int intValueExact3 = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact();
                                            if (intValueExact3 < i2) {
                                                i2 = intValueExact3;
                                            }
                                        } else if (tagNo == 1 && (intValueExact2 = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact()) < i12) {
                                            i12 = intValueExact2;
                                        }
                                    }
                                }
                                try {
                                    ASN1Integer aSN1Integer = (ASN1Integer) getExtensionValue(x509Certificate2, INHIBIT_ANY_POLICY);
                                    if (aSN1Integer != null && (intValueExact = aSN1Integer.intValueExact()) < i6) {
                                        i6 = intValueExact;
                                    }
                                } catch (AnnotatedException unused) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyInhibitExtError"), this.certPath, size);
                                }
                            } catch (AnnotatedException unused2) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
                            }
                        } catch (AnnotatedException e5) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyMapExtError"), e5, this.certPath, size);
                        }
                    } else {
                        hashSet = hashSet5;
                        str2 = str;
                        i6 = i4;
                        i12 = i5;
                    }
                    size--;
                    x509Certificate = x509Certificate2;
                    str5 = str2;
                    hashSet5 = hashSet;
                    i9 = i14;
                    i11 = i6;
                    initialPolicies = set;
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str5), e6, this.certPath, size);
                }
            }
            Set<String> set2 = initialPolicies;
            int i21 = i9;
            PKIXPolicyNode pKIXPolicyNode9 = pKIXPolicyNode3;
            if (!isSelfIssued(x509Certificate) && i2 > 0) {
                i2--;
            }
            try {
                ASN1Sequence aSN1Sequence9 = (ASN1Sequence) getExtensionValue(x509Certificate, POLICY_CONSTRAINTS);
                if (aSN1Sequence9 != null) {
                    Enumeration objects4 = aSN1Sequence9.getObjects();
                    int i22 = i2;
                    while (objects4.hasMoreElements()) {
                        ASN1TaggedObject aSN1TaggedObject2 = (ASN1TaggedObject) objects4.nextElement();
                        if (aSN1TaggedObject2.getTagNo() == 0 && ASN1Integer.getInstance(aSN1TaggedObject2, false).intValueExact() == 0) {
                            i22 = 0;
                        }
                    }
                    i3 = 0;
                    i2 = i22;
                } else {
                    i3 = 0;
                }
                if (pKIXPolicyNode9 == null) {
                    if (this.pkixParams.isExplicitPolicyRequired()) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                    }
                    pKIXPolicyNode = null;
                } else if (isAnyPolicy(set2)) {
                    if (this.pkixParams.isExplicitPolicyRequired()) {
                        if (hashSet5.isEmpty()) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                        }
                        HashSet hashSet11 = new HashSet();
                        int i23 = i3;
                        while (true) {
                            int i24 = i21;
                            if (i23 >= i24) {
                                break;
                            }
                            ArrayList arrayList5 = arrayListArr[i23];
                            for (int i25 = i3; i25 < arrayList5.size(); i25++) {
                                PKIXPolicyNode pKIXPolicyNode10 = (PKIXPolicyNode) arrayList5.get(i25);
                                if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode10.getValidPolicy())) {
                                    Iterator children2 = pKIXPolicyNode10.getChildren();
                                    while (children2.hasNext()) {
                                        hashSet11.add(children2.next());
                                    }
                                }
                            }
                            i23++;
                            i21 = i24;
                        }
                        Iterator it2 = hashSet11.iterator();
                        while (it2.hasNext()) {
                            hashSet5.contains(((PKIXPolicyNode) it2.next()).getValidPolicy());
                        }
                        if (pKIXPolicyNode9 != null) {
                            pKIXPolicyNode = pKIXPolicyNode9;
                            for (int i26 = this.n - 1; i26 >= 0; i26--) {
                                ArrayList arrayList6 = arrayListArr[i26];
                                for (int i27 = i3; i27 < arrayList6.size(); i27++) {
                                    PKIXPolicyNode pKIXPolicyNode11 = (PKIXPolicyNode) arrayList6.get(i27);
                                    if (!pKIXPolicyNode11.hasChildren()) {
                                        pKIXPolicyNode = removePolicyNode(pKIXPolicyNode, arrayListArr, pKIXPolicyNode11);
                                    }
                                }
                            }
                        }
                    }
                    pKIXPolicyNode = pKIXPolicyNode9;
                } else {
                    HashSet<PKIXPolicyNode> hashSet12 = new HashSet();
                    for (int i28 = i3; i28 < i21; i28++) {
                        ArrayList arrayList7 = arrayListArr[i28];
                        for (int i29 = i3; i29 < arrayList7.size(); i29++) {
                            PKIXPolicyNode pKIXPolicyNode12 = (PKIXPolicyNode) arrayList7.get(i29);
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode12.getValidPolicy())) {
                                Iterator children3 = pKIXPolicyNode12.getChildren();
                                while (children3.hasNext()) {
                                    PKIXPolicyNode pKIXPolicyNode13 = (PKIXPolicyNode) children3.next();
                                    if (!RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode13.getValidPolicy())) {
                                        hashSet12.add(pKIXPolicyNode13);
                                    }
                                }
                            }
                        }
                    }
                    pKIXPolicyNode = pKIXPolicyNode9;
                    for (PKIXPolicyNode pKIXPolicyNode14 : hashSet12) {
                        Set<String> set3 = set2;
                        if (!set3.contains(pKIXPolicyNode14.getValidPolicy())) {
                            pKIXPolicyNode = removePolicyNode(pKIXPolicyNode, arrayListArr, pKIXPolicyNode14);
                        }
                        set2 = set3;
                    }
                    if (pKIXPolicyNode != null) {
                        for (int i30 = this.n - 1; i30 >= 0; i30--) {
                            ArrayList arrayList8 = arrayListArr[i30];
                            for (int i31 = i3; i31 < arrayList8.size(); i31++) {
                                PKIXPolicyNode pKIXPolicyNode15 = (PKIXPolicyNode) arrayList8.get(i31);
                                if (!pKIXPolicyNode15.hasChildren()) {
                                    pKIXPolicyNode = removePolicyNode(pKIXPolicyNode, arrayListArr, pKIXPolicyNode15);
                                }
                            }
                        }
                    }
                }
                if (i2 <= 0 && pKIXPolicyNode == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicy"));
                }
            } catch (AnnotatedException unused3) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:28|(2:130|131)(2:30|(2:124|125)(3:32|(2:36|(1:38))|39))|(2:40|41)|42|(18:86|87|(15:89|90|91|(11:93|94|(2:97|95)|98|99|(2:102|100)|103|104|105|106|107)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107)|117|90|91|(0)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107)(1:44)|(1:85)(1:48)|49|(7:51|(2:53|(1:55))(1:83)|56|57|(2:59|(1:61))(1:80)|62|(1:66))(1:84)|67|68|69|71|72|74|75) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:86|87|(3:(15:89|90|91|(11:93|94|(2:97|95)|98|99|(2:102|100)|103|104|105|106|107)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107)|106|107)|117|90|91|(0)|114|94|(1:95)|98|99|(1:100)|103|104|105) */
    /* JADX WARN: Can't wrap try/catch for region: R(18:86|87|(15:89|90|91|(11:93|94|(2:97|95)|98|99|(2:102|100)|103|104|105|106|107)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107)|117|90|91|(0)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107) */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x032a, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x032b, code lost:
    
        r18 = r4;
        r13 = r6;
        r19 = r7;
        r12 = r8;
        r11 = r9;
        r15 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02b0, code lost:
    
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x03ea, code lost:
    
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.pubKeyError"), r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x03e8, code lost:
    
        r7 = r19;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02f6 A[LOOP:2: B:100:0x02f0->B:102:0x02f6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0289 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02ab A[Catch: AnnotatedException -> 0x02b0, TRY_LEAVE, TryCatch #18 {AnnotatedException -> 0x02b0, blocks: (B:91:0x02a3, B:93:0x02ab), top: B:90:0x02a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02cd A[LOOP:1: B:95:0x02c7->B:97:0x02cd, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkSignatures() {
        TrustAnchor trustAnchor;
        TrustAnchor trustAnchor2;
        X500Principal x500Principal;
        X509Certificate x509Certificate;
        PublicKey publicKey;
        int size;
        ErrorBundle errorBundle;
        ErrorBundle errorBundle2;
        CRLDistPoint cRLDistPoint;
        AuthorityInformationAccess authorityInformationAccess;
        Iterator it;
        Iterator it2;
        int i;
        int i2;
        PublicKey publicKey2;
        X500Principal x500Principal2;
        TrustAnchor trustAnchor3;
        X509Certificate x509Certificate2;
        ASN1Primitive extensionValue;
        ASN1Primitive extensionValue2;
        char c;
        char c2;
        char c3;
        AuthorityKeyIdentifier authorityKeyIdentifier;
        GeneralNames authorityCertIssuer;
        boolean[] keyUsage;
        char c4 = 2;
        char c5 = 0;
        char c6 = 1;
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathValidDate", new Object[]{new TrustedInput(this.validDate), new TrustedInput(new Date())}));
        try {
            List list = this.certs;
            X509Certificate x509Certificate3 = (X509Certificate) list.get(list.size() - 1);
            Collection trustAnchors = getTrustAnchors(x509Certificate3, this.pkixParams.getTrustAnchors());
            if (trustAnchors.size() > 1) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.conflictingTrustAnchors", new Object[]{Integers.valueOf(trustAnchors.size()), new UntrustedInput(x509Certificate3.getIssuerX500Principal())}));
            } else if (trustAnchors.isEmpty()) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noTrustAnchorFound", new Object[]{new UntrustedInput(x509Certificate3.getIssuerX500Principal()), Integers.valueOf(this.pkixParams.getTrustAnchors().size())}));
            } else {
                trustAnchor = (TrustAnchor) trustAnchors.iterator().next();
                try {
                    try {
                        CertPathValidatorUtilities.verifyX509Certificate(x509Certificate3, trustAnchor.getTrustedCert() != null ? trustAnchor.getTrustedCert().getPublicKey() : trustAnchor.getCAPublicKey(), this.pkixParams.getSigProvider());
                    } catch (SignatureException unused) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustButInvalidCert"));
                    } catch (Exception unused2) {
                    }
                } catch (CertPathReviewerException e) {
                    e = e;
                    addError(e.getErrorMessage());
                    trustAnchor2 = trustAnchor;
                    if (trustAnchor2 != null) {
                    }
                    if (trustAnchor2 != null) {
                    }
                    X509Certificate x509Certificate4 = x509Certificate;
                    X500Principal x500Principal3 = x500Principal;
                    PublicKey publicKey3 = publicKey;
                    size = this.certs.size() - 1;
                    while (size >= 0) {
                    }
                    this.trustAnchor = trustAnchor2;
                    this.subjectPublicKey = publicKey3;
                } catch (Throwable th) {
                    th = th;
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknown", new Object[]{new UntrustedInput(th.getMessage()), new UntrustedInput(th)}));
                    trustAnchor2 = trustAnchor;
                    if (trustAnchor2 != null) {
                    }
                    if (trustAnchor2 != null) {
                    }
                    X509Certificate x509Certificate42 = x509Certificate;
                    X500Principal x500Principal32 = x500Principal;
                    PublicKey publicKey32 = publicKey;
                    size = this.certs.size() - 1;
                    while (size >= 0) {
                    }
                    this.trustAnchor = trustAnchor2;
                    this.subjectPublicKey = publicKey32;
                }
            }
            trustAnchor = null;
        } catch (CertPathReviewerException e2) {
            e = e2;
            trustAnchor = null;
        } catch (Throwable th2) {
            th = th2;
            trustAnchor = null;
        }
        trustAnchor2 = trustAnchor;
        if (trustAnchor2 != null) {
            X509Certificate trustedCert = trustAnchor2.getTrustedCert();
            try {
                x500Principal = trustedCert != null ? getSubjectPrincipal(trustedCert) : new X500Principal(trustAnchor2.getCAName());
            } catch (IllegalArgumentException unused3) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustDNInvalid", new Object[]{new UntrustedInput(trustAnchor2.getCAName())}));
                x500Principal = null;
            }
            if (trustedCert != null && (keyUsage = trustedCert.getKeyUsage()) != null && !keyUsage[5]) {
                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustKeyUsage"));
            }
        } else {
            x500Principal = null;
        }
        if (trustAnchor2 != null) {
            x509Certificate = trustAnchor2.getTrustedCert();
            publicKey = x509Certificate != null ? x509Certificate.getPublicKey() : trustAnchor2.getCAPublicKey();
            try {
                AlgorithmIdentifier algorithmIdentifier = getAlgorithmIdentifier(publicKey);
                algorithmIdentifier.getAlgorithm();
                algorithmIdentifier.getParameters();
            } catch (CertPathValidatorException unused4) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustPubKeyError"));
            }
        } else {
            x509Certificate = null;
            publicKey = null;
        }
        X509Certificate x509Certificate422 = x509Certificate;
        X500Principal x500Principal322 = x500Principal;
        PublicKey publicKey322 = publicKey;
        size = this.certs.size() - 1;
        while (size >= 0) {
            int i3 = this.n - size;
            X509Certificate x509Certificate5 = (X509Certificate) this.certs.get(size);
            if (publicKey322 != null) {
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(x509Certificate5, publicKey322, this.pkixParams.getSigProvider());
                } catch (GeneralSecurityException e3) {
                    Object[] objArr = new Object[3];
                    objArr[c5] = e3.getMessage();
                    objArr[c6] = e3;
                    objArr[c4] = e3.getClass().getName();
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr);
                    addError(errorBundle, size);
                    x509Certificate5.checkValidity(this.validDate);
                    if (this.pkixParams.isRevocationEnabled()) {
                    }
                    if (x500Principal2 != null) {
                    }
                    c = 2;
                    c2 = 0;
                    if (i == this.n) {
                    }
                    x500Principal322 = x509Certificate2.getSubjectX500Principal();
                    publicKey322 = getNextWorkingKey(this.certs, i2);
                    AlgorithmIdentifier algorithmIdentifier2 = getAlgorithmIdentifier(publicKey322);
                    algorithmIdentifier2.getAlgorithm();
                    algorithmIdentifier2.getParameters();
                    size = i2 - 1;
                    c6 = c3;
                    c5 = c2;
                    trustAnchor2 = trustAnchor3;
                    x509Certificate422 = x509Certificate2;
                    c4 = c;
                }
            } else if (isSelfIssued(x509Certificate5)) {
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(x509Certificate5, x509Certificate5.getPublicKey(), this.pkixParams.getSigProvider());
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.rootKeyIsValidButNotATrustAnchor"), size);
                } catch (GeneralSecurityException e4) {
                    Object[] objArr2 = new Object[3];
                    objArr2[c5] = e4.getMessage();
                    objArr2[c6] = e4;
                    objArr2[c4] = e4.getClass().getName();
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr2);
                    addError(errorBundle, size);
                    x509Certificate5.checkValidity(this.validDate);
                    if (this.pkixParams.isRevocationEnabled()) {
                    }
                    if (x500Principal2 != null) {
                    }
                    c = 2;
                    c2 = 0;
                    if (i == this.n) {
                    }
                    x500Principal322 = x509Certificate2.getSubjectX500Principal();
                    publicKey322 = getNextWorkingKey(this.certs, i2);
                    AlgorithmIdentifier algorithmIdentifier22 = getAlgorithmIdentifier(publicKey322);
                    algorithmIdentifier22.getAlgorithm();
                    algorithmIdentifier22.getParameters();
                    size = i2 - 1;
                    c6 = c3;
                    c5 = c2;
                    trustAnchor2 = trustAnchor3;
                    x509Certificate422 = x509Certificate2;
                    c4 = c;
                }
            } else {
                ErrorBundle errorBundle3 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.NoIssuerPublicKey");
                byte[] extensionValue3 = x509Certificate5.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if (extensionValue3 != null && (authorityCertIssuer = (authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(DEROctetString.getInstance(extensionValue3).getOctets())).getAuthorityCertIssuer()) != null) {
                    GeneralName generalName = authorityCertIssuer.getNames()[c5];
                    BigInteger authorityCertSerialNumber = authorityKeyIdentifier.getAuthorityCertSerialNumber();
                    if (authorityCertSerialNumber != null) {
                        Object[] objArr3 = new Object[7];
                        objArr3[c5] = new LocaleString(RESOURCE_NAME, "missingIssuer");
                        objArr3[1] = " \"";
                        objArr3[2] = generalName;
                        objArr3[3] = "\" ";
                        objArr3[4] = new LocaleString(RESOURCE_NAME, "missingSerial");
                        objArr3[5] = " ";
                        objArr3[6] = authorityCertSerialNumber;
                        errorBundle3.setExtraArguments(objArr3);
                    }
                }
                addError(errorBundle3, size);
            }
            try {
                x509Certificate5.checkValidity(this.validDate);
            } catch (CertificateExpiredException unused5) {
                Object[] objArr4 = new Object[1];
                objArr4[c5] = new TrustedInput(x509Certificate5.getNotAfter());
                errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateExpired", objArr4);
                addError(errorBundle2, size);
                if (this.pkixParams.isRevocationEnabled()) {
                }
                if (x500Principal2 != null) {
                }
                c = 2;
                c2 = 0;
                if (i == this.n) {
                }
                x500Principal322 = x509Certificate2.getSubjectX500Principal();
                publicKey322 = getNextWorkingKey(this.certs, i2);
                AlgorithmIdentifier algorithmIdentifier222 = getAlgorithmIdentifier(publicKey322);
                algorithmIdentifier222.getAlgorithm();
                algorithmIdentifier222.getParameters();
                size = i2 - 1;
                c6 = c3;
                c5 = c2;
                trustAnchor2 = trustAnchor3;
                x509Certificate422 = x509Certificate2;
                c4 = c;
            } catch (CertificateNotYetValidException unused6) {
                Object[] objArr5 = new Object[1];
                objArr5[c5] = new TrustedInput(x509Certificate5.getNotBefore());
                errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateNotYetValid", objArr5);
                addError(errorBundle2, size);
                if (this.pkixParams.isRevocationEnabled()) {
                }
                if (x500Principal2 != null) {
                }
                c = 2;
                c2 = 0;
                if (i == this.n) {
                }
                x500Principal322 = x509Certificate2.getSubjectX500Principal();
                publicKey322 = getNextWorkingKey(this.certs, i2);
                AlgorithmIdentifier algorithmIdentifier2222 = getAlgorithmIdentifier(publicKey322);
                algorithmIdentifier2222.getAlgorithm();
                algorithmIdentifier2222.getParameters();
                size = i2 - 1;
                c6 = c3;
                c5 = c2;
                trustAnchor2 = trustAnchor3;
                x509Certificate422 = x509Certificate2;
                c4 = c;
            }
            if (this.pkixParams.isRevocationEnabled()) {
                try {
                    extensionValue2 = getExtensionValue(x509Certificate5, CRL_DIST_POINTS);
                } catch (AnnotatedException unused7) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPtExtError"), size);
                }
                try {
                    if (extensionValue2 != null) {
                        cRLDistPoint = CRLDistPoint.getInstance(extensionValue2);
                        extensionValue = getExtensionValue(x509Certificate5, AUTH_INFO_ACCESS);
                        if (extensionValue != null) {
                            authorityInformationAccess = AuthorityInformationAccess.getInstance(extensionValue);
                            Vector cRLDistUrls = getCRLDistUrls(cRLDistPoint);
                            Vector oCSPUrls = getOCSPUrls(authorityInformationAccess);
                            it = cRLDistUrls.iterator();
                            while (it.hasNext()) {
                                Object[] objArr6 = new Object[1];
                                objArr6[c5] = new UntrustedUrlInput(it.next());
                                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPoint", objArr6), size);
                                x509Certificate5 = x509Certificate5;
                            }
                            X509Certificate x509Certificate6 = x509Certificate5;
                            it2 = oCSPUrls.iterator();
                            while (it2.hasNext()) {
                                Object[] objArr7 = new Object[1];
                                objArr7[c5] = new UntrustedUrlInput(it2.next());
                                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ocspLocation", objArr7), size);
                            }
                            x509Certificate2 = x509Certificate6;
                            i = i3;
                            i2 = size;
                            publicKey2 = publicKey322;
                            x500Principal2 = x500Principal322;
                            trustAnchor3 = trustAnchor2;
                            checkRevocation(this.pkixParams, x509Certificate2, this.validDate, x509Certificate422, publicKey322, cRLDistUrls, oCSPUrls, i2);
                        }
                        authorityInformationAccess = null;
                        Vector cRLDistUrls2 = getCRLDistUrls(cRLDistPoint);
                        Vector oCSPUrls2 = getOCSPUrls(authorityInformationAccess);
                        it = cRLDistUrls2.iterator();
                        while (it.hasNext()) {
                        }
                        X509Certificate x509Certificate62 = x509Certificate5;
                        it2 = oCSPUrls2.iterator();
                        while (it2.hasNext()) {
                        }
                        x509Certificate2 = x509Certificate62;
                        i = i3;
                        i2 = size;
                        publicKey2 = publicKey322;
                        x500Principal2 = x500Principal322;
                        trustAnchor3 = trustAnchor2;
                        checkRevocation(this.pkixParams, x509Certificate2, this.validDate, x509Certificate422, publicKey322, cRLDistUrls2, oCSPUrls2, i2);
                    }
                    checkRevocation(this.pkixParams, x509Certificate2, this.validDate, x509Certificate422, publicKey322, cRLDistUrls2, oCSPUrls2, i2);
                } catch (CertPathReviewerException e5) {
                    e = e5;
                    addError(e.getErrorMessage(), i2);
                    if (x500Principal2 != null) {
                    }
                    c = 2;
                    c2 = 0;
                    if (i == this.n) {
                    }
                    x500Principal322 = x509Certificate2.getSubjectX500Principal();
                    publicKey322 = getNextWorkingKey(this.certs, i2);
                    AlgorithmIdentifier algorithmIdentifier22222 = getAlgorithmIdentifier(publicKey322);
                    algorithmIdentifier22222.getAlgorithm();
                    algorithmIdentifier22222.getParameters();
                    size = i2 - 1;
                    c6 = c3;
                    c5 = c2;
                    trustAnchor2 = trustAnchor3;
                    x509Certificate422 = x509Certificate2;
                    c4 = c;
                }
                cRLDistPoint = null;
                extensionValue = getExtensionValue(x509Certificate5, AUTH_INFO_ACCESS);
                if (extensionValue != null) {
                }
                authorityInformationAccess = null;
                Vector cRLDistUrls22 = getCRLDistUrls(cRLDistPoint);
                Vector oCSPUrls22 = getOCSPUrls(authorityInformationAccess);
                it = cRLDistUrls22.iterator();
                while (it.hasNext()) {
                }
                X509Certificate x509Certificate622 = x509Certificate5;
                it2 = oCSPUrls22.iterator();
                while (it2.hasNext()) {
                }
                x509Certificate2 = x509Certificate622;
                i = i3;
                i2 = size;
                publicKey2 = publicKey322;
                x500Principal2 = x500Principal322;
                trustAnchor3 = trustAnchor2;
            } else {
                x509Certificate2 = x509Certificate5;
                i = i3;
                i2 = size;
                publicKey2 = publicKey322;
                x500Principal2 = x500Principal322;
                trustAnchor3 = trustAnchor2;
            }
            if (x500Principal2 != null || x509Certificate2.getIssuerX500Principal().equals(x500Principal2)) {
                c = 2;
                c2 = 0;
            } else {
                c = 2;
                c2 = 0;
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certWrongIssuer", new Object[]{x500Principal2.getName(), x509Certificate2.getIssuerX500Principal().getName()}), i2);
            }
            if (i == this.n) {
                if (x509Certificate2 != null) {
                    c3 = 1;
                    if (x509Certificate2.getVersion() == 1) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i2);
                    }
                } else {
                    c3 = 1;
                }
                try {
                    BasicConstraints basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate2, BASIC_CONSTRAINTS));
                    if (basicConstraints == null) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBasicConstraints"), i2);
                    } else if (!basicConstraints.isCA()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i2);
                    }
                } catch (AnnotatedException unused8) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.errorProcesingBC"), i2);
                }
                boolean[] keyUsage2 = x509Certificate2.getKeyUsage();
                if (keyUsage2 != null && !keyUsage2[5]) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCertSign"), i2);
                }
            } else {
                c3 = 1;
            }
            x500Principal322 = x509Certificate2.getSubjectX500Principal();
            publicKey322 = getNextWorkingKey(this.certs, i2);
            AlgorithmIdentifier algorithmIdentifier222222 = getAlgorithmIdentifier(publicKey322);
            algorithmIdentifier222222.getAlgorithm();
            algorithmIdentifier222222.getParameters();
            size = i2 - 1;
            c6 = c3;
            c5 = c2;
            trustAnchor2 = trustAnchor3;
            x509Certificate422 = x509Certificate2;
            c4 = c;
        }
        this.trustAnchor = trustAnchor2;
        this.subjectPublicKey = publicKey322;
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals(ProxyConfig.MATCH_HTTP) && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (QCStatement.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (QCStatement.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            addNotification(monetaryValue.getCurrency().isAlphabetic() ? new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), monetaryValue}) : new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue}), i);
                        } else {
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                            z = true;
                        }
                    }
                }
                addNotification(errorBundle, i);
            }
            return true ^ z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:176:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void checkCRLs(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, int i) throws CertPathReviewerException {
        Iterator it;
        X509CRL x509crl;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        String str;
        boolean[] keyUsage;
        X509CRL x509crl2;
        Iterator it2;
        String str2;
        X509CRL crl;
        boolean z6;
        Iterator it3;
        X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        try {
            x509CRLStoreSelector.addIssuerName(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            x509CRLStoreSelector.setCertificateChecking(x509Certificate);
            try {
                Set findCRLs = CRL_UTIL.findCRLs(x509CRLStoreSelector, pKIXParameters);
                Iterator it4 = findCRLs.iterator();
                if (findCRLs.isEmpty()) {
                    Iterator it5 = CRL_UTIL.findCRLs(new X509CRLStoreSelector(), pKIXParameters).iterator();
                    ArrayList arrayList = new ArrayList();
                    while (it5.hasNext()) {
                        arrayList.add(((X509CRL) it5.next()).getIssuerX500Principal());
                    }
                    it3 = it4;
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCrlInCertstore", new Object[]{new UntrustedInput(x509CRLStoreSelector.getIssuerNames()), new UntrustedInput(arrayList), Integers.valueOf(arrayList.size())}), i);
                } else {
                    it3 = it4;
                }
                it = it3;
            } catch (AnnotatedException e) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlExtractionError", new Object[]{e.getCause().getMessage(), e.getCause(), e.getCause().getClass().getName()}), i);
                it = new ArrayList().iterator();
            }
            X509CRL x509crl3 = null;
            while (it.hasNext()) {
                x509crl3 = (X509CRL) it.next();
                if (x509crl3.getNextUpdate() == null || pKIXParameters.getDate().before(x509crl3.getNextUpdate())) {
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.localValidCRL", new Object[]{new TrustedInput(x509crl3.getThisUpdate()), new TrustedInput(x509crl3.getNextUpdate())}), i);
                    x509crl = x509crl3;
                    z = true;
                    break;
                }
                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.localInvalidCRL", new Object[]{new TrustedInput(x509crl3.getThisUpdate()), new TrustedInput(x509crl3.getNextUpdate())}), i);
            }
            x509crl = x509crl3;
            z = false;
            if (!z) {
                Iterator it6 = vector.iterator();
                boolean z7 = z;
                while (true) {
                    if (!it6.hasNext()) {
                        z2 = z7;
                        break;
                    }
                    try {
                        str2 = (String) it6.next();
                        crl = getCRL(str2);
                    } catch (CertPathReviewerException e2) {
                        e = e2;
                        x509crl2 = x509crl;
                        it2 = it6;
                    }
                    if (crl != null) {
                        if (x509Certificate.getIssuerX500Principal().equals(crl.getIssuerX500Principal())) {
                            x509crl2 = x509crl;
                            it2 = it6;
                            try {
                            } catch (CertPathReviewerException e3) {
                                e = e3;
                                addNotification(e.getErrorMessage(), i);
                                it6 = it2;
                                x509crl = x509crl2;
                            }
                            if (crl.getNextUpdate() != null && !this.pkixParams.getDate().before(crl.getNextUpdate())) {
                                Object[] objArr = new Object[3];
                                z6 = z7;
                                try {
                                    objArr[0] = new TrustedInput(crl.getThisUpdate());
                                    objArr[1] = new TrustedInput(crl.getNextUpdate());
                                    objArr[2] = new UntrustedUrlInput(str2);
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineInvalidCRL", objArr), i);
                                    it6 = it2;
                                    x509crl = x509crl2;
                                    z7 = z6;
                                } catch (CertPathReviewerException e4) {
                                    e = e4;
                                    z7 = z6;
                                }
                            }
                            try {
                                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineValidCRL", new Object[]{new TrustedInput(crl.getThisUpdate()), new TrustedInput(crl.getNextUpdate()), new UntrustedUrlInput(str2)}), i);
                                x509crl = crl;
                                z2 = true;
                                break;
                            } catch (CertPathReviewerException e5) {
                                e = e5;
                                z7 = true;
                            }
                        } else {
                            try {
                                x509crl2 = x509crl;
                                it2 = it6;
                                try {
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineCRLWrongCA", new Object[]{new UntrustedInput(crl.getIssuerX500Principal().getName()), new UntrustedInput(x509Certificate.getIssuerX500Principal().getName()), new UntrustedUrlInput(str2)}), i);
                                } catch (CertPathReviewerException e6) {
                                    e = e6;
                                }
                            } catch (CertPathReviewerException e7) {
                                e = e7;
                                x509crl2 = x509crl;
                                it2 = it6;
                            }
                        }
                        addNotification(e.getErrorMessage(), i);
                        it6 = it2;
                        x509crl = x509crl2;
                    } else {
                        x509crl2 = x509crl;
                        it2 = it6;
                    }
                    z6 = z7;
                    it6 = it2;
                    x509crl = x509crl2;
                    z7 = z6;
                }
            } else {
                z2 = z;
            }
            if (x509crl != null) {
                if (x509Certificate2 != null && (keyUsage = x509Certificate2.getKeyUsage()) != null && (keyUsage.length < 7 || !keyUsage[6])) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCrlSigningPermited"));
                }
                if (publicKey == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlNoIssuerPublicKey"));
                }
                try {
                    x509crl.verify(publicKey, BouncyCastleProvider.PROVIDER_NAME);
                    X509CRLEntry revokedCertificate = x509crl.getRevokedCertificate(x509Certificate.getSerialNumber());
                    if (revokedCertificate != null) {
                        if (revokedCertificate.hasExtensions()) {
                            try {
                                ASN1Enumerated aSN1Enumerated = ASN1Enumerated.getInstance(getExtensionValue(revokedCertificate, Extension.reasonCode.getId()));
                                if (aSN1Enumerated != null) {
                                    str = crlReasons[aSN1Enumerated.intValueExact()];
                                    if (str == null) {
                                        str = crlReasons[7];
                                    }
                                    LocaleString localeString = new LocaleString(RESOURCE_NAME, str);
                                    if (date.before(revokedCertificate.getRevocationDate())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certRevoked", new Object[]{new TrustedInput(revokedCertificate.getRevocationDate()), localeString}));
                                    }
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.revokedAfterValidation", new Object[]{new TrustedInput(revokedCertificate.getRevocationDate()), localeString}), i);
                                }
                            } catch (AnnotatedException e8) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlReasonExtError"), e8);
                            }
                        }
                        str = null;
                        if (str == null) {
                        }
                        LocaleString localeString2 = new LocaleString(RESOURCE_NAME, str);
                        if (date.before(revokedCertificate.getRevocationDate())) {
                        }
                    } else {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notRevoked"), i);
                    }
                    if (x509crl.getNextUpdate() == null || !x509crl.getNextUpdate().before(this.pkixParams.getDate())) {
                        z3 = true;
                        z4 = false;
                    } else {
                        z3 = true;
                        z4 = false;
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlUpdateAvailable", new Object[]{new TrustedInput(x509crl.getNextUpdate())}), i);
                    }
                    try {
                        ASN1Primitive extensionValue = getExtensionValue(x509crl, ISSUING_DISTRIBUTION_POINT);
                        try {
                            ASN1Primitive extensionValue2 = getExtensionValue(x509crl, DELTA_CRL_INDICATOR);
                            if (extensionValue2 != null) {
                                X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
                                try {
                                    x509CRLStoreSelector2.addIssuerName(getIssuerPrincipal(x509crl).getEncoded());
                                    x509CRLStoreSelector2.setMinCRLNumber(((ASN1Integer) extensionValue2).getPositiveValue());
                                    try {
                                        x509CRLStoreSelector2.setMaxCRLNumber(((ASN1Integer) getExtensionValue(x509crl, CRL_NUMBER)).getPositiveValue().subtract(BigInteger.valueOf(1L)));
                                        try {
                                            Iterator it7 = CRL_UTIL.findCRLs(x509CRLStoreSelector2, pKIXParameters).iterator();
                                            while (it7.hasNext()) {
                                                try {
                                                    ASN1Primitive extensionValue3 = getExtensionValue((X509CRL) it7.next(), ISSUING_DISTRIBUTION_POINT);
                                                    if (extensionValue == null) {
                                                        if (extensionValue3 == null) {
                                                            z5 = z3;
                                                            break;
                                                        }
                                                    } else if (extensionValue.equals(extensionValue3)) {
                                                        z5 = z3;
                                                        break;
                                                    }
                                                } catch (AnnotatedException e9) {
                                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.distrPtExtError"), e9);
                                                }
                                            }
                                            z5 = z4;
                                            if (!z5) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBaseCRL"));
                                            }
                                        } catch (AnnotatedException e10) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlExtractionError"), e10);
                                        }
                                    } catch (AnnotatedException e11) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlNbrExtError"), e11);
                                    }
                                } catch (IOException e12) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlIssuerException"), e12);
                                }
                            }
                            if (extensionValue != null) {
                                IssuingDistributionPoint issuingDistributionPoint = IssuingDistributionPoint.getInstance(extensionValue);
                                try {
                                    BasicConstraints basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
                                    if (issuingDistributionPoint.onlyContainsUserCerts() && basicConstraints != null && basicConstraints.isCA()) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyUserCert"));
                                    }
                                    if (issuingDistributionPoint.onlyContainsCACerts() && (basicConstraints == null || !basicConstraints.isCA())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyCaCert"));
                                    }
                                    if (issuingDistributionPoint.onlyContainsAttributeCerts()) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyAttrCert"));
                                    }
                                } catch (AnnotatedException e13) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlBCExtError"), e13);
                                }
                            }
                        } catch (AnnotatedException unused) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.deltaCrlExtError"));
                        }
                    } catch (AnnotatedException unused2) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.distrPtExtError"));
                    }
                } catch (Exception e14) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlVerifyFailed"), e14);
                }
            }
            if (!z2) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidCrlFound"));
            }
        } catch (IOException e15) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlIssuerException"), e15);
        }
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    protected void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i = this.n;
        this.notifications = new List[i + 1];
        this.errors = new List[i + 1];
        int i2 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i2 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i2] = new ArrayList();
            this.errors[i2] = new ArrayList();
            i2++;
        }
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((DERIA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((DERIA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                if (keyIdentifier != null) {
                    x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        Objects.requireNonNull(certPath, "certPath was null");
        this.certPath = certPath;
        List<? extends Certificate> certificates = certPath.getCertificates();
        this.certs = certificates;
        this.n = certificates.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        PKIXParameters pKIXParameters2 = (PKIXParameters) pKIXParameters.clone();
        this.pkixParams = pKIXParameters2;
        this.validDate = getValidDate(pKIXParameters2);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }
}
