package com.google.firestore.v1;

import com.google.firestore.v1.Value;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class Cursor extends GeneratedMessageLite<Cursor, Builder> implements CursorOrBuilder {
    public static final int BEFORE_FIELD_NUMBER = 2;
    private static final Cursor DEFAULT_INSTANCE;
    private static volatile Parser<Cursor> PARSER = null;
    public static final int VALUES_FIELD_NUMBER = 1;
    private boolean before_;
    private Internal.ProtobufList<Value> values_ = emptyProtobufList();

    private Cursor() {
    }

    @Override // com.google.firestore.v1.CursorOrBuilder
    public List<Value> getValuesList() {
        return this.values_;
    }

    public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    @Override // com.google.firestore.v1.CursorOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.google.firestore.v1.CursorOrBuilder
    public Value getValues(int i) {
        return this.values_.get(i);
    }

    public ValueOrBuilder getValuesOrBuilder(int i) {
        return this.values_.get(i);
    }

    private void ensureValuesIsMutable() {
        Internal.ProtobufList<Value> protobufList = this.values_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.values_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValues(int i, Value value) {
        value.getClass();
        ensureValuesIsMutable();
        this.values_.set(i, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addValues(Value value) {
        value.getClass();
        ensureValuesIsMutable();
        this.values_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addValues(int i, Value value) {
        value.getClass();
        ensureValuesIsMutable();
        this.values_.add(i, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllValues(Iterable<? extends Value> iterable) {
        ensureValuesIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.values_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValues() {
        this.values_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeValues(int i) {
        ensureValuesIsMutable();
        this.values_.remove(i);
    }

    @Override // com.google.firestore.v1.CursorOrBuilder
    public boolean getBefore() {
        return this.before_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBefore(boolean z) {
        this.before_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBefore() {
        this.before_ = false;
    }

    public static Cursor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Cursor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Cursor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Cursor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Cursor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Cursor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Cursor parseFrom(InputStream inputStream) throws IOException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Cursor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Cursor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Cursor) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Cursor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Cursor) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Cursor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Cursor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Cursor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Cursor cursor) {
        return DEFAULT_INSTANCE.createBuilder(cursor);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<Cursor, Builder> implements CursorOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Cursor.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public List<Value> getValuesList() {
            return Collections.unmodifiableList(((Cursor) this.instance).getValuesList());
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public int getValuesCount() {
            return ((Cursor) this.instance).getValuesCount();
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public Value getValues(int i) {
            return ((Cursor) this.instance).getValues(i);
        }

        public Builder setValues(int i, Value value) {
            copyOnWrite();
            ((Cursor) this.instance).setValues(i, value);
            return this;
        }

        public Builder setValues(int i, Value.Builder builder) {
            copyOnWrite();
            ((Cursor) this.instance).setValues(i, builder.build());
            return this;
        }

        public Builder addValues(Value value) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(value);
            return this;
        }

        public Builder addValues(int i, Value value) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(i, value);
            return this;
        }

        public Builder addValues(Value.Builder builder) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(builder.build());
            return this;
        }

        public Builder addValues(int i, Value.Builder builder) {
            copyOnWrite();
            ((Cursor) this.instance).addValues(i, builder.build());
            return this;
        }

        public Builder addAllValues(Iterable<? extends Value> iterable) {
            copyOnWrite();
            ((Cursor) this.instance).addAllValues(iterable);
            return this;
        }

        public Builder clearValues() {
            copyOnWrite();
            ((Cursor) this.instance).clearValues();
            return this;
        }

        public Builder removeValues(int i) {
            copyOnWrite();
            ((Cursor) this.instance).removeValues(i);
            return this;
        }

        @Override // com.google.firestore.v1.CursorOrBuilder
        public boolean getBefore() {
            return ((Cursor) this.instance).getBefore();
        }

        public Builder setBefore(boolean z) {
            copyOnWrite();
            ((Cursor) this.instance).setBefore(z);
            return this;
        }

        public Builder clearBefore() {
            copyOnWrite();
            ((Cursor) this.instance).clearBefore();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Cursor$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Cursor();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u0007", new Object[]{"values_", Value.class, "before_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Cursor> parser = PARSER;
                if (parser == null) {
                    synchronized (Cursor.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        Cursor cursor = new Cursor();
        DEFAULT_INSTANCE = cursor;
        GeneratedMessageLite.registerDefaultInstance(Cursor.class, cursor);
    }

    public static Cursor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Cursor> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
