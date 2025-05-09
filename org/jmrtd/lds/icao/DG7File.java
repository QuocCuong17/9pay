package org.jmrtd.lds.icao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.jmrtd.lds.DisplayedImageDataGroup;
import org.jmrtd.lds.DisplayedImageInfo;

/* loaded from: classes6.dex */
public class DG7File extends DisplayedImageDataGroup {
    private static final long serialVersionUID = 7189545112850471359L;

    public DG7File(List<DisplayedImageInfo> list) {
        super(103, list, DisplayedImageInfo.DISPLAYED_SIGNATURE_OR_MARK_TAG);
    }

    public DG7File(InputStream inputStream) throws IOException {
        super(103, inputStream);
    }
}
