package io.sentry;

/* loaded from: classes5.dex */
public interface JsonDeserializer<T> {
    T deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception;
}
