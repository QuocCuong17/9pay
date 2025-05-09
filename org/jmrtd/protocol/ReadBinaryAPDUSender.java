package org.jmrtd.protocol;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.util.Hex;
import org.jmrtd.APDULevelReadBinaryCapable;

/* loaded from: classes6.dex */
public class ReadBinaryAPDUSender implements APDULevelReadBinaryCapable {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    private SecureMessagingAPDUSender secureMessagingSender;
    private CardService service;

    public ReadBinaryAPDUSender(CardService cardService) {
        this.service = cardService;
        this.secureMessagingSender = new SecureMessagingAPDUSender(cardService);
    }

    @Override // org.jmrtd.APDULevelReadBinaryCapable
    public synchronized void sendSelectApplet(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException {
        if (bArr == null) {
            throw new IllegalArgumentException("AID cannot be null");
        }
        CommandAPDU commandAPDU = new CommandAPDU(0, -92, 4, 12, bArr);
        checkStatusWordAfterFileOperation(commandAPDU, this.secureMessagingSender.transmit(aPDUWrapper, commandAPDU));
    }

    @Override // org.jmrtd.APDULevelReadBinaryCapable
    public synchronized void sendSelectFile(APDUWrapper aPDUWrapper, short s) throws CardServiceException {
        CommandAPDU commandAPDU = new CommandAPDU(0, -92, 2, 12, new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)}, 0);
        ResponseAPDU transmit = this.secureMessagingSender.transmit(aPDUWrapper, commandAPDU);
        if (transmit == null) {
            return;
        }
        checkStatusWordAfterFileOperation(commandAPDU, transmit);
    }

    @Override // org.jmrtd.APDULevelReadBinaryCapable
    public synchronized byte[] sendReadBinary(APDUWrapper aPDUWrapper, int i, int i2, int i3, boolean z, boolean z2) throws CardServiceException {
        CommandAPDU commandAPDU;
        CommandAPDU commandAPDU2;
        int sw;
        int i4 = i3;
        synchronized (this) {
            ResponseAPDU responseAPDU = null;
            if (i4 == 0) {
                return null;
            }
            byte b = (byte) ((65280 & i2) >> 8);
            byte b2 = (byte) (i2 & 255);
            if (z2) {
                int i5 = i4 < 128 ? i4 + 2 : i4 < 256 ? i4 + 3 : i4;
                if (i5 > 256) {
                    i5 = 256;
                }
                commandAPDU2 = new CommandAPDU(0, -79, 0, 0, new byte[]{84, 2, b, b2}, i5);
                i4 = i5;
            } else {
                if (z) {
                    commandAPDU = new CommandAPDU(0, -80, (byte) i, b2, i3);
                } else {
                    commandAPDU = new CommandAPDU(0, -80, b, b2, i3);
                }
                commandAPDU2 = commandAPDU;
            }
            try {
                responseAPDU = this.secureMessagingSender.transmit(aPDUWrapper, commandAPDU2);
                sw = responseAPDU.getSW();
            } catch (CardServiceException e) {
                if (this.service.isConnectionLost(e)) {
                    throw e;
                }
                LOGGER.log(Level.FINE, "Exception during READ BINARY", (Throwable) e);
                sw = e.getSW();
            }
            short s = (short) sw;
            byte[] responseData = getResponseData(responseAPDU, z2);
            if (responseData != null && responseData.length != 0) {
                checkStatusWordAfterFileOperation(commandAPDU2, responseAPDU);
                return responseData;
            }
            LOGGER.warning("Empty response data: response APDU bytes = " + Arrays.toString(responseData) + ", le = " + i4 + ", sw = " + Integer.toHexString(s));
            return responseData;
        }
    }

    private static byte[] getResponseData(ResponseAPDU responseAPDU, boolean z) throws CardServiceException {
        if (responseAPDU == null) {
            return null;
        }
        byte[] data = responseAPDU.getData();
        if (data == null) {
            throw new CardServiceException("Malformed read binary long response data");
        }
        if (!z) {
            return data;
        }
        if (data[0] != 83) {
            throw new CardServiceException("Malformed read binary long response data");
        }
        int i = (((byte) (data[1] & 128)) == Byte.MIN_VALUE ? (data[1] & 15) + 1 : 1) + 1;
        int length = data.length - i;
        byte[] bArr = new byte[length];
        System.arraycopy(data, i, bArr, 0, length);
        return bArr;
    }

    private static void checkStatusWordAfterFileOperation(CommandAPDU commandAPDU, ResponseAPDU responseAPDU) throws CardServiceException {
        short sw = (short) responseAPDU.getSW();
        String str = "CAPDU = " + Hex.bytesToHexString(commandAPDU.getBytes()) + ", RAPDU = " + Hex.bytesToHexString(responseAPDU.getBytes());
        if (sw != -28672) {
            if (sw != 27010) {
                if (sw == 27266) {
                    throw new CardServiceException("File not found, " + str, sw);
                }
                if (sw != 27013 && sw != 27014) {
                    throw new CardServiceException("Error occured, " + str, sw);
                }
            }
            throw new CardServiceException("Access to file denied, " + str, sw);
        }
    }
}
