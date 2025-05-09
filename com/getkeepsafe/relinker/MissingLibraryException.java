package com.getkeepsafe.relinker;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class MissingLibraryException extends RuntimeException {
    public MissingLibraryException(final String library, final String[] wantedABIs, final String[] supportedABIs) {
        super("Could not find '" + library + "'. Looked for: " + Arrays.toString(wantedABIs) + ", but only found: " + Arrays.toString(supportedABIs) + ".");
    }
}
