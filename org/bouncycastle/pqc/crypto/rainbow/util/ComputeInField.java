package org.bouncycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

/* loaded from: classes6.dex */
public class ComputeInField {
    private short[][] A;
    short[] x;

    private void computeZerosAbove() throws RuntimeException {
        for (int length = this.A.length - 1; length > 0; length--) {
            for (int i = length - 1; i >= 0; i--) {
                short[][] sArr = this.A;
                short s = sArr[i][length];
                short invElem = GF2Field.invElem(sArr[length][length]);
                if (invElem == 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                int i2 = length;
                while (true) {
                    short[][] sArr2 = this.A;
                    if (i2 < sArr2.length * 2) {
                        short multElem = GF2Field.multElem(s, GF2Field.multElem(sArr2[length][i2], invElem));
                        short[][] sArr3 = this.A;
                        sArr3[i][i2] = GF2Field.addElem(sArr3[i][i2], multElem);
                        i2++;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0058, code lost:
    
        r0 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void computeZerosUnder(boolean z) throws RuntimeException {
        int length = z ? this.A.length * 2 : this.A.length + 1;
        int i = 0;
        while (i < this.A.length - 1) {
            int i2 = i + 1;
            int i3 = i2;
            while (true) {
                short[][] sArr = this.A;
                if (i3 < sArr.length) {
                    short s = sArr[i3][i];
                    short invElem = GF2Field.invElem(sArr[i][i]);
                    if (invElem == 0) {
                        throw new IllegalStateException("Matrix not invertible! We have to choose another one!");
                    }
                    for (int i4 = i; i4 < length; i4++) {
                        short multElem = GF2Field.multElem(s, GF2Field.multElem(this.A[i][i4], invElem));
                        short[][] sArr2 = this.A;
                        sArr2[i3][i4] = GF2Field.addElem(sArr2[i3][i4], multElem);
                    }
                    i3++;
                }
            }
        }
    }

    private void substitute() throws IllegalStateException {
        short invElem = GF2Field.invElem(this.A[r0.length - 1][r0.length - 1]);
        if (invElem == 0) {
            throw new IllegalStateException("The equation system is not solvable");
        }
        short[] sArr = this.x;
        short[][] sArr2 = this.A;
        sArr[sArr2.length - 1] = GF2Field.multElem(sArr2[sArr2.length - 1][sArr2.length], invElem);
        for (int length = this.A.length - 2; length >= 0; length--) {
            short[][] sArr3 = this.A;
            short s = sArr3[length][sArr3.length];
            for (int length2 = sArr3.length - 1; length2 > length; length2--) {
                s = GF2Field.addElem(s, GF2Field.multElem(this.A[length][length2], this.x[length2]));
            }
            short invElem2 = GF2Field.invElem(this.A[length][length]);
            if (invElem2 == 0) {
                throw new IllegalStateException("Not solvable equation system");
            }
            this.x[length] = GF2Field.multElem(s, invElem2);
        }
    }

    public short[][] addSquareMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr.length != sArr2.length || sArr[0].length != sArr2[0].length) {
            throw new RuntimeException("Addition is not possible!");
        }
        short[][] sArr3 = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr.length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i][i2] = GF2Field.addElem(sArr[i][i2], sArr2[i][i2]);
            }
        }
        return sArr3;
    }

    public short[] addVect(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        int length = sArr.length;
        short[] sArr3 = new short[length];
        for (int i = 0; i < length; i++) {
            sArr3[i] = GF2Field.addElem(sArr[i], sArr2[i]);
        }
        return sArr3;
    }

    public short[][] inverse(short[][] sArr) {
        try {
            int i = 0;
            this.A = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr.length * 2);
            if (sArr.length != sArr[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr.length; i3++) {
                    this.A[i2][i3] = sArr[i2][i3];
                }
                for (int length = sArr.length; length < sArr.length * 2; length++) {
                    this.A[i2][length] = 0;
                }
                short[][] sArr2 = this.A;
                sArr2[i2][sArr2.length + i2] = 1;
            }
            computeZerosUnder(true);
            int i4 = 0;
            while (true) {
                short[][] sArr3 = this.A;
                if (i4 >= sArr3.length) {
                    break;
                }
                short invElem = GF2Field.invElem(sArr3[i4][i4]);
                int i5 = i4;
                while (true) {
                    short[][] sArr4 = this.A;
                    if (i5 < sArr4.length * 2) {
                        sArr4[i4][i5] = GF2Field.multElem(sArr4[i4][i5], invElem);
                        i5++;
                    }
                }
                i4++;
            }
            computeZerosAbove();
            short[][] sArr5 = this.A;
            short[][] sArr6 = (short[][]) Array.newInstance((Class<?>) short.class, sArr5.length, sArr5.length);
            while (true) {
                short[][] sArr7 = this.A;
                if (i >= sArr7.length) {
                    return sArr6;
                }
                int length2 = sArr7.length;
                while (true) {
                    short[][] sArr8 = this.A;
                    if (length2 < sArr8.length * 2) {
                        sArr6[i][length2 - sArr8.length] = sArr8[i][length2];
                        length2++;
                    }
                }
                i++;
            }
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short[][] multMatrix(short s, short[][] sArr) {
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr[0].length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr[0].length; i2++) {
                sArr2[i][i2] = GF2Field.multElem(s, sArr[i][i2]);
            }
        }
        return sArr2;
    }

    public short[] multVect(short s, short[] sArr) {
        int length = sArr.length;
        short[] sArr2 = new short[length];
        for (int i = 0; i < length; i++) {
            sArr2[i] = GF2Field.multElem(s, sArr[i]);
        }
        return sArr2;
    }

    public short[][] multVects(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[][] sArr3 = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr2.length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i][i2] = GF2Field.multElem(sArr[i], sArr2[i2]);
            }
        }
        return sArr3;
    }

    public short[] multiplyMatrix(short[][] sArr, short[] sArr2) throws RuntimeException {
        if (sArr[0].length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] sArr3 = new short[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i] = GF2Field.addElem(sArr3[i], GF2Field.multElem(sArr[i][i2], sArr2[i2]));
            }
        }
        return sArr3;
    }

    public short[][] multiplyMatrix(short[][] sArr, short[][] sArr2) throws RuntimeException {
        if (sArr[0].length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        this.A = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr2[0].length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                for (int i3 = 0; i3 < sArr2[0].length; i3++) {
                    short multElem = GF2Field.multElem(sArr[i][i2], sArr2[i2][i3]);
                    short[][] sArr3 = this.A;
                    sArr3[i][i3] = GF2Field.addElem(sArr3[i][i3], multElem);
                }
            }
        }
        return this.A;
    }

    public short[] solveEquation(short[][] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return null;
        }
        try {
            this.A = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr.length + 1);
            this.x = new short[sArr.length];
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr[0].length; i2++) {
                    this.A[i][i2] = sArr[i][i2];
                }
            }
            for (int i3 = 0; i3 < sArr2.length; i3++) {
                short[][] sArr3 = this.A;
                sArr3[i3][sArr2.length] = GF2Field.addElem(sArr2[i3], sArr3[i3][sArr2.length]);
            }
            computeZerosUnder(false);
            substitute();
            return this.x;
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
