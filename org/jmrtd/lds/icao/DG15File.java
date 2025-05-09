package org.jmrtd.lds.icao;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jmrtd.Util;
import org.jmrtd.lds.DataGroup;

/* loaded from: classes6.dex */
public class DG15File extends DataGroup {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final String[] PUBLIC_KEY_ALGORITHMS = {"RSA", "EC"};
    private static final long serialVersionUID = 3834304239673755744L;
    private PublicKey publicKey;

    public DG15File(PublicKey publicKey) {
        super(111);
        this.publicKey = publicKey;
    }

    public DG15File(InputStream inputStream) throws IOException {
        super(111, inputStream);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void readContent(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        try {
            byte[] bArr = new byte[getLength()];
            dataInputStream.readFully(bArr);
            this.publicKey = getPublicKey(bArr);
        } catch (GeneralSecurityException e) {
            LOGGER.log(Level.WARNING, "Unexpected exception while reading DG15 content", (Throwable) e);
        }
    }

    private static PublicKey getPublicKey(byte[] bArr) throws GeneralSecurityException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bArr);
        String[] strArr = PUBLIC_KEY_ALGORITHMS;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            try {
                return Util.getPublicKey(strArr[i], x509EncodedKeySpec);
            } catch (InvalidKeySpecException e) {
                LOGGER.log(Level.FINE, "Ignore, try next algorithm", (Throwable) e);
            }
        }
        throw new InvalidAlgorithmParameterException();
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void writeContent(OutputStream outputStream) throws IOException {
        outputStream.write(this.publicKey.getEncoded());
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            return this.publicKey.equals(((DG15File) obj).publicKey);
        }
        return false;
    }

    public int hashCode() {
        return (this.publicKey.hashCode() * 5) + 61;
    }

    @Override // org.jmrtd.lds.DataGroup, org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        return "DG15File [" + Util.getDetailedPublicKeyAlgorithm(this.publicKey) + "]";
    }
}
