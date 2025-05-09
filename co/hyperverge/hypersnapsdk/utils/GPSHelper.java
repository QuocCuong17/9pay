package co.hyperverge.hypersnapsdk.utils;

/* loaded from: classes2.dex */
public class GPSHelper {
    private static StringBuilder sb = new StringBuilder(20);

    public static String latitudeRef(double d) {
        return d < 0.0d ? "S" : "N";
    }

    public static String longitudeRef(double d) {
        return d < 0.0d ? "W" : "E";
    }

    public static final synchronized String convert(double d) {
        String sb2;
        synchronized (GPSHelper.class) {
            double abs = Math.abs(d);
            int i = (int) abs;
            double d2 = (abs * 60.0d) - (i * 60.0d);
            int i2 = (int) d2;
            sb.setLength(0);
            sb.append(i);
            sb.append("/1,");
            sb.append(i2);
            sb.append("/1,");
            sb.append((int) (((d2 * 60.0d) - (i2 * 60.0d)) * 1000.0d));
            sb.append("/1000");
            sb2 = sb.toString();
        }
        return sb2;
    }
}
