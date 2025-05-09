package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface DocumentChangeOrBuilder extends MessageLiteOrBuilder {
    Document getDocument();

    int getRemovedTargetIds(int i);

    int getRemovedTargetIdsCount();

    List<Integer> getRemovedTargetIdsList();

    int getTargetIds(int i);

    int getTargetIdsCount();

    List<Integer> getTargetIdsList();

    boolean hasDocument();
}
