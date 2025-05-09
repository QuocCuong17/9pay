package org.jmrtd.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.APDUEvent;
import net.sf.scuba.smartcards.APDUListener;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.util.Hex;
import org.jmrtd.Util;
import org.jmrtd.WrappedAPDUEvent;

/* loaded from: classes6.dex */
public class SecureMessagingAPDUSender {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    private int apduCount = 0;
    private CardService service;

    public SecureMessagingAPDUSender(CardService cardService) {
        this.service = cardService;
    }

    public ResponseAPDU transmit(APDUWrapper aPDUWrapper, CommandAPDU commandAPDU) throws CardServiceException {
        CommandAPDU wrap = aPDUWrapper != null ? aPDUWrapper.wrap(commandAPDU) : commandAPDU;
        ResponseAPDU transmit = this.service.transmit(wrap);
        short sw = (short) transmit.getSW();
        try {
            if (aPDUWrapper == null) {
                int i = this.apduCount + 1;
                this.apduCount = i;
                notifyExchangedAPDU(new APDUEvent(this, "PLAIN", i, wrap, transmit));
                return transmit;
            }
            try {
                if (transmit.getBytes().length <= 2) {
                    throw new CardServiceException("Exception during transmission of wrapped APDU, C=" + Hex.bytesToHexString(commandAPDU.getBytes()), sw);
                }
                ResponseAPDU unwrap = aPDUWrapper.unwrap(transmit);
                String type = aPDUWrapper.getType();
                int i2 = this.apduCount + 1;
                this.apduCount = i2;
                notifyExchangedAPDU(new WrappedAPDUEvent(this, type, i2, commandAPDU, unwrap, wrap, transmit));
                return unwrap;
            } catch (CardServiceException e) {
                throw e;
            } catch (Exception e2) {
                throw new CardServiceException("Exception during transmission of wrapped APDU, C=" + Hex.bytesToHexString(commandAPDU.getBytes()), e2, sw);
            }
        } catch (Throwable th) {
            String type2 = aPDUWrapper.getType();
            int i3 = this.apduCount + 1;
            this.apduCount = i3;
            notifyExchangedAPDU(new WrappedAPDUEvent(this, type2, i3, commandAPDU, transmit, wrap, transmit));
            throw th;
        }
    }

    public boolean isExtendedAPDULengthSupported() {
        return this.service.isExtendedAPDULengthSupported();
    }

    public void addAPDUListener(APDUListener aPDUListener) {
        this.service.addAPDUListener(aPDUListener);
    }

    public void removeAPDUListener(APDUListener aPDUListener) {
        this.service.removeAPDUListener(aPDUListener);
    }

    protected void notifyExchangedAPDU(APDUEvent aPDUEvent) {
        Collection<APDUListener> aPDUListeners = this.service.getAPDUListeners();
        if (aPDUListeners == null || aPDUListeners.isEmpty()) {
            return;
        }
        Iterator<APDUListener> it = aPDUListeners.iterator();
        while (it.hasNext()) {
            it.next().exchangedAPDU(aPDUEvent);
        }
    }

    private List<ResponseAPDU> sendUsingCommandChaining(CommandAPDU commandAPDU, int i) throws CardServiceException {
        List<byte[]> partition = Util.partition(i, commandAPDU.getData());
        ArrayList arrayList = new ArrayList(partition.size());
        int i2 = 0;
        for (byte[] bArr : partition) {
            i2++;
            boolean z = i2 >= partition.size();
            int cla = commandAPDU.getCLA();
            arrayList.add(this.service.transmit(new CommandAPDU(!z ? cla | 16 : cla, commandAPDU.getINS(), commandAPDU.getP1(), commandAPDU.getP2(), bArr, commandAPDU.getNe())));
        }
        return arrayList;
    }

    private byte[] continueSendingUsingResponseChaining(APDUWrapper aPDUWrapper, short s, byte[] bArr) throws CardServiceException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((65280 & s) == 24832) {
            try {
                try {
                    byteArrayOutputStream.write(bArr);
                    int i = s & 255;
                    if (i <= 0) {
                        break;
                    }
                    ResponseAPDU transmit = transmit(aPDUWrapper, new CommandAPDU(0, -64, 0, 0, i));
                    byte[] data = transmit.getData();
                    s = (short) transmit.getSW();
                    bArr = data;
                } catch (IOException e) {
                    throw new CardServiceException("Could not write to stream", e, s);
                }
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e3) {
            LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e3);
        }
        return byteArray;
    }
}
