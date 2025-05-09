package co.hyperverge.hvcamera.magicfilter.utils;

/* loaded from: classes2.dex */
public class Settings3A {
    private static final float AE_REGION_BOX = 0.3f;
    private static final float AF_REGION_BOX = 0.2f;
    private static final int FOCUS_HOLD_MILLIS = 3000;
    private static final float GCAM_METERING_REGION_FRACTION = 0.1225f;
    private static final float GCAM_METERING_REGION_WEIGHT = 45.0f;
    private static final int GCAM_POST_SHOT_FOCUS_HOLD_MILLIS = 1000;
    private static final float REGION_WEIGHT = 0.022f;

    @Deprecated
    public static float getAutoFocusRegionWidth() {
        return 0.2f;
    }

    @Deprecated
    public static int getFocusHoldMillis() {
        return 3000;
    }

    @Deprecated
    public static float getGcamMeteringRegionFraction() {
        return GCAM_METERING_REGION_FRACTION;
    }

    @Deprecated
    public static float getGcamMeteringRegionWeight() {
        return GCAM_METERING_REGION_WEIGHT;
    }

    @Deprecated
    public static int getGcamPostShotFocusHoldMillis() {
        return 1000;
    }

    @Deprecated
    public static float getMeteringRegionWeight() {
        return REGION_WEIGHT;
    }

    @Deprecated
    public static float getMeteringRegionWidth() {
        return 0.3f;
    }

    public float getMeteringRegionFraction() {
        return GCAM_METERING_REGION_FRACTION;
    }

    public int getMeteringWeight() {
        return (int) (0 + (1000 * GCAM_METERING_REGION_FRACTION));
    }
}
