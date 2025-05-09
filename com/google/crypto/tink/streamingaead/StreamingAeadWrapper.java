package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public class StreamingAeadWrapper implements PrimitiveWrapper<StreamingAead, StreamingAead> {
    private static final StreamingAeadWrapper WRAPPER = new StreamingAeadWrapper();

    StreamingAeadWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public StreamingAead wrap(final PrimitiveSet<StreamingAead> primitives) throws GeneralSecurityException {
        return new StreamingAeadHelper(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<StreamingAead> getPrimitiveClass() {
        return StreamingAead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<StreamingAead> getInputPrimitiveClass() {
        return StreamingAead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
