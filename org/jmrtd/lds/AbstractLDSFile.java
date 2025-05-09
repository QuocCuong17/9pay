package org.jmrtd.lds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes6.dex */
abstract class AbstractLDSFile implements LDSFile {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -4908935713109830409L;

    protected abstract void readObject(InputStream inputStream) throws IOException;

    protected abstract void writeObject(OutputStream outputStream) throws IOException;

    @Override // org.jmrtd.lds.LDSElement
    public byte[] getEncoded() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            writeObject(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
            return null;
        }
    }
}
