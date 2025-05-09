package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes4.dex */
public interface ExistenceFilterOrBuilder extends MessageLiteOrBuilder {
    int getCount();

    int getTargetId();

    BloomFilter getUnchangedNames();

    boolean hasUnchangedNames();
}
