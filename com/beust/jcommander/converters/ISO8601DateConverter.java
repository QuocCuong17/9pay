package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class ISO8601DateConverter extends BaseConverter<Date> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public ISO8601DateConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Date convert(String str) {
        try {
            return DATE_FORMAT.parse(str);
        } catch (ParseException unused) {
            throw new ParameterException(getErrorString(str, String.format("an ISO-8601 formatted date (%s)", DATE_FORMAT.toPattern())));
        }
    }
}
