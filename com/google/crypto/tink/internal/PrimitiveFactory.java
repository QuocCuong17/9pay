package com.google.crypto.tink.internal;

import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public abstract class PrimitiveFactory<PrimitiveT, KeyProtoT extends MessageLite> {
    private final Class<PrimitiveT> clazz;

    public abstract PrimitiveT getPrimitive(KeyProtoT key) throws GeneralSecurityException;

    public PrimitiveFactory(Class<PrimitiveT> clazz) {
        this.clazz = clazz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Class<PrimitiveT> getPrimitiveClass() {
        return this.clazz;
    }
}
