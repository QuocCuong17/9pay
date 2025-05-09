package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeysetHandle;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: classes4.dex */
public final class HybridEncryptFactory {
    @Deprecated
    public static HybridEncrypt getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        HybridEncryptWrapper.register();
        return (HybridEncrypt) keysetHandle.getPrimitive(HybridEncrypt.class);
    }

    private HybridEncryptFactory() {
    }
}
