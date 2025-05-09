package org.jmrtd.lds.icao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.jmrtd.lds.DisplayedImageDataGroup;
import org.jmrtd.lds.DisplayedImageInfo;

/* loaded from: classes6.dex */
public class DG5File extends DisplayedImageDataGroup {
    private static final long serialVersionUID = 923840683207218244L;

    public DG5File(List<DisplayedImageInfo> list) {
        super(101, list, DisplayedImageInfo.DISPLAYED_PORTRAIT_TAG);
    }

    public DG5File(InputStream inputStream) throws IOException {
        super(101, inputStream);
    }
}
