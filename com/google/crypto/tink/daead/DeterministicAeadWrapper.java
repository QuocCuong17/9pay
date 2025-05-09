package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;

/* loaded from: classes4.dex */
public class DeterministicAeadWrapper implements PrimitiveWrapper<DeterministicAead, DeterministicAead> {
    private static final Logger logger = Logger.getLogger(DeterministicAeadWrapper.class.getName());
    private static final DeterministicAeadWrapper WRAPPER = new DeterministicAeadWrapper();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class WrappedDeterministicAead implements DeterministicAead {
        private final MonitoringClient.Logger decLogger;
        private final MonitoringClient.Logger encLogger;
        private final PrimitiveSet<DeterministicAead> primitives;

        public WrappedDeterministicAead(PrimitiveSet<DeterministicAead> primitives) {
            this.primitives = primitives;
            if (primitives.hasAnnotations()) {
                MonitoringClient monitoringClient = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
                this.encLogger = monitoringClient.createLogger(monitoringKeysetInfo, "daead", "encrypt");
                this.decLogger = monitoringClient.createLogger(monitoringKeysetInfo, "daead", "decrypt");
                return;
            }
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] encryptDeterministically(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
            try {
                byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().encryptDeterministically(plaintext, associatedData));
                this.encLogger.log(this.primitives.getPrimary().getKeyId(), plaintext.length);
                return concat;
            } catch (GeneralSecurityException e) {
                this.encLogger.logFailure();
                throw e;
            }
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] decryptDeterministically(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
            if (ciphertext.length > 5) {
                byte[] copyOf = Arrays.copyOf(ciphertext, 5);
                byte[] copyOfRange = Arrays.copyOfRange(ciphertext, 5, ciphertext.length);
                for (PrimitiveSet.Entry<DeterministicAead> entry : this.primitives.getPrimitive(copyOf)) {
                    try {
                        byte[] decryptDeterministically = entry.getPrimitive().decryptDeterministically(copyOfRange, associatedData);
                        this.decLogger.log(entry.getKeyId(), copyOfRange.length);
                        return decryptDeterministically;
                    } catch (GeneralSecurityException e) {
                        DeterministicAeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e);
                    }
                }
            }
            for (PrimitiveSet.Entry<DeterministicAead> entry2 : this.primitives.getRawPrimitives()) {
                try {
                    byte[] decryptDeterministically2 = entry2.getPrimitive().decryptDeterministically(ciphertext, associatedData);
                    this.decLogger.log(entry2.getKeyId(), ciphertext.length);
                    return decryptDeterministically2;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }
    }

    DeterministicAeadWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public DeterministicAead wrap(final PrimitiveSet<DeterministicAead> primitives) {
        return new WrappedDeterministicAead(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<DeterministicAead> getPrimitiveClass() {
        return DeterministicAead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<DeterministicAead> getInputPrimitiveClass() {
        return DeterministicAead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
