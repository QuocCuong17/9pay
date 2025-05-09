package com.google.crypto.tink.prf;

import com.google.crypto.tink.config.TinkFips;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public final class PrfConfig {
    public static final String PRF_TYPE_URL = new HkdfPrfKeyManager().getKeyType();

    public static void register() throws GeneralSecurityException {
        PrfSetWrapper.register();
        HmacPrfKeyManager.register(true);
        if (TinkFips.useOnlyFips()) {
            return;
        }
        AesCmacPrfKeyManager.register(true);
        HkdfPrfKeyManager.register(true);
    }

    private PrfConfig() {
    }
}
