package org.ejbca.cvc.example;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.ejbca.cvc.AuthorizationRoleEnum;
import org.ejbca.cvc.CAReferenceField;
import org.ejbca.cvc.CertificateGenerator;
import org.ejbca.cvc.CertificateParser;
import org.ejbca.cvc.HolderReferenceField;

/* loaded from: classes6.dex */
public final class GenerateCert {
    private GenerateCert() {
    }

    public static void main(String[] strArr) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
            keyPairGenerator.initialize(1024, new SecureRandom());
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            CAReferenceField cAReferenceField = new CAReferenceField("SE", "PASS-CVCA", "00111");
            FileHelper.writeFile(new File("C:/cv_certs/mycert1.cvcert"), CertificateGenerator.createTestCertificate(generateKeyPair.getPublic(), generateKeyPair.getPrivate(), cAReferenceField, new HolderReferenceField(cAReferenceField.getCountry(), cAReferenceField.getMnemonic(), cAReferenceField.getSequence()), "SHA1WithRSA", AuthorizationRoleEnum.IS).getDEREncoded());
            System.out.println(CertificateParser.parseCertificate(FileHelper.loadFile(new File("C:/cv_certs/mycert1.cvcert"))).getAsText(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
