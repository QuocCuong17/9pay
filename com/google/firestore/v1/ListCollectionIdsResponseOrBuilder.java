package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface ListCollectionIdsResponseOrBuilder extends MessageLiteOrBuilder {
    String getCollectionIds(int i);

    ByteString getCollectionIdsBytes(int i);

    int getCollectionIdsCount();

    List<String> getCollectionIdsList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
