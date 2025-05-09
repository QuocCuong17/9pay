package org.jmrtd;

import net.sf.scuba.smartcards.CardFileInputStream;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;

/* loaded from: classes6.dex */
public abstract class FileSystemCardService extends CardService {
    public abstract CardFileInputStream getInputStream(short s) throws CardServiceException;
}
