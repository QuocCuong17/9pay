package io.sentry;

import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class MonitorSchedule implements JsonUnknown, JsonSerializable {
    private String type;
    private String unit;
    private Map<String, Object> unknown;
    private String value;

    /* loaded from: classes5.dex */
    public static final class JsonKeys {
        public static final String TYPE = "type";
        public static final String UNIT = "unit";
        public static final String VALUE = "value";
    }

    public static MonitorSchedule crontab(String str) {
        return new MonitorSchedule(MonitorScheduleType.CRONTAB.apiName(), str, null);
    }

    public static MonitorSchedule interval(Integer num, MonitorScheduleUnit monitorScheduleUnit) {
        return new MonitorSchedule(MonitorScheduleType.INTERVAL.apiName(), num.toString(), monitorScheduleUnit.apiName());
    }

    public MonitorSchedule(String str, String str2, String str3) {
        this.type = str;
        this.value = str2;
        this.unit = str3;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setValue(Integer num) {
        this.value = num.toString();
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setUnit(MonitorScheduleUnit monitorScheduleUnit) {
        this.unit = monitorScheduleUnit == null ? null : monitorScheduleUnit.apiName();
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
        objectWriter.name("type").value(this.type);
        if (MonitorScheduleType.INTERVAL.apiName().equalsIgnoreCase(this.type)) {
            try {
                objectWriter.name("value").value(Integer.valueOf(this.value));
            } catch (Throwable unused) {
                iLogger.log(SentryLevel.ERROR, "Unable to serialize monitor schedule value: %s", this.value);
            }
        } else {
            objectWriter.name("value").value(this.value);
        }
        if (this.unit != null) {
            objectWriter.name("unit").value(this.unit);
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
    public static final class Deserializer implements JsonDeserializer<MonitorSchedule> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0050 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0055 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x005a A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0045 A[SYNTHETIC] */
        @Override // io.sentry.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public MonitorSchedule deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            jsonObjectReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            HashMap hashMap = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                String nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case 3575610:
                        if (nextName.equals("type")) {
                            c = 0;
                        }
                        switch (c) {
                            case 0:
                                str = jsonObjectReader.nextStringOrNull();
                                break;
                            case 1:
                                str3 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 2:
                                str2 = jsonObjectReader.nextStringOrNull();
                                break;
                            default:
                                if (hashMap == null) {
                                    hashMap = new HashMap();
                                }
                                jsonObjectReader.nextUnknown(iLogger, hashMap, nextName);
                                break;
                        }
                    case 3594628:
                        if (nextName.equals("unit")) {
                            c = 1;
                        }
                        switch (c) {
                        }
                        break;
                    case 111972721:
                        if (nextName.equals("value")) {
                            c = 2;
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
            if (str == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"type\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"type\"", illegalStateException);
                throw illegalStateException;
            }
            if (str2 == null) {
                IllegalStateException illegalStateException2 = new IllegalStateException("Missing required field \"value\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"value\"", illegalStateException2);
                throw illegalStateException2;
            }
            MonitorSchedule monitorSchedule = new MonitorSchedule(str, str2, str3);
            monitorSchedule.setUnknown(hashMap);
            return monitorSchedule;
        }
    }
}
