package org.jmrtd;

import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardServiceException;

/* loaded from: classes6.dex */
public interface APDULevelAACapable {
    byte[] sendInternalAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException;
}
