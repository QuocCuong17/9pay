package org.jmrtd.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.tlv.TLVUtil;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jmrtd.APDULevelEACCACapable;
import org.jmrtd.Util;

/* loaded from: classes6.dex */
public class EACCAAPDUSender implements APDULevelEACCACapable {
    private static final byte INS_BSI_GENERAL_AUTHENTICATE = -122;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    private SecureMessagingAPDUSender secureMessagingSender;

    public EACCAAPDUSender(CardService cardService) {
        this.secureMessagingSender = new SecureMessagingAPDUSender(cardService);
    }

    @Override // org.jmrtd.APDULevelEACCACapable
    public synchronized void sendMSEKAT(APDUWrapper aPDUWrapper, byte[] bArr, byte[] bArr2) throws CardServiceException {
        byte[] bArr3 = new byte[bArr.length + (bArr2 != null ? bArr2.length : 0)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        if (bArr2 != null) {
            System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        }
        CommandAPDU commandAPDU = new CommandAPDU(0, 34, 65, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, bArr3);
        commandAPDU.getBytes();
        short sw = (short) this.secureMessagingSender.transmit(aPDUWrapper, commandAPDU).getSW();
        if (sw != -28672) {
            throw new CardServiceException("Sending MSE KAT failed", sw);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0071 A[Catch: all -> 0x0079, TRY_ENTER, TryCatch #0 {, blocks: (B:20:0x0007, B:23:0x0010, B:25:0x0023, B:26:0x0037, B:12:0x0071, B:13:0x0078, B:17:0x0066, B:29:0x002e, B:4:0x004b), top: B:19:0x0007, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0066 A[Catch: all -> 0x0079, TRY_LEAVE, TryCatch #0 {, blocks: (B:20:0x0007, B:23:0x0010, B:25:0x0023, B:26:0x0037, B:12:0x0071, B:13:0x0078, B:17:0x0066, B:29:0x002e, B:4:0x004b), top: B:19:0x0007, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006f A[DONT_GENERATE] */
    @Override // org.jmrtd.APDULevelEACCACapable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void sendMSESetATIntAuth(APDUWrapper aPDUWrapper, String str, BigInteger bigInteger) throws CardServiceException {
        ResponseAPDU transmit;
        short sw;
        if (bigInteger != null) {
            if (bigInteger.compareTo(BigInteger.ZERO) >= 0) {
                byte[] oIDBytes = Util.toOIDBytes(str);
                byte[] wrapDO = TLVUtil.wrapDO(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, Util.i2os(bigInteger));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byteArrayOutputStream.write(oIDBytes);
                    byteArrayOutputStream.write(wrapDO);
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
                }
                transmit = this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, 34, 65, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, byteArrayOutputStream.toByteArray()));
                sw = transmit != null ? (short) -1 : (short) transmit.getSW();
                if (sw == -28672) {
                    throw new CardServiceException("Sending MSE AT failed", sw);
                }
            }
        }
        transmit = this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, 34, 65, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, Util.toOIDBytes(str)));
        if (transmit != null) {
        }
        if (sw == -28672) {
        }
    }

    @Override // org.jmrtd.APDULevelEACCACapable
    public synchronized byte[] sendGeneralAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr, boolean z) throws CardServiceException {
        return sendGeneralAuthenticate(aPDUWrapper, bArr, 256, z);
    }

    public synchronized byte[] sendGeneralAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr, int i, boolean z) throws CardServiceException {
        byte[] data;
        byte[] wrapDO = TLVUtil.wrapDO(124, bArr);
        ResponseAPDU transmit = this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(z ? 0 : 16, -122, 0, 0, wrapDO, i));
        short sw = (short) transmit.getSW();
        if (sw == 26368) {
            transmit = this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(z ? 0 : 16, -122, 0, 0, wrapDO, 256));
        }
        if (sw != -28672) {
            throw new CardServiceException("Sending general authenticate failed", sw);
        }
        data = transmit.getData();
        try {
            data = TLVUtil.unwrapDO(124, data);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Could not unwrap response to GENERAL AUTHENTICATE", (Throwable) e);
        }
        return data;
    }
}
