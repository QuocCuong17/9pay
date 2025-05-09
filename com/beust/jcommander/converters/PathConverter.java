package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;
import java.nio.file.Path;
import java.nio.file.Paths;

/* loaded from: classes2.dex */
public class PathConverter implements IStringConverter<Path> {
    @Override // com.beust.jcommander.IStringConverter
    public Path convert(String str) {
        return Paths.get(str, new String[0]);
    }
}
