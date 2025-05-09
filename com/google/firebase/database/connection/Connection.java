package com.google.firebase.database.connection;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.firebase.database.connection.WebsocketConnection;
import com.google.firebase.database.logging.LogWrapper;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class Connection implements WebsocketConnection.Delegate {
    private static final String REQUEST_PAYLOAD = "d";
    private static final String REQUEST_TYPE = "t";
    private static final String REQUEST_TYPE_DATA = "d";
    private static final String SERVER_CONTROL_MESSAGE = "c";
    private static final String SERVER_CONTROL_MESSAGE_DATA = "d";
    private static final String SERVER_CONTROL_MESSAGE_HELLO = "h";
    private static final String SERVER_CONTROL_MESSAGE_RESET = "r";
    private static final String SERVER_CONTROL_MESSAGE_SHUTDOWN = "s";
    private static final String SERVER_CONTROL_MESSAGE_TYPE = "t";
    private static final String SERVER_DATA_MESSAGE = "d";
    private static final String SERVER_ENVELOPE_DATA = "d";
    private static final String SERVER_ENVELOPE_TYPE = "t";
    private static final String SERVER_HELLO_HOST = "h";
    private static final String SERVER_HELLO_SESSION_ID = "s";
    private static final String SERVER_HELLO_TIMESTAMP = "ts";
    private static long connectionIds;
    private WebsocketConnection conn;
    private Delegate delegate;
    private HostInfo hostInfo;
    private final LogWrapper logger;
    private State state;

    /* loaded from: classes4.dex */
    public interface Delegate {
        void onCacheHost(String str);

        void onDataMessage(Map<String, Object> map);

        void onDisconnect(DisconnectReason disconnectReason);

        void onKill(String str);

        void onReady(long j, String str);
    }

    /* loaded from: classes4.dex */
    public enum DisconnectReason {
        SERVER_RESET,
        OTHER
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum State {
        REALTIME_CONNECTING,
        REALTIME_CONNECTED,
        REALTIME_DISCONNECTED
    }

    public Connection(ConnectionContext connectionContext, HostInfo hostInfo, String str, Delegate delegate, String str2, String str3) {
        long j = connectionIds;
        connectionIds = 1 + j;
        this.hostInfo = hostInfo;
        this.delegate = delegate;
        this.logger = new LogWrapper(connectionContext.getLogger(), "Connection", "conn_" + j);
        this.state = State.REALTIME_CONNECTING;
        this.conn = new WebsocketConnection(connectionContext, hostInfo, str, str3, this, str2);
    }

    public void open() {
        if (this.logger.logsDebug()) {
            this.logger.debug("Opening a connection", new Object[0]);
        }
        this.conn.open();
    }

    public void close(DisconnectReason disconnectReason) {
        if (this.state != State.REALTIME_DISCONNECTED) {
            if (this.logger.logsDebug()) {
                this.logger.debug("closing realtime connection", new Object[0]);
            }
            this.state = State.REALTIME_DISCONNECTED;
            WebsocketConnection websocketConnection = this.conn;
            if (websocketConnection != null) {
                websocketConnection.close();
                this.conn = null;
            }
            this.delegate.onDisconnect(disconnectReason);
        }
    }

    public void close() {
        close(DisconnectReason.OTHER);
    }

    public void sendRequest(Map<String, Object> map, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("t", "d");
        hashMap.put("d", map);
        sendData(hashMap, z);
    }

    @Override // com.google.firebase.database.connection.WebsocketConnection.Delegate
    public void onMessage(Map<String, Object> map) {
        try {
            String str = (String) map.get("t");
            if (str != null) {
                if (str.equals("d")) {
                    onDataMessage((Map) map.get("d"));
                    return;
                }
                if (str.equals(SERVER_CONTROL_MESSAGE)) {
                    onControlMessage((Map) map.get("d"));
                    return;
                }
                if (this.logger.logsDebug()) {
                    this.logger.debug("Ignoring unknown server message type: " + str, new Object[0]);
                    return;
                }
                return;
            }
            if (this.logger.logsDebug()) {
                this.logger.debug("Failed to parse server message: missing message type:" + map.toString(), new Object[0]);
            }
            close();
        } catch (ClassCastException e) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Failed to parse server message: " + e.toString(), new Object[0]);
            }
            close();
        }
    }

    @Override // com.google.firebase.database.connection.WebsocketConnection.Delegate
    public void onDisconnect(boolean z) {
        this.conn = null;
        if (!z && this.state == State.REALTIME_CONNECTING) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Realtime connection failed", new Object[0]);
            }
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Realtime connection lost", new Object[0]);
        }
        close();
    }

    private void onDataMessage(Map<String, Object> map) {
        if (this.logger.logsDebug()) {
            this.logger.debug("received data message: " + map.toString(), new Object[0]);
        }
        this.delegate.onDataMessage(map);
    }

    private void onControlMessage(Map<String, Object> map) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Got control message: " + map.toString(), new Object[0]);
        }
        try {
            String str = (String) map.get("t");
            if (str != null) {
                if (str.equals(CmcdData.Factory.STREAMING_FORMAT_SS)) {
                    onConnectionShutdown((String) map.get("d"));
                    return;
                }
                if (str.equals("r")) {
                    onReset((String) map.get("d"));
                    return;
                }
                if (str.equals(CmcdData.Factory.STREAMING_FORMAT_HLS)) {
                    onHandshake((Map) map.get("d"));
                    return;
                }
                if (this.logger.logsDebug()) {
                    this.logger.debug("Ignoring unknown control message: " + str, new Object[0]);
                    return;
                }
                return;
            }
            if (this.logger.logsDebug()) {
                this.logger.debug("Got invalid control message: " + map.toString(), new Object[0]);
            }
            close();
        } catch (ClassCastException e) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Failed to parse control message: " + e.toString(), new Object[0]);
            }
            close();
        }
    }

    private void onConnectionShutdown(String str) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Connection shutdown command received. Shutting down...", new Object[0]);
        }
        this.delegate.onKill(str);
        close();
    }

    private void onHandshake(Map<String, Object> map) {
        long longValue = ((Long) map.get(SERVER_HELLO_TIMESTAMP)).longValue();
        this.delegate.onCacheHost((String) map.get(CmcdData.Factory.STREAMING_FORMAT_HLS));
        String str = (String) map.get(CmcdData.Factory.STREAMING_FORMAT_SS);
        if (this.state == State.REALTIME_CONNECTING) {
            this.conn.start();
            onConnectionReady(longValue, str);
        }
    }

    private void onConnectionReady(long j, String str) {
        if (this.logger.logsDebug()) {
            this.logger.debug("realtime connection established", new Object[0]);
        }
        this.state = State.REALTIME_CONNECTED;
        this.delegate.onReady(j, str);
    }

    private void onReset(String str) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Got a reset; killing connection to " + this.hostInfo.getHost() + "; Updating internalHost to " + str, new Object[0]);
        }
        this.delegate.onCacheHost(str);
        close(DisconnectReason.SERVER_RESET);
    }

    private void sendData(Map<String, Object> map, boolean z) {
        if (this.state != State.REALTIME_CONNECTED) {
            this.logger.debug("Tried to send on an unconnected connection", new Object[0]);
            return;
        }
        if (z) {
            this.logger.debug("Sending data (contents hidden)", new Object[0]);
        } else {
            this.logger.debug("Sending data: %s", map);
        }
        this.conn.send(map);
    }

    public void injectConnectionFailure() {
        close();
    }
}
