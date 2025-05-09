package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import java.io.IOException;
import java.net.Socket;

/* loaded from: classes5.dex */
interface HandshakerSocketFactory {
    HandshakeResult handshake(Socket socket, Attributes attributes) throws IOException;

    /* loaded from: classes5.dex */
    public static final class HandshakeResult {
        public final Attributes attributes;
        public final InternalChannelz.Security securityInfo;
        public final Socket socket;

        public HandshakeResult(Socket socket, Attributes attributes, InternalChannelz.Security security) {
            this.socket = (Socket) Preconditions.checkNotNull(socket, "socket");
            this.attributes = (Attributes) Preconditions.checkNotNull(attributes, "attributes");
            this.securityInfo = security;
        }
    }
}
