package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

/* loaded from: classes4.dex */
public interface TypeOrBuilder extends MessageLiteOrBuilder {
    Field getFields(int index);

    int getFieldsCount();

    List<Field> getFieldsList();

    String getName();

    ByteString getNameBytes();

    String getOneofs(int index);

    ByteString getOneofsBytes(int index);

    int getOneofsCount();

    List<String> getOneofsList();

    Option getOptions(int index);

    int getOptionsCount();

    List<Option> getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    boolean hasSourceContext();
}
