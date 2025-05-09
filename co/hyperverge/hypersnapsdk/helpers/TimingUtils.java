package co.hyperverge.hypersnapsdk.helpers;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class TimingUtils implements Serializable {
    public static final int DIM_MICROS = 2;
    public static final int DIM_MILLIS = 1;
    public static final int DIM_NANOS = 3;
    private String dim;
    long oldTime;
    private int timeDimension;

    public TimingUtils() {
        this.timeDimension = 1;
        this.dim = "ms";
        this.oldTime = 0L;
        this.oldTime = getNowTime();
    }

    public TimingUtils(int i) {
        this.timeDimension = 1;
        this.dim = "ms";
        this.oldTime = 0L;
        this.timeDimension = i;
        if (i == 1) {
            this.dim = "ms";
        } else if (i == 2) {
            this.dim = "us";
        } else if (i == 3) {
            this.dim = "ns";
        }
        this.oldTime = getNowTime();
    }

    public void init() {
        this.oldTime = getNowTime();
    }

    public void pitch(String str, String str2) {
        this.oldTime = getNowTime();
    }

    public long getTimeDifferenceAndUpdateLong() {
        long longValue = getTimeDifferenceLong().longValue();
        this.oldTime = getNowTime();
        return longValue;
    }

    public String getTimeDifferenceAndUpdate() {
        long longValue = getTimeDifferenceLong().longValue();
        this.oldTime = getNowTime();
        return longValue + " " + this.dim;
    }

    public long getNowTime() {
        int i = this.timeDimension;
        if (i == 1) {
            return System.currentTimeMillis();
        }
        if (i == 2) {
            return System.nanoTime() / 1000;
        }
        if (i == 3) {
            return System.nanoTime();
        }
        return System.currentTimeMillis();
    }

    public String getTimeDifference() {
        return (getNowTime() - this.oldTime) + " " + this.dim;
    }

    public Long getTimeDifferenceLong() {
        return Long.valueOf(getNowTime() - this.oldTime);
    }
}
