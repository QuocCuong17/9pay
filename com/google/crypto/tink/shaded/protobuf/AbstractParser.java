package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    private UninitializedMessageException newUninitializedMessageException(MessageType message) {
        if (message instanceof AbstractMessageLite) {
            return ((AbstractMessageLite) message).newUninitializedMessageException();
        }
        return new UninitializedMessageException(message);
    }

    private MessageType checkMessageInitialized(MessageType message) throws InvalidProtocolBufferException {
        if (message == null || message.isInitialized()) {
            return message;
        }
        throw newUninitializedMessageException(message).asInvalidProtocolBufferException().setUnfinishedMessage(message);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(CodedInputStream input) throws InvalidProtocolBufferException {
        return (MessageType) parsePartialFrom(input, EMPTY_REGISTRY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MessageType) checkMessageInitialized((MessageLite) parsePartialFrom(codedInputStream, extensionRegistryLite));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(CodedInputStream input) throws InvalidProtocolBufferException {
        return parseFrom(input, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newCodedInput = data.newCodedInput();
            MessageType messagetype = (MessageType) parsePartialFrom(newCodedInput, extensionRegistry);
            try {
                newCodedInput.checkLastTagWas(0);
                return messagetype;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(messagetype);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(ByteString data) throws InvalidProtocolBufferException {
        return parsePartialFrom(data, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(data, extensionRegistry));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return parseFrom(data, EMPTY_REGISTRY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newInstance = CodedInputStream.newInstance(byteBuffer);
            MessageLite messageLite = (MessageLite) parsePartialFrom(newInstance, extensionRegistryLite);
            try {
                newInstance.checkLastTagWas(0);
                return (MessageType) checkMessageInitialized(messageLite);
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(messageLite);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return parseFrom(data, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newInstance = CodedInputStream.newInstance(data, off, len);
            MessageType messagetype = (MessageType) parsePartialFrom(newInstance, extensionRegistry);
            try {
                newInstance.checkLastTagWas(0);
                return messagetype;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(messagetype);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
        return parsePartialFrom(data, off, len, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return parsePartialFrom(data, 0, data.length, extensionRegistry);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(byte[] data) throws InvalidProtocolBufferException {
        return parsePartialFrom(data, 0, data.length, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(data, off, len, extensionRegistry));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
        return parseFrom(data, off, len, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return parseFrom(data, 0, data.length, extensionRegistry);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return parseFrom(data, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CodedInputStream newInstance = CodedInputStream.newInstance(input);
        MessageType messagetype = (MessageType) parsePartialFrom(newInstance, extensionRegistry);
        try {
            newInstance.checkLastTagWas(0);
            return messagetype;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(messagetype);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialFrom(InputStream input) throws InvalidProtocolBufferException {
        return parsePartialFrom(input, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialFrom(input, extensionRegistry));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseFrom(InputStream input) throws InvalidProtocolBufferException {
        return parseFrom(input, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        try {
            int read = input.read();
            if (read == -1) {
                return null;
            }
            return parsePartialFrom((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(input, CodedInputStream.readRawVarint32(read, input)), extensionRegistry);
        } catch (IOException e) {
            throw new InvalidProtocolBufferException(e);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parsePartialDelimitedFrom(InputStream input) throws InvalidProtocolBufferException {
        return parsePartialDelimitedFrom(input, EMPTY_REGISTRY);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return checkMessageInitialized(parsePartialDelimitedFrom(input, extensionRegistry));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Parser
    public MessageType parseDelimitedFrom(InputStream input) throws InvalidProtocolBufferException {
        return parseDelimitedFrom(input, EMPTY_REGISTRY);
    }
}
