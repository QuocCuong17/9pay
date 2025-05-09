package io.sentry;

import io.sentry.SentryItemType;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final class SentryEnvelopeItemHeader implements JsonSerializable, JsonUnknown {
    private final String attachmentType;
    private final String contentType;
    private final String fileName;
    private final Callable<Integer> getLength;
    private final int length;
    private final SentryItemType type;
    private Map<String, Object> unknown;

    /* loaded from: classes5.dex */
    public static final class JsonKeys {
        public static final String ATTACHMENT_TYPE = "attachment_type";
        public static final String CONTENT_TYPE = "content_type";
        public static final String FILENAME = "filename";
        public static final String LENGTH = "length";
        public static final String TYPE = "type";
    }

    public SentryItemType getType() {
        return this.type;
    }

    public int getLength() {
        Callable<Integer> callable = this.getLength;
        if (callable != null) {
            try {
                return callable.call().intValue();
            } catch (Throwable unused) {
                return -1;
            }
        }
        return this.length;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public SentryEnvelopeItemHeader(SentryItemType sentryItemType, int i, String str, String str2, String str3) {
        this.type = (SentryItemType) Objects.requireNonNull(sentryItemType, "type is required");
        this.contentType = str;
        this.length = i;
        this.fileName = str2;
        this.getLength = null;
        this.attachmentType = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SentryEnvelopeItemHeader(SentryItemType sentryItemType, Callable<Integer> callable, String str, String str2, String str3) {
        this.type = (SentryItemType) Objects.requireNonNull(sentryItemType, "type is required");
        this.contentType = str;
        this.length = -1;
        this.fileName = str2;
        this.getLength = callable;
        this.attachmentType = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SentryEnvelopeItemHeader(SentryItemType sentryItemType, Callable<Integer> callable, String str, String str2) {
        this(sentryItemType, callable, str, str2, (String) null);
    }

    public String getAttachmentType() {
        return this.attachmentType;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.contentType != null) {
            objectWriter.name("content_type").value(this.contentType);
        }
        if (this.fileName != null) {
            objectWriter.name("filename").value(this.fileName);
        }
        objectWriter.name("type").value(iLogger, this.type);
        if (this.attachmentType != null) {
            objectWriter.name(JsonKeys.ATTACHMENT_TYPE).value(this.attachmentType);
        }
        objectWriter.name("length").value(getLength());
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
    public static final class Deserializer implements JsonDeserializer<SentryEnvelopeItemHeader> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0067 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x006d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x007a A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0080 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0086 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x005c A[SYNTHETIC] */
        @Override // io.sentry.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public SentryEnvelopeItemHeader deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            jsonObjectReader.beginObject();
            HashMap hashMap = null;
            int i = 0;
            SentryItemType sentryItemType = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                String nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -1106363674:
                        if (nextName.equals("length")) {
                            c = 0;
                        }
                        switch (c) {
                            case 0:
                                i = jsonObjectReader.nextInt();
                                break;
                            case 1:
                                str2 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 2:
                                str3 = jsonObjectReader.nextStringOrNull();
                                break;
                            case 3:
                                sentryItemType = (SentryItemType) jsonObjectReader.nextOrNull(iLogger, new SentryItemType.Deserializer());
                                break;
                            case 4:
                                str = jsonObjectReader.nextStringOrNull();
                                break;
                            default:
                                if (hashMap == null) {
                                    hashMap = new HashMap();
                                }
                                jsonObjectReader.nextUnknown(iLogger, hashMap, nextName);
                                break;
                        }
                    case -734768633:
                        if (nextName.equals("filename")) {
                            c = 1;
                        }
                        switch (c) {
                        }
                        break;
                    case -672977706:
                        if (nextName.equals(JsonKeys.ATTACHMENT_TYPE)) {
                            c = 2;
                        }
                        switch (c) {
                        }
                        break;
                    case 3575610:
                        if (nextName.equals("type")) {
                            c = 3;
                        }
                        switch (c) {
                        }
                        break;
                    case 831846208:
                        if (nextName.equals("content_type")) {
                            c = 4;
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
            if (sentryItemType == null) {
                throw missingRequiredFieldException("type", iLogger);
            }
            SentryEnvelopeItemHeader sentryEnvelopeItemHeader = new SentryEnvelopeItemHeader(sentryItemType, i, str, str2, str3);
            sentryEnvelopeItemHeader.setUnknown(hashMap);
            jsonObjectReader.endObject();
            return sentryEnvelopeItemHeader;
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, illegalStateException);
            return illegalStateException;
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
}
