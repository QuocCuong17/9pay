package androidx.media3.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SniffFailure;
import java.io.IOException;

/* loaded from: classes.dex */
final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    public static SniffFailure sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    public static SniffFailure sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }

    private static SniffFailure sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        int i;
        int i2;
        boolean z3;
        int[] iArr;
        long length = extractorInput.getLength();
        long j = -1;
        long j2 = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        if (length != -1 && length <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j2 = length;
        }
        int i3 = (int) j2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i4 = 0;
        int i5 = 0;
        boolean z4 = false;
        while (i5 < i3) {
            parsableByteArray.reset(8);
            if (!extractorInput.peekFully(parsableByteArray.getData(), i4, 8, true)) {
                break;
            }
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            int i6 = 16;
            if (readUnsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.getData(), 8, 8);
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j) {
                        readUnsignedInt = (length2 - extractorInput.getPeekPosition()) + 8;
                    }
                }
                i6 = 8;
            }
            long j3 = readUnsignedInt;
            long j4 = i6;
            if (j3 < j4) {
                return new AtomSizeTooSmallSniffFailure(readInt, j3, i6);
            }
            i5 += i6;
            if (readInt == 1836019574) {
                i3 += (int) j3;
                if (length != -1 && i3 > length) {
                    i3 = (int) length;
                }
            } else {
                if (readInt == 1836019558 || readInt == 1836475768) {
                    i = 1;
                    break;
                }
                long j5 = length;
                if (readInt == 1835295092) {
                    z4 = true;
                }
                if ((i5 + j3) - j4 >= i3) {
                    i = 0;
                    break;
                }
                int i7 = (int) (j3 - j4);
                i5 += i7;
                if (readInt != 1718909296) {
                    i2 = 0;
                    if (i7 != 0) {
                        extractorInput.advancePeekPosition(i7);
                    }
                } else {
                    if (i7 < 8) {
                        return new AtomSizeTooSmallSniffFailure(readInt, i7, 8);
                    }
                    parsableByteArray.reset(i7);
                    i2 = 0;
                    extractorInput.peekFully(parsableByteArray.getData(), 0, i7);
                    int readInt2 = parsableByteArray.readInt();
                    if (isCompatibleBrand(readInt2, z2)) {
                        z4 = true;
                    }
                    parsableByteArray.skipBytes(4);
                    int bytesLeft = parsableByteArray.bytesLeft() / 4;
                    if (!z4 && bytesLeft > 0) {
                        iArr = new int[bytesLeft];
                        int i8 = 0;
                        while (true) {
                            if (i8 >= bytesLeft) {
                                z3 = z4;
                                break;
                            }
                            iArr[i8] = parsableByteArray.readInt();
                            if (isCompatibleBrand(iArr[i8], z2)) {
                                z3 = true;
                                break;
                            }
                            i8++;
                        }
                    } else {
                        z3 = z4;
                        iArr = null;
                    }
                    if (!z3) {
                        return new UnsupportedBrandsSniffFailure(readInt2, iArr);
                    }
                    z4 = z3;
                }
                i4 = i2;
                length = j5;
            }
            j = -1;
        }
        i = i4;
        if (!z4) {
            return NoDeclaredBrandSniffFailure.INSTANCE;
        }
        if (z == i) {
            return null;
        }
        if (i != 0) {
            return IncorrectFragmentationSniffFailure.FILE_FRAGMENTED;
        }
        return IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED;
    }

    private static boolean isCompatibleBrand(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
