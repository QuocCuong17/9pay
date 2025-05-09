package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.lang.reflect.Array;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class Haraka512Digest extends HarakaBase {
    private static byte[][] RC = {new byte[]{6, -124, ISO7816.INS_MANAGE_CHANNEL, 76, -26, 32, ISO7816.INS_GET_RESPONSE, 10, -78, -59, -2, -16, 117, ISOFileInfo.DATA_BYTES2, 123, -99}, new byte[]{ISOFileInfo.SECURITY_ATTR_EXP, 102, ISO7816.INS_READ_BINARY_STAMPED, -31, -120, -13, ISOFileInfo.A0, 107, 100, 15, 107, -92, 47, 8, -9, Ascii.ETB}, new byte[]{ISO7816.INS_DECREASE_STAMPED, 2, -34, 45, 83, -14, -124, -104, -49, 2, -99, 96, -97, 2, -111, Ascii.DC4}, new byte[]{14, ISO7816.INS_UPDATE_BINARY, -22, -26, 46, 123, 79, 8, ByteSourceJsonBootstrapper.UTF8_BOM_2, -13, PSSSigner.TRAILER_IMPLICIT, -81, -3, 91, 79, 121}, new byte[]{-53, -49, ISO7816.INS_READ_BINARY, -53, 72, 114, ISO7816.INS_REHABILITATE_CHV, ISOFileInfo.SECURITY_ATTR_EXP, 121, -18, -51, 28, -66, 57, ISO7816.INS_MANAGE_CHANNEL, ISO7816.INS_REHABILITATE_CHV}, new byte[]{126, -22, -51, -18, 110, -112, ISO7816.INS_INCREASE, -73, ISOFileInfo.ENV_TEMP_EF, 83, 53, -19, 43, ISOFileInfo.LCS_BYTE, 5, 123}, new byte[]{103, ISO7816.INS_ENVELOPE, -113, 67, 94, 46, 124, ISO7816.INS_WRITE_BINARY, ISO7816.INS_APPEND_RECORD, 65, 39, 97, ISO7816.INS_PUT_DATA, 79, ByteSourceJsonBootstrapper.UTF8_BOM_1, Ascii.ESC}, new byte[]{41, ISO7816.INS_CHANGE_CHV, -39, ISO7816.INS_READ_BINARY, -81, ISO7816.INS_GET_DATA, -52, 7, 103, 95, -3, ISO7816.INS_APPEND_RECORD, Ascii.US, -57, 11, 59}, new byte[]{ISOFileInfo.AB, AvcSpsUtils.PROFILE_IDC_MAIN, 99, -15, -26, -122, Byte.MAX_VALUE, -23, -20, -37, -113, ISO7816.INS_GET_DATA, -71, -44, 101, -18}, new byte[]{28, ISO7816.INS_DECREASE, ByteSourceJsonBootstrapper.UTF8_BOM_3, -124, -44, -73, -51, 100, 91, ISO7816.INS_PSO, SignedBytes.MAX_POWER_OF_TWO, 79, -83, 3, 126, 51}, new byte[]{-78, -52, 11, -71, -108, Ascii.ETB, 35, ByteSourceJsonBootstrapper.UTF8_BOM_3, 105, 2, ISOFileInfo.SECURITY_ATTR_EXP, 46, ISOFileInfo.ENV_TEMP_EF, -10, -104, 0}, new byte[]{-6, 4, 120, -90, -34, ISOFileInfo.FCI_BYTE, 85, 114, 74, -86, -98, -56, 92, -99, 45, ISOFileInfo.LCS_BYTE}, new byte[]{-33, ISO7816.INS_READ_BINARY_STAMPED, -97, 43, 107, 119, ISO7816.INS_PSO, Ascii.DC2, 14, -6, 79, 46, 41, Ascii.DC2, -97, -44}, new byte[]{30, ISOFileInfo.A1, 3, ISO7816.INS_REHABILITATE_CHV, -12, 73, -94, 54, ISO7816.INS_INCREASE, ISO7816.INS_UPDATE_BINARY, 17, -82, ByteSourceJsonBootstrapper.UTF8_BOM_2, 106, Ascii.DC2, -18}, new byte[]{-81, 4, 73, -120, 75, 5, 0, -124, 95, -106, 0, -55, -100, -88, -20, -90}, new byte[]{33, 2, 94, ISO7816.INS_LOAD_KEY_FILE, -99, Ascii.EM, -100, 79, 120, -94, -57, -29, 39, -27, -109, -20}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_3, 58, -86, -8, -89, 89, -55, -73, -71, 40, 46, -51, -126, -44, 1, 115}, new byte[]{ISOFileInfo.FCP_BYTE, 96, ISO7816.INS_MANAGE_CHANNEL, 13, 97, -122, ISO7816.INS_READ_BINARY, Ascii.ETB, 55, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 16, ISO7816.INS_DECREASE, 125, 107}, new byte[]{90, ISO7816.INS_GET_DATA, 69, ISO7816.INS_ENVELOPE, 33, ISO7816.INS_DECREASE, 4, 67, ISOFileInfo.DATA_BYTES2, ISO7816.INS_ENVELOPE, -111, 83, -10, -4, -102, -58}, new byte[]{-110, 35, -105, 60, ISO7816.INS_MSE, 107, 104, ByteSourceJsonBootstrapper.UTF8_BOM_2, ISO7816.INS_UNBLOCK_CHV, -81, -110, -24, 54, -47, -108, 58}, new byte[]{-45, ByteSourceJsonBootstrapper.UTF8_BOM_3, -110, 56, ISO7816.INS_MSE, AvcSpsUtils.PROFILE_IDC_EXTENDED, -122, -21, 108, -70, -71, AvcSpsUtils.PROFILE_IDC_EXTENDED, -27, 16, 113, ISO7816.INS_READ_BINARY_STAMPED}, new byte[]{-37, -122, 60, -27, -82, -16, -58, 119, -109, Base64.padSymbol, -3, -35, ISO7816.INS_CHANGE_CHV, -31, Ascii.DC2, ISOFileInfo.ENV_TEMP_EF}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_2, 96, ISOFileInfo.FCP_BYTE, 104, -1, -21, ISOFileInfo.A0, -100, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_DELETE_FILE, ISOFileInfo.ENV_TEMP_EF, -29, -53, ISO7816.INS_MSE, Ascii.DC2, ISO7816.INS_READ_BINARY2}, new byte[]{115, 75, -45, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_APPEND_RECORD, ISO7816.INS_DELETE_FILE, -47, -100, 45, -71, Ascii.SUB, 78, -57, 43, -9, 125}, new byte[]{67, ByteSourceJsonBootstrapper.UTF8_BOM_2, 71, -61, 97, ISO7816.INS_DECREASE, Ascii.ESC, 67, 75, Ascii.DC4, Ascii.NAK, -60, ISO7816.INS_UNBLOCK_CHV, ISO7816.INS_READ_RECORD2, -110, 78}, new byte[]{-37, -89, 117, -88, -25, 7, ByteSourceJsonBootstrapper.UTF8_BOM_1, -10, 3, -78, 49, -35, Ascii.SYN, -21, 104, -103}, new byte[]{109, -13, 97, 75, 60, 117, 89, 119, ISOFileInfo.CHANNEL_SECURITY, 94, 35, 2, 126, ISO7816.INS_GET_DATA, 71, ISO7816.INS_UNBLOCK_CHV}, new byte[]{-51, -89, 90, Ascii.ETB, ISO7816.INS_UPDATE_BINARY, -34, 125, 119, 109, Ascii.ESC, -27, -71, -72, -122, Ascii.ETB, -7}, new byte[]{-20, 107, 67, -16, 107, -88, -23, -86, -99, 108, 6, -99, -87, 70, -18, 93}, new byte[]{-53, 30, 105, 80, -7, 87, 51, 43, -94, 83, 17, 89, 59, -13, 39, -63}, new byte[]{ISO7816.INS_UNBLOCK_CHV, -18, 12, 117, 0, ISO7816.INS_PUT_DATA, 97, -100, ISO7816.INS_DELETE_FILE, -19, 3, 83, 96, 14, ISO7816.INS_WRITE_BINARY, -39}, new byte[]{-16, ISO7816.INS_READ_BINARY2, ISOFileInfo.A5, ISOFileInfo.A1, -106, -23, 12, ISOFileInfo.AB, Byte.MIN_VALUE, ByteSourceJsonBootstrapper.UTF8_BOM_2, -70, PSSSigner.TRAILER_IMPLICIT, 99, -92, -93, 80}, new byte[]{-82, Base64.padSymbol, ISO7816.INS_READ_BINARY2, 2, 94, -106, 41, -120, ISOFileInfo.AB, 13, -34, ISO7816.INS_DECREASE, -109, ISOFileInfo.ENV_TEMP_EF, ISO7816.INS_GET_DATA, 57}, new byte[]{Ascii.ETB, ByteSourceJsonBootstrapper.UTF8_BOM_2, -113, 56, -43, 84, -92, 11, -120, Ascii.DC4, -13, -88, 46, 117, ISO7816.INS_READ_BINARY_STAMPED, 66}, new byte[]{ISO7816.INS_DECREASE_STAMPED, ByteSourceJsonBootstrapper.UTF8_BOM_2, ISOFileInfo.LCS_BYTE, 91, 95, 66, Byte.MAX_VALUE, -41, -82, ISO7816.INS_READ_RECORD_STAMPED, -73, 121, 54, 10, Ascii.SYN, -10}, new byte[]{38, -10, 82, 65, -53, -27, 84, 56, 67, -50, 89, Ascii.CAN, -1, -70, -81, -34}, new byte[]{76, -23, -102, 84, -71, -13, 2, 106, -94, ISO7816.INS_GET_DATA, -100, -9, ISOFileInfo.FILE_IDENTIFIER, -98, -55, 120}, new byte[]{-82, 81, ISOFileInfo.A5, Ascii.SUB, Ascii.ESC, -33, -9, -66, SignedBytes.MAX_POWER_OF_TWO, ISO7816.INS_GET_RESPONSE, 110, 40, ISO7816.INS_MSE, -112, Ascii.DC2, 53}, new byte[]{ISOFileInfo.A0, -63, 97, 60, -70, 126, ISO7816.INS_WRITE_RECORD, 43, -63, 115, PSSSigner.TRAILER_IMPLICIT, 15, 72, -90, 89, -49}, new byte[]{117, 106, -52, 3, 2, 40, -126, -120, 74, ISO7816.INS_UPDATE_BINARY, -67, -3, -23, -59, -99, ISOFileInfo.A1}};
    private final byte[] buffer;
    private int off;

    public Haraka512Digest() {
        this.buffer = new byte[64];
    }

    public Haraka512Digest(Haraka512Digest haraka512Digest) {
        this.buffer = Arrays.clone(haraka512Digest.buffer);
        this.off = haraka512Digest.off;
    }

    private int haraka512256(byte[] bArr, byte[] bArr2, int i) {
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 16);
        byte[][] bArr4 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 16);
        System.arraycopy(bArr, 0, bArr3[0], 0, 16);
        System.arraycopy(bArr, 16, bArr3[1], 0, 16);
        System.arraycopy(bArr, 32, bArr3[2], 0, 16);
        System.arraycopy(bArr, 48, bArr3[3], 0, 16);
        bArr3[0] = aesEnc(bArr3[0], RC[0]);
        bArr3[1] = aesEnc(bArr3[1], RC[1]);
        bArr3[2] = aesEnc(bArr3[2], RC[2]);
        bArr3[3] = aesEnc(bArr3[3], RC[3]);
        bArr3[0] = aesEnc(bArr3[0], RC[4]);
        bArr3[1] = aesEnc(bArr3[1], RC[5]);
        bArr3[2] = aesEnc(bArr3[2], RC[6]);
        bArr3[3] = aesEnc(bArr3[3], RC[7]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[8]);
        bArr3[1] = aesEnc(bArr4[1], RC[9]);
        bArr3[2] = aesEnc(bArr4[2], RC[10]);
        bArr3[3] = aesEnc(bArr4[3], RC[11]);
        bArr3[0] = aesEnc(bArr3[0], RC[12]);
        bArr3[1] = aesEnc(bArr3[1], RC[13]);
        bArr3[2] = aesEnc(bArr3[2], RC[14]);
        bArr3[3] = aesEnc(bArr3[3], RC[15]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[16]);
        bArr3[1] = aesEnc(bArr4[1], RC[17]);
        bArr3[2] = aesEnc(bArr4[2], RC[18]);
        bArr3[3] = aesEnc(bArr4[3], RC[19]);
        bArr3[0] = aesEnc(bArr3[0], RC[20]);
        bArr3[1] = aesEnc(bArr3[1], RC[21]);
        bArr3[2] = aesEnc(bArr3[2], RC[22]);
        bArr3[3] = aesEnc(bArr3[3], RC[23]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[24]);
        bArr3[1] = aesEnc(bArr4[1], RC[25]);
        bArr3[2] = aesEnc(bArr4[2], RC[26]);
        bArr3[3] = aesEnc(bArr4[3], RC[27]);
        bArr3[0] = aesEnc(bArr3[0], RC[28]);
        bArr3[1] = aesEnc(bArr3[1], RC[29]);
        bArr3[2] = aesEnc(bArr3[2], RC[30]);
        bArr3[3] = aesEnc(bArr3[3], RC[31]);
        mix512(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], RC[32]);
        bArr3[1] = aesEnc(bArr4[1], RC[33]);
        bArr3[2] = aesEnc(bArr4[2], RC[34]);
        bArr3[3] = aesEnc(bArr4[3], RC[35]);
        bArr3[0] = aesEnc(bArr3[0], RC[36]);
        bArr3[1] = aesEnc(bArr3[1], RC[37]);
        bArr3[2] = aesEnc(bArr3[2], RC[38]);
        bArr3[3] = aesEnc(bArr3[3], RC[39]);
        mix512(bArr3, bArr4);
        bArr3[0] = xor(bArr4[0], bArr, 0);
        bArr3[1] = xor(bArr4[1], bArr, 16);
        bArr3[2] = xor(bArr4[2], bArr, 32);
        bArr3[3] = xor(bArr4[3], bArr, 48);
        System.arraycopy(bArr3[0], 8, bArr2, i, 8);
        System.arraycopy(bArr3[1], 8, bArr2, i + 8, 8);
        System.arraycopy(bArr3[2], 0, bArr2, i + 16, 8);
        System.arraycopy(bArr3[3], 0, bArr2, i + 24, 8);
        return 32;
    }

    private void mix512(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 12, bArr2[0], 0, 4);
        System.arraycopy(bArr[2], 12, bArr2[0], 4, 4);
        System.arraycopy(bArr[1], 12, bArr2[0], 8, 4);
        System.arraycopy(bArr[3], 12, bArr2[0], 12, 4);
        System.arraycopy(bArr[2], 0, bArr2[1], 0, 4);
        System.arraycopy(bArr[0], 0, bArr2[1], 4, 4);
        System.arraycopy(bArr[3], 0, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 0, bArr2[1], 12, 4);
        System.arraycopy(bArr[2], 4, bArr2[2], 0, 4);
        System.arraycopy(bArr[0], 4, bArr2[2], 4, 4);
        System.arraycopy(bArr[3], 4, bArr2[2], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[2], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[3], 0, 4);
        System.arraycopy(bArr[2], 8, bArr2[3], 4, 4);
        System.arraycopy(bArr[1], 8, bArr2[3], 8, 4);
        System.arraycopy(bArr[3], 8, bArr2[3], 12, 4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        if (this.off != 64) {
            throw new IllegalStateException("input must be exactly 64 bytes");
        }
        if (bArr.length - i < 32) {
            throw new IllegalArgumentException("output too short to receive digest");
        }
        int haraka512256 = haraka512256(this.buffer, bArr, i);
        reset();
        return haraka512256;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Haraka-512";
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.off;
        if (i + 1 > 64) {
            throw new IllegalArgumentException("total input cannot be more than 64 bytes");
        }
        byte[] bArr = this.buffer;
        this.off = i + 1;
        bArr[i] = b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 + i2 > 64) {
            throw new IllegalArgumentException("total input cannot be more than 64 bytes");
        }
        System.arraycopy(bArr, i, this.buffer, i3, i2);
        this.off += i2;
    }
}
