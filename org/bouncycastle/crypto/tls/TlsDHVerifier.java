package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.params.DHParameters;

/* loaded from: classes6.dex */
public interface TlsDHVerifier {
    boolean accept(DHParameters dHParameters);
}
