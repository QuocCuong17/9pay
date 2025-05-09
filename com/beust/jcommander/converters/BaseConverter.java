package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;

/* loaded from: classes2.dex */
public abstract class BaseConverter<T> implements IStringConverter<T> {
    private String m_optionName;

    public BaseConverter(String str) {
        this.m_optionName = str;
    }

    public String getOptionName() {
        return this.m_optionName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getErrorString(String str, String str2) {
        return "\"" + getOptionName() + "\": couldn't convert \"" + str + "\" to " + str2;
    }
}
