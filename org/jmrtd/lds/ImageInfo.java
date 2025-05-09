package org.jmrtd.lds;

import java.io.InputStream;

/* loaded from: classes6.dex */
public interface ImageInfo extends LDSElement {
    public static final String JPEG2000_MIME_TYPE = "image/jp2";
    public static final String JPEG_MIME_TYPE = "image/jpeg";
    public static final int TYPE_FINGER = 2;
    public static final int TYPE_IRIS = 3;
    public static final int TYPE_PORTRAIT = 0;
    public static final int TYPE_SIGNATURE_OR_MARK = 1;
    public static final int TYPE_UNKNOWN = -1;
    public static final String WSQ_MIME_TYPE = "image/x-wsq";

    int getHeight();

    InputStream getImageInputStream();

    int getImageLength();

    String getMimeType();

    long getRecordLength();

    int getType();

    int getWidth();
}
