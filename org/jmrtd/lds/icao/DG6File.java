package org.jmrtd.lds.icao;

import java.io.IOException;
import java.io.InputStream;
import org.jmrtd.lds.DisplayedImageDataGroup;

/* loaded from: classes6.dex */
public class DG6File extends DisplayedImageDataGroup {
    private static final long serialVersionUID = -583717105472408528L;

    public DG6File(InputStream inputStream) throws IOException {
        super(102, inputStream);
    }
}
