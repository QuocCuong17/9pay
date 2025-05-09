package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class PublicKeyVerifyWrapper implements PrimitiveWrapper<PublicKeyVerify, PublicKeyVerify> {
    private static final Logger logger = Logger.getLogger(PublicKeyVerifyWrapper.class.getName());
    private static final byte[] FORMAT_VERSION = {0};
    private static final PublicKeyVerifyWrapper WRAPPER = new PublicKeyVerifyWrapper();

    PublicKeyVerifyWrapper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class WrappedPublicKeyVerify implements PublicKeyVerify {
        private final MonitoringClient.Logger monitoringLogger;
        private final PrimitiveSet<PublicKeyVerify> primitives;

        public WrappedPublicKeyVerify(PrimitiveSet<PublicKeyVerify> primitives) {
            this.primitives = primitives;
            if (primitives.hasAnnotations()) {
                this.monitoringLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitives), "public_key_verify", "verify");
            } else {
                this.monitoringLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
        }

        @Override // com.google.crypto.tink.PublicKeyVerify
        public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
            if (signature.length <= 5) {
                this.monitoringLogger.logFailure();
                throw new GeneralSecurityException("signature too short");
            }
            byte[] copyOf = Arrays.copyOf(signature, 5);
            byte[] copyOfRange = Arrays.copyOfRange(signature, 5, signature.length);
            for (PrimitiveSet.Entry<PublicKeyVerify> entry : this.primitives.getPrimitive(copyOf)) {
                try {
                    entry.getPrimitive().verify(copyOfRange, entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY) ? Bytes.concat(data, PublicKeyVerifyWrapper.FORMAT_VERSION) : data);
                    this.monitoringLogger.log(entry.getKeyId(), r3.length);
                    return;
                } catch (GeneralSecurityException e) {
                    PublicKeyVerifyWrapper.logger.info("signature prefix matches a key, but cannot verify: " + e);
                }
            }
            for (PrimitiveSet.Entry<PublicKeyVerify> entry2 : this.primitives.getRawPrimitives()) {
                try {
                    entry2.getPrimitive().verify(signature, data);
                    this.monitoringLogger.log(entry2.getKeyId(), data.length);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.monitoringLogger.logFailure();
            throw new GeneralSecurityException("invalid signature");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PublicKeyVerify wrap(final PrimitiveSet<PublicKeyVerify> primitives) {
        return new WrappedPublicKeyVerify(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getInputPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
