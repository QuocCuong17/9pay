package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;
import com.facebook.internal.ServerProtocol;

/* loaded from: classes2.dex */
public class BooleanConverter extends BaseConverter<Boolean> {
    public BooleanConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Boolean convert(String str) {
        if ("false".equalsIgnoreCase(str) || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str)) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
        throw new ParameterException(getErrorString(str, "a boolean"));
    }
}
