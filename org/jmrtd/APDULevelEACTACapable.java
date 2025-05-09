package org.jmrtd;

import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardServiceException;

/* loaded from: classes6.dex */
public interface APDULevelEACTACapable {
    byte[] sendGetChallenge(APDUWrapper aPDUWrapper) throws CardServiceException;

    void sendMSESetATExtAuth(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException;

    void sendMSESetDST(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException;

    void sendMutualAuthenticate(APDUWrapper aPDUWrapper, byte[] bArr) throws CardServiceException;

    void sendPSOExtendedLengthMode(APDUWrapper aPDUWrapper, byte[] bArr, byte[] bArr2) throws CardServiceException;
}
