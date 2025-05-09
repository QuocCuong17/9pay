package com.google.crypto.tink.config;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public final class TinkFips {
    public static boolean useOnlyFips() {
        return TinkFipsUtil.useOnlyFips();
    }

    public static void restrictToFips() throws GeneralSecurityException {
        Registry.restrictToFipsIfEmpty();
    }

    private TinkFips() {
    }
}
