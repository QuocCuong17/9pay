package net.sf.scuba.smartcards;

/* loaded from: classes5.dex */
public class WrappingCardService extends CardService {
    private boolean enabled;
    private CardService service;
    private APDUWrapper wrapper;

    public WrappingCardService(CardService cardService, APDUWrapper aPDUWrapper) {
        this.service = cardService;
        this.wrapper = aPDUWrapper;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void open() throws CardServiceException {
        this.service.open();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isOpen() {
        return this.service.isOpen();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException {
        if (isEnabled()) {
            return this.wrapper.unwrap(this.service.transmit(this.wrapper.wrap(commandAPDU)));
        }
        return this.service.transmit(commandAPDU);
    }

    @Override // net.sf.scuba.smartcards.CardService
    public byte[] getATR() throws CardServiceException {
        return this.service.getATR();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void close() {
        this.service.close();
    }

    public void enable() {
        this.enabled = true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void disable() {
        this.enabled = false;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isConnectionLost(Exception exc) {
        return this.service.isConnectionLost(exc);
    }
}
