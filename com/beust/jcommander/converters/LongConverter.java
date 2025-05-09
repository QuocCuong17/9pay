package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;

/* loaded from: classes2.dex */
public class LongConverter extends BaseConverter<Long> {
    public LongConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Long convert(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            throw new ParameterException(getErrorString(str, "a long"));
        }
    }
}
