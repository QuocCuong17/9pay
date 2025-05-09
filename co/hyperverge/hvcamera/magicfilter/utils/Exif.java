package co.hyperverge.hvcamera.magicfilter.utils;

import co.hyperverge.hvcamera.HVLog;

/* loaded from: classes2.dex */
public class Exif {
    private static final String TAG = "Exif";

    /* JADX WARN: Code restructure failed: missing block: B:80:0x0077, code lost:
    
        co.hyperverge.hvcamera.HVLog.e(co.hyperverge.hvcamera.magicfilter.utils.Exif.TAG, "getOrientation: Invalid length");
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x007c, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getOrientation(byte[] bArr) {
        int i;
        int i2;
        int i3;
        HVLog.d(TAG, "getOrientation() called with: jpeg = [" + bArr + "]");
        if (bArr == null) {
            return 0;
        }
        int i4 = 0;
        while (true) {
            if (i4 + 3 >= bArr.length) {
                i = 0;
                break;
            }
            i2 = i4 + 1;
            if ((bArr[i4] & 255) != 255) {
                break;
            }
            int i5 = bArr[i2] & 255;
            if (i5 != 255) {
                i2++;
                if (i5 != 216 && i5 != 1) {
                    if (i5 != 217 && i5 != 218) {
                        int pack = pack(bArr, i2, 2, false);
                        if (pack >= 2 && (i3 = i2 + pack) <= bArr.length) {
                            if (i5 == 225 && pack >= 8 && pack(bArr, i2 + 2, 4, false) == 1165519206 && pack(bArr, i2 + 6, 2, false) == 0) {
                                i4 = i2 + 8;
                                i = pack - 8;
                                break;
                            }
                            i4 = i3;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            i4 = i2;
        }
        i = 0;
        i4 = i2;
        if (i > 8) {
            int pack2 = pack(bArr, i4, 4, false);
            if (pack2 != 1229531648 && pack2 != 1296891946) {
                HVLog.e(TAG, "getOrientation: Invalid byte order");
                return 0;
            }
            boolean z = pack2 == 1229531648;
            int pack3 = pack(bArr, i4 + 4, 4, z) + 2;
            if (pack3 < 10 || pack3 > i) {
                HVLog.e(TAG, "getOrientation: Invalid offset");
                return 0;
            }
            int i6 = i4 + pack3;
            int i7 = i - pack3;
            int pack4 = pack(bArr, i6 - 2, 2, z);
            while (true) {
                int i8 = pack4 - 1;
                if (pack4 <= 0 || i7 < 12) {
                    break;
                }
                if (pack(bArr, i6, 2, z) == 274) {
                    int pack5 = pack(bArr, i6 + 8, 2, z);
                    if (pack5 == 1) {
                        return 1;
                    }
                    int i9 = 3;
                    if (pack5 != 3) {
                        i9 = 6;
                        if (pack5 != 6) {
                            if (pack5 == 8) {
                                return 8;
                            }
                            HVLog.d(TAG, "Unsupported orientation");
                            return 0;
                        }
                    }
                    return i9;
                }
                i6 += 12;
                i7 -= 12;
                pack4 = i8;
            }
        }
        HVLog.d(TAG, "Orientation not found");
        return 0;
    }

    private static int pack(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        HVLog.d(TAG, "pack() called with: bytes = [" + bArr + "], offset = [" + i + "], length = [" + i2 + "], littleEndian = [" + z + "]");
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                return i4;
            }
            i4 = (bArr[i] & 255) | (i4 << 8);
            i += i3;
            i2 = i5;
        }
    }
}
