package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes4.dex */
public interface BloomFilterOrBuilder extends MessageLiteOrBuilder {
    BitSequence getBits();

    int getHashCount();

    boolean hasBits();
}
