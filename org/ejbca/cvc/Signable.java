package org.ejbca.cvc;

import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes6.dex */
public interface Signable {
    byte[] getTBS() throws ConstructionException;
}
