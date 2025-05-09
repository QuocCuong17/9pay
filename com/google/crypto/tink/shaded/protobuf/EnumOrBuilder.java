package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

/* loaded from: classes4.dex */
public interface EnumOrBuilder extends MessageLiteOrBuilder {
    EnumValue getEnumvalue(int index);

    int getEnumvalueCount();

    List<EnumValue> getEnumvalueList();

    String getName();

    ByteString getNameBytes();

    Option getOptions(int index);

    int getOptionsCount();

    List<Option> getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    boolean hasSourceContext();
}
