package org.bouncycastle.jcajce.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.bouncycastle.crypto.io.InvalidCipherTextIOException;

/* loaded from: classes6.dex */
public class CipherOutputStream extends FilterOutputStream {
    private final Cipher cipher;
    private final byte[] oneByte;

    public CipherOutputStream(OutputStream outputStream, Cipher cipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.cipher = cipher;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:(4:1|2|(1:4)|6)|7|8|(1:10)(1:12)) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0039, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        if (r0 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void close() throws IOException {
        IOException iOException;
        IOException iOException2;
        try {
            byte[] doFinal = this.cipher.doFinal();
            if (doFinal != null) {
                this.out.write(doFinal);
            }
            iOException2 = null;
        } catch (GeneralSecurityException e) {
            iOException = new InvalidCipherTextIOException("Error during cipher finalisation", e);
            iOException2 = iOException;
            flush();
            this.out.close();
            if (iOException2 != null) {
            }
        } catch (Exception e2) {
            iOException = new IOException("Error closing stream: " + e2);
            iOException2 = iOException;
            flush();
            this.out.close();
            if (iOException2 != null) {
            }
        }
        flush();
        this.out.close();
        if (iOException2 != null) {
            throw iOException2;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] update = this.cipher.update(bArr, i, i2);
        if (update != null) {
            this.out.write(update);
        }
    }
}
