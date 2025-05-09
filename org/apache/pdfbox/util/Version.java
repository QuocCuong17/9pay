package org.apache.pdfbox.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/* loaded from: classes5.dex */
public final class Version {
    private static final String PDFBOX_VERSION_PROPERTIES = "org/apache/pdfbox/resources/pdfbox.properties";

    private Version() {
    }

    public static String getVersion() {
        try {
            URL resource = Version.class.getClassLoader().getResource(PDFBOX_VERSION_PROPERTIES);
            if (resource == null) {
                return null;
            }
            Properties properties = new Properties();
            properties.load(resource.openStream());
            return properties.getProperty("pdfbox.version", null);
        } catch (IOException unused) {
            return null;
        }
    }
}
