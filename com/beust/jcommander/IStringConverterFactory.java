package com.beust.jcommander;

/* loaded from: classes2.dex */
public interface IStringConverterFactory {
    <T> Class<? extends IStringConverter<T>> getConverter(Class<T> cls);
}
