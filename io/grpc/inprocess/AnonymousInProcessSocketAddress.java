package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public final class AnonymousInProcessSocketAddress extends SocketAddress {
    private static final long serialVersionUID = -8567592561863414695L;

    @Nullable
    private InProcessServer server;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public synchronized InProcessServer getServer() {
        return this.server;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setServer(InProcessServer inProcessServer) throws IOException {
        if (this.server != null) {
            throw new IOException("Server instance already registered");
        }
        this.server = inProcessServer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void clearServer(InProcessServer inProcessServer) {
        Preconditions.checkState(this.server == inProcessServer);
        this.server = null;
    }
}
