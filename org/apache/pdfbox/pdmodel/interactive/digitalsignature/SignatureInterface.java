package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public interface SignatureInterface {
    byte[] sign(InputStream inputStream) throws IOException;
}
