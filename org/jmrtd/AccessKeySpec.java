package org.jmrtd;

import java.io.Serializable;
import java.security.spec.KeySpec;

/* loaded from: classes6.dex */
public interface AccessKeySpec extends Serializable, KeySpec {
    String getAlgorithm();

    byte[] getKey();
}
