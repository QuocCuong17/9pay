package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;
import java.net.URI;
import java.net.URISyntaxException;

/* loaded from: classes2.dex */
public class URIConverter extends BaseConverter<URI> {
    public URIConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public URI convert(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException unused) {
            throw new ParameterException(getErrorString(str, "a RFC 2396 and RFC 2732 compliant URI"));
        }
    }
}
