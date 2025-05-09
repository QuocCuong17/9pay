package org.ejbca.cvc;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.ejbca.cvc.exception.ConstructionException;
import org.ejbca.cvc.exception.ParseException;

/* loaded from: classes6.dex */
public final class CertificateParser {
    private CertificateParser() {
    }

    public static CVCObject parseCVCObject(byte[] bArr) throws ParseException, ConstructionException {
        return decode(bArr, (CVCTagEnum) null);
    }

    public static CVCertificate parseCertificate(byte[] bArr) throws ParseException, ConstructionException {
        return (CVCertificate) decode(bArr, CVCTagEnum.CV_CERTIFICATE);
    }

    private static CVCObject decode(byte[] bArr, CVCTagEnum cVCTagEnum) throws ParseException, ConstructionException {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            try {
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
                try {
                    CVCObject decode = decode(new DataInputStream(byteArrayInputStream2), cVCTagEnum);
                    byteArrayInputStream2.close();
                    return decode;
                } catch (Throwable th) {
                    th = th;
                    byteArrayInputStream = byteArrayInputStream2;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    private static CVCObject decode(DataInputStream dataInputStream, CVCTagEnum cVCTagEnum) throws IOException, ConstructionException, ParseException {
        CVCTagEnum findTagFromValue = findTagFromValue(decodeTag(dataInputStream));
        if (cVCTagEnum != null && findTagFromValue != cVCTagEnum) {
            throw new ParseException("Expected first tag " + cVCTagEnum + " but found " + findTagFromValue);
        }
        int decodeLength = CVCObject.decodeLength(dataInputStream);
        if (findTagFromValue.isSequence()) {
            int available = dataInputStream.available() - decodeLength;
            AbstractSequence createSequence = SequenceFactory.createSequence(findTagFromValue);
            while (dataInputStream.available() > available) {
                createSequence.addSubfield(decode(dataInputStream, (CVCTagEnum) null));
            }
            return createSequence instanceof GenericPublicKeyField ? KeyFactory.createInstance((GenericPublicKeyField) createSequence) : createSequence;
        }
        byte[] bArr = new byte[decodeLength];
        dataInputStream.read(bArr, 0, decodeLength);
        return FieldFactory.decodeField(findTagFromValue, bArr);
    }

    private static CVCTagEnum findTagFromValue(int i) throws ParseException {
        CVCTagEnum cVCTagEnum;
        CVCTagEnum[] values = CVCTagEnum.values();
        int length = values.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                cVCTagEnum = null;
                break;
            }
            cVCTagEnum = values[i2];
            if (cVCTagEnum.getValue() == i) {
                break;
            }
            i2++;
        }
        if (cVCTagEnum != null) {
            return cVCTagEnum;
        }
        throw new ParseException("Unknown CVC tag value " + Integer.toHexString(i));
    }

    private static int decodeTag(DataInputStream dataInputStream) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & 31) != 31) {
            return readUnsignedByte;
        }
        return (readUnsignedByte << 8) + dataInputStream.readByte();
    }
}
