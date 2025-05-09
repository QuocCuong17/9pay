package lmlf.ayxnhy;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes5.dex */
public class klcf implements X509TrustManager {
    public X509TrustManagerExtensions idz;
    public ThreadLocal<String> umhmt;

    public klcf() {
        this.umhmt = new ThreadLocal<>();
        this.idz = tfwhgw.rnj();
    }

    public klcf(String str) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        this.umhmt = threadLocal;
        threadLocal.set(str);
        this.idz = tfwhgw.rnj();
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        tfwhgw.vfrf(x509CertificateArr, str, this.umhmt.get(), this.idz);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        tfwhgw.kjckce(x509CertificateArr, str, this.umhmt.get(), this.idz);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return tfwhgw.cvsc();
    }

    public void smysce(String str) {
        this.umhmt.set(str);
    }
}
