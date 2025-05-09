package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public interface CursorOrBuilder extends MessageLiteOrBuilder {
    boolean getBefore();

    Value getValues(int i);

    int getValuesCount();

    List<Value> getValuesList();
}
