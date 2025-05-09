package com.fasterxml.jackson.core.type;

import com.fasterxml.jackson.core.JsonToken;

/* loaded from: classes2.dex */
public class WritableTypeId {
    public String asProperty;
    public Object extra;
    public Object forValue;
    public Class<?> forValueType;

    /* renamed from: id, reason: collision with root package name */
    public Object f67id;
    public Inclusion include;
    public JsonToken valueShape;
    public boolean wrapperWritten;

    /* loaded from: classes2.dex */
    public enum Inclusion {
        WRAPPER_ARRAY,
        WRAPPER_OBJECT,
        METADATA_PROPERTY,
        PAYLOAD_PROPERTY,
        PARENT_PROPERTY;

        public boolean requiresObjectContext() {
            return this == METADATA_PROPERTY || this == PAYLOAD_PROPERTY;
        }
    }

    public WritableTypeId() {
    }

    public WritableTypeId(Object obj, JsonToken jsonToken) {
        this(obj, jsonToken, (Object) null);
    }

    public WritableTypeId(Object obj, Class<?> cls, JsonToken jsonToken) {
        this(obj, jsonToken, (Object) null);
        this.forValueType = cls;
    }

    public WritableTypeId(Object obj, JsonToken jsonToken, Object obj2) {
        this.forValue = obj;
        this.f67id = obj2;
        this.valueShape = jsonToken;
    }
}
