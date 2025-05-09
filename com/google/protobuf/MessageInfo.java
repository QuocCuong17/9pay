package com.google.protobuf;

@CheckReturnValue
/* loaded from: classes4.dex */
interface MessageInfo {
    MessageLite getDefaultInstance();

    ProtoSyntax getSyntax();

    boolean isMessageSetWireFormat();
}
