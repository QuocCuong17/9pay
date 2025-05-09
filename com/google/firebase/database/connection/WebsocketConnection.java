package com.google.firebase.database.connection;

import com.google.firebase.database.connection.util.StringListReader;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.tubesock.WebSocket;
import com.google.firebase.database.tubesock.WebSocketEventHandler;
import com.google.firebase.database.tubesock.WebSocketException;
import com.google.firebase.database.tubesock.WebSocketMessage;
import com.google.firebase.database.util.JsonMapper;
import java.io.EOFException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class WebsocketConnection {
    private static final long CONNECT_TIMEOUT_MS = 30000;
    private static final long KEEP_ALIVE_TIMEOUT_MS = 45000;
    private static final int MAX_FRAME_SIZE = 16384;
    private static long connectionId;
    private WSClient conn;
    private ScheduledFuture<?> connectTimeout;
    private final ConnectionContext connectionContext;
    private Delegate delegate;
    private final ScheduledExecutorService executorService;
    private StringListReader frameReader;
    private ScheduledFuture<?> keepAlive;
    private final LogWrapper logger;
    private boolean everConnected = false;
    private boolean isClosed = false;
    private long totalFrames = 0;

    /* loaded from: classes4.dex */
    public interface Delegate {
        void onDisconnect(boolean z);

        void onMessage(Map<String, Object> map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public interface WSClient {
        void close();

        void connect();

        void send(String str);
    }

    public void start() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class WSClientTubesock implements WSClient, WebSocketEventHandler {
        private WebSocket ws;

        private WSClientTubesock(WebSocket webSocket) {
            this.ws = webSocket;
            webSocket.setEventHandler(this);
        }

        @Override // com.google.firebase.database.tubesock.WebSocketEventHandler
        public void onOpen() {
            WebsocketConnection.this.executorService.execute(new Runnable() { // from class: com.google.firebase.database.connection.WebsocketConnection.WSClientTubesock.1
                @Override // java.lang.Runnable
                public void run() {
                    WebsocketConnection.this.connectTimeout.cancel(false);
                    WebsocketConnection.this.everConnected = true;
                    if (WebsocketConnection.this.logger.logsDebug()) {
                        WebsocketConnection.this.logger.debug("websocket opened", new Object[0]);
                    }
                    WebsocketConnection.this.resetKeepAlive();
                }
            });
        }

        @Override // com.google.firebase.database.tubesock.WebSocketEventHandler
        public void onMessage(WebSocketMessage webSocketMessage) {
            final String text = webSocketMessage.getText();
            if (WebsocketConnection.this.logger.logsDebug()) {
                WebsocketConnection.this.logger.debug("ws message: " + text, new Object[0]);
            }
            WebsocketConnection.this.executorService.execute(new Runnable() { // from class: com.google.firebase.database.connection.WebsocketConnection.WSClientTubesock.2
                @Override // java.lang.Runnable
                public void run() {
                    WebsocketConnection.this.handleIncomingFrame(text);
                }
            });
        }

        @Override // com.google.firebase.database.tubesock.WebSocketEventHandler
        public void onClose() {
            WebsocketConnection.this.executorService.execute(new Runnable() { // from class: com.google.firebase.database.connection.WebsocketConnection.WSClientTubesock.3
                @Override // java.lang.Runnable
                public void run() {
                    if (WebsocketConnection.this.logger.logsDebug()) {
                        WebsocketConnection.this.logger.debug("closed", new Object[0]);
                    }
                    WebsocketConnection.this.onClosed();
                }
            });
        }

        @Override // com.google.firebase.database.tubesock.WebSocketEventHandler
        public void onError(final WebSocketException webSocketException) {
            WebsocketConnection.this.executorService.execute(new Runnable() { // from class: com.google.firebase.database.connection.WebsocketConnection.WSClientTubesock.4
                @Override // java.lang.Runnable
                public void run() {
                    if (webSocketException.getCause() == null || !(webSocketException.getCause() instanceof EOFException)) {
                        WebsocketConnection.this.logger.debug("WebSocket error.", webSocketException, new Object[0]);
                    } else {
                        WebsocketConnection.this.logger.debug("WebSocket reached EOF.", new Object[0]);
                    }
                    WebsocketConnection.this.onClosed();
                }
            });
        }

        @Override // com.google.firebase.database.tubesock.WebSocketEventHandler
        public void onLogMessage(String str) {
            if (WebsocketConnection.this.logger.logsDebug()) {
                WebsocketConnection.this.logger.debug("Tubesock: " + str, new Object[0]);
            }
        }

        @Override // com.google.firebase.database.connection.WebsocketConnection.WSClient
        public void send(String str) {
            this.ws.send(str);
        }

        @Override // com.google.firebase.database.connection.WebsocketConnection.WSClient
        public void close() {
            this.ws.close();
        }

        private void shutdown() {
            this.ws.close();
            try {
                this.ws.blockClose();
            } catch (InterruptedException e) {
                WebsocketConnection.this.logger.error("Interrupted while shutting down websocket threads", e);
            }
        }

        @Override // com.google.firebase.database.connection.WebsocketConnection.WSClient
        public void connect() {
            try {
                this.ws.connect();
            } catch (WebSocketException e) {
                if (WebsocketConnection.this.logger.logsDebug()) {
                    WebsocketConnection.this.logger.debug("Error connecting", e, new Object[0]);
                }
                shutdown();
            }
        }
    }

    public WebsocketConnection(ConnectionContext connectionContext, HostInfo hostInfo, String str, String str2, Delegate delegate, String str3) {
        this.connectionContext = connectionContext;
        this.executorService = connectionContext.getExecutorService();
        this.delegate = delegate;
        long j = connectionId;
        connectionId = 1 + j;
        this.logger = new LogWrapper(connectionContext.getLogger(), "WebSocket", "ws_" + j);
        this.conn = createConnection(hostInfo, str, str2, str3);
    }

    private WSClient createConnection(HostInfo hostInfo, String str, String str2, String str3) {
        if (str == null) {
            str = hostInfo.getHost();
        }
        URI connectionUrl = HostInfo.getConnectionUrl(str, hostInfo.isSecure(), hostInfo.getNamespace(), str3);
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", this.connectionContext.getUserAgent());
        hashMap.put("X-Firebase-GMPID", this.connectionContext.getApplicationId());
        hashMap.put("X-Firebase-AppCheck", str2);
        return new WSClientTubesock(new WebSocket(this.connectionContext, connectionUrl, null, hashMap));
    }

    public void open() {
        this.conn.connect();
        this.connectTimeout = this.executorService.schedule(new Runnable() { // from class: com.google.firebase.database.connection.WebsocketConnection.1
            @Override // java.lang.Runnable
            public void run() {
                WebsocketConnection.this.closeIfNeverConnected();
            }
        }, 30000L, TimeUnit.MILLISECONDS);
    }

    public void close() {
        if (this.logger.logsDebug()) {
            this.logger.debug("websocket is being closed", new Object[0]);
        }
        this.isClosed = true;
        this.conn.close();
        ScheduledFuture<?> scheduledFuture = this.connectTimeout;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        ScheduledFuture<?> scheduledFuture2 = this.keepAlive;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(true);
        }
    }

    public void send(Map<String, Object> map) {
        resetKeepAlive();
        try {
            String[] splitIntoFrames = splitIntoFrames(JsonMapper.serializeJson(map), 16384);
            if (splitIntoFrames.length > 1) {
                this.conn.send("" + splitIntoFrames.length);
            }
            for (String str : splitIntoFrames) {
                this.conn.send(str);
            }
        } catch (IOException e) {
            this.logger.error("Failed to serialize message: " + map.toString(), e);
            shutdown();
        }
    }

    private void appendFrame(String str) {
        this.frameReader.addString(str);
        long j = this.totalFrames - 1;
        this.totalFrames = j;
        if (j == 0) {
            try {
                this.frameReader.freeze();
                Map<String, Object> parseJson = JsonMapper.parseJson(this.frameReader.toString());
                this.frameReader = null;
                if (this.logger.logsDebug()) {
                    this.logger.debug("handleIncomingFrame complete frame: " + parseJson, new Object[0]);
                }
                this.delegate.onMessage(parseJson);
            } catch (IOException e) {
                this.logger.error("Error parsing frame: " + this.frameReader.toString(), e);
                close();
                shutdown();
            } catch (ClassCastException e2) {
                this.logger.error("Error parsing frame (cast error): " + this.frameReader.toString(), e2);
                close();
                shutdown();
            }
        }
    }

    private void handleNewFrameCount(int i) {
        this.totalFrames = i;
        this.frameReader = new StringListReader();
        if (this.logger.logsDebug()) {
            this.logger.debug("HandleNewFrameCount: " + this.totalFrames, new Object[0]);
        }
    }

    private String extractFrameCount(String str) {
        if (str.length() <= 6) {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt <= 0) {
                    return null;
                }
                handleNewFrameCount(parseInt);
                return null;
            } catch (NumberFormatException unused) {
            }
        }
        handleNewFrameCount(1);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIncomingFrame(String str) {
        if (this.isClosed) {
            return;
        }
        resetKeepAlive();
        if (isBuffering()) {
            appendFrame(str);
            return;
        }
        String extractFrameCount = extractFrameCount(str);
        if (extractFrameCount != null) {
            appendFrame(extractFrameCount);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetKeepAlive() {
        if (this.isClosed) {
            return;
        }
        ScheduledFuture<?> scheduledFuture = this.keepAlive;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            if (this.logger.logsDebug()) {
                this.logger.debug("Reset keepAlive. Remaining: " + this.keepAlive.getDelay(TimeUnit.MILLISECONDS), new Object[0]);
            }
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Reset keepAlive", new Object[0]);
        }
        this.keepAlive = this.executorService.schedule(nop(), KEEP_ALIVE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }

    private Runnable nop() {
        return new Runnable() { // from class: com.google.firebase.database.connection.WebsocketConnection.2
            @Override // java.lang.Runnable
            public void run() {
                if (WebsocketConnection.this.conn != null) {
                    WebsocketConnection.this.conn.send("0");
                    WebsocketConnection.this.resetKeepAlive();
                }
            }
        };
    }

    private boolean isBuffering() {
        return this.frameReader != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClosed() {
        if (!this.isClosed) {
            if (this.logger.logsDebug()) {
                this.logger.debug("closing itself", new Object[0]);
            }
            shutdown();
        }
        this.conn = null;
        ScheduledFuture<?> scheduledFuture = this.keepAlive;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    private void shutdown() {
        this.isClosed = true;
        this.delegate.onDisconnect(this.everConnected);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeIfNeverConnected() {
        if (this.everConnected || this.isClosed) {
            return;
        }
        if (this.logger.logsDebug()) {
            this.logger.debug("timed out on connect", new Object[0]);
        }
        this.conn.close();
    }

    private static String[] splitIntoFrames(String str, int i) {
        int i2 = 0;
        if (str.length() <= i) {
            return new String[]{str};
        }
        ArrayList arrayList = new ArrayList();
        while (i2 < str.length()) {
            int i3 = i2 + i;
            arrayList.add(str.substring(i2, Math.min(i3, str.length())));
            i2 = i3;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
