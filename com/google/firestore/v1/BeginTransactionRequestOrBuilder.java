package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes4.dex */
public interface BeginTransactionRequestOrBuilder extends MessageLiteOrBuilder {
    String getDatabase();

    ByteString getDatabaseBytes();

    TransactionOptions getOptions();

    boolean hasOptions();
}
