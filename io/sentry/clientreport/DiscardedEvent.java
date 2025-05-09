package io.sentry.clientreport;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonObjectReader;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class DiscardedEvent implements JsonUnknown, JsonSerializable {
    private final String category;
    private final Long quantity;
    private final String reason;
    private Map<String, Object> unknown;

    /* loaded from: classes5.dex */
    public static final class JsonKeys {
        public static final String CATEGORY = "category";
        public static final String QUANTITY = "quantity";
        public static final String REASON = "reason";
    }

    public DiscardedEvent(String str, String str2, Long l) {
        this.reason = str;
        this.category = str2;
        this.quantity = l;
    }

    public String getReason() {
        return this.reason;
    }

    public String getCategory() {
        return this.category;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public String toString() {
        return "DiscardedEvent{reason='" + this.reason + "', category='" + this.category + "', quantity=" + this.quantity + '}';
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
        objectWriter.name("reason").value(this.reason);
        objectWriter.name("category").value(this.category);
        objectWriter.name("quantity").value(this.quantity);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    /* loaded from: classes5.dex */
    public static final class Deserializer implements JsonDeserializer<DiscardedEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Removed duplicated region for block: B:16:0x004d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0052 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0057 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0042 A[SYNTHETIC] */
        @Override // io.sentry.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public DiscardedEvent deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            jsonObjectReader.beginObject();
            String str = null;
            String str2 = null;
            Long l = null;
            HashMap hashMap = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                String nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -1285004149:
                        if (nextName.equals("quantity")) {
                            c = 0;
                        }
                        switch (c) {
                            case 0:
                                l = jsonObjectReader.nextLongOrNull();
                                break;
                            case 1:
                                str = jsonObjectReader.nextStringOrNull();
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
                    case -934964668:
                        if (nextName.equals("reason")) {
                            c = 1;
                        }
                        switch (c) {
                        }
                        break;
                    case 50511102:
                        if (nextName.equals("category")) {
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
                throw missingRequiredFieldException("reason", iLogger);
            }
            if (str2 == null) {
                throw missingRequiredFieldException("category", iLogger);
            }
            if (l == null) {
                throw missingRequiredFieldException("quantity", iLogger);
            }
            DiscardedEvent discardedEvent = new DiscardedEvent(str, str2, l);
            discardedEvent.setUnknown(hashMap);
            return discardedEvent;
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, illegalStateException);
            return illegalStateException;
        }
    }
}
