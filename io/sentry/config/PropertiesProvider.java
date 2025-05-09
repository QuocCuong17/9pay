package io.sentry.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface PropertiesProvider {
    Boolean getBooleanProperty(String str);

    Double getDoubleProperty(String str);

    List<String> getList(String str);

    Long getLongProperty(String str);

    Map<String, String> getMap(String str);

    String getProperty(String str);

    String getProperty(String str, String str2);

    /* renamed from: io.sentry.config.PropertiesProvider$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static List $default$getList(PropertiesProvider _this, String str) {
            String property = _this.getProperty(str);
            return property != null ? Arrays.asList(property.split(",")) : Collections.emptyList();
        }

        public static String $default$getProperty(PropertiesProvider _this, String str, String str2) {
            String property = _this.getProperty(str);
            return property != null ? property : str2;
        }

        public static Boolean $default$getBooleanProperty(PropertiesProvider _this, String str) {
            String property = _this.getProperty(str);
            if (property != null) {
                return Boolean.valueOf(property);
            }
            return null;
        }

        public static Double $default$getDoubleProperty(PropertiesProvider _this, String str) {
            String property = _this.getProperty(str);
            if (property != null) {
                try {
                    return Double.valueOf(property);
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }

        public static Long $default$getLongProperty(PropertiesProvider _this, String str) {
            String property = _this.getProperty(str);
            if (property != null) {
                try {
                    return Long.valueOf(property);
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }
    }
}
