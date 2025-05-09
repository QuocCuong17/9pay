package com.google.crypto.tink.daead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public final class DeterministicAeadConfig {
    public static final String AES_SIV_TYPE_URL = new AesSivKeyManager().getKeyType();

    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = RegistryConfig.getDefaultInstance();

    @Deprecated
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();

    static {
        try {
            register();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        DeterministicAeadWrapper.register();
        if (TinkFips.useOnlyFips()) {
            return;
        }
        AesSivKeyManager.register(true);
    }

    private DeterministicAeadConfig() {
    }
}
