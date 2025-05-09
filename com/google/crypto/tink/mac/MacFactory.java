package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: classes4.dex */
public final class MacFactory {
    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        MacWrapper.register();
        return (Mac) keysetHandle.getPrimitive(Mac.class);
    }

    private MacFactory() {
    }
}
