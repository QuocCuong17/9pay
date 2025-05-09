package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes4.dex */
public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        boolean z = false;
        int i = 0;
        int i2 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                iArr[i] = iArr[i] + 1;
            } else {
                if (i == 5) {
                    float f = MAX_AVG_VARIANCE;
                    int i3 = -1;
                    for (int i4 = 103; i4 <= 105; i4++) {
                        float patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i4], 0.7f);
                        if (patternMatchVariance < f) {
                            i3 = i4;
                            f = patternMatchVariance;
                        }
                    }
                    if (i3 >= 0 && bitArray.isRange(Math.max(0, i2 - ((nextSet - i2) / 2)), i2, false)) {
                        return new int[]{i2, nextSet, i3};
                    }
                    i2 += iArr[0] + iArr[1];
                    int i5 = i - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i] = 0;
                    i--;
                } else {
                    i++;
                }
                iArr[i] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i3 >= iArr2.length) {
                break;
            }
            float patternMatchVariance = patternMatchVariance(iArr, iArr2[i3], 0.7f);
            if (patternMatchVariance < f) {
                i2 = i3;
                f = patternMatchVariance;
            }
            i3++;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0151, code lost:
    
        if (r3 != false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00f4, code lost:
    
        if (r3 != false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0153, code lost:
    
        r2 = false;
        r3 = false;
        r5 = false;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0089. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:50:0x00c5. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:84:0x011c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:73:0x015a A[PHI: r21
      0x015a: PHI (r21v10 boolean) = (r21v5 boolean), (r21v15 boolean) binds: [B:84:0x011c, B:50:0x00c5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0162 A[PHI: r21
      0x0162: PHI (r21v9 boolean) = (r21v5 boolean), (r21v15 boolean) binds: [B:84:0x011c, B:50:0x00c5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x011f A[FALL_THROUGH, PHI: r21 r22
      0x011f: PHI (r21v8 boolean) = 
      (r21v5 boolean)
      (r21v5 boolean)
      (r21v5 boolean)
      (r21v5 boolean)
      (r21v15 boolean)
      (r21v15 boolean)
      (r21v15 boolean)
      (r21v15 boolean)
     binds: [B:84:0x011c, B:88:0x0133, B:92:0x013f, B:91:0x013b, B:50:0x00c5, B:54:0x00db, B:58:0x00e7, B:57:0x00e3] A[DONT_GENERATE, DONT_INLINE]
      0x011f: PHI (r22v8 int) = (r22v1 int), (r22v6 int), (r22v6 int), (r22v6 int), (r22v1 int), (r22v12 int), (r22v12 int), (r22v12 int) binds: [B:84:0x011c, B:88:0x0133, B:92:0x013f, B:91:0x013b, B:50:0x00c5, B:54:0x00db, B:58:0x00e7, B:57:0x00e3] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.zxing.oned.OneDReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        char c;
        char c2;
        boolean z;
        boolean z2;
        boolean z3 = true;
        boolean z4 = false;
        boolean z5 = map != null && map.containsKey(DecodeHintType.ASSUME_GS1);
        int[] findStartPattern = findStartPattern(bitArray);
        int i2 = findStartPattern[2];
        ArrayList arrayList = new ArrayList(20);
        arrayList.add(Byte.valueOf((byte) i2));
        switch (i2) {
            case 103:
                c = 'e';
                break;
            case 104:
                c = 'd';
                break;
            case 105:
                c = 'c';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        StringBuilder sb = new StringBuilder(20);
        int i3 = 6;
        int[] iArr = new int[6];
        boolean z6 = true;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        char c3 = c;
        int i8 = findStartPattern[0];
        int i9 = findStartPattern[1];
        char c4 = c3;
        while (!z8) {
            int decodeCode = decodeCode(bitArray, iArr, i9);
            arrayList.add(Byte.valueOf((byte) decodeCode));
            if (decodeCode != 106) {
                z6 = z3;
            }
            if (decodeCode != 106) {
                i5++;
                i2 += i5 * decodeCode;
            }
            int i10 = i9;
            for (int i11 = 0; i11 < i3; i11++) {
                i10 += iArr[i11];
            }
            switch (decodeCode) {
                case 103:
                case 104:
                case 105:
                    throw FormatException.getFormatInstance();
                default:
                    switch (c4) {
                        case 'c':
                            c2 = 'd';
                            if (decodeCode >= 100) {
                                if (decodeCode != 106) {
                                    z6 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 100:
                                            c4 = 'd';
                                            break;
                                        case 101:
                                            z = false;
                                            c4 = 'e';
                                            break;
                                        case 102:
                                            if (sb.length() == 0) {
                                                i7 = 1;
                                            } else if (sb.length() == 1) {
                                                i7 = 2;
                                            }
                                            if (z5) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                    break;
                                                } else {
                                                    sb.append((char) 29);
                                                    break;
                                                }
                                            }
                                            break;
                                    }
                                } else {
                                    z = false;
                                    z8 = true;
                                    break;
                                }
                            } else {
                                if (decodeCode < 10) {
                                    sb.append('0');
                                }
                                sb.append(decodeCode);
                            }
                            z = false;
                            break;
                        case 'd':
                            if (decodeCode < 96) {
                                if (z4 == z7) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                                c2 = 'd';
                                z4 = false;
                                z = false;
                                break;
                            } else {
                                if (decodeCode != 106) {
                                    z6 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 97:
                                            z2 = false;
                                            i7 = 4;
                                            break;
                                        case 98:
                                            z2 = true;
                                            c4 = 'e';
                                            break;
                                        case 99:
                                            z2 = false;
                                            c4 = 'c';
                                            break;
                                        case 100:
                                            if (z7 || !z4) {
                                                if (z7) {
                                                }
                                                z2 = false;
                                                z4 = true;
                                                break;
                                            }
                                            z2 = false;
                                            z4 = false;
                                            z7 = true;
                                            break;
                                        case 101:
                                            z2 = false;
                                            c4 = 'e';
                                            break;
                                        case 102:
                                            if (sb.length() == 0) {
                                                i7 = 1;
                                            } else if (sb.length() == 1) {
                                                i7 = 2;
                                            }
                                            if (z5) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                } else {
                                                    sb.append((char) 29);
                                                }
                                            }
                                        default:
                                            z2 = false;
                                            break;
                                    }
                                    z = z2;
                                    c2 = 'd';
                                    break;
                                }
                                z2 = false;
                                z8 = true;
                                z = z2;
                                c2 = 'd';
                            }
                            break;
                        case 'e':
                            if (decodeCode < 64) {
                                if (z4 == z7) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                            } else if (decodeCode >= 96) {
                                if (decodeCode != 106) {
                                    z6 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 98:
                                            z2 = true;
                                            c4 = 'd';
                                            break;
                                        case 100:
                                            z2 = false;
                                            c4 = 'd';
                                            break;
                                        case 101:
                                            if (z7 || !z4) {
                                                if (z7) {
                                                }
                                                z2 = false;
                                                z4 = true;
                                                break;
                                            }
                                            z2 = false;
                                            z4 = false;
                                            z7 = true;
                                            break;
                                        case 102:
                                            if (sb.length() == 0) {
                                                i7 = 1;
                                            } else if (sb.length() == 1) {
                                                i7 = 2;
                                            }
                                            if (z5) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                } else {
                                                    sb.append((char) 29);
                                                }
                                            }
                                    }
                                    z = z2;
                                    c2 = 'd';
                                    break;
                                }
                                z2 = false;
                                z8 = true;
                                z = z2;
                                c2 = 'd';
                            } else if (z4 == z7) {
                                sb.append((char) (decodeCode - 64));
                            } else {
                                sb.append((char) (decodeCode + 64));
                            }
                            c2 = 'd';
                            z4 = false;
                            z = false;
                            break;
                        default:
                            c2 = 'd';
                            z = false;
                            break;
                    }
                    if (z9) {
                        c4 = c4 == 'e' ? c2 : 'e';
                    }
                    z9 = z;
                    z3 = true;
                    i3 = 6;
                    i8 = i9;
                    i9 = i10;
                    i6 = i4;
                    i4 = decodeCode;
                    break;
            }
        }
        int i12 = i9 - i8;
        int nextUnset = bitArray.getNextUnset(i9);
        if (!bitArray.isRange(nextUnset, Math.min(bitArray.getSize(), ((nextUnset - i8) / 2) + nextUnset), false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i13 = i6;
        if ((i2 - (i5 * i13)) % 103 != i13) {
            throw ChecksumException.getChecksumInstance();
        }
        int length = sb.length();
        if (length == 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (length > 0 && z6) {
            if (c4 == 'c') {
                sb.delete(length - 2, length);
            } else {
                sb.delete(length - 1, length);
            }
        }
        float f = (findStartPattern[1] + findStartPattern[0]) / 2.0f;
        float f2 = i8 + (i12 / 2.0f);
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        for (int i14 = 0; i14 < size; i14++) {
            bArr[i14] = ((Byte) arrayList.get(i14)).byteValue();
        }
        float f3 = i;
        Result result = new Result(sb.toString(), bArr, new ResultPoint[]{new ResultPoint(f, f3), new ResultPoint(f2, f3)}, BarcodeFormat.CODE_128);
        result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]C" + i7);
        return result;
    }
}
