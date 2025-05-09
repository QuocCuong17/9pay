package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class BigDecimalConverter extends BaseConverter<BigDecimal> {
    public BigDecimalConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public BigDecimal convert(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException unused) {
            throw new ParameterException(getErrorString(str, "a BigDecimal"));
        }
    }
}
