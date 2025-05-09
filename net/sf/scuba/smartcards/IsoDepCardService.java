package net.sf.scuba.smartcards;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.os.Build;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public class IsoDepCardService extends CardService {
    private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");
    private int apduCount = 0;
    private IsoDep isoDep;

    public IsoDepCardService(IsoDep isoDep) {
        this.isoDep = isoDep;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void open() throws CardServiceException {
        if (isOpen()) {
            return;
        }
        try {
            this.isoDep.connect();
            if (!this.isoDep.isConnected()) {
                throw new CardServiceException("Failed to connect");
            }
            this.state = 1;
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to connect", (Throwable) e);
            throw new CardServiceException(e.toString());
        }
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isOpen() {
        if (this.isoDep.isConnected()) {
            this.state = 1;
            return true;
        }
        this.state = 0;
        return false;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException {
        try {
            if (!this.isoDep.isConnected()) {
                throw new CardServiceException("Not connected");
            }
            byte[] transceive = this.isoDep.transceive(commandAPDU.getBytes());
            if (transceive == null || transceive.length < 2) {
                throw new CardServiceException("Failed response");
            }
            ResponseAPDU responseAPDU = new ResponseAPDU(transceive);
            int i = this.apduCount + 1;
            this.apduCount = i;
            notifyExchangedAPDU(new APDUEvent(this, "ISODep", i, commandAPDU, responseAPDU));
            return responseAPDU;
        } catch (IOException e) {
            throw new CardServiceException(e.getMessage());
        } catch (Exception e2) {
            throw new CardServiceException(e2.getMessage());
        }
    }

    @Override // net.sf.scuba.smartcards.CardService
    public byte[] getATR() {
        Tag tag;
        IsoDep isoDep = this.isoDep;
        if (isoDep == null || (tag = isoDep.getTag()) == null) {
            return null;
        }
        if (NfcA.get(tag) != null) {
            return this.isoDep.getHistoricalBytes();
        }
        if (NfcB.get(tag) != null) {
            return this.isoDep.getHiLayerResponse();
        }
        return this.isoDep.getHistoricalBytes();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isExtendedAPDULengthSupported() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.isoDep.isExtendedLengthApduSupported();
        }
        return this.isoDep.getMaxTransceiveLength() > 261;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void close() {
        try {
            this.isoDep.close();
            this.state = 0;
        } catch (IOException unused) {
        }
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isConnectionLost(Exception exc) {
        if (exc == null) {
            return false;
        }
        String name = exc.getClass().getName();
        if (name != null && name.contains("TagLostException")) {
            return true;
        }
        String message = exc.getMessage();
        if (message == null) {
            message = "";
        }
        return message.toLowerCase().contains("tag was lost");
    }
}
