package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* loaded from: classes6.dex */
public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private short[][] A1;
    private short[][] A1inv;
    private short[][] A2;
    private short[][] A2inv;
    private short[] b1;
    private short[] b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;
    private SecureRandom sr;
    private int[] vi;

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance((Class<?>) short.class, length, ((length2 + 1) * length2) / 2);
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            for (int i3 = 0; i3 < length2; i3++) {
                for (int i4 = i3; i4 < length2; i4++) {
                    short[][] sArr2 = this.pub_quadratic;
                    if (i4 == i3) {
                        sArr2[i][i2] = sArr[i][i3][i4];
                    } else {
                        sArr2[i][i2] = GF2Field.addElem(sArr[i][i3][i4], sArr[i][i4][i3]);
                    }
                    i2++;
                }
            }
        }
    }

    private void computePublicKey() {
        Class<short> cls;
        Class<short> cls2 = short.class;
        ComputeInField computeInField = new ComputeInField();
        int[] iArr = this.vi;
        int i = 0;
        int i2 = iArr[iArr.length - 1] - iArr[0];
        int i3 = iArr[iArr.length - 1];
        int i4 = 3;
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) cls2, i2, i3, i3);
        this.pub_singular = (short[][]) Array.newInstance((Class<?>) cls2, i2, i3);
        this.pub_scalar = new short[i2];
        short[] sArr2 = new short[i3];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (i5 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[i5].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[i5].getCoeffBeta();
            short[][] coeffGamma = this.layers[i5].getCoeffGamma();
            short[] coeffEta = this.layers[i5].getCoeffEta();
            int length = coeffAlpha[i].length;
            int length2 = coeffBeta[i].length;
            while (i < length) {
                int i7 = 0;
                while (true) {
                    cls = cls2;
                    if (i7 >= length) {
                        break;
                    }
                    int i8 = 0;
                    while (i8 < length2) {
                        int i9 = i2;
                        int i10 = i3;
                        int i11 = i7 + length2;
                        short[] multVect = computeInField.multVect(coeffAlpha[i][i7][i8], this.A2[i11]);
                        int i12 = i6 + i;
                        int i13 = i5;
                        sArr[i12] = computeInField.addSquareMatrix(sArr[i12], computeInField.multVects(multVect, this.A2[i8]));
                        short[] multVect2 = computeInField.multVect(this.b2[i8], multVect);
                        short[][] sArr3 = this.pub_singular;
                        sArr3[i12] = computeInField.addVect(multVect2, sArr3[i12]);
                        short[] multVect3 = computeInField.multVect(this.b2[i11], computeInField.multVect(coeffAlpha[i][i7][i8], this.A2[i8]));
                        short[][] sArr4 = this.pub_singular;
                        sArr4[i12] = computeInField.addVect(multVect3, sArr4[i12]);
                        short multElem = GF2Field.multElem(coeffAlpha[i][i7][i8], this.b2[i11]);
                        short[] sArr5 = this.pub_scalar;
                        sArr5[i12] = GF2Field.addElem(sArr5[i12], GF2Field.multElem(multElem, this.b2[i8]));
                        i8++;
                        i3 = i10;
                        i2 = i9;
                        coeffAlpha = coeffAlpha;
                        i5 = i13;
                        coeffEta = coeffEta;
                    }
                    i7++;
                    cls2 = cls;
                }
                int i14 = i3;
                int i15 = i2;
                int i16 = i5;
                short[][][] sArr6 = coeffAlpha;
                short[] sArr7 = coeffEta;
                for (int i17 = 0; i17 < length2; i17++) {
                    for (int i18 = 0; i18 < length2; i18++) {
                        short[] multVect4 = computeInField.multVect(coeffBeta[i][i17][i18], this.A2[i17]);
                        int i19 = i6 + i;
                        sArr[i19] = computeInField.addSquareMatrix(sArr[i19], computeInField.multVects(multVect4, this.A2[i18]));
                        short[] multVect5 = computeInField.multVect(this.b2[i18], multVect4);
                        short[][] sArr8 = this.pub_singular;
                        sArr8[i19] = computeInField.addVect(multVect5, sArr8[i19]);
                        short[] multVect6 = computeInField.multVect(this.b2[i17], computeInField.multVect(coeffBeta[i][i17][i18], this.A2[i18]));
                        short[][] sArr9 = this.pub_singular;
                        sArr9[i19] = computeInField.addVect(multVect6, sArr9[i19]);
                        short multElem2 = GF2Field.multElem(coeffBeta[i][i17][i18], this.b2[i17]);
                        short[] sArr10 = this.pub_scalar;
                        sArr10[i19] = GF2Field.addElem(sArr10[i19], GF2Field.multElem(multElem2, this.b2[i18]));
                    }
                }
                for (int i20 = 0; i20 < length2 + length; i20++) {
                    short[] multVect7 = computeInField.multVect(coeffGamma[i][i20], this.A2[i20]);
                    short[][] sArr11 = this.pub_singular;
                    int i21 = i6 + i;
                    sArr11[i21] = computeInField.addVect(multVect7, sArr11[i21]);
                    short[] sArr12 = this.pub_scalar;
                    sArr12[i21] = GF2Field.addElem(sArr12[i21], GF2Field.multElem(coeffGamma[i][i20], this.b2[i20]));
                }
                short[] sArr13 = this.pub_scalar;
                int i22 = i6 + i;
                sArr13[i22] = GF2Field.addElem(sArr13[i22], sArr7[i]);
                i++;
                cls2 = cls;
                i3 = i14;
                i2 = i15;
                coeffAlpha = sArr6;
                i5 = i16;
                coeffEta = sArr7;
            }
            i6 += length;
            i5++;
            i = 0;
            i4 = 3;
        }
        Class<short> cls3 = cls2;
        int i23 = i3;
        int i24 = i2;
        int[] iArr2 = new int[i4];
        iArr2[2] = i23;
        iArr2[1] = i23;
        iArr2[0] = i24;
        short[][][] sArr14 = (short[][][]) Array.newInstance((Class<?>) cls3, iArr2);
        short[][] sArr15 = (short[][]) Array.newInstance((Class<?>) cls3, i24, i23);
        short[] sArr16 = new short[i24];
        for (int i25 = 0; i25 < i24; i25++) {
            int i26 = 0;
            while (true) {
                short[][] sArr17 = this.A1;
                if (i26 < sArr17.length) {
                    sArr14[i25] = computeInField.addSquareMatrix(sArr14[i25], computeInField.multMatrix(sArr17[i25][i26], sArr[i26]));
                    sArr15[i25] = computeInField.addVect(sArr15[i25], computeInField.multVect(this.A1[i25][i26], this.pub_singular[i26]));
                    sArr16[i25] = GF2Field.addElem(sArr16[i25], GF2Field.multElem(this.A1[i25][i26], this.pub_scalar[i26]));
                    i26++;
                }
            }
            sArr16[i25] = GF2Field.addElem(sArr16[i25], this.b1[i25]);
        }
        this.pub_singular = sArr15;
        this.pub_scalar = sArr16;
        compactPublicKey(sArr14);
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int i = 0;
        while (i < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] iArr = this.vi;
            int i2 = i + 1;
            layerArr[i] = new Layer(iArr[i], iArr[i2], this.sr);
            i = i2;
        }
    }

    private void generateL1() {
        int[] iArr = this.vi;
        int i = iArr[iArr.length - 1] - iArr[0];
        this.A1 = (short[][]) Array.newInstance((Class<?>) short.class, i, i);
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.A1[i2][i3] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.A1);
        }
        this.b1 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.b1[i4] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int[] iArr = this.vi;
        int i = iArr[iArr.length - 1];
        this.A2 = (short[][]) Array.newInstance((Class<?>) short.class, i, i);
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A2inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.A2[i2][i3] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.A2);
        }
        this.b2 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.b2[i4] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(CryptoServicesRegistrar.getSecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
        int[] iArr = this.vi;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(iArr[iArr.length - 1] - iArr[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) rainbowPrivateKeyParameters);
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.rainbowParams = rainbowKeyGenerationParameters;
        this.sr = rainbowKeyGenerationParameters.getRandom();
        this.vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }
}
