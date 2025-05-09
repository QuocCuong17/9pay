package org.apache.fontbox.util.autodetect;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class FontFileFinder {
    private FontDirFinder fontDirFinder = null;

    private FontDirFinder determineDirFinder() {
        if (System.getProperty("java.vendor").equals("The Android Project")) {
            return new AndroidFontDirFinder();
        }
        return new UnixFontDirFinder();
    }

    public List<URI> find() {
        if (this.fontDirFinder == null) {
            this.fontDirFinder = determineDirFinder();
        }
        List<File> find = this.fontDirFinder.find();
        ArrayList arrayList = new ArrayList();
        Iterator<File> it = find.iterator();
        while (it.hasNext()) {
            walk(it.next(), arrayList);
        }
        return arrayList;
    }

    public List<URI> find(String str) {
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        if (file.isDirectory()) {
            walk(file, arrayList);
        }
        return arrayList;
    }

    private void walk(File file, List<URI> list) {
        File[] listFiles;
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                if (!file2.getName().startsWith(".")) {
                    walk(file2, list);
                }
            } else if (checkFontfile(file2)) {
                list.add(file2.toURI());
            }
        }
    }

    private boolean checkFontfile(File file) {
        String lowerCase = file.getName().toLowerCase();
        return lowerCase.endsWith(".ttf") || lowerCase.endsWith(".otf") || lowerCase.endsWith(".pfb") || lowerCase.endsWith(".ttc");
    }
}
