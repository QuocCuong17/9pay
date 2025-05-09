package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes4.dex */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);


    /* renamed from: id, reason: collision with root package name */
    private final int f81id;

    DeliveryMechanism(int i) {
        this.f81id = i;
    }

    public int getId() {
        return this.f81id;
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(this.f81id);
    }

    public static DeliveryMechanism determineFrom(String str) {
        return str != null ? APP_STORE : DEVELOPER;
    }
}
