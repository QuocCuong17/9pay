package vn.ai.faceauth.sdk.presentation.di;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001c\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J.\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J.\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001c\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J.\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0013\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00160\tH\u0016¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160\tH\u0016¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006!"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/di/TLSSocketFactory;", "Ljavax/net/ssl/SSLSocketFactory;", "()V", "delegate", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "getTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "trustManagers", "", "Ljavax/net/ssl/TrustManager;", "[Ljavax/net/ssl/TrustManager;", "createSocket", "Ljava/net/Socket;", URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST, "Ljava/net/InetAddress;", URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT, "", "address", "localAddress", "localPort", CmcdData.Factory.STREAMING_FORMAT_SS, "", "autoClose", "", "localHost", "enableTLSOnSocket", "socket", "generateTrustManagers", "", "getDefaultCipherSuites", "()[Ljava/lang/String;", "getSupportedCipherSuites", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class TLSSocketFactory extends SSLSocketFactory {
    private final SSLSocketFactory delegate;
    private TrustManager[] trustManagers;

    public TLSSocketFactory() {
        generateTrustManagers();
        SSLContext sSLContext = SSLContext.getInstance(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_COMPRESSION));
        TrustManager[] trustManagerArr = this.trustManagers;
        if (trustManagerArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(260));
            trustManagerArr = null;
        }
        sSLContext.init(null, trustManagerArr, null);
        this.delegate = sSLContext.getSocketFactory();
    }

    private final Socket enableTLSOnSocket(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            ((SSLSocket) socket).setEnabledProtocols(new String[]{tfwhgw.rnigpa(261), tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION)});
        }
        return socket;
    }

    private final void generateTrustManagers() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            this.trustManagers = trustManagers;
        } else {
            throw new IllegalStateException((tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_THRESHHOLDING) + Arrays.toString(trustManagers)).toString());
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return enableTLSOnSocket(this.delegate.createSocket());
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port) {
        return enableTLSOnSocket(this.delegate.createSocket(host, port));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) {
        return enableTLSOnSocket(this.delegate.createSocket(host, port, localHost, localPort));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress host, int port) {
        return enableTLSOnSocket(this.delegate.createSocket(host, port));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) {
        return enableTLSOnSocket(this.delegate.createSocket(address, port, localAddress, localPort));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) {
        return enableTLSOnSocket(this.delegate.createSocket(s, host, port, autoClose));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public final X509TrustManager getTrustManager() {
        TrustManager[] trustManagerArr = this.trustManagers;
        if (trustManagerArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_CELL_WIDTH));
            trustManagerArr = null;
        }
        return (X509TrustManager) trustManagerArr[0];
    }
}
