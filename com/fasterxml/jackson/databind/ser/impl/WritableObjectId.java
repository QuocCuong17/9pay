package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class WritableObjectId {
    public final ObjectIdGenerator<?> generator;

    /* renamed from: id, reason: collision with root package name */
    public Object f68id;
    protected boolean idWritten = false;

    public WritableObjectId(ObjectIdGenerator<?> objectIdGenerator) {
        this.generator = objectIdGenerator;
    }

    public boolean writeAsId(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) throws IOException {
        if (this.f68id == null) {
            return false;
        }
        if (!this.idWritten && !objectIdWriter.alwaysAsId) {
            return false;
        }
        if (jsonGenerator.canWriteObjectId()) {
            jsonGenerator.writeObjectRef(String.valueOf(this.f68id));
            return true;
        }
        objectIdWriter.serializer.serialize(this.f68id, jsonGenerator, serializerProvider);
        return true;
    }

    public Object generateId(Object obj) {
        if (this.f68id == null) {
            this.f68id = this.generator.generateId(obj);
        }
        return this.f68id;
    }

    public void writeAsField(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) throws IOException {
        this.idWritten = true;
        if (jsonGenerator.canWriteObjectId()) {
            Object obj = this.f68id;
            jsonGenerator.writeObjectId(obj == null ? null : String.valueOf(obj));
            return;
        }
        SerializableString serializableString = objectIdWriter.propertyName;
        if (serializableString != null) {
            jsonGenerator.writeFieldName(serializableString);
            objectIdWriter.serializer.serialize(this.f68id, jsonGenerator, serializerProvider);
        }
    }
}
