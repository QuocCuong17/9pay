package org.apache.pdfbox.pdmodel;

/* loaded from: classes5.dex */
public enum PageLayout {
    SINGLE_PAGE("SinglePage"),
    ONE_COLUMN("OneColumn"),
    TWO_COLUMN_LEFT("TwoColumnLeft"),
    TWO_COLUMN_RIGHT("TwoColumnRight"),
    TWO_PAGE_LEFT("TwoPageLeft"),
    TWO_PAGE_RIGHT("TwoPageRight");

    private final String value;

    public static PageLayout fromString(String str) {
        if (str.equals("SinglePage")) {
            return SINGLE_PAGE;
        }
        if (str.equals("OneColumn")) {
            return ONE_COLUMN;
        }
        if (str.equals("TwoColumnLeft")) {
            return TWO_COLUMN_LEFT;
        }
        if (str.equals("TwoPageLeft")) {
            return TWO_PAGE_LEFT;
        }
        if (str.equals("TwoPageRight")) {
            return TWO_PAGE_RIGHT;
        }
        throw new IllegalArgumentException(str);
    }

    PageLayout(String str) {
        this.value = str;
    }

    public String stringValue() {
        return this.value;
    }
}
