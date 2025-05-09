package org.apache.pdfbox.pdmodel.graphics.state;

import androidx.exifinterface.media.ExifInterface;

/* loaded from: classes5.dex */
public enum RenderingIntent {
    ABSOLUTE_COLORIMETRIC("AbsoluteColorimetric"),
    RELATIVE_COLORIMETRIC("RelativeColorimetric"),
    SATURATION(ExifInterface.TAG_SATURATION),
    PERCEPTUAL("Perceptual");

    private final String value;

    public static RenderingIntent fromString(String str) {
        if (str.equals("AbsoluteColorimetric")) {
            return ABSOLUTE_COLORIMETRIC;
        }
        if (str.equals("RelativeColorimetric")) {
            return RELATIVE_COLORIMETRIC;
        }
        if (str.equals(ExifInterface.TAG_SATURATION)) {
            return SATURATION;
        }
        if (str.equals("Perceptual")) {
            return PERCEPTUAL;
        }
        throw new IllegalArgumentException(str);
    }

    RenderingIntent(String str) {
        this.value = str;
    }

    public String stringValue() {
        return this.value;
    }
}
