package org.bouncycastle.crypto.params;

import java.util.Objects;

/* loaded from: classes6.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    private final ECDomainParameters parameters;

    /* JADX INFO: Access modifiers changed from: protected */
    public ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        Objects.requireNonNull(eCDomainParameters, "'parameters' cannot be null");
        this.parameters = eCDomainParameters;
    }

    public ECDomainParameters getParameters() {
        return this.parameters;
    }
}
