package com.google.firebase.database.tubesock;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
class MessageBuilderFactory {

    /* loaded from: classes4.dex */
    interface Builder {
        boolean appendBytes(byte[] bArr);

        WebSocketMessage toMessage();
    }

    MessageBuilderFactory() {
    }

    /* loaded from: classes4.dex */
    static class BinaryBuilder implements Builder {
        private int pendingByteCount = 0;
        private List<byte[]> pendingBytes = new ArrayList();

        BinaryBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bArr) {
            this.pendingBytes.add(bArr);
            this.pendingByteCount += bArr.length;
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            byte[] bArr = new byte[this.pendingByteCount];
            int i = 0;
            for (int i2 = 0; i2 < this.pendingBytes.size(); i2++) {
                byte[] bArr2 = this.pendingBytes.get(i2);
                System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
                i += bArr2.length;
            }
            return new WebSocketMessage(bArr);
        }
    }

    /* loaded from: classes4.dex */
    static class TextBuilder implements Builder {
        private static ThreadLocal<CharsetDecoder> localDecoder = new ThreadLocal<CharsetDecoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetDecoder initialValue() {
                CharsetDecoder newDecoder = Charset.forName("UTF8").newDecoder();
                newDecoder.onMalformedInput(CodingErrorAction.REPORT);
                newDecoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return newDecoder;
            }
        };
        private static ThreadLocal<CharsetEncoder> localEncoder = new ThreadLocal<CharsetEncoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetEncoder initialValue() {
                CharsetEncoder newEncoder = Charset.forName("UTF8").newEncoder();
                newEncoder.onMalformedInput(CodingErrorAction.REPORT);
                newEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return newEncoder;
            }
        };
        private StringBuilder builder = new StringBuilder();
        private ByteBuffer carryOver;

        TextBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bArr) {
            String decodeString = decodeString(bArr);
            if (decodeString == null) {
                return false;
            }
            this.builder.append(decodeString);
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            if (this.carryOver != null) {
                return null;
            }
            return new WebSocketMessage(this.builder.toString());
        }

        private String decodeString(byte[] bArr) {
            try {
                return localDecoder.get().decode(ByteBuffer.wrap(bArr)).toString();
            } catch (CharacterCodingException unused) {
                return null;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
        
            if (r6.remaining() <= 0) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x003c, code lost:
        
            r5.carryOver = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
        
            com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localEncoder.get().encode(java.nio.CharBuffer.wrap(r2));
            r2.flip();
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0054, code lost:
        
            return r2.toString();
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private String decodeStringStreaming(byte[] bArr) {
            try {
                ByteBuffer buffer = getBuffer(bArr);
                int remaining = (int) (buffer.remaining() * localDecoder.get().averageCharsPerByte());
                CharBuffer allocate = CharBuffer.allocate(remaining);
                while (true) {
                    CoderResult decode = localDecoder.get().decode(buffer, allocate, false);
                    if (decode.isError()) {
                        return null;
                    }
                    if (decode.isUnderflow()) {
                        break;
                    }
                    if (decode.isOverflow()) {
                        remaining = (remaining * 2) + 1;
                        CharBuffer allocate2 = CharBuffer.allocate(remaining);
                        allocate.flip();
                        allocate2.put(allocate);
                        allocate = allocate2;
                    }
                }
            } catch (CharacterCodingException unused) {
                return null;
            }
        }

        private ByteBuffer getBuffer(byte[] bArr) {
            ByteBuffer byteBuffer = this.carryOver;
            if (byteBuffer != null) {
                ByteBuffer allocate = ByteBuffer.allocate(bArr.length + byteBuffer.remaining());
                allocate.put(this.carryOver);
                this.carryOver = null;
                allocate.put(bArr);
                allocate.flip();
                return allocate;
            }
            return ByteBuffer.wrap(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder(byte b) {
        if (b == 2) {
            return new BinaryBuilder();
        }
        return new TextBuilder();
    }
}
