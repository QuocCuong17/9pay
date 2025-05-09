package com.getkeepsafe.relinker;

import android.os.Build;
import com.getkeepsafe.relinker.ReLinker;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SystemLibraryLoader implements ReLinker.LibraryLoader {
    @Override // com.getkeepsafe.relinker.ReLinker.LibraryLoader
    public void loadLibrary(final String libraryName) {
        System.loadLibrary(libraryName);
    }

    @Override // com.getkeepsafe.relinker.ReLinker.LibraryLoader
    public void loadPath(final String libraryPath) {
        System.load(libraryPath);
    }

    @Override // com.getkeepsafe.relinker.ReLinker.LibraryLoader
    public String mapLibraryName(final String libraryName) {
        return (libraryName.startsWith("lib") && libraryName.endsWith(".so")) ? libraryName : System.mapLibraryName(libraryName);
    }

    @Override // com.getkeepsafe.relinker.ReLinker.LibraryLoader
    public String unmapLibraryName(String mappedLibraryName) {
        return mappedLibraryName.substring(3, mappedLibraryName.length() - 3);
    }

    @Override // com.getkeepsafe.relinker.ReLinker.LibraryLoader
    public String[] supportedAbis() {
        if (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) {
            return !TextUtils.isEmpty(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI};
        }
        return Build.SUPPORTED_ABIS;
    }
}
