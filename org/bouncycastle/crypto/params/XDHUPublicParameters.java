package org.bouncycastle.crypto.params;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes6.dex */
public class XDHUPublicParameters implements CipherParameters {
    private AsymmetricKeyParameter ephemeralPublicKey;
    private AsymmetricKeyParameter staticPublicKey;

    public XDHUPublicParameters(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        Objects.requireNonNull(asymmetricKeyParameter, "staticPublicKey cannot be null");
        if (!(asymmetricKeyParameter instanceof X448PublicKeyParameters) && !(asymmetricKeyParameter instanceof X25519PublicKeyParameters)) {
            throw new IllegalArgumentException("only X25519 and X448 paramaters can be used");
        }
        Objects.requireNonNull(asymmetricKeyParameter2, "ephemeralPublicKey cannot be null");
        if (!asymmetricKeyParameter.getClass().isAssignableFrom(asymmetricKeyParameter2.getClass())) {
            throw new IllegalArgumentException("static and ephemeral public keys have different domain parameters");
        }
        this.staticPublicKey = asymmetricKeyParameter;
        this.ephemeralPublicKey = asymmetricKeyParameter2;
    }

    public AsymmetricKeyParameter getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    public AsymmetricKeyParameter getStaticPublicKey() {
        return this.staticPublicKey;
    }
}
