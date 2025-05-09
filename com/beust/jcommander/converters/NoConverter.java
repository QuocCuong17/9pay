package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;

/* loaded from: classes2.dex */
public class NoConverter implements IStringConverter<String> {
    @Override // com.beust.jcommander.IStringConverter
    public String convert(String str) {
        throw new UnsupportedOperationException();
    }
}
