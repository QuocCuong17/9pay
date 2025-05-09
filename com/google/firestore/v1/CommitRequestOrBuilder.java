package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface CommitRequestOrBuilder extends MessageLiteOrBuilder {
    String getDatabase();

    ByteString getDatabaseBytes();

    ByteString getTransaction();

    Write getWrites(int i);

    int getWritesCount();

    List<Write> getWritesList();
}
