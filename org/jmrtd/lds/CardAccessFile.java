package org.jmrtd.lds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DLSet;

/* loaded from: classes6.dex */
public class CardAccessFile implements Serializable {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.lds");
    private static final long serialVersionUID = -3536507558193769951L;
    private Set<SecurityInfo> securityInfos;

    public CardAccessFile(Collection<SecurityInfo> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Null securityInfos");
        }
        this.securityInfos = new HashSet(collection);
    }

    public CardAccessFile(InputStream inputStream) throws IOException {
        readContent(inputStream);
    }

    protected void readContent(InputStream inputStream) throws IOException {
        this.securityInfos = new HashSet();
        ASN1Set aSN1Set = (ASN1Set) new ASN1InputStream(inputStream).readObject();
        for (int i = 0; i < aSN1Set.size(); i++) {
            SecurityInfo securityInfo = SecurityInfo.getInstance(aSN1Set.getObjectAt(i).toASN1Primitive());
            if (securityInfo != null) {
                this.securityInfos.add(securityInfo);
            }
        }
    }

    protected void writeContent(OutputStream outputStream) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Iterator<SecurityInfo> it = this.securityInfos.iterator();
        while (it.hasNext()) {
            aSN1EncodableVector.add(it.next().getDERObject());
        }
        outputStream.write(new DLSet(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
    }

    public byte[] getEncoded() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                writeContent(byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e);
                }
                return byteArray;
            } catch (IOException e2) {
                LOGGER.log(Level.WARNING, "Exception while encoding", (Throwable) e2);
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e3);
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e4) {
                LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e4);
            }
            throw th;
        }
    }

    public Collection<SecurityInfo> getSecurityInfos() {
        return Collections.unmodifiableCollection(this.securityInfos);
    }

    public String toString() {
        return "CardAccessFile [" + this.securityInfos.toString() + "]";
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        CardAccessFile cardAccessFile = (CardAccessFile) obj;
        Set<SecurityInfo> set = this.securityInfos;
        if (set == null) {
            return cardAccessFile.securityInfos == null;
        }
        Set<SecurityInfo> set2 = cardAccessFile.securityInfos;
        if (set2 == null) {
            return set == null;
        }
        return set.equals(set2);
    }

    public int hashCode() {
        return (this.securityInfos.hashCode() * 7) + 61;
    }
}
