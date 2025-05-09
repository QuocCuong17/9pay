package org.apache.fontbox.util.autodetect;

/* loaded from: classes5.dex */
public class MacFontDirFinder extends NativeFontDirFinder {
    @Override // org.apache.fontbox.util.autodetect.NativeFontDirFinder
    protected String[] getSearchableDirectories() {
        return new String[]{System.getProperty("user.home") + "/Library/Fonts/", "/Library/Fonts/", "/System/Library/Fonts/", "/Network/Library/Fonts/"};
    }
}
