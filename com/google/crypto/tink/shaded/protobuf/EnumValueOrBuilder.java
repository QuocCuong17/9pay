package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

/* loaded from: classes4.dex */
public interface EnumValueOrBuilder extends MessageLiteOrBuilder {
    String getName();

    ByteString getNameBytes();

    int getNumber();

    Option getOptions(int index);

    int getOptionsCount();

    List<Option> getOptionsList();
}
