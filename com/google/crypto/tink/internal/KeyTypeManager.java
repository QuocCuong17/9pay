package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class KeyTypeManager<KeyProtoT extends MessageLite> {
    private final Class<KeyProtoT> clazz;
    private final Map<Class<?>, PrimitiveFactory<?, KeyProtoT>> factories;
    private final Class<?> firstPrimitiveClass;

    public abstract String getKeyType();

    public abstract int getVersion();

    public abstract KeyData.KeyMaterialType keyMaterialType();

    public abstract KeyProtoT parseKey(ByteString byteString) throws InvalidProtocolBufferException;

    public abstract void validateKey(KeyProtoT keyProto) throws GeneralSecurityException;

    /* JADX INFO: Access modifiers changed from: protected */
    @SafeVarargs
    public KeyTypeManager(Class<KeyProtoT> clazz, PrimitiveFactory<?, KeyProtoT>... factories) {
        this.clazz = clazz;
        HashMap hashMap = new HashMap();
        for (PrimitiveFactory<?, KeyProtoT> primitiveFactory : factories) {
            if (hashMap.containsKey(primitiveFactory.getPrimitiveClass())) {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive " + primitiveFactory.getPrimitiveClass().getCanonicalName());
            }
            hashMap.put(primitiveFactory.getPrimitiveClass(), primitiveFactory);
        }
        if (factories.length > 0) {
            this.firstPrimitiveClass = factories[0].getPrimitiveClass();
        } else {
            this.firstPrimitiveClass = Void.class;
        }
        this.factories = Collections.unmodifiableMap(hashMap);
    }

    public final Class<KeyProtoT> getKeyClass() {
        return this.clazz;
    }

    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public final <P> P getPrimitive(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        PrimitiveFactory<?, KeyProtoT> primitiveFactory = this.factories.get(cls);
        if (primitiveFactory == null) {
            throw new IllegalArgumentException("Requested primitive class " + cls.getCanonicalName() + " not supported.");
        }
        return (P) primitiveFactory.getPrimitive(keyprotot);
    }

    public final Set<Class<?>> supportedPrimitives() {
        return this.factories.keySet();
    }

    public final Class<?> firstSupportedPrimitiveClass() {
        return this.firstPrimitiveClass;
    }

    /* loaded from: classes4.dex */
    public static abstract class KeyFactory<KeyFormatProtoT extends MessageLite, KeyProtoT extends MessageLite> {
        private final Class<KeyFormatProtoT> clazz;

        public abstract KeyProtoT createKey(KeyFormatProtoT keyFormat) throws GeneralSecurityException;

        public abstract KeyFormatProtoT parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException;

        public abstract void validateKeyFormat(KeyFormatProtoT keyFormatProto) throws GeneralSecurityException;

        public KeyFactory(Class<KeyFormatProtoT> clazz) {
            this.clazz = clazz;
        }

        /* loaded from: classes4.dex */
        public static final class KeyFormat<KeyFormatProtoT> {
            public KeyFormatProtoT keyFormat;
            public KeyTemplate.OutputPrefixType outputPrefixType;

            public KeyFormat(KeyFormatProtoT keyFormat, KeyTemplate.OutputPrefixType outputPrefixType) {
                this.keyFormat = keyFormat;
                this.outputPrefixType = outputPrefixType;
            }
        }

        public final Class<KeyFormatProtoT> getKeyFormatClass() {
            return this.clazz;
        }

        public KeyProtoT deriveKey(KeyFormatProtoT keyFormat, InputStream pseudoRandomness) throws GeneralSecurityException {
            throw new GeneralSecurityException("deriveKey not implemented for key of type " + this.clazz);
        }

        public Map<String, KeyFormat<KeyFormatProtoT>> keyFormats() throws GeneralSecurityException {
            return Collections.emptyMap();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static void readFully(InputStream input, byte[] output) throws IOException, GeneralSecurityException {
            int length = output.length;
            int i = 0;
            while (i < length) {
                int read = input.read(output, i, length - i);
                if (read == -1) {
                    throw new GeneralSecurityException("Not enough pseudorandomness provided");
                }
                i += read;
            }
        }
    }

    public KeyFactory<?, KeyProtoT> keyFactory() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }
}
