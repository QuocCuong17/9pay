package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public final class TraceContext implements JsonUnknown, JsonSerializable {
    private final String environment;
    private final String publicKey;
    private final String release;
    private final String sampleRate;
    private final String sampled;
    private final SentryId traceId;
    private final String transaction;
    private Map<String, Object> unknown;
    private final String userId;
    private final String userSegment;

    /* loaded from: classes5.dex */
    public static final class JsonKeys {
        public static final String ENVIRONMENT = "environment";
        public static final String PUBLIC_KEY = "public_key";
        public static final String RELEASE = "release";
        public static final String SAMPLED = "sampled";
        public static final String SAMPLE_RATE = "sample_rate";
        public static final String TRACE_ID = "trace_id";
        public static final String TRANSACTION = "transaction";
        public static final String USER = "user";
        public static final String USER_ID = "user_id";
        public static final String USER_SEGMENT = "user_segment";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TraceContext(SentryId sentryId, String str) {
        this(sentryId, str, null, null, null, null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TraceContext(SentryId sentryId, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.traceId = sentryId;
        this.publicKey = str;
        this.release = str2;
        this.environment = str3;
        this.userId = str4;
        this.userSegment = str5;
        this.transaction = str6;
        this.sampleRate = str7;
        this.sampled = str8;
    }

    private static String getUserId(SentryOptions sentryOptions, User user) {
        if (!sentryOptions.isSendDefaultPii() || user == null) {
            return null;
        }
        return user.getId();
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public String getRelease() {
        return this.release;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUserSegment() {
        return this.userSegment;
    }

    public String getTransaction() {
        return this.transaction;
    }

    public String getSampleRate() {
        return this.sampleRate;
    }

    public String getSampled() {
        return this.sampled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    /* loaded from: classes5.dex */
    public static final class TraceContextUser implements JsonUnknown {

        /* renamed from: id, reason: collision with root package name */
        private String f130id;
        private String segment;
        private Map<String, Object> unknown;

        /* loaded from: classes5.dex */
        public static final class JsonKeys {
            public static final String ID = "id";
            public static final String SEGMENT = "segment";
        }

        private TraceContextUser(String str, String str2) {
            this.f130id = str;
            this.segment = str2;
        }

        public String getId() {
            return this.f130id;
        }

        public String getSegment() {
            return this.segment;
        }

        @Override // io.sentry.JsonUnknown
        public Map<String, Object> getUnknown() {
            return this.unknown;
        }

        @Override // io.sentry.JsonUnknown
        public void setUnknown(Map<String, Object> map) {
            this.unknown = map;
        }

        /* loaded from: classes5.dex */
        public static final class Deserializer implements JsonDeserializer<TraceContextUser> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.sentry.JsonDeserializer
            public TraceContextUser deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
                jsonObjectReader.beginObject();
                String str = null;
                String str2 = null;
                ConcurrentHashMap concurrentHashMap = null;
                while (jsonObjectReader.peek() == JsonToken.NAME) {
                    String nextName = jsonObjectReader.nextName();
                    nextName.hashCode();
                    if (nextName.equals("id")) {
                        str = jsonObjectReader.nextStringOrNull();
                    } else if (nextName.equals("segment")) {
                        str2 = jsonObjectReader.nextStringOrNull();
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        jsonObjectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                    }
                }
                TraceContextUser traceContextUser = new TraceContextUser(str, str2);
                traceContextUser.setUnknown(concurrentHashMap);
                jsonObjectReader.endObject();
                return traceContextUser;
            }
        }
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("trace_id").value(iLogger, this.traceId);
        objectWriter.name(JsonKeys.PUBLIC_KEY).value(this.publicKey);
        if (this.release != null) {
            objectWriter.name("release").value(this.release);
        }
        if (this.environment != null) {
            objectWriter.name("environment").value(this.environment);
        }
        if (this.userId != null) {
            objectWriter.name("user_id").value(this.userId);
        }
        if (this.userSegment != null) {
            objectWriter.name(JsonKeys.USER_SEGMENT).value(this.userSegment);
        }
        if (this.transaction != null) {
            objectWriter.name("transaction").value(this.transaction);
        }
        if (this.sampleRate != null) {
            objectWriter.name(JsonKeys.SAMPLE_RATE).value(this.sampleRate);
        }
        if (this.sampled != null) {
            objectWriter.name(JsonKeys.SAMPLED).value(this.sampled);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    /* loaded from: classes5.dex */
    public static final class Deserializer implements JsonDeserializer<TraceContext> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a9  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00c0  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x00c6  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00d3  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00d9  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00df  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00f2  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00f8  */
        @Override // io.sentry.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public TraceContext deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            String str;
            String str2;
            jsonObjectReader.beginObject();
            TraceContextUser traceContextUser = null;
            String str3 = null;
            SentryId sentryId = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            String str10 = null;
            ConcurrentHashMap concurrentHashMap = null;
            while (true) {
                String str11 = str10;
                if (jsonObjectReader.peek() != JsonToken.NAME) {
                    if (sentryId == null) {
                        throw missingRequiredFieldException("trace_id", iLogger);
                    }
                    if (str5 == null) {
                        throw missingRequiredFieldException(JsonKeys.PUBLIC_KEY, iLogger);
                    }
                    if (traceContextUser != null) {
                        if (str3 == null) {
                            str3 = traceContextUser.getId();
                        }
                        if (str4 == null) {
                            str2 = traceContextUser.getSegment();
                            str = str3;
                            TraceContext traceContext = new TraceContext(sentryId, str5, str6, str7, str, str2, str8, str9, str11);
                            traceContext.setUnknown(concurrentHashMap);
                            jsonObjectReader.endObject();
                            return traceContext;
                        }
                    }
                    str = str3;
                    str2 = str4;
                    TraceContext traceContext2 = new TraceContext(sentryId, str5, str6, str7, str, str2, str8, str9, str11);
                    traceContext2.setUnknown(concurrentHashMap);
                    jsonObjectReader.endObject();
                    return traceContext2;
                }
                String nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -795593025:
                        if (nextName.equals(JsonKeys.USER_SEGMENT)) {
                            c = 0;
                        }
                        switch (c) {
                            case 0:
                                str4 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 1:
                                str3 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 2:
                                str7 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 3:
                                traceContextUser = (TraceContextUser) jsonObjectReader.nextOrNull(iLogger, new TraceContextUser.Deserializer());
                                break;
                            case 4:
                                str9 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 5:
                                str6 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 6:
                                sentryId = new SentryId.Deserializer().deserialize(jsonObjectReader, iLogger);
                                break;
                            case 7:
                                str10 = jsonObjectReader.nextStringOrNull();
                                continue;
                                continue;
                                continue;
                                continue;
                                continue;
                                continue;
                                continue;
                                continue;
                                continue;
                                continue;
                                continue;
                            case '\b':
                                str5 = jsonObjectReader.nextString();
                                break;
                            case '\t':
                                str8 = jsonObjectReader.nextStringOrNull();
                                break;
                            default:
                                if (concurrentHashMap == null) {
                                    concurrentHashMap = new ConcurrentHashMap();
                                }
                                jsonObjectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                                break;
                        }
                        str10 = str11;
                    case -147132913:
                        if (nextName.equals("user_id")) {
                            c = 1;
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case -85904877:
                        if (nextName.equals("environment")) {
                            c = 2;
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case 3599307:
                        if (nextName.equals("user")) {
                            c = 3;
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case 153193045:
                        if (nextName.equals(JsonKeys.SAMPLE_RATE)) {
                            c = 4;
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case 1090594823:
                        if (nextName.equals("release")) {
                            c = 5;
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case 1270300245:
                        if (nextName.equals("trace_id")) {
                            c = 6;
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case 1864843258:
                        if (nextName.equals(JsonKeys.SAMPLED)) {
                            c = 7;
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case 1904812937:
                        if (nextName.equals(JsonKeys.PUBLIC_KEY)) {
                            c = '\b';
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    case 2141246174:
                        if (nextName.equals("transaction")) {
                            c = '\t';
                        }
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                    default:
                        switch (c) {
                        }
                        str10 = str11;
                        break;
                }
            }
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, illegalStateException);
            return illegalStateException;
        }
    }
}
