package com.google.crypto.tink;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.LegacyProtoParameters;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.TinkBugException;
import com.google.crypto.tink.monitoring.MonitoringAnnotations;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.internal.InternalKeyHandle;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class KeysetHandle {
    private final MonitoringAnnotations annotations;
    private final List<Entry> entries;
    private final Keyset keyset;

    /* loaded from: classes4.dex */
    public static final class Builder {
        private final List<Entry> entries = new ArrayList();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class KeyIdStrategy {
            private static final KeyIdStrategy RANDOM_ID = new KeyIdStrategy();
            private final int fixedId;

            static /* synthetic */ KeyIdStrategy access$200() {
                return randomId();
            }

            private KeyIdStrategy() {
                this.fixedId = 0;
            }

            private KeyIdStrategy(int id2) {
                this.fixedId = id2;
            }

            private static KeyIdStrategy randomId() {
                return RANDOM_ID;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static KeyIdStrategy fixedId(int id2) {
                return new KeyIdStrategy(id2);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int getFixedId() {
                return this.fixedId;
            }
        }

        /* loaded from: classes4.dex */
        public static final class Entry {

            @Nullable
            private Builder builder;
            private boolean isPrimary;

            @Nullable
            private final Key key;
            private KeyStatus keyStatus;

            @Nullable
            private final Parameters parameters;
            private KeyIdStrategy strategy;

            /* synthetic */ Entry(Key key, AnonymousClass1 anonymousClass1) {
                this(key);
            }

            /* synthetic */ Entry(Parameters parameters, AnonymousClass1 anonymousClass1) {
                this(parameters);
            }

            private Entry(Key key) {
                this.keyStatus = KeyStatus.ENABLED;
                this.strategy = null;
                this.builder = null;
                this.key = key;
                this.parameters = null;
            }

            private Entry(Parameters parameters) {
                this.keyStatus = KeyStatus.ENABLED;
                this.strategy = null;
                this.builder = null;
                this.key = null;
                this.parameters = parameters;
            }

            public Entry makePrimary() {
                Builder builder = this.builder;
                if (builder != null) {
                    builder.clearPrimary();
                }
                this.isPrimary = true;
                return this;
            }

            public boolean isPrimary() {
                return this.isPrimary;
            }

            public Entry setStatus(KeyStatus status) {
                this.keyStatus = status;
                return this;
            }

            public KeyStatus getStatus() {
                return this.keyStatus;
            }

            public Entry withFixedId(int id2) {
                this.strategy = KeyIdStrategy.fixedId(id2);
                return this;
            }

            public Entry withRandomId() {
                this.strategy = KeyIdStrategy.access$200();
                return this;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPrimary() {
            Iterator<Entry> it = this.entries.iterator();
            while (it.hasNext()) {
                it.next().isPrimary = false;
            }
        }

        public Builder addEntry(Entry entry) {
            if (entry.builder == null) {
                if (entry.isPrimary) {
                    clearPrimary();
                }
                entry.builder = this;
                this.entries.add(entry);
                return this;
            }
            throw new IllegalStateException("Entry has already been added to a KeysetHandle.Builder");
        }

        public int size() {
            return this.entries.size();
        }

        public Entry getAt(int i) {
            return this.entries.get(i);
        }

        @Deprecated
        public Entry removeAt(int i) {
            return this.entries.remove(i);
        }

        public Builder deleteAt(int i) {
            this.entries.remove(i);
            return this;
        }

        private static void checkIdAssignments(List<Entry> entries) throws GeneralSecurityException {
            for (int i = 0; i < entries.size() - 1; i++) {
                if (entries.get(i).strategy == KeyIdStrategy.RANDOM_ID && entries.get(i + 1).strategy != KeyIdStrategy.RANDOM_ID) {
                    throw new GeneralSecurityException("Entries with 'withRandomId()' may only be followed by other entries with 'withRandomId()'.");
                }
            }
        }

        private static int randomIdNotInSet(Set<Integer> ids) {
            int i = 0;
            while (true) {
                if (i != 0 && !ids.contains(Integer.valueOf(i))) {
                    return i;
                }
                i = com.google.crypto.tink.internal.Util.randKeyId();
            }
        }

        private static Keyset.Key createKeyFromParameters(Parameters parameters, int id2, KeyStatusType keyStatusType) throws GeneralSecurityException {
            ProtoParametersSerialization protoParametersSerialization;
            if (parameters instanceof LegacyProtoParameters) {
                protoParametersSerialization = ((LegacyProtoParameters) parameters).getSerialization();
            } else {
                protoParametersSerialization = (ProtoParametersSerialization) MutableSerializationRegistry.globalInstance().serializeParameters(parameters, ProtoParametersSerialization.class);
            }
            return (Keyset.Key) Keyset.Key.newBuilder().setKeyId(id2).setStatus(keyStatusType).setKeyData(Registry.newKeyData(protoParametersSerialization.getKeyTemplate())).setOutputPrefixType(protoParametersSerialization.getKeyTemplate().getOutputPrefixType()).build();
        }

        private static int getNextIdFromBuilderEntry(Entry builderEntry, Set<Integer> idsSoFar) throws GeneralSecurityException {
            if (builderEntry.strategy != null) {
                if (builderEntry.strategy != KeyIdStrategy.RANDOM_ID) {
                    return builderEntry.strategy.getFixedId();
                }
                return randomIdNotInSet(idsSoFar);
            }
            throw new GeneralSecurityException("No ID was set (with withFixedId or withRandomId)");
        }

        private static Keyset.Key createKeysetKeyFromBuilderEntry(Entry builderEntry, int id2) throws GeneralSecurityException {
            ProtoKeySerialization protoKeySerialization;
            if (builderEntry.key != null) {
                if (builderEntry.key instanceof LegacyProtoKey) {
                    protoKeySerialization = ((LegacyProtoKey) builderEntry.key).getSerialization(InsecureSecretKeyAccess.get());
                } else {
                    protoKeySerialization = (ProtoKeySerialization) MutableSerializationRegistry.globalInstance().serializeKey(builderEntry.key, ProtoKeySerialization.class, InsecureSecretKeyAccess.get());
                }
                Integer idRequirementOrNull = protoKeySerialization.getIdRequirementOrNull();
                if (idRequirementOrNull == null || idRequirementOrNull.intValue() == id2) {
                    return KeysetHandle.toKeysetKey(id2, KeysetHandle.serializeStatus(builderEntry.getStatus()), protoKeySerialization);
                }
                throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
            }
            return createKeyFromParameters(builderEntry.parameters, id2, KeysetHandle.serializeStatus(builderEntry.getStatus()));
        }

        public KeysetHandle build() throws GeneralSecurityException {
            Keyset.Builder newBuilder = Keyset.newBuilder();
            checkIdAssignments(this.entries);
            HashSet hashSet = new HashSet();
            Integer num = null;
            for (Entry entry : this.entries) {
                if (entry.keyStatus == null) {
                    throw new GeneralSecurityException("Key Status not set.");
                }
                int nextIdFromBuilderEntry = getNextIdFromBuilderEntry(entry, hashSet);
                if (hashSet.contains(Integer.valueOf(nextIdFromBuilderEntry))) {
                    throw new GeneralSecurityException("Id " + nextIdFromBuilderEntry + " is used twice in the keyset");
                }
                hashSet.add(Integer.valueOf(nextIdFromBuilderEntry));
                newBuilder.addKey(createKeysetKeyFromBuilderEntry(entry, nextIdFromBuilderEntry));
                if (entry.isPrimary) {
                    if (num != null) {
                        throw new GeneralSecurityException("Two primaries were set");
                    }
                    num = Integer.valueOf(nextIdFromBuilderEntry);
                }
            }
            if (num == null) {
                throw new GeneralSecurityException("No primary was set");
            }
            newBuilder.setPrimaryKeyId(num.intValue());
            return KeysetHandle.fromKeyset((Keyset) newBuilder.build());
        }
    }

    @Immutable
    /* loaded from: classes4.dex */
    public static final class Entry {

        /* renamed from: id, reason: collision with root package name */
        private final int f75id;
        private final boolean isPrimary;
        private final Key key;
        private final KeyStatus keyStatus;

        /* synthetic */ Entry(Key key, KeyStatus keyStatus, int i, boolean z, AnonymousClass1 anonymousClass1) {
            this(key, keyStatus, i, z);
        }

        private Entry(Key key, KeyStatus keyStatus, int id2, boolean isPrimary) {
            this.key = key;
            this.keyStatus = keyStatus;
            this.f75id = id2;
            this.isPrimary = isPrimary;
        }

        public Key getKey() {
            return this.key;
        }

        public KeyStatus getStatus() {
            return this.keyStatus;
        }

        public int getId() {
            return this.f75id;
        }

        public boolean isPrimary() {
            return this.isPrimary;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.KeysetHandle$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;

        static {
            int[] iArr = new int[KeyStatusType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$KeyStatusType = iArr;
            try {
                iArr[KeyStatusType.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DESTROYED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static KeyStatus parseStatus(KeyStatusType in) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[in.ordinal()];
        if (i == 1) {
            return KeyStatus.ENABLED;
        }
        if (i == 2) {
            return KeyStatus.DISABLED;
        }
        if (i == 3) {
            return KeyStatus.DESTROYED;
        }
        throw new GeneralSecurityException("Unknown key status");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyStatusType serializeStatus(KeyStatus in) {
        if (KeyStatus.ENABLED.equals(in)) {
            return KeyStatusType.ENABLED;
        }
        if (KeyStatus.DISABLED.equals(in)) {
            return KeyStatusType.DISABLED;
        }
        if (KeyStatus.DESTROYED.equals(in)) {
            return KeyStatusType.DESTROYED;
        }
        throw new IllegalStateException("Unknown key status");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Keyset.Key toKeysetKey(int id2, KeyStatusType status, ProtoKeySerialization protoKeySerialization) {
        return (Keyset.Key) Keyset.Key.newBuilder().setKeyData(KeyData.newBuilder().setTypeUrl(protoKeySerialization.getTypeUrl()).setValue(protoKeySerialization.getValue()).setKeyMaterialType(protoKeySerialization.getKeyMaterialType())).setStatus(status).setKeyId(id2).setOutputPrefixType(protoKeySerialization.getOutputPrefixType()).build();
    }

    private static List<Entry> getEntriesFromKeyset(Keyset keyset) {
        ArrayList arrayList = new ArrayList(keyset.getKeyCount());
        for (Keyset.Key key : keyset.getKeyList()) {
            int keyId = key.getKeyId();
            try {
                arrayList.add(new Entry(MutableSerializationRegistry.globalInstance().parseKeyWithLegacyFallback(toProtoKeySerialization(key), InsecureSecretKeyAccess.get()), parseStatus(key.getStatus()), keyId, keyId == keyset.getPrimaryKeyId(), null));
            } catch (GeneralSecurityException unused) {
                arrayList.add(null);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static ProtoKeySerialization toProtoKeySerialization(Keyset.Key protoKey) {
        try {
            return ProtoKeySerialization.create(protoKey.getKeyData().getTypeUrl(), protoKey.getKeyData().getValue(), protoKey.getKeyData().getKeyMaterialType(), protoKey.getOutputPrefixType(), protoKey.getOutputPrefixType() == OutputPrefixType.RAW ? null : Integer.valueOf(protoKey.getKeyId()));
        } catch (GeneralSecurityException e) {
            throw new TinkBugException("Creating a protokey serialization failed", e);
        }
    }

    private Entry entryByIndex(int i) {
        if (this.entries.get(i) == null) {
            throw new IllegalStateException("Keyset-Entry at position i has wrong status or key parsing failed");
        }
        return this.entries.get(i);
    }

    public static Builder.Entry importKey(Key key) {
        Builder.Entry entry = new Builder.Entry(key, (AnonymousClass1) null);
        Integer idRequirementOrNull = key.getIdRequirementOrNull();
        if (idRequirementOrNull != null) {
            entry.withFixedId(idRequirementOrNull.intValue());
        }
        return entry;
    }

    public static Builder.Entry generateEntryFromParametersName(String namedParameters) throws GeneralSecurityException {
        if (!Registry.keyTemplateMap().containsKey(namedParameters)) {
            throw new GeneralSecurityException("cannot find key template: " + namedParameters);
        }
        return new Builder.Entry(MutableSerializationRegistry.globalInstance().parseParametersWithLegacyFallback(ProtoParametersSerialization.create(Registry.keyTemplateMap().get(namedParameters).getProto())), (AnonymousClass1) null);
    }

    public static Builder.Entry generateEntryFromParameters(Parameters parameters) {
        return new Builder.Entry(parameters, (AnonymousClass1) null);
    }

    private KeysetHandle(Keyset keyset, List<Entry> entries) {
        this.keyset = keyset;
        this.entries = entries;
        this.annotations = MonitoringAnnotations.EMPTY;
    }

    private KeysetHandle(Keyset keyset, List<Entry> entries, MonitoringAnnotations annotations) {
        this.keyset = keyset;
        this.entries = entries;
        this.annotations = annotations;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final KeysetHandle fromKeyset(Keyset keyset) throws GeneralSecurityException {
        assertEnoughKeyMaterial(keyset);
        return new KeysetHandle(keyset, getEntriesFromKeyset(keyset));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final KeysetHandle fromKeysetAndAnnotations(Keyset keyset, MonitoringAnnotations annotations) throws GeneralSecurityException {
        assertEnoughKeyMaterial(keyset);
        return new KeysetHandle(keyset, getEntriesFromKeyset(keyset), annotations);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Keyset getKeyset() {
        return this.keyset;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(KeysetHandle handle) {
        Builder builder = new Builder();
        for (int i = 0; i < handle.size(); i++) {
            Entry entryByIndex = handle.entryByIndex(i);
            Builder.Entry withFixedId = importKey(entryByIndex.getKey()).withFixedId(entryByIndex.getId());
            withFixedId.setStatus(entryByIndex.getStatus());
            if (entryByIndex.isPrimary()) {
                withFixedId.makePrimary();
            }
            builder.addEntry(withFixedId);
        }
        return builder;
    }

    public Entry getPrimary() {
        for (int i = 0; i < this.keyset.getKeyCount(); i++) {
            if (this.keyset.getKey(i).getKeyId() == this.keyset.getPrimaryKeyId()) {
                Entry entryByIndex = entryByIndex(i);
                if (entryByIndex.getStatus() == KeyStatus.ENABLED) {
                    return entryByIndex;
                }
                throw new IllegalStateException("Keyset has primary which isn't enabled");
            }
        }
        throw new IllegalStateException("Keyset has no primary");
    }

    public int size() {
        return this.keyset.getKeyCount();
    }

    public Entry getAt(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Invalid index " + i + " for keyset of size " + size());
        }
        return entryByIndex(i);
    }

    @Deprecated
    public List<KeyHandle> getKeys() {
        ArrayList arrayList = new ArrayList();
        for (Keyset.Key key : this.keyset.getKeyList()) {
            arrayList.add(new InternalKeyHandle(new ProtoKey(key.getKeyData(), KeyTemplate.fromProto(key.getOutputPrefixType())), key.getStatus(), key.getKeyId()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public KeysetInfo getKeysetInfo() {
        return Util.getKeysetInfo(this.keyset);
    }

    @Deprecated
    public static final KeysetHandle generateNew(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        return newBuilder().addEntry(generateEntryFromParameters(new LegacyProtoParameters(ProtoParametersSerialization.create(keyTemplate))).makePrimary().withRandomId()).build();
    }

    public static final KeysetHandle generateNew(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return newBuilder().addEntry(generateEntryFromParameters(new LegacyProtoParameters(ProtoParametersSerialization.create(keyTemplate.getProto()))).makePrimary().withRandomId()).build();
    }

    @Deprecated
    public static final KeysetHandle createFromKey(KeyHandle keyHandle, KeyAccess access) throws GeneralSecurityException {
        KeysetManager add = KeysetManager.withEmptyKeyset().add(keyHandle);
        add.setPrimary(add.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
        return add.getKeysetHandle();
    }

    public static final KeysetHandle read(KeysetReader reader, Aead masterKey) throws GeneralSecurityException, IOException {
        return readWithAssociatedData(reader, masterKey, new byte[0]);
    }

    public static final KeysetHandle readWithAssociatedData(KeysetReader reader, Aead masterKey, byte[] associatedData) throws GeneralSecurityException, IOException {
        EncryptedKeyset readEncrypted = reader.readEncrypted();
        assertEnoughEncryptedKeyMaterial(readEncrypted);
        return fromKeyset(decrypt(readEncrypted, masterKey, associatedData));
    }

    public static final KeysetHandle readNoSecret(KeysetReader reader) throws GeneralSecurityException, IOException {
        try {
            Keyset read = reader.read();
            assertNoSecretKeyMaterial(read);
            return fromKeyset(read);
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    @Deprecated
    public static final KeysetHandle readNoSecret(final byte[] serialized) throws GeneralSecurityException {
        try {
            Keyset parseFrom = Keyset.parseFrom(serialized, ExtensionRegistryLite.getEmptyRegistry());
            assertNoSecretKeyMaterial(parseFrom);
            return fromKeyset(parseFrom);
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public void write(KeysetWriter keysetWriter, Aead masterKey) throws GeneralSecurityException, IOException {
        writeWithAssociatedData(keysetWriter, masterKey, new byte[0]);
    }

    public void writeWithAssociatedData(KeysetWriter keysetWriter, Aead masterKey, byte[] associatedData) throws GeneralSecurityException, IOException {
        keysetWriter.write(encrypt(this.keyset, masterKey, associatedData));
    }

    public void writeNoSecret(KeysetWriter writer) throws GeneralSecurityException, IOException {
        assertNoSecretKeyMaterial(this.keyset);
        writer.write(this.keyset);
    }

    private static EncryptedKeyset encrypt(Keyset keyset, Aead masterKey, byte[] associatedData) throws GeneralSecurityException {
        byte[] encrypt = masterKey.encrypt(keyset.toByteArray(), associatedData);
        try {
            if (!Keyset.parseFrom(masterKey.decrypt(encrypt, associatedData), ExtensionRegistryLite.getEmptyRegistry()).equals(keyset)) {
                throw new GeneralSecurityException("cannot encrypt keyset");
            }
            return (EncryptedKeyset) EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(encrypt)).setKeysetInfo(Util.getKeysetInfo(keyset)).build();
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static Keyset decrypt(EncryptedKeyset encryptedKeyset, Aead masterKey, byte[] associatedData) throws GeneralSecurityException {
        try {
            Keyset parseFrom = Keyset.parseFrom(masterKey.decrypt(encryptedKeyset.getEncryptedKeyset().toByteArray(), associatedData), ExtensionRegistryLite.getEmptyRegistry());
            assertEnoughKeyMaterial(parseFrom);
            return parseFrom;
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public KeysetHandle getPublicKeysetHandle() throws GeneralSecurityException {
        if (this.keyset == null) {
            throw new GeneralSecurityException("cleartext keyset is not available");
        }
        Keyset.Builder newBuilder = Keyset.newBuilder();
        for (Keyset.Key key : this.keyset.getKeyList()) {
            newBuilder.addKey((Keyset.Key) ((Keyset.Key.Builder) key.toBuilder()).setKeyData(createPublicKeyData(key.getKeyData())).build());
        }
        newBuilder.setPrimaryKeyId(this.keyset.getPrimaryKeyId());
        return fromKeyset((Keyset) newBuilder.build());
    }

    private static KeyData createPublicKeyData(KeyData privateKeyData) throws GeneralSecurityException {
        if (privateKeyData.getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
            throw new GeneralSecurityException("The keyset contains a non-private key");
        }
        KeyData publicKeyData = Registry.getPublicKeyData(privateKeyData.getTypeUrl(), privateKeyData.getValue());
        validate(publicKeyData);
        return publicKeyData;
    }

    private static void validate(KeyData keyData) throws GeneralSecurityException {
        Registry.getPrimitive(keyData);
    }

    public String toString() {
        return getKeysetInfo().toString();
    }

    private static void assertNoSecretKeyMaterial(Keyset keyset) throws GeneralSecurityException {
        for (Keyset.Key key : keyset.getKeyList()) {
            if (key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", key.getKeyData().getKeyMaterialType().name(), key.getKeyData().getTypeUrl()));
            }
        }
    }

    private static void assertEnoughKeyMaterial(Keyset keyset) throws GeneralSecurityException {
        if (keyset == null || keyset.getKeyCount() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private static void assertEnoughEncryptedKeyMaterial(EncryptedKeyset keyset) throws GeneralSecurityException {
        if (keyset == null || keyset.getEncryptedKeyset().size() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <B, P> P getPrimitiveWithKnownInputPrimitive(Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        Util.validateKeyset(this.keyset);
        PrimitiveSet.Builder newBuilder = PrimitiveSet.newBuilder(cls2);
        newBuilder.setAnnotations(this.annotations);
        for (int i = 0; i < size(); i++) {
            Keyset.Key key = this.keyset.getKey(i);
            if (key.getStatus().equals(KeyStatusType.ENABLED)) {
                Object legacyPrimitiveOrNull = getLegacyPrimitiveOrNull(key, cls2);
                Object fullPrimitiveOrNull = this.entries.get(i) != null ? getFullPrimitiveOrNull(this.entries.get(i).getKey(), cls2) : null;
                if (key.getKeyId() == this.keyset.getPrimaryKeyId()) {
                    newBuilder.addPrimaryFullPrimitiveAndOptionalPrimitive(fullPrimitiveOrNull, legacyPrimitiveOrNull, key);
                } else {
                    newBuilder.addFullPrimitiveAndOptionalPrimitive(fullPrimitiveOrNull, legacyPrimitiveOrNull, key);
                }
            }
        }
        return (P) Registry.wrap(newBuilder.build(), cls);
    }

    public <P> P getPrimitive(Class<P> cls) throws GeneralSecurityException {
        Class<?> inputPrimitive = Registry.getInputPrimitive(cls);
        if (inputPrimitive == null) {
            throw new GeneralSecurityException("No wrapper found for " + cls.getName());
        }
        return (P) getPrimitiveWithKnownInputPrimitive(cls, inputPrimitive);
    }

    @Deprecated
    public KeyHandle primaryKey() throws GeneralSecurityException {
        int primaryKeyId = this.keyset.getPrimaryKeyId();
        for (Keyset.Key key : this.keyset.getKeyList()) {
            if (key.getKeyId() == primaryKeyId) {
                return new InternalKeyHandle(new ProtoKey(key.getKeyData(), KeyTemplate.fromProto(key.getOutputPrefixType())), key.getStatus(), key.getKeyId());
            }
        }
        throw new GeneralSecurityException("No primary key found in keyset.");
    }

    @Nullable
    private static <B> B getLegacyPrimitiveOrNull(Keyset.Key key, Class<B> cls) throws GeneralSecurityException {
        try {
            return (B) Registry.getPrimitive(key.getKeyData(), cls);
        } catch (GeneralSecurityException e) {
            if (e.getMessage().contains("No key manager found for key type ") || e.getMessage().contains(" not supported by key manager of type ")) {
                return null;
            }
            throw e;
        }
    }

    @Nullable
    private <B> B getFullPrimitiveOrNull(Key key, Class<B> cls) throws GeneralSecurityException {
        try {
            return (B) Registry.getFullPrimitive(key, cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }
}
