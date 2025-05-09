package com.google.common.math;

import androidx.media3.extractor.AacUtil;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import io.sentry.protocol.ViewHierarchyNode;
import java.math.RoundingMode;
import org.bouncycastle.crypto.tls.CipherSuite;

@ElementTypesAreNonnullByDefault
/* loaded from: classes.dex */
public final class IntMath {
    static final int FLOOR_SQRT_MAX_INT = 46340;
    static final int MAX_POWER_OF_SQRT2_UNSIGNED = -1257966797;
    static final int MAX_SIGNED_POWER_OF_TWO = 1073741824;
    static final byte[] maxLog10ForLeadingZeros = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    static final int[] powersOf10 = {1, 10, 100, 1000, 10000, AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND, 1000000, 10000000, 100000000, 1000000000};
    static final int[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    static int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    public static boolean isPowerOfTwo(int x) {
        return (x > 0) & ((x & (x + (-1))) == 0);
    }

    static int lessThanBranchFree(int x, int y) {
        return (~(~(x - y))) >>> 31;
    }

    public static int mean(int x, int y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    public static int ceilingPowerOfTwo(int x) {
        MathPreconditions.checkPositive(ViewHierarchyNode.JsonKeys.X, x);
        if (x > 1073741824) {
            throw new ArithmeticException("ceilingPowerOfTwo(" + x + ") not representable as an int");
        }
        return 1 << (-Integer.numberOfLeadingZeros(x - 1));
    }

    public static int floorPowerOfTwo(int x) {
        MathPreconditions.checkPositive(ViewHierarchyNode.JsonKeys.X, x);
        return Integer.highestOneBit(x);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.IntMath$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static int log2(int x, RoundingMode mode) {
        MathPreconditions.checkPositive(ViewHierarchyNode.JsonKeys.X, x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(x));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(x - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(x);
                return (31 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, x);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(x);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0016. Please report as an issue. */
    public static int log10(int x, RoundingMode mode) {
        int lessThanBranchFree;
        MathPreconditions.checkPositive(ViewHierarchyNode.JsonKeys.X, x);
        int log10Floor = log10Floor(x);
        int i = powersOf10[log10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(x == i);
            case 2:
            case 3:
                return log10Floor;
            case 4:
            case 5:
                lessThanBranchFree = lessThanBranchFree(i, x);
                return log10Floor + lessThanBranchFree;
            case 6:
            case 7:
            case 8:
                lessThanBranchFree = lessThanBranchFree(halfPowersOf10[log10Floor], x);
                return log10Floor + lessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int log10Floor(int x) {
        byte b = maxLog10ForLeadingZeros[Integer.numberOfLeadingZeros(x)];
        return b - lessThanBranchFree(x, powersOf10[b]);
    }

    public static int pow(int b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (b == -2) {
            if (k < 32) {
                return (k & 1) == 0 ? 1 << k : -(1 << k);
            }
            return 0;
        }
        if (b == -1) {
            return (k & 1) == 0 ? 1 : -1;
        }
        if (b == 0) {
            return k == 0 ? 1 : 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            if (k < 32) {
                return 1 << k;
            }
            return 0;
        }
        int i = 1;
        while (k != 0) {
            if (k == 1) {
                return b * i;
            }
            i *= (k & 1) == 0 ? 1 : b;
            b *= b;
            k >>= 1;
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0012. Please report as an issue. */
    public static int sqrt(int x, RoundingMode mode) {
        int lessThanBranchFree;
        MathPreconditions.checkNonNegative(ViewHierarchyNode.JsonKeys.X, x);
        int sqrtFloor = sqrtFloor(x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(sqrtFloor * sqrtFloor == x);
            case 2:
            case 3:
                return sqrtFloor;
            case 4:
            case 5:
                lessThanBranchFree = lessThanBranchFree(sqrtFloor * sqrtFloor, x);
                return sqrtFloor + lessThanBranchFree;
            case 6:
            case 7:
            case 8:
                lessThanBranchFree = lessThanBranchFree((sqrtFloor * sqrtFloor) + sqrtFloor, x);
                return sqrtFloor + lessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int sqrtFloor(int x) {
        return (int) Math.sqrt(x);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
    
        if (((r7 == java.math.RoundingMode.HALF_EVEN) & ((r0 & 1) != 0)) != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0047, code lost:
    
        if (r1 > 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004a, code lost:
    
        if (r5 > 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004d, code lost:
    
        if (r5 < 0) goto L37;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x001c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int divide(int p, int q, RoundingMode mode) {
        Preconditions.checkNotNull(mode);
        if (q == 0) {
            throw new ArithmeticException("/ by zero");
        }
        int i = p / q;
        int i2 = p - (q * i);
        if (i2 == 0) {
            return i;
        }
        int i3 = ((p ^ q) >> 31) | 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(i2 == 0);
                r2 = false;
                return !r2 ? i + i3 : i;
            case 2:
                r2 = false;
                if (!r2) {
                }
                break;
            case 3:
                break;
            case 4:
                if (!r2) {
                }
                break;
            case 5:
                break;
            case 6:
            case 7:
            case 8:
                int abs = Math.abs(i2);
                int abs2 = abs - (Math.abs(q) - abs);
                if (abs2 == 0) {
                    if (mode != RoundingMode.HALF_UP) {
                        break;
                    }
                    if (!r2) {
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public static int mod(int x, int m) {
        if (m <= 0) {
            throw new ArithmeticException("Modulus " + m + " must be > 0");
        }
        int i = x % m;
        return i >= 0 ? i : i + m;
    }

    public static int gcd(int a, int b) {
        MathPreconditions.checkNonNegative("a", a);
        MathPreconditions.checkNonNegative("b", b);
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(a);
        int i = a >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(b);
        int i2 = b >> numberOfTrailingZeros2;
        while (i != i2) {
            int i3 = i - i2;
            int i4 = (i3 >> 31) & i3;
            int i5 = (i3 - i4) - i4;
            i2 += i4;
            i = i5 >> Integer.numberOfTrailingZeros(i5);
        }
        return i << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static int checkedAdd(int a, int b) {
        long j = a + b;
        int i = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i), "checkedAdd", a, b);
        return i;
    }

    public static int checkedSubtract(int a, int b) {
        long j = a - b;
        int i = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i), "checkedSubtract", a, b);
        return i;
    }

    public static int checkedMultiply(int a, int b) {
        long j = a * b;
        int i = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) i), "checkedMultiply", a, b);
        return i;
    }

    public static int checkedPow(int b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (b == -2) {
            MathPreconditions.checkNoOverflow(k < 32, "checkedPow", b, k);
            return (k & 1) == 0 ? 1 << k : (-1) << k;
        }
        if (b == -1) {
            return (k & 1) == 0 ? 1 : -1;
        }
        if (b == 0) {
            return k == 0 ? 1 : 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            MathPreconditions.checkNoOverflow(k < 31, "checkedPow", b, k);
            return 1 << k;
        }
        int i = 1;
        while (k != 0) {
            if (k == 1) {
                return checkedMultiply(i, b);
            }
            if ((k & 1) != 0) {
                i = checkedMultiply(i, b);
            }
            k >>= 1;
            if (k > 0) {
                MathPreconditions.checkNoOverflow((-46340 <= b) & (b <= FLOOR_SQRT_MAX_INT), "checkedPow", b, k);
                b *= b;
            }
        }
        return i;
    }

    public static int saturatedAdd(int a, int b) {
        return Ints.saturatedCast(a + b);
    }

    public static int saturatedSubtract(int a, int b) {
        return Ints.saturatedCast(a - b);
    }

    public static int saturatedMultiply(int a, int b) {
        return Ints.saturatedCast(a * b);
    }

    public static int saturatedPow(int b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (b == -2) {
            return k >= 32 ? (k & 1) + Integer.MAX_VALUE : (k & 1) == 0 ? 1 << k : (-1) << k;
        }
        if (b == -1) {
            return (k & 1) == 0 ? 1 : -1;
        }
        if (b == 0) {
            return k == 0 ? 1 : 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            if (k >= 31) {
                return Integer.MAX_VALUE;
            }
            return 1 << k;
        }
        int i = ((b >>> 31) & k & 1) + Integer.MAX_VALUE;
        int i2 = 1;
        while (k != 0) {
            if (k == 1) {
                return saturatedMultiply(i2, b);
            }
            if ((k & 1) != 0) {
                i2 = saturatedMultiply(i2, b);
            }
            k >>= 1;
            if (k > 0) {
                if ((-46340 > b) || (b > FLOOR_SQRT_MAX_INT)) {
                    return i;
                }
                b *= b;
            }
        }
        return i2;
    }

    public static int factorial(int n) {
        MathPreconditions.checkNonNegative("n", n);
        int[] iArr = factorials;
        if (n < iArr.length) {
            return iArr[n];
        }
        return Integer.MAX_VALUE;
    }

    public static int binomial(int n, int k) {
        MathPreconditions.checkNonNegative("n", n);
        MathPreconditions.checkNonNegative("k", k);
        int i = 0;
        Preconditions.checkArgument(k <= n, "k (%s) > n (%s)", k, n);
        if (k > (n >> 1)) {
            k = n - k;
        }
        int[] iArr = biggestBinomials;
        if (k >= iArr.length || n > iArr[k]) {
            return Integer.MAX_VALUE;
        }
        if (k == 0) {
            return 1;
        }
        if (k == 1) {
            return n;
        }
        long j = 1;
        while (i < k) {
            long j2 = j * (n - i);
            i++;
            j = j2 / i;
        }
        return (int) j;
    }

    public static boolean isPrime(int n) {
        return LongMath.isPrime(n);
    }

    private IntMath() {
    }
}
