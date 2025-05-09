package com.pichillilorenzo.flutter_inappwebview_android.types;

/* loaded from: classes5.dex */
public enum PreferredContentModeOptionType {
    RECOMMENDED(0),
    MOBILE(1),
    DESKTOP(2);

    private final int value;

    PreferredContentModeOptionType(int i) {
        this.value = i;
    }

    public boolean equalsValue(int i) {
        return this.value == i;
    }

    public static PreferredContentModeOptionType fromValue(int i) {
        for (PreferredContentModeOptionType preferredContentModeOptionType : values()) {
            if (i == preferredContentModeOptionType.toValue()) {
                return preferredContentModeOptionType;
            }
        }
        throw new IllegalArgumentException("No enum constant: " + i);
    }

    public int toValue() {
        return this.value;
    }
}
