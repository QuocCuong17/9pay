package com.google.crypto.tink.mac;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class ChunkedMacWrapper implements PrimitiveWrapper<ChunkedMac, ChunkedMac> {
    private static final ChunkedMacWrapper WRAPPER = new ChunkedMacWrapper();

    /* loaded from: classes4.dex */
    private static class WrappedChunkedMacVerification implements ChunkedMacVerification {
        private final List<ChunkedMacVerification> verifications;

        private WrappedChunkedMacVerification(List<ChunkedMacVerification> verificationEntries) {
            this.verifications = verificationEntries;
        }

        @Override // com.google.crypto.tink.mac.ChunkedMacVerification
        public void update(ByteBuffer data) throws GeneralSecurityException {
            ByteBuffer duplicate = data.duplicate();
            duplicate.mark();
            for (ChunkedMacVerification chunkedMacVerification : this.verifications) {
                duplicate.reset();
                chunkedMacVerification.update(duplicate);
            }
            data.position(data.limit());
        }

        @Override // com.google.crypto.tink.mac.ChunkedMacVerification
        public void verifyMac() throws GeneralSecurityException {
            GeneralSecurityException generalSecurityException = new GeneralSecurityException("MAC verification failed for all suitable keys in keyset");
            Iterator<ChunkedMacVerification> it = this.verifications.iterator();
            while (it.hasNext()) {
                try {
                    it.next().verifyMac();
                    return;
                } catch (GeneralSecurityException e) {
                    generalSecurityException.addSuppressed(e);
                }
            }
            throw generalSecurityException;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes4.dex */
    public static class WrappedChunkedMac implements ChunkedMac {
        private final PrimitiveSet<ChunkedMac> primitives;

        private WrappedChunkedMac(PrimitiveSet<ChunkedMac> primitives) {
            this.primitives = primitives;
        }

        @Override // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacComputation createComputation() throws GeneralSecurityException {
            return getChunkedMac(this.primitives.getPrimary()).createComputation();
        }

        private ChunkedMac getChunkedMac(PrimitiveSet.Entry<ChunkedMac> entry) {
            return entry.getFullPrimitive();
        }

        @Override // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacVerification createVerification(final byte[] tag) throws GeneralSecurityException {
            byte[] copyOf = Arrays.copyOf(tag, 5);
            ArrayList arrayList = new ArrayList();
            Iterator<PrimitiveSet.Entry<ChunkedMac>> it = this.primitives.getPrimitive(copyOf).iterator();
            while (it.hasNext()) {
                arrayList.add(getChunkedMac(it.next()).createVerification(tag));
            }
            Iterator<PrimitiveSet.Entry<ChunkedMac>> it2 = this.primitives.getRawPrimitives().iterator();
            while (it2.hasNext()) {
                arrayList.add(getChunkedMac(it2.next()).createVerification(tag));
            }
            return new WrappedChunkedMacVerification(arrayList);
        }
    }

    private ChunkedMacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public ChunkedMac wrap(final PrimitiveSet<ChunkedMac> primitives) throws GeneralSecurityException {
        if (primitives == null) {
            throw new GeneralSecurityException("primitive set must be non-null");
        }
        if (primitives.getPrimary() == null) {
            throw new GeneralSecurityException("no primary in primitive set");
        }
        Iterator<List<PrimitiveSet.Entry<ChunkedMac>>> it = primitives.getAll().iterator();
        while (it.hasNext()) {
            Iterator<PrimitiveSet.Entry<ChunkedMac>> it2 = it.next().iterator();
            while (it2.hasNext()) {
                it2.next().getFullPrimitive();
            }
        }
        return new WrappedChunkedMac(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<ChunkedMac> getPrimitiveClass() {
        return ChunkedMac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<ChunkedMac> getInputPrimitiveClass() {
        return ChunkedMac.class;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
