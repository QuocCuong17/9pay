package com.pichillilorenzo.flutter_inappwebview_android.types;

/* loaded from: classes5.dex */
public enum NavigationActionPolicy {
    CANCEL(0),
    ALLOW(1);

    private final int value;

    NavigationActionPolicy(int i) {
        this.value = i;
    }

    public boolean equalsValue(int i) {
        return this.value == i;
    }

    public static NavigationActionPolicy fromValue(int i) {
        for (NavigationActionPolicy navigationActionPolicy : values()) {
            if (i == navigationActionPolicy.value) {
                return navigationActionPolicy;
            }
        }
        throw new IllegalArgumentException("No enum constant: " + i);
    }

    public int rawValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }
}
