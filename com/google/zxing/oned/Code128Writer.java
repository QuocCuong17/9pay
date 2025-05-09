package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 241;
    private static final char ESCAPE_FNC_2 = 242;
    private static final char ESCAPE_FNC_3 = 243;
    private static final char ESCAPE_FNC_4 = 244;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    protected Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_128);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        return encode(str, null);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    protected boolean[] encode(String str, Map<EncodeHintType, ?> map) {
        return map != null && map.containsKey(EncodeHintType.CODE128_COMPACT) && Boolean.parseBoolean(map.get(EncodeHintType.CODE128_COMPACT).toString()) ? new MinimalEncoder(null).encode(str) : encodeFast(str, check(str, map));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003e, code lost:
    
        if (r6.equals("B") == false) goto L12;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:30:0x0075. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int check(String str, Map<EncodeHintType, ?> map) {
        int length = str.length();
        char c = 1;
        if (length < 1 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        int i = -1;
        if (map != null && map.containsKey(EncodeHintType.FORCE_CODE_SET)) {
            String obj = map.get(EncodeHintType.FORCE_CODE_SET).toString();
            obj.hashCode();
            switch (obj.hashCode()) {
                case 65:
                    if (obj.equals("A")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 66:
                    break;
                case 67:
                    if (obj.equals("C")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    i = 101;
                    break;
                case 1:
                    i = 100;
                    break;
                case 2:
                    i = 99;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported code set hint: " + obj);
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            switch (charAt) {
                default:
                    if (charAt > 127) {
                        throw new IllegalArgumentException("Bad character in input: ASCII value=" + ((int) charAt));
                    }
                case 241:
                case 242:
                case 243:
                case 244:
                    switch (i) {
                        case 99:
                            if (charAt < '0' || ((charAt > '9' && charAt <= 127) || charAt == 242 || charAt == 243 || charAt == 244)) {
                                throw new IllegalArgumentException("Bad character in input for forced code set C: ASCII value=" + ((int) charAt));
                            }
                            break;
                        case 100:
                            if (charAt <= ' ') {
                                throw new IllegalArgumentException("Bad character in input for forced code set B: ASCII value=" + ((int) charAt));
                            }
                            break;
                        case 101:
                            if (charAt > '_' && charAt <= 127) {
                                throw new IllegalArgumentException("Bad character in input for forced code set A: ASCII value=" + ((int) charAt));
                            }
                            break;
                    }
                    break;
            }
        }
        return i;
    }

    private static boolean[] encodeFast(String str, int i) {
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i2 < length) {
            int chooseCode = i == -1 ? chooseCode(str, i2, i4) : i;
            int i6 = 100;
            if (chooseCode == i4) {
                switch (str.charAt(i2)) {
                    case 241:
                        i6 = 102;
                        break;
                    case 242:
                        i6 = 97;
                        break;
                    case 243:
                        i6 = 96;
                        break;
                    case 244:
                        if (i4 == 101) {
                            i6 = 101;
                            break;
                        }
                        break;
                    default:
                        if (i4 == 100) {
                            i6 = str.charAt(i2) - ' ';
                            break;
                        } else if (i4 == 101) {
                            i6 = str.charAt(i2) - ' ';
                            if (i6 < 0) {
                                i6 += 96;
                                break;
                            }
                        } else {
                            int i7 = i2 + 1;
                            if (i7 == length) {
                                throw new IllegalArgumentException("Bad number of characters for digit only encoding.");
                            }
                            i6 = Integer.parseInt(str.substring(i2, i2 + 2));
                            i2 = i7;
                            break;
                        }
                        break;
                }
                i2++;
            } else {
                if (i4 == 0) {
                    i6 = chooseCode != 100 ? chooseCode != 101 ? 105 : 103 : 104;
                } else {
                    i6 = chooseCode;
                }
                i4 = chooseCode;
            }
            arrayList.add(Code128Reader.CODE_PATTERNS[i6]);
            i3 += i6 * i5;
            if (i2 != 0) {
                i5++;
            }
        }
        return produceResult(arrayList, i3);
    }

    static boolean[] produceResult(Collection<int[]> collection, int i) {
        collection.add(Code128Reader.CODE_PATTERNS[i % 103]);
        collection.add(Code128Reader.CODE_PATTERNS[106]);
        int i2 = 0;
        int i3 = 0;
        for (int[] iArr : collection) {
            for (int i4 : iArr) {
                i3 += i4;
            }
        }
        boolean[] zArr = new boolean[i3];
        Iterator<int[]> it = collection.iterator();
        while (it.hasNext()) {
            i2 += appendPattern(zArr, i2, it.next(), true);
        }
        return zArr;
    }

    private static CType findCType(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return CType.UNCODABLE;
        }
        char charAt = charSequence.charAt(i);
        if (charAt == 241) {
            return CType.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return CType.UNCODABLE;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return CType.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i2);
        if (charAt2 < '0' || charAt2 > '9') {
            return CType.ONE_DIGIT;
        }
        return CType.TWO_DIGITS;
    }

    private static int chooseCode(CharSequence charSequence, int i, int i2) {
        CType findCType;
        CType findCType2;
        char charAt;
        CType findCType3 = findCType(charSequence, i);
        if (findCType3 == CType.ONE_DIGIT) {
            return i2 == 101 ? 101 : 100;
        }
        if (findCType3 == CType.UNCODABLE) {
            return (i >= charSequence.length() || ((charAt = charSequence.charAt(i)) >= ' ' && (i2 != 101 || (charAt >= '`' && (charAt < 241 || charAt > 244))))) ? 100 : 101;
        }
        if (i2 == 101 && findCType3 == CType.FNC_1) {
            return 101;
        }
        if (i2 == 99) {
            return 99;
        }
        if (i2 == 100) {
            if (findCType3 == CType.FNC_1 || (findCType = findCType(charSequence, i + 2)) == CType.UNCODABLE || findCType == CType.ONE_DIGIT) {
                return 100;
            }
            if (findCType == CType.FNC_1) {
                return findCType(charSequence, i + 3) == CType.TWO_DIGITS ? 99 : 100;
            }
            int i3 = i + 4;
            while (true) {
                findCType2 = findCType(charSequence, i3);
                if (findCType2 != CType.TWO_DIGITS) {
                    break;
                }
                i3 += 2;
            }
            return findCType2 == CType.ONE_DIGIT ? 100 : 99;
        }
        if (findCType3 == CType.FNC_1) {
            findCType3 = findCType(charSequence, i + 1);
        }
        return findCType3 == CType.TWO_DIGITS ? 99 : 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class MinimalEncoder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final String A = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fÿ";
        static final String B = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u007fÿ";
        private static final int CODE_SHIFT = 98;
        private int[][] memoizedCost;
        private Latch[][] minPath;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public enum Charset {
            A,
            B,
            C,
            NONE
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public enum Latch {
            A,
            B,
            C,
            SHIFT,
            NONE
        }

        private static boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        private MinimalEncoder() {
        }

        /* synthetic */ MinimalEncoder(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:12:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00bb  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean[] encode(String str) {
            Charset charset;
            int i = 2;
            this.memoizedCost = (int[][]) Array.newInstance((Class<?>) int.class, 4, str.length());
            this.minPath = (Latch[][]) Array.newInstance((Class<?>) Latch.class, 4, str.length());
            encode(str, Charset.NONE, 0);
            ArrayList arrayList = new ArrayList();
            int[] iArr = {0};
            int[] iArr2 = {1};
            int length = str.length();
            Charset charset2 = Charset.NONE;
            int i2 = 0;
            while (i2 < length) {
                Latch latch = this.minPath[charset2.ordinal()][i2];
                int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch[latch.ordinal()];
                int i4 = 100;
                if (i3 == 1) {
                    charset = Charset.A;
                    addPattern(arrayList, i2 == 0 ? 103 : 101, iArr, iArr2, i2);
                } else if (i3 == i) {
                    charset = Charset.B;
                    addPattern(arrayList, i2 == 0 ? 104 : 100, iArr, iArr2, i2);
                } else if (i3 == 3) {
                    charset = Charset.C;
                    addPattern(arrayList, i2 == 0 ? 105 : 99, iArr, iArr2, i2);
                } else {
                    if (i3 == 4) {
                        addPattern(arrayList, 98, iArr, iArr2, i2);
                    }
                    if (charset2 != Charset.C) {
                        if (str.charAt(i2) == 241) {
                            addPattern(arrayList, 102, iArr, iArr2, i2);
                        } else {
                            addPattern(arrayList, Integer.parseInt(str.substring(i2, i2 + 2)), iArr, iArr2, i2);
                            int i5 = i2 + 1;
                            if (i5 < length) {
                                i2 = i5;
                            }
                        }
                    } else {
                        switch (str.charAt(i2)) {
                            case 241:
                                i4 = 102;
                                break;
                            case 242:
                                i4 = 97;
                                break;
                            case 243:
                                i4 = 96;
                                break;
                            case 244:
                                if ((charset2 == Charset.A && latch != Latch.SHIFT) || (charset2 == Charset.B && latch == Latch.SHIFT)) {
                                    i4 = 101;
                                    break;
                                }
                                break;
                            default:
                                i4 = str.charAt(i2) - ' ';
                                break;
                        }
                        if (((charset2 == Charset.A && latch != Latch.SHIFT) || (charset2 == Charset.B && latch == Latch.SHIFT)) && i4 < 0) {
                            i4 += 96;
                        }
                        addPattern(arrayList, i4, iArr, iArr2, i2);
                    }
                    i2++;
                    i = 2;
                }
                charset2 = charset;
                if (charset2 != Charset.C) {
                }
                i2++;
                i = 2;
            }
            this.memoizedCost = null;
            this.minPath = null;
            return Code128Writer.produceResult(arrayList, iArr[0]);
        }

        private static void addPattern(Collection<int[]> collection, int i, int[] iArr, int[] iArr2, int i2) {
            collection.add(Code128Reader.CODE_PATTERNS[i]);
            if (i2 != 0) {
                iArr2[0] = iArr2[0] + 1;
            }
            iArr[0] = iArr[0] + (i * iArr2[0]);
        }

        private boolean canEncode(CharSequence charSequence, Charset charset, int i) {
            int i2;
            char charAt = charSequence.charAt(i);
            int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset[charset.ordinal()];
            if (i3 == 1) {
                return charAt == 241 || charAt == 242 || charAt == 243 || charAt == 244 || A.indexOf(charAt) >= 0;
            }
            if (i3 == 2) {
                return charAt == 241 || charAt == 242 || charAt == 243 || charAt == 244 || B.indexOf(charAt) >= 0;
            }
            if (i3 != 3) {
                return false;
            }
            return charAt == 241 || ((i2 = i + 1) < charSequence.length() && isDigit(charAt) && isDigit(charSequence.charAt(i2)));
        }

        private int encode(CharSequence charSequence, Charset charset, int i) {
            int i2;
            int i3;
            int i4 = this.memoizedCost[charset.ordinal()][i];
            if (i4 > 0) {
                return i4;
            }
            Latch latch = Latch.NONE;
            int i5 = i + 1;
            boolean z = i5 >= charSequence.length();
            Charset[] charsetArr = {Charset.A, Charset.B};
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 <= 1; i7++) {
                if (canEncode(charSequence, charsetArr[i7], i)) {
                    Latch latch2 = Latch.NONE;
                    if (charset != charsetArr[i7]) {
                        latch2 = Latch.valueOf(charsetArr[i7].toString());
                        i3 = 2;
                    } else {
                        i3 = 1;
                    }
                    if (!z) {
                        i3 += encode(charSequence, charsetArr[i7], i5);
                    }
                    if (i3 < i6) {
                        latch = latch2;
                        i6 = i3;
                    }
                    if (charset == charsetArr[(i7 + 1) % 2]) {
                        Latch latch3 = Latch.SHIFT;
                        int encode = !z ? encode(charSequence, charset, i5) + 2 : 2;
                        if (encode < i6) {
                            latch = latch3;
                            i6 = encode;
                        }
                    }
                }
            }
            if (canEncode(charSequence, Charset.C, i)) {
                Latch latch4 = Latch.NONE;
                if (charset != Charset.C) {
                    latch4 = Latch.C;
                    i2 = 2;
                } else {
                    i2 = 1;
                }
                int i8 = (charSequence.charAt(i) != 241 ? 2 : 1) + i;
                if (i8 < charSequence.length()) {
                    i2 += encode(charSequence, Charset.C, i8);
                }
                if (i2 < i6) {
                    latch = latch4;
                    i6 = i2;
                }
            }
            if (i6 == Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Bad character in input: ASCII value=" + ((int) charSequence.charAt(i)));
            }
            this.memoizedCost[charset.ordinal()][i] = i6;
            this.minPath[charset.ordinal()][i] = latch;
            return i6;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.oned.Code128Writer$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset;
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch;

        static {
            int[] iArr = new int[MinimalEncoder.Charset.values().length];
            $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset = iArr;
            try {
                iArr[MinimalEncoder.Charset.A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset[MinimalEncoder.Charset.B.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset[MinimalEncoder.Charset.C.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[MinimalEncoder.Latch.values().length];
            $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch = iArr2;
            try {
                iArr2[MinimalEncoder.Latch.A.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch[MinimalEncoder.Latch.B.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch[MinimalEncoder.Latch.C.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch[MinimalEncoder.Latch.SHIFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
