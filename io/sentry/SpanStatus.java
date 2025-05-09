package io.sentry;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes5.dex */
public enum SpanStatus implements JsonSerializable {
    OK(200, 299),
    CANCELLED(499),
    INTERNAL_ERROR(500),
    UNKNOWN(500),
    UNKNOWN_ERROR(500),
    INVALID_ARGUMENT(400),
    DEADLINE_EXCEEDED(TypedValues.PositionType.TYPE_PERCENT_HEIGHT),
    NOT_FOUND(404),
    ALREADY_EXISTS(409),
    PERMISSION_DENIED(TypedValues.CycleType.TYPE_ALPHA),
    RESOURCE_EXHAUSTED(429),
    FAILED_PRECONDITION(400),
    ABORTED(409),
    OUT_OF_RANGE(400),
    UNIMPLEMENTED(TypedValues.PositionType.TYPE_TRANSITION_EASING),
    UNAVAILABLE(TypedValues.PositionType.TYPE_PERCENT_WIDTH),
    DATA_LOSS(500),
    UNAUTHENTICATED(TypedValues.CycleType.TYPE_CURVE_FIT);

    private final int maxHttpStatusCode;
    private final int minHttpStatusCode;

    SpanStatus(int i) {
        this.minHttpStatusCode = i;
        this.maxHttpStatusCode = i;
    }

    SpanStatus(int i, int i2) {
        this.minHttpStatusCode = i;
        this.maxHttpStatusCode = i2;
    }

    public static SpanStatus fromHttpStatusCode(int i) {
        for (SpanStatus spanStatus : values()) {
            if (spanStatus.matches(i)) {
                return spanStatus;
            }
        }
        return null;
    }

    public static SpanStatus fromHttpStatusCode(Integer num, SpanStatus spanStatus) {
        SpanStatus fromHttpStatusCode = num != null ? fromHttpStatusCode(num.intValue()) : spanStatus;
        return fromHttpStatusCode != null ? fromHttpStatusCode : spanStatus;
    }

    private boolean matches(int i) {
        return i >= this.minHttpStatusCode && i <= this.maxHttpStatusCode;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(name().toLowerCase(Locale.ROOT));
    }

    /* loaded from: classes5.dex */
    public static final class Deserializer implements JsonDeserializer<SpanStatus> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SpanStatus deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            return SpanStatus.valueOf(jsonObjectReader.nextString().toUpperCase(Locale.ROOT));
        }
    }
}
