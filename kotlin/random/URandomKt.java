package kotlin.random;

import androidx.media3.exoplayer.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;

/* compiled from: URandom.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0019\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a/\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0011\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007¢\u0006\u0002\u0010\u0019\u001a\u001b\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a#\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0019\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010 \u001a\u0011\u0010!\u001a\u00020\b*\u00020\rH\u0007¢\u0006\u0002\u0010\"\u001a\u001b\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007¢\u0006\u0004\b#\u0010$\u001a#\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007¢\u0006\u0004\b%\u0010&\u001a\u0019\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007¢\u0006\u0002\u0010(¨\u0006)"}, d2 = {"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", SessionDescription.ATTR_RANGE, "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class URandomKt {
    public static final int nextUInt(Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return UInt.m1297constructorimpl(random.nextInt());
    }

    /* renamed from: nextUInt-qCasIEU, reason: not valid java name */
    public static final int m2423nextUIntqCasIEU(Random nextUInt, int i) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        return m2422nextUInta8DCA5k(nextUInt, 0, i);
    }

    /* renamed from: nextUInt-a8DCA5k, reason: not valid java name */
    public static final int m2422nextUInta8DCA5k(Random nextUInt, int i, int i2) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        m2417checkUIntRangeBoundsJ1ME1BU(i, i2);
        return UInt.m1297constructorimpl(nextUInt.nextInt(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
    }

    public static final int nextUInt(Random random, UIntRange range) {
        int compare;
        int compare2;
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        compare = Integer.compare(range.getLast() ^ Integer.MIN_VALUE, (-1) ^ Integer.MIN_VALUE);
        if (compare < 0) {
            return m2422nextUInta8DCA5k(random, range.getFirst(), UInt.m1297constructorimpl(range.getLast() + 1));
        }
        compare2 = Integer.compare(range.getFirst() ^ Integer.MIN_VALUE, 0 ^ Integer.MIN_VALUE);
        return compare2 > 0 ? UInt.m1297constructorimpl(m2422nextUInta8DCA5k(random, UInt.m1297constructorimpl(range.getFirst() - 1), range.getLast()) + 1) : nextUInt(random);
    }

    public static final long nextULong(Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return ULong.m1376constructorimpl(random.nextLong());
    }

    /* renamed from: nextULong-V1Xi4fY, reason: not valid java name */
    public static final long m2424nextULongV1Xi4fY(Random nextULong, long j) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        return m2425nextULongjmpaWc(nextULong, 0L, j);
    }

    /* renamed from: nextULong-jmpaW-c, reason: not valid java name */
    public static final long m2425nextULongjmpaWc(Random nextULong, long j, long j2) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        m2418checkULongRangeBoundseb3DHEI(j, j2);
        return ULong.m1376constructorimpl(nextULong.nextLong(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
    }

    public static final long nextULong(Random random, ULongRange range) {
        int compare;
        int compare2;
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        compare = Long.compare(range.getLast() ^ Long.MIN_VALUE, (-1) ^ Long.MIN_VALUE);
        if (compare < 0) {
            return m2425nextULongjmpaWc(random, range.getFirst(), ULong.m1376constructorimpl(range.getLast() + ULong.m1376constructorimpl(1 & 4294967295L)));
        }
        compare2 = Long.compare(range.getFirst() ^ Long.MIN_VALUE, 0 ^ Long.MIN_VALUE);
        if (compare2 <= 0) {
            return nextULong(random);
        }
        long j = 1 & 4294967295L;
        return ULong.m1376constructorimpl(m2425nextULongjmpaWc(random, ULong.m1376constructorimpl(range.getFirst() - ULong.m1376constructorimpl(j)), range.getLast()) + ULong.m1376constructorimpl(j));
    }

    /* renamed from: nextUBytes-EVgfTAA, reason: not valid java name */
    public static final byte[] m2419nextUBytesEVgfTAA(Random nextUBytes, byte[] array) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array);
        return array;
    }

    public static final byte[] nextUBytes(Random random, int i) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return UByteArray.m1273constructorimpl(random.nextBytes(i));
    }

    /* renamed from: nextUBytes-Wvrt4B4$default, reason: not valid java name */
    public static /* synthetic */ byte[] m2421nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m1279getSizeimpl(bArr);
        }
        return m2420nextUBytesWvrt4B4(random, bArr, i, i2);
    }

    /* renamed from: nextUBytes-Wvrt4B4, reason: not valid java name */
    public static final byte[] m2420nextUBytesWvrt4B4(Random nextUBytes, byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array, i, i2);
        return array;
    }

    /* renamed from: checkUIntRangeBounds-J1ME1BU, reason: not valid java name */
    public static final void m2417checkUIntRangeBoundsJ1ME1BU(int i, int i2) {
        int compare;
        compare = Integer.compare(i2 ^ Integer.MIN_VALUE, i ^ Integer.MIN_VALUE);
        if (!(compare > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m1291boximpl(i), UInt.m1291boximpl(i2)).toString());
        }
    }

    /* renamed from: checkULongRangeBounds-eb3DHEI, reason: not valid java name */
    public static final void m2418checkULongRangeBoundseb3DHEI(long j, long j2) {
        int compare;
        compare = Long.compare(j2 ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE);
        if (!(compare > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m1370boximpl(j), ULong.m1370boximpl(j2)).toString());
        }
    }
}
