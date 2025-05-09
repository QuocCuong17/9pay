package lmlf.ayxnhy;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;

/* loaded from: classes5.dex */
public class ukqce {
    public static X509TrustManager vzpzzp = new klcf();

    public static X509TrustManager lbvmwe() {
        return vzpzzp;
    }

    public static SSLSocketFactory quvtm() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new X509TrustManager[]{vzpzzp}, null);
            return sSLContext.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Interceptor vgbid() {
        return new ysci((klcf) vzpzzp);
    }
}
