package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: classes4.dex */
public interface JwtRsaSsaPssPrivateKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getCrt();

    ByteString getD();

    ByteString getDp();

    ByteString getDq();

    ByteString getP();

    JwtRsaSsaPssPublicKey getPublicKey();

    ByteString getQ();

    int getVersion();

    boolean hasPublicKey();
}
