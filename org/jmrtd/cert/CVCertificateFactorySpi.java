package org.jmrtd.cert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.util.Collection;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;
import org.ejbca.cvc.CVCertificate;
import org.ejbca.cvc.CertificateParser;
import org.ejbca.cvc.exception.ConstructionException;
import org.ejbca.cvc.exception.ParseException;

/* loaded from: classes6.dex */
public class CVCertificateFactorySpi extends CertificateFactorySpi {
    private static final int CV_CERTIFICATE_TAG = 32545;

    @Override // java.security.cert.CertificateFactorySpi
    public CRL engineGenerateCRL(InputStream inputStream) throws CRLException {
        return null;
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection<? extends CRL> engineGenerateCRLs(InputStream inputStream) throws CRLException {
        return null;
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection<? extends Certificate> engineGenerateCertificates(InputStream inputStream) throws CertificateException {
        return null;
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Certificate engineGenerateCertificate(InputStream inputStream) throws CertificateException {
        try {
            TLVInputStream tLVInputStream = new TLVInputStream(inputStream);
            int readTag = tLVInputStream.readTag();
            if (readTag != CV_CERTIFICATE_TAG) {
                throw new CertificateException("Expected CV_CERTIFICATE_TAG, found " + Integer.toHexString(readTag));
            }
            tLVInputStream.readLength();
            byte[] readValue = tLVInputStream.readValue();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            TLVOutputStream tLVOutputStream = new TLVOutputStream(byteArrayOutputStream);
            tLVOutputStream.writeTag(CV_CERTIFICATE_TAG);
            tLVOutputStream.writeValue(readValue);
            tLVOutputStream.close();
            CVCertificate parseCertificate = CertificateParser.parseCertificate(byteArrayOutputStream.toByteArray());
            CVCertificate cVCertificate = parseCertificate;
            return new CardVerifiableCertificate(parseCertificate);
        } catch (IOException e) {
            throw new CertificateException(e);
        } catch (ConstructionException e2) {
            throw new CertificateException(e2);
        } catch (ParseException e3) {
            throw new CertificateException(e3);
        }
    }
}
