package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;

/* loaded from: classes2.dex */
public class IntegerConverter extends BaseConverter<Integer> {
    public IntegerConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Integer convert(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            throw new ParameterException(getErrorString(str, "an integer"));
        }
    }
}
