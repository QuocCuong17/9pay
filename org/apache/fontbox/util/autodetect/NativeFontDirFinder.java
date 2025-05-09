package org.apache.fontbox.util.autodetect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class NativeFontDirFinder implements FontDirFinder {
    protected abstract String[] getSearchableDirectories();

    @Override // org.apache.fontbox.util.autodetect.FontDirFinder
    public List<File> find() {
        ArrayList arrayList = new ArrayList();
        String[] searchableDirectories = getSearchableDirectories();
        if (searchableDirectories != null) {
            for (String str : searchableDirectories) {
                File file = new File(str);
                if (file.exists() && file.canRead()) {
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }
}
