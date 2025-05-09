package lmlf.ayxnhy;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class hrn {
    private byte[] nmldaq;

    public hrn(List<String> list, String str) {
        String rnigpa = tfwhgw.rnigpa(CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA);
        if (list == null || list.isEmpty()) {
            throw new RuntimeException(rnigpa);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] decode = Base64.decode(str, 2);
        if (str == null || decode.length < 32) {
            throw new RuntimeException(rnigpa);
        }
        try {
            byteArrayOutputStream.write(tpvc(decode.length));
            byteArrayOutputStream.write(decode);
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                byte[] decode2 = Base64.decode(it.next(), 2);
                byteArrayOutputStream.write(tpvc(decode2.length));
                byteArrayOutputStream.write(decode2);
            }
            this.nmldaq = byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            throw new RuntimeException(rnigpa);
        }
    }

    public static byte[] tpvc(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 3; i2 >= 0; i2--) {
            bArr[i2] = (byte) (i & 255);
            i >>= 8;
        }
        return bArr;
    }

    public byte[] shwerg() {
        return tfwhgw.wzx(this.nmldaq);
    }
}
