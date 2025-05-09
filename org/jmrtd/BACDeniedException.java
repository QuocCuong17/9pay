package org.jmrtd;

import net.sf.scuba.smartcards.CardServiceException;

/* loaded from: classes6.dex */
public class BACDeniedException extends CardServiceException {
    private static final long serialVersionUID = -7094953658210693249L;
    private final BACKeySpec bacKey;

    public BACDeniedException(String str, BACKeySpec bACKeySpec, int i) {
        super(str, i);
        this.bacKey = bACKeySpec;
    }

    public BACKeySpec getBACKey() {
        return this.bacKey;
    }
}
