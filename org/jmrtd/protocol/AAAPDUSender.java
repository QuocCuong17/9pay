package org.jmrtd.protocol;

import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UShort;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.util.Hex;
import org.jmrtd.APDULevelAACapable;

/* loaded from: classes6.dex */
public class AAAPDUSender implements APDULevelAACapable {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    private SecureMessagingAPDUSender secureMessagingSender;

    public AAAPDUSender(CardService cardService) {
        this.secureMessagingSender = new SecureMessagingAPDUSender(cardService);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0091, code lost:
    
        if (r1 != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0094, code lost:
    
        return r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a3  */
    @Override // org.jmrtd.APDULevelAACapable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized byte[] sendInternalAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        ResponseAPDU responseAPDU;
        int sw;
        short s;
        if (bArr != null) {
            if (bArr.length == 8) {
                CommandAPDU commandAPDU = new CommandAPDU(0, -120, 0, 0, bArr, 256);
                byte[] bArr2 = null;
                try {
                    responseAPDU = this.secureMessagingSender.transmit(aPDUWrapper, commandAPDU);
                    try {
                        sw = responseAPDU.getSW();
                    } catch (CardServiceException e) {
                        e = e;
                        LOGGER.log(Level.INFO, "Exception during transmission of command APDU = " + Hex.bytesToHexString(commandAPDU.getBytes()), (Throwable) e);
                        sw = e.getSW();
                        s = (short) sw;
                        if (s != -28672) {
                        }
                        if ((65280 & s) != 24832) {
                        }
                    }
                } catch (CardServiceException e2) {
                    e = e2;
                    responseAPDU = null;
                }
                s = (short) sw;
                if (s != -28672 && responseAPDU != null) {
                    return responseAPDU.getData();
                }
                if ((65280 & s) != 24832) {
                    byte[] data = responseAPDU == null ? null : responseAPDU.getData();
                    ResponseAPDU transmit = this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, -120, 0, 0, bArr, 65536));
                    if (transmit != null) {
                        bArr2 = transmit.getData();
                    }
                    if (data == null && bArr2 == null) {
                        throw new CardServiceException("Internal Authenticate failed", s);
                    }
                    return (data != null || bArr2 == null) ? data.length > bArr2.length ? data : bArr2 : bArr2;
                }
                if (responseAPDU != null && responseAPDU.getData() != null) {
                    LOGGER.warning("Internal Authenticate may not have succeeded, got status word " + Integer.toHexString(s & UShort.MAX_VALUE));
                    return responseAPDU.getData();
                }
                throw new CardServiceException("Internal Authenticate failed", s);
            }
        }
        throw new IllegalArgumentException("rndIFD wrong length");
    }
}
