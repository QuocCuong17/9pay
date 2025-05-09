package org.jmrtd.protocol;

import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jmrtd.APDULevelEACTACapable;

/* loaded from: classes6.dex */
public class EACTAAPDUSender implements APDULevelEACTACapable {
    private SecureMessagingAPDUSender secureMessagingSender;

    public EACTAAPDUSender(CardService cardService) {
        this.secureMessagingSender = new SecureMessagingAPDUSender(cardService);
    }

    @Override // org.jmrtd.APDULevelEACTACapable
    public synchronized void sendMSESetDST(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        short sw = (short) this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, 34, 129, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, bArr)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending MSE Set DST failed", sw);
        }
    }

    @Override // org.jmrtd.APDULevelEACTACapable
    public synchronized void sendPSOExtendedLengthMode(APDUWrapper aPDUWrapper, byte[] bArr, byte[] bArr2) throws CardServiceException {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        short sw = (short) this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, 42, 0, 190, bArr3)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending PSO failed", sw);
        }
    }

    @Override // org.jmrtd.APDULevelEACTACapable
    public synchronized void sendMSESetATExtAuth(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        short sw = (short) this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, 34, 129, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, bArr)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending MSE AT failed", sw);
        }
    }

    @Override // org.jmrtd.APDULevelEACTACapable
    public synchronized byte[] sendGetChallenge(APDUWrapper aPDUWrapper) throws CardServiceException {
        return this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, -124, 0, 0, 8)).getData();
    }

    @Override // org.jmrtd.APDULevelEACTACapable
    public synchronized void sendMutualAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        short sw = (short) this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, -126, 0, 0, bArr)).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending External Authenticate failed.", sw);
        }
    }
}
