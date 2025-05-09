package com.otaliastudios.transcoder.resize;

import com.otaliastudios.transcoder.common.Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class MultiResizer implements Resizer {
    private final List<Resizer> list = new ArrayList();

    public MultiResizer() {
    }

    public MultiResizer(Resizer... resizerArr) {
        for (Resizer resizer : resizerArr) {
            addResizer(resizer);
        }
    }

    public void addResizer(Resizer resizer) {
        this.list.add(resizer);
    }

    @Override // com.otaliastudios.transcoder.resize.Resizer
    public Size getOutputSize(Size size) throws Exception {
        Iterator<Resizer> it = this.list.iterator();
        while (it.hasNext()) {
            size = it.next().getOutputSize(size);
        }
        return size;
    }
}
