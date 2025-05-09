package com.getkeepsafe.relinker;

import android.content.Context;
import java.io.File;

/* loaded from: classes3.dex */
public class ReLinker {

    /* loaded from: classes3.dex */
    public interface LibraryInstaller {
        void installLibrary(Context context, String[] abis, String mappedLibraryName, File destination, ReLinkerInstance logger);
    }

    /* loaded from: classes3.dex */
    public interface LibraryLoader {
        void loadLibrary(String libraryName);

        void loadPath(String libraryPath);

        String mapLibraryName(String libraryName);

        String[] supportedAbis();

        String unmapLibraryName(String mappedLibraryName);
    }

    /* loaded from: classes3.dex */
    public interface LoadListener {
        void failure(Throwable t);

        void success();
    }

    /* loaded from: classes3.dex */
    public interface Logger {
        void log(String message);
    }

    public static void loadLibrary(final Context context, final String library) {
        loadLibrary(context, library, null, null);
    }

    public static void loadLibrary(final Context context, final String library, final String version) {
        loadLibrary(context, library, version, null);
    }

    public static void loadLibrary(final Context context, final String library, final LoadListener listener) {
        loadLibrary(context, library, null, listener);
    }

    public static void loadLibrary(final Context context, final String library, final String version, final LoadListener listener) {
        new ReLinkerInstance().loadLibrary(context, library, version, listener);
    }

    public static ReLinkerInstance force() {
        return new ReLinkerInstance().force();
    }

    public static ReLinkerInstance log(final Logger logger) {
        return new ReLinkerInstance().log(logger);
    }

    public static ReLinkerInstance recursively() {
        return new ReLinkerInstance().recursively();
    }

    private ReLinker() {
    }
}
