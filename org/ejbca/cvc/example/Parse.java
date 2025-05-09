package org.ejbca.cvc.example;

import java.io.File;
import org.ejbca.cvc.CertificateParser;

/* loaded from: classes6.dex */
public final class Parse {
    private Parse() {
    }

    public static void main(String[] strArr) {
        try {
            System.out.println(CertificateParser.parseCVCObject(FileHelper.loadFile(new File("C:/cv_certs/mycert1.cvcert"))).getAsText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
