package net.sf.scuba.smartcards;

import java.io.Serializable;
import java.util.EventObject;

/* loaded from: classes5.dex */
public class APDUEvent extends EventObject {
    private static final long serialVersionUID = 7152351242541552732L;
    private CommandAPDU capdu;
    private ResponseAPDU rapdu;
    private int sequenceNumber;
    private Serializable type;

    public APDUEvent(Object obj, Serializable serializable, int i, CommandAPDU commandAPDU, ResponseAPDU responseAPDU) {
        super(obj);
        this.type = serializable;
        this.sequenceNumber = i;
        this.capdu = commandAPDU;
        this.rapdu = responseAPDU;
    }

    public Object getType() {
        return this.type;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public CommandAPDU getCommandAPDU() {
        return this.capdu;
    }

    public ResponseAPDU getResponseAPDU() {
        return this.rapdu;
    }
}
