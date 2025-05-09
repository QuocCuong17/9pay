package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;

@Immutable
/* loaded from: classes4.dex */
interface HpkeKemPrivateKey {
    Bytes getSerializedPrivate();

    Bytes getSerializedPublic();
}
