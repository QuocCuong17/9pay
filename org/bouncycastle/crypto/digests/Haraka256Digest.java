package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.lang.reflect.Array;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class Haraka256Digest extends HarakaBase {
    private static final byte[][] RC = {new byte[]{6, -124, ISO7816.INS_MANAGE_CHANNEL, 76, -26, 32, ISO7816.INS_GET_RESPONSE, 10, -78, -59, -2, -16, 117, ISOFileInfo.DATA_BYTES2, 123, -99}, new byte[]{ISOFileInfo.SECURITY_ATTR_EXP, 102, ISO7816.INS_READ_BINARY_STAMPED, -31, -120, -13, ISOFileInfo.A0, 107, 100, 15, 107, -92, 47, 8, -9, Ascii.ETB}, new byte[]{ISO7816.INS_DECREASE_STAMPED, 2, -34, 45, 83, -14, -124, -104, -49, 2, -99, 96, -97, 2, -111, Ascii.DC4}, new byte[]{14, ISO7816.INS_UPDATE_BINARY, -22, -26, 46, 123, 79, 8, ByteSourceJsonBootstrapper.UTF8_BOM_2, -13, PSSSigner.TRAILER_IMPLICIT, -81, -3, 91, 79, 121}, new byte[]{-53, -49, ISO7816.INS_READ_BINARY, -53, 72, 114, ISO7816.INS_REHABILITATE_CHV, ISOFileInfo.SECURITY_ATTR_EXP, 121, -18, -51, 28, -66, 57, ISO7816.INS_MANAGE_CHANNEL, ISO7816.INS_REHABILITATE_CHV}, new byte[]{126, -22, -51, -18, 110, -112, ISO7816.INS_INCREASE, -73, ISOFileInfo.ENV_TEMP_EF, 83, 53, -19, 43, ISOFileInfo.LCS_BYTE, 5, 123}, new byte[]{103, ISO7816.INS_ENVELOPE, -113, 67, 94, 46, 124, ISO7816.INS_WRITE_BINARY, ISO7816.INS_APPEND_RECORD, 65, 39, 97, ISO7816.INS_PUT_DATA, 79, ByteSourceJsonBootstrapper.UTF8_BOM_1, Ascii.ESC}, new byte[]{41, ISO7816.INS_CHANGE_CHV, -39, ISO7816.INS_READ_BINARY, -81, ISO7816.INS_GET_DATA, -52, 7, 103, 95, -3, ISO7816.INS_APPEND_RECORD, Ascii.US, -57, 11, 59}, new byte[]{ISOFileInfo.AB, AvcSpsUtils.PROFILE_IDC_MAIN, 99, -15, -26, -122, Byte.MAX_VALUE, -23, -20, -37, -113, ISO7816.INS_GET_DATA, -71, -44, 101, -18}, new byte[]{28, ISO7816.INS_DECREASE, ByteSourceJsonBootstrapper.UTF8_BOM_3, -124, -44, -73, -51, 100, 91, ISO7816.INS_PSO, SignedBytes.MAX_POWER_OF_TWO, 79, -83, 3, 126, 51}, new byte[]{-78, -52, 11, -71, -108, Ascii.ETB, 35, ByteSourceJsonBootstrapper.UTF8_BOM_3, 105, 2, ISOFileInfo.SECURITY_ATTR_EXP, 46, ISOFileInfo.ENV_TEMP_EF, -10, -104, 0}, new byte[]{-6, 4, 120, -90, -34, ISOFileInfo.FCI_BYTE, 85, 114, 74, -86, -98, -56, 92, -99, 45, ISOFileInfo.LCS_BYTE}, new byte[]{-33, ISO7816.INS_READ_BINARY_STAMPED, -97, 43, 107, 119, ISO7816.INS_PSO, Ascii.DC2, 14, -6, 79, 46, 41, Ascii.DC2, -97, -44}, new byte[]{30, ISOFileInfo.A1, 3, ISO7816.INS_REHABILITATE_CHV, -12, 73, -94, 54, ISO7816.INS_INCREASE, ISO7816.INS_UPDATE_BINARY, 17, -82, ByteSourceJsonBootstrapper.UTF8_BOM_2, 106, Ascii.DC2, -18}, new byte[]{-81, 4, 73, -120, 75, 5, 0, -124, 95, -106, 0, -55, -100, -88, -20, -90}, new byte[]{33, 2, 94, ISO7816.INS_LOAD_KEY_FILE, -99, Ascii.EM, -100, 79, 120, -94, -57, -29, 39, -27, -109, -20}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_3, 58, -86, -8, -89, 89, -55, -73, -71, 40, 46, -51, -126, -44, 1, 115}, new byte[]{ISOFileInfo.FCP_BYTE, 96, ISO7816.INS_MANAGE_CHANNEL, 13, 97, -122, ISO7816.INS_READ_BINARY, Ascii.ETB, 55, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 16, ISO7816.INS_DECREASE, 125, 107}, new byte[]{90, ISO7816.INS_GET_DATA, 69, ISO7816.INS_ENVELOPE, 33, ISO7816.INS_DECREASE, 4, 67, ISOFileInfo.DATA_BYTES2, ISO7816.INS_ENVELOPE, -111, 83, -10, -4, -102, -58}, new byte[]{-110, 35, -105, 60, ISO7816.INS_MSE, 107, 104, ByteSourceJsonBootstrapper.UTF8_BOM_2, ISO7816.INS_UNBLOCK_CHV, -81, -110, -24, 54, -47, -108, 58}};
    private final byte[] buffer;
    private int off;

    public Haraka256Digest() {
        this.buffer = new byte[32];
    }

    public Haraka256Digest(Haraka256Digest haraka256Digest) {
        this.buffer = Arrays.clone(haraka256Digest.buffer);
        this.off = haraka256Digest.off;
    }

    private int haraka256256(byte[] bArr, byte[] bArr2, int i) {
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) byte.class, 2, 16);
        byte[][] bArr4 = (byte[][]) Array.newInstance((Class<?>) byte.class, 2, 16);
        System.arraycopy(bArr, 0, bArr3[0], 0, 16);
        System.arraycopy(bArr, 16, bArr3[1], 0, 16);
        byte[] bArr5 = bArr3[0];
        byte[][] bArr6 = RC;
        bArr3[0] = aesEnc(bArr5, bArr6[0]);
        bArr3[1] = aesEnc(bArr3[1], bArr6[1]);
        bArr3[0] = aesEnc(bArr3[0], bArr6[2]);
        bArr3[1] = aesEnc(bArr3[1], bArr6[3]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], bArr6[4]);
        bArr3[1] = aesEnc(bArr4[1], bArr6[5]);
        bArr3[0] = aesEnc(bArr3[0], bArr6[6]);
        bArr3[1] = aesEnc(bArr3[1], bArr6[7]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], bArr6[8]);
        bArr3[1] = aesEnc(bArr4[1], bArr6[9]);
        bArr3[0] = aesEnc(bArr3[0], bArr6[10]);
        bArr3[1] = aesEnc(bArr3[1], bArr6[11]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], bArr6[12]);
        bArr3[1] = aesEnc(bArr4[1], bArr6[13]);
        bArr3[0] = aesEnc(bArr3[0], bArr6[14]);
        bArr3[1] = aesEnc(bArr3[1], bArr6[15]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], bArr6[16]);
        bArr3[1] = aesEnc(bArr4[1], bArr6[17]);
        bArr3[0] = aesEnc(bArr3[0], bArr6[18]);
        bArr3[1] = aesEnc(bArr3[1], bArr6[19]);
        mix256(bArr3, bArr4);
        bArr3[0] = xor(bArr4[0], bArr, 0);
        bArr3[1] = xor(bArr4[1], bArr, 16);
        System.arraycopy(bArr3[0], 0, bArr2, i, 16);
        System.arraycopy(bArr3[1], 0, bArr2, i + 16, 16);
        return 32;
    }

    private void mix256(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 0, bArr2[0], 0, 4);
        System.arraycopy(bArr[1], 0, bArr2[0], 4, 4);
        System.arraycopy(bArr[0], 4, bArr2[0], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[0], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[1], 0, 4);
        System.arraycopy(bArr[1], 8, bArr2[1], 4, 4);
        System.arraycopy(bArr[0], 12, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 12, bArr2[1], 12, 4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        if (this.off != 32) {
            throw new IllegalStateException("input must be exactly 32 bytes");
        }
        if (bArr.length - i < 32) {
            throw new IllegalArgumentException("output too short to receive digest");
        }
        int haraka256256 = haraka256256(this.buffer, bArr, i);
        reset();
        return haraka256256;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Haraka-256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.off;
        if (i + 1 > 32) {
            throw new IllegalArgumentException("total input cannot be more than 32 bytes");
        }
        byte[] bArr = this.buffer;
        this.off = i + 1;
        bArr[i] = b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 + i2 > 32) {
            throw new IllegalArgumentException("total input cannot be more than 32 bytes");
        }
        System.arraycopy(bArr, i, this.buffer, i3, i2);
        this.off += i2;
    }
}
