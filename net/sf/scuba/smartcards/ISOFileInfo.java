package net.sf.scuba.smartcards;

import java.math.BigInteger;
import net.sf.scuba.util.Hex;
import org.apache.commons.io.IOUtils;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes5.dex */
public class ISOFileInfo extends FileInfo {
    public static final byte A0 = -96;
    public static final byte A1 = -95;
    public static final byte A2 = -94;
    public static final byte A5 = -91;
    public static final byte AB = -85;
    public static final byte AC = -84;
    public static final byte CHANNEL_SECURITY = -114;
    public static final byte DATA_BYTES1 = Byte.MIN_VALUE;
    public static final byte DATA_BYTES2 = -127;
    public static final byte DF_NAME = -124;
    public static final byte ENV_TEMP_EF = -115;
    public static final byte FCI_BYTE = 111;
    public static final byte FCI_EXT = -121;
    public static final byte FCP_BYTE = 98;
    public static final byte FILE_DESCRIPTOR = -126;
    public static final byte FILE_IDENTIFIER = -125;
    public static final byte FMD_BYTE = 100;
    public static final byte LCS_BYTE = -118;
    public static final byte PROP_INFO = -123;
    public static final byte SECURITY_ATTR_COMPACT = -116;
    public static final byte SECURITY_ATTR_EXP = -117;
    public static final byte SECURITY_ATTR_PROP = -122;
    public static final byte SHORT_EF = -120;
    byte[] a0;
    byte[] a1;
    byte[] a2;
    byte[] a5;
    byte[] ab;
    byte[] ac;
    byte channelSecurity;
    byte dataCodingByte;
    byte descriptorByte;
    byte[] dfName;
    short envTempEF;
    short fciExt;
    short fid;
    int fileLength;
    int fileLengthFCI;
    byte lcsByte;
    byte mainTag;
    short maxRecordSize;
    short maxRecordsCount;
    byte[] propInfo;
    byte[] secAttrCompact;
    byte[] secAttrExp;
    byte[] secAttrProp;
    byte shortEF;

    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0081. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0084. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0087. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    public ISOFileInfo(byte[] bArr) throws CardServiceException {
        this.mainTag = (byte) -1;
        this.fileLength = -1;
        this.fileLengthFCI = -1;
        this.descriptorByte = (byte) -1;
        this.dataCodingByte = (byte) -1;
        this.maxRecordSize = (short) -1;
        this.maxRecordsCount = (short) -1;
        this.fid = (short) -1;
        this.dfName = null;
        this.propInfo = null;
        this.secAttrProp = null;
        this.secAttrExp = null;
        this.secAttrCompact = null;
        this.fciExt = (short) -1;
        this.envTempEF = (short) -1;
        this.shortEF = (byte) -1;
        this.lcsByte = (byte) -1;
        this.channelSecurity = (byte) -1;
        this.a0 = null;
        this.a1 = null;
        this.a2 = null;
        this.a5 = null;
        this.ab = null;
        this.ac = null;
        if (bArr.length == 0) {
            return;
        }
        if (bArr[0] != 111 && bArr[0] != 98 && bArr[0] != 100) {
            throw new CardServiceException("Malformed FCI data");
        }
        this.mainTag = bArr[0];
        int i = bArr[1];
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 2, bArr2, 0, bArr[1]);
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            try {
                byte b = bArr2[i2];
                int i4 = i3 + 1;
                int i5 = bArr2[i3];
                byte[] bArr3 = new byte[i5];
                System.arraycopy(bArr2, i4, bArr3, 0, i5);
                int i6 = i4 + i5;
                if (b == -91) {
                    byte[] bArr4 = new byte[i5];
                    this.a5 = bArr4;
                    System.arraycopy(bArr3, 0, bArr4, 0, i5);
                } else if (b == -85) {
                    byte[] bArr5 = new byte[i5];
                    this.ab = bArr5;
                    System.arraycopy(bArr3, 0, bArr5, 0, i5);
                } else if (b != -84) {
                    switch (b) {
                        case Byte.MIN_VALUE:
                            this.fileLength = new BigInteger(bArr3).abs().intValue();
                            break;
                        case -127:
                            checkLen(i5, 2);
                            this.fileLengthFCI = new BigInteger(bArr3).intValue();
                            break;
                        case -126:
                            checkLen(i5, 1, 6);
                            this.descriptorByte = bArr3[0];
                            if (1 != i5) {
                                this.dataCodingByte = bArr3[1];
                                if (2 != i5) {
                                    int i7 = 3;
                                    if (i5 == 3) {
                                        this.maxRecordSize = bArr3[2];
                                    } else {
                                        this.maxRecordSize = new BigInteger(new byte[]{bArr3[2], bArr3[3]}).shortValue();
                                        i7 = 4;
                                    }
                                    if (i7 == i5) {
                                        break;
                                    } else if (i5 == 5) {
                                        this.maxRecordsCount = bArr3[i7];
                                        break;
                                    } else {
                                        this.maxRecordsCount = new BigInteger(new byte[]{bArr3[i7], bArr3[i7 + 1]}).shortValue();
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        case -125:
                            checkLen(i5, 2);
                            this.fid = new BigInteger(bArr3).shortValue();
                            break;
                        case -124:
                            checkLen(i5, 0, 16);
                            byte[] bArr6 = new byte[i5];
                            this.dfName = bArr6;
                            System.arraycopy(bArr3, 0, bArr6, 0, i5);
                            break;
                        case -123:
                            byte[] bArr7 = new byte[i5];
                            this.propInfo = bArr7;
                            System.arraycopy(bArr3, 0, bArr7, 0, i5);
                            break;
                        case -122:
                            byte[] bArr8 = new byte[i5];
                            this.secAttrProp = bArr8;
                            System.arraycopy(bArr3, 0, bArr8, 0, i5);
                            break;
                        case -121:
                            checkLen(i5, 2);
                            this.fciExt = new BigInteger(bArr3).shortValue();
                            break;
                        case -120:
                            checkLen(i5, 0, 1);
                            if (i5 == 0) {
                                this.shortEF = (byte) 0;
                                break;
                            } else {
                                this.shortEF = bArr3[0];
                                break;
                            }
                        default:
                            switch (b) {
                                case -118:
                                    checkLen(i5, 1);
                                    this.lcsByte = bArr3[0];
                                    break;
                                case -117:
                                    byte[] bArr9 = new byte[i5];
                                    this.secAttrExp = bArr9;
                                    System.arraycopy(bArr3, 0, bArr9, 0, i5);
                                    break;
                                case -116:
                                    byte[] bArr10 = new byte[i5];
                                    this.secAttrCompact = bArr10;
                                    System.arraycopy(bArr3, 0, bArr10, 0, i5);
                                    break;
                                case -115:
                                    checkLen(i5, 2);
                                    this.envTempEF = new BigInteger(bArr3).shortValue();
                                    break;
                                case -114:
                                    checkLen(i5, 1);
                                    this.channelSecurity = bArr3[0];
                                    break;
                                default:
                                    switch (b) {
                                        case -96:
                                            byte[] bArr11 = new byte[i5];
                                            this.a0 = bArr11;
                                            System.arraycopy(bArr3, 0, bArr11, 0, i5);
                                            break;
                                        case ISO781611.BIOMETRIC_HEADER_TEMPLATE_BASE_TAG /* -95 */:
                                            byte[] bArr12 = new byte[i5];
                                            this.a1 = bArr12;
                                            System.arraycopy(bArr3, 0, bArr12, 0, i5);
                                            break;
                                        case -94:
                                            byte[] bArr13 = new byte[i5];
                                            this.a2 = bArr13;
                                            System.arraycopy(bArr3, 0, bArr13, 0, i5);
                                            break;
                                        default:
                                            throw new CardServiceException("Malformed FCI: unrecognized tag.");
                                    }
                            }
                    }
                } else {
                    byte[] bArr14 = new byte[i5];
                    this.ac = bArr14;
                    System.arraycopy(bArr3, 0, bArr14, 0, i5);
                }
                i2 = i6;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new CardServiceException("Malformed FCI.");
            }
        }
    }

    private static void checkLen(int i, int i2) throws CardServiceException {
        if (i != i2) {
            throw new CardServiceException("Malformed FCI.");
        }
    }

    private static void checkLen(int i, int i2, int i3) throws CardServiceException {
        if (i < i2 || i > i3) {
            throw new CardServiceException("Malformed FCI.");
        }
    }

    public byte[] getFormatted() {
        String shortToHexString;
        String shortToHexString2;
        byte[] bArr = new byte[0];
        if (this.mainTag == -1) {
            return bArr;
        }
        int i = this.fileLength;
        if (i != -1) {
            bArr = catArray(bArr, getArray(Byte.MIN_VALUE, Hex.hexStringToBytes(Hex.shortToHexString((short) i))));
        }
        int i2 = this.fileLengthFCI;
        if (i2 != -1) {
            bArr = catArray(bArr, getArray(DATA_BYTES2, Hex.hexStringToBytes(Hex.shortToHexString((short) i2))));
        }
        byte b = this.descriptorByte;
        if (b != -1) {
            byte[] bArr2 = {b};
            byte[] bArr3 = new byte[0];
            byte b2 = this.dataCodingByte;
            if (b2 != -1) {
                bArr3 = new byte[]{b2};
            }
            byte[] bArr4 = new byte[0];
            short s = this.maxRecordSize;
            if (s != -1) {
                if (s <= 256) {
                    if (this.maxRecordsCount == -1) {
                        shortToHexString2 = Hex.byteToHexString((byte) s);
                    } else {
                        shortToHexString2 = Hex.shortToHexString(s);
                    }
                } else {
                    shortToHexString2 = Hex.shortToHexString(s);
                }
                bArr4 = Hex.hexStringToBytes(shortToHexString2);
            }
            byte[] bArr5 = new byte[0];
            short s2 = this.maxRecordsCount;
            if (s2 != -1) {
                if (s2 <= 256) {
                    shortToHexString = Hex.byteToHexString((byte) s2);
                } else {
                    shortToHexString = Hex.shortToHexString(s2);
                }
                bArr5 = Hex.hexStringToBytes(shortToHexString);
            }
            bArr = catArray(bArr, getArray((byte) -126, catArray(catArray(catArray(bArr2, bArr3), bArr4), bArr5)));
        }
        short s3 = this.fid;
        if (s3 != -1) {
            bArr = catArray(bArr, getArray(FILE_IDENTIFIER, Hex.hexStringToBytes(Hex.shortToHexString(s3))));
        }
        byte[] bArr6 = this.dfName;
        if (bArr6 != null) {
            bArr = catArray(bArr, getArray((byte) -124, bArr6));
        }
        byte[] bArr7 = this.propInfo;
        if (bArr7 != null) {
            bArr = catArray(bArr, getArray(PROP_INFO, bArr7));
        }
        byte[] bArr8 = this.secAttrProp;
        if (bArr8 != null) {
            bArr = catArray(bArr, getArray((byte) -122, bArr8));
        }
        short s4 = this.fciExt;
        if (s4 != -1) {
            bArr = catArray(bArr, getArray(FCI_EXT, Hex.hexStringToBytes(Hex.shortToHexString(s4))));
        }
        byte b3 = this.shortEF;
        if (b3 != -1) {
            bArr = catArray(bArr, getArray((byte) -120, b3 == 0 ? new byte[0] : new byte[]{b3}));
        }
        byte b4 = this.lcsByte;
        if (b4 != -1) {
            bArr = catArray(bArr, getArray(LCS_BYTE, new byte[]{b4}));
        }
        byte[] bArr9 = this.secAttrExp;
        if (bArr9 != null) {
            bArr = catArray(bArr, getArray(SECURITY_ATTR_EXP, bArr9));
        }
        byte[] bArr10 = this.secAttrCompact;
        if (bArr10 != null) {
            bArr = catArray(bArr, getArray(SECURITY_ATTR_COMPACT, bArr10));
        }
        short s5 = this.envTempEF;
        if (s5 != -1) {
            bArr = catArray(bArr, getArray(ENV_TEMP_EF, Hex.hexStringToBytes(Hex.shortToHexString(s5))));
        }
        byte b5 = this.channelSecurity;
        if (b5 != -1) {
            bArr = catArray(bArr, getArray(CHANNEL_SECURITY, new byte[]{b5}));
        }
        byte[] bArr11 = this.a0;
        if (bArr11 != null) {
            bArr = catArray(bArr, getArray(A0, bArr11));
        }
        byte[] bArr12 = this.a1;
        if (bArr12 != null) {
            bArr = catArray(bArr, getArray(A1, bArr12));
        }
        byte[] bArr13 = this.a2;
        if (bArr13 != null) {
            bArr = catArray(bArr, getArray((byte) -94, bArr13));
        }
        byte[] bArr14 = this.a5;
        if (bArr14 != null) {
            bArr = catArray(bArr, getArray(A5, bArr14));
        }
        byte[] bArr15 = this.ab;
        if (bArr15 != null) {
            bArr = catArray(bArr, getArray(AB, bArr15));
        }
        byte[] bArr16 = this.ac;
        if (bArr16 != null) {
            bArr = catArray(bArr, getArray((byte) -84, bArr16));
        }
        return getArray(this.mainTag, bArr);
    }

    private static byte[] getArray(byte b, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 2];
        bArr2[0] = b;
        bArr2[1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    private static byte[] catArray(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public String toString() {
        return "Length: " + this.fileLength + "\nLength FCI: " + this.fileLengthFCI + "\nDesc byte: " + ((int) this.descriptorByte) + "\nData byte: " + ((int) this.dataCodingByte) + "\nRecord size: " + ((int) this.maxRecordSize) + "\nRecord count: " + ((int) this.maxRecordsCount) + "\nFID: " + Hex.shortToHexString(this.fid) + "\nDF name: " + Hex.bytesToHexString(this.dfName) + "\npropInfo: " + Hex.bytesToHexString(this.propInfo) + "\nsecAttrProp: " + Hex.bytesToHexString(this.secAttrProp) + "\nsecAttrExp: " + Hex.bytesToHexString(this.secAttrExp) + "\nsecAttrComp: " + Hex.bytesToHexString(this.secAttrCompact) + "\nFCI ext: " + Hex.shortToHexString(this.fciExt) + "\nEF env temp: " + Hex.shortToHexString(this.envTempEF) + "\nShort EF: " + Hex.byteToHexString(this.shortEF) + "\nLCS byte: " + Hex.byteToHexString(this.lcsByte) + "\nChannel sec: " + Hex.byteToHexString(this.channelSecurity) + "\na0: " + Hex.bytesToHexString(this.a0) + "\na1: " + Hex.bytesToHexString(this.a1) + "\na2: " + Hex.bytesToHexString(this.a2) + "\na5: " + Hex.bytesToHexString(this.a5) + "\nab: " + Hex.bytesToHexString(this.ab) + "\nac: " + Hex.bytesToHexString(this.ac) + IOUtils.LINE_SEPARATOR_UNIX;
    }

    @Override // net.sf.scuba.smartcards.FileInfo
    public short getFID() {
        return this.fid;
    }

    @Override // net.sf.scuba.smartcards.FileInfo
    public int getFileLength() {
        return this.fileLength;
    }
}
