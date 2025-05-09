package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;

/* loaded from: classes2.dex */
public class DoubleConverter extends BaseConverter<Double> {
    public DoubleConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Double convert(String str) {
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            throw new ParameterException(getErrorString(str, "a double"));
        }
    }
}
