package co.hyperverge.hyperkyc.data.network.progress;

import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProgressFriendlySocketFactory.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0016J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0003H\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0003H\u0016J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/progress/ProgressFriendlySocketFactory;", "Ljavax/net/SocketFactory;", "sendBufferSize", "", "(I)V", "createSocket", "Ljava/net/Socket;", URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST, "Ljava/net/InetAddress;", URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT, "address", "localAddress", "localPort", "", "localHost", "setSendBufferSize", "socket", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ProgressFriendlySocketFactory extends SocketFactory {
    public static final int DEFAULT_BUFFER_SIZE = 262144;
    private final int sendBufferSize;

    public ProgressFriendlySocketFactory() {
        this(0, 1, null);
    }

    public /* synthetic */ ProgressFriendlySocketFactory(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 262144 : i);
    }

    public ProgressFriendlySocketFactory(int i) {
        this.sendBufferSize = i;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return setSendBufferSize(new Socket());
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port) {
        Intrinsics.checkNotNullParameter(host, "host");
        return setSendBufferSize(new Socket(host, port));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(localHost, "localHost");
        return setSendBufferSize(new Socket(host, port, localHost, localPort));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress host, int port) {
        Intrinsics.checkNotNullParameter(host, "host");
        return setSendBufferSize(new Socket(host, port));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(localAddress, "localAddress");
        return setSendBufferSize(new Socket(address, port, localAddress, localPort));
    }

    private final Socket setSendBufferSize(Socket socket) {
        socket.setSendBufferSize(this.sendBufferSize);
        return socket;
    }
}
