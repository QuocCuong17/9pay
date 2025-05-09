package net.sf.scuba.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public abstract class Country implements Serializable {
    private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");
    private static final Class<?>[] SUB_CLASSES = {UnicodeCountry.class, ISOCountry.class, TestCountry.class};
    private static final long serialVersionUID = 9117477643532355118L;

    public abstract String getName();

    public abstract String getNationality();

    public abstract String toAlpha2Code();

    public abstract String toAlpha3Code();

    public abstract int valueOf();

    public static Country getInstance(int i) {
        for (Country country : values()) {
            if (country.valueOf() == i) {
                return country;
            }
        }
        throw new IllegalArgumentException("Illegal country code" + Integer.toHexString(i));
    }

    public static Country getInstance(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Illegal country code");
        }
        String trim = str.trim();
        int length = trim.length();
        if (length == 2) {
            return fromAlpha2(trim);
        }
        if (length == 3) {
            return fromAlpha3(trim);
        }
        throw new IllegalArgumentException("Illegal country code " + trim);
    }

    public static Country[] values() {
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls : SUB_CLASSES) {
            if (Country.class.isAssignableFrom(cls)) {
                try {
                    arrayList.addAll(Arrays.asList((Country[]) cls.getMethod("values", new Class[0]).invoke(null, new Object[0])));
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
                }
            }
        }
        Country[] countryArr = new Country[arrayList.size()];
        arrayList.toArray(countryArr);
        return countryArr;
    }

    private static Country fromAlpha2(String str) {
        for (Country country : values()) {
            if (country.toAlpha2Code().equals(str)) {
                return country;
            }
        }
        throw new IllegalArgumentException("Unknown country code " + str);
    }

    private static Country fromAlpha3(String str) {
        for (Country country : values()) {
            if (country.toAlpha3Code().equals(str)) {
                return country;
            }
        }
        throw new IllegalArgumentException("Unknown country code " + str);
    }
}
