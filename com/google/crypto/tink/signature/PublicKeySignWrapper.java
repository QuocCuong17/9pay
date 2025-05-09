package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public class PublicKeySignWrapper implements PrimitiveWrapper<PublicKeySign, PublicKeySign> {
    private static final byte[] FORMAT_VERSION = {0};
    private static final PublicKeySignWrapper WRAPPER = new PublicKeySignWrapper();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class WrappedPublicKeySign implements PublicKeySign {
        private final MonitoringClient.Logger logger;
        private final PrimitiveSet<PublicKeySign> primitives;

        public WrappedPublicKeySign(final PrimitiveSet<PublicKeySign> primitives) {
            this.primitives = primitives;
            if (primitives.hasAnnotations()) {
                this.logger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitives), "public_key_sign", "sign");
            } else {
                this.logger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
        }

        @Override // com.google.crypto.tink.PublicKeySign
        public byte[] sign(final byte[] data) throws GeneralSecurityException {
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                data = Bytes.concat(data, PublicKeySignWrapper.FORMAT_VERSION);
            }
            try {
                byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().sign(data));
                this.logger.log(this.primitives.getPrimary().getKeyId(), data.length);
                return concat;
            } catch (GeneralSecurityException e) {
                this.logger.logFailure();
                throw e;
            }
        }
    }

    PublicKeySignWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PublicKeySign wrap(final PrimitiveSet<PublicKeySign> primitives) {
        return new WrappedPublicKeySign(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeySign> getPrimitiveClass() {
        return PublicKeySign.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeySign> getInputPrimitiveClass() {
        return PublicKeySign.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }
}
