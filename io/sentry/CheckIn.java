package io.sentry;

import io.sentry.MonitorConfig;
import io.sentry.MonitorContexts;
import io.sentry.protocol.SentryId;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class CheckIn implements JsonUnknown, JsonSerializable {
    private final SentryId checkInId;
    private final MonitorContexts contexts;
    private Double duration;
    private String environment;
    private MonitorConfig monitorConfig;
    private String monitorSlug;
    private String release;
    private String status;
    private Map<String, Object> unknown;

    /* loaded from: classes5.dex */
    public static final class JsonKeys {
        public static final String CHECK_IN_ID = "check_in_id";
        public static final String CONTEXTS = "contexts";
        public static final String DURATION = "duration";
        public static final String ENVIRONMENT = "environment";
        public static final String MONITOR_CONFIG = "monitor_config";
        public static final String MONITOR_SLUG = "monitor_slug";
        public static final String RELEASE = "release";
        public static final String STATUS = "status";
    }

    public CheckIn(String str, CheckInStatus checkInStatus) {
        this((SentryId) null, str, checkInStatus.apiName());
    }

    public CheckIn(SentryId sentryId, String str, CheckInStatus checkInStatus) {
        this(sentryId, str, checkInStatus.apiName());
    }

    public CheckIn(SentryId sentryId, String str, String str2) {
        this.contexts = new MonitorContexts();
        this.checkInId = sentryId == null ? new SentryId() : sentryId;
        this.monitorSlug = str;
        this.status = str2;
    }

    public SentryId getCheckInId() {
        return this.checkInId;
    }

    public String getMonitorSlug() {
        return this.monitorSlug;
    }

    public void setMonitorSlug(String str) {
        this.monitorSlug = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setStatus(CheckInStatus checkInStatus) {
        this.status = checkInStatus.apiName();
    }

    public Double getDuration() {
        return this.duration;
    }

    public void setDuration(Double d) {
        this.duration = d;
    }

    public String getRelease() {
        return this.release;
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public MonitorConfig getMonitorConfig() {
        return this.monitorConfig;
    }

    public void setMonitorConfig(MonitorConfig monitorConfig) {
        this.monitorConfig = monitorConfig;
    }

    public MonitorContexts getContexts() {
        return this.contexts;
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
        objectWriter.name(JsonKeys.CHECK_IN_ID);
        this.checkInId.serialize(objectWriter, iLogger);
        objectWriter.name(JsonKeys.MONITOR_SLUG).value(this.monitorSlug);
        objectWriter.name("status").value(this.status);
        if (this.duration != null) {
            objectWriter.name("duration").value(this.duration);
        }
        if (this.release != null) {
            objectWriter.name("release").value(this.release);
        }
        if (this.environment != null) {
            objectWriter.name("environment").value(this.environment);
        }
        if (this.monitorConfig != null) {
            objectWriter.name(JsonKeys.MONITOR_CONFIG);
            this.monitorConfig.serialize(objectWriter, iLogger);
        }
        if (this.contexts != null) {
            objectWriter.name("contexts");
            this.contexts.serialize(objectWriter, iLogger);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    /* loaded from: classes5.dex */
    public static final class Deserializer implements JsonDeserializer<CheckIn> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Removed duplicated region for block: B:31:0x008a A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0090 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x009b A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00a1 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00a7 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x00b2 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00b8 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00be A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:56:0x007f A[SYNTHETIC] */
        @Override // io.sentry.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public CheckIn deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            jsonObjectReader.beginObject();
            SentryId sentryId = null;
            String str = null;
            String str2 = null;
            HashMap hashMap = null;
            Double d = null;
            String str3 = null;
            String str4 = null;
            MonitorConfig monitorConfig = null;
            MonitorContexts monitorContexts = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                String nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -2075530809:
                        if (nextName.equals(JsonKeys.MONITOR_CONFIG)) {
                            c = 0;
                        }
                        switch (c) {
                            case 0:
                                monitorConfig = new MonitorConfig.Deserializer().deserialize(jsonObjectReader, iLogger);
                                break;
                            case 1:
                                d = jsonObjectReader.nextDoubleOrNull();
                                break;
                            case 2:
                                str2 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 3:
                                monitorContexts = new MonitorContexts.Deserializer().deserialize(jsonObjectReader, iLogger);
                                break;
                            case 4:
                                str4 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 5:
                                str3 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 6:
                                sentryId = new SentryId.Deserializer().deserialize(jsonObjectReader, iLogger);
                                break;
                            case 7:
                                str = jsonObjectReader.nextStringOrNull();
                                break;
                            default:
                                if (hashMap == null) {
                                    hashMap = new HashMap();
                                }
                                jsonObjectReader.nextUnknown(iLogger, hashMap, nextName);
                                break;
                        }
                    case -1992012396:
                        if (nextName.equals("duration")) {
                            c = 1;
                        }
                        switch (c) {
                        }
                        break;
                    case -892481550:
                        if (nextName.equals("status")) {
                            c = 2;
                        }
                        switch (c) {
                        }
                        break;
                    case -567312220:
                        if (nextName.equals("contexts")) {
                            c = 3;
                        }
                        switch (c) {
                        }
                        break;
                    case -85904877:
                        if (nextName.equals("environment")) {
                            c = 4;
                        }
                        switch (c) {
                        }
                        break;
                    case 1090594823:
                        if (nextName.equals("release")) {
                            c = 5;
                        }
                        switch (c) {
                        }
                        break;
                    case 1101887614:
                        if (nextName.equals(JsonKeys.CHECK_IN_ID)) {
                            c = 6;
                        }
                        switch (c) {
                        }
                        break;
                    case 1732390512:
                        if (nextName.equals(JsonKeys.MONITOR_SLUG)) {
                            c = 7;
                        }
                        switch (c) {
                        }
                        break;
                    default:
                        switch (c) {
                        }
                        break;
                }
            }
            jsonObjectReader.endObject();
            if (sentryId == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"check_in_id\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"check_in_id\"", illegalStateException);
                throw illegalStateException;
            }
            if (str == null) {
                IllegalStateException illegalStateException2 = new IllegalStateException("Missing required field \"monitor_slug\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"monitor_slug\"", illegalStateException2);
                throw illegalStateException2;
            }
            if (str2 == null) {
                IllegalStateException illegalStateException3 = new IllegalStateException("Missing required field \"status\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"status\"", illegalStateException3);
                throw illegalStateException3;
            }
            CheckIn checkIn = new CheckIn(sentryId, str, str2);
            checkIn.setDuration(d);
            checkIn.setRelease(str3);
            checkIn.setEnvironment(str4);
            checkIn.setMonitorConfig(monitorConfig);
            checkIn.getContexts().putAll(monitorContexts);
            checkIn.setUnknown(hashMap);
            return checkIn;
        }
    }
}
