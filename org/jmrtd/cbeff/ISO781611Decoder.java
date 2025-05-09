package org.jmrtd.cbeff;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.HashMap;
import java.util.logging.Logger;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVUtil;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes6.dex */
public class ISO781611Decoder implements ISO781611 {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private BiometricDataBlockDecoder<?> bdbDecoder;

    public ISO781611Decoder(BiometricDataBlockDecoder<?> biometricDataBlockDecoder) {
        this.bdbDecoder = biometricDataBlockDecoder;
    }

    public ComplexCBEFFInfo decode(InputStream inputStream) throws IOException {
        return readBITGroup(inputStream);
    }

    private ComplexCBEFFInfo readBITGroup(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag != 32609) {
            throw new IllegalArgumentException("Expected tag " + Integer.toHexString(ISO781611.BIOMETRIC_INFORMATION_GROUP_TEMPLATE_TAG) + ", found " + Integer.toHexString(readTag));
        }
        return readBITGroup(readTag, tLVInputStream.readLength(), inputStream);
    }

    private ComplexCBEFFInfo readBITGroup(int i, int i2, InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        ComplexCBEFFInfo complexCBEFFInfo = new ComplexCBEFFInfo();
        if (i != 32609) {
            throw new IllegalArgumentException("Expected tag " + Integer.toHexString(ISO781611.BIOMETRIC_INFORMATION_GROUP_TEMPLATE_TAG) + ", found " + Integer.toHexString(i));
        }
        int readTag = tLVInputStream.readTag();
        if (readTag != 2) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_INFO_COUNT_TAG (" + Integer.toHexString(2) + ") in CBEFF structure, found " + Integer.toHexString(readTag));
        }
        int readLength = tLVInputStream.readLength();
        if (readLength != 1) {
            throw new IllegalArgumentException("BIOMETRIC_INFO_COUNT should have length 1, found length " + readLength);
        }
        byte[] readValue = tLVInputStream.readValue();
        int i3 = readValue[0] & 255;
        for (int i4 = 0; i4 < i3; i4++) {
            complexCBEFFInfo.add(readBIT(inputStream, i4));
        }
        return complexCBEFFInfo;
    }

    private CBEFFInfo readBIT(InputStream inputStream, int i) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        return readBIT(tLVInputStream.readTag(), tLVInputStream.readLength(), inputStream, i);
    }

    private CBEFFInfo readBIT(int i, int i2, InputStream inputStream, int i3) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (i != 32608) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_INFORMATION_TEMPLATE_TAG (" + Integer.toHexString(ISO781611.BIOMETRIC_INFORMATION_TEMPLATE_TAG) + "), found " + Integer.toHexString(i) + ", index is " + i3);
        }
        int readTag = tLVInputStream.readTag();
        int readLength = tLVInputStream.readLength();
        if (readTag == 125) {
            readStaticallyProtectedBIT(inputStream, readTag, readLength, i3);
            return null;
        }
        if ((readTag & 160) == 160) {
            return new SimpleCBEFFInfo(readBiometricDataBlock(inputStream, readBHT(inputStream, readTag, readLength, i3), i3));
        }
        throw new IllegalArgumentException("Unsupported template tag: " + Integer.toHexString(readTag));
    }

    private StandardBiometricHeader readBHT(InputStream inputStream, int i, int i2, int i3) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (i != 161) {
            LOGGER.warning("Expected tag " + Integer.toHexString(CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384) + ", found " + Integer.toHexString(i));
        }
        HashMap hashMap = new HashMap();
        int i4 = 0;
        while (i4 < i2) {
            int readTag = tLVInputStream.readTag();
            int tagLength = i4 + TLVUtil.getTagLength(readTag) + TLVUtil.getLengthLength(tLVInputStream.readLength());
            byte[] readValue = tLVInputStream.readValue();
            i4 = tagLength + readValue.length;
            hashMap.put(Integer.valueOf(readTag), readValue);
        }
        return new StandardBiometricHeader(hashMap);
    }

    private void readStaticallyProtectedBIT(InputStream inputStream, int i, int i2, int i3) throws IOException {
        TLVInputStream tLVInputStream = new TLVInputStream(new ByteArrayInputStream(decodeSMTValue(inputStream)));
        try {
            readBiometricDataBlock(new ByteArrayInputStream(decodeSMTValue(inputStream)), readBHT(tLVInputStream, tLVInputStream.readTag(), tLVInputStream.readLength(), i3), i3);
        } finally {
            tLVInputStream.close();
        }
    }

    private byte[] decodeSMTValue(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        int readLength = tLVInputStream.readLength();
        if (readTag == 129) {
            return tLVInputStream.readValue();
        }
        if (readTag == 133) {
            throw new AccessControlException("Access denied. Biometric Information Template is statically protected.");
        }
        long j = 0;
        if (readTag == 142) {
            while (true) {
                long j2 = readLength;
                if (j >= j2) {
                    return null;
                }
                j += tLVInputStream.skip(j2);
            }
        } else {
            if (readTag != 158) {
                LOGGER.info("Unsupported data object tag " + Integer.toHexString(readTag));
                return null;
            }
            while (true) {
                long j3 = readLength;
                if (j >= j3) {
                    return null;
                }
                j += tLVInputStream.skip(j3);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [org.jmrtd.cbeff.BiometricDataBlock] */
    private BiometricDataBlock readBiometricDataBlock(InputStream inputStream, StandardBiometricHeader standardBiometricHeader, int i) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag != 24366 && readTag != 32558) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_DATA_BLOCK_TAG (" + Integer.toHexString(ISO781611.BIOMETRIC_DATA_BLOCK_TAG) + ") or BIOMETRIC_DATA_BLOCK_TAG_ALT (" + Integer.toHexString(ISO781611.BIOMETRIC_DATA_BLOCK_CONSTRUCTED_TAG) + "), found " + Integer.toHexString(readTag));
        }
        return this.bdbDecoder.decode(inputStream, standardBiometricHeader, i, tLVInputStream.readLength());
    }
}
