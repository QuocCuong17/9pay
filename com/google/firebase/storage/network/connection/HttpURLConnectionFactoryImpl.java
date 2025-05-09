package com.google.firebase.storage.network.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes4.dex */
public class HttpURLConnectionFactoryImpl implements HttpURLConnectionFactory {
    @Override // com.google.firebase.storage.network.connection.HttpURLConnectionFactory
    public HttpURLConnection createInstance(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
