package com.fasterxml.jackson.databind.jsontype;

import com.android.tools.r8.annotations.SynthesizedClass;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import java.util.Collection;

/* loaded from: classes3.dex */
public interface TypeResolverBuilder<T extends TypeResolverBuilder<T>> {
    TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection);

    TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection);

    T defaultImpl(Class<?> cls);

    Class<?> getDefaultImpl();

    T inclusion(JsonTypeInfo.As as);

    T init(JsonTypeInfo.Id id2, TypeIdResolver typeIdResolver);

    T typeIdVisibility(boolean z);

    T typeProperty(String str);

    T withDefaultImpl(Class<?> cls);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder$-CC, reason: invalid class name */
    /* loaded from: classes3.dex */
    public final /* synthetic */ class CC<T extends TypeResolverBuilder<T>> {
    }
}
