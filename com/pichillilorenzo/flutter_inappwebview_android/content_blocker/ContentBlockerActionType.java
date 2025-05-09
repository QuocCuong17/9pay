package com.pichillilorenzo.flutter_inappwebview_android.content_blocker;

/* loaded from: classes4.dex */
public enum ContentBlockerActionType {
    BLOCK("block"),
    CSS_DISPLAY_NONE("css-display-none"),
    MAKE_HTTPS("make-https");

    private final String value;

    ContentBlockerActionType(String str) {
        this.value = str;
    }

    public boolean equalsValue(String str) {
        return this.value.equals(str);
    }

    public static ContentBlockerActionType fromValue(String str) {
        for (ContentBlockerActionType contentBlockerActionType : values()) {
            if (str.equals(contentBlockerActionType.value)) {
                return contentBlockerActionType;
            }
        }
        throw new IllegalArgumentException("No enum constant: " + str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
