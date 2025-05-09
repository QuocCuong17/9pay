package net.sf.scuba.smartcards;

import java.util.EventObject;

/* loaded from: classes5.dex */
public class CardEvent extends EventObject {
    public static final int INSERTED = 1;
    public static final int REMOVED = 0;
    private static final long serialVersionUID = -5645277246646615351L;
    private transient CardService service;
    private int type;

    public CardEvent(int i, CardService cardService) {
        super(cardService);
        this.type = i;
        this.service = cardService;
    }

    public int getType() {
        return this.type;
    }

    public CardService getService() {
        return this.service;
    }

    @Override // java.util.EventObject
    public String toString() {
        int i = this.type;
        if (i == 0) {
            return "Card removed from " + this.service;
        }
        if (i == 1) {
            return "Card inserted in " + this.service;
        }
        throw new IllegalStateException("Unknown event type " + this.type);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        CardEvent cardEvent = (CardEvent) obj;
        return this.type == cardEvent.type && this.service.equals(cardEvent.service);
    }

    public int hashCode() {
        return (this.service.hashCode() * 5) + (this.type * 7);
    }
}
