package com.beust.jcommander.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

/* loaded from: classes2.dex */
public class NoValueValidator<T> implements IValueValidator<T> {
    @Override // com.beust.jcommander.IValueValidator
    public void validate(String str, T t) throws ParameterException {
    }
}
