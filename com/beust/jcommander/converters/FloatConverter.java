package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;

/* loaded from: classes2.dex */
public class FloatConverter extends BaseConverter<Float> {
    public FloatConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Float convert(String str) {
        try {
            return Float.valueOf(Float.parseFloat(str));
        } catch (NumberFormatException unused) {
            throw new ParameterException(getErrorString(str, "a float"));
        }
    }
}
