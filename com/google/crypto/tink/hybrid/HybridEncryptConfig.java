package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Config;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: classes4.dex */
public final class HybridEncryptConfig {
    @Deprecated
    public static void registerStandardKeyTypes() throws GeneralSecurityException {
        Config.register(HybridConfig.TINK_1_0_0);
    }

    private HybridEncryptConfig() {
    }
}
