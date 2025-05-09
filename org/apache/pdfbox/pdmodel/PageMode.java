package org.apache.pdfbox.pdmodel;

import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;

/* loaded from: classes5.dex */
public enum PageMode {
    USE_NONE(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_NONE),
    USE_OUTLINES(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_OUTLINES),
    USE_THUMBS(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_THUMBS),
    FULL_SCREEN("FullScreen"),
    USE_OPTIONAL_CONTENT(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_OPTIONAL_CONTENT),
    USE_ATTACHMENTS("UseAttachments");

    private final String value;

    public static PageMode fromString(String str) {
        if (str.equals(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_NONE)) {
            return USE_NONE;
        }
        if (str.equals(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_OUTLINES)) {
            return USE_OUTLINES;
        }
        if (str.equals(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_THUMBS)) {
            return USE_THUMBS;
        }
        if (str.equals("FullScreen")) {
            return FULL_SCREEN;
        }
        if (str.equals(PDViewerPreferences.NON_FULL_SCREEN_PAGE_MODE_USE_OPTIONAL_CONTENT)) {
            return USE_OPTIONAL_CONTENT;
        }
        if (str.equals("UseAttachments")) {
            return USE_ATTACHMENTS;
        }
        throw new IllegalArgumentException(str);
    }

    PageMode(String str) {
        this.value = str;
    }

    public String stringValue() {
        return this.value;
    }
}
