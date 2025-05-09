package org.apache.fontbox.util.autodetect;

/* loaded from: classes5.dex */
public class AndroidFontDirFinder extends NativeFontDirFinder {
    @Override // org.apache.fontbox.util.autodetect.NativeFontDirFinder
    protected String[] getSearchableDirectories() {
        return new String[]{"/system/fonts"};
    }
}
