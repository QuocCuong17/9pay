package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
/* loaded from: classes4.dex */
final class OneofInfo {
    private final java.lang.reflect.Field caseField;

    /* renamed from: id, reason: collision with root package name */
    private final int f77id;
    private final java.lang.reflect.Field valueField;

    public OneofInfo(int id2, java.lang.reflect.Field caseField, java.lang.reflect.Field valueField) {
        this.f77id = id2;
        this.caseField = caseField;
        this.valueField = valueField;
    }

    public int getId() {
        return this.f77id;
    }

    public java.lang.reflect.Field getCaseField() {
        return this.caseField;
    }

    public java.lang.reflect.Field getValueField() {
        return this.valueField;
    }
}
