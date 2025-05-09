package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class MacWrapper implements PrimitiveWrapper<Mac, Mac> {
    private static final Logger logger = Logger.getLogger(MacWrapper.class.getName());
    private static final byte[] FORMAT_VERSION = {0};
    private static final MacWrapper WRAPPER = new MacWrapper();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class WrappedMac implements Mac {
        private final MonitoringClient.Logger computeLogger;
        private final PrimitiveSet<Mac> primitives;
        private final MonitoringClient.Logger verifyLogger;

        private WrappedMac(PrimitiveSet<Mac> primitives) {
            this.primitives = primitives;
            if (primitives.hasAnnotations()) {
                MonitoringClient monitoringClient = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitives);
                this.computeLogger = monitoringClient.createLogger(monitoringKeysetInfo, "mac", "compute");
                this.verifyLogger = monitoringClient.createLogger(monitoringKeysetInfo, "mac", "verify");
                return;
            }
            this.computeLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.verifyLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.Mac
        public byte[] computeMac(final byte[] data) throws GeneralSecurityException {
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                data = Bytes.concat(data, MacWrapper.FORMAT_VERSION);
            }
            try {
                byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().computeMac(data));
                this.computeLogger.log(this.primitives.getPrimary().getKeyId(), data.length);
                return concat;
            } catch (GeneralSecurityException e) {
                this.computeLogger.logFailure();
                throw e;
            }
        }

        @Override // com.google.crypto.tink.Mac
        public void verifyMac(final byte[] mac, final byte[] data) throws GeneralSecurityException {
            if (mac.length <= 5) {
                this.verifyLogger.logFailure();
                throw new GeneralSecurityException("tag too short");
            }
            byte[] copyOf = Arrays.copyOf(mac, 5);
            byte[] copyOfRange = Arrays.copyOfRange(mac, 5, mac.length);
            for (PrimitiveSet.Entry<Mac> entry : this.primitives.getPrimitive(copyOf)) {
                try {
                    entry.getPrimitive().verifyMac(copyOfRange, entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY) ? Bytes.concat(data, MacWrapper.FORMAT_VERSION) : data);
                    this.verifyLogger.log(entry.getKeyId(), r3.length);
                    return;
                } catch (GeneralSecurityException e) {
                    MacWrapper.logger.info("tag prefix matches a key, but cannot verify: " + e);
                }
            }
            for (PrimitiveSet.Entry<Mac> entry2 : this.primitives.getRawPrimitives()) {
                try {
                    entry2.getPrimitive().verifyMac(mac, data);
                    this.verifyLogger.log(entry2.getKeyId(), data.length);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.verifyLogger.logFailure();
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    private void validateMacKeyPrefixes(PrimitiveSet<Mac> primitives) throws GeneralSecurityException {
        Iterator<List<PrimitiveSet.Entry<Mac>>> it = primitives.getAll().iterator();
        while (it.hasNext()) {
            for (PrimitiveSet.Entry<Mac> entry : it.next()) {
                if (entry.getKey() instanceof MacKey) {
                    MacKey macKey = (MacKey) entry.getKey();
                    com.google.crypto.tink.util.Bytes copyFrom = com.google.crypto.tink.util.Bytes.copyFrom(entry.getIdentifier());
                    if (!copyFrom.equals(macKey.getOutputPrefix())) {
                        throw new GeneralSecurityException("Mac Key with parameters " + macKey.getParameters() + " has wrong output prefix (" + macKey.getOutputPrefix() + ") instead of (" + copyFrom + ")");
                    }
                }
            }
        }
    }

    MacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Mac wrap(final PrimitiveSet<Mac> primitives) throws GeneralSecurityException {
        validateMacKeyPrefixes(primitives);
        return new WrappedMac(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getPrimitiveClass() {
        return Mac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getInputPrimitiveClass() {
        return Mac.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
