package org.jmrtd.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.tlv.TLVUtil;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jmrtd.APDULevelPACECapable;
import org.jmrtd.AccessDeniedException;
import org.jmrtd.Util;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes6.dex */
public class PACEAPDUSender implements APDULevelPACECapable {
    public static final byte CAN_PACE_KEY_REFERENCE = 2;
    private static final byte INS_PACE_GENERAL_AUTHENTICATE = -122;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    public static final byte MRZ_PACE_KEY_REFERENCE = 1;
    public static final byte NO_PACE_KEY_REFERENCE = 0;
    public static final byte PIN_PACE_KEY_REFERENCE = 3;
    public static final byte PUK_PACE_KEY_REFERENCE = 4;
    private SecureMessagingAPDUSender secureMessagingSender;

    public PACEAPDUSender(CardService cardService) {
        this.secureMessagingSender = new SecureMessagingAPDUSender(cardService);
    }

    @Override // org.jmrtd.APDULevelPACECapable
    public synchronized void sendMSESetATMutualAuth(APDUWrapper aPDUWrapper, String str, int i, byte[] bArr) throws CardServiceException {
        try {
            if (str == null) {
                throw new IllegalArgumentException("OID cannot be null");
            }
            byte[] oIDBytes = Util.toOIDBytes(str);
            if (i != 1 && i != 2 && i != 3 && i != 4) {
                throw new IllegalArgumentException("Unsupported key type reference (MRZ, CAN, etc), found " + i);
            }
            byte[] wrapDO = TLVUtil.wrapDO(ISO781611.CREATION_DATE_AND_TIME_TAG, new byte[]{(byte) i});
            if (bArr != null) {
                bArr = TLVUtil.wrapDO(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, bArr);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(oIDBytes);
                byteArrayOutputStream.write(wrapDO);
                if (bArr != null) {
                    byteArrayOutputStream.write(bArr);
                }
                short sw = (short) this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, 34, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, byteArrayOutputStream.toByteArray())).getSW();
                if (sw != -28672) {
                    throw new CardServiceException("Sending MSE AT failed", sw);
                }
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Error while copying data", (Throwable) e);
                throw new IllegalStateException("Error while copying data");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // org.jmrtd.APDULevelPACECapable
    public synchronized byte[] sendGeneralAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr, int i, boolean z) throws CardServiceException {
        ResponseAPDU transmit;
        transmit = this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(z ? 0 : 16, -122, 0, 0, TLVUtil.wrapDO(124, bArr), i));
        short sw = (short) transmit.getSW();
        if (sw != -28672) {
            throw new AccessDeniedException("Sending general authenticate failed", sw);
        }
        return TLVUtil.unwrapDO(124, transmit.getData());
    }
}
