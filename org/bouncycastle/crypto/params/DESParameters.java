package org.bouncycastle.crypto.params;

import com.google.common.base.Ascii;
import net.sf.scuba.smartcards.ISO7816;

/* loaded from: classes6.dex */
public class DESParameters extends KeyParameter {
    public static final int DES_KEY_LENGTH = 8;
    private static byte[] DES_weak_keys = {1, 1, 1, 1, 1, 1, 1, 1, Ascii.US, Ascii.US, Ascii.US, Ascii.US, 14, 14, 14, 14, ISO7816.INS_CREATE_FILE, ISO7816.INS_CREATE_FILE, ISO7816.INS_CREATE_FILE, ISO7816.INS_CREATE_FILE, -15, -15, -15, -15, -2, -2, -2, -2, -2, -2, -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, Ascii.US, ISO7816.INS_CREATE_FILE, Ascii.US, ISO7816.INS_CREATE_FILE, 14, -15, 14, -15, 1, ISO7816.INS_CREATE_FILE, 1, ISO7816.INS_CREATE_FILE, 1, -15, 1, -15, Ascii.US, -2, Ascii.US, -2, 14, -2, 14, -2, 1, Ascii.US, 1, Ascii.US, 1, 14, 1, 14, ISO7816.INS_CREATE_FILE, -2, ISO7816.INS_CREATE_FILE, -2, -15, -2, -15, -2, -2, 1, -2, 1, -2, 1, -2, 1, ISO7816.INS_CREATE_FILE, Ascii.US, ISO7816.INS_CREATE_FILE, Ascii.US, -15, 14, -15, 14, ISO7816.INS_CREATE_FILE, 1, ISO7816.INS_CREATE_FILE, 1, -15, 1, -15, 1, -2, Ascii.US, -2, Ascii.US, -2, 14, -2, 14, Ascii.US, 1, Ascii.US, 1, 14, 1, 14, 1, -2, ISO7816.INS_CREATE_FILE, -2, ISO7816.INS_CREATE_FILE, -2, -15, -2, -15};
    private static final int N_DES_WEAK_KEYS = 16;

    public DESParameters(byte[] bArr) {
        super(bArr);
        if (isWeakKey(bArr, 0)) {
            throw new IllegalArgumentException("attempt to create weak DES key");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001c, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isWeakKey(byte[] bArr, int i) {
        if (bArr.length - i < 8) {
            throw new IllegalArgumentException("key material too short.");
        }
        int i2 = 0;
        while (i2 < 16) {
            for (int i3 = 0; i3 < 8; i3++) {
                if (bArr[i3 + i] != DES_weak_keys[(i2 * 8) + i3]) {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public static void setOddParity(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            bArr[i] = (byte) (((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) & 1) | (b & 254));
        }
    }
}
