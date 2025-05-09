package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/* loaded from: classes5.dex */
public interface IOFileFilter extends FileFilter, FilenameFilter {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    @Override // java.io.FileFilter
    boolean accept(File file);

    boolean accept(File file, String str);
}
