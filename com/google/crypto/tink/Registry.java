package com.google.crypto.tink;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class Registry {
    private static final Logger logger = Logger.getLogger(Registry.class.getName());
    private static final AtomicReference<KeyManagerRegistry> keyManagerRegistry = new AtomicReference<>(new KeyManagerRegistry());
    private static final ConcurrentMap<String, KeyDeriverContainer> keyDeriverMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> newKeyAllowedMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Catalogue<?>> catalogueMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, KeyTemplate> keyTemplateMap = new ConcurrentHashMap();

    /* loaded from: classes4.dex */
    private interface KeyDeriverContainer {
        KeyData deriveKey(ByteString serializedKeyFormat, InputStream stream) throws GeneralSecurityException;
    }

    private static <KeyProtoT extends MessageLite> KeyDeriverContainer createDeriverFor(final KeyTypeManager<KeyProtoT> keyManager) {
        return new KeyDeriverContainer() { // from class: com.google.crypto.tink.Registry.1
            /* JADX WARN: Unknown type variable: KeyProtoT in type: com.google.crypto.tink.internal.KeyTypeManager$KeyFactory<KeyFormatProtoT extends com.google.crypto.tink.shaded.protobuf.MessageLite, KeyProtoT> */
            private <KeyFormatProtoT extends MessageLite> MessageLite deriveKeyWithFactory(ByteString serializedKeyFormat, InputStream stream, KeyTypeManager.KeyFactory<KeyFormatProtoT, KeyProtoT> keyFactory) throws GeneralSecurityException {
                try {
                    KeyFormatProtoT parseKeyFormat = keyFactory.parseKeyFormat(serializedKeyFormat);
                    keyFactory.validateKeyFormat(parseKeyFormat);
                    return keyFactory.deriveKey(parseKeyFormat, stream);
                } catch (InvalidProtocolBufferException e) {
                    throw new GeneralSecurityException("parsing key format failed in deriveKey", e);
                }
            }

            @Override // com.google.crypto.tink.Registry.KeyDeriverContainer
            public KeyData deriveKey(ByteString serializedKeyFormat, InputStream stream) throws GeneralSecurityException {
                return (KeyData) KeyData.newBuilder().setTypeUrl(KeyTypeManager.this.getKeyType()).setValue(deriveKeyWithFactory(serializedKeyFormat, stream, KeyTypeManager.this.keyFactory()).toByteString()).setKeyMaterialType(KeyTypeManager.this.keyMaterialType()).build();
            }
        };
    }

    static synchronized void reset() {
        synchronized (Registry.class) {
            keyManagerRegistry.set(new KeyManagerRegistry());
            MutablePrimitiveRegistry.resetGlobalInstanceTestOnly();
            keyDeriverMap.clear();
            newKeyAllowedMap.clear();
            catalogueMap.clear();
            keyTemplateMap.clear();
        }
    }

    @Deprecated
    public static synchronized void addCatalogue(String catalogueName, Catalogue<?> catalogue) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (catalogueName == null) {
                throw new IllegalArgumentException("catalogueName must be non-null.");
            }
            if (catalogue == null) {
                throw new IllegalArgumentException("catalogue must be non-null.");
            }
            ConcurrentMap<String, Catalogue<?>> concurrentMap = catalogueMap;
            if (concurrentMap.containsKey(catalogueName.toLowerCase(Locale.US))) {
                if (!catalogue.getClass().getName().equals(concurrentMap.get(catalogueName.toLowerCase(Locale.US)).getClass().getName())) {
                    logger.warning("Attempted overwrite of a catalogueName catalogue for name " + catalogueName);
                    throw new GeneralSecurityException("catalogue for name " + catalogueName + " has been already registered");
                }
            }
            concurrentMap.put(catalogueName.toLowerCase(Locale.US), catalogue);
        }
    }

    @Deprecated
    public static Catalogue<?> getCatalogue(String catalogueName) throws GeneralSecurityException {
        if (catalogueName == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        Catalogue<?> catalogue = catalogueMap.get(catalogueName.toLowerCase(Locale.US));
        if (catalogue != null) {
            return catalogue;
        }
        String format = String.format("no catalogue found for %s. ", catalogueName);
        if (catalogueName.toLowerCase(Locale.US).startsWith("tinkaead")) {
            format = format + "Maybe call AeadConfig.register().";
        }
        if (catalogueName.toLowerCase(Locale.US).startsWith("tinkdeterministicaead")) {
            format = format + "Maybe call DeterministicAeadConfig.register().";
        } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkstreamingaead")) {
            format = format + "Maybe call StreamingAeadConfig.register().";
        } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkhybriddecrypt") || catalogueName.toLowerCase(Locale.US).startsWith("tinkhybridencrypt")) {
            format = format + "Maybe call HybridConfig.register().";
        } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkmac")) {
            format = format + "Maybe call MacConfig.register().";
        } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeysign") || catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeyverify")) {
            format = format + "Maybe call SignatureConfig.register().";
        } else if (catalogueName.toLowerCase(Locale.US).startsWith("tink")) {
            format = format + "Maybe call TinkConfig.register().";
        }
        throw new GeneralSecurityException(format);
    }

    public static synchronized <P> void registerKeyManager(final KeyManager<P> manager) throws GeneralSecurityException {
        synchronized (Registry.class) {
            registerKeyManager((KeyManager) manager, true);
        }
    }

    public static synchronized <P> void registerKeyManager(final KeyManager<P> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        synchronized (Registry.class) {
            try {
                if (manager == null) {
                    throw new IllegalArgumentException("key manager must be non-null.");
                }
                AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
                KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
                keyManagerRegistry2.registerKeyManager(manager);
                if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
                    throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
                }
                String keyType = manager.getKeyType();
                ensureKeyManagerInsertable(keyType, Collections.emptyMap(), newKeyAllowed);
                newKeyAllowedMap.put(keyType, Boolean.valueOf(newKeyAllowed));
                atomicReference.set(keyManagerRegistry2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static synchronized <KeyProtoT extends MessageLite> void registerKeyManager(final KeyTypeManager<KeyProtoT> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (manager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
            KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
            keyManagerRegistry2.registerKeyManager(manager);
            String keyType = manager.getKeyType();
            ensureKeyManagerInsertable(keyType, newKeyAllowed ? manager.keyFactory().keyFormats() : Collections.emptyMap(), newKeyAllowed);
            if (!atomicReference.get().typeUrlExists(keyType)) {
                keyDeriverMap.put(keyType, createDeriverFor(manager));
                if (newKeyAllowed) {
                    registerKeyTemplates(keyType, manager.keyFactory().keyFormats());
                }
            }
            newKeyAllowedMap.put(keyType, Boolean.valueOf(newKeyAllowed));
            atomicReference.set(keyManagerRegistry2);
        }
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String typeUrl, final KeyManager<P> manager) throws GeneralSecurityException {
        synchronized (Registry.class) {
            registerKeyManager(typeUrl, manager, true);
        }
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String typeUrl, final KeyManager<P> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        synchronized (Registry.class) {
            try {
                if (manager == null) {
                    throw new IllegalArgumentException("key manager must be non-null.");
                }
                if (!typeUrl.equals(manager.getKeyType())) {
                    throw new GeneralSecurityException("Manager does not support key type " + typeUrl + ".");
                }
                registerKeyManager(manager, newKeyAllowed);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0052, code lost:
    
        r5 = r4.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0062, code lost:
    
        if (com.google.crypto.tink.Registry.keyTemplateMap.containsKey(r5.getKey()) == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0089, code lost:
    
        throw new java.security.GeneralSecurityException("Attempted to register a new key template " + r5.getKey() + " from an existing key manager of type " + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008a, code lost:
    
        r3 = r4.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0096, code lost:
    
        if (r3.hasNext() == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0098, code lost:
    
        r4 = r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a8, code lost:
    
        if (com.google.crypto.tink.Registry.keyTemplateMap.containsKey(r4.getKey()) != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c7, code lost:
    
        throw new java.security.GeneralSecurityException("Attempted overwrite of a registered key template " + r4.getKey());
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0042, code lost:
    
        if (com.google.crypto.tink.Registry.keyManagerRegistry.get().typeUrlExists(r3) == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0044, code lost:
    
        r4 = r4.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0050, code lost:
    
        if (r4.hasNext() == false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static synchronized <KeyProtoT extends MessageLite, KeyFormatProtoT extends MessageLite> void ensureKeyManagerInsertable(String typeUrl, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> keyFormats, boolean newKeyAllowed) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (newKeyAllowed) {
                try {
                    ConcurrentMap<String, Boolean> concurrentMap = newKeyAllowedMap;
                    if (concurrentMap.containsKey(typeUrl) && !concurrentMap.get(typeUrl).booleanValue()) {
                        throw new GeneralSecurityException("New keys are already disallowed for key type " + typeUrl);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public static synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> publicKeyTypeManager, boolean newKeyAllowed) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (privateKeyTypeManager == null || publicKeyTypeManager == null) {
                throw new IllegalArgumentException("given key managers must be non-null.");
            }
            AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
            KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
            keyManagerRegistry2.registerAsymmetricKeyManagers(privateKeyTypeManager, publicKeyTypeManager);
            String keyType = privateKeyTypeManager.getKeyType();
            String keyType2 = publicKeyTypeManager.getKeyType();
            ensureKeyManagerInsertable(keyType, newKeyAllowed ? privateKeyTypeManager.keyFactory().keyFormats() : Collections.emptyMap(), newKeyAllowed);
            ensureKeyManagerInsertable(keyType2, Collections.emptyMap(), false);
            if (!atomicReference.get().typeUrlExists(keyType)) {
                keyDeriverMap.put(keyType, createDeriverFor(privateKeyTypeManager));
                if (newKeyAllowed) {
                    registerKeyTemplates(privateKeyTypeManager.getKeyType(), privateKeyTypeManager.keyFactory().keyFormats());
                }
            }
            ConcurrentMap<String, Boolean> concurrentMap = newKeyAllowedMap;
            concurrentMap.put(keyType, Boolean.valueOf(newKeyAllowed));
            concurrentMap.put(keyType2, false);
            atomicReference.set(keyManagerRegistry2);
        }
    }

    private static <KeyFormatProtoT extends MessageLite> void registerKeyTemplates(String typeUrl, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> keyFormats) {
        for (Map.Entry<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> entry : keyFormats.entrySet()) {
            keyTemplateMap.put(entry.getKey(), KeyTemplate.create(typeUrl, entry.getValue().keyFormat.toByteArray(), entry.getValue().outputPrefixType));
        }
    }

    public static synchronized <B, P> void registerPrimitiveWrapper(final PrimitiveWrapper<B, P> wrapper) throws GeneralSecurityException {
        synchronized (Registry.class) {
            MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(wrapper);
        }
    }

    @Deprecated
    public static <P> KeyManager<P> getKeyManager(String typeUrl) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl);
    }

    public static <P> KeyManager<P> getKeyManager(String typeUrl, Class<P> primitiveClass) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl, primitiveClass);
    }

    public static KeyManager<?> getUntypedKeyManager(String typeUrl) throws GeneralSecurityException {
        return keyManagerRegistry.get().getUntypedKeyManager(typeUrl);
    }

    public static synchronized KeyData newKeyData(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        synchronized (Registry.class) {
            KeyManager<?> untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
            if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
                newKeyData = untypedKeyManager.newKeyData(keyTemplate.getValue());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
            }
        }
        return newKeyData;
    }

    public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        synchronized (Registry.class) {
            newKeyData = newKeyData(keyTemplate.getProto());
        }
        return newKeyData;
    }

    public static synchronized MessageLite newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        MessageLite newKey;
        synchronized (Registry.class) {
            KeyManager<?> untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
            if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
                newKey = untypedKeyManager.newKey(keyTemplate.getValue());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
            }
        }
        return newKey;
    }

    public static synchronized MessageLite newKey(String typeUrl, MessageLite format) throws GeneralSecurityException {
        MessageLite newKey;
        synchronized (Registry.class) {
            KeyManager keyManager = getKeyManager(typeUrl);
            if (newKeyAllowedMap.get(typeUrl).booleanValue()) {
                newKey = keyManager.newKey(format);
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + typeUrl);
            }
        }
        return newKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized KeyData deriveKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, InputStream randomStream) throws GeneralSecurityException {
        KeyData deriveKey;
        synchronized (Registry.class) {
            String typeUrl = keyTemplate.getTypeUrl();
            ConcurrentMap<String, KeyDeriverContainer> concurrentMap = keyDeriverMap;
            if (!concurrentMap.containsKey(typeUrl)) {
                throw new GeneralSecurityException("No keymanager registered or key manager cannot derive keys for " + typeUrl);
            }
            deriveKey = concurrentMap.get(typeUrl).deriveKey(keyTemplate.getValue(), randomStream);
        }
        return deriveKey;
    }

    public static KeyData getPublicKeyData(String typeUrl, ByteString serializedPrivateKey) throws GeneralSecurityException {
        KeyManager keyManager = getKeyManager(typeUrl);
        if (!(keyManager instanceof PrivateKeyManager)) {
            throw new GeneralSecurityException("manager for key type " + typeUrl + " is not a PrivateKeyManager");
        }
        return ((PrivateKeyManager) keyManager).getPublicKeyData(serializedPrivateKey);
    }

    @Deprecated
    public static <P> P getPrimitive(String typeUrl, MessageLite key) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl).getPrimitive(key);
    }

    public static <P> P getPrimitive(String typeUrl, MessageLite key, Class<P> primitiveClass) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl, primitiveClass).getPrimitive(key);
    }

    @Deprecated
    public static <P> P getPrimitive(String typeUrl, ByteString serializedKey) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl).getPrimitive(serializedKey);
    }

    public static <P> P getPrimitive(String typeUrl, ByteString serializedKey, Class<P> primitiveClass) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl, primitiveClass).getPrimitive(serializedKey);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, byte[] bArr) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr));
    }

    public static <P> P getPrimitive(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr), cls);
    }

    @Deprecated
    public static <P> P getPrimitive(KeyData keyData) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue());
    }

    public static <P> P getPrimitive(KeyData keyData, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue(), cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <KeyT extends Key, P> P getFullPrimitive(KeyT keyt, Class<P> cls) throws GeneralSecurityException {
        return (P) MutablePrimitiveRegistry.globalInstance().getPrimitive(keyt, cls);
    }

    public static <B, P> P wrap(PrimitiveSet<B> primitiveSet, Class<P> cls) throws GeneralSecurityException {
        return (P) MutablePrimitiveRegistry.globalInstance().wrap(primitiveSet, cls);
    }

    public static <P> P wrap(PrimitiveSet<P> primitiveSet) throws GeneralSecurityException {
        return (P) wrap(primitiveSet, primitiveSet.getPrimitiveClass());
    }

    public static synchronized List<String> keyTemplates() {
        List<String> unmodifiableList;
        synchronized (Registry.class) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(keyTemplateMap.keySet());
            unmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return unmodifiableList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized Map<String, KeyTemplate> keyTemplateMap() {
        Map<String, KeyTemplate> unmodifiableMap;
        synchronized (Registry.class) {
            unmodifiableMap = Collections.unmodifiableMap(keyTemplateMap);
        }
        return unmodifiableMap;
    }

    @Nullable
    public static Class<?> getInputPrimitive(Class<?> wrappedPrimitive) {
        try {
            return MutablePrimitiveRegistry.globalInstance().getInputPrimitiveClass(wrappedPrimitive);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageLite parseKeyData(KeyData keyData) throws GeneralSecurityException, InvalidProtocolBufferException {
        return keyManagerRegistry.get().parseKeyData(keyData);
    }

    public static synchronized void restrictToFipsIfEmpty() throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (TinkFipsUtil.useOnlyFips()) {
                return;
            }
            if (keyManagerRegistry.get().isEmpty()) {
                TinkFipsUtil.setFipsRestricted();
                return;
            }
            throw new GeneralSecurityException("Could not enable FIPS mode as Registry is not empty.");
        }
    }

    private Registry() {
    }
}
