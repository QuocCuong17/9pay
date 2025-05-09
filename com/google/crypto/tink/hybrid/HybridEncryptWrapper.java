package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public class HybridEncryptWrapper implements PrimitiveWrapper<HybridEncrypt, HybridEncrypt> {
    private static final HybridEncryptWrapper WRAPPER = new HybridEncryptWrapper();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class WrappedHybridEncrypt implements HybridEncrypt {
        private final MonitoringClient.Logger encLogger;
        final PrimitiveSet<HybridEncrypt> primitives;

        public WrappedHybridEncrypt(final PrimitiveSet<HybridEncrypt> primitives) {
            this.primitives = primitives;
            if (primitives.hasAnnotations()) {
                this.encLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitives), "hybrid_encrypt", "encrypt");
            } else {
                this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
        }

        @Override // com.google.crypto.tink.HybridEncrypt
        public byte[] encrypt(final byte[] plaintext, final byte[] contextInfo) throws GeneralSecurityException {
            if (this.primitives.getPrimary() == null) {
                this.encLogger.logFailure();
                throw new GeneralSecurityException("keyset without primary key");
            }
            try {
                byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().encrypt(plaintext, contextInfo));
                this.encLogger.log(this.primitives.getPrimary().getKeyId(), plaintext.length);
                return concat;
            } catch (GeneralSecurityException e) {
                this.encLogger.logFailure();
                throw e;
            }
        }
    }

    HybridEncryptWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public HybridEncrypt wrap(final PrimitiveSet<HybridEncrypt> primitives) {
        return new WrappedHybridEncrypt(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridEncrypt> getPrimitiveClass() {
        return HybridEncrypt.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridEncrypt> getInputPrimitiveClass() {
        return HybridEncrypt.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
