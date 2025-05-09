package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeysetHandle;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: classes4.dex */
public final class DeterministicAeadFactory {
    @Deprecated
    public static DeterministicAead getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        DeterministicAeadWrapper.register();
        return (DeterministicAead) keysetHandle.getPrimitive(DeterministicAead.class);
    }

    private DeterministicAeadFactory() {
    }
}
