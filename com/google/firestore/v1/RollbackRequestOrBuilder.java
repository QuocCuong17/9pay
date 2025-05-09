package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes4.dex */
public interface RollbackRequestOrBuilder extends MessageLiteOrBuilder {
    String getDatabase();

    ByteString getDatabaseBytes();

    ByteString getTransaction();
}
