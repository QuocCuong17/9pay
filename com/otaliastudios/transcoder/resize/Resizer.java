package com.otaliastudios.transcoder.resize;

import com.otaliastudios.transcoder.common.Size;

/* loaded from: classes4.dex */
public interface Resizer {
    Size getOutputSize(Size size) throws Exception;
}
