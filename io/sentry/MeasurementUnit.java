package io.sentry;

import java.util.Locale;

/* loaded from: classes5.dex */
public interface MeasurementUnit {
    public static final String NONE = "none";

    /* loaded from: classes5.dex */
    public enum Duration implements MeasurementUnit {
        NANOSECOND,
        MICROSECOND,
        MILLISECOND,
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK;

        @Override // io.sentry.MeasurementUnit
        public /* synthetic */ String apiName() {
            return CC.$default$apiName(this);
        }
    }

    /* loaded from: classes5.dex */
    public enum Fraction implements MeasurementUnit {
        RATIO,
        PERCENT;

        @Override // io.sentry.MeasurementUnit
        public /* synthetic */ String apiName() {
            return CC.$default$apiName(this);
        }
    }

    /* loaded from: classes5.dex */
    public enum Information implements MeasurementUnit {
        BIT,
        BYTE,
        KILOBYTE,
        KIBIBYTE,
        MEGABYTE,
        MEBIBYTE,
        GIGABYTE,
        GIBIBYTE,
        TERABYTE,
        TEBIBYTE,
        PETABYTE,
        PEBIBYTE,
        EXABYTE,
        EXBIBYTE;

        @Override // io.sentry.MeasurementUnit
        public /* synthetic */ String apiName() {
            return CC.$default$apiName(this);
        }
    }

    String apiName();

    String name();

    /* loaded from: classes5.dex */
    public static final class Custom implements MeasurementUnit {
        private final String name;

        @Override // io.sentry.MeasurementUnit
        public /* synthetic */ String apiName() {
            return CC.$default$apiName(this);
        }

        public Custom(String str) {
            this.name = str;
        }

        @Override // io.sentry.MeasurementUnit
        public String name() {
            return this.name;
        }
    }

    /* renamed from: io.sentry.MeasurementUnit$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static String $default$apiName(MeasurementUnit _this) {
            return _this.name().toLowerCase(Locale.ROOT);
        }
    }
}
