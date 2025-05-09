package com.google.android.gms.internal.clearcut;

import androidx.media3.extractor.ts.PsExtractor;
import java.nio.ByteBuffer;
import net.sf.scuba.smartcards.ISO7816;

/* loaded from: classes3.dex */
final class zzfj extends zzfg {
    private static int zza(byte[] bArr, int i, long j, int i2) {
        int zzam;
        int zzp;
        int zzd;
        if (i2 == 0) {
            zzam = zzff.zzam(i);
            return zzam;
        }
        if (i2 == 1) {
            zzp = zzff.zzp(i, zzfd.zza(bArr, j));
            return zzp;
        }
        if (i2 != 2) {
            throw new AssertionError();
        }
        zzd = zzff.zzd(i, zzfd.zza(bArr, j), zzfd.zza(bArr, j + 1));
        return zzd;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ba, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0065, code lost:
    
        return -1;
     */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final int zzb(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if ((i2 | i3 | (bArr.length - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        long j = i2;
        int i5 = (int) (i3 - j);
        if (i5 >= 16) {
            i4 = 0;
            long j2 = j;
            while (true) {
                if (i4 >= i5) {
                    i4 = i5;
                    break;
                }
                long j3 = j2 + 1;
                if (zzfd.zza(bArr, j2) < 0) {
                    break;
                }
                i4++;
                j2 = j3;
            }
        } else {
            i4 = 0;
        }
        int i6 = i5 - i4;
        long j4 = j + i4;
        while (true) {
            byte b = 0;
            while (true) {
                if (i6 <= 0) {
                    break;
                }
                long j5 = j4 + 1;
                byte zza = zzfd.zza(bArr, j4);
                if (zza < 0) {
                    b = zza;
                    j4 = j5;
                    break;
                }
                i6--;
                b = zza;
                j4 = j5;
            }
            if (i6 != 0) {
                int i7 = i6 - 1;
                if (b >= -32) {
                    if (b >= -16) {
                        if (i7 >= 3) {
                            i6 = i7 - 3;
                            long j6 = j4 + 1;
                            byte zza2 = zzfd.zza(bArr, j4);
                            if (zza2 > -65 || (((b << 28) + (zza2 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) != 0) {
                                break;
                            }
                            long j7 = j6 + 1;
                            if (zzfd.zza(bArr, j6) > -65) {
                                break;
                            }
                            j4 = j7 + 1;
                            if (zzfd.zza(bArr, j7) > -65) {
                                break;
                            }
                        } else {
                            return zza(bArr, b, j4, i7);
                        }
                    } else if (i7 >= 2) {
                        i6 = i7 - 2;
                        long j8 = j4 + 1;
                        byte zza3 = zzfd.zza(bArr, j4);
                        if (zza3 > -65 || ((b == -32 && zza3 < -96) || (b == -19 && zza3 >= -96))) {
                            break;
                        }
                        j4 = j8 + 1;
                        if (zzfd.zza(bArr, j8) > -65) {
                            break;
                        }
                    } else {
                        return zza(bArr, b, j4, i7);
                    }
                } else if (i7 != 0) {
                    i6 = i7 - 1;
                    if (b < -62) {
                        break;
                    }
                    long j9 = j4 + 1;
                    if (zzfd.zza(bArr, j4) > -65) {
                        break;
                    }
                    j4 = j9;
                } else {
                    return b;
                }
            } else {
                return 0;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        long j2;
        long j3;
        char c2;
        int i3;
        char charAt;
        long j4 = i;
        long j5 = i2 + j4;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzfd.zza(bArr, j4, (byte) charAt);
            i4++;
            j4 = 1 + j4;
        }
        if (i4 == length) {
            return (int) j4;
        }
        while (i4 < length) {
            char charAt3 = charSequence.charAt(i4);
            if (charAt3 < c && j4 < j5) {
                long j6 = j4 + j;
                zzfd.zza(bArr, j4, (byte) charAt3);
                j3 = j;
                j2 = j6;
                c2 = c;
            } else if (charAt3 < 2048 && j4 <= j5 - 2) {
                long j7 = j4 + j;
                zzfd.zza(bArr, j4, (byte) ((charAt3 >>> 6) | 960));
                long j8 = j7 + j;
                zzfd.zza(bArr, j7, (byte) ((charAt3 & '?') | 128));
                long j9 = j;
                c2 = 128;
                j2 = j8;
                j3 = j9;
            } else {
                if ((charAt3 >= 55296 && 57343 >= charAt3) || j4 > j5 - 3) {
                    if (j4 > j5 - 4) {
                        if (55296 <= charAt3 && charAt3 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                            throw new zzfi(i4, length);
                        }
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(charAt3);
                        sb2.append(" at index ");
                        sb2.append(j4);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    }
                    int i5 = i4 + 1;
                    if (i5 != length) {
                        char charAt4 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            long j10 = j4 + 1;
                            zzfd.zza(bArr, j4, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                            long j11 = j10 + 1;
                            c2 = 128;
                            zzfd.zza(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j12 = j11 + 1;
                            zzfd.zza(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                            j3 = 1;
                            j2 = j12 + 1;
                            zzfd.zza(bArr, j12, (byte) ((codePoint & 63) | 128));
                            i4 = i5;
                        } else {
                            i4 = i5;
                        }
                    }
                    throw new zzfi(i4 - 1, length);
                }
                long j13 = j4 + j;
                zzfd.zza(bArr, j4, (byte) ((charAt3 >>> '\f') | 480));
                long j14 = j13 + j;
                zzfd.zza(bArr, j13, (byte) (((charAt3 >>> 6) & 63) | 128));
                zzfd.zza(bArr, j14, (byte) ((charAt3 & '?') | 128));
                j2 = j14 + 1;
                j3 = 1;
                c2 = 128;
            }
            i4++;
            c = c2;
            long j15 = j3;
            j4 = j2;
            j = j15;
        }
        return (int) j4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        char c;
        int i;
        long j;
        int i2;
        int i3;
        long j2;
        char c2;
        char charAt;
        ByteBuffer byteBuffer2 = byteBuffer;
        long zzb = zzfd.zzb(byteBuffer);
        long position = byteBuffer.position() + zzb;
        long limit = byteBuffer.limit() + zzb;
        int length = charSequence.length();
        if (length > limit - position) {
            char charAt2 = charSequence.charAt(length - 1);
            int limit2 = byteBuffer.limit();
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(limit2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzfd.zza(position, (byte) charAt);
            i4++;
            position++;
        }
        if (i4 == length) {
            i = (int) (position - zzb);
        } else {
            while (i4 < length) {
                char charAt3 = charSequence.charAt(i4);
                if (charAt3 >= c || position >= limit) {
                    if (charAt3 >= 2048 || position > limit - 2) {
                        j = zzb;
                        if ((charAt3 >= 55296 && 57343 >= charAt3) || position > limit - 3) {
                            if (position > limit - 4) {
                                if (55296 <= charAt3 && charAt3 <= 57343 && ((i2 = i4 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(i2)))) {
                                    throw new zzfi(i4, length);
                                }
                                StringBuilder sb2 = new StringBuilder(46);
                                sb2.append("Failed writing ");
                                sb2.append(charAt3);
                                sb2.append(" at index ");
                                sb2.append(position);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            }
                            i3 = i4 + 1;
                            if (i3 != length) {
                                char charAt4 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(charAt3, charAt4)) {
                                    int codePoint = Character.toCodePoint(charAt3, charAt4);
                                    j2 = limit;
                                    long j3 = position + 1;
                                    zzfd.zza(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                    long j4 = j3 + 1;
                                    c2 = 128;
                                    zzfd.zza(j3, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j5 = j4 + 1;
                                    zzfd.zza(j4, (byte) (((codePoint >>> 6) & 63) | 128));
                                    zzfd.zza(j5, (byte) ((codePoint & 63) | 128));
                                    position = j5 + 1;
                                } else {
                                    i4 = i3;
                                }
                            }
                            throw new zzfi(i4 - 1, length);
                        }
                        long j6 = position + 1;
                        zzfd.zza(position, (byte) ((charAt3 >>> '\f') | 480));
                        long j7 = j6 + 1;
                        zzfd.zza(j6, (byte) (((charAt3 >>> 6) & 63) | 128));
                        zzfd.zza(j7, (byte) ((charAt3 & '?') | 128));
                        position = j7 + 1;
                    } else {
                        j = zzb;
                        long j8 = position + 1;
                        zzfd.zza(position, (byte) ((charAt3 >>> 6) | 960));
                        zzfd.zza(j8, (byte) ((charAt3 & '?') | 128));
                        position = j8 + 1;
                    }
                    j2 = limit;
                    i3 = i4;
                    c2 = 128;
                } else {
                    zzfd.zza(position, (byte) charAt3);
                    j2 = limit;
                    i3 = i4;
                    c2 = c;
                    position++;
                    j = zzb;
                }
                c = c2;
                zzb = j;
                limit = j2;
                i4 = i3 + 1;
            }
            i = (int) (position - zzb);
            byteBuffer2 = byteBuffer;
        }
        byteBuffer2.position(i);
    }
}
