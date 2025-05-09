package com.beust.jcommander;

/* loaded from: classes2.dex */
public interface IValueValidator<T> {
    void validate(String str, T t) throws ParameterException;
}
