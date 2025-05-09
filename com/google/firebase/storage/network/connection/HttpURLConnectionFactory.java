package com.google.firebase.storage.network.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes4.dex */
public interface HttpURLConnectionFactory {
    HttpURLConnection createInstance(URL url) throws IOException;
}
